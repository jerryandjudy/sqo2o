package com.jspgou.cms.action.admin.main;

import static com.jspgou.common.page.SimplePage.cpn;

import java.io.BufferedOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.SortedMap;
import java.util.TreeMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import com.jspgou.cms.Alipay;
import com.jspgou.cms.entity.Accountcz;
import com.jspgou.cms.entity.Bld;
import com.jspgou.cms.entity.Dls;
import com.jspgou.cms.entity.Fws;
import com.jspgou.cms.entity.Gys;
import com.jspgou.cms.entity.KetaUser;
import com.jspgou.cms.entity.Order;
import com.jspgou.cms.entity.Organization;
import com.jspgou.cms.entity.PaymentPlugins;
import com.jspgou.cms.manager.AccountczMng;
import com.jspgou.cms.manager.BldMng;
import com.jspgou.cms.manager.CmsLogMng;
import com.jspgou.cms.manager.DlsMng;
import com.jspgou.cms.manager.FwsMng;
import com.jspgou.cms.manager.GysMng;
import com.jspgou.cms.manager.JmfMng;
import com.jspgou.cms.manager.KetaUserMng;
import com.jspgou.cms.manager.OrganizationMng;
import com.jspgou.cms.manager.PaymentPluginsMng;
import com.jspgou.cms.manager.ShopDictionaryMng;
import com.jspgou.cms.web.FrontUtils;
import com.jspgou.cms.web.ShopFrontHelper;
import com.jspgou.common.hibernate3.Updater;
import com.jspgou.common.page.Pagination;
import com.jspgou.common.web.CookieUtils;
import com.jspgou.common.web.RequestUtils;
import com.jspgou.core.entity.Account;
import com.jspgou.core.entity.AccountItem;
import com.jspgou.core.entity.User;
import com.jspgou.core.entity.Website;
import com.jspgou.core.manager.AccountItemMng;
import com.jspgou.core.manager.AccountMng;
import com.jspgou.core.manager.WebsiteMng;
import com.jspgou.core.web.SiteUtils;
import com.jspgou.core.web.WebErrors;
import com.jspgou.core.web.front.FrontHelper;
import com.tencent.demo.WxPayResult;
import com.tencent.utils.GetWxOrderno;
import com.tencent.utils.RequestHandler;
import com.tencent.utils.WxPayCodeRes;

/**
* This class should preserve.
* @preserve
*/
@Controller
public class AccountczAct extends Alipay{
	private static final Logger log = LoggerFactory.getLogger(AccountczAct.class);

	@RequestMapping("/accountcz/v_list.do")
	public String list(String payStatus,String username,String siteId,Long organizationId,String createTime,Integer pageNo, HttpServletRequest request,ModelMap model) {
		
	   Website website=SiteUtils.getWeb(request);
	   Pagination pagination = accountczMng.getPage(payStatus, username, siteId, organizationId, createTime, cpn(pageNo), CookieUtils.getPageSize(request));
	   List siteList=websiteMng.getAllList();
		model.addAttribute("pagination", pagination);
		model.addAttribute("username", username);
		model.addAttribute("organizationId", organizationId);
		model.addAttribute("siteId", siteId);
		model.addAttribute("payStatus", payStatus);
		model.addAttribute("createTime", createTime);
		model.addAttribute("siteList", siteList);
		return "accountcz/list";
	}
	
	
	
	
	@RequestMapping("/accountcz/userlist.do")
	public String userlist(Long organizationId,Integer pageNo,Long siteId, HttpServletRequest request,ModelMap model) {
		
		Website website=SiteUtils.getWeb(request);
		Pagination pagination = null;
		if(organizationId!=null && organizationId>0){
				pagination = accountczMng.getPageByOrganization(siteId,organizationId, cpn(pageNo), CookieUtils.getPageSize(request));
		}
	model.addAttribute("pagination", pagination);
	model.addAttribute("organizationId", organizationId);
	return "accountcz/userlist";
}
	
