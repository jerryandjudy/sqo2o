package com.jspgou.cms.action.admin.main;

import static com.jspgou.common.page.SimplePage.cpn;

import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Random;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import com.jspgou.cms.Alipay;
import com.jspgou.cms.entity.AccountTx;
import com.jspgou.cms.entity.Accountcz;
import com.jspgou.cms.entity.Bld;
import com.jspgou.cms.entity.Fws;
import com.jspgou.cms.entity.Gys;
import com.jspgou.cms.entity.PaymentPlugins;
import com.jspgou.cms.manager.AccountTxMng;
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
import com.jspgou.common.hibernate3.Updater;
import com.jspgou.common.page.Pagination;
import com.jspgou.common.web.CookieUtils;
import com.jspgou.core.entity.Account;
import com.jspgou.core.entity.AccountItem;
import com.jspgou.core.entity.Website;
import com.jspgou.core.manager.AccountItemMng;
import com.jspgou.core.manager.AccountMng;
import com.jspgou.core.manager.WebsiteMng;
import com.jspgou.core.web.SiteUtils;
import com.jspgou.core.web.WebErrors;

/**
 * This class should preserve.
 * 
 * @preserve
 */
@Controller
public class AccountTxAct extends Alipay {
	private static final Logger log = LoggerFactory
			.getLogger(AccountTxAct.class);

	@RequestMapping("/accountTx/v_list.do")
	public String list(Long siteId, String payStatus, String paySuccessTime,
			String username, String organizationType, Integer pageNo,
			HttpServletRequest request, ModelMap model) {

		Website website = SiteUtils.getWeb(request);

		Pagination pagination = accountTxMng.findAll(siteId, payStatus,
				paySuccessTime, username, organizationType, cpn(pageNo),
				CookieUtils.getPageSize(request));
		List siteList = websiteMng.getAllList();
		model.addAttribute("pageNo", pageNo);
		model.addAttribute("username", username);
		model.addAttribute("siteId", siteId);
		model.addAttribute("payStatus", payStatus);
		model.addAttribute("organizationType", organizationType);
		model.addAttribute("paySuccessTime", paySuccessTime);
		model.addAttribute("pagination", pagination);
		model.addAttribute("siteList", siteList);
		return "accounttx/list";
	}
	// 支付宝返回参数
		@RequestMapping(value = "/accountTx/aliReturn.do")
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
			//获取支付宝的通知返回参数，可参考技术文档中页面跳转同步通知参数列表(以下仅供参考)//
			//批量付款数据中转账成功的详细信息

			String success_details = new String(request.getParameter("success_details").getBytes("ISO-8859-1"),"UTF-8");
            System.out.println("/accountTx/aliReturn.do:success_details---"+success_details);
			//批量付款数据中转账失败的详细信息
			String fail_details = new String(request.getParameter("fail_details").getBytes("ISO-8859-1"),"UTF-8");

			System.out.println("/accountTx/aliReturn.do:fail_details--"+success_details);

			// 批量退款数据中转账成功的笔数
			String tradeNo = "";
		
