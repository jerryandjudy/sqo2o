package com.jspgou.core.web.front;

import com.jspgou.core.entity.Website;
import com.jspgou.core.web.SiteUtils;
import java.util.Locale;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.propertyeditors.LocaleEditor;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;
import org.springframework.web.servlet.support.RequestContextUtils;
/**
* This class should preserve.
* @preserve
*/
public class FrontLocaleInterceptor extends HandlerInterceptorAdapter{

    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, 
    		Object obj) throws ServletException{
        LocaleResolver localeresolver = RequestContextUtils.getLocaleResolver(request);
        if(localeresolver == null){
            throw new IllegalStateException("No LocaleResolver found: not in a DispatcherServlet request?");
        } else{
            Website website = SiteUtils.getWeb(request);
            String s = website.getLocaleFront();
            LocaleEditor localeeditor = new LocaleEditor();
            localeeditor.setAsText(s);
            localeresolver.setLocale(request, response, (Locale)localeeditor.getValue());
            return true;
        }
    }
}
