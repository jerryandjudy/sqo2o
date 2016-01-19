package com.jspgou.cms.action.front;


import java.io.BufferedOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.SortedMap;
import java.util.TreeMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.jspgou.cms.Alipay;
import com.jspgou.cms.entity.Order;
import com.jspgou.cms.entity.PaymentPlugins;
import com.jspgou.cms.entity.ShopMember;
import com.jspgou.cms.manager.OrderMng;
import com.jspgou.cms.manager.PaymentPluginsMng;
import com.jspgou.cms.web.FrontUtils;
import com.jspgou.cms.web.ShopFrontHelper;
import com.jspgou.cms.web.SiteUtils;
import com.jspgou.cms.web.threadvariable.MemberThread;
import com.jspgou.common.web.RequestUtils;
import com.jspgou.core.entity.User;
import com.jspgou.core.entity.Website;
import com.jspgou.core.web.WebErrors;
import com.jspgou.core.web.front.FrontHelper;
import com.tencent.demo.WxPayResult;
import com.tencent.utils.GetWxOrderno;
import com.tencent.utils.RequestHandler;
import com.tencent.utils.WxPayCodeRes;
import com.unionpay.acp.YlPay;
import com.unionpay.acp.sdk.SDKConfig;

/**
 * This class should preserve.
 * 
 * @preserve
 */
@Controller
public class AlipayAct extends Alipay {

	// 在线支付订单
	@RequestMapping(value = "/pay.jspx", method = RequestMethod.POST)
	public String pay(Long orderId, String code, String wycode,String wxcode,
			HttpServletRequest request, HttpServletResponse response,
			ModelMap model) {
		Website web = SiteUtils.getWeb(request);
		ShopMember member = MemberThread.get();
		WebErrors errors = validateOrderView(orderId, member, request);
		if (errors.hasErrors()) {
			return FrontHelper.showError(errors, web, model, request);
		}
		if (wycode != null && wycode.length() > 0) {
			code = "bankPay";
		}
		if (wxcode != null && wxcode.length() > 0) {
			code = "wxPay";
		}
		if (orderId != null && orderMng.findById(orderId) != null) {
			Order order = orderMng.findById(orderId);
			PaymentPlugins paymentPlugins = paymentPluginsMng.findByCode(code);
			PrintWriter out = null;
			String aliURL = null;
			try {
				if (!StringUtils.isBlank(code) && code.equals("alipayPartner")) {
					order.setPaymentCode(code);
					aliURL = alipay(paymentPlugins, web, order, request, model);
				} else if (!StringUtils.isBlank(code) && code.equals("alipay")) {
					order.setPaymentCode(code);
					aliURL = alipayapi(paymentPlugins, web, order, request,
							model);
				} else if (!StringUtils.isBlank(code) && code.equals("ylpay")) {
					order.setPaymentCode(code);
					aliURL = ylpay(paymentPlugins, web, order, request, model);
				} else if (!StringUtils.isBlank(code) && code.equals("bankPay")) {
					order.setPaymentCode(code);
					aliURL = this.alipaywy(paymentPlugins, wycode, web, order,
							request, model);
				} else if (!StringUtils.isBlank(code) && code.equals("wxPay")) {
					order.setPaymentCode(code);
					aliURL = wxpay(paymentPlugins, web, order, request, model);
				}
				orderMng.updateByUpdater(order);
				ShopFrontHelper.setCommonData(request, model, web, 1);
				response.setContentType("text/html;charset=UTF-8");
				out = response.getWriter();
				out.print(aliURL);
			} catch (IOException e) {
				e.printStackTrace();
			} finally {
				if(out!=null){
				out.close();
				}
			}
			return null;
		} else {
			return FrontHelper.pageNotFound(web, model, request);
		}
	}

