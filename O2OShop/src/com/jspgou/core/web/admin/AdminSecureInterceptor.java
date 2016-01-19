package com.jspgou.core.web.admin;

import java.io.IOException;
import java.util.Iterator;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.jspgou.cms.entity.ShopAdmin;
import com.jspgou.cms.web.threadvariable.AdminThread;
/**
* This class should preserve.
* @preserve
*/
public class AdminSecureInterceptor extends HandlerInterceptorAdapter{
	
    private boolean develop = false;

    @SuppressWarnings("unchecked")
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response,
    		 Object obj) throws Exception{
    	Subject subject = SecurityUtils.getSubject();
    	ShopAdmin admin = AdminThread.get();
        if(develop){
            return true;
        }
        Set set = (Set)request.getAttribute("_permission_key");
        String s = getURI(request.getRequestURI(), request.getContextPath());
        if(s.equals("/login.do")){
            return true;
        }
        if(admin!=null&&admin.getAdmin().isSuper()){
        	return true;
        }
        if(set == null){
            redirectToLogin(request, response);
            return false;
        }
        Iterator iterator = set.iterator();
        while (iterator.hasNext()){
          String str2 = (String)iterator.next();
          if ((s.equals(str2)) || (s.startsWith(str2)))
            return true;
        }
        response.sendError(403);
        return false;
    }

    public static String getURI(String s, String s1)
        throws IllegalStateException{
        int i = 0;
        int j = 0;
        int k = 2;
        if(!StringUtils.isBlank(s1)){
            k++;
        }
        for(; j < k && i != -1; j++){
            i = s.indexOf('/', i + 1);
        }
        if(i <= 0){
            throw new IllegalStateException("admin access path not like '/nhaocang/admin/...' pattern: "+s);
        }else{
            return s.substring(i);
        }
    }

    private void redirectToLogin(HttpServletRequest request, HttpServletResponse response)
            throws IOException {
        String s = request.getRequestURI();
        int i = StringUtils.countMatches(s, "/");
        StringBuilder stringbuilder = new StringBuilder();
        int j = 3;
        if(!StringUtils.isBlank(request.getContextPath())){
            j++;
        }
        for(; j < i; j++){
            stringbuilder.append("../");
        }
        stringbuilder.append("login.do");
        response.sendRedirect(stringbuilder.toString());
    }

    public void setDevelop(boolean develop){
        this.develop = develop;
    }

}
