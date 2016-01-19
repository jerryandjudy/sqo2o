package com.jspgou.cms.action.front;

import static com.jspgou.cms.Constants.MEMBER_SYS;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.jspgou.cms.entity.Order;
import com.jspgou.cms.entity.OrderReturn;
import com.jspgou.cms.entity.ShopDictionary;
import com.jspgou.cms.entity.ShopMember;
import com.jspgou.cms.manager.OrderMng;
import com.jspgou.cms.manager.OrderReturnMng;
import com.jspgou.cms.manager.ShopDictionaryMng;
import com.jspgou.cms.web.ShopFrontHelper;
import com.jspgou.cms.web.SiteUtils;
import com.jspgou.cms.web.threadvariable.MemberThread;
import com.jspgou.common.security.annotation.Secured;
import com.jspgou.common.web.springmvc.MessageResolver;
import com.jspgou.core.entity.User;
import com.jspgou.core.entity.Website;
import com.jspgou.core.web.WebErrors;
import com.jspgou.core.web.front.FrontHelper;

/**
* This class should preserve.
* @preserve
*/
@Secured
@Controller
public class OrderReturnAct {
	private static final Logger log = LoggerFactory.getLogger(OrderReturnAct.class);
	/**
	 * 我的订单
	 */
	private static final String NODELIVERY_ORDER_RETURN = "tpl.noDeliveryOrderReturn";
	private static final String DELIVERYED_ORDER_RETURN = "tpl.DeliveryedOrderReturn";
	private static final String NODELIVERY_RETURN_MONEY_SUCCESS = "tpl.NoDeliveryedReturnMoneySuccess";
	private static final String DELIVERY_RETURN_MONEY_SUCCESS = "tpl.DeliveryedReturnMoneySuccess";
	
	/**
	 * 订单退款申请页
	 */
	@RequestMapping(value = "/orderReturn/orderReturn.jspx")
	public String getOrderReturn(Long orderId,Boolean delivery,
			HttpServletRequest request,ModelMap model){
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
		ShopFrontHelper.setCommonData(request, model, web, 1);
		List<ShopDictionary> ndList=null;
		List<ShopDictionary> returnList=shopDictionaryMng.getListByType((long)9);
		model.addAttribute("returnList", returnList);
		model.addAttribute("order", order);	
		model.addAttribute("delivery", delivery);
//		if(delivery){
//			//已发货退款list
//			ndList=shopDictionaryMng.getListByType((long)8);
//			model.addAttribute("ndList", ndList);	
//			return web.getTplSys(MEMBER_SYS, MessageResolver.getMessage(request,DELIVERYED_ORDER_RETURN));
//		}else{
			//未发货退款list
			ndList=shopDictionaryMng.getListByType((long)6);
			model.addAttribute("ndList", ndList);	
		    return web.getTplSys(MEMBER_SYS, MessageResolver.getMessage(request,NODELIVERY_ORDER_RETURN));
//		}
	}
	
	/**
	 * 订单退款申请提交页
	 */
	@RequestMapping(value = "/orderReturn/orderReturnRefer.jspx", method = RequestMethod.POST)
	public String getOrderReturnRefer(OrderReturn bean,Long orderId,Boolean delivery,
			Long reasonId,String[] picPaths, String[] picDescs,HttpServletRequest request,ModelMap model){
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
		//生成退款记录
		OrderReturn orderReturn=orderReturnMng.save(bean,order,reasonId,delivery,picPaths,picDescs);
		log.debug("orderReturn createTime is: {}", orderReturn.getCreateTime());
		//修改订单
		order.setReturnOrder(orderReturn);
		orderMng.updateByUpdater(order);
		model.addAttribute("order", order);	
		model.addAttribute("orderReturn", orderReturn);
		ShopFrontHelper.setCommonData(request, model, web, 1);
		if(delivery){
			 return web.getTplSys(MEMBER_SYS, MessageResolver.getMessage(request,DELIVERY_RETURN_MONEY_SUCCESS));
		}else{
		     return web.getTplSys(MEMBER_SYS, MessageResolver.getMessage(request,NODELIVERY_RETURN_MONEY_SUCCESS));
		}
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
		if(!(order.getPaymentStatus()==2 && (order.getStatus()==0|| order.getStatus()==1) && order.getReturnOrder()==null&& order.getShippingStatus()==1 && order.getPayment().getId()!=3)){
			errors.addError("订单不符合退款条件");
			return errors;
		}
		return errors;
	}
	
	@Autowired
	private OrderMng orderMng;
	@Autowired
	private OrderReturnMng orderReturnMng;
	@Autowired
	private ShopDictionaryMng shopDictionaryMng;
}
