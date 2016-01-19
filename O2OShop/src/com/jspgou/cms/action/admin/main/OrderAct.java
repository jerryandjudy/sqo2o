package com.jspgou.cms.action.admin.main;

import static com.jspgou.common.page.SimplePage.cpn;

import java.io.InputStream;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.lang.StringUtils;
import org.json.JSONException;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import com.jspgou.cms.action.member.SendMsg_webchinese;
import com.jspgou.cms.entity.Bld;
import com.jspgou.cms.entity.Gathering;
import com.jspgou.cms.entity.Gys;
import com.jspgou.cms.entity.Jysp;
import com.jspgou.cms.entity.Lsfc;
import com.jspgou.cms.entity.Order;
import com.jspgou.cms.entity.OrderItem;
import com.jspgou.cms.entity.OrderReturn;
import com.jspgou.cms.entity.Payment;
import com.jspgou.cms.entity.Product;
import com.jspgou.cms.entity.ProductFashion;
import com.jspgou.cms.entity.Shipping;
import com.jspgou.cms.entity.ShopAdmin;
import com.jspgou.cms.entity.ShopMember;
import com.jspgou.cms.entity.ShopScore;
import com.jspgou.cms.manager.BldMng;
import com.jspgou.cms.manager.GatheringMng;
import com.jspgou.cms.manager.GysMng;
import com.jspgou.cms.manager.JxcBldGoodsNumMng;
import com.jspgou.cms.manager.JyspMng;
import com.jspgou.cms.manager.LsfcMng;
import com.jspgou.cms.manager.OrderItemMng;
import com.jspgou.cms.manager.OrderMng;
import com.jspgou.cms.manager.OrderReturnMng;
import com.jspgou.cms.manager.PaymentMng;
import com.jspgou.cms.manager.ProductFashionMng;
import com.jspgou.cms.manager.ProductMng;
import com.jspgou.cms.manager.ShippingMng;
import com.jspgou.cms.manager.ShopMemberMng;
import com.jspgou.cms.manager.ShopScoreMng;
import com.jspgou.cms.manager.SqOrderMng;
import com.jspgou.cms.web.AdminContextInterceptor;
import com.jspgou.cms.web.threadvariable.AdminThread;
import com.jspgou.cms.web.threadvariable.MemberThread;
import com.jspgou.common.hibernate3.Updater;
import com.jspgou.common.page.Pagination;
import com.jspgou.common.web.CookieUtils;
import com.jspgou.common.web.RequestUtils;
import com.jspgou.common.web.ResponseUtils;
import com.jspgou.core.entity.Account;
import com.jspgou.core.entity.AccountItem;
import com.jspgou.core.entity.User;
import com.jspgou.core.entity.Website;
import com.jspgou.core.manager.AccountItemMng;
import com.jspgou.core.manager.AccountMng;
import com.jspgou.core.manager.WebsiteMng;
import com.jspgou.core.web.SiteUtils;
import com.jspgou.core.web.WebErrors;

/**
* This class should preserve.
* @preserve
*/
@Controller
public class OrderAct {
	private static final Logger log = LoggerFactory.getLogger(OrderAct.class);
	public static final String ALL = "all";
	public static final String PENDING = "pending";
	public static final String PROSESSING = "processing";
	public static final String DELIVERED = "delivered";
	public static final String COMPLETE = "complete";
	public static final String[] TYPES = { ALL, PENDING, PROSESSING, DELIVERED,
			COMPLETE };
	 //供浏览器读取的信息  
	    private String fileName;  
	   private InputStream inputStream;  
	      
	   private String filedataFileName;  
  


