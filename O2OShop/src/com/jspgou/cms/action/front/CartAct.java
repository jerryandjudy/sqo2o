package com.jspgou.cms.action.front;

import static com.jspgou.cms.Constants.MEMBER_SYS;
import static com.jspgou.core.web.Constants.BACK_URL;

import java.math.BigDecimal;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.json.JSONException;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import com.jspgou.cms.entity.Address;
import com.jspgou.cms.entity.Cart;
import com.jspgou.cms.entity.CartItem;
import com.jspgou.cms.entity.Order;
import com.jspgou.cms.entity.OrderItem;
import com.jspgou.cms.entity.Payment;
import com.jspgou.cms.entity.PopularityGroup;
import com.jspgou.cms.entity.PopularityItem;
import com.jspgou.cms.entity.Product;
import com.jspgou.cms.entity.ProductFashion;
import com.jspgou.cms.entity.ProductSite;
import com.jspgou.cms.entity.Shipping;
import com.jspgou.cms.entity.ShopMember;
import com.jspgou.cms.entity.ShopMemberAddress;
import com.jspgou.cms.entity.ZfbWy;
import com.jspgou.cms.manager.AddressMng;
import com.jspgou.cms.manager.CartItemMng;
import com.jspgou.cms.manager.CartMng;
import com.jspgou.cms.manager.MemberCouponMng;
import com.jspgou.cms.manager.OrderMng;
import com.jspgou.cms.manager.PaymentMng;
import com.jspgou.cms.manager.PopularityGroupMng;
import com.jspgou.cms.manager.PopularityItemMng;
import com.jspgou.cms.manager.ProductFashionMng;
import com.jspgou.cms.manager.ProductMng;
import com.jspgou.cms.manager.ProductSiteMng;
import com.jspgou.cms.manager.ShippingMng;
import com.jspgou.cms.manager.ShopMemberAddressMng;
import com.jspgou.cms.manager.ZfbWyMng;
import com.jspgou.cms.service.ShoppingSvc;
import com.jspgou.cms.web.ShopFrontHelper;
import com.jspgou.cms.web.SiteUtils;
import com.jspgou.cms.web.threadvariable.MemberThread;
import com.jspgou.common.web.ResponseUtils;
import com.jspgou.common.web.springmvc.MessageResolver;
import com.jspgou.core.entity.User;
import com.jspgou.core.entity.Website;

/**
* This class should preserve.
* @preserve
*/
@Controller
public class CartAct {
	private static final Logger log = LoggerFactory.getLogger(CartAct.class);
	
	private static final String SHOPPING_CART = "tpl.shoppingCart";
	private static final String CHECKOUT_SHIPPING = "tpl.checkoutShipping";
	
	//购物车
	@RequestMapping(value="/cart/shopping_cart.jspx")
	public String shoppingCart(String backUrl, HttpServletRequest request,HttpServletResponse response, ModelMap model) {
		ShopMember member = MemberThread.get();
		User user = SiteUtils.getUser(request);
		if(member == null || member.getUsername()==null||user==null){
			return "redirect:../login.jspx";
		}
		Website web = SiteUtils.getWeb(request);
		Cart cart = shoppingSvc.getCart(member.getId(), web.getId());
		List<PopularityItem> popularityItems = null;
		if(cart!=null){
			popularityItems=popularityItemMng.getlist(cart.getId(),null);
		}
		model.addAttribute("cart", cart);
		if (!StringUtils.isBlank(backUrl)) {
			model.addAttribute(BACK_URL, backUrl);
		}
		model.addAttribute("popularityItems", popularityItems);
		ShopFrontHelper.setCommonData(request, model, web, 1);
		return web.getTplSys(MEMBER_SYS, MessageResolver.getMessage(request,SHOPPING_CART));
	}
	
	
	

	//加入购物车
	@RequestMapping(value = "/cart/add_orderItem.jspx")
	public void addToCart(Long productId,Integer productAmount,Long fashId,
			HttpServletRequest request, HttpServletResponse response,
			ModelMap model) throws JSONException {
		Website web = SiteUtils.getWeb(request);
		// 0:未登录;1:正常,商品总数;2:数量过大,最大数量;
		ShopMember member = MemberThread.get();
		JSONObject json=new JSONObject();
		User user = SiteUtils.getUser(request);
		if(member == null || member.getUsername()==null||user==null){
			json.put("status", 0);
			ResponseUtils.renderJson(response, json.toString());
			return;
		}else{
		    ProductSite productSite=productSiteMng.findById(productId);
				  Cart cart = shoppingSvc.collectAddToCart(productSite,fashId,null,productAmount,true,member, web, request, response);
				  json.put("status", 1);
				  json.put("count", cart.getTotalItems());
	   }
		log.info("add to cart productId={}, count={}", productId, productAmount);
		 ResponseUtils.renderJson(response, json.toString());
	}
	
