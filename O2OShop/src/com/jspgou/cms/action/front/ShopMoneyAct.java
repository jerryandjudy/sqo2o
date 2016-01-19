package com.jspgou.cms.action.front;

import static com.jspgou.cms.Constants.MEMBER_SYS;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import com.jspgou.cms.entity.ShopMember;
import com.jspgou.cms.web.ShopFrontHelper;
import com.jspgou.cms.web.SiteUtils;
import com.jspgou.cms.web.threadvariable.MemberThread;
import com.jspgou.common.web.springmvc.MessageResolver;
import com.jspgou.core.entity.User;
import com.jspgou.core.entity.Website;
import com.jspgou.core.web.front.URLHelper;

/**
* This class should preserve.
* @preserve
*/
@Controller
public class ShopMoneyAct {
	
	/**
	 * 账户余额
	 */
	public static final String MEMBER_MONEY = "tpl.mymoney";
	
	@RequestMapping(value = "/shopMoney/mymoney*.jspx")
	public String getMyScore(Integer status,Integer useStatus,String startTime,
			String endTime,HttpServletRequest request, ModelMap model) {
		Website web = SiteUtils.getWeb(request);
		ShopMember member = MemberThread.get();
		//用户没有登录，跳转到登录页
		User user = SiteUtils.getUser(request);
		if(member == null || member.getUsername()==null||user==null){
			return "redirect:../login.jspx";
		}
		Integer pageNo = URLHelper.getPageNo(request);
		model.addAttribute("status", status);
		model.addAttribute("startTime", startTime);
		model.addAttribute("endTime", endTime);
		ShopFrontHelper.setCommonData(request, model, web, 1);
		ShopFrontHelper.setDynamicPageData(request, model, web, "","mymoney", ".jspx", pageNo);
		return web.getTplSys(MEMBER_SYS, MessageResolver.getMessage(request,MEMBER_MONEY));
	}
}
