package com.jspgou.cms.action.front;

import java.util.List;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.jspgou.common.web.session.SessionProvider;
import com.jspgou.common.web.springmvc.MessageResolver;
import com.jspgou.core.entity.Website;
import com.jspgou.cms.entity.MemberCoupon;
import com.jspgou.cms.entity.ShopMember;
import com.jspgou.cms.manager.MemberCouponMng;
import com.jspgou.cms.web.ShopFrontHelper;
import com.jspgou.cms.web.SiteUtils;
import com.jspgou.cms.web.threadvariable.MemberThread;

/**
* This class should preserve.
* @preserve
*/
@Controller
public class MemberCouponAct {
	
	/**
	 * 会员中心优惠劵
	 */
	@RequestMapping(value = "/myCoupon.jspx", method = RequestMethod.GET)
	public String pay1(HttpServletRequest request, ModelMap model) {
		Website web = SiteUtils.getWeb(request);
		ShopMember member=MemberThread.get();
		List<MemberCoupon> list=memberCouponMng.getList(member.getId());
		model.addAttribute("couList", list);
		model.addAttribute("historyProductIds", getHistoryProductIds(request));
		ShopFrontHelper.setCommonData(request, model, web, 1);
		return web.getTplSys("member", MessageResolver.getMessage(request,"tpl.myCoupon"));
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
	private MemberCouponMng memberCouponMng;
}