	@RequestMapping("/order/v_list.do")
	public String list(Long code,Integer ordeRType,Integer status,Integer paymentStatus,Integer shippingStatus,Long paymentId, Long shoppingId,Date startTime,Date endTime,
			Integer pageNo, HttpServletRequest request,
			ModelMap model) {
		Website web = SiteUtils.getWeb(request);
		List siteList=websiteMng.getAllList();
		ShopAdmin admin = AdminThread.get();
		boolean isSuper=false;
		if(admin.getAdmin().getIsSuper()&&admin.getAdmin().isSuper()){
			isSuper=true;
		}
		model.addAttribute("isSuper", isSuper);
		model.addAttribute("siteList", siteList);
		model.addAttribute("siteParam", AdminContextInterceptor.SITE_PARAM);
		model.addAttribute("site", web);
		String userName = RequestUtils.getQueryParam(request, "userName");
		userName = StringUtils.trim(userName);
		Pagination pagination = orderMng.getPage(ordeRType,web.getId(), null,null,userName,
				paymentId,shoppingId, startTime,endTime,null,null,status,paymentStatus,shippingStatus,code,
				cpn(pageNo), CookieUtils.getPageSize(request));
		model.addAttribute("pagination", pagination);
		
		List<Shipping> shippingList = shippingMng.getList(null, true);
		List<Payment> paymentList = paymentMng.getList(null, true);
		model.addAttribute("paymentList", paymentList);
		model.addAttribute("shippingList", shippingList);
		model.addAttribute("paymentId", paymentId);
		model.addAttribute("shoppingId", shoppingId);
		model.addAttribute("userName", userName);
		model.addAttribute("startTime", startTime);
		model.addAttribute("endTime", endTime);
		model.addAttribute("status", status);
		model.addAttribute("ordeRType", ordeRType);
		model.addAttribute("code", code);
		model.addAttribute("paymentStatus", paymentStatus);
		model.addAttribute("shippingStatus", shippingStatus);
		return "order/list";
	}
	@RequestMapping("/order/v_sqlist.do")
	public String sqlist(String receiveName,String sellerName,Long code,Integer status,Date createTime,Date finishedTime,
			Integer pageNo, HttpServletRequest request,
			ModelMap model) {
		Website web = SiteUtils.getWeb(request);
		List siteList=websiteMng.getAllList();
		ShopAdmin admin = AdminThread.get();
		boolean isSuper=false;
		if(admin.getAdmin().getIsSuper()&&admin.getAdmin().isSuper()){
			isSuper=true;
		}
		model.addAttribute("isSuper", isSuper);
		model.addAttribute("siteList", siteList);
		model.addAttribute("siteParam", AdminContextInterceptor.SITE_PARAM);
		model.addAttribute("site", web);
		String userName = RequestUtils.getQueryParam(request, "userName");
		userName = StringUtils.trim(userName);
		Pagination pagination = sqOrderMng.getPage(web.getId(),null, receiveName,sellerName, createTime,finishedTime,status,code,
				cpn(pageNo), CookieUtils.getPageSize(request));
		model.addAttribute("pagination", pagination);
		model.addAttribute("code", code);
		model.addAttribute("createTime", createTime);
		model.addAttribute("finishedTime", finishedTime);
		model.addAttribute("receiveName", receiveName);
		model.addAttribute("status", status);
		return "order/sqlist";
	}
	 /** 
	     * 导出Excel 
	     * @return 
	     *  
	    */   
	    @RequestMapping("/order/v_exportExcel.do")
	    public void exportExcel(Long code,Integer ordeRType,Integer status,Integer paymentStatus,Integer shippingStatus,Long paymentId, Long shoppingId,Date startTime,Date endTime,
				 HttpServletRequest request,HttpServletResponse response,
				ModelMap model){  
	        fileName = "订单信息"+new Date().getTime()+".xls";  
	        Website web = SiteUtils.getWeb(request);
	       try {  
	           fileName = new String(fileName.getBytes(), "ISO8859-1");  
	           String userName = RequestUtils.getQueryParam(request, "userName");
	            inputStream = orderMng.exportExcel(ordeRType,web.getId(), null,null,userName,
	    				paymentId,shoppingId, startTime,endTime,null,null,status,paymentStatus,shippingStatus,code);  
	            response.reset(); // 必要地清除response中的缓存信息
	            response.setHeader("Content-Disposition", "attachment; filename=" + fileName);
	            response.setContentType("application/vnd.ms-excel");//根据个人需要,这个是下载文件的类型
	            javax.servlet.ServletOutputStream out = response.getOutputStream();

	            byte[] content = new byte[1024];
	            int length = 0;
	            while ((length = inputStream.read(content)) != -1) {
	            out.write(content, 0, length);
	            }

	            out.write(content);
	            out.flush();
	            out.close();
	       
	       } catch (Exception e) {  
	            e.printStackTrace();  
	        }  
//	        return "export";  
	    }  

	
	/**
	 * 查看订单
	 * @param id
	 * @param request
	 * @param model
	 * @return
	 */
	@RequestMapping("/order/v_view.do")
	public String view(Long id, HttpServletRequest request, ModelMap model) {
		Website web = SiteUtils.getWeb(request);
		WebErrors errors = validateEdit(id, request);
		if (errors.hasErrors()) {
			return errors.showErrorPage(model);
		}
		model.addAttribute("order", orderMng.findById(id));
		return "order/view";
	}
	/**
	 * 分配订单
	 * @param id
	 * @param pageNo
	 * @param request
	 * @param model
	 * @return
	 */
	@RequestMapping("/order/v_fpdd.do")
	public String fpdd(Long id,Integer pageNo, HttpServletRequest request, ModelMap model) {
		Website web = SiteUtils.getWeb(request);
		WebErrors errors = validateEdit(id, request);
		if (errors.hasErrors()) {
			return errors.showErrorPage(model);
		}
		Order order=orderMng.findById(id);
		model.addAttribute("order", order);
		if(order.getOrdeRType()==1){ //商城订单
		List<Bld> list = bldMng.getBldByOrder(order);
		model.addAttribute("list", list);
		}else if(order.getOrdeRType()==2){//进销存订单
			List<Gys> list =gysMng.getGysByOrder( order);
			model.addAttribute("list", list);
			return "order/fpgyslist";
		}
		return "order/fpbldlist";
	}
	/**
	 * 拆分订单
	 * @param id
	 * @param pageNo
	 * @param request
	 * @param model
	 * @return
	 */
	@RequestMapping("/order/v_cfdd.do")
	public String cfdd(Long id,Integer pageNo, HttpServletRequest request, ModelMap model) {
		Website web = SiteUtils.getWeb(request);
		WebErrors errors = validateEdit(id, request);
		
		Order order=orderMng.findById(id);
		Bld bld = bldMng.findByIsDef(true, order.getWebsite().getId());
		if(bld==null){
			errors.addError("请先设置该站点下默认平台便利店！！");
		}
		if (errors.hasErrors()) {
			return errors.showErrorPage(model);
		}
		if(order.getStatus()==0){
		List<OrderItem> list=orderMng.cfdd(order);
		model.addAttribute("list", list);
		}
//		model.addAttribute("status", 0);
//		model.addAttribute("backUrl", "v_list.do");
		return this.list(null, null, 0, null, null, null, null, null, null, pageNo, request, model);
	}
	/**
	 * 根据订单和配送区域以，商品库存量足够，如果是货到付款可用余额足够， 获得bld list
	 * 
	 * @param siteId
	 * @param province
	 * @param city
	 * @param country
	 * @param street
	 * @return
	 */
	public List<Bld> getBldByOrder(Order order) {
		Bld bld = null;
		List<Bld> list = bldMng.getBldByOrder(order);
		
		return list;
	}
	
