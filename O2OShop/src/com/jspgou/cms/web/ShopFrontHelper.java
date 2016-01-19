package com.jspgou.cms.web;

import static com.jspgou.cms.action.directive.abs.WebDirective.CONFIG;
import static com.jspgou.cms.action.directive.abs.WebDirective.GROUP;
import static com.jspgou.cms.action.directive.abs.WebDirective.MEMBER;
import static com.jspgou.cms.Constants.HREF;
import static com.jspgou.cms.Constants.HREF_FORMER;
import static com.jspgou.cms.Constants.HREF_LATTER;
import static com.jspgou.cms.Constants.PAGE_NO;
import static com.jspgou.cms.Constants.REQUEST_SHOP_CONFIG_KEY;
import static com.jspgou.core.web.Constants.REQUEST_MEMBER_KEY;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.ui.ModelMap;
import org.springframework.web.util.UrlPathHelper;

import com.jspgou.core.entity.User;
import com.jspgou.core.entity.Website;
import com.jspgou.core.web.CmsThreadVariable;
import com.jspgou.core.web.front.FrontHelper;
import com.jspgou.core.web.front.URLHelper;
import com.jspgou.core.web.front.URLHelper.PageInfo;
import com.jspgou.cms.entity.ShopMember;
import com.jspgou.cms.web.threadvariable.GroupThread;
import com.jspgou.cms.web.threadvariable.MemberThread;

/**
* This class should preserve.
* @preserve
*/
public abstract class ShopFrontHelper {
	/**
	 * 用户
	 */
	public static final String USER = "user";
	/**
	 * 设置动态页面数据
	 * 
	 * @param request
	 *            HTTP请求
	 * @param model
	 *            数据模型
	 * @param web
	 *            站点对象
	 * @param location
	 *            当前url地址
	 * @param urlPrefix
	 *            分页前缀
	 * @param urlSuffix
	 *            分页后缀
	 * @param pageNo
	 *            页码
	 */
	public static void setDynamicPageData(HttpServletRequest request,
			ModelMap model, Website web, String location, String urlPrefix,
			String urlSuffix, int pageNo) {
		FrontHelper.setDynamicPageData(request, model, web, location,
				urlPrefix, urlSuffix, pageNo);
		setShopDate(request, model);
	}
	
	/**
	 * 为前台模板设置分页相关数据
	 * 
	 * @param request
	 * @param map
	 */
	public static void frontPageData(HttpServletRequest request,
			Map<String, Object> map) {
		int pageNo = URLHelper.getPageNo(request);
		PageInfo info = URLHelper.getPageInfo(request);
		String href = info.getHref();
		String hrefFormer = info.getHrefFormer();
		String hrefLatter = info.getHrefLatter();
		frontPageData(pageNo, href, hrefFormer, hrefLatter, map);
	}
	
	/**
	 * 为前台模板设置分页相关数据
	 * 
	 * @param request
	 * @param map
	 */
	public static void frontPage(HttpServletRequest request,
			Map<String, Object> map) {
		int pageNo = URLHelper.getPageNo(request);
		PageInfo info = URLHelper.getPageInfo(request);
		String href = info.getHref();
		String hrefFormer = info.getHrefFormer();
		String hrefLatter = info.getHrefLatter();
		frontPageData(pageNo, href, hrefFormer, hrefLatter, map);
	}
	
	/**
	 * 为前台模板设置分页相关数据
	 * 
	 * @param pageNo
	 * @param href
	 * @param urlFormer
	 * @param urlLatter
	 * @param map
	 */
	public static void frontPageData(int pageNo, String href,
			String hrefFormer, String hrefLatter, Map<String, Object> map) {
		map.put(PAGE_NO, pageNo);
		map.put(HREF, href);
		map.put(HREF_FORMER, hrefFormer);
		map.put(HREF_LATTER, hrefLatter);
	}

	/**
	 * 设置公共数据
	 * 
	 * @param request
	 * @param model
	 * @param web
	 * @param pageNo
	 */
	public static void setCommonData(HttpServletRequest request,
			ModelMap model, Website web, int pageNo) {
		User user = SiteUtils.getUser(request);
		ShopMember member=MemberThread.get();
//		ShopMember member=(ShopMember) request.getAttribute(REQUEST_MEMBER_KEY);
		if(user!=null&&member != null && member.getId()!=null){
			model.put(USER, user);
		}
		if(request.getSession().getAttribute("defsite")!=null){
			model.put("defsite", request.getSession().getAttribute("defsite"));
		}
		if(request.getSession().getAttribute("defstreet")!=null){
			model.put("defstreet", request.getSession().getAttribute("defstreet"));
		}
		if(request.getSession().getAttribute("imei")!=null){
			model.put("imei", request.getSession().getAttribute("imei"));
		}
		FrontHelper.setCommonData(request, model, web, pageNo);
		setShopDate(request, model);
	}
	
	
	/**
	 * 设置公共数据
	 * 
	 * @param request
	 * @param model
	 * @param web
	 * @param pageNo
	 */
	public static void setCommon(HttpServletRequest request,
			ModelMap model, Website web) {
		User user = SiteUtils.getUser(request);
		ShopMember member=(ShopMember) request.getAttribute(REQUEST_MEMBER_KEY);
		if (member != null) {
			model.addAttribute(MEMBER, member);
			model.addAttribute(GROUP, GroupThread.get());
		}
		if(user!=null){
			model.put(USER, user);
		}
		if(request.getSession().getAttribute("defsite")!=null){
			model.put("defsite", request.getSession().getAttribute("defsite"));
		}
		if(request.getSession().getAttribute("defstreet")!=null){
			model.put("defstreet", request.getSession().getAttribute("defstreet"));
		}
		if(request.getSession().getAttribute("imei")!=null){
			model.put("imei", request.getSession().getAttribute("imei"));
		}
		model.addAttribute(CONFIG, request.getAttribute(REQUEST_SHOP_CONFIG_KEY));
		FrontHelper.setCommon(request, model, web);
	}

	/**
	 * 设置商城数据
	 * 
	 * @param request
	 * @param model
	 */
	public static void setShopDate(HttpServletRequest request, ModelMap model) {
		model.addAttribute(CONFIG, request.getAttribute(REQUEST_SHOP_CONFIG_KEY));
		String location = getLocation(request);//获得完整路径（http://localhost:...）wang ze wu
		model.addAttribute("location", location);
		ShopMember member = MemberThread.get();
		if (member != null&&member.getId()>0) {
			model.addAttribute(MEMBER, member);
			model.addAttribute(GROUP, GroupThread.get());
		}
	}
	
	public static String getLocation(HttpServletRequest request) {
		UrlPathHelper helper = new UrlPathHelper();
		StringBuffer buff = request.getRequestURL();
		String uri = request.getRequestURI();
		String origUri = helper.getOriginatingRequestUri(request);
		buff.replace(buff.length() - uri.length(), buff.length(), origUri);
		String queryString = helper.getOriginatingQueryString(request);
		if (queryString != null) {
			buff.append("?").append(queryString);
		}
		return buff.toString();
	}

}