			// String result_details = "2015070700001000210059292471^0.01^SUCCESS";
			if (success_details != null && success_details.length() > 0) {
				String[] res = success_details.split("\\^");
				tradeNo = res[0];
			}
			if(tradeNo.length()==0){
				String[] res = fail_details.split("\\^");
				tradeNo = res[0];
			}
			System.out.println("/accountTx/aliReturn.do:tradeNo"+tradeNo);
			AccountTx entity  = accountTxMng.findById(Long.parseLong(tradeNo));
			PaymentPlugins paymentPlugins = paymentPluginsMng.findByCode("alipay");
			if (verify(params, paymentPlugins.getPartner(),
					paymentPlugins.getSellerKey())) {

				// order.setPaymentStatus(2);
				// order.setPaymentCode("alipayPartner");
				entity.setPayStatus("1");
				entity.setPaySuccessTime(new Date());
				Account account =null;
				if("便利店".equals(entity.getOrganizationType())){
					Bld bld = bldMng.findByKetaUserId(entity.getKetaUser().getId());
					account = bld.getAccount();
				}else if("供应商".equals(entity.getOrganizationType())){
					Gys gys = gysMng.findByKetaUserId(entity.getKetaUser().getId());
					account = gys.getAccount();
				}else if("代理商".equals(entity.getOrganizationType())){
					account = entity.getWebsite().getAccount();
				}else if("服务商".equals(entity.getOrganizationType())){
					Fws fws =fwsMng.findByKetaUserId(entity.getKetaUser().getId());
					account =fws.getAccount();
				    }
				account.setMoney(account.getMoney()-entity.getRealValue());
				accountMng.updateByUpdater(new Updater(account));
				AccountItem accountItem = new AccountItem();
				accountItem.setAccount(account);
				accountItem.setMoney(entity.getRealValue());
				accountItem.setMoneyTime(new Date());
				accountItem.setMoneyType(4);
				accountItem.setName("提现");
				accountItem.setRemark(entity.getOrganizationType()+"提现");
				accountItem.setStatus(true);
				accountItem.setUseStatus(true);
				accountItemMng.save(accountItem);
				accountTxMng.update(entity);
				return FrontUtils.showMessage(request, model, "SUCCESS");

			}
			return FrontUtils.showMessage(request, model, "付款异常！");
		}
	@RequestMapping("/accountTx/alipay.do")
	public String reimburse(Long id, HttpServletRequest request,
			HttpServletResponse response, ModelMap model) {
		Website web = SiteUtils.getWeb(request);
		WebErrors errors = validateEdit(id, request);
		if (errors.hasErrors()) {
			return errors.showErrorPage(model);
		}
		AccountTx entity = accountTxMng.findById(id);
		PaymentPlugins paymentPlugins = paymentPluginsMng.findByCode("alipay");
		if (paymentPlugins != null) {
			PrintWriter out = null;
			try {
				String aliURL = "";

				aliURL = alipayzz(paymentPlugins, web, entity, request, model);

				response.setContentType("text/html;charset=UTF-8");
				out = response.getWriter();
				out.print(aliURL);
			} catch (Exception e) {
			} finally {
				if (out != null) {
					out.close();
				}
			}
		}
		return null;
	}

	/**
	 * 支付宝转账
	 * 
	 * @param paymentPlugins
	 * @param web
	 * @param orderReturn
	 * @param request
	 * @param model
	 * @return
	 */
	private String alipayzz(PaymentPlugins paymentPlugins, Website web,
			AccountTx entity, HttpServletRequest request, ModelMap model) {
		// //////////////////////////////////请求参数//////////////////////////////////////
		// 必填参数//
		Date date = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
		SimpleDateFormat sdf2 = new SimpleDateFormat("yyyyMMddmmss");
		String date1 = sdf.format(date);
		String date2 = sdf2.format(date);
		// //////////////////////////////////请求参数//////////////////////////////////////

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
		notify_url = notify_url + "/nhaocang/admin/accountTx/aliReturn.do";
		// 需http://格式的完整路径，不允许加?id=123这类自定义参数

		// 付款账号
		String email = paymentPlugins.getSellerEmail();
		// 必填

		// 付款账户名
		String account_name = web.getCompany();
		// 必填，个人支付宝账号是真实姓名公司支付宝账号是公司名称

		// 付款当天日期
		String pay_date = date1;
		// 必填，格式：年[4位]月[2位]日[2位]，如：20100801

		// 批次号
		String batch_no = date2+"0"+ entity.getId();
		// 必填，格式：当天日期[8位]+序列号[3至16位]，如：201008010000001

		// 付款总金额
		String batch_fee = entity.getRealValue() + "";
		// 必填，即参数detail_data的值中所有金额的总和

		// 付款笔数
		String batch_num = "1";
		// 必填，即参数detail_data的值中，“|”字符出现的数量加1，最大支持1000笔（即“|”字符出现的数量999个）

		// 付款详细数据
		String detail_data = entity.getId() + "^" + entity.getPayBankAccount()
				+ "^" + entity.getPayHm() + "^" + entity.getRealValue() + "^提现";
		// 必填，格式：流水号1^收款方帐号1^真实姓名^付款金额1^备注说明1|流水号2^收款方帐号2^真实姓名^付款金额2^备注说明2....

		// ////////////////////////////////////////////////////////////////////////////////

		// 把请求参数打包成数组
		Map<String, String> sParaTemp = new HashMap<String, String>();
		sParaTemp.put("service", "batch_trans_notify");
		sParaTemp.put("partner", paymentPlugins.getPartner());
		sParaTemp.put("_input_charset", "utf-8");
		sParaTemp.put("notify_url", notify_url);
		sParaTemp.put("email", email);
		sParaTemp.put("account_name", account_name);
		sParaTemp.put("pay_date", pay_date);
		sParaTemp.put("batch_no", batch_no);
		sParaTemp.put("batch_fee", batch_fee);
		sParaTemp.put("batch_num", batch_num);
		sParaTemp.put("detail_data", detail_data);

		// 建立请求
				String sHtmlText = buildRequest(sParaTemp,
						paymentPlugins.getSellerKey(), "get", "确认");
		return sHtmlText;
	}

	private WebErrors validateEdit(Long id, HttpServletRequest request) {
		WebErrors errors = WebErrors.create(request);
		errors.ifNull(id, "id");
		vldExist(id, errors);
		return errors;
	}

	private boolean vldExist(Long id, WebErrors errors) {
		AccountTx entity = accountTxMng.findById(id);
		if (entity != null && entity.getPayStatus() != null
				&& "1".equals(entity.getPayStatus())) {
			errors.addError("该记录已经成功提现");
			return true;
		}
		Account account =null;
		if("便利店".equals(entity.getOrganizationType())){
			Bld bld = bldMng.findByKetaUserId(entity.getKetaUser().getId());
			account = bld.getAccount();
		}else if("供应商".equals(entity.getOrganizationType())){
			Gys gys = gysMng.findByKetaUserId(entity.getKetaUser().getId());
			account = gys.getAccount();
		}else if("代理商".equals(entity.getOrganizationType())){
			account = entity.getWebsite().getAccount();
		}else if("服务商".equals(entity.getOrganizationType())){
		Fws fws =fwsMng.findByKetaUserId(entity.getKetaUser().getId());
		account =fws.getAccount();
	    }
		if (entity != null&&account!=null && entity.getRealValue() >account.getMoney()) {
			errors.addError("账户可用于提现金额不足，不可提现");
			return true;
		}
		return errors.ifNotExist(entity, AccountTx.class, id);
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
	private AccountTxMng accountTxMng;
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