package com.jspgou.cms.action.front;

import static com.jspgou.cms.Constants.MEMBER_SYS;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Set;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.jspgou.cms.entity.Address;
import com.jspgou.cms.entity.Cart;
import com.jspgou.cms.entity.Order;
import com.jspgou.cms.entity.OrderItem;
import com.jspgou.cms.entity.OrderReturn;
import com.jspgou.cms.entity.Payment;
import com.jspgou.cms.entity.PaymentPlugins;
import com.jspgou.cms.entity.Shipping;
import com.jspgou.cms.entity.ShopMember;
import com.jspgou.cms.entity.ShopMemberAddress;
import com.jspgou.cms.entity.ShopScore;
import com.jspgou.cms.entity.SqOrder;
import com.jspgou.cms.entity.SqService;
import com.jspgou.cms.entity.ZfbWy;
import com.jspgou.cms.manager.AddressMng;
import com.jspgou.cms.manager.JxcBldGoodsNumMng;
import com.jspgou.cms.manager.OrderMng;
import com.jspgou.cms.manager.OrderReturnMng;
import com.jspgou.cms.manager.PaymentMng;
import com.jspgou.cms.manager.PaymentPluginsMng;
import com.jspgou.cms.manager.ProductFashionMng;
import com.jspgou.cms.manager.ProductMng;
import com.jspgou.cms.manager.ShippingMng;
import com.jspgou.cms.manager.ShopMemberAddressMng;
import com.jspgou.cms.manager.ShopMemberMng;
import com.jspgou.cms.manager.ShopScoreMng;
import com.jspgou.cms.manager.SqOrderMng;
import com.jspgou.cms.manager.SqServiceMng;
import com.jspgou.cms.manager.ZfbWyMng;
import com.jspgou.cms.service.ShoppingSvc;
import com.jspgou.cms.web.ShopFrontHelper;
import com.jspgou.cms.web.SiteUtils;
import com.jspgou.cms.web.threadvariable.MemberThread;
import com.jspgou.common.page.Pagination;
import com.jspgou.common.security.annotation.Secured;
import com.jspgou.common.web.ResponseUtils;
import com.jspgou.common.web.springmvc.MessageResolver;
import com.jspgou.core.entity.User;
import com.jspgou.core.entity.Website;
import com.jspgou.core.web.WebErrors;
import com.jspgou.core.web.front.FrontHelper;
import com.jspgou.core.web.front.URLHelper;

/**
* This class should preserve.
* @preserve
*/
@Secured
@Controller
public class OrderAct {
	/**
	 * 我的订单
	 */
	private static final String MY_ORDER = "tpl.myOrder";
	private static final String MY_FWORDER = "tpl.myFwOrder";
	private static final String MY_RETURN_ORDER = "tpl.myReturnOrder";
	private static final String MY_ORDER_VIEW = "tpl.myOrderView";
	private static final String SUCCESSFULLY_ORDER = "tpl.successfullyOrder";
	private static final String FWORDER = "tpl.fwOrder";