	@RequestMapping("/order/o_affirm.do")
	public String affirm(Long id, HttpServletRequest request, ModelMap model) {
		Website web = SiteUtils.getWeb(request);
		WebErrors errors = validateEdit(id, request);
		if (errors.hasErrors()) {
			return errors.showErrorPage(model);
		}
		Order order =  orderMng.findById(id);
		if(order.getStatus()==1){
			order.setStatus(2);
			orderMng.updateByUpdater(order);
		}
		model.addAttribute("order", order);
		return "order/view";
	}
	
	@RequestMapping("/order/o_abolish.do")
	public String abolish(Long id, HttpServletRequest request, ModelMap model) {
		Website web = SiteUtils.getWeb(request);
		WebErrors errors = validateEdit(id, request);
		if (errors.hasErrors()) {
			return errors.showErrorPage(model);
		}
		Order order =  orderMng.findById(id);
		if(order.getStatus()==3||order.getStatus()==4){
			errors.addError("该订单不可取消！！");
			return errors.showErrorPage(model);
		}
	 if(order.getPaymentStatus()==2 && order.getPayment().getId()==1){
		 OrderReturn orderReturn=null;
		    if(order.getReturnOrder()==null){
			 orderReturn =new OrderReturn();
			orderReturn.setMoney(order.getTotal());
			orderReturn.setReason("系统管理员("+SiteUtils.getUser(request).getUsername()+")手动取消订单");
			orderReturn=orderReturnMng.save(orderReturn, order, 42l, true, null, null);
			order.setReturnOrder(orderReturn);
			orderMng.updateByUpdater(order);
		    }else{
		    	orderReturn=order.getReturnOrder();
		    }
			return "redirect:../orderReturn/o_reimburse.do?id="+orderReturn.getId();
		}	else{
			order.setStatus(3);
			order.setFinishedTime(new Date());
			//处理库存
//			for(OrderItem item:order.getItems()){	
//				Product product=item.getProduct();
//				if(item.getProductFash()!=null){
//					ProductFashion fashion=item.getProductFash();
//					fashion.setStockCount(fashion.getStockCount()+item.getCount());
//					product.setStockCount(product.getStockCount()+item.getCount());
//					productFashionMng.update(fashion);
//				}else{
////					product.setStockCount(product.getStockCount()+item.getCount());
//				}
//				productMng.updateByUpdater(product);
//			}
			//会员冻结的积分
			if(order.getOrdeRType()==1){
			ShopMember member = order.getMember();
			member.setFreezeScore(member.getFreezeScore()-order.getScore());
			shopMemberMng.update(member);
			List<ShopScore> list = shopScoreMng.getlist(Long.toString(order.getCode()));
			for(ShopScore s:list){
				shopScoreMng.deleteById(s.getId());
			}
			}
			order.setLastModified(new Date());
			orderMng.updateByUpdater(order);
		}
		model.addAttribute("order", order);
		return "order/view";
	}

	
	@RequestMapping("/order/v_payment.do")
	public String zhifu(Long id, HttpServletRequest request, ModelMap model) {
		Website web = SiteUtils.getWeb(request);
		WebErrors errors = validateEdit(id, request);
		if (errors.hasErrors()) {
			return errors.showErrorPage(model);
		}
		Order order =  orderMng.findById(id);
		model.addAttribute("order", order);
		return "order/payment";
	}
	
	@RequestMapping("/order/v_shipments.do")
	public String shipmentses(Long id, HttpServletRequest request, ModelMap model) {
		Website web = SiteUtils.getWeb(request);
		WebErrors errors = validateEdit(id, request);
		if (errors.hasErrors()) {
			return errors.showErrorPage(model);
		}
		Order order =  orderMng.findById(id);
		model.addAttribute("order", order);
		return "order/shipments";
	}
	
	
	
	@RequestMapping("/order/o_payment.do")
	public String payment(Gathering bean,Long id,HttpServletRequest request, ModelMap model) {
		Website web = SiteUtils.getWeb(request);
		WebErrors errors = validateEdit(id, request);
		if (errors.hasErrors()) {
			return errors.showErrorPage(model);
		}
		Order order =  orderMng.findById(id);
		ShopAdmin admin = AdminThread.get();
		bean.setShopAdmin(admin);
		bean.setIndent(order);
		if(order.getPaymentStatus()==1&&order.getPayment().getType()==2){
			gatheringMng.save(bean);
			order.setPaymentStatus(2);
			orderMng.updateByUpdater(order);
		}
		model.addAttribute("order", order);
		return "order/view";
	}
	
	
	
	
	@RequestMapping("/order/o_accomplish.do")
	public String accomplish(Long id, HttpServletRequest request, ModelMap model){
		WebErrors errors = validateEdit(id, request);
		if (errors.hasErrors()) {
			return errors.showErrorPage(model);
		}
		Order order =  orderMng.findById(id);
		if(order.getShippingStatus()==2&&order.getStatus()==2&&order.getPaymentStatus()==2){
			order.setStatus(4);
			//会员冻结的积分
			ShopMember member = order.getMember();
			member.setFreezeScore(member.getFreezeScore()-order.getScore());
			member.setScore(member.getScore()+order.getScore());
			shopMemberMng.update(member);
			List<ShopScore> list = shopScoreMng.getlist(Long.toString(order.getCode()));
			for(ShopScore s:list){
				s.setStatus(true);
				shopScoreMng.update(s);
			}
		    orderMng.updateByUpdater(order);
		    orderMng.updateliRun(id);
		}
		model.addAttribute("order", order);
		return "order/view";
	}

