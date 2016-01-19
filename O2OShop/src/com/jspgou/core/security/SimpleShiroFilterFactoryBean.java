/**
 * 吉林省艾利特信息技术有限公司
 * 进销存管理系统
 * @date 2015年5月14日
 * @author liuwang
 * @version 1.0
 */
package com.jspgou.core.security;

import org.apache.shiro.spring.web.ShiroFilterFactoryBean;

public class SimpleShiroFilterFactoryBean extends ShiroFilterFactoryBean {
    private String waploginUrl;

	public String getWaploginUrl() {
		return waploginUrl;
	}

	public void setWaploginUrl(String waploginUrl) {
		this.waploginUrl = waploginUrl;
	}
	

}