	/**
	 * 订单列表
	 */
	@RequestMapping(value = "/order/myorder*.jspx")
	public String myOrder(Integer status,String code,String userName, Long paymentId,
			Long shippingId, String startTime,String endTime,Double startOrderTotal,
			Double endOrderTotal,HttpServletRequest request, ModelMap model) {
		Website web = SiteUtils.getWeb(request);
		ShopMember member = MemberThread.get();
		User user = SiteUtils.getUser(request);
		if(member == null || member.getUsername()==null||user==null){
			return "redirect:../login.jspx";
		}
		if(StringUtils.isBlank(userName)){
			userName = null;
		}
		if(StringUtils.isBlank(startTime)){
			startTime = null;
		}
		if(StringUtils.isBlank(endTime)){
			endTime = null;
		}
		List<Shipping> shippingList = shippingMng.getList(web.getId(), true);
		List<Payment> paymentList = paymentMng.getList(web.getId(), true);
		model.addAttribute("historyProductIds", getHistoryProductIds(request));
		model.addAttribute("paymentList", paymentList);
		model.addAttribute("shippingList", shippingList);
		model.addAttribute("status", status);
		model.addAttribute("code",code);
		model.addAttribute("userName", userName);
		model.addAttribute("startTime", startTime);
		model.addAttribute("endTime", endTime);
		model.addAttribute("paymentId", paymentId);
		model.addAttribute("shippingId", shippingId);
		model.addAttribute("startOrderTotal",startOrderTotal);
		model.addAttribute("endOrderTotal", endOrderTotal);
		Integer pageNo = URLHelper.getPageNo(request);
		ShopFrontHelper.setCommonData(request, model, web, 1);
		ShopFrontHelper.setDynamicPageData(request, model, web, "", "myorder",".jspx", pageNo);
		return web.getTplSys(MEMBER_SYS, MessageResolver.getMessage(request,MY_ORDER));
	}
	/**
	 * 服务订单列表
	 */
	@RequestMapping(value = "/order/myfworder*.jspx")
	public String myOrder(Integer status,String code,HttpServletRequest request, ModelMap model) {
		Website web = SiteUtils.getWeb(request);
		ShopMember member = MemberThread.get();
		User user = SiteUtils.getUser(request);
		if(member == null || member.getUsername()==null||user==null){
			return "redirect:../login.jspx";
		}
		
		model.addAttribute("status", status);
		model.addAttribute("code",code);
		Integer pageNo = URLHelper.getPageNo(request);
		ShopFrontHelper.setCommonData(request, model, web, 1);
		ShopFrontHelper.setDynamicPageData(request, model, web, "", "myfworder",".jspx", pageNo);
		return web.getTplSys(MEMBER_SYS, MessageResolver.getMessage(request,MY_FWORDER));
	}
	
	/**
	 * 查看订单详情
	 */
	@RequestMapping(value = "/order/myOrderView.jspx")
	public String myOrderView(Long orderId, HttpServletRequest request,
			ModelMap model) {
		Website web = SiteUtils.getWeb(request);
		ShopMember member = MemberThread.get();
		User user = SiteUtils.getUser(request);
		if(member == null || member.getUsername()==null||user==null){
			return "redirect:../login.jspx";
		}
		WebErrors errors = validateOrderView(orderId, member, request);
		if (errors.hasErrors()) {
			return FrontHelper.showError(errors, web, model, request);
		}
		Order order = orderMng.findById(orderId);
		model.addAttribute("order", order);
		ShopFrontHelper.setCommonData(request, model, web, 1);
		return web.getTplSys(MEMBER_SYS, MessageResolver.getMessage(request,MY_ORDER_VIEW));
	}

	/**
	 * 生成订单
	 */
	@RequestMapping(value = "/order/order_shipping.jspx", method = RequestMethod.POST)
	public String orderShipping(Long deliveryInfo,Long shippingMethodId,Long paymentMethodId,Long[] cartItemId,String comments,String memberCouponId,
			HttpServletRequest request,HttpServletResponse response, ModelMap model) {
		Website web = SiteUtils.getWeb(request);
		ShopMember member = MemberThread.get();
		User user = SiteUtils.getUser(request);
		if(member == null || member.getUsername()==null||user==null){
			return "redirect:../login.jspx";
		}
		WebErrors errors = WebErrors.create(request);
		Order order = null;
		Cart cart = shoppingSvc.getCart(member.getId(), web.getId());		
		if(cart!=null){
		   order = orderMng.createOrder(cart, cartItemId, shippingMethodId,deliveryInfo,paymentMethodId,comments,request.getRemoteAddr(), member, web.getId(),memberCouponId);
		   if(order==null){
			   errors.addError("对不起，商品库存不足请修改订单！！");
		   }
		}else{
			errors.addError("购物车无商品，无法生成订单！！");
		}
		if (errors.hasErrors()) {
			model.put("backUrl", "/cart/shopping_cart.jspx");
			return FrontHelper.showError(errors, web, model, request);
		}
		shoppingSvc.clearCookie(request, response);	
		List<PaymentPlugins> list = paymentPluginsMng.getList();
		List<ZfbWy> wylist =zfbWyMng.getList();
		model.addAttribute("wylist", wylist);
		model.addAttribute("list", list);
		model.addAttribute("order", order);
		ShopFrontHelper.setCommonData(request, model, web, 1);
		return web.getTplSys(MEMBER_SYS, MessageResolver.getMessage(request,SUCCESSFULLY_ORDER));
	}
	