	@RequestMapping("/order/v_edit.do")
	public String edit(Long id, HttpServletRequest request, ModelMap model) {
		Website web = SiteUtils.getWeb(request);
		WebErrors errors = validateEdit(id, request);
		if (errors.hasErrors()) {
			return errors.showErrorPage(model);
		}
		List<Shipping> shippingList = shippingMng.getList(web.getId(), false);
		List<Payment> paymentList = paymentMng.getList(web.getId(), false);
//		Double lscc=0d;
		Order order=orderMng.findById(id);
//		if(order.getPayment().getType() == 2&&order.getStatus()!=0){
//			AccountItem findByAccountIdAndOrderId=null;
//			if(order.getOrdeRType()==1){
//				Account account = order.getBld().getAccount();	
//				findByAccountIdAndOrderId = accountItemMng.findByAccountIdAndOrderId(account.getId(), order.getId());
//			    
//			}else if(order.getOrdeRType()==2){
//				Account account = order.getGys().getAccount();	
//				findByAccountIdAndOrderId = accountItemMng.findByAccountIdAndOrderId(account.getId(), order.getId());
//			}
//			if(findByAccountIdAndOrderId!=null){
//		    	lscc=findByAccountIdAndOrderId.getMoney();
//		    }
//		}
		model.addAttribute("order", order);
		model.addAttribute("paymentList", paymentList);
//		model.addAttribute("lscc", lscc);
		model.addAttribute("shippingList", shippingList);
		model.addAttribute("orderReturn", orderReturnMng.findByOrderId(id));
		return "order/edit";
	}

