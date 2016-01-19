package com.jspgou.core.web.front;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.BeanFactoryUtils;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import com.jspgou.core.entity.Website;
import com.jspgou.core.manager.WebsiteMng;

/**
* This class should preserve.
* @preserve
*/
public class WildcardServlet extends HttpServlet{
	
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public static String DYNAMIC = "jeedynamic";
    public static String URL = "_dynamic_url";
    public static String URI_INFO = "_dynamic_uri_info";
    public static String QUERY_STRING = "_dynamic_query_string";
    private String dynamic = DYNAMIC;
    private WebsiteMng websiteMng;

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException{
        String s = request.getServerName();
        Website website = websiteMng.getWebsite(s);
        if(website != null){
            String s1 = request.getRequestURL().toString();
            request.setAttribute(URL, s1);
            request.setAttribute(URI_INFO, URLHelper.getURLInfo(s1, request.getContextPath()));
            request.setAttribute(QUERY_STRING, request.getQueryString());
            //每个地址里面都要加jspgou
//          String s2 = website.getCurrentSystem();
//          String s3 = (new StringBuilder("/")).append(s2).append("/").append(dynamic).toString();
            String s3 = (new StringBuilder("/")).append(dynamic).toString();
            RequestDispatcher requestdispatcher = request.getRequestDispatcher(s3);
            requestdispatcher.forward(request, response);
        } else{
            throw new IllegalStateException("no website found");
        }
    }

    public void init() throws ServletException{
       WebApplicationContext webapplicationcontext = WebApplicationContextUtils.getWebApplicationContext(getServletContext());
        websiteMng = (WebsiteMng)BeanFactoryUtils.beanOfTypeIncludingAncestors(webapplicationcontext,WebsiteMng.class, true, false);
        String s = getServletConfig().getInitParameter("dynamic");
        if(!StringUtils.isBlank(s)){
        	dynamic = s;
        } 
    }

}
