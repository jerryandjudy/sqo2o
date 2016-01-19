package com.jspgou.cms.action.front;

import static com.jspgou.common.page.SimplePage.cpn;
import static com.jspgou.cms.Constants.MEMBER_SYS;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import com.jspgou.common.page.Pagination;
import com.jspgou.common.security.annotation.Secured;
import com.jspgou.common.web.springmvc.MessageResolver;
import com.jspgou.core.entity.User;
import com.jspgou.core.entity.Website;
import com.jspgou.cms.entity.ShopMember;
import com.jspgou.cms.manager.ConsultMng;
import com.jspgou.cms.manager.DiscussMng;
import com.jspgou.cms.manager.OrderItemMng;
import com.jspgou.cms.web.ShopFrontHelper;
import com.jspgou.cms.web.SiteUtils;
import com.jspgou.cms.web.threadvariable.MemberThread;

/**
* This class should preserve.
* @preserve
*/
@Secured
@Controller
public class MyInfomationAct {
	
	/**
	 * 我的评论
	 */
	@RequestMapping(value = "/my_discuss*.jspx")
	public String getMyDiscuss(Integer pageNo, HttpServletRequest request,
			ModelMap model) {
		Website web = SiteUtils.getWeb(request);
		ShopMember member = MemberThread.get();
		User user = SiteUtils.getUser(request);
		if(member == null || member.getUsername()==null||user==null){
			return "redirect:../login.jspx";
		}
		Pagination pagination = discussMng.getPageByMember(member.getId(),cpn(pageNo), 10, true);
		model.addAttribute("pagination", pagination);
		model.addAttribute("historyProductIds", getHistoryProductIds(request));
		ShopFrontHelper.setCommonData(request, model, web, 1);
		ShopFrontHelper.setDynamicPageData(request, model, web, "","my_discuss", ".jspx", cpn(pageNo));
		return web.getTplSys(MEMBER_SYS, MessageResolver.getMessage(request,"tpl.mydiscuss"));
	}

	/**
	 * 我的咨询
	 */
	@RequestMapping(value = "/my_cousult*.jspx")
	public String getMyCousult(Integer pageNo, HttpServletRequest request,
			ModelMap model) {
		Website web = SiteUtils.getWeb(request);
		ShopMember member = MemberThread.get();
		User user = SiteUtils.getUser(request);
		if(member == null || member.getUsername()==null||user==null){
			return "redirect:../login.jspx";
		}
		Pagination pagination = consultMng.getPageByMember(member.getId(),cpn(pageNo), 1, true);
		model.addAttribute("pagination", pagination);
		model.addAttribute("historyProductIds", getHistoryProductIds(request));
		ShopFrontHelper.setCommonData(request, model, web, 1);
		ShopFrontHelper.setDynamicPageData(request, model, web, "","my_cousult", ".jspx", cpn(pageNo));
		return web.getTplSys(MEMBER_SYS, MessageResolver.getMessage(request,"tpl.myconsult"));
	}

	/**
	 * 购物记录
	 */
	@RequestMapping(value = "/buyRecord*.jspx")
	public String getBuyRecord(Integer pageNo, HttpServletRequest request,
			ModelMap model) {
		Website web = SiteUtils.getWeb(request);
		ShopMember member = MemberThread.get();
		User user = SiteUtils.getUser(request);
		if(member == null || member.getUsername()==null||user==null){
			return "redirect:../login.jspx";
		}
		Pagination pagination = orderItemMng.getPageByMember(4, member.getId(),cpn(pageNo), 2);
		model.addAttribute("pagination", pagination);
		model.addAttribute("historyProductIds", getHistoryProductIds(request));
		ShopFrontHelper.setCommonData(request, model, web, 1);
		ShopFrontHelper.setDynamicPageData(request, model, web, "","buyRecord", ".jspx", cpn(pageNo));
		return web.getTplSys(MEMBER_SYS, MessageResolver.getMessage(request,"tpl.buyRecord"));
	}

	public String getHistoryProductIds(HttpServletRequest request){
		String str = null ;
		Cookie[] cookie = request.getCookies();
		int num = cookie.length;
		for (int i = 0; i < num; i++) {
			if (cookie[i].getName().equals("shop_record")) {
				str = cookie[i].getValue();
				break;
			}
		}
		return str;
	}

	@Autowired
	private OrderItemMng orderItemMng;
	@Autowired
	private ConsultMng consultMng;
	@Autowired
	private DiscussMng discussMng;

}