	//加入购物车
	@RequestMapping(value = "/cart/add_popularity.jspx")
	public void addToPopularity(Long popularityId,HttpServletRequest request, HttpServletResponse response,
			ModelMap model) throws JSONException {
		Website web = SiteUtils.getWeb(request);
		// 0:未登录;1:正常,商品总数;2:数量过大,最大数量;
		ShopMember member = MemberThread.get();
		JSONObject json=new JSONObject();
		User user = SiteUtils.getUser(request);
		if(member == null || member.getUsername()==null||user==null){
			json.put("status", 0);
		}else{
			if(getinventory(popularityId)){
				Cart cart = null;
				for(Product product:popularityGroupMng.findById(popularityId).getProducts()){
					cart = shoppingSvc.collectAddToCart(product,null,popularityId,1,true,member, web, request, response);
				}
				popularityItemMng.save(cart, popularityId);
				json.put("status", 1);
				json.put("count", cart.getTotalItems());
			}else{
				json.put("status", 2);
				json.put("error", "库存不足");
			}
		}	
		ResponseUtils.renderJson(response, json.toString());
	}
	
	
	public boolean getinventory(Long popularityId){
		for(Product product:popularityGroupMng.findById(popularityId).getProducts()){
//			if(1>product.getStockCount()){
//				return false;
//			}
		}
		return true;
	}

	
	//订单再次加入购物车
	@RequestMapping(value = "/cart/add_orderItem1.jspx")
	public String orderAddToCart(Long orderId,Boolean isAdd,
			HttpServletRequest request, HttpServletResponse response,
			ModelMap model) throws JSONException {
		Website web = SiteUtils.getWeb(request);
		// 0:未登录;1:正常,商品总数;2:数量过大,最大数量;
		User user = SiteUtils.getUser(request);
		ShopMember member = MemberThread.get();
		if(member == null || member.getUsername()==null||user==null){
			return "redirect:../login.jspx";
		}
		Order order=orderMng.findById(orderId);
		ProductSite productSite=null;
		Integer productAmount=0;
		Long fashId=null;
		Cart cart=null;
		for(OrderItem item: order.getItems()){
			productSite=item.getProductSite();
			productAmount=item.getCount();
			if(item.getProductFash()!=null){
				fashId=item.getProductFash().getId();
			}
			cart = shoppingSvc.collectAddToCart(productSite,fashId,null,productAmount,true,member, web, request, response);
		}
		return "redirect:shopping_cart.jspx";
	}
	
	//更新购物车项
	@RequestMapping(value="/cart/ajaxUpdateCartItem.jspx")
	public void ajaxUpdateCartItem(Long cartItemId,Integer count,
			HttpServletRequest request, HttpServletResponse response,
			ModelMap model) throws JSONException {
		ShopMember member = MemberThread.get();
		JSONObject json=new JSONObject();
		User user = SiteUtils.getUser(request);
		if(member == null || member.getUsername()==null||user==null){
			json.put("status", 0);
		}
		CartItem cartItem=cartItemMng.findById(cartItemId);
		Integer oldCount = cartItem.getCount();
		
		cartItem.setCount(count);
		cartItem.setScore(cartItem.getProduct().getScore()*count);
		Cart cart=cartItem.getCart();
		if(oldCount>count){
			cart.setTotalItems(cart.getTotalItems()-(oldCount-count));
		}else{
			cart.setTotalItems(cart.getTotalItems()+(count-oldCount));
		}
		if(count<1){
			PopularityGroup popularityGroup =  cartItem.getPopularityGroup();    
			cart.getItems().remove(cartItem);
			cart=cartMng.update(cart);
			if(cart!=null&&popularityGroup!=null){
				List<CartItem> list = cartItemMng.getlist(cart.getId(), popularityGroup.getId());
				list.remove(cartItem);
				for(CartItem item:list){
					item.setPopularityGroup(null);
					cartItemMng.updateByUpdater(item);
				}
				update(cart,popularityGroup);
			}
		}else{
		cartItemMng.updateByUpdater(cartItem);
		}
		log.info("update to cartItem cartItemId={}", cartItemId);
		json.put("status", 1);
		json.put("subtotal", cartItem.getCart().getSubtotal());
		json.put("info", count);
		json.put("count", cartItem.getCart().getTotalItems());
		ResponseUtils.renderJson(response, json.toString());
	}
	