	/**
	 * 加入服务订单
	 */
	@RequestMapping(value = "/order/fworder.jspx", method = RequestMethod.GET)
	public String fworder(Long sqServiceId,String returnUrl,HttpServletRequest request,HttpServletResponse response, ModelMap model) {
		Website web = SiteUtils.getWeb(request);
		ShopMember member = MemberThread.get();
		User user = SiteUtils.getUser(request);
		if(member == null || member.getUsername()==null||user==null){
			return "redirect:../login.jspx?returnUrl="+returnUrl+"?";
		}
		WebErrors errors = WebErrors.create(request);
		if(sqServiceId==null || sqServiceMng.getById(sqServiceId)==null){
			errors.addError("该服务不存在，无法生成订单！！");
		}
		if (errors.hasErrors()) {
			return FrontHelper.showError(errors, web, model, request);
		}
		//所处省份
		List<Address> plist=addressMng.getListBySiteId(web.getId());
		//会员地址
		List<ShopMemberAddress> smalist = shopMemberAddressMng.getList(member.getId(),web.getId());
		model.addAttribute("plist", plist);
		model.addAttribute("web", web);
		model.addAttribute("sqService", sqServiceMng.getById(sqServiceId));
		model.addAttribute("returnUrl", "/order/fworder.jspx?sqServiceId="+sqServiceId);
		model.addAttribute("smalist", smalist);
		ShopFrontHelper.setCommonData(request, model, web, 1);
		return web.getTplSys(MEMBER_SYS, MessageResolver.getMessage(request,FWORDER));
	}
	
