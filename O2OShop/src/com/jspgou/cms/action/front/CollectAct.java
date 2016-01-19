package com.jspgou.cms.action.front;

import static com.jspgou.cms.Constants.MEMBER_SYS;

import java.util.List;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import com.jspgou.cms.entity.Collect;
import com.jspgou.cms.entity.ShopMember;
import com.jspgou.cms.manager.CollectMng;
import com.jspgou.cms.web.ShopFrontHelper;
import com.jspgou.cms.web.SiteUtils;
import com.jspgou.cms.web.threadvariable.MemberThread;
import com.jspgou.common.web.ResponseUtils;
import com.jspgou.common.web.springmvc.MessageResolver;
import com.jspgou.core.entity.User;
import com.jspgou.core.entity.Website;
import com.jspgou.core.web.front.URLHelper;

/**
* This class should preserve.
* @preserve
*/
@Controller
public class CollectAct {
	
	//收藏夹
	private static final String MY_COLLECT = "tpl.mycollect";

	@RequestMapping(value = "/collect/add_to_collect.jspx")
	public String addToCollect(Long productId,Long productFashId, Boolean isAdd,
			HttpServletRequest request, HttpServletResponse response,
			ModelMap model) throws JSONException {
		ShopMember member = MemberThread.get();
		User user = SiteUtils.getUser(request);
		if(member == null || member.getUsername()==null||user==null){
			ResponseUtils.renderJson(response, new JSONObject().put("status", 0).toString());
			return null;
		}
		Collect collect =null;
		if(productFashId==null){
			List<Collect> clist=collectMng.findByProductId(productId);
			if (clist.size()>0) {
				ResponseUtils.renderJson(response, new JSONObject().put("status", 2).toString());
				return null;
			}
			collect = collectMng.save(member,productId,null);
		}else{
			collect = collectMng.findByProductFashionId(productFashId);
				if (collect != null) {
					ResponseUtils.renderJson(response, new JSONObject().put("status", 2).toString());
					return null;
				}
				collect = collectMng.save(member,productId,productFashId);
		}
		ResponseUtils.renderJson(response, new JSONObject().put("status", 1).toString());
		return null;
	}

	@RequestMapping(value = "/collect/mycollect*.jspx")
	public String myCollect(HttpServletRequest request,ModelMap model) {
		Website web = SiteUtils.getWeb(request);
		ShopMember member = MemberThread.get();
		User user = SiteUtils.getUser(request);
		if(member == null || member.getUsername()==null||user==null){
			return "";
		}
	    Integer pageNo = URLHelper.getPageNo(request);
		model.addAttribute("historyProductIds", "");
		Cookie[] cookie = request.getCookies();
		for(int i=0;i<cookie.length;i++){
			if(cookie[i].getName().equals("shop_record")){
				String str = cookie[i].getValue();
				model.addAttribute("historyProductIds", str);
				break;
			}
		}
		ShopFrontHelper.setCommonData(request, model, web, 1);
		ShopFrontHelper.setDynamicPageData(request, model, web, "","mycollect", ".jspx", pageNo);
		return web.getTplSys(MEMBER_SYS, MessageResolver.getMessage(request,MY_COLLECT));
	}
	
	@RequestMapping(value = "/collect/delCollect.jspx")
	public String delCollect(Integer[] collectIds,HttpServletResponse response,ModelMap model){
		ShopMember member = MemberThread.get();
		if(member == null){
			return "";
		}
		collectMng.deleteByIds(collectIds);
		ResponseUtils.renderJson(response, "删除成功!");
		return null;
	}

	@Autowired
	private CollectMng collectMng;
}