	//删除购物车项
	@RequestMapping(value = "/cart/ajaxDeleteCartItem.jspx")
	public void ajaxDeleteCartItem(Long cartItemId,
			HttpServletRequest request, HttpServletResponse response,
			ModelMap model) throws JSONException {
		ShopMember member = MemberThread.get();
		JSONObject json=new JSONObject();
		User user = SiteUtils.getUser(request);
		if(member == null || member.getUsername()==null||user==null){
			json.put("status", 0);
		}
		CartItem cartItem=cartItemMng.findById(cartItemId);
		Cart cart=cartItem.getCart();
		PopularityGroup popularityGroup =  cartItem.getPopularityGroup();    
		cart.getItems().remove(cartItem);
		cartMng.update(cart);
		if(cart!=null&&popularityGroup!=null){
			List<CartItem> list = cartItemMng.getlist(cart.getId(), popularityGroup.getId());
			list.remove(cartItem);
			for(CartItem item:list){
				item.setPopularityGroup(null);
				cartItemMng.updateByUpdater(item);
			}
			update(cart,popularityGroup);
		}
		log.info("delete to cartItem cartItemId={}", cartItemId);
		json.put("status", 1);
		ResponseUtils.renderJson(response, json.toString());
	}
	//wap清空购物车项
	@RequestMapping(value = "/cart/ajaxDeleteAllCartItem.jspx")
	public void ajaxDeleteAllCartItem(
			HttpServletRequest request, HttpServletResponse response,
			ModelMap model) throws JSONException {
		ShopMember member = MemberThread.get();
		JSONObject json=new JSONObject();
		User user = SiteUtils.getUser(request);
		if(member == null || member.getUsername()==null||user==null){
			json.put("status", 0);
			ResponseUtils.renderJson(response, json.toString());
			return;
		}
		Website web = SiteUtils.getWeb(request);
		Cart cart=cartMng.findById(member.getId(), web.getId());
		cart.getItems().clear();
		cart.setTotalItems(0);
		cartMng.update(cart);
		json.put("status", 1);
		ResponseUtils.renderJson(response, json.toString());
	}
	
	
	
	public void update(Cart cart,PopularityGroup popularityGroup){
		if(popularityGroup!=null){
			PopularityItem popularityItem = popularityItemMng.findById(cart.getId(), popularityGroup.getId());	
			if(popularityItem!=null){
				popularityItemMng.deleteById(popularityItem.getId());
			}
			
		}
	}
	
	
	//检查商品库存
	@RequestMapping(value = "/cart/checkStockCount.jspx")
	public void checkStockCount(Long productId,String productFashionId,Integer count,
			HttpServletRequest request, HttpServletResponse response,
			ModelMap model) throws JSONException {
		ShopMember member = MemberThread.get();
		JSONObject json=new JSONObject();
		User user = SiteUtils.getUser(request);
		if(member == null || member.getUsername()==null||user==null){
			json.put("status", 0);
		}else{
            Product product=productMng.findById(productId);
           if(productFashionId.equals("undefined")){
//              if(count>product.getStockCount()){
//         	     json.put("status", 2);
//         	     json.put("error",product.getName()+"该商品库存不足。");
//              }else{
//         	    json.put("status", 1);
//              }
           }else{
        	   ProductFashion productFashion=productFashionMng.findById(Long.parseLong(productFashionId)); 
        	   if(count>productFashion.getStockCount()){
        		   json.put("error",product.getName()+productFashion.getAttitude()+"该款式库存不足。");
        		   json.put("status", 2);
        	   }else{
        	 	   json.put("status", 1);
        	   }
         }
	   }
       ResponseUtils.renderJson(response, json.toString());
	}
	
