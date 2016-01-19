package com.jspgou.cms.action.admin.main;

import static com.jspgou.common.page.SimplePage.cpn;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.SortedMap;
import java.util.TreeMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.codec.digest.DigestUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import com.jspgou.cms.Alipay;
import com.jspgou.cms.entity.Order;
import com.jspgou.cms.entity.OrderReturn;
import com.jspgou.cms.entity.PaymentPlugins;
import com.jspgou.cms.entity.ShopMember;
import com.jspgou.cms.entity.ShopScore;
import com.jspgou.cms.manager.OrderMng;
import com.jspgou.cms.manager.OrderReturnMng;
import com.jspgou.cms.manager.PaymentPluginsMng;
import com.jspgou.cms.manager.ProductFashionMng;
import com.jspgou.cms.manager.ProductMng;
import com.jspgou.cms.manager.ShopMemberMng;
import com.jspgou.cms.manager.ShopScoreMng;
import com.jspgou.cms.web.FrontUtils;
import com.jspgou.common.page.Pagination;
import com.jspgou.common.web.CookieUtils;
import com.jspgou.core.entity.Website;
import com.jspgou.core.web.SiteUtils;
import com.jspgou.core.web.WebErrors;
import com.tencent.utils.GetWxOrderno;
import com.tencent.utils.RequestHandler;

/**
 * This class should preserve.
 * 
 * @preserve
 */
@Controller
public class OrderReturnAct extends Alipay {
	private static final Logger log = LoggerFactory
			.getLogger(OrderReturnAct.class);

	@RequestMapping("/orderReturn/v_list.do")
	public String list(Integer status, Integer pageNo,
			HttpServletRequest request, ModelMap model) {
		Pagination pagination = orderReturnMng.getPage(status, cpn(pageNo),
				CookieUtils.getPageSize(request));
		model.addAttribute("pagination", pagination);
		model.addAttribute("status", status);
		return "orderReturn/list";
	}

	@RequestMapping("/orderReturn/v_view.do")
	public String view(Long id, HttpServletRequest request, ModelMap model) {
		WebErrors errors = validateEdit(id, request);
		if (errors.hasErrors()) {
			return errors.showErrorPage(model);
		}
		model.addAttribute("order", orderReturnMng.findById(id).getOrder());
		return "orderReturn/view";
	}

	@RequestMapping("/orderReturn/o_affirm.do")
	public String affirm(Long id, HttpServletRequest request, ModelMap model) {
		WebErrors errors = validateEdit(id, request);
		if (errors.hasErrors()) {
			return errors.showErrorPage(model);
		}
		OrderReturn entity = orderReturnMng.findById(id);
		entity.setStatus(2);
		orderReturnMng.update(entity);
		model.addAttribute("order", orderReturnMng.findById(id).getOrder());
		return "orderReturn/view";
	}

	@RequestMapping("/orderReturn/o_sendback.do")
	public String sendback(Long id, HttpServletRequest request, ModelMap model) {
		WebErrors errors = validateEdit(id, request);
		if (errors.hasErrors()) {
			return errors.showErrorPage(model);
		}
		OrderReturn entity = orderReturnMng.findById(id);
		entity.setStatus(3);
		orderReturnMng.update(entity);
		model.addAttribute("order", orderReturnMng.findById(id).getOrder());
		return "orderReturn/view";
	}

	@RequestMapping("/orderReturn/o_accomplish.do")
	public String accomplish(Long id, HttpServletRequest request, ModelMap model) {
		WebErrors errors = validateEdit(id, request);
		if (errors.hasErrors()) {
			return errors.showErrorPage(model);
		}
		OrderReturn entity = orderReturnMng.findById(id);
		entity.setStatus(7);
		Order order = orderReturnMng.findById(id).getOrder();
		order.setStatus(3);
		order.setFinishedTime(new Date());
		orderReturnMng.update(entity);
		orderMng.updateByUpdater(order);
		model.addAttribute("order", orderReturnMng.findById(id).getOrder());
		return "orderReturn/view";
	}