	@RequestMapping("/order/o_update.do")
	public String update(Long id, String comments,Long shippingId,Long paymentId,Long[] itemId, Integer[] itemCount,
			Double[] itemPrice,Double totalPrice, Integer pageNo,
			HttpServletRequest request,HttpServletResponse response, ModelMap model) {
		WebErrors errors = validateUpdate(id,shippingId,itemId,itemCount,itemPrice,request);
		if (errors.hasErrors()) {
			return errors.showErrorPage(model);
		}
		Order order = orderMng.findById(id);
		int score = 0;
		int weight=0;
		double price=0.00;
		for(int i=0;i<itemId.length;i++){
			OrderItem orderItem = orderItemMng.findById(itemId[i]);
			Product product = orderItem.getProductSite().getProduct();
//			product.setStockCount(product.getStockCount()+orderItem.getCount()-itemCount[i]);
			if(orderItem.getProductFash()!=null){
				ProductFashion  productFash = orderItem.getProductFash();
				productFash.setStockCount(productFash.getStockCount()+orderItem.getCount()-itemCount[i]);
				productFashionMng.update(productFash);
			}
			orderItem.setCount(itemCount[i]);
			orderItem.setSalePrice(itemPrice[i]);
			score = (int) (score +itemCount[i]*product.getScore());
			weight= weight+product.getWeight()*itemCount[i];
			if(orderItem.getProductFash()!=null){
				price+=itemPrice[i]*itemCount[i];
			}else{
				price+=itemPrice[i]*itemCount[i];
			}
			orderItemMng.updateByUpdater(orderItem);
//			productMng.updateByUpdater(product);
		}
		order.setScore(score);
		order.setWeight((double)weight);
		order.setProductPrice(price);
		double freight=shippingMng.findById(shippingId).calPrice((double)weight);
		order.setFreight(freight);
		order.setTotal(freight+price);
	    order.setComments(comments);
		order.setShipping(shippingMng.findById(shippingId));
		order.setPayment(paymentMng.findById(paymentId));
		order.setLastModified(new Timestamp(System.currentTimeMillis()));
	    orderMng.updateByUpdater(order);
		log.info("update Order, id={}.", order.getId());
		return list(null,null,null,null,null,null,null,null,null, pageNo, request, model);
	}
	@RequestMapping("/order/o_bldfpdd.do")
	public String bldfpdd(String bldId,Long orderId,Integer pageNo,
			HttpServletRequest request,HttpServletResponse response, ModelMap model) {
		WebErrors errors = validatefpdd(bldId,orderId,request);
		if (errors.hasErrors()) {
			model.put("backUrl", "v_fpdd.do?id="+orderId+"");
			return errors.showErrorPage(model);
		}
		
		
		Order order = orderMng.findById(orderId);
		Order oldorder=new Order();
		BeanUtils.copyProperties(order, oldorder);
//		if (oldorder.getBld() != null) {
//			for (OrderItem item : order.getItems()) {
//				//恢复便利店库存
//				JxcBldGoodsNum jxcBldGoodsNum = jxcBldGoodsNumMng
//						.findByProductIdAndKetaUserId(item.getProductSite()
//								.getProduct().getId(), oldorder.getBld().getKetaUser()
//								.getId());
//				jxcBldGoodsNum.setGoodsNum(jxcBldGoodsNum.getGoodsNum()
//						+ item.getCount());
//				jxcBldGoodsNumMng.update(jxcBldGoodsNum);
//			}
//			//恢复便利店冻结流水抽成
//			Account account = null;
//			if ( oldorder.getPayment().getType() == 2) {// 有便利店接单且支付方式为货到付款冻结便利店账户金额
//				account = oldorder.getBld().getAccount();
//					account.setFrozenMoney(account.getFrozenMoney() -oldorder.getLsccMoney());// 获取便利店流水分成比例金额
//					account.setMoney(account.getMoney()+oldorder.getLsccMoney());
//					Updater<Account> updaters = new Updater<Account>(account);
//					accountMng.updateByUpdater(updaters); // 更新冻结金额字段
//					//this.createSelf(order,order.getLsccMoney());//生成收支明细记录
//			}
//		}
		
		
		order.setBld(bldMng.findById(bldId));
		
		if(order.getBld()!=null && order.getBld().getId().length()>0){
			SendMsg_webchinese.sendMsg(order.getBld().getPhone(),"您有新订单，订单号："+order.getCode()+",请尽快处理。否则超时系统将自动收回订单");
		}
		order.setSysComment("系统手动分单");
		Timestamp timestamp = new Timestamp(System.currentTimeMillis());
		order.setLastModified(timestamp);
		order.setStatus(1);
		ShopScore shopScore = null;
		Product product = null;
		ProductFashion fashion = null;
//		if (order.getBld() != null) {
//			for (OrderItem item : order.getItems()) {
				// 处理库存
//				JxcBldGoodsNum jxcBldGoodsNum = jxcBldGoodsNumMng
//						.findByProductIdAndKetaUserId(item.getProductSite()
//								.getProduct().getId(), order.getBld().getKetaUser()
//								.getId());
//				jxcBldGoodsNum.setGoodsNum(jxcBldGoodsNum.getGoodsNum()
//						- item.getCount());
//				jxcBldGoodsNumMng.update(jxcBldGoodsNum);
//				shopScore = new ShopScore();
//				shopScore.setMember(order.getMember());
//				shopScore.setName(item.getProductSite().getProduct().getName());
//				shopScore.setScoreTime(new Date());
//				shopScore.setStatus(false);
//				shopScore.setUseStatus(false);
//				shopScore.setScoreType(ScoreTypes.ORDER_SCORE.getValue());
//				shopScore.setScore(item.getProductSite().getProduct()
//						.getScore());
//				shopScore.setCode(Long.toString(order.getCode()));
//				shopScoreMng.save(shopScore);
//			}
//		}
		orderMng.updateByUpdater(order);
//		this.djMoney(order, oldorder);
		log.info("update Order, id={}.", order.getId());
		return list(null,null,0,null,null,null,null,null,null, pageNo, request, model);
	}
	@RequestMapping("/order/o_gysfpdd.do")
	public String gysfpdd(String gysId,Long orderId,Integer pageNo,
			HttpServletRequest request,HttpServletResponse response, ModelMap model) {
		WebErrors errors = validatefpdd(gysId,orderId,request);
		if (errors.hasErrors()) {
			return errors.showErrorPage(model);
		}
		Order order = orderMng.findById(orderId);
		Order oldorder=new Order();
		BeanUtils.copyProperties(order, oldorder);
		if (oldorder.getGys() != null) {
			for (OrderItem item : order.getItems()) {
				//恢复供应商库存
				Jysp jysp = jyspMng
						.findByProductIdAndKetaUserId(item.getProductSite()
								.getProduct().getId(), oldorder.getGys().getKetaUser()
								.getId());
				jysp.setGoodsNum(jysp.getGoodsNum()
						+ item.getCount());
				jyspMng.update(jysp);
			}
			//货到付款恢复供应商冻结金额
			Account account = null;
			if ( oldorder.getPayment().getType() == 2) {
				account = oldorder.getGys().getAccount();
					account.setFrozenMoney(account.getFrozenMoney() -oldorder.getLsccMoney());// 获取便利店流水分成比例金额
					account.setMoney(account.getMoney()+oldorder.getLsccMoney());
					Updater<Account> updaters = new Updater<Account>(account);
					accountMng.updateByUpdater(updaters); // 更新冻结金额字段
					//this.createSelf(order,order.getLsccMoney());//生成收支明细记录
			}
		}
		
		order.setGys(gysMng.findById(gysId));
		order.setSysComment("系统手动分单");
		Timestamp timestamp = new Timestamp(System.currentTimeMillis());
		order.setLastModified(timestamp);
		order.setStatus(1);
		orderMng.updateByUpdater(order);
		log.info("update Order, id={}.", order.getId());
		return list(null,null,0,null,null,null,null,null,null, pageNo, request, model);
	}
	/**
	 * 更新冻结金额
	 * 
	 * @param order
	 * @return
	 */
	private Account djMoney(Order order,Order oldOrder) {
		Account account = null;
		if (oldOrder.getStatus() == 0 && order.getPayment().getType() == 2) {// 未分配订单支付方式为货到付款冻结便利店账户金额
			if(oldOrder.getBld()!=null){
			   account = order.getBld().getAccount();
				account.setFrozenMoney(account.getFrozenMoney() + order.getLsccMoney());// 获取便利店流水分成比例金额
				account.setMoney(account.getMoney()-order.getLsccMoney());
				Updater<Account> updater = new Updater<Account>(account);
				
				accountMng.updateByUpdater(updater); // 更新冻结金额字段
			}
				this.createSelf(order,oldOrder,order.getLsccMoney());//生成收支明细记录
		}
		return account;
	}
	 /**
     * 创建订单收支明细
     * @param order
     * @return
     */
    public List<AccountItem> createSelf(Order order,Order oldOrder,Double lscc){
    	List  accountItems=new ArrayList();
    	if (oldOrder.getStatus() == 0 && order.getPayment().getType() == 2) {
    		if(oldOrder.getBld()!=null){
    			AccountItem item=accountItemMng.findByAccountIdAndOrderId(oldOrder.getBld().getAccount().getId(), oldOrder.getId());
    			if(item!=null && item.getId()>0){
    			accountItemMng.deleteById(item.getId());
    			}
    		}
    		Account account = order.getBld().getAccount();
			Lsfc lsfc = lsfcMng.findByFctypeId(36l, order.getWebsite().getId());// 流水分成便利店
			if (lsfc != null && lsfc.getId() > 0) {
				AccountItem accountItem=new AccountItem();
				accountItem.setOrder(order);
				accountItem.setMoney(lscc);
				accountItem.setMoneyTime(new Date());
				accountItem.setMoneyType(1);
				accountItem.setName("货到付款购买便利店商品");
				accountItem.setRemark("货到付款购买”"+order.getBld().getCompanyName()+"“商品，便利店预付流水分成");
				accountItem.setStatus(false);
				accountItem.setUseStatus(true);
				accountItem.setAccount(account);
				accountItemMng.save(accountItem); // 保存记录
				accountItems.add(accountItem);
			}
    	}
    	return accountItems;
    }
	@RequestMapping("/order/o_returnMoney.do")
	public void returnMoney(Long id,HttpServletRequest request,
			HttpServletResponse response, ModelMap model) {
		Website web = SiteUtils.getWeb(request);
	    OrderReturn orderReturn=orderReturnMng.findByOrderId(id);
    	if(orderReturn.getPayType()==2){
    		Payment pay=paymentMng.findById((long)3);
	    	PrintWriter out = null;
			try {
				String aliURL = alipayReturn(pay,web,orderReturn, request, model);
				response.setContentType("text/html;charset=UTF-8");
				out = response.getWriter();
				out.print(aliURL);
			} catch (Exception e) {
			}finally{
				out.close();
			}
    	}
	}
	
