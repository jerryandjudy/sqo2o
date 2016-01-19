package com.jspgou.cms.web;

import static com.jspgou.cms.Constants.REQUEST_SHOP_CONFIG_KEY;

import javax.servlet.http.HttpServletRequest;

import com.jspgou.cms.entity.ShopConfig;

/**
* This class should preserve.
* @preserve
*/
public class SiteUtils extends com.jspgou.core.web.SiteUtils {
	public static ShopConfig getConfig(HttpServletRequest request) {
		ShopConfig config = (ShopConfig) request.getAttribute(REQUEST_SHOP_CONFIG_KEY);
		if (config == null) {
			throw new IllegalStateException(
					"Config not found in Request Attribute '" + REQUEST_SHOP_CONFIG_KEY
							+ "'");
		}
		return config;
	}
}
