package com.jspgou.cms.action.admin;

import static com.jspgou.common.page.SimplePage.cpn;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import com.jspgou.cms.entity.ShopAdmin;
import com.jspgou.cms.manager.OrderMng;
import com.jspgou.cms.manager.UpdateMng;
import com.jspgou.cms.web.AdminContextInterceptor;
import com.jspgou.cms.web.SiteUtils;
import com.jspgou.cms.web.threadvariable.AdminThread;
import com.jspgou.common.web.CookieUtils;
import com.jspgou.core.entity.Account;
import com.jspgou.core.entity.Website;
import com.jspgou.core.manager.AccountMng;
import com.jspgou.core.manager.WebsiteMng;

/**
 * This class should preserve.
 * 
 * @preserve
 */
@Controller
public class WelcomeAct {

	@RequestMapping("/main.do")
	public String main() {
		return "main";
	}

	@RequestMapping("/left.do")
	public String left() {
		return "left";
	}

	@RequestMapping("/right.do")
	public String right(HttpServletRequest request, ModelMap model) {
		ShopAdmin admin = AdminThread.get();
		List<Object> o =new ArrayList();
		boolean isSuper=false;
		if(admin.getIsSuper()||admin.getAdmin().getIsSuper()){
			isSuper=true;
		}
		if(isSuper){
			o = orderMng.getTotlaOrder(null);
		}else{
			Website website=SiteUtils.getWeb(request);
			o = orderMng.getTotlaOrder(website.getId());
		}
		
		Long[] c = (Long[]) o.get(0);
		Runtime runtime = Runtime.getRuntime();
		long freeMemoery = runtime.freeMemory();
		long totalMemory = runtime.totalMemory();
		long usedMemory = totalMemory - freeMemoery;
		long maxMemory = runtime.maxMemory();
		long useableMemory = maxMemory - totalMemory + freeMemoery;
		model.addAttribute("c", c);
		model.addAttribute("admin", admin);
		model.addAttribute("restart", Integer.parseInt(updateMng.getRestart()));
		model.addAttribute("site", SiteUtils.getWeb(request));
		model.addAttribute("freeMemoery", freeMemoery);
		model.addAttribute("totalMemory", totalMemory);
		model.addAttribute("usedMemory", usedMemory);
		model.addAttribute("maxMemory", maxMemory);
		model.addAttribute("useableMemory", useableMemory);
		return "right";
	}

	@RequestMapping("/top.do")
	public String top(HttpServletRequest request, ModelMap model) {
		ShopAdmin admin = AdminThread.get();
		boolean isSuper=false;
		if(admin.getAdmin().getIsSuper()&&admin.getAdmin().isSuper()){
			isSuper=true;
		}
		// 需要获得站点列表
		if(isSuper){
	    Account account = accountMng.findByStatus(1);
	    model.addAttribute("money", account.getMoney());
		}
		List<Website> siteList = websiteMng.getAllList();
		model.addAttribute("siteList", siteList);
		model.addAttribute("isSuper", isSuper);
		Website site = SiteUtils.getWeb(request);
		model.addAttribute("siteParam", AdminContextInterceptor.SITE_PARAM);
		model.addAttribute("site", site);
		model.addAttribute("admin", admin);
		return "top";
	}

	@Autowired
	private OrderMng orderMng;
	@Autowired
	private AccountMng accountMng;
	@Autowired
	private WebsiteMng websiteMng;
	@Autowired
	private UpdateMng updateMng;
}
