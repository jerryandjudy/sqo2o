package com.jspgou.cms.action.admin.main;

import static com.jspgou.common.page.SimplePage.cpn;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.security.SignatureException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Random;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.jspgou.cms.Alipay;
import com.jspgou.cms.dao.DlsDao;
import com.jspgou.cms.dao.KetaUserDao;
import com.jspgou.cms.entity.CmsLog;
import com.jspgou.cms.entity.Dls;
import com.jspgou.cms.entity.Jmf;
import com.jspgou.cms.entity.JmfRecharge;
import com.jspgou.cms.entity.KetaUser;
import com.jspgou.cms.entity.Order;
import com.jspgou.cms.entity.Organization;
import com.jspgou.cms.entity.PaymentPlugins;
import com.jspgou.cms.entity.ShopDictionary;
import com.jspgou.cms.manager.CmsLogMng;
import com.jspgou.cms.manager.DlsMng;
import com.jspgou.cms.manager.JmfMng;
import com.jspgou.cms.manager.JmfRechargeMng;
import com.jspgou.cms.manager.KetaUserMng;
import com.jspgou.cms.manager.OrganizationMng;
import com.jspgou.cms.manager.PaymentPluginsMng;
import com.jspgou.cms.manager.ShopDictionaryMng;
import com.jspgou.cms.web.FrontUtils;
import com.jspgou.cms.web.ShopFrontHelper;
import com.jspgou.common.page.Pagination;
import com.jspgou.common.web.CookieUtils;
import com.jspgou.common.web.RequestUtils;
import com.jspgou.core.dao.WebsiteDao;
import com.jspgou.core.entity.User;
import com.jspgou.core.entity.Website;
import com.jspgou.core.manager.WebsiteMng;
import com.jspgou.core.security.CmsAuthorizingRealm;
import com.jspgou.core.security.CmsAuthorizingRealm.HashPassword;
import com.jspgou.core.web.SiteUtils;
import com.jspgou.core.web.WebErrors;
import com.jspgou.core.web.front.FrontHelper;

/**
 * This class should preserve.
 * 
 * @preserve
 */
@Controller
public class JmfRechargeAct extends Alipay{
	private static final Logger log = LoggerFactory
			.getLogger(JmfRechargeAct.class);

	@RequestMapping("/jmfrecharge/v_list.do")
	public String list(Integer pageNo, HttpServletRequest request,
			ModelMap model) {

		Website website = SiteUtils.getWeb(request);

		// = manager.getPageByIsDisabledAndWebsiteId(website.getId(), false,
		// cpn(pageNo), CookieUtils.getPageSize(request));
		Pagination pagination = jmfRechargeMng.getPageByIsDisabled(false,
				cpn(pageNo), CookieUtils.getPageSize(request));

		model.addAttribute("pagination", pagination);
		return "jmfrecharge/list";
	}

	@RequestMapping("/jmfrecharge/userlist.do")
	public String userlist(Integer pageNo, Long siteId,
			HttpServletRequest request, ModelMap model) {

		Website website = SiteUtils.getWeb(request);
		String jmftypeIdstr = request.getParameter("jmftypeIdstr");
		// = manager.getPageByIsDisabledAndWebsiteId(website.getId(), false,
		// cpn(pageNo), CookieUtils.getPageSize(request));
		// 39,供应商加盟费,40,便利店加盟费,41,代理商加盟费
		List fctype = null;
		Pagination pagination = null;

		if (jmftypeIdstr != null && jmftypeIdstr.trim().length() > 0) {

			fctype = jmfRechargeMng.getFcList(jmftypeIdstr);
			pagination = jmfRechargeMng
					.getPageByOrganization(siteId, jmftypeIdstr, cpn(pageNo),
							CookieUtils.getPageSize(request));
		}

		model.addAttribute("pagination", pagination);
		model.addAttribute("jmftypeIdstr", jmftypeIdstr);
		return "jmfrecharge/userlist";
	}

	/**
	 * 选择支付方式
	 * @param id
	 * @param request
	 * @param model
	 * @return
	 */
	@RequestMapping("/jmfrecharge/jmfpay.do")
	public String jmfpay(Long id, HttpServletRequest request, ModelMap model) {

		WebErrors errors = validateEdit(id, request);
		if (errors.hasErrors()) {
			return errors.showErrorPage(model);
		}
		List<PaymentPlugins> paymentPluginsList = paymentPluginsMng.getList();
		JmfRecharge jmfRecharge = jmfRechargeMng.findById(id);
		model.addAttribute("jmfRecharge", jmfRecharge);
		model.addAttribute("paymentPluginsList", paymentPluginsList);
		return "jmfrecharge/jmfpay";
	}

