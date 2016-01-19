package com.jspgou.core.web;

import static com.jspgou.cms.Constants.REQUEST_SHOP_CONFIG_KEY;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.jspgou.cms.entity.ShopConfig;
import com.jspgou.core.entity.User;
import com.jspgou.core.entity.Website;
/**
* This class should preserve.
* @preserve
*/
public abstract class SiteUtils{
	 /**
		 * 用户KEY
		 */
		public static final String USER_KEY = "_user_key";
		/**
		 * 站点KEY
		 */
		public static final String SITE_KEY = "_web_key";
		/**
		 * 站点list
		 */
		public static final String SITE_LIST = "_web_list";
		
    public static Website getWeb(HttpServletRequest request){
        Website website = (Website)request.getAttribute(SITE_KEY);
        if(website == null){
            throw new IllegalStateException("Webiste not found in Request Attribute '_web_key'");
        }else{
            return website;
        }
    }

    public static Long getWebId(HttpServletRequest request){
        return getWeb(request).getId();
    }
    public static List<Website> getSiteList(HttpServletRequest request){
    	return (List<Website>) request.getAttribute(SITE_LIST);
    }
   

	/**
	 * 设置站点
	 * 
	 * @param request
	 * @param site
	 */
	public static void setSite(HttpServletRequest request, Website site) {
		request.setAttribute(SITE_KEY, site);
	}
	/**
	 * 设置站点list
	 * 
	 * @param request
	 * @param site
	 */
	public static void setSiteList(HttpServletRequest request, List<Website> list) {
		request.setAttribute(SITE_LIST, list);
	}
	/**
	 * 设置shopConfig
	 * 
	 * @param request
	 * @param config
	 */
	public static void setConfig(HttpServletRequest request, ShopConfig config) {
		request.setAttribute(REQUEST_SHOP_CONFIG_KEY, config);
	}
	/**
	 * 获得用户
	 * 
	 * @param request
	 * @return
	 */
	public static User getUser(HttpServletRequest request) {
		return (User) request.getAttribute(USER_KEY);
	}
	/**
	 * 获得站点设置
	 * 
	 * @param request
	 * @return
	 */
	public static ShopConfig getConfig(HttpServletRequest request) {
		return (ShopConfig) request.getAttribute(REQUEST_SHOP_CONFIG_KEY);
	}

	

	/**
	 * 获得用户ID
	 * 
	 * @param request
	 * @return
	 */
	public static Integer getUserId(HttpServletRequest request) {
		User user = getUser(request);
		if (user != null) {
			return user.getId().intValue();
		} else {
			return null;
		}
	}

	/**
	 * 设置用户
	 * 
	 * @param request
	 * @param user
	 */
	public static void setUser(HttpServletRequest request, User user) {
		request.setAttribute(USER_KEY, user);
	}
	
}
