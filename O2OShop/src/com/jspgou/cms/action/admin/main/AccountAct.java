package com.jspgou.cms.action.admin.main;

import static com.jspgou.common.page.SimplePage.cpn;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import com.jspgou.cms.manager.CmsLogMng;
import com.jspgou.common.page.Pagination;
import com.jspgou.common.web.CookieUtils;
import com.jspgou.common.web.ResponseUtils;
import com.jspgou.core.entity.Account;
import com.jspgou.core.entity.User;
import com.jspgou.core.entity.Website;
import com.jspgou.core.manager.AccountItemMng;
import com.jspgou.core.manager.AccountMng;
import com.jspgou.core.manager.GlobalMng;
import com.jspgou.core.manager.WebsiteMng;
import com.jspgou.core.web.SiteUtils;
import com.jspgou.core.web.WebErrors;


@Controller
public class AccountAct {
	private static final Logger log = LoggerFactory.getLogger(AccountAct.class);
	
	@RequestMapping("/account/account_list.do")
	public String siteMain(ModelMap model) {
		return "account/account_list";
	}
	@RequestMapping("/account/v_list.do")
	public String list(Integer pageNo, HttpServletRequest request, ModelMap model) {
		Account account = accountMng.findByStatus(1);//查询平台账户
		if(account!=null&&account.getId().length()>0){
		String accountId=account.getId();
		Pagination pagination=accountItemMng.getPageByAccountId(accountId, cpn(pageNo), CookieUtils
				.getPageSize(request));
		model.addAttribute("pagination",pagination);
		}
		return "account/ptaccountItem_list";
	}
	@RequestMapping("/account/qt_list.do")
	public String qtlist(String accountId,Integer pageNo, HttpServletRequest request, ModelMap model) {
		if(accountId!=null&&accountId.length()>0){
			Pagination pagination=accountItemMng.getPageByAccountId(accountId, cpn(pageNo), CookieUtils
					.getPageSize(request));
			model.addAttribute("pagination",pagination);
		}
		model.addAttribute("accountId",accountId);
		model.addAttribute("pageNo",pageNo);
		return "account/accountItem_list";
	}
	

	@Autowired
	private GlobalMng globalMng;
	@Autowired
	private AccountMng accountMng;
	@Autowired
	private AccountItemMng accountItemMng;
	@Autowired
	private CmsLogMng cmsLogMng;
}