	// 支付宝返回参数
	@RequestMapping(value = "/aliReturn.jspx")
	public String aliReturn(HttpServletRequest request,
			HttpServletResponse response, ModelMap model)
			throws UnsupportedEncodingException {
		Map<String, String> params = new HashMap<String, String>();
		Map requestParams = request.getParameterMap();
		for (Iterator iter = requestParams.keySet().iterator(); iter.hasNext();) {
			String name = (String) iter.next();
			String[] values = (String[]) requestParams.get(name);
			String valueStr = "";
			for (int i = 0; i < values.length; i++) {
				valueStr = (i == values.length - 1) ? valueStr + values[i]
						: valueStr + values[i] + ",";
			}
			params.put(name, valueStr);
		}
		// 商户订单号
		String out_trade_no = new String(request.getParameter("out_trade_no")
				.getBytes("ISO-8859-1"), "UTF-8");
		// 支付宝交易号
		String trade_no = new String(request.getParameter("trade_no").getBytes(
				"ISO-8859-1"), "UTF-8");
		// 支付宝交易金额
		String total_fee = new String(request.getParameter("total_fee").getBytes(
				"ISO-8859-1"), "UTF-8");
		// 交易状态
		String trade_status = new String(request.getParameter("trade_status")
				.getBytes("ISO-8859-1"), "UTF-8");
		Long code = Long.parseLong(out_trade_no);
		Order order = orderMng.findByCode(code);
		PaymentPlugins paymentPlugins = paymentPluginsMng.findByCode(order
				.getPaymentCode());
		if (verify(params, paymentPlugins.getPartner(),
				paymentPlugins.getSellerKey())) {
//			System.out.println("code===========:" + order.getCode());
			if (trade_status.equals("WAIT_BUYER_PAY")) {
				// 该判断表示买家已在支付宝交易管理中产生了交易记录，但没有付款
				return FrontUtils.showMessage(request, model, "付款失败！");
			} else if (trade_status.equals("WAIT_SELLER_SEND_GOODS")) {
				// 该判断表示买家已在支付宝交易管理中产生了交易记录且付款成功，但卖家没有发货
				order.setPaymentStatus(2);
				order.setTradeNo(trade_no);
				orderMng.updateByUpdater(order);
				return FrontUtils.showMessage(request, model, "付款成功，请等待发货！");
			} else if (trade_status.equals("WAIT_BUYER_CONFIRM_GOODS")) {
				// 该判断表示卖家已经发了货，但买家还没有做确认收货的操作
				return FrontUtils.showMessage(request, model, "已发货，未确认收货！");
			} else if (trade_status.equals("TRADE_FINISHED")) {
				// 该判断表示买家已经确认收货，这笔交易完成
				return FrontUtils.showMessage(request, model, "交易完成！");
			} else if (trade_status.equals("TRADE_SUCCESS")){
				if(Double.parseDouble(total_fee)==order.getTotal()){
					order.setPaymentStatus(2);
					order.setTradeNo(trade_no);
					orderMng.updateByUpdater(order);
					return FrontUtils.showMessage(request, model,"SUCCESS");
				}
			}
		}
		return FrontUtils.showMessage(request, model, "付款异常！");
	}
	// 微信返回参数
	@RequestMapping(value = "/wxReturn.jspx")
	public void wxReturn(HttpServletRequest request,
			HttpServletResponse response, ModelMap model) {
		//把如下代码贴到的你的处理回调的servlet 或者.do 中即可明白回调操作
		SimpleDateFormat sFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
				System.out.println("微信支付回调数据开始:"+sFormat.format(new Date()));
				//示例报文
//				String xml = "<xml><appid><![CDATA[wxb4dc385f953b356e]]></appid><bank_type><![CDATA[CCB_CREDIT]]></bank_type><cash_fee><![CDATA[1]]></cash_fee><fee_type><![CDATA[CNY]]></fee_type><is_subscribe><![CDATA[Y]]></is_subscribe><mch_id><![CDATA[1228442802]]></mch_id><nonce_str><![CDATA[1002477130]]></nonce_str><openid><![CDATA[o-HREuJzRr3moMvv990VdfnQ8x4k]]></openid><out_trade_no><![CDATA[1000000000051249]]></out_trade_no><result_code><![CDATA[SUCCESS]]></result_code><return_code><![CDATA[SUCCESS]]></return_code><sign><![CDATA[1269E03E43F2B8C388A414EDAE185CEE]]></sign><time_end><![CDATA[20150324100405]]></time_end><total_fee>1</total_fee><trade_type><![CDATA[JSAPI]]></trade_type><transaction_id><![CDATA[1009530574201503240036299496]]></transaction_id></xml>";
				String inputLine;
				String notityXml = "";
				String resXml = "";

				try {
					while ((inputLine = request.getReader().readLine()) != null) {
						notityXml += inputLine;
					}
					request.getReader().close();
				} catch (Exception e) {
					e.printStackTrace();
				}

//				System.out.println("接收到的报文：" + notityXml);
				
				Map m = parseXmlToList2(notityXml);
				String out_trade_no=m.get("out_trade_no").toString();
				String transaction_id=m.get("transaction_id").toString();
				
				WxPayResult wpr = new WxPayResult();
				wpr.setAppid(m.get("appid").toString());
				wpr.setBankType(m.get("bank_type").toString());
				wpr.setCashFee(m.get("cash_fee").toString());
				wpr.setFeeType(m.get("fee_type").toString());
				wpr.setIsSubscribe(m.get("is_subscribe").toString());
				wpr.setMchId(m.get("mch_id").toString());
				wpr.setNonceStr(m.get("nonce_str").toString());
				wpr.setOpenid(m.get("openid").toString());
				wpr.setOutTradeNo(out_trade_no);
				wpr.setResultCode(m.get("result_code").toString());
				wpr.setReturnCode(m.get("return_code").toString());
				wpr.setSign(m.get("sign").toString());
				wpr.setTimeEnd(m.get("time_end").toString());
				wpr.setTotalFee(m.get("total_fee").toString());
				wpr.setTradeType(m.get("trade_type").toString());
				wpr.setTransactionId(transaction_id);
				Long code = Long.parseLong(out_trade_no);
				Order order = orderMng.findByCode(code);
				if(order.getPaymentStatus()==2){
					return;
				}
				PaymentPlugins paymentPlugins = paymentPluginsMng.findByCode(order
						.getPaymentCode());
				if("SUCCESS".equals(wpr.getResultCode())){
					//支付成功
					System.out.println("微信支付回调状态：返回 成功");
					Double total_fee=Double.parseDouble(m.get("total_fee").toString());
					if(total_fee==Double.parseDouble(getMoney(order.getTotal().toString()))){
					resXml = "<xml>" + "<return_code><![CDATA[SUCCESS]]></return_code>"
					+ "<return_msg><![CDATA[OK]]></return_msg>" + "</xml> ";
					order.setPaymentStatus(2);
					order.setTradeNo(transaction_id);
					orderMng.updateByUpdater(order);
					}
				}else{
					resXml = "<xml>" + "<return_code><![CDATA[FAIL]]></return_code>"
					+ "<return_msg><![CDATA[报文为空]]></return_msg>" + "</xml> ";
				}

				System.out.println("微信支付回调数据结束");

				BufferedOutputStream out;
				try {
					out = new BufferedOutputStream(
							response.getOutputStream());
//					response.getWriter().print(resXml);
					out.write(resXml.getBytes());
					out.flush();
					out.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

	}

	// 银联返回参数
	@RequestMapping(value = "/ylReturn.jspx")
	public String ylReturn(HttpServletRequest request,
			HttpServletResponse response, ModelMap model)
			throws UnsupportedEncodingException {
		Map<String, String> params = new HashMap<String, String>();
		Map requestParams = request.getParameterMap();
		for (Iterator iter = requestParams.keySet().iterator(); iter.hasNext();) {
			String name = (String) iter.next();
			String[] values = (String[]) requestParams.get(name);
			String valueStr = "";
			for (int i = 0; i < values.length; i++) {
				valueStr = (i == values.length - 1) ? valueStr + values[i]
						: valueStr + values[i] + ",";
			}
			params.put(name, valueStr);
		}
		// 商户订单号
		String out_trade_no = new String(request.getParameter("out_trade_no")
				.getBytes("ISO-8859-1"), "UTF-8");
		// 支付宝交易号
		String trade_no = new String(request.getParameter("trade_no").getBytes(
				"ISO-8859-1"), "UTF-8");
		// 交易状态
		String trade_status = new String(request.getParameter("trade_status")
				.getBytes("ISO-8859-1"), "UTF-8");
		Long code = Long.parseLong(out_trade_no);
		Order order = orderMng.findByCode(code);
		PaymentPlugins paymentPlugins = paymentPluginsMng.findByCode(order
				.getPaymentCode());
		if (verify(params, paymentPlugins.getPartner(),
				paymentPlugins.getSellerKey())) {
			System.out.println("code===========:" + order.getCode());
			if (trade_status.equals("WAIT_BUYER_PAY")) {
				// 该判断表示买家已在支付宝交易管理中产生了交易记录，但没有付款
				return FrontUtils.showMessage(request, model, "付款失败！");
			} else if (trade_status.equals("WAIT_SELLER_SEND_GOODS")) {
				// 该判断表示买家已在支付宝交易管理中产生了交易记录且付款成功，但卖家没有发货
				order.setPaymentStatus(2);
				order.setTradeNo(trade_no);
				orderMng.updateByUpdater(order);
				return FrontUtils.showMessage(request, model, "付款成功，请等待发货！");
			} else if (trade_status.equals("WAIT_BUYER_CONFIRM_GOODS")) {
				// 该判断表示卖家已经发了货，但买家还没有做确认收货的操作
				return FrontUtils.showMessage(request, model, "已发货，未确认收货！");
			} else if (trade_status.equals("TRADE_FINISHED")) {
				// 该判断表示买家已经确认收货，这笔交易完成
				return FrontUtils.showMessage(request, model, "交易完成！");
			} else {
				order.setPaymentStatus(2);
				order.setTradeNo(trade_no);
				orderMng.updateByUpdater(order);
				return FrontUtils.showMessage(request, model,"SUCCESS");
			}
		}
		return FrontUtils.showMessage(request, model, "付款异常！");
	}

	private String alipay(PaymentPlugins paymentPlugins, Website web,
			Order order, HttpServletRequest request, ModelMap model) {
		// 支付类型
		String payment_type = "1"; // 必填，不能修改
		// 服务器异步通知页面路径
		String notify_url = "http://" + web.getDomain();
		if (web.getGlobal() != null && web.getGlobal().getPort() != null
				&& web.getGlobal().getPort() > 0) {
			notify_url = notify_url + ":" + web.getGlobal().getPort();
		}
		if (web.getGlobal() != null && web.getGlobal().getContextPath() != null
				&& web.getGlobal().getContextPath().length() > 0) {
			notify_url = notify_url + web.getGlobal().getContextPath();
		}
		notify_url = notify_url + "/aliReturn.jspx";
		System.out.println("notify_url==================:" + notify_url);
		// 页面跳转同步通知页面路径
		// String return_url =
		// "http://"+web.getDomain()+":"+web.getGlobal().getPort()+"/aliReturnUrl.jspx";
		String return_url = "http://" + web.getDomain();
		if (web.getGlobal() != null && web.getGlobal().getPort() != null
				&& web.getGlobal().getPort() > 0) {
			return_url = return_url + ":" + web.getGlobal().getPort();
		}
		if (web.getGlobal() != null && web.getGlobal().getContextPath() != null
				&& web.getGlobal().getContextPath().length() > 0) {
			return_url = return_url + web.getGlobal().getContextPath();
		}
		return_url = return_url + "/aliReturnUrl.jspx";
		// 卖家支付宝帐户
		String seller_email = paymentPlugins.getSellerEmail();// 必填
		// 商户订单号
		String out_trade_no = order.getCode() + "";// 商户网站订单系统中唯一订单号，必填
		// 订单名称
		String subject = order.getProductName();// 必填
		if (subject.length() > 128) {
			subject = subject.substring(0, 123) + "...";
		}
		// 付款金额
		String price = String.valueOf(order.getTotal());// 必填
		// 商品数量
		String quantity = "1";// 必填，建议默认为1，不改变值，把一次交易看成是一次下订单而非购买一件商品
		// 物流费用
		String logistics_fee = String.valueOf(order.getFreight());
		// 物流类型
		String logistics_type = getLogisticsType(order);// 必填，三个值可选：EXPRESS（快递）、POST（平邮）、EMS（EMS）
		// 物流支付方式
		String logistics_payment = "BUYER_PAY";// 必填，两个值可选：SELLER_PAY（卖家承担运费）、BUYER_PAY（买家承担运费）
		// 订单描述
		String body = order.getProductName();
		// 商品展示地址
		String show_url = "http://" + web.getDomain() + "/";
		// 收货人姓名
		String receive_name = order.getReceiveName();
		// 收货人地址
		String receive_address = order.getReceiveAddress();
		// 收货人邮编
		String receive_zip = order.getReceiveZip();
		// 收货人电话号码
		String receive_phone = order.getReceivePhone();
		// 收货人手机号码
		String receive_mobile = order.getReceiveMobile();
		// 把请求参数打包成数组
		Map<String, String> sParaTemp = new HashMap<String, String>();
		sParaTemp.put("service", "create_partner_trade_by_buyer");
		sParaTemp.put("partner", paymentPlugins.getPartner());
		sParaTemp.put("_input_charset", "utf-8");
		sParaTemp.put("payment_type", payment_type);
		sParaTemp.put("notify_url", notify_url);
		// sParaTemp.put("return_url", return_url);
		sParaTemp.put("seller_email", seller_email);
		sParaTemp.put("out_trade_no", out_trade_no);
		sParaTemp.put("subject", subject);
		sParaTemp.put("price", price);
		sParaTemp.put("quantity", quantity);
		sParaTemp.put("logistics_fee", logistics_fee);
		sParaTemp.put("logistics_type", logistics_type);
		sParaTemp.put("logistics_payment", logistics_payment);
		sParaTemp.put("body", body);
		sParaTemp.put("show_url", show_url);
		sParaTemp.put("receive_name", receive_name);
		sParaTemp.put("receive_address", receive_address);
		sParaTemp.put("receive_zip", receive_zip);
		sParaTemp.put("receive_phone", receive_phone);
		sParaTemp.put("receive_mobile", receive_mobile);
		// 建立请求
		String sHtmlText = buildRequest(sParaTemp,
				paymentPlugins.getSellerKey(), "get", "确认");
		return sHtmlText;
	}

	// 支付宝返回参数
	@RequestMapping(value = "/aliReturnUrl.jspx")
	public String aliReturndirect(HttpServletRequest request,
			HttpServletResponse response, ModelMap model)
			throws UnsupportedEncodingException {
		// 获取支付宝POST过来反馈信息
		User user = SiteUtils.getUser(request);
		Map<String, String> params = new HashMap<String, String>();
		Map requestParams = request.getParameterMap();
		for (Iterator iter = requestParams.keySet().iterator(); iter.hasNext();) {
			String name = (String) iter.next();
			String[] values = (String[]) requestParams.get(name);
			String valueStr = "";
			for (int i = 0; i < values.length; i++) {
				valueStr = (i == values.length - 1) ? valueStr + values[i]
						: valueStr + values[i] + ",";
			}
			// 乱码解决，这段代码在出现乱码时使用。如果mysign和sign不相等也可以使用这段代码转化
			// valueStr = new String(valueStr.getBytes("ISO-8859-1"), "gbk");
			params.put(name, valueStr);
		}
		// 商户订单号
		String out_trade_no = new String(request.getParameter("out_trade_no")
				.getBytes("ISO-8859-1"), "UTF-8");
		// 支付宝交易号
		String trade_no = new String(request.getParameter("trade_no").getBytes(
				"ISO-8859-1"), "UTF-8");
		// 交易状态
		String trade_status = new String(request.getParameter("trade_status")
				.getBytes("ISO-8859-1"), "UTF-8");

		PaymentPlugins paymentPlugins = paymentPluginsMng.findByCode("alipay");
		Long orderId = Long.parseLong(out_trade_no);
		Order order = orderMng.findById(orderId);
		if (verify(params, paymentPlugins.getPartner(),
				paymentPlugins.getSellerKey())) {// 验证成功
			if (trade_status.equals("TRADE_FINISHED")) {
				// 判断该笔订单是否在商户网站中已经做过处理
				// 如果没有做过处理，根据订单号（out_trade_no）在商户网站的订单系统中查到该笔订单的详细，并执行商户的业务程序
				// 如果有做过处理，不执行商户的业务程序
				order.setTradeNo(trade_no);
				order.setLastModified(new Date());
				order.setPaymentStatus(2);
				order.setPaymentCode("alipay");
				orderMng.updateByUpdater(order);
				user = SiteUtils.getUser(request);
				return FrontUtils.showMessage(request, model, "SUCCESS");
				// 注意：
				// 该种交易状态只在两种情况下出现
				// 1、开通了普通即时到账，买家付款成功后。
				// 2、开通了高级即时到账，从该笔交易成功时间算起，过了签约时的可退款时限（如：三个月以内可退款、一年以内可退款等）后。
			} else if (trade_status.equals("TRADE_SUCCESS")) {
				// 判断该笔订单是否在商户网站中已经做过处理
				// 如果没有做过处理，根据订单号（out_trade_no）在商户网站的订单系统中查到该笔订单的详细，并执行商户的业务程序
				// 如果有做过处理，不执行商户的业务程序
				order.setTradeNo(trade_no);
				order.setLastModified(new Date());
				order.setPaymentStatus(2);
				order.setPaymentCode("alipay");
				orderMng.updateByUpdater(order);
				user = SiteUtils.getUser(request);
				return FrontUtils.showMessage(request, model, "SUCCESS");
				// 注意：
				// 该种交易状态只在一种情况下出现——开通了高级即时到账，买家付款成功后。
			}
		} else {// 验证失败
			return FrontUtils.showMessage(request, model, "验证失败！");
		}
		return FrontUtils.showMessage(request, model, "付款异常！");
	}

	private String alipayapi(PaymentPlugins paymentPlugins, Website web,
			Order order, HttpServletRequest request, ModelMap model) {
		// 支付类型
		String payment_type = "1";// 必填，不能修改
		// 服务器异步通知页面路径
		String notify_url = "http://" + web.getDomain();
		if (web.getGlobal() != null && web.getGlobal().getPort() != null
				&& web.getGlobal().getPort() > 0) {
			notify_url = notify_url + ":" + web.getGlobal().getPort();
		}
		if (web.getGlobal() != null && web.getGlobal().getContextPath() != null
				&& web.getGlobal().getContextPath().length() > 0) {
			notify_url = notify_url + web.getGlobal().getContextPath();
		}
		notify_url = notify_url + "/aliReturn.jspx";
		System.out.println("notify_url==================:" + notify_url);
		// 页面跳转同步通知页面路径
		// String return_url =
		// "http://"+web.getDomain()+":"+web.getGlobal().getPort()+"/aliReturnUrl.jspx";
		String return_url = "http://" + web.getDomain();
		if (web.getGlobal() != null && web.getGlobal().getPort() != null
				&& web.getGlobal().getPort() > 0) {
			return_url = return_url + ":" + web.getGlobal().getPort();
		}
		if (web.getGlobal() != null && web.getGlobal().getContextPath() != null
				&& web.getGlobal().getContextPath().length() > 0) {
			return_url = return_url + web.getGlobal().getContextPath();
		}
		return_url = return_url + "/aliReturnUrl.jspx";
		// 卖家支付宝帐户
		String seller_email = paymentPlugins.getSellerEmail();// 必填
		// 商户订单号
		String out_trade_no = order.getCode() + "";// 商户网站订单系统中唯一订单号，必填
		// 订单名称
		String subject = order.getProductName();// 必填
		if (subject.length() > 128) {
			subject = subject.substring(0, 123) + "...";
		}
		// 付款金额
		String total_fee = String.valueOf(order.getTotal());// 必填
		// 订单描述
		String body = order.getProductName();
		// 商品展示地址
		String show_url = "http://" + web.getDomain();
		if (web.getGlobal() != null && web.getGlobal().getPort() != null
				&& web.getGlobal().getPort() > 0) {
			show_url = show_url + ":" + web.getGlobal().getPort();
		}
		if (web.getGlobal() != null && web.getGlobal().getContextPath() != null
				&& web.getGlobal().getContextPath().length() > 0) {
			show_url = show_url + web.getGlobal().getContextPath();
		}
		// 防钓鱼时间戳
		String anti_phishing_key = "";
		// try {
		// anti_phishing_key = query_timestamp();
		// } catch (MalformedURLException e) {
		// // TODO Auto-generated catch block
		// e.printStackTrace();
		// } catch (DocumentException e) {
		// // TODO Auto-generated catch block
		// e.printStackTrace();
		// } catch (IOException e) {
		// // TODO Auto-generated catch block
		// e.printStackTrace();
		// }//若要使用请调用类文件submit中的query_timestamp函数
		// 客户端的IP地址
		String exter_invoke_ip = RequestUtils.getIpAddr(request);// 非局域网的外网IP地址，如：221.0.0.1
		Map<String, String> sParaTemp = new HashMap<String, String>();
		sParaTemp.put("service", "create_direct_pay_by_user");
		sParaTemp.put("partner", paymentPlugins.getPartner());
		sParaTemp.put("_input_charset", "utf-8");
		sParaTemp.put("payment_type", payment_type);
		sParaTemp.put("notify_url", notify_url);
		// sParaTemp.put("return_url", return_url);
		sParaTemp.put("seller_email", seller_email);
		sParaTemp.put("out_trade_no", out_trade_no);
		sParaTemp.put("subject", subject);
		sParaTemp.put("total_fee", total_fee);
		sParaTemp.put("body", body);
		sParaTemp.put("show_url", show_url);
		// sParaTemp.put("anti_phishing_key", anti_phishing_key);
		sParaTemp.put("exter_invoke_ip", exter_invoke_ip);
		// 建立请求
		String sHtmlText = buildRequest(sParaTemp,
				paymentPlugins.getSellerKey(), "get", "确认");
		return sHtmlText;
	}

	/**
	 * 扫码支付
	 * 
	 * @param paymentPlugins
	 * @param web
	 * @param order
	 * @param request
	 * @param model
	 * @return
	 */
	private String wxpay(PaymentPlugins paymentPlugins, Website web,
			Order order, HttpServletRequest request, ModelMap model) {
		// 服务器异步通知页面路径
		String notify_url = "http://" + web.getDomain() ;
		if (web.getGlobal() != null && web.getGlobal().getPort() != null
				&& web.getGlobal().getPort() > 0) {
			notify_url = notify_url + ":"+ web.getGlobal().getPort() ;
		}
		if (web.getGlobal() != null && web.getGlobal().getContextPath() != null
				&& web.getGlobal().getContextPath().length() > 0) {
			notify_url = notify_url+ web.getGlobal().getContextPath();
		}
		notify_url = notify_url + "/wxReturn.jspx";
		
		
		// 商户订单号
		String out_trade_no = order.getCode()+"";// 商户网站订单系统中唯一订单号，必填
		
		// 付款金额
		String total_fee = String.valueOf(order.getTotal());// 必填
		// 订单描述
		String body = order.getProductName();
		// 商品展示地址
		String show_url = "http://" + web.getDomain() ;
		if (web.getGlobal() != null && web.getGlobal().getPort() != null
				&& web.getGlobal().getPort() > 0) {
			show_url = show_url + ":"+ web.getGlobal().getPort() ;
		}
		if (web.getGlobal() != null && web.getGlobal().getContextPath() != null
				&& web.getGlobal().getContextPath().length() > 0) {
			show_url = show_url+ web.getGlobal().getContextPath();
		}

		// 客户端的IP地址
		String exter_invoke_ip = RequestUtils.getIpAddr(request);// 非局域网的外网IP地址，如：221.0.0.1
		        // 1 参数
		String appid=paymentPlugins.getSellerKey();
		String appsecret = paymentPlugins.getSellerEmail();
		String partner = paymentPlugins.getPartner();
		   	//这个参数partnerkey是在商户后台配置的一个32位的key,微信商户平台-账户设置-安全设置-api安全
		String partnerkey = paymentPlugins.getPartnerKey();
				// 订单号
				String orderId = order.getCode()+"";
				// 附加数据 原样返回
				String attach = "";
				// 总金额以分为单位，不带小数点
				String totalFee = getMoney(total_fee);
				
				// 订单生成的机器 IP
				String spbill_create_ip = exter_invoke_ip;
				// 这里notify_url是 支付完成后微信发给该链接信息，可以判断会员是否支付成功，改变订单状态等。
				String trade_type = "NATIVE";
				// 商户号
				String mch_id = paymentPlugins.getPartner();
				// 随机字符串
				String nonce_str = getNonceStr();
				SortedMap<String, String> packageParams = new TreeMap<String, String>();
				packageParams.put("appid", appid);
				packageParams.put("mch_id", partner);
				packageParams.put("nonce_str", nonce_str);
				packageParams.put("body", body);
				packageParams.put("attach", attach);
				packageParams.put("out_trade_no", out_trade_no);

				// 这里写的金额为1 分到时修改
				packageParams.put("total_fee", totalFee);
				packageParams.put("spbill_create_ip", spbill_create_ip);
				packageParams.put("notify_url", notify_url);

				packageParams.put("trade_type", trade_type);

				RequestHandler reqHandler = new RequestHandler(null, null);
				reqHandler.init(appid, appsecret, partnerkey);

				String sign = reqHandler.createSign(packageParams);
				String xml = "<xml>" + "<appid>" + appid + "</appid>" + "<mch_id>"
						+ mch_id + "</mch_id>" + "<nonce_str>" + nonce_str
						+ "</nonce_str>" + "<sign>" + sign + "</sign>"
						+ "<body><![CDATA[" + body + "]]></body>" 
						+ "<out_trade_no>" + out_trade_no
						+ "</out_trade_no>" + "<attach>" + attach + "</attach>"
						+ "<total_fee>" + totalFee + "</total_fee>"
						+ "<spbill_create_ip>" + spbill_create_ip
						+ "</spbill_create_ip>" + "<notify_url>" + notify_url
						+ "</notify_url>" + "<trade_type>" + trade_type
						+ "</trade_type>" + "</xml>";
				String createOrderURL = "https://api.mch.weixin.qq.com/pay/unifiedorder";
				String reshtml="<!doctype html><html><meta charset=\"utf-8\"><meta http-equiv=\"X-UA-Compatible\" content=\"IE=edge\"><meta name=\"viewport\" content=\"width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no\"><head><title>微信扫码支付</title><meta name=\"content-type\" content=\"text/html;charset=utf-8\">"
						+ "<link rel=\"stylesheet\" href=\""+show_url+"/wapstyle/assets/css/amazeui.min.css?version="+new Date().getTime()+"\"><link rel=\"stylesheet\" href=\""+show_url+"/wapstyle/assets/css/app.css?version="+new Date().getTime()+"\"><script src=\""+show_url+"/wapstyle/assets/js/jquery.min.js?version="+new Date().getTime()+"\"></script><script src=\""+show_url+"/wapstyle/assets/js/amazeui.min.js?version="+new Date().getTime()+"\"></script><script src=\""+show_url+"/wapstyle/assets/js/app.js?version="+new Date().getTime()+"\"></script>"
						+ "<script src=\""+show_url+"/r/gou/www/assets/js/qrcode.js\"></script></head><body><div align=\"center\" id=\"qrcode\"></div>buttonset</body><script>createscript</script></html>";
				String buttons="";
				WxPayCodeRes wxPayCodeRes = new GetWxOrderno().getCodeUrl(createOrderURL, xml);
				if(wxPayCodeRes.getCode().indexOf("FAIL")!=-1||wxPayCodeRes==null){
					String scrs="var dom=document.createElement('DIV');dom.innerHTML = '"+wxPayCodeRes.getMsg()+"';var element=document.getElementById('qrcode');element.appendChild(dom);";
					reshtml=reshtml.replace("createscript",scrs);
					reshtml=reshtml.replace("buttonset", "<a class=\"am-btn  am-btn-block\" href='"+show_url+"/order/myorder.jspx'>返回我的订单</>");
				}else{
					String scrs="var url = '"+wxPayCodeRes.getMsg()+"';var qr = qrcode(10, 'M');qr.addData(url);qr.make();var dom=document.createElement('DIV');dom.innerHTML = qr.createImgTag();var element=document.getElementById('qrcode');element.appendChild(dom);";
					reshtml=reshtml.replace("createscript", scrs);
					reshtml=reshtml.replace("buttonset", "<a class=\"am-btn  am-btn-block\" href='"+show_url+"/order/myorder.jspx'>返回我的订单</>");
				}
				return reshtml;
	}

	/**
	 * 支付宝网银支付
	 * 
	 * @param paymentPlugins
	 * @param wycode
	 * @param web
	 * @param order
	 * @param request
	 * @param model
	 * @return
	 */
	private String alipaywy(PaymentPlugins paymentPlugins, String wycode,
			Website web, Order order, HttpServletRequest request, ModelMap model) {
		// 支付类型
		String payment_type = "1";// 必填，不能修改
		// 服务器异步通知页面路径
		String notify_url = "http://" + web.getDomain();
		if (web.getGlobal() != null && web.getGlobal().getPort() != null
				&& web.getGlobal().getPort() > 0) {
			notify_url = notify_url + ":" + web.getGlobal().getPort();
		}
		if (web.getGlobal() != null && web.getGlobal().getContextPath() != null
				&& web.getGlobal().getContextPath().length() > 0) {
			notify_url = notify_url + web.getGlobal().getContextPath();
		}
		notify_url = notify_url + "/aliReturn.jspx";
		System.out.println("notify_url==================:" + notify_url);
		// 页面跳转同步通知页面路径
		// String return_url =
		// "http://"+web.getDomain()+":"+web.getGlobal().getPort()+"/aliReturnUrl.jspx";
		String return_url = "http://" + web.getDomain();

		if (web.getGlobal() != null && web.getGlobal().getPort() != null
				&& web.getGlobal().getPort() > 0) {
			return_url = return_url + ":" + web.getGlobal().getPort();
		}
		if (web.getGlobal() != null && web.getGlobal().getContextPath() != null
				&& web.getGlobal().getContextPath().length() > 0) {
			return_url = return_url + web.getGlobal().getContextPath();
		}
		return_url = return_url + "/aliReturnUrl.jspx";
		// 卖家支付宝帐户
		String seller_email = paymentPlugins.getSellerEmail();// 必填
		// 商户订单号
		String out_trade_no = order.getCode() + "";// 商户网站订单系统中唯一订单号，必填
		// 订单名称
		String subject = order.getProductName();// 必填
		if (subject.length() > 128) {
			subject = subject.substring(0, 123) + "...";
		}
		// 付款金额
		String total_fee = String.valueOf(order.getTotal());// 必填
		// 订单描述
		String body = order.getProductName();
		// 商品展示地址
		String show_url = "http://" + web.getDomain();
		if (web.getGlobal() != null && web.getGlobal().getPort() != null
				&& web.getGlobal().getPort() > 0) {
			show_url = show_url + ":" + web.getGlobal().getPort();
		}
		if (web.getGlobal() != null && web.getGlobal().getContextPath() != null
				&& web.getGlobal().getContextPath().length() > 0) {
			show_url = show_url + web.getGlobal().getContextPath();
		}

		// 防钓鱼时间戳
		String anti_phishing_key = "";
		String exter_invoke_ip = RequestUtils.getIpAddr(request);// 非局域网的外网IP地址，如：221.0.0.1
		Map<String, String> sParaTemp = new HashMap<String, String>();
		sParaTemp.put("service", "create_direct_pay_by_user");
		sParaTemp.put("partner", paymentPlugins.getPartner());
		sParaTemp.put("seller_email", seller_email);
		sParaTemp.put("_input_charset", "utf-8");
		sParaTemp.put("payment_type", payment_type);
		sParaTemp.put("notify_url", notify_url);
		// sParaTemp.put("return_url", return_url);
		sParaTemp.put("out_trade_no", out_trade_no);
		sParaTemp.put("subject", subject);
		sParaTemp.put("total_fee", total_fee);
		sParaTemp.put("body", body);
		sParaTemp.put("paymethod", order.getPaymentCode());
		sParaTemp.put("defaultbank", wycode);
		sParaTemp.put("show_url", show_url);
		sParaTemp.put("anti_phishing_key", anti_phishing_key);
		sParaTemp.put("exter_invoke_ip", exter_invoke_ip);

		// 建立请求
		String sHtmlText = buildRequest(sParaTemp,
				paymentPlugins.getSellerKey(), "get", "确认");
		return sHtmlText;
	}

	/**
	 * 银联在线支付
	 * 
	 * @param paymentPlugins
	 * @param web
	 * @param order
	 * @param request
	 * @param model
	 * @return
	 */
	private String ylpay(PaymentPlugins paymentPlugins, Website web,
			Order order, HttpServletRequest request, ModelMap model) {
		// 服务器异步通知页面路径
		String notify_url = "http://" + web.getDomain();
		if (web.getGlobal() != null && web.getGlobal().getPort() != null
				&& web.getGlobal().getPort() > 0) {
			notify_url = notify_url + ":" + web.getGlobal().getPort();
		}
		if (web.getGlobal() != null && web.getGlobal().getContextPath() != null
				&& web.getGlobal().getContextPath().length() > 0) {
			notify_url = notify_url + web.getGlobal().getContextPath();
		}
		notify_url = notify_url + "/ylReturn.jspx";
		/**
		 * 组装请求报文
		 */
		Map<String, String> data = new HashMap<String, String>();
		// 版本号
		data.put("version", "5.0.0");
		// 字符集编码 默认"UTF-8"
		data.put("encoding", "UTF-8");
		// 签名方法 01 RSA
		data.put("signMethod", "01");
		// 交易类型 01-消费
		data.put("txnType", "01");
		// 交易子类型 01:自助消费 02:订购 03:分期付款
		data.put("txnSubType", "01");
		// 业务类型
		data.put("bizType", "000201");
		// 渠道类型，07-PC，08-手机
		data.put("channelType", "07");
		// 前台通知地址 ，控件接入方式无作用
		// data.put("frontUrl",
		// "http://localhost:8080/ACPTest/acp_front_url.do");
		// 后台通知地址
		data.put("backUrl", notify_url);
		// 接入类型，商户接入填0 0- 商户 ， 1： 收单， 2：平台商户
		data.put("accessType", "0");
		// 商户号码，请改成自己的商户号
		data.put("merId", paymentPlugins.getPartner());
		// 商户订单号，8-40位数字字母
		data.put("orderId", order.getCode() + "");
		// 订单发送时间，取系统时间
		data.put("txnTime",
				new SimpleDateFormat("yyyyMMddHHmmss").format(new Date()));
		// 交易金额，单位分
		data.put("txnAmt", order.getTotal() + "");
		// 交易币种
		data.put("currencyCode", "156");
		// 请求方保留域，透传字段，查询、通知、对账文件中均会原样出现
		// data.put("reqReserved", "透传信息");
		// 订单描述，可不上送，上送时控件中会显示该信息
		data.put("orderDesc", order.getProductName());

		Map<String, String> submitFromData = YlPay.signData(data);

		// 交易请求url 从配置文件读取
		String requestFrontUrl = SDKConfig.getConfig().getFrontRequestUrl();

		/**
		 * 创建表单
		 */
		String html = YlPay.createHtml(requestFrontUrl, submitFromData);
		return html;
	}

	public String getLogisticsType(Order order) {
		String logistics_type;
		if (!StringUtils.isBlank(order.getShipping().getLogisticsType())) {
			logistics_type = order.getShipping().getLogisticsType();
		} else {
			logistics_type = "EXPRESS";
		}
		return logistics_type;

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

	@Autowired
	private OrderMng orderMng;
	@Autowired
	private PaymentPluginsMng paymentPluginsMng;
}