	private String alipayReturn(Payment pay,Website web,OrderReturn orderReturn,
			HttpServletRequest request,ModelMap model){
		////////////////////////////////////请求参数//////////////////////////////////////
		//必填参数//
		Date date=new Date();
		SimpleDateFormat sdf=new SimpleDateFormat("yyyyMMdd");
		SimpleDateFormat sdf1=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String date1=sdf.format(date);
		//退款批次号。格式为：退款日期（8位当天日期）+流水号（3～24位，不能接受“000”，但是可以接受英文字符）
//		String batch_no = request.getParameter("batch_no");
		String batch_no = date1.concat(String.valueOf(orderReturn.getId()*100));
		//退款请求时间
//		String refund_date = request.getParameter("refund_date");
		String refund_date = sdf1.format(date);
		//退款总笔数
//		String batch_num = request.getParameter("batch_num");
		String batch_num =String.valueOf(1);
		//单笔数据集
//		String detail_data =  new String(request.getParameter("detail_data").getBytes("ISO-8859-1"),"gbk");
		String detail_data = orderReturn.getOrder().getId().toString()+"^"+1.00+"^"+orderReturn.getShopDictionary().getName();
		//格式：第一笔交易#第二笔交易#第三笔交易
	        //第N笔交易格式：交易退款信息
	        //交易退款信息格式：原付款支付宝交易号^退款总金额^退款理由
	        //注意：
	        //1.detail_data中的退款笔数总和要等于参数batch_num的值
	        //2.detail_data的值中不能有“^”、“|”、“#”、“$”等影响detail_data的格式的特殊字符
	        //3.detail_data中退款总金额不能大于交易总金额
	        //4.一笔交易可以多次退款，只需要遵守多次退款的总金额不超过该笔交易付款时金额。
	        //5.不支持退分润功能
		//选填参数（以下两个参数不能同时为空）
		//卖家支付宝账号
//		String seller_email = request.getParameter("seller_email");
		//String seller_email = pay.getSellerEmail();
		//卖家用户ID
//		String seller_user_id = request.getParameter("seller_user_id");
	//	String seller_user_id = pay.getSellerKey();
		//服务器页面跳转同步通知页面
		String notify_url="http://"+web.getDomain()+"/cart/aliReturn.jspx";
		//////////////////////////////////////////////////////////////////////////////////
		//把请求参数打包成数组
		Map<String, String> sParaTemp = new HashMap<String, String>();
	//	sParaTemp.put("partner", pay.getPartner());
		//sParaTemp.put("seller_email", seller_email);
		//sParaTemp.put("seller_user_id", seller_user_id);
        sParaTemp.put("batch_no", batch_no);
        sParaTemp.put("refund_date", refund_date);
        sParaTemp.put("batch_num", batch_num);
        sParaTemp.put("detail_data", detail_data);
        sParaTemp.put("notify_url", notify_url);
		//构造函数，生成请求URL  
		String sHtmlText=null;
		try {
			sHtmlText = refund_fastpay_by_platform_pwd(sParaTemp);
		} catch (Exception e) {
			e.printStackTrace();
		}
//		out.println(sHtmlText);
		return sHtmlText;
	}
	
	
	/**
     * 支付宝提供给商户的服务接入网关URL(新)
     */
    private static final String ALIPAY_GATEWAY_NEW = "https://mapi.alipay.com/gateway.do?";
	
	/**
     * 构造即时到账批量退款有密接口
     * @param sParaTemp 请求参数集合
     * @return 支付宝返回表单提交HTML信息
     * @throws Exception 
     */
    public static String refund_fastpay_by_platform_pwd(Map<String, String> sParaTemp) throws Exception {
    	//增加基本配置
        sParaTemp.put("service", "refund_fastpay_by_platform_pwd");
        sParaTemp.put("_input_charset", "UTF-8");
        String strButtonName="退款";
        return buildForm(sParaTemp, ALIPAY_GATEWAY_NEW,"get",strButtonName);
    }
    
