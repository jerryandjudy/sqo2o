package com.jspgou.core.web;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.BeanFactoryUtils;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import com.jspgou.core.entity.Website;
import com.jspgou.core.manager.WebsiteMng;
/**
* This class should preserve.
* @preserve
*/
public class WebsiteFilter implements Filter{

    public WebsiteFilter(){}

    public void doFilter(ServletRequest servletrequest, ServletResponse servletresponse, 
    		FilterChain filterchain)throws IOException, ServletException{
        HttpServletRequest request = (HttpServletRequest)servletrequest;
        HttpServletResponse response = (HttpServletResponse)servletresponse;
//        System.out.println("uri:"+request.getRequestURI());
//        System.out.println("url:"+request.getRequestURL());
        String s = request.getServerName();
        Website website = websiteMng.getWebsite(s);
        if(website != null){
        	request.setAttribute("_web_key", website);
            String s1 = website.getBaseDomain();
            if(!StringUtils.isBlank(s1)){
            	request.setAttribute("_base_domain_key", s1);
            }
            filterchain.doFilter(request, response);
        }else{
        	response.sendError(404, "domain '"+s+"' not found.");
        }
    }

    public void init(FilterConfig filterconfig) throws ServletException {
        WebApplicationContext webapplicationcontext = WebApplicationContextUtils.getWebApplicationContext(filterconfig.getServletContext());
        websiteMng = (WebsiteMng)BeanFactoryUtils.beanOfTypeIncludingAncestors(webapplicationcontext, WebsiteMng.class, true, false);
    }

    public void destroy(){ }

    private WebsiteMng websiteMng;
}
