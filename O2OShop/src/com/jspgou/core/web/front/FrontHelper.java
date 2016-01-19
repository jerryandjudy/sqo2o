package com.jspgou.core.web.front;

import java.util.Iterator;
import java.util.Map;

import com.jspgou.common.web.RequestUtils;
import com.jspgou.common.web.springmvc.MessageResolver;
import com.jspgou.core.entity.Website;
import com.jspgou.core.web.WebErrors;
import javax.servlet.http.HttpServletRequest;
import org.apache.commons.lang.StringUtils;
import org.springframework.ui.ModelMap;
/**
* This class should preserve.
* @preserve
*/
public abstract class FrontHelper{
	
    public static final String TPL_PAGE_NOT_FOUND = "tpl.pageNotFound";
    public static final String TPL_SUCCESS_PAGE = "tpl.successPage";
    public static final String TPL_ERROR_PAGE = "tpl.errorPage";
    public static final String TPL_MESSAGE_PAGE = "tpl.messagePage";

    public static String pageNotFound(Website website, ModelMap modelmap, 
    		HttpServletRequest request){
        setCommonData(request, modelmap, website, 1);
        return website.getTplSys("common", MessageResolver.getMessage(request, TPL_PAGE_NOT_FOUND, new Object[0]));
    }

    public static String showSuccess(String s, String s1, Website website, 
    		ModelMap modelmap, HttpServletRequest request) {
        setCommonData(request, modelmap, website, 1);
        modelmap.addAttribute("message", s);
        if(!StringUtils.isBlank(s1))
            modelmap.addAttribute("backUrl", s1);
        return website.getTplSys("common", MessageResolver.getMessage(request, TPL_SUCCESS_PAGE, new Object[0]));
    }

    @SuppressWarnings("unchecked")
	public static String showError(WebErrors weberrors, Website website, 
    		ModelMap modelmap, HttpServletRequest request){
        setCommonData(request, modelmap, website, 1);
        weberrors.toModel(modelmap);
        return website.getTplSys("common", MessageResolver.getMessage(request,TPL_ERROR_PAGE, new Object[0]));
    }

    public static String showMessage(String s, Website website,
    		ModelMap modelmap, HttpServletRequest request){
        setCommonData(request, modelmap, website, 1);
        modelmap.addAttribute("message", s);
        return website.getTplSys("common", MessageResolver.getMessage(request, TPL_MESSAGE_PAGE, new Object[0]));
    }

    public static void setDynamicPageData(HttpServletRequest request, ModelMap model, 
    		Website web, String location, String urlPrefix, String urlSuffix, int pageNo){
    	model.addAttribute("urlPrefix", urlPrefix);
    	model.addAttribute("urlSuffix", urlSuffix);
        setCommonData(request, model, web, pageNo, location);
    }

    private static void setCommonData(HttpServletRequest request, ModelMap modelmap, 
    		Website website, int i, String s){
        modelmap.addAttribute("location", s);
        modelmap.addAttribute("web", website);
        String s1 = (String)request.getAttribute("_base_domain_key");
        if(s1 != null){
            modelmap.addAttribute("baseDomain", s1);
        }
        String s2 = (String)request.getAttribute("_login_url");
        if(s2 != null){
            modelmap.addAttribute("loginUrl", getLoginUrl(s2, request.getContextPath(), s));
        }
        modelmap.addAttribute("root", website.getResBaseUrl());
        modelmap.addAttribute("rootSys", website.getResSystemUrl());
        modelmap.addAttribute("pageNo", Integer.valueOf(i));
        modelmap.addAttribute("_start_time", request.getAttribute("_start_time"));
    }

    public static void setCommonData(HttpServletRequest request, 
    		ModelMap modelmap, Website website, int i){
    	 StringBuffer originalURL = request.getRequestURL();  
  	   Map parameters = request.getParameterMap();
  	   if (parameters != null && parameters.size() > 0) {
            originalURL.append("?");
            for (Iterator iter = parameters.keySet().iterator(); iter.hasNext();) {
                String key = (String) iter.next();
                String[] values = (String[]) parameters.get(key);
                for (int j = 0; j < values.length; j++) {
                    originalURL.append(key).append("=").append(values[j]).append("&");
                }
            }
        }
        setCommonData(request, modelmap, website, i, originalURL.toString());
    }
    
    public static void setCommon(HttpServletRequest request, 
    		ModelMap modelmap, Website website){
    	String location=RequestUtils.getLocation(request);
        setCommon(request, modelmap, website,location);
    }
    
    private static void setCommon(HttpServletRequest request, ModelMap modelmap, 
    		Website website, String location){
        modelmap.addAttribute("location", location);
        modelmap.addAttribute("web", website);
        String baseDomain = (String)request.getAttribute("_base_domain_key");
        if(baseDomain != null){
            modelmap.addAttribute("baseDomain", baseDomain);
        }
        String loginUrl = (String)request.getAttribute("_login_url");
        if(loginUrl != null){
            modelmap.addAttribute("loginUrl", getLoginUrl(loginUrl, request.getContextPath(), location));
        }
        modelmap.addAttribute("root", website.getResBaseUrl());
        modelmap.addAttribute("_start_time", request.getAttribute("_start_time"));
    }

  public static String getLoginUrl(String s, String s1, String s2){
        StringBuilder stringbuilder = new StringBuilder();
        if(!s.startsWith("http")){
            stringbuilder.append(s1);
            if(!s.startsWith("/")){
                stringbuilder.append("/");
            }
        }
        stringbuilder.append(s).append("?returnUrl=").append(s2);
        return stringbuilder.toString();
    }

    public static String getLoginUrl(HttpServletRequest request){
    	   StringBuffer originalURL = request.getRequestURL();  
    	   Map parameters = request.getParameterMap();
    	   if (parameters != null && parameters.size() > 0) {
              originalURL.append("?");
              for (Iterator iter = parameters.keySet().iterator(); iter.hasNext();) {
                  String key = (String) iter.next();
                  String[] values = (String[]) parameters.get(key);
                  for (int i = 0; i < values.length; i++) {
                      originalURL.append(key).append("=").append(values[i]).append("&");
                  }
              }
          }
        return getLoginUrl((String)request.getAttribute("_login_url"), "", originalURL.toString());
    }
}
