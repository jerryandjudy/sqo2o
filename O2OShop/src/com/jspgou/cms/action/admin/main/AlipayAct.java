package com.jspgou.cms.action.admin.main;

import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import com.jspgou.cms.Alipay;
import com.jspgou.cms.entity.Order;
import com.jspgou.cms.entity.PaymentPlugins;
import com.jspgou.cms.entity.Shipments;
import com.jspgou.cms.entity.ShopAdmin;
import com.jspgou.cms.manager.OrderMng;
import com.jspgou.cms.manager.PaymentPluginsMng;
import com.jspgou.cms.manager.ShipmentsMng;
import com.jspgou.cms.web.threadvariable.AdminThread;

/**
* This class should preserve.
* @preserve
*/
@Controller
public class AlipayAct extends Alipay {
    
	@RequestMapping("/order/o_shipments.do")
	public String shipments(Shipments bean,Long id, HttpServletRequest request,HttpServletResponse response, ModelMap model) {
		Order order =  orderMng.findById(id);
		ShopAdmin admin = AdminThread.get();
		bean.setShopAdmin(admin);
		bean.setIndent(order);
		if(order.getPayment().getType()==1){
			if(order.getShippingStatus()==1&&order.getStatus()==2&&order.getPaymentStatus()==2){
				shipments(bean,order,id,response);
			}
		}else{
			if(order.getShippingStatus()==1&&order.getStatus()==2){
				shipments(bean,order,id,response);
			}
		}
		model.addAttribute("order", order);
		return "order/view";
	}
	
	public void shipments(Shipments bean,Order order,Long id,HttpServletResponse response){
		Shipments shipments =shipmentsMng.save(bean);
		order.setShippingStatus(2);
		orderMng.updateByUpdater(order);
		orderMng.updateSaleCount(id);
		if(order.getPaymentCode()!=null){
			PaymentPlugins paymentPlugins = paymentPluginsMng.findByCode(order.getPaymentCode());
			if(!StringUtils.isBlank(order.getPaymentCode())&&order.getPaymentCode().equals("alipayPartner")){
					try {
						alipay(paymentPlugins,order,shipments.getWaybill());
					} catch (Exception e) {
						e.printStackTrace();
					}		
			}
		}
		
		
	}

	
	private String alipay(PaymentPlugins paymentPlugins,Order order,String waybill) throws Exception{
////////////////////////////////////请求参数//////////////////////////////////////

		//支付宝交易号
		String trade_no = order.getTradeNo();
		//必填

		//物流公司名称
		String logistics_name = order.getShipping().getLogistics().getName();
		//必填

		//物流发货单号
		String invoice_no = waybill;
		//物流运输类型
		String transport_type = order.getShipping().getLogisticsType();
		//三个值可选：POST（平邮）、EXPRESS（快递）、EMS（EMS）
		//////////////////////////////////////////////////////////////////////////////////
		//把请求参数打包成数组
		Map<String, String> sParaTemp = new HashMap<String, String>();
		sParaTemp.put("service", "send_goods_confirm_by_platform");
        sParaTemp.put("partner", paymentPlugins.getPartner());
        sParaTemp.put("_input_charset", "utf-8");
		sParaTemp.put("trade_no", trade_no);
		sParaTemp.put("logistics_name", logistics_name);
		sParaTemp.put("invoice_no", invoice_no);
		sParaTemp.put("transport_type", transport_type);
		
		//建立请求
		String sHtmlText = buildRequest("", "", sParaTemp,paymentPlugins.getSellerKey());
		return sHtmlText;
	}
	
	@Autowired
	private OrderMng orderMng;
	@Autowired
	private PaymentPluginsMng paymentPluginsMng;
	@Autowired
	private ShipmentsMng shipmentsMng;

}