	// 在线支付订单
	@RequestMapping("/jmfrecharge/jmfpays.do")
	public String pay(Long jmfRechargeId, String code, HttpServletRequest request,
			HttpServletResponse response, ModelMap model) {
		Website web = SiteUtils.getWeb(request);
		WebErrors errors = validateEdit(jmfRechargeId, request);
		if (errors.hasErrors()) {
			return errors.showErrorPage(model);
		}
		JmfRecharge jmfRecharge = jmfRechargeMng.findById(jmfRechargeId);
		if (jmfRechargeId != null && jmfRecharge != null) {
			PaymentPlugins paymentPlugins = paymentPluginsMng.findByCode(code);
			PrintWriter out = null;
			String aliURL = null;
			try {
				if (!StringUtils.isBlank(code) && code.equals("alipayPartner")) {

					aliURL = alipay(paymentPlugins, web, jmfRecharge, request,
							model);
				} else if (!StringUtils.isBlank(code) && code.equals("alipay")) {
					aliURL = alipayapi(paymentPlugins, web, jmfRecharge,
							request, model);
				}
				jmfRecharge.setPayType(code);
				jmfRechargeMng.updateByUpdater(jmfRecharge);
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

	private String alipayapi(PaymentPlugins paymentPlugins, Website web,
			JmfRecharge jmfRecharge, HttpServletRequest request, ModelMap model) {
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
		notify_url = notify_url + "/nhaocang/admin/jmfrecharge/aliReturn.do";
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
//		return_url = return_url + "/aliReturnUrl.jspx";
		// 卖家支付宝帐户
		String seller_email = paymentPlugins.getSellerEmail();// 必填
		// 付款金额
		String price = String.valueOf(jmfRecharge.getRealValue());// 必填
		// 商户订单号
		String out_trade_no = jmfRecharge.getId() + "";// 商户网站订单系统中唯一订单号，必填
		// 订单名称
		String subject = "收取" + jmfRecharge.getUserRealname() + "加盟费";// 必填
		if (subject.length() > 128) {
			subject = subject.substring(0, 123) + "...";
		}
		// 商品数量
		String quantity = "1";// 必填，建议默认为1，不改变值，把一次交易看成是一次下订单而非购买一件商品
		// 物流支付方式
		String logistics_payment = "BUYER_PAY";// 必填，两个值可选：SELLER_PAY（卖家承担运费）、BUYER_PAY（买家承担运费）
		// 订单描述
		String body = "收取" + jmfRecharge.getUserRealname() + "加盟费";// 必填
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
	@RequestMapping(value = "/jmfrecharge/aliReturn.do")
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
		System.out.println("/jmfrecharge/aliReturn.do");
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
		JmfRecharge jmfRecharge = jmfRechargeMng.findById(code);
		PaymentPlugins paymentPlugins = paymentPluginsMng.findByCode(jmfRecharge
				.getPayType());
		if (verify(params, paymentPlugins.getPartner(),
				paymentPlugins.getSellerKey())) {
			System.out.println("id===========:" + jmfRecharge.getId());
			if (trade_status.equals("WAIT_BUYER_PAY")) {
				// 该判断表示买家已在支付宝交易管理中产生了交易记录，但没有付款
				return FrontUtils.showMessage(request, model, "付款失败！");
			} else if (trade_status.equals("WAIT_SELLER_SEND_GOODS")) {
				// 该判断表示买家已在支付宝交易管理中产生了交易记录且付款成功，但卖家没有发货
				Double totalfee=Double.parseDouble(total_fee);
				if(totalfee==jmfRecharge.getRealValue()){
				jmfRecharge.setAddTime(new Date());
				jmfRecharge.setPayStatus("2");
				jmfRechargeMng.updateByUpdater(jmfRecharge);
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
				if(totalfee==jmfRecharge.getRealValue()){
					jmfRecharge.setAddTime(new Date());
					jmfRecharge.setPayStatus("2");
					jmfRechargeMng.updateByUpdater(jmfRecharge);
					}
				return FrontUtils.showMessage(request, model, "SUCCESS");
			}
		}
		return FrontUtils.showMessage(request, model, "付款异常！");
	}

	private String alipay(PaymentPlugins paymentPlugins, Website web,
			JmfRecharge jmfRecharge, HttpServletRequest request, ModelMap model) {
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
		notify_url = notify_url + "/nhaocang/admin/jmfrecharge/aliReturn.do";
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
		String out_trade_no = jmfRecharge.getId() + "";// 商户网站订单系统中唯一订单号，必填
		// 订单名称
		String subject = "收取" + jmfRecharge.getUserRealname() + "加盟费";// 必填
		if (subject.length() > 128) {
			subject = subject.substring(0, 123) + "...";
		}
		// 付款金额
		String price = String.valueOf(jmfRecharge.getRealValue());// 必填
		// 商品数量
		String quantity = "1";// 必填，建议默认为1，不改变值，把一次交易看成是一次下订单而非购买一件商品
		// 物流支付方式
		String logistics_payment = "BUYER_PAY";// 必填，两个值可选：SELLER_PAY（卖家承担运费）、BUYER_PAY（买家承担运费）
		// 订单描述
		String body = "收取" + jmfRecharge.getUserRealname() + "加盟费";// 必填
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

	






	@RequestMapping("/jmfrecharge/v_list_operating.do")
	public String listOperating(String queryUsername, String queryName,
			Integer pageNo, String jmftypeIdstr, HttpServletRequest request,
			ModelMap model) {
		Website site = SiteUtils.getWeb(request);
		Pagination pagination = jmfRechargeMng.getPage(queryUsername,
				queryName, cpn(pageNo), jmftypeIdstr,
				CookieUtils.getPageSize(request));
		model.addAttribute("pagination", pagination);
		model.addAttribute("pageNo", pagination.getPageNo());
		model.addAttribute("queryUsername", queryUsername);
		model.addAttribute("queryName", queryName);
		model.addAttribute("jmftypeIdstr", jmftypeIdstr);
		return "jmfrecharge/userlist";
	}

	@RequestMapping("/jmfrecharge/v_add.do")
	public String add(HttpServletRequest request, ModelMap model) {

		List<Jmf> jmfDictList = jmfMng.getAllList();
		model.put("jmfDictList", jmfDictList);
		return "jmfrecharge/add";
	}

	@RequestMapping("/jmfrecharge/o_edit.do")
	public String edit(Long id, HttpServletRequest request, ModelMap model) {
		WebErrors errors = validateEdit(id, request);
		if (errors.hasErrors()) {
			return errors.showErrorPage(model);
		}
		JmfRecharge jmfRecharge = jmfRechargeMng.findById(id);
		jmfRecharge.setPayStatus("2");
		jmfRechargeMng.updateByUpdater(jmfRecharge);
		model.addAttribute("JmfRecharge", jmfRecharge);
		return "redirect:v_list.do";
	}

	private WebErrors validateEdit(Long id, HttpServletRequest request) {
		WebErrors errors = WebErrors.create(request);
		errors.ifNull(id, "id");
		if(errors.hasErrors()){
			return errors;
		}
		vldExist(id, errors);
		return errors;
	}

	private boolean vldExist(Long id, WebErrors errors) {
		JmfRecharge entity = jmfRechargeMng.findById(id);
		if (entity != null && entity.getPayStatus() != null
				&& "2".equals(entity.getPayStatus())) {
			errors.addError("该记录已经充值");
			return true;
		}
		return errors.ifNotExist(entity, JmfRecharge.class, id);
	}

	@RequestMapping("/jmfrecharge/o_save.do")
	public String save(JmfRecharge bean, Long websiteId, String ketauserId,
			Date validFrom, Date validUntil, String ketauserUsername,
			HttpServletRequest request, ModelMap model) {
		String ids = ketauserId;
		/*
		 * Organization organization=organizationMng.findByName("代理商");
		 * WebErrors errors = validateSave(organization, request); if
		 * (errors.hasErrors()) { return errors.showErrorPage(model); }
		 */
		KetaUser ketaUser = null;
		if (ketauserId != null) {
			ketaUser = ketaUserMng.findById(Long.parseLong(ketauserId));

		}
		bean.setKetaUser(ketaUser);
		bean.setOrganizationType(ketaUser.getOrganization().getName());

		Website website = websiteMng.findById(websiteId);
		bean.setWebsite(website);

		String content = "";

		Double dlsfc = bean.getRealValue()
				* (bean.getDlsFc() / (bean.getDlsFc() + bean.getPtFc()));
		Double ptfc = bean.getRealValue()
				* (bean.getPtFc() / (bean.getDlsFc() + bean.getPtFc()));

		// ketaUser.setOrganization(organization);
		User user = SiteUtils.getUser(request);

		bean.setAddPeople(user.getUsername());
		bean.setDlsFc(dlsfc);
		bean.setPtFc(ptfc);
		bean.setPayStatus("1");
		bean.setAddTime(new Date());
		bean = jmfRechargeMng.save(bean);
		content = content + "id:" + bean.getId() + ";新增加盟费充值记录用户："
				+ bean.getKetaUser().getRealname() + ";";
		cmsLogMng.operating(request, "JmfRecharge.log.save", content);
		return "redirect:v_list.do";
	}

	private WebErrors validateSave(Organization organization,
			HttpServletRequest request) {
		WebErrors errors = WebErrors.create(request);
		// JmfRecharge
		// lsfc=JmfRechargeMng.findByFctypeId(bean.getFctypeId().getId(),
		// bean.getWebsite().getId());
		if (organization == null && organization.getId() < 1) {
			errors.addError("代理商组织机构不存在");
		}
		return errors;
	}

	@RequestMapping("/jmfrecharge/o_update.do")
	public String update(JmfRecharge bean, String text,
			HttpServletRequest request, ModelMap model) {
		WebErrors errors = validateUpdate(bean, request);
		if (errors.hasErrors()) {
			return errors.showErrorPage(model);
		}
		String content = "";
		String s = "";
		JmfRecharge oldbean = null;
		oldbean = jmfRechargeMng.findById(bean.getId());
		User user = SiteUtils.getUser(request);
		content = "操作人：" + user.getUsername() + ";Id=" + bean.getId()
				+ ";代理商名称：" + oldbean.getKetaUser().getRealname() + "-to-"
				+ bean.getKetaUser().getRealname() + ";代理商电话："
				+ oldbean.getKetaUser().getPhone() + "-to-"
				+ bean.getKetaUser().getPhone() + ";";
		cmsLogMng.operating(request, "JmfRecharge.log.update", content);
		KetaUser ketaUser = oldbean.getKetaUser();

		log.info("update brand. id={}.", bean.getId());

		return "redirect:v_list.do";
	}

	private WebErrors validateUpdate(JmfRecharge bean,
			HttpServletRequest request) {
		WebErrors errors = WebErrors.create(request);
		Long id = bean.getId();

		errors.ifNull(id, "id");
		vldExist(id, errors);
		return errors;
	}

	@RequestMapping("/jmfrecharge/o_delete.do")
	public String delete(JmfRecharge bean, HttpServletRequest request,
			ModelMap model) {
		// Long id = Long.parseLong(request.getParameter("id"));
		// Long ids = 5l;
		WebErrors errors = validateUpdate(bean, request);
		if (errors.hasErrors()) {
			return errors.showErrorPage(model);
		}
		String content = "";
		String s = "";
		JmfRecharge oldbean = null;
		oldbean = jmfRechargeMng.findById(bean.getId());
		User user = SiteUtils.getUser(request);
		content = "操作人：" + user.getUsername() + ";Id=" + bean.getId()
				+ ";加盟费名称：" + oldbean.getKetaUser().getRealname();
		cmsLogMng.operating(request, "JmfRecharge.log.delete", content);

		jmfRechargeMng.deleteById(oldbean.getId());

		log.info("update brand. id={}.", bean.getId());

		return "redirect:v_list.do";
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

		// 参数length，表示生成几位随机数
		for (int i = 0; i < length; i++) {

			String charOrNum = random.nextInt(2) % 2 == 0 ? "char" : "num";
			// 输出字母还是数字
			if ("char".equalsIgnoreCase(charOrNum)) {
				// 输出是大写字母还是小写字母
				int temp = random.nextInt(2) % 2 == 0 ? 65 : 97;
				val += (char) (random.nextInt(26) + temp);
			} else if ("num".equalsIgnoreCase(charOrNum)) {
				val += String.valueOf(random.nextInt(10));
			}
		}
		return val;
	}

	@Autowired
	private JmfRechargeMng jmfRechargeMng;
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
	private JmfMng jmfMng;
	@Autowired
	private PaymentPluginsMng paymentPluginsMng;
	@Autowired
	private ShopDictionaryMng shopDictionaryMng;

}