	@RequestMapping("/orderReturn/o_reimburse.do")
	public String reimburse(Long id, HttpServletRequest request,
			HttpServletResponse response, ModelMap model) {
		Website web = SiteUtils.getWeb(request);
		WebErrors errors = validateEdit(id, request);
		if (errors.hasErrors()) {
			return errors.showErrorPage(model);
		}
		OrderReturn entity = orderReturnMng.findById(id);
		PaymentPlugins paymentPlugins = paymentPluginsMng.findByCode(entity
				.getOrder().getPaymentCode());
		if (paymentPlugins != null) {
			PrintWriter out = null;
			try {
				String aliURL = "";
				if (entity.getOrder().getPaymentCode().equals("zhpay")) {

				} else if (entity.getOrder().getPaymentCode().equals("alipay")
						|| entity.getOrder().getPaymentCode().equals("bankPay")) {
					aliURL = alipayReturn(paymentPlugins, web, entity, request,
							model);
				} else if (entity.getOrder().getPaymentCode().equals("wxPay")) {
					aliURL = wxpayReturn(paymentPlugins, web, entity, request,
							model);
					return aliURL;
				}
				response.setContentType("text/html;charset=UTF-8");
				out = response.getWriter();
				out.print(aliURL);
			} catch (Exception e) {
			} finally {
				if (out != null) {
					out.close();
				}
			}
		} else {
//			ShopMember shopMember = entity.getOrder().getMember();
//			shopMember
//					.setMoney(shopMember.getFreezeScore() + entity.getMoney());
//			shopMemberMng.update(shopMember);
		}
		// entity.setStatus(6);
		// orderReturnMng.update(entity);
		// model.addAttribute("order", orderReturnMng.findById(id).getOrder());
		return null;
	}

	// 支付宝返回参数
	@RequestMapping(value = "/orderReturn/aliReturn.do")
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
		// String out_trade_no = new
		// String(request.getParameter("out_trade_no").getBytes("ISO-8859-1"),"UTF-8");
		// //支付宝交易号
		// String trade_no = new
		// String(request.getParameter("trade_no").getBytes("ISO-8859-1"),"UTF-8");
		// 批次号
		String batch_no = new String(request.getParameter("batch_no").getBytes(
				"ISO-8859-1"), "UTF-8");
		// 批量退款数据中转账成功的笔数
		String tradeNo = "";
		String success_num = new String(request.getParameter("success_num")
				.getBytes("ISO-8859-1"), "UTF-8");
		// 批量退款数据中的详细信息
		String result_details = new String(request.getParameter(
				"result_details").getBytes("ISO-8859-1"), "UTF-8");
		// String result_details = "2015070700001000210059292471^0.01^SUCCESS";
		if (result_details != null && result_details.length() > 0) {
			String[] res = result_details.split("\\^");
			tradeNo = res[0];
		}