    /**
     * 构造提交表单HTML数据
     * @param sParaTemp 请求参数数组
     * @param gateway 网关地址
     * @param strMethod 提交方式。两个值可选：post、get
     * @param strButtonName 确认按钮显示文字
     * @return 提交表单HTML文本
     */
    public static String buildForm(Map<String, String> sParaTemp, String gateway, String strMethod,
                                   String strButtonName) {
        //待请求参数数组
        Map<String, String> sPara = buildRequestPara(sParaTemp);
        List<String> keys = new ArrayList<String>(sPara.keySet());

        StringBuffer sbHtml = new StringBuffer();

        sbHtml.append("<form id=\"alipaysubmit\" name=\"alipaysubmit\" action=\"" + gateway
                      + "_input_charset=" + "UTF-8" + "\" method=\"" + strMethod
                      + "\">");

        for (int i = 0; i < keys.size(); i++) {
            String name = (String) keys.get(i);
            String value = (String) sPara.get(name);

            sbHtml.append("<input type=\"hidden\" name=\"" + name + "\" value=\"" + value + "\"/>");
        }

        //submit按钮控件请不要含有name属性
        sbHtml.append("<input type=\"submit\" value=\"" + strButtonName + "\" style=\"display:none;\"></form>");
        sbHtml.append("<script>document.forms['alipaysubmit'].submit();</script>");

        return sbHtml.toString();
    }
    
    
    /**
    * 生成要请求给支付宝的参数数组
    * @param sParaTemp 请求前的参数数组
    * @return 要请求的参数数组
    */
   private static Map<String, String> buildRequestPara(Map<String, String> sParaTemp) {
       //除去数组中的空值和签名参数
       Map<String, String> sPara = paraFilter(sParaTemp);
       //生成签名结果
       String mysign = buildMysign(sPara);

       //签名结果与签名方式加入请求提交参数组中
       sPara.put("sign", mysign);
       sPara.put("sign_type", "MD5");

       return sPara;
   }
   
   public static String buildMysign(Map<String, String> sArray) {
       String prestr = createLinkString(sArray); //把数组所有元素，按照“参数=参数值”的模式用“&”字符拼接成字符串
       prestr = prestr +sArray.get("key"); //把拼接后的字符串再与安全校验码直接连接起来
       String mysign = md5(prestr);
       return mysign;
   }
   
   public static String md5(String text) {

       return DigestUtils.md5Hex(getContentBytes(text, "UTF-8"));

   }
   
   
   private static byte[] getContentBytes(String content, String charset) {
       if (charset == null || "".equals(charset)) {
           return content.getBytes();
       }

       try {
           return content.getBytes(charset);
       } catch (UnsupportedEncodingException e) {
           throw new RuntimeException("MD5签名过程中出现错误,指定的编码集不对,您目前指定的编码集是:" + charset);
       }
   }
   /** 
    * 把数组所有元素排序，并按照“参数=参数值”的模式用“&”字符拼接成字符串
    * @param params 需要排序并参与字符拼接的参数组
    * @return 拼接后字符串
    */
   public static String createLinkString(Map<String, String> params) {

       List<String> keys = new ArrayList<String>(params.keySet());
       Collections.sort(keys);

       String prestr = "";

       for (int i = 0; i < keys.size(); i++) {
           String key = keys.get(i);
           String value = params.get(key);

           if (i == keys.size() - 1) {//拼接时，不包括最后一个&字符
               prestr = prestr + key + "=" + value;
           } else {
               prestr = prestr + key + "=" + value + "&";
           }
       }

       return prestr;
   }
   
   
   /** 
    * 除去数组中的空值和签名参数
    * @param sArray 签名参数组
    * @return 去掉空值与签名参数后的新签名参数组
    */
   public static Map<String, String> paraFilter(Map<String, String> sArray) {

       Map<String, String> result = new HashMap<String, String>();

       if (sArray == null || sArray.size() <= 0) {
           return result;
       }

       for (String key : sArray.keySet()) {
           String value = sArray.get(key);
           if (value == null || value.equals("") || key.equalsIgnoreCase("sign")
               || key.equalsIgnoreCase("sign_type")) {
               continue;
           }
           result.put(key, value);
       }

       return result;
   }
	
	//计算运费
	@RequestMapping(value = "/order/ajaxtotalDeliveryFee.do")
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
	
	private Integer findItem(Long[] itemIds, Long itemId) {
		for(int i=0;i<itemIds.length;i++){
			if (itemIds[i].equals(itemId)) {
				return i;
			}
		}
		return null;
	}

	@RequestMapping("/order/o_delete.do")
	public String delete(Long[] ids, String type, Integer pageNo,
			HttpServletRequest request, ModelMap model) {
		WebErrors errors = validateDelete(ids, request);
		if (errors.hasErrors()) {
			return errors.showErrorPage(model);
		}
		Order[] beans = orderMng.deleteByIds(ids);
		for (Order bean : beans) {
			log.info("delete Order, id={}", bean.getId());
		}
		return list(null,null,null,null,null,null,null,null,null, pageNo, request, model);
	}

	private WebErrors validateEdit(Long id, HttpServletRequest request) {
		WebErrors errors = WebErrors.create(request);
		Website web = SiteUtils.getWeb(request);
		if (vldExist(id, web.getId(), errors)) {
			return errors;
		}
		return errors;
	}