	//计算运费
	@RequestMapping(value = "/cart/ajaxtotalDeliveryFee.jspx")
	public void ajaxtotalDeliveryFee(Long deliveryMethod,Double weight,
			HttpServletRequest request, HttpServletResponse response,
			ModelMap model) throws JSONException {
		ShopMember member = MemberThread.get();
		JSONObject json=new JSONObject();
		User user = SiteUtils.getUser(request);
		if(member == null || member.getUsername()==null||user==null){
			json.put("status", 0);
		}
		Shipping shipping=shippingMng.findById(deliveryMethod);
		//计算运费
		Double freight=shipping.calPrice(weight);
		json.put("status", 1);
		json.put("freight", freight);
		ResponseUtils.renderJson(response, json.toString());
	}
	
	//选择收货地址、付款方式、配送方式
	@RequestMapping(value = "/cart/checkout_shipping.jspx")
	public String shippingInput(Long[] cart2Checkbox,HttpServletRequest request,
			HttpServletResponse response, ModelMap model) {
		Website web = SiteUtils.getWeb(request);
		ShopMember member = MemberThread.get();
		User user = SiteUtils.getUser(request);
		if(member == null || member.getUsername()==null||user==null){					
			return "redirect:../login.jspx";
		}
		Cart cart = shoppingSvc.getCart(member.getId(), web.getId());
		if (cart == null) {
			return "redirect:shopping_cart.jspx";
		}
		List<PopularityItem> popularityItems = null;
		Double  popularityPrice =0.0;
		if(cart!=null){
			popularityItems=popularityItemMng.getlist(cart.getId(),null);
			for(PopularityItem popularityItem:popularityItems){
				popularityPrice+=popularityItem.getPopularityGroup().getPrivilege()*popularityItem.getCount();
			}
		}
		
		Set<CartItem> items = getItems(cart2Checkbox,cart);
		Double price=getPrice(items);
       //配送方式
		List<Shipping> splist=shippingMng.getList(null, false);
		//会员地址
		List<ShopMemberAddress> smalist = shopMemberAddressMng.getList(member.getId(),web.getId());
		//所处省份
		List<Address> plist=addressMng.getListBySiteId(web.getId());
		//付款方式
		List<Payment> paylist=paymentMng.getList(null, true);
		
    	model.addAttribute("memberCouponlist", memberCouponMng.getList(member.getId(),new BigDecimal(price)));
		model.addAttribute("items", getItems(cart2Checkbox,cart));
		model.addAttribute("smalist", smalist);
		model.addAttribute("plist", plist);
		model.addAttribute("paylist", paylist);
	
		model.addAttribute("splist", splist);
		model.addAttribute("popularityPrice", popularityPrice);
		ShopFrontHelper.setCommonData(request, model, web, 1);
		return web.getTplSys(MEMBER_SYS, MessageResolver.getMessage(request,CHECKOUT_SHIPPING));
	}

	public java.util.Set<com.jspgou.cms.entity.CartItem> getItems(Long[] cart2Checkbox,Cart cart) {
		Set<CartItem> items = new HashSet<CartItem>();
        if(cart2Checkbox!=null){
        	for(Long id:cart2Checkbox){
        		items.add(cartItemMng.findById(id));
        	}
		}else{
			items = cart.getItems();
		}
		return items;
	}
	
	public Double getPrice(Set<CartItem> items){
		Double price=0.00;
		 for (CartItem item : items) {
	        	if(item.getProductFash()!=null){
					 price+=item.getProductFash().getSalePrice()*item.getCount();
				}else{
				     price+=item.getProductSite().getSalePrice()*item.getCount();
				}
	        }
		return price;
	}
	
	@Autowired
	private OrderMng orderMng;
	@Autowired
	private ShoppingSvc shoppingSvc;
	@Autowired
	private ProductMng productMng;
	@Autowired
	private ProductSiteMng productSiteMng;
	@Autowired
	private ProductFashionMng productFashionMng;
	@Autowired
	private CartItemMng cartItemMng;
	@Autowired
	private CartMng cartMng;
	@Autowired
	private AddressMng addressMng;
	@Autowired
	private PaymentMng paymentMng;
	@Autowired
	private ShippingMng shippingMng;
	@Autowired
	private ShopMemberAddressMng shopMemberAddressMng;
	@Autowired
	private MemberCouponMng memberCouponMng;
	@Autowired
	private PopularityGroupMng popularityGroupMng;
	@Autowired
	private PopularityItemMng popularityItemMng;
	@Autowired
	private ZfbWyMng zfbWyMng;
}
