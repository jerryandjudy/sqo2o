package com.jspgou.cms.action.front;

import static com.jspgou.cms.Constants.COUPON;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import com.jspgou.common.web.RequestUtils;
import com.jspgou.common.web.ResponseUtils;
import com.jspgou.common.web.springmvc.MessageResolver;
import com.jspgou.core.entity.Website;
import com.jspgou.core.web.front.URLHelper;
import com.jspgou.cms.entity.Coupon;
import com.jspgou.cms.entity.MemberCoupon;
import com.jspgou.cms.entity.ShopMember;
import com.jspgou.cms.manager.CouponMng;
import com.jspgou.cms.manager.MemberCouponMng;
import com.jspgou.cms.web.ShopFrontHelper;
import com.jspgou.cms.web.SiteUtils;
import com.jspgou.cms.web.threadvariable.MemberThread;

/**
* This class should preserve.
* @preserve
*/
@Controller
public class CouponAct {

	@RequestMapping(value = "/coupon*.jspx", method = RequestMethod.GET)
	public String list(HttpServletRequest request, ModelMap model) {
		Website web = SiteUtils.getWeb(request);
		int pageNo = URLHelper.getPageNo(request);
		ShopFrontHelper.setCommonData(request, model, web,pageNo);
		ShopFrontHelper.setDynamicPageData(request, model, web, RequestUtils.getLocation(request),"coupon", ".jspx", pageNo);
		return web.getTplSys(COUPON, MessageResolver.getMessage(request,"tpl.coupon"));
	}

	@RequestMapping(value = "/getcoupon.jspx")
	public void fetchGift(Long id,HttpServletRequest request, HttpServletResponse response, ModelMap model) throws JSONException {
		Website web = SiteUtils.getWeb(request);
		ShopMember member = MemberThread.get();
		Date newdate= new Date();
		JSONObject json=new JSONObject();
		if (member == null) {
			json.put("status", 0);
		}else if(id==null||couponMng.findById(id)==null){
			json.put("status", 2);
			json.put("error", "参数有误");
		}else if(memberCouponMng.findByCoupon(member.getId(), id)!=null){
			json.put("status", 2);
			json.put("error", "您已领取过该优惠券了");
		}else if(!couponMng.findById(id).getIsusing()){
			json.put("status", 2);
			json.put("error", "该优惠券已停用");
		}else if(!newdate.before(couponMng.findById(id).getCouponEndTime())){
			json.put("status", 2);
			json.put("error", "该优惠券已过期");
		}else if(couponMng.findById(id).getCouponCount()<1){
			json.put("status", 2);
			json.put("error", "该优惠券已领完");
		}else{
			Coupon  coupon=couponMng.findById(id);
			MemberCoupon memberCoupon = new MemberCoupon();
			memberCoupon.setCoupon(coupon);
			memberCoupon.setMember(member);
			memberCoupon.setIsuse(false);
			coupon.setCouponCount(coupon.getCouponCount()-1);
			memberCouponMng.save(memberCoupon);
			couponMng.update(coupon);
			json.put("status", 1);
		}
		ShopFrontHelper.setCommonData(request, model, web, 1);
        ResponseUtils.renderJson(response, json.toString());	
	}
	
	
	@Autowired
	private CouponMng couponMng;
	@Autowired
	private MemberCouponMng memberCouponMng;
	
}
