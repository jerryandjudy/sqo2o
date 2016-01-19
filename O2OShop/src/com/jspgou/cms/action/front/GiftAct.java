package com.jspgou.cms.action.front;

import static com.jspgou.cms.Constants.GIFT;

import java.util.List;

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
import com.jspgou.core.entity.User;
import com.jspgou.core.entity.Website;
import com.jspgou.core.web.WebErrors;
import com.jspgou.core.web.front.FrontHelper;
import com.jspgou.core.web.front.URLHelper;
import com.jspgou.cms.entity.Address;
import com.jspgou.cms.entity.Gift;
import com.jspgou.cms.entity.ShopMember;
import com.jspgou.cms.entity.ShopMemberAddress;
import com.jspgou.cms.manager.AddressMng;
import com.jspgou.cms.manager.GiftExchangeMng;
import com.jspgou.cms.manager.GiftMng;
import com.jspgou.cms.manager.ShopMemberAddressMng;
import com.jspgou.cms.web.FrontUtils;
import com.jspgou.cms.web.ShopFrontHelper;
import com.jspgou.cms.web.SiteUtils;
import com.jspgou.cms.web.threadvariable.MemberThread;

/**
* This class should preserve.
* @preserve
*/
@Controller
public class GiftAct {

	@RequestMapping(value = "/gift*.jspx", method = RequestMethod.GET)
	public String list(HttpServletRequest request, ModelMap model) {
		Website web = SiteUtils.getWeb(request);
		int pageNo = URLHelper.getPageNo(request);
		ShopFrontHelper.setCommonData(request, model, web,pageNo);
		ShopFrontHelper.setDynamicPageData(request, model, web, RequestUtils.getLocation(request),"gift", ".jspx", pageNo);
		return web.getTplSys(GIFT, MessageResolver.getMessage(request,"tpl.gift"));
	}

	@RequestMapping(value = "/present.jspx", method = RequestMethod.GET)
	public String present(Long id,HttpServletRequest request, ModelMap model) {
		Website web = SiteUtils.getWeb(request);
		if(id!=null&&giftMng.findById(id)!=null){
			model.addAttribute("gift", giftMng.findById(id));
		}else {
			return FrontHelper.pageNotFound(web, model, request);
		}
		ShopFrontHelper.setCommonData(request, model, web, 1);
		return web.getTplSys(GIFT, MessageResolver.getMessage(request,"tpl.present"));
	}
	
	@RequestMapping(value = "/fetchGift.jspx")
	public void fetchGift(Long giftId,Integer giftNumb,HttpServletRequest request, HttpServletResponse response, ModelMap model) throws JSONException {
		Website web = SiteUtils.getWeb(request);
		ShopMember member = MemberThread.get();
		JSONObject json=new JSONObject();
		User user = SiteUtils.getUser(request);
		if(member == null || member.getUsername()==null||user==null){
			json.put("status", 0);
		}else{
			Gift gift = giftMng.findById(giftId);
			if(giftNumb>gift.getGiftStock()){
				json.put("status", 2);
				json.put("error", "库存不足");
			}if((gift.getGiftScore()*(Long.parseLong(giftNumb.toString())))>member.getScore()){
				json.put("status", 2);
				json.put("error", "积分不足");
			}else{
				json.put("status", 1);
			}
	   }
		ShopFrontHelper.setCommonData(request, model, web, 1);
        ResponseUtils.renderJson(response, json.toString());	
	}

	//选择收货地址、付款方式、配送方式
	@RequestMapping(value = "/exchange.jspx")
	public String shippingInput(Long id,Integer count,HttpServletRequest request,
			HttpServletResponse response, ModelMap model) {
		Website web = SiteUtils.getWeb(request);
		ShopMember member = MemberThread.get();
		User user = SiteUtils.getUser(request);
		if(member == null || member.getUsername()==null||user==null){
			return "redirect:login.jspx";
		}
		WebErrors errors = validateGiftView(id,request);
		if (errors.hasErrors()) {
			return FrontHelper.showError(errors, web, model, request);
		}
		Gift gift = giftMng.findById(id);
		if(count>gift.getGiftStock()){
			return  FrontUtils.showMessage(request, model,"库存不足");
		}
		if((gift.getGiftScore()*(Long.parseLong(count.toString())))>member.getScore()){
			return  FrontUtils.showMessage(request, model,"积分不足");
		}
		model.addAttribute("gift", gift);
		model.addAttribute("count", count);
		model.addAttribute("totalScore", gift.getGiftScore()*(Long.parseLong(count.toString())));
		//会员地址
		List<ShopMemberAddress> smalist = shopMemberAddressMng.getList(member.getId());
		model.addAttribute("smalist", smalist);
		//所处省份
		List<Address> plist=addressMng.getListById(null);
		model.addAttribute("plist", plist);
		ShopFrontHelper.setCommonData(request, model, web, 1);
		return web.getTplSys(GIFT, MessageResolver.getMessage(request,
				"tpl.exchange"));
	}
	
	
	@RequestMapping(value = "/create_exchange.jspx", method = RequestMethod.POST)
	public String createExchange(Long deliveryInfo,Long id,Integer count,HttpServletRequest request, ModelMap model) {
		Website web = SiteUtils.getWeb(request);
		ShopMember member = MemberThread.get();
		User user = SiteUtils.getUser(request);
		if(member == null || member.getUsername()==null||user==null){
			return "redirect:login.jspx";
		}
		WebErrors errors = validateGiftView(id,request);
		if (errors.hasErrors()) {
			return FrontHelper.showError(errors, web, model, request);
		}
		Gift gift = giftMng.findById(id);
		if(count>gift.getGiftStock()){
			return  FrontUtils.showMessage(request, model,"库存不足");
		}
		if((gift.getGiftScore()*(Long.parseLong(count.toString())))>member.getScore()){
			return  FrontUtils.showMessage(request, model,"积分不足");
		}
		ShopMemberAddress shopMemberAddress = shopMemberAddressMng.findById(deliveryInfo);
		giftExchangeMng.save(gift, shopMemberAddress, member, count);
		return FrontUtils.showMessage(request, model,"兑换成功");
	}
	
	private WebErrors validateGiftView(Long id,HttpServletRequest request) {
		WebErrors errors = WebErrors.create(request);
		if (errors.ifNull(id, "id")) {
			return errors;
		}
		Gift gift = giftMng.findById(id);
		if (errors.ifNotExist(gift,Gift.class, id)) {
			return errors;
		}
		return errors;
	}

	@Autowired
	private GiftMng giftMng;
	@Autowired
	private ShopMemberAddressMng shopMemberAddressMng;
	@Autowired
	private AddressMng addressMng;
	@Autowired
	private GiftExchangeMng giftExchangeMng;
}