	@RequestMapping("/accountcz/v_list_operating.do")
	public String listOperating(String queryUsername, String queryName,
			 Integer pageNo, String jmftypeIdstr, HttpServletRequest request,
			ModelMap model) {
		Website site = SiteUtils.getWeb(request);
		Pagination pagination = accountczMng.getPage( 
				queryUsername, queryName,  cpn(pageNo), jmftypeIdstr, CookieUtils
						.getPageSize(request));
		model.addAttribute("pagination", pagination);
		model.addAttribute("pageNo", pagination.getPageNo());
		model.addAttribute("queryUsername", queryUsername);
		model.addAttribute("queryName", queryName);
		model.addAttribute("jmftypeIdstr", jmftypeIdstr);
		return "accountcz/userlist";
	}
	@RequestMapping("/accountcz/v_add.do")
	public String add(Long id,HttpServletRequest request, ModelMap model) {
		
		Organization organization = organizationMng.findById(id);
		WebErrors errors = WebErrors.create(request);
		if(organization==null){
			errors.addError("无该组织类型");
			return errors.showErrorPage(model);
		}
		model.put("organization", organization);
		return "accountcz/add";
	}
	/**
	 * 选择支付方式
	 * @param id
	 * @param request
	 * @param model
	 * @return
	 */
	@RequestMapping("/accountcz/accountczpay.do")
	public String jmfpay(Long id, HttpServletRequest request, ModelMap model) {

		WebErrors errors = validateEdit(id, request);
		if (errors.hasErrors()) {
			return errors.showErrorPage(model);
		}
		List<PaymentPlugins> paymentPluginsList = paymentPluginsMng.getList();
		Accountcz accountcz = accountczMng.findById(id);
		model.addAttribute("accountcz", accountcz);
		model.addAttribute("paymentPluginsList", paymentPluginsList);
		return "accountcz/accountczpay";
	}

	
	// 在线支付订单
		@RequestMapping("/accountcz/accountczpays.do")
		public String pay(Long accountczId, String code, HttpServletRequest request,
				HttpServletResponse response, ModelMap model) {
			Website web = SiteUtils.getWeb(request);
			WebErrors errors = validateEdit(accountczId, request);
			if (errors.hasErrors()) {
				return errors.showErrorPage(model);
			}
			Accountcz accountcz = accountczMng.findById(accountczId);
			if (accountcz != null && accountcz != null) {
				PaymentPlugins paymentPlugins = paymentPluginsMng.findByCode(code);
				PrintWriter out = null;
				String aliURL = null;
				try {
					if (!StringUtils.isBlank(code) && code.equals("alipayPartner")) {

						aliURL = alipay(paymentPlugins, web, accountcz, request,
								model);
					} else if (!StringUtils.isBlank(code) && code.equals("alipay")) {
						aliURL = alipayapi(paymentPlugins, web, accountcz,
								request, model);
					}else if (!StringUtils.isBlank(code) && code.equals("bankPay")) {
						aliURL = this.alipaywy(paymentPlugins,  web, accountcz,code,
								request, model);
					} else if (!StringUtils.isBlank(code) && code.equals("wxPay")) {
						aliURL = wxpay(paymentPlugins, web,accountcz, request, model);
					}
					accountcz.setPayType(code);
					accountczMng.updateByUpdater(accountcz);
					ShopFrontHelper.setCommonData(request, model, web, 1);
					response.setContentType("text/html;charset=UTF-8");
					out = response.getWriter();
					out.print(aliURL);
				} catch (IOException e) {
					e.printStackTrace();
				} finally {
					out.close();
				}
				return null;
			} else {
				return FrontHelper.pageNotFound(web, model, request);
			}
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
				Accountcz accountcz, HttpServletRequest request, ModelMap model) {
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
			notify_url = notify_url + "/nhaocang/admin/accountcz/wxReturn.do";
			
			
			// 付款金额
			String price = String.valueOf(accountcz.getRealValue());// 必填
			// 商户订单号
			String out_trade_no = accountcz.getId() + "";// 商户网站订单系统中唯一订单号，必填
			// 订单名称
			String subject = "收取" + accountcz.getUserRealname() + "充值费";// 必填
			if (subject.length() > 128) {
				subject = subject.substring(0, 123) + "...";
			}
			// 订单描述
			String body = subject;
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
					
					// 附加数据 原样返回
					String attach = "";
					// 总金额以分为单位，不带小数点
					String totalFee = getMoney(price);
					
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
							+ "<script src=\""+show_url+"/r/gou/www/assets/js/qrcode.js\"></script></head><body><div align=\"center\" id=\"qrcode\"></div>buttonset</body><script>createscript</script></html>";
					String buttons="";
					WxPayCodeRes wxPayCodeRes = new GetWxOrderno().getCodeUrl(createOrderURL, xml);
					if(wxPayCodeRes.getCode().indexOf("FAIL")!=-1){
						String scrs="var dom=document.createElement('DIV');dom.innerHTML = '"+wxPayCodeRes.getMsg()+"';var element=document.getElementById('qrcode');element.appendChild(dom);";
						reshtml=reshtml.replace("createscript",scrs);
						reshtml=reshtml.replace("buttonset", "<div align='center'>二维码生成错误</div>");
					}else{
						String scrs="var url = '"+wxPayCodeRes.getMsg()+"';var qr = qrcode(10, 'M');qr.addData(url);qr.make();var dom=document.createElement('DIV');dom.innerHTML = qr.createImgTag();var element=document.getElementById('qrcode');element.appendChild(dom);";
						reshtml=reshtml.replace("createscript", scrs);
						reshtml=reshtml.replace("buttonset", "<div align='center'>扫描支付</div>");
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
		private String alipaywy(PaymentPlugins paymentPlugins,
				Website web, Accountcz accountcz,String paymentCode, HttpServletRequest request, ModelMap model) {
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
			
			// 付款金额
			String price = String.valueOf(accountcz.getRealValue());// 必填
			// 商户订单号
			String out_trade_no = accountcz.getId() + "";// 商户网站订单系统中唯一订单号，必填
			// 订单名称
			String subject = "收取" + accountcz.getUserRealname() + "充值费";// 必填
			if (subject.length() > 128) {
				subject = subject.substring(0, 123) + "...";
			}
			// 订单描述
			String body = subject;
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
			sParaTemp.put("total_fee", price);
			sParaTemp.put("body", body);
			sParaTemp.put("paymethod", paymentCode);
			sParaTemp.put("defaultbank", "ICBCB2C");
			sParaTemp.put("show_url", show_url);
			sParaTemp.put("anti_phishing_key", anti_phishing_key);
			sParaTemp.put("exter_invoke_ip", exter_invoke_ip);

			// 建立请求
			String sHtmlText = buildRequest(sParaTemp,
					paymentPlugins.getSellerKey(), "get", "确认");
			return sHtmlText;
		}
		private String alipayapi(PaymentPlugins paymentPlugins, Website web,
				Accountcz accountcz, HttpServletRequest request, ModelMap model) {
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
			notify_url = notify_url + "/nhaocang/admin/accountcz/aliReturn.do";
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
//			return_url = return_url + "/aliReturnUrl.jspx";
			// 卖家支付宝帐户
			String seller_email = paymentPlugins.getSellerEmail();// 必填
			// 付款金额
			String price = String.valueOf(accountcz.getRealValue());// 必填
			// 商户订单号
			String out_trade_no = accountcz.getId() + "";// 商户网站订单系统中唯一订单号，必填
			// 订单名称
			String subject = "收取" + accountcz.getUserRealname() + "充值费";// 必填
			if (subject.length() > 128) {
				subject = subject.substring(0, 123) + "...";
			}
			// 商品数量
			String quantity = "1";// 必填，建议默认为1，不改变值，把一次交易看成是一次下订单而非购买一件商品
			// 物流支付方式
			String logistics_payment = "BUYER_PAY";// 必填，两个值可选：SELLER_PAY（卖家承担运费）、BUYER_PAY（买家承担运费）
			// 订单描述
			String body = "收取" + accountcz.getUserRealname() + "充值费";// 必填
			// 商品展示地址
			String show_url = "http://" + web.getDomain() + "/";
			// 防钓鱼时间戳
			String anti_phishing_key = "";
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
			sParaTemp.put("total_fee", price);

			sParaTemp.put("body", body);
			sParaTemp.put("show_url", show_url);
			// sParaTemp.put("anti_phishing_key", anti_phishing_key);
			sParaTemp.put("exter_invoke_ip", exter_invoke_ip);
			// 建立请求
			String sHtmlText = buildRequest(sParaTemp,
					paymentPlugins.getSellerKey(), "get", "确认");
			return sHtmlText;
		}

		// 支付宝返回参数
		@RequestMapping(value = "/accountcz/aliReturn.do")
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
			
			System.out.println("/accountcz/aliReturn.do");
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
			Accountcz accountcz = accountczMng.findById(code);
			PaymentPlugins paymentPlugins = paymentPluginsMng.findByCode(accountcz
					.getPayType());
			if (verify(params, paymentPlugins.getPartner(),
					paymentPlugins.getSellerKey())) {
//				System.out.println("id===========:" + accountcz.getId());
				if (trade_status.equals("WAIT_BUYER_PAY")) {
					// 该判断表示买家已在支付宝交易管理中产生了交易记录，但没有付款
					return FrontUtils.showMessage(request, model, "付款失败！");
				} else if (trade_status.equals("WAIT_SELLER_SEND_GOODS")) {
					// 该判断表示买家已在支付宝交易管理中产生了交易记录且付款成功，但卖家没有发货
					Double totalfee=Double.parseDouble(total_fee);
					if(totalfee==accountcz.getRealValue()){
					accountcz.setAddTime(new Date());
					accountcz.setPayStatus("2");
					accountczMng.updateByUpdater(accountcz);
					}
					return FrontUtils.showMessage(request, model, "付款成功，请等待发货！");
				} else if (trade_status.equals("WAIT_BUYER_CONFIRM_GOODS")) {
					// 该判断表示卖家已经发了货，但买家还没有做确认收货的操作
					return FrontUtils.showMessage(request, model, "已发货，未确认收货！");
				} else if (trade_status.equals("TRADE_FINISHED")) {
					// 该判断表示买家已经确认收货，这笔交易完成
					return FrontUtils.showMessage(request, model, "交易完成！");
				} else {
					Double totalfee=Double.parseDouble(total_fee);
					if(totalfee==accountcz.getRealValue()){
					accountcz.setAddTime(new Date());
					accountcz.setPayStatus("2");
					accountczMng.updateByUpdater(accountcz);
					}
					return FrontUtils.showMessage(request, model, "success！");
				}
			}
			return FrontUtils.showMessage(request, model, "付款异常！");
		}
		// 微信返回参数
		@RequestMapping(value = "/accountcz/wxReturn.do")
		public void wxReturn(HttpServletRequest request,
				HttpServletResponse response, ModelMap model) {
			//把如下代码贴到的你的处理回调的servlet 或者.do 中即可明白回调操作
					System.out.print("微信支付回调数据开始");
					//示例报文
//					String xml = "<xml><appid><![CDATA[wxb4dc385f953b356e]]></appid><bank_type><![CDATA[CCB_CREDIT]]></bank_type><cash_fee><![CDATA[1]]></cash_fee><fee_type><![CDATA[CNY]]></fee_type><is_subscribe><![CDATA[Y]]></is_subscribe><mch_id><![CDATA[1228442802]]></mch_id><nonce_str><![CDATA[1002477130]]></nonce_str><openid><![CDATA[o-HREuJzRr3moMvv990VdfnQ8x4k]]></openid><out_trade_no><![CDATA[1000000000051249]]></out_trade_no><result_code><![CDATA[SUCCESS]]></result_code><return_code><![CDATA[SUCCESS]]></return_code><sign><![CDATA[1269E03E43F2B8C388A414EDAE185CEE]]></sign><time_end><![CDATA[20150324100405]]></time_end><total_fee>1</total_fee><trade_type><![CDATA[JSAPI]]></trade_type><transaction_id><![CDATA[1009530574201503240036299496]]></transaction_id></xml>";
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

					System.out.println("接收到的报文：" + notityXml);
					
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
					Accountcz accountcz = accountczMng.findById(code);
					PaymentPlugins paymentPlugins = paymentPluginsMng.findByCode(accountcz
							.getPayType());
					if("SUCCESS".equals(wpr.getResultCode())){
						//支付成功
						Double total_fee=Double.parseDouble(m.get("total_fee").toString());
						if(total_fee==accountcz.getRealValue()){
						resXml = "<xml>" + "<return_code><![CDATA[SUCCESS]]></return_code>"
						+ "<return_msg><![CDATA[OK]]></return_msg>" + "</xml> ";
						accountcz.setAddTime(new Date());
						accountcz.setPayStatus("2");
						accountczMng.updateByUpdater(accountcz);
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
						out.write(resXml.getBytes());
						out.flush();
						out.close();
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}

		}

		private String alipay(PaymentPlugins paymentPlugins, Website web,
				Accountcz accountcz, HttpServletRequest request, ModelMap model) {
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
			notify_url = notify_url + "/nhaocang/admin/accountcz/aliReturn.do";
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
			String out_trade_no = accountcz.getId() + "";// 商户网站订单系统中唯一订单号，必填
			// 订单名称
			String subject = "收取" + accountcz.getUserRealname() + "充值费";// 必填
			if (subject.length() > 128) {
				subject = subject.substring(0, 123) + "...";
			}
			// 付款金额
			String price = String.valueOf(accountcz.getRealValue());// 必填
			// 商品数量
			String quantity = "1";// 必填，建议默认为1，不改变值，把一次交易看成是一次下订单而非购买一件商品
			// 物流支付方式
			String logistics_payment = "BUYER_PAY";// 必填，两个值可选：SELLER_PAY（卖家承担运费）、BUYER_PAY（买家承担运费）
			// 订单描述
			String body = "收取" + accountcz.getUserRealname() + "充值费";// 必填
			// 商品展示地址
			String show_url = "http://" + web.getDomain() + "/";
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
			sParaTemp.put("logistics_payment", logistics_payment);
			sParaTemp.put("body", body);
			sParaTemp.put("show_url", show_url);
			// 建立请求
			String sHtmlText = buildRequest(sParaTemp,
					paymentPlugins.getSellerKey(), "get", "确认");
			return sHtmlText;
		}
	
	@RequestMapping("/accountcz/o_edit.do")
	public String edit(Long id, HttpServletRequest request, ModelMap model) {
		WebErrors errors = validateEdit(id, request);
		if (errors.hasErrors()) {
			return errors.showErrorPage(model);
		}
		Accountcz acountcz = accountczMng.findById(id);
		KetaUser ketaUser=acountcz.getKetaUser();
		Organization organization = ketaUser.getOrganization();
		if(organization!=null){
			Long organizationId=organization.getId();
			Account account=null;
			if(organizationId==15){//供应商
				Gys gys=gysMng.findByKetaUserId(ketaUser.getId());
				account=gys.getAccount();
			}else if(organizationId==13){//代理商
				Dls dls = dlsMng.findByKetaUserId(ketaUser.getId().toString());
				account=dls.getWebsite().getAccount();
			}else if(organizationId==20){//代理商
				Bld bld=bldMng.findByKetaUserId(ketaUser.getId());
				account=bld.getAccount();
			}
			account.setMoney(account.getMoney()+acountcz.getRealValue());
			Updater updater=new Updater(account);
			accountMng.updateByUpdater(updater);
			AccountItem accountItem=new AccountItem();
			accountItem.setAccount(account);
			accountItem.setMoney(acountcz.getRealValue());
			accountItem.setMoneyTime(new Date());
			accountItem.setMoneyType(2);
			accountItem.setName("平台充值");
			accountItem.setStatus(true);
			accountItem.setUseStatus(false);
			accountItemMng.save(accountItem);
		}
		acountcz.setPayStatus("2");
		accountczMng.updateByUpdater(acountcz);
		model.addAttribute("Accountcz", acountcz);
		return "redirect:v_list.do";
	}
	
	private WebErrors validateEdit(Long id, HttpServletRequest request) {
		WebErrors errors = WebErrors.create(request);
		errors.ifNull(id, "id");
		vldExist(id, errors);
		return errors;
	}
	
	private boolean vldExist(Long id, WebErrors errors) {
		Accountcz entity = accountczMng.findById(id);
		if(entity!=null&&entity.getPayStatus()!=null&&"2".equals(entity.getPayStatus())){
			errors.addError("该记录已经充值");
			return true;
		}
		return errors.ifNotExist(entity, Accountcz.class, id);
	}

	
	@RequestMapping("/accountcz/o_save.do")
	public String save(Accountcz bean,Long websiteId,  String ketauserId, Date validFrom, Date validUntil, String ketauserUsername,
			HttpServletRequest request, ModelMap model) {
		String ids = ketauserId;
		/*Organization organization=organizationMng.findByName("代理商");
		WebErrors errors = validateSave(organization, request);
		if (errors.hasErrors()) {
			return errors.showErrorPage(model);
		}*/
		KetaUser ketaUser=null;
		if(ketauserId!=null){
			ketaUser = ketaUserMng.findById(Long.parseLong(ketauserId));
			
		}
		Organization organization = ketaUser.getOrganization();
		if(organization!=null){
			Long organizationId=organization.getId();
			Website website=null;
			if(organizationId==15){//供应商
				Gys gys=gysMng.findByKetaUserId(ketaUser.getId());
				website=gys.getWebsite();
			}else if(organizationId==13){//代理商
				Dls dls = dlsMng.findByKetaUserId(ketauserId);
				website=dls.getWebsite();
			}else if(organizationId==20){//代理商
				Bld bld=bldMng.findByKetaUserId(ketaUser.getId());
				website=bld.getWebsite();
			}else if(organizationId==25){//代理商
				Fws fws=fwsMng.findByKetaUserId(ketaUser.getId());
				website=fws.getWebsite();
			}
			bean.setWebsite(website);
		}
		bean.setKetaUser(ketaUser);
		bean.setOrganizationType(ketaUser.getOrganization().getName());
		
		
		
		
		String content="";
		
		//ketaUser.setOrganization(organization);
		User user = SiteUtils.getUser(request);
		
		bean.setAddPeople(user.getUsername());
		bean.setPayStatus("1");
		bean.setAddTime(new Date());
		bean = accountczMng.save(bean);
		content=content+"id:"+bean.getId()+";新增账户充值记录用户："+bean.getKetaUser().getRealname()+";";
		cmsLogMng.operating(request, "Accountcz.log.save", content);
		return "redirect:v_list.do";
	}
	
	
	
	private WebErrors validateUpdate(Accountcz bean, HttpServletRequest request) {
		WebErrors errors = WebErrors.create(request);
		Long id = bean.getId();
		
		errors.ifNull(id, "id");
		vldExist(id, errors);
		return errors;
	}
	
	
	private WebErrors validateDelete(Long[] ids, HttpServletRequest request) {
		WebErrors errors = WebErrors.create(request);
		errors.ifEmpty(ids, "ids");
		for (Long id : ids) {
			vldExist(id, errors);
		}
		return errors;
	}
	 public String getStringRandom(int length) {  
         
	        String val = "";  
	        Random random = new Random();  
	          
	        //参数length，表示生成几位随机数  
	        for(int i = 0; i < length; i++) {  
	              
	            String charOrNum = random.nextInt(2) % 2 == 0 ? "char" : "num";  
	            //输出字母还是数字  
	            if( "char".equalsIgnoreCase(charOrNum) ) {  
	                //输出是大写字母还是小写字母  
	                int temp = random.nextInt(2) % 2 == 0 ? 65 : 97;  
	                val += (char)(random.nextInt(26) + temp);  
	            } else if( "num".equalsIgnoreCase(charOrNum) ) {  
	                val += String.valueOf(random.nextInt(10));  
	            }  
	        }  
	        return val;  
	    } 
	
	
	@Autowired
	private AccountczMng accountczMng;
	@Autowired
	private AccountItemMng accountItemMng;
	@Autowired
	private AccountMng accountMng;
	@Autowired
	private KetaUserMng ketaUserMng;
	
	@Autowired
	private WebsiteMng websiteMng;
	
	@Autowired
	private CmsLogMng cmsLogMng;
	@Autowired
	private OrganizationMng organizationMng;
	
	
	@Autowired
	private DlsMng dlsMng;
	@Autowired
	private GysMng gysMng;
	@Autowired
	private BldMng bldMng;
	@Autowired
	private FwsMng fwsMng;
	@Autowired
	private JmfMng jmfMng;
	@Autowired
	private PaymentPluginsMng paymentPluginsMng;
	
	@Autowired
	private ShopDictionaryMng shopDictionaryMng;
	
}