		// 付款账户名
		Order order = orderMng.findByTradeNo(tradeNo);
		PaymentPlugins paymentPlugins = paymentPluginsMng.findByCode(order
				.getPaymentCode());
		OrderReturn returnOrder = order.getReturnOrder();
		if (verify(params, paymentPlugins.getPartner(),
				paymentPlugins.getSellerKey())) {
            if(returnOrder.getStatus()!=2){
			// order.setPaymentStatus(2);
			// order.setPaymentCode("alipayPartner");
			returnOrder.setFinishedTime(new Date());
			returnOrder.setStatus(2);
			order.setReturnOrder(returnOrder);
			order.setStatus(3);
			order.setFinishedTime(new Date());
			orderMng.updateByUpdater(order);
            }
			return FrontUtils.showMessage(request, model, "SUCCESS");

		}
		return FrontUtils.showMessage(request, model, "付款异常！");
	}

	private String alipayReturn(PaymentPlugins paymentPlugins, Website web,
			OrderReturn orderReturn, HttpServletRequest request, ModelMap model) {
		// //////////////////////////////////请求参数//////////////////////////////////////
		// 必填参数//
		Date date = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
		SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String date1 = sdf.format(date);
		// 退款批次号。格式为：退款日期（8位当天日期）+流水号（3～24位，不能接受“000”，但是可以接受英文字符）
		// String batch_no = request.getParameter("batch_no");
		String batch_no = date1
				.concat(String.valueOf(orderReturn.getId() * 100));
		// 退款请求时间
		// String refund_date = request.getParameter("refund_date");
		String refund_date = sdf1.format(date);
		// 退款总笔数
		// String batch_num = request.getParameter("batch_num");
		String batch_num = String.valueOf(1);
		// 单笔数据集
		// String detail_data = new
		// String(request.getParameter("detail_data").getBytes("ISO-8859-1"),"gbk");
		String detail_data = orderReturn.getOrder().getTradeNo().toString()
				+ "^" + orderReturn.getMoney() + "^"
				+ orderReturn.getShopDictionary().getName();
		// 格式：第一笔交易#第二笔交易#第三笔交易
		// 第N笔交易格式：交易退款信息
		// 交易退款信息格式：原付款支付宝交易号^退款总金额^退款理由
		// 注意：
		// 1.detail_data中的退款笔数总和要等于参数batch_num的值
		// 2.detail_data的值中不能有“^”、“|”、“#”、“$”等影响detail_data的格式的特殊字符
		// 3.detail_data中退款总金额不能大于交易总金额
		// 4.一笔交易可以多次退款，只需要遵守多次退款的总金额不超过该笔交易付款时金额。
		// 5.不支持退分润功能
		// 选填参数（以下两个参数不能同时为空）
		// 卖家支付宝账号
		// String seller_email = request.getParameter("seller_email");
		String seller_email = paymentPlugins.getSellerEmail();
		// 卖家用户ID
		// String seller_user_id = request.getParameter("seller_user_id");
		String seller_user_id = paymentPlugins.getSellerKey();
		// 服务器页面跳转同步通知页面
		// String
		// notify_url="http://商户网关地址/refund_fastpay_by_platform_pwd-JAVA-UTF-8/notify_url.jsp";
		String notify_url = "http://" + web.getDomain();
		if (web.getGlobal() != null && web.getGlobal().getPort() != null
				&& web.getGlobal().getPort() > 0) {
			notify_url = notify_url + ":" + web.getGlobal().getPort();
		}
		if (web.getGlobal() != null && web.getGlobal().getContextPath() != null
				&& web.getGlobal().getContextPath().length() > 0) {
			notify_url = notify_url + web.getGlobal().getContextPath();
		}
		notify_url = notify_url + "/nhaocang/admin/orderReturn/aliReturn.do";
		System.out.println("notify_url===========:" + notify_url);
		// ////////////////////////////////////////////////////////////////////////////////
		// 把请求参数打包成数组
		// 把请求参数打包成数组
		Map<String, String> sParaTemp = new HashMap<String, String>();
		sParaTemp.put("service", "refund_fastpay_by_platform_pwd");
		sParaTemp.put("partner", paymentPlugins.getPartner());
		sParaTemp.put("_input_charset", "UTF-8");
		sParaTemp.put("notify_url", notify_url);
		sParaTemp.put("seller_email", seller_email);
		sParaTemp.put("refund_date", refund_date);
		sParaTemp.put("batch_no", batch_no);
		sParaTemp.put("batch_num", batch_num);
		sParaTemp.put("key", seller_user_id);
		sParaTemp.put("detail_data", detail_data);
		String strButtonName = "退款";

		// Map<String, String> sParaTemp = new HashMap<String, String>();
		// sParaTemp.put("partner", paymentPlugins.getPartner());
		// sParaTemp.put("seller_email", seller_email);
		// sParaTemp.put("seller_user_id", seller_user_id);
		// sParaTemp.put("batch_no", batch_no);
		// sParaTemp.put("refund_date", refund_date);
		// sParaTemp.put("batch_num", batch_num);
		// sParaTemp.put("detail_data", detail_data);
		// sParaTemp.put("notify_url", notify_url);
		// 构造函数，生成请求URL
		String sHtmlText = null;
		try {
			sHtmlText = buildForm(sParaTemp, ALIPAY_GATEWAY_NEW, "get",
					strButtonName);
		} catch (Exception e) {
			e.printStackTrace();
		}
		// out.println(sHtmlText);
		return sHtmlText;
	}

	/**
	 * 微信退款
	 * 
	 * @param paymentPlugins
	 * @param web
	 * @param orderReturn
	 * @param request
	 * @param model
	 * @return
	 */
	private String wxpayReturn(PaymentPlugins paymentPlugins, Website web,
			OrderReturn orderReturn, HttpServletRequest request, ModelMap model) {
		// //////////////////////////////////请求参数//////////////////////////////////////
		// 1 参数
		String appid = paymentPlugins.getSellerKey();
		String appsecret = paymentPlugins.getSellerEmail();
		String partner = paymentPlugins.getPartner();
		// 这个参数partnerkey是在商户后台配置的一个32位的key,微信商户平台-账户设置-安全设置-api安全
		String partnerkey = paymentPlugins.getPartnerKey();
		// 必填参数//
		String orderId = orderReturn.getOrder().getCode() + "";
		// 附加数据 原样返回
		String attach = "";
		// 总金额以分为单位，不带小数点
		String totalFee = getMoney(orderReturn.getMoney() + "");

		// 商户号
		String mch_id = paymentPlugins.getPartner();
		// 随机字符串
		String nonce_str = getNonceStr();

		// 商户订单号
		String out_trade_no = orderId;

		SortedMap<String, String> packageParams = new TreeMap<String, String>();
		packageParams.put("appid", appid);
		packageParams.put("mch_id", mch_id);
		packageParams.put("nonce_str", nonce_str);
		packageParams.put("out_refund_no", nonce_str);
		packageParams.put("out_trade_no", out_trade_no);
		packageParams.put("op_user_id", partner);
		packageParams.put("total_fee", totalFee);
		packageParams.put("refund_fee", totalFee);

		RequestHandler reqHandler = new RequestHandler(null, null);
		reqHandler.init(appid, appsecret, partnerkey);

		String sign = reqHandler.createSign(packageParams);
		String xml = "<xml>" + "<appid>" + appid + "</appid>" + "<mch_id>"
				+ mch_id + "</mch_id>" + "<nonce_str>" + nonce_str
				+ "</nonce_str>" + "<sign>" + sign + "</sign>"
				+ "<out_trade_no>" + out_trade_no + "</out_trade_no>"
				+ "<out_refund_no>" + nonce_str + "</out_refund_no>"
				+ "<op_user_id>" + partner + "</op_user_id>" + "<refund_fee>"
				+ totalFee + "</refund_fee>" + "<total_fee>" + totalFee
				+ "</total_fee>" + "</xml>";
		String code_url = "";
		String createOrderURL = "https://api.mch.weixin.qq.com/secapi/pay/refund";
		String certpath=request.getRealPath("/")+"cert/cert.p12";
		Map map = new GetWxOrderno().getTkRes(mch_id,certpath,createOrderURL, xml);
		
		if("SUCCESS".equals(map.get("result_code"))){
			Order order = orderReturn.getOrder();
			orderReturn.setFinishedTime(new Date());
			orderReturn.setStatus(2);
			order.setReturnOrder(orderReturn);
			order.setStatus(3);
			order.setFinishedTime(new Date());
			orderMng.updateByUpdater(order);
			//会员冻结的积分
			ShopMember member = order.getMember();
			member.setFreezeScore(member.getFreezeScore()-order.getScore());
			shopMemberMng.update(member);
			List<ShopScore> list = shopScoreMng.getlist(Long.toString(order.getCode()));
			for(ShopScore s:list){
				shopScoreMng.deleteById(s.getId());
			}
			
			return "redirect:v_list.do";
		}else{
			model.put("error", map.get("err_code_des"));
		}
		return "orderReturn/error";
	}

	/**
	 * 支付宝提供给商户的服务接入网关URL(新)
	 */
	private static final String ALIPAY_GATEWAY_NEW = "https://mapi.alipay.com/gateway.do?";
	/**
	 * 支付宝消息验证地址
	 */
	private static final String HTTPS_VERIFY_URL = "https://mapi.alipay.com/gateway.do?service=notify_verify&";

	/**
	 * 构造即时到账批量退款有密接口
	 * 
	 * @param sParaTemp
	 *            请求参数集合
	 * @return 支付宝返回表单提交HTML信息
	 * @throws Exception
	 */
	public static String refund_fastpay_by_platform_pwd(
			Map<String, String> sParaTemp) throws Exception {
		// 增加基本配置
		sParaTemp.put("service", "refund_fastpay_by_platform_pwd");
		sParaTemp.put("_input_charset", "UTF-8");
		String strButtonName = "退款";
		return buildForm(sParaTemp, ALIPAY_GATEWAY_NEW, "get", strButtonName);
	}

	/**
	 * 构造提交表单HTML数据
	 * 
	 * @param sParaTemp
	 *            请求参数数组
	 * @param gateway
	 *            网关地址
	 * @param strMethod
	 *            提交方式。两个值可选：post、get
	 * @param strButtonName
	 *            确认按钮显示文字
	 * @return 提交表单HTML文本
	 */
	public static String buildForm(Map<String, String> sParaTemp,
			String gateway, String strMethod, String strButtonName) {
		// 待请求参数数组
		Map<String, String> sPara = buildRequestPara(sParaTemp);
		List<String> keys = new ArrayList<String>(sPara.keySet());

		StringBuffer sbHtml = new StringBuffer();

		sbHtml.append("<form id=\"alipaysubmit\" name=\"alipaysubmit\" action=\""
				+ gateway
				+ "_input_charset="
				+ "UTF-8"
				+ "\" method=\""
				+ strMethod + "\">");

		for (int i = 0; i < keys.size(); i++) {
			String name = (String) keys.get(i);
			String value = (String) sPara.get(name);

			sbHtml.append("<input type=\"hidden\" name=\"" + name
					+ "\" value=\"" + value + "\"/>");
		}

		// submit按钮控件请不要含有name属性
		sbHtml.append("<input type=\"submit\" value=\"" + strButtonName
				+ "\" style=\"display:none;\"></form>");
		sbHtml.append("<script>document.forms['alipaysubmit'].submit();</script>");
		// System.out.println(sbHtml.toString());
		return sbHtml.toString();
	}

	/**
	 * 生成要请求给支付宝的参数数组
	 * 
	 * @param sParaTemp
	 *            请求前的参数数组
	 * @return 要请求的参数数组
	 */
	private static Map<String, String> buildRequestPara(
			Map<String, String> sParaTemp) {
		// 除去数组中的空值和签名参数
		Map<String, String> sPara = paraFilter(sParaTemp);
		// 生成签名结果
		String mysign = buildMysign(sPara);

		// 签名结果与签名方式加入请求提交参数组中
		sPara.put("sign", mysign);
		sPara.put("sign_type", "MD5");

		return sPara;
	}

	public static String buildMysign(Map<String, String> sArray) {
		String prestr = createLinkString(sArray); // 把数组所有元素，按照“参数=参数值”的模式用“&”字符拼接成字符串
		prestr = prestr + sArray.get("key"); // 把拼接后的字符串再与安全校验码直接连接起来
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
			throw new RuntimeException("MD5签名过程中出现错误,指定的编码集不对,您目前指定的编码集是:"
					+ charset);
		}
	}

	/**
	 * 验证消息是否是支付宝发出的合法消息
	 * 
	 * @param params
	 *            通知返回来的参数数组
	 * @return 验证结果
	 */
	public static boolean verify(Map<String, String> params, String partner,
			String key) {
		// 判断responsetTxt是否为true，isSign是否为true
		// responsetTxt的结果不是true，与服务器设置问题、合作身份者ID、notify_id一分钟失效有关
		// isSign不是true，与安全校验码、请求时的参数格式（如：带自定义参数等）、编码格式有关
		String responseTxt = "true";
		if (params.get("notify_id") != null) {
			String notify_id = params.get("notify_id");
			responseTxt = verifyResponse(notify_id, partner);
		}
		String sign = "";
		if (params.get("sign") != null) {
			sign = params.get("sign");
		}
		boolean isSign = getSignVeryfy(params, sign, key);
		// 写日志记录（若要调试，请取消下面两行注释）
		// String sWord = "responseTxt=" + responseTxt + "\n isSign=" + isSign +
		// "\n 返回回来的参数：" + AlipayCore.createLinkString(params);
		// AlipayCore.logResult(sWord);
		if (isSign && responseTxt.equals("true")) {
			return true;
		} else {
			return false;
		}
	}

	/**
	 * 根据反馈回来的信息，生成签名结果
	 * 
	 * @param Params
	 *            通知返回来的参数数组
	 * @param sign
	 *            比对的签名结果
	 * @return 生成的签名结果
	 */
	private static boolean getSignVeryfy(Map<String, String> Params,
			String sign, String key) {
		// 过滤空值、sign与sign_type参数
		Map<String, String> sParaNew = paraFilter(Params);
		// 获取待签名字符串
		String preSignStr = createLinkString(sParaNew);
		// 获得签名验证结果
		boolean isSign = false;
		isSign = verify(preSignStr, sign, key, "utf-8");
		return isSign;
	}

	/**
	 * 签名字符串
	 * 
	 * @param text
	 *            需要签名的字符串
	 * @param sign
	 *            签名结果
	 * @param key
	 *            密钥
	 * @param input_charset
	 *            编码格式
	 * @return 签名结果
	 */
	public static boolean verify(String text, String sign, String key,
			String input_charset) {
		text = text + key;
		String mysign = DigestUtils
				.md5Hex(getContentBytes(text, input_charset));
		if (mysign.equals(sign)) {
			return true;
		} else {
			return false;
		}
	}

	/**
	 * 获取远程服务器ATN结果,验证返回URL
	 * 
	 * @param notify_id
	 *            通知校验ID
	 * @return 服务器ATN结果 验证结果集： invalid命令参数不对 出现这个错误，请检测返回处理中partner和key是否为空 true
	 *         返回正确信息 false 请检查防火墙或者是服务器阻止端口问题以及验证时间是否超过一分钟
	 */
	private static String verifyResponse(String notify_id, String partner) {
		// 获取远程服务器ATN结果，验证是否是支付宝服务器发来的请求
		String veryfy_url = HTTPS_VERIFY_URL + "partner=" + partner
				+ "&notify_id=" + notify_id;
		return checkUrl(veryfy_url);
	}

	/**
	 * 获取远程服务器ATN结果
	 * 
	 * @param urlvalue
	 *            指定URL路径地址
	 * @return 服务器ATN结果 验证结果集： invalid命令参数不对 出现这个错误，请检测返回处理中partner和key是否为空 true
	 *         返回正确信息 false 请检查防火墙或者是服务器阻止端口问题以及验证时间是否超过一分钟
	 */
	private static String checkUrl(String urlvalue) {
		String inputLine = "";
		try {
			URL url = new URL(urlvalue);
			HttpURLConnection urlConnection = (HttpURLConnection) url
					.openConnection();
			BufferedReader in = new BufferedReader(new InputStreamReader(
					urlConnection.getInputStream()));
			inputLine = in.readLine().toString();
		} catch (Exception e) {
			e.printStackTrace();
			inputLine = "";
		}
		return inputLine;
	}

	/**
	 * 把数组所有元素排序，并按照“参数=参数值”的模式用“&”字符拼接成字符串
	 * 
	 * @param params
	 *            需要排序并参与字符拼接的参数组
	 * @return 拼接后字符串
	 */
	public static String createLinkString(Map<String, String> params) {

		List<String> keys = new ArrayList<String>(params.keySet());
		Collections.sort(keys);

		String prestr = "";

		for (int i = 0; i < keys.size(); i++) {
			String key = keys.get(i);
			String value = params.get(key);

			if (i == keys.size() - 1) {// 拼接时，不包括最后一个&字符
				prestr = prestr + key + "=" + value;
			} else {
				prestr = prestr + key + "=" + value + "&";
			}
		}

		return prestr;
	}

	/**
	 * 除去数组中的空值和签名参数
	 * 
	 * @param sArray
	 *            签名参数组
	 * @return 去掉空值与签名参数后的新签名参数组
	 */
	public static Map<String, String> paraFilter(Map<String, String> sArray) {

		Map<String, String> result = new HashMap<String, String>();

		if (sArray == null || sArray.size() <= 0) {
			return result;
		}

		for (String key : sArray.keySet()) {
			String value = sArray.get(key);
			if (value == null || value.equals("")
					|| key.equalsIgnoreCase("sign")
					|| key.equalsIgnoreCase("sign_type")) {
				continue;
			}
			result.put(key, value);
		}

		return result;
	}

	@RequestMapping("/orderReturn/o_salesreturn.do")
	public String salesreturn(Long id, HttpServletRequest request,
			ModelMap model) {
		WebErrors errors = validateEdit(id, request);
		if (errors.hasErrors()) {
			return errors.showErrorPage(model);
		}
		OrderReturn entity = orderReturnMng.findById(id);
		entity.setStatus(5);

		// 处理库存
		// for(OrderItem item:entity.getOrder().getItems()){
		// Product product=item.getProduct();
		// if(item.getProductFash()!=null){
		// ProductFashion fashion=item.getProductFash();
		// fashion.setStockCount(fashion.getStockCount()+item.getCount());
		// product.setStockCount(product.getStockCount()+item.getCount());
		// product.setSaleCount(product.getSaleCount()-item.getCount());
		// product.setLiRun(product.getLiRun()-item.getCount()*(fashion.getSalePrice()-fashion.getCostPrice()));
		// productFashionMng.update(fashion);
		// }else{
		// product.setLiRun(product.getLiRun()-item.getCount()*(product.getSalePrice()-product.getCostPrice()));
		// product.setSaleCount(product.getSaleCount()-item.getCount());
		// product.setStockCount(product.getStockCount()+item.getCount());
		// }
		// productMng.updateByUpdater(product);
		// }
		// 会员冻结的积分
		ShopMember member = entity.getOrder().getMember();
		member.setFreezeScore(member.getScore() - entity.getOrder().getScore());
		shopMemberMng.update(member);
		List<ShopScore> list = shopScoreMng.getlist(Long.toString(entity
				.getOrder().getCode()));
		for (ShopScore s : list) {
			shopScoreMng.deleteById(s.getId());
		}

		orderReturnMng.update(entity);
		model.addAttribute("order", orderReturnMng.findById(id).getOrder());
		return "orderReturn/view";
	}

	@RequestMapping("/orderReturn/o_delete.do")
	public String delete(Long[] ids, Integer pageNo,
			HttpServletRequest request, ModelMap model) {
		WebErrors errors = validateDelete(ids, request);
		if (errors.hasErrors()) {
			return errors.showErrorPage(model);
		}
		OrderReturn[] beans = orderReturnMng.deleteByIds(ids);
		for (OrderReturn bean : beans) {
			log.info("delete OrderReturn id={}", bean.getId());
		}
		return list(null, pageNo, request, model);
	}

	private WebErrors validateEdit(Long id, HttpServletRequest request) {
		WebErrors errors = WebErrors.create(request);
		Website web = SiteUtils.getWeb(request);
		if (vldExist(id, web.getId(), errors)) {
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
		OrderReturn entity = orderReturnMng.findById(id);
		if (errors.ifNotExist(entity, OrderReturn.class, id)) {
			return true;
		}
		return false;
	}

	@Autowired
	private OrderReturnMng orderReturnMng;
	@Autowired
	private ProductMng productMng;
	@Autowired
	private ProductFashionMng productFashionMng;
	@Autowired
	private ShopMemberMng shopMemberMng;
	@Autowired
	private ShopScoreMng shopScoreMng;
	@Autowired
	private PaymentPluginsMng paymentPluginsMng;
	@Autowired
	private OrderMng orderMng;

}