	/**
	 * 生成订单
	 */
	@RequestMapping(value = "/order/wap_order_shipping.jspx", method = RequestMethod.POST)
	public void wapOrderShipping(Long deliveryInfo,Long shippingMethodId,Long paymentMethodId,Long[] cartItemId,String comments,String memberCouponId,
			HttpServletRequest request,HttpServletResponse response, ModelMap model) {
		Website web = SiteUtils.getWeb(request);
		ShopMember member = MemberThread.get();
			JSONObject json=new JSONObject();
			User user = SiteUtils.getUser(request);
			if(member == null || member.getUsername()==null||user==null){
				try {
					json.put("url", "../wap/waplogin.jspx");
					json.put("info", "nologin");
					json.put("status", false);
				} catch (JSONException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				ResponseUtils.renderJson(response, json.toString());
				return;
			}
		WebErrors errors = WebErrors.create(request);
		Order order = null;
		Cart cart = shoppingSvc.getCart(member.getId(), web.getId());		
		if(cart!=null){
			order = orderMng.createOrder(cart, cartItemId, shippingMethodId,deliveryInfo,paymentMethodId,comments,request.getRemoteAddr(), member, web.getId(),memberCouponId);
			if(order==null){
				errors.addError("对不起，商品库存不足请修改订单！！");
			}
		}else{
			errors.addError("购物车无商品，无法生成订单！！");
		}
		if (errors.hasErrors()) {
//			model.put("backUrl", "/cart/shopping_cart.jspx");
			try {
				json.put("url", "");
				json.put("info", errors.toString());
				json.put("status", false);
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			ResponseUtils.renderJson(response, json.toString());
			return;
			//return FrontHelper.showError(errors, web, model, request);
		}
		String info="";
		shoppingSvc.clearCookie(request, response);	
		List<PaymentPlugins> list = paymentPluginsMng.getList();
		model.addAttribute("list", list);
		model.addAttribute("order", order);
		if(order.getPayment().getId()==3){
			info="ok";
		}
		ShopFrontHelper.setCommonData(request, model, web, 1);
		try {
			json.put("url", "../wap/zffs.jspx?orderId="+order.getId());
			json.put("info", info);
			json.put("status", true);
			ResponseUtils.renderJson(response, json.toString());
			return;
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
//		return web.getTplSys(MEMBER_SYS, MessageResolver.getMessage(request,SUCCESSFULLY_ORDER));
	}
	/**
	 * 生成服务订单
	 */
	@RequestMapping(value = "/order/addSqOrder.jspx", method = RequestMethod.POST)
	public void addSqOrder(Long deliveryInfo,Long sqServiceId,String comments, HttpServletRequest request,HttpServletResponse response, ModelMap model) {
		JSONObject json=new JSONObject();
		Website web = SiteUtils.getWeb(request);
		ShopMember member = MemberThread.get();
		User user = SiteUtils.getUser(request);
		if(member == null || member.getUsername()==null||user==null){
			try {
				json.put("returnUrl", "/order/fworder.jspx?sqServiceId="+sqServiceId);
				json.put("info", "未登录请登录");
				json.put("status", false);
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			ResponseUtils.renderJson(response, json.toString());
			return;
		}
		WebErrors errors = WebErrors.create(request);
		SqOrder sqOrder = new SqOrder();
		
		if(sqServiceId!=null && sqServiceMng.getById(sqServiceId)!=null ){
			SimpleDateFormat df = new SimpleDateFormat("yyMMdd");
			SqService sqService = sqServiceMng.getById(sqServiceId);
			String code=df.format(new Date())+(new Date().getTime() + member.getId());
			Long date = Long.parseLong(code);
			ShopMemberAddress address = shopMemberAddressMng.findById(deliveryInfo);
			sqOrder.setCode(date);
			sqOrder.setComments(comments);
			sqOrder.setCreateTime(new Date());
			sqOrder.setMember(member);
			sqOrder.setReceiveName(address.getUsername());
			sqOrder.setReceiveAddress(getAddress(address));
			sqOrder.setReceiveTel(address.getTel());
			sqOrder.setSeller(sqService.getKetaUser());
			sqOrder.setSqService(sqService);
			sqOrder.setStatus(1);
			sqOrder.setTotalPrice(sqService.getPrice());
			sqOrder.setWebsite(web);
			sqOrderMng.save(sqOrder);
		}else{
			try {
				json.put("returnUrl", "/order/fworder.jspx?sqServiceId="+sqServiceId);
				json.put("info", "该商品不存在，请重新选择");
				json.put("status", false);
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			ResponseUtils.renderJson(response, json.toString());
			return;
		}

		ShopFrontHelper.setCommonData(request, model, web, 1);
		try {
			json.put("returnUrl", "/order/myfworder.jspx");
			json.put("info", "加入订单成功");
			json.put("status", true);
			ResponseUtils.renderJson(response, json.toString());
			return;
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
//		return web.getTplSys(MEMBER_SYS, MessageResolver.getMessage(request,SUCCESSFULLY_ORDER));
	}
	
	public String getAddress(ShopMemberAddress address) {
		String str = "";
		if (address.getProvince() != null) {
			str = str + address.getProvince().getName() + " ";
		}
		if (address.getCity() != null) {
			str = str + address.getCity().getName() + " ";
		}
		if (address.getCountry() != null) {
			str = str + address.getCountry().getName() + " ";
		}
		if(address.getStreet()!=null){
			str = str + address.getStreet().getName() + " ";
		}
		str = str + address.getDetailaddress();
		return str;
	}
	/**
	 * 删除订单
	 */
	@RequestMapping(value = "/order/deleteOrder.jspx")
	public void deleteOrder(Long orderId,HttpServletRequest request,
			HttpServletResponse response) throws JSONException {
		JSONObject json=new JSONObject();
		ShopMember member = MemberThread.get();
		WebErrors error = this.validateOrderView(orderId, member, request);
		if(orderId!=null){
			Order order=orderMng.findById(orderId);
			if(order.getStatus()<3 || !order.getChild().isEmpty()){
				error.addError("订单不可删除");
			}
			if(error.hasErrors()){
				json.put("success", false);
				ResponseUtils.renderJson(response, json.toString());
			}
			order.getItems().clear();
			orderMng.deleteById(orderId);
		}
		json.put("success", true);
		ResponseUtils.renderJson(response, json.toString());
	}
	
	/**
	 * 取消订单
	 */
	@RequestMapping(value = "/order/abolishOrder.jspx")
	public void abolishOrder(Long orderId,HttpServletRequest request,
			HttpServletResponse response) throws JSONException {
		JSONObject json=new JSONObject();
		ShopMember member = MemberThread.get();
		WebErrors error = this.validateOrderView(orderId, member, request);
		if(orderId!=null){
			Order order=orderMng.findById(orderId);
			if(order.getStatus()>2 || order.getPaymentStatus()==2){
				error.addError("订单不可取消");
			}
			if(error.hasErrors()){
				json.put("success", false);
				ResponseUtils.renderJson(response, json.toString());
			}
			order.setStatus(3);
			Timestamp d = new Timestamp(System.currentTimeMillis()); 
			order.setLastModified(d);
			order.setFinishedTime(new Date());
//			Set<OrderItem> set = order.getItems();
			//处理库存
//			for(OrderItem item:set){	
//				Product product=item.getProduct();
//				if(item.getProductFash()!=null){
//					ProductFashion fashion=item.getProductFash();
//					fashion.setStockCount(fashion.getStockCount()+item.getCount());
//					product.setStockCount(product.getStockCount()+item.getCount());
//					productFashionMng.update(fashion);
//				}else{
//					product.setStockCount(product.getStockCount()+item.getCount());
//				}
//				productMng.updateByUpdater(product);
//			}
//			if (order.getBld() != null) {
//				for(OrderItem item:set){	
//					//恢复便利店库存
//					JxcBldGoodsNum jxcBldGoodsNum = jxcBldGoodsNumMng
//							.findByProductIdAndKetaUserId(item.getProductSite()
//									.getProduct().getId(), order.getBld().getKetaUser()
//									.getId());
//					jxcBldGoodsNum.setGoodsNum(jxcBldGoodsNum.getGoodsNum()
//							+ item.getCount());
//					jxcBldGoodsNumMng.update(jxcBldGoodsNum);
//				}
//			}
			//会员冻结的积分
			member.setFreezeScore(member.getFreezeScore()-order.getScore());
			shopMemberMng.update(member);
			List<ShopScore> list = shopScoreMng.getlist(Long.toString(order.getCode()));
			for(ShopScore s:list){
				shopScoreMng.deleteById(s.getId());
			}
			orderMng.updateByUpdater(order);
		}	
		json.put("success", true);
		ResponseUtils.renderJson(response, json.toString());
	}
	
	
	/**
	 * 确认收货
	 */
	@RequestMapping(value = "/order/accomplishOrder.jspx")
	public void accomplishOrder(Long orderId,HttpServletRequest request,
			HttpServletResponse response) throws JSONException {
		JSONObject json=new JSONObject();
		ShopMember member = MemberThread.get();
		WebErrors error = this.validateOrderView(orderId, member, request);
		if(orderId!=null){
			Order order=orderMng.findById(orderId);
			if(order.getStatus()>2 ){
				error.addError("订单不可确认收货");
			}
			if(error.hasErrors()){
				json.put("success", false);
				ResponseUtils.renderJson(response, json.toString());
			}
			order.setStatus(4);
			order.setShippingStatus(2);
			if(order.getPayment().getType()==2){
				order.setPaymentStatus(2);
			}
			order.setFinishedTime(new Date());
			//会员冻结的积分
			member.setFreezeScore(member.getFreezeScore()-order.getScore());
			member.setScore(member.getScore()+order.getScore());
			shopMemberMng.update(member);
			List<ShopScore> list = shopScoreMng.getlist(Long.toString(order.getCode()));
			for(ShopScore s:list){
				s.setStatus(true);
				shopScoreMng.update(s);
			}
			orderMng.updateliRun(orderId);
			orderMng.updateByUpdater(order);//后续金额处理在触发器处理
		}
		json.put("success", true);
		ResponseUtils.renderJson(response, json.toString());
	}
	
	/**
	 * 再次支付订单
	 */
	@RequestMapping(value = "/order/order_payAgain.jspx")
	public String payOrderAgain(Long orderId,HttpServletRequest request,HttpServletResponse response, ModelMap model) {
		Website web = SiteUtils.getWeb(request);
		ShopMember member = MemberThread.get();
		User user = SiteUtils.getUser(request);
		if(member == null || member.getUsername()==null||user==null){
			return "redirect:../login.jspx";
		}
		WebErrors errors = validateOrderView(orderId, member, request);
		if(orderId!=null&&orderMng.findById(orderId).getPaymentStatus()==2){
			errors.addError("订单已经支付");
		}
		if (errors.hasErrors()) {
			return FrontHelper.showError(errors, web, model, request);
		}
		shoppingSvc.clearCookie(request, response);
		Order order=orderMng.findById(orderId);
		List<PaymentPlugins> list = paymentPluginsMng.getList();
		List<ZfbWy> wylist =zfbWyMng.getList();
		model.addAttribute("wylist", wylist);
		model.addAttribute("list", list);
		model.addAttribute("order", order);
		ShopFrontHelper.setCommonData(request, model, web, 1);
		return web.getTplSys(MEMBER_SYS, MessageResolver.getMessage(request,SUCCESSFULLY_ORDER));
	}
	
	/**
	 * 退换货订单列表
	 */
	@RequestMapping(value = "/order/myReturnOrder*.jspx")
	public String myReturnOrder(HttpServletRequest request, ModelMap model) {
		Website web = SiteUtils.getWeb(request);
		ShopMember member = MemberThread.get();
		User user = SiteUtils.getUser(request);
		if(member == null || member.getUsername()==null||user==null){
			return "redirect:../login.jspx";
		}
	   
		Integer pageNo = URLHelper.getPageNo(request);
		Pagination pagination = orderMng.getPageForOrderReturn(member.getId(), pageNo, 10);
		model.addAttribute("pagination", pagination);
		model.addAttribute("historyProductIds", getHistoryProductIds(request));
		ShopFrontHelper.setCommonData(request, model, web, 1);
		ShopFrontHelper.setDynamicPageData(request, model, web, "", "myReturnOrder",".jspx", pageNo);
		return web.getTplSys(MEMBER_SYS, MessageResolver.getMessage(request,MY_RETURN_ORDER));
	}
	
	
	/**
	 * 发货
	 */
	@RequestMapping(value = "/order/shipments.jspx")
	public String shipments(Long id,HttpServletRequest request, ModelMap model) {
		Website web = SiteUtils.getWeb(request);
		ShopMember member = MemberThread.get();
		User user = SiteUtils.getUser(request);
		if(member == null || member.getUsername()==null||user==null){
			return "redirect:../login.jspx";
		}
		WebErrors errors = validateOrderReturnView(id,member,request);
		if(id!=null&&orderReturnMng.findById(id).getStatus()==4){
			errors.addError("订单已经发货");
		}
		if (errors.hasErrors()) {
			return FrontHelper.showError(errors, web, model, request);
		}
		OrderReturn entity = orderReturnMng.findById(id);
		entity.setStatus(4);
		orderReturnMng.update(entity);
		return myReturnOrder(request,model);
	}
	
	/**
	 *退货确认
	 */
	@RequestMapping(value = "/order/accomplish.jspx")
	public String accomplish(Long id, HttpServletRequest request, ModelMap model) {
		Website web = SiteUtils.getWeb(request);
		ShopMember member = MemberThread.get();
		User user = SiteUtils.getUser(request);
		if(member == null || member.getUsername()==null||user==null){
			return "redirect:../login.jspx";
		}
		WebErrors errors = validateOrderReturnView(id,member,request);
		if(id!=null&&orderReturnMng.findById(id).getStatus()==7){
			errors.addError("订单已经确认");
		}
		if (errors.hasErrors()) {
			return FrontHelper.showError(errors, web, model, request);
		}
		OrderReturn entity = orderReturnMng.findById(id);
		entity.setStatus(7);
		Set<OrderItem> set = entity.getOrder().getItems();
		//处理库存
//		for(OrderItem item:set){	
//			Product product=item.getProduct();
//			if(item.getProductFash()!=null){
//				ProductFashion fashion=item.getProductFash();
//				fashion.setStockCount(fashion.getStockCount()+item.getCount());
//				product.setStockCount(product.getStockCount()+item.getCount());
//				productFashionMng.update(fashion);
//			}else{
//				product.setStockCount(product.getStockCount()+item.getCount());
//			}
//			productMng.updateByUpdater(product);
//		}
		//会员冻结的积分
		member.setFreezeScore(member.getFreezeScore()-entity.getOrder().getScore());
		shopMemberMng.update(member);
		List<ShopScore> list = shopScoreMng.getlist(Long.toString(entity.getOrder().getCode()));
		for(ShopScore s:list){
			shopScoreMng.deleteById(s.getId());
		}
		orderReturnMng.update(entity);
		return myReturnOrder(request,model);
	}

	private WebErrors validateOrderView(Long orderId, ShopMember member,
			HttpServletRequest request) {
		WebErrors errors = WebErrors.create(request);
		if (errors.ifNull(orderId, "orderId")) {
			return errors;
		}
		Order order = orderMng.findById(orderId);
		if (errors.ifNotExist(order, Order.class, orderId)) {
			return errors;
		}
		if (!order.getMember().getId().equals(member.getId())) {
			errors.noPermission(Order.class, orderId);
			return errors;
		}
		return errors;
	}
	
	
	public String getHistoryProductIds(HttpServletRequest request){
		String str = null ;
		Cookie[] cookie = request.getCookies();
		int num = cookie.length;
		for (int i = 0; i < num; i++) {
			if (cookie[i].getName().equals("shop_record")) {
				str = cookie[i].getValue();
				break;
			}
		}
		return str;
	}
	
	private WebErrors validateOrderReturnView(Long orderReturnId, ShopMember member,
			HttpServletRequest request) {
		WebErrors errors = WebErrors.create(request);
		if (errors.ifNull(orderReturnId, "orderReturnId")) {
			return errors;
		}
		OrderReturn orderReturn = orderReturnMng.findById(orderReturnId);
		if (errors.ifNotExist(orderReturn,OrderReturn.class, orderReturnId)) {
			return errors;
		}
		if (!orderReturn.getOrder().getMember().getId().equals(member.getId())) {
			errors.noPermission(OrderReturn.class, orderReturnId);
			return errors;
		}
		return errors;
	}

	@Autowired
	private OrderMng orderMng;
	@Autowired
	private SqOrderMng sqOrderMng;
	@Autowired
	private SqServiceMng sqServiceMng;
	@Autowired
	private ShippingMng shippingMng;
	@Autowired
	private PaymentMng paymentMng;
	@Autowired
	private ShoppingSvc shoppingSvc;
	@Autowired
	private PaymentPluginsMng paymentPluginsMng;
	@Autowired
	private ProductMng productMng;
	@Autowired
	private ProductFashionMng productFashionMng;
	@Autowired
	private ShopMemberMng shopMemberMng;
	@Autowired
	private ShopScoreMng shopScoreMng;
	@Autowired
	private JxcBldGoodsNumMng jxcBldGoodsNumMng;
	@Autowired
	private AddressMng addressMng;
	@Autowired
	private ShopMemberAddressMng shopMemberAddressMng;
	@Autowired
	private OrderReturnMng orderReturnMng;
	@Autowired
	private ZfbWyMng zfbWyMng;
}