	private WebErrors validateUpdate(Long id,
			Long shippingId,Long[] itemId,
			Integer[] itemCount, Double[] itemPrice,
			HttpServletRequest request) {
		WebErrors errors = WebErrors.create(request);
		Website web = SiteUtils.getWeb(request);
		if (vldExist(id, web.getId(), errors)) {
			return errors;
		}
		if (itemId == null || itemCount == null || itemPrice == null
				|| itemId.length == 0 || itemId.length != itemCount.length
				|| itemCount.length != itemPrice.length) {
			errors.addErrorString("order item invalid!");
			return errors;
		}
		return errors;
	}
	private WebErrors validatefpdd(String bldOrGysId,
			Long orderId,
			HttpServletRequest request) {
		WebErrors errors = WebErrors.create(request);
		Website web = SiteUtils.getWeb(request);
		if (vldOrderExist(orderId, web.getId(), errors)) {
			return errors;
		}
		Order entity = orderMng.findById(orderId);
		
		if (entity.getOrdeRType()==1&&vldBldExist(orderId,bldOrGysId, web.getId(), errors)) {
			return errors;
		}else if(entity.getOrdeRType()==2&&vldGysExist(orderId,bldOrGysId, web.getId(), errors)){
			return errors;
		}
		return errors;
	}

	private WebErrors validateDelete(Long[] ids, HttpServletRequest request) {
		WebErrors errors = WebErrors.create(request);
		Website web = SiteUtils.getWeb(request);
		errors.ifEmpty(ids, "ids");
		for (Long id : ids) {
			vldExist(id, web.getId(), errors);
		}
		return errors;
	}

	private boolean vldExist(Long id, Long webId, WebErrors errors) {
		if (errors.ifNull(id, "id")) {
			return true;
		}
		Order entity = orderMng.findById(id);
		if (errors.ifNotExist(entity, Order.class, id)) {
			return true;
		}
		if (!entity.getWebsite().getId().equals(webId)) {
			errors.notInWeb(Order.class, id);
			return true;
		}
		return false;
	}
	private boolean vldOrderExist(Long id, Long webId, WebErrors errors) {
		if (errors.ifNull(id, "id")) {
			return true;
		}
		Order entity = orderMng.findById(id);
		if (errors.ifNotExist(entity, Order.class, id)) {
			return true;
		}
		if (!entity.getWebsite().getId().equals(webId)) {
			errors.notInWeb(Order.class, id);
			return true;
		}
		if(entity.getStatus()!=0){
			errors.addError("订单状态为不可分配订单");
			return true;
		}
		return false;
	}
	private boolean vldBldExist(Long orderId,String bldId, Long webId, WebErrors errors) {
		if (errors.ifNull(bldId, "id")) {
			return true;
		}
		Bld entity=bldMng.findById(bldId);
		if (errors.ifNotExist(entity, Bld.class, bldId)) {
			return true;
		}
		if (!entity.getWebsite().getId().equals(webId)) {
			errors.notInWeb(Bld.class, bldId);
			return true;
		}
		Bld entitys = bldMng.getBldByOrder(orderMng.findById(orderId), bldId);
		if(entitys==null || entitys.getId()==null){
			errors.addError("该便利店不符合分配条件，请重新分配");
			return true;
		}
		return false;
	}
	private boolean vldGysExist(Long orderId,String gysId, Long webId, WebErrors errors) {
		if (errors.ifNull(gysId, "id")) {
			return true;
		}
		Gys entity=gysMng.findById(gysId);
		if (errors.ifNotExist(entity, Gys.class, gysId)) {
			return true;
		}
		if (!entity.getWebsite().getId().equals(webId)) {
			errors.notInWeb(Bld.class, gysId);
			return true;
		}
		Gys entitys = gysMng.getGysByOrder(orderMng.findById(orderId), gysId);
		if(entitys==null || entitys.getId()==null){
			errors.addError("该供应商不符合分配条件，请重新分配");
			return true;
		}
		return false;
	}
	
	
	public String getFileName() {
		return fileName;
	}
	public void setFileName(String fileName) {
		this.fileName = fileName;
	}
	public InputStream getInputStream() {
		return inputStream;
	}
	public void setInputStream(InputStream inputStream) {
		this.inputStream = inputStream;
	}
	public String getFiledataFileName() {
		return filedataFileName;
	}
	public void setFiledataFileName(String filedataFileName) {
		this.filedataFileName = filedataFileName;
	}


	@Autowired
	private WebsiteMng websiteMng;
	@Autowired
	private ShippingMng shippingMng;
	@Autowired
	private PaymentMng paymentMng;
	@Autowired
	private OrderMng orderMng;
	@Autowired
	private SqOrderMng sqOrderMng;
	@Autowired
	private OrderReturnMng orderReturnMng;
    @Autowired
	private ShopMemberMng shopMemberMng;
 	@Autowired
	private ShopScoreMng shopScoreMng;
	@Autowired
	private ProductMng productMng;
	@Autowired
	private BldMng bldMng;
	@Autowired
	private GysMng gysMng;
	@Autowired
	private ProductFashionMng productFashionMng;
	@Autowired
	private AccountItemMng accountItemMng;
	@Autowired
	private OrderItemMng orderItemMng;
	@Autowired
	private GatheringMng gatheringMng;
	@Autowired
	private LsfcMng lsfcMng;
	@Autowired
	private AccountMng accountMng;
	@Autowired
	private JxcBldGoodsNumMng jxcBldGoodsNumMng;
	@Autowired
	private JyspMng jyspMng;
	
}