package com.jspgou.cms.action.front;

import static com.jspgou.cms.Constants.SHOP_SYS;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.context.ServletContextAware;

import com.jspgou.common.web.RequestUtils;
import com.jspgou.common.web.springmvc.MessageResolver;
import com.jspgou.core.entity.Website;
import com.jspgou.core.web.front.URLHelper;
import com.jspgou.core.web.front.URLHelper.URLInfo;
import com.jspgou.cms.entity.Exended;
import com.jspgou.cms.manager.AddressMng;
import com.jspgou.cms.manager.BrandMng;
import com.jspgou.cms.manager.ExendedMng;
import com.jspgou.cms.manager.KeyWordMng;
import com.jspgou.cms.web.ShopFrontHelper;
import com.jspgou.cms.web.SiteUtils;

/**
* This class should preserve.
* @preserve
*/
@Controller
public class SearchAct implements ServletContextAware {
	private static final String SEARCH_INPUT = "tpl.searchInput";
	private static final String SEARCH_RESULT = "tpl.searchResult";

	@RequestMapping(value = "/search*.jspx", method = RequestMethod.GET)
	public String search(HttpServletRequest request,ModelMap model){
		Website web = SiteUtils.getWeb(request);
		String url = request.getRequestURL().toString();
		URLInfo info = URLHelper.getURLInfo(url, request.getContextPath());
		ShopFrontHelper.setDynamicPageData(request, model, web, url, info.getUrlPrefix(),info.getUrlSuffix(), info.getPageNo());
		Exended exended = exendedMng.findById(1l);
		List<Exended> exendeds = new ArrayList(); 
		if(exended!=null&&exended.getId()!=null){
		exendeds.add(exended);
		}
		Map map= new HashMap(); 
		Map map1= new HashMap(); 
		int num = exendeds.size();
		for(int i=0;i<num;i++){
			map.put(exendeds.get(i).getId().toString(), exendeds.get(i).getItems());
			map1.put(exendeds.get(i).getId().toString(), exendeds.get(i));
		}
		model.addAttribute("brandId",getBrandId(request));
		model.addAttribute("map", map);
		model.addAttribute("map1", map1);
		model.addAttribute("fields", getNames(request));
		model.addAttribute("zhis", getValues(request));
		model.addAttribute("area", getArea(request));
		String areaName="请选择";
		if(getArea(request)!=null&&getArea(request).length()>0){
			String[] areas=getArea(request).split(",");
			if(areas!=null&&areas.length==4){
			areaName=addressMng.findById(Long.parseLong(areas[0])).getName()+addressMng.findById(Long.parseLong(areas[1])).getName()+addressMng.findById(Long.parseLong(areas[2])).getName()+addressMng.findById(Long.parseLong(areas[3])).getName();
			}
			}
		model.addAttribute("areaName",areaName );
		model.addAttribute("pageSize", getpageSize(request));
		model.addAttribute("names", getName(request));
		model.addAttribute("values", getValue(request));
		model.addAttribute("isShow", getIsShow(request));
		model.addAttribute("orderBy",getOrderBy(request));
		model.putAll(RequestUtils.getQueryParams(request));
		ShopFrontHelper.setCommon(request, model, web);
		ShopFrontHelper.frontPage(request, model);
		String q = RequestUtils.getQueryParam(request, "q");
		q=StringUtils.trim(q);	
		q=parseKeywords(q);
		model.addAttribute("q",q);
		String ctgId = RequestUtils.getQueryParam(request, "ctgId");
		ctgId=StringUtils.trim(ctgId);
		if (StringUtils.isBlank(q) && StringUtils.isBlank(ctgId)) {
			model.remove("q");
			model.remove("ctgId");
			return web.getTplSys(SHOP_SYS, MessageResolver.getMessage(request,SEARCH_INPUT));
		} else {
			model.addAttribute("q", q);
			if(StringUtils.isBlank(ctgId)){
				model.addAttribute("ctgId",null);
			}else{
				model.addAttribute("ctgId", Integer.valueOf(ctgId));
			}
			keyWordMng.save(q);
			return web.getTplSys(SHOP_SYS, MessageResolver.getMessage(request,SEARCH_RESULT));
		}
	}
	public Integer getpageSize(HttpServletRequest request){
		String pageSize = RequestUtils.getQueryParam(request, "pageSize");
		Integer pagesize = null;
		if (!StringUtils.isBlank(pageSize)) {
			pagesize = Integer.parseInt(pageSize);
		}
		if(pagesize==null){
			pagesize = 12;
		}
		return pagesize;
	}
	public String getArea(HttpServletRequest request){
		String areas= RequestUtils.getQueryParam(request, "area");
		String area = null;
		if (!StringUtils.isBlank(areas)) {
			area=areas.replace("#none", "");
		}
		return area;
	}
	public String encodeFilename(HttpServletRequest request,String fileName)  {
		  String agent = request.getHeader("USER-AGENT");
		  try {
		         // IE
		          if (null != agent && -1 != agent.indexOf("MSIE")) {
		                    fileName = URLEncoder.encode(fileName, "UTF8");
		           }else {
		        	   fileName = new String(fileName.getBytes("utf-8"),"iso-8859-1");
		           }
		  } catch (UnsupportedEncodingException e) {
			  e.printStackTrace();
		  }
		  return fileName;
	 }
	
	
	public static String parseKeywords(String q){
		try {
			String regular = "[\\+\\-\\&\\|\\!\\(\\)\\{\\}\\[\\]\\^\\~\\*\\?\\:\\\\]";
			Pattern p = Pattern.compile(regular);
			Matcher m = p.matcher(q);
			String src = null;
			while (m.find()) {
				src = m.group();
				q = q.replaceAll("\\" + src, ("\\\\" + src));
			}
			q = q.replaceAll("AND", "and").replaceAll("OR", "or").replace("NOT", "not");
		} catch (Exception e) {
		}
		return  q;
	}

	private ServletContext servletContext;

	public void setServletContext(ServletContext servletContext) {
		this.servletContext = servletContext;
	}
	

	public Integer getIsShow(HttpServletRequest request){
		String isShow = RequestUtils.getQueryParam(request, "isShow");
		Integer isshow = null;
		if (!StringUtils.isBlank(isShow)) {
			isshow = Integer.parseInt(isShow);
		}
		if(isshow==null){
			isshow = 0;
		}
		return isshow;
	}
	
	public Integer getOrderBy(HttpServletRequest request){
		String orderBy = RequestUtils.getQueryParam(request, "orderBy");
		Integer orderby = null;
		if (!StringUtils.isBlank(orderBy)) {
			orderby = Integer.parseInt(orderBy);
		}
		if(orderby==null){
			orderby = 0;
		}
		return orderby;
	}
	
	public String[] getNames(HttpServletRequest request){
		Map<String, String> attr = RequestUtils.getRequestMap(request, "exended_");
		List li=new ArrayList(attr.keySet());
	    String name= "";
	    for(int i=0;i<li.size();i++){
	    	if(i+1==li.size()){
	    		name+=(String) li.get(i);
	    	}else{
	    		name+=(String) li.get(i)+",";
	    	}
	    }
	    String[] names= StringUtils.split(name, ',');
		return names;
	}
	
	public String[] getValues(HttpServletRequest request){
		Map<String, String> attr = RequestUtils.getRequestMap(request, "exended_");
		List li=new ArrayList(attr.keySet());
	    String value= "";
	    for(int i=0;i<li.size();i++){
	    	if(i+1==li.size()){
	    		if(StringUtils.isBlank(attr.get(li.get(i)))){
	    			value+="0";
	    		}else{
	    			value+=attr.get(li.get(i));
	    		}
	    	}else{
	    		if(StringUtils.isBlank(attr.get(li.get(i)))){
	    			value+="0,";
	    		}else{
	    			value+=attr.get(li.get(i))+",";
	    		}
	    	}
	    }
		String[] values= StringUtils.split(value, ',');
		return values;
	}
	
	
	public String getName(HttpServletRequest request){
		Map<String, String> attr = RequestUtils.getRequestMap(request, "exended_");
		List li=new ArrayList(attr.keySet());
	    String name= "";
	    for(int i=0;i<li.size();i++){
	    	if(i+1==li.size()){
	    		name+=(String) li.get(i);
	    	}else{
	    		name+=(String) li.get(i)+",";
	    	}
	    }
	    
		return name;
	}
	
	public String getValue(HttpServletRequest request){
		Map<String, String> attr = RequestUtils.getRequestMap(request, "exended_");
		List li=new ArrayList(attr.keySet());
	    String value= "";
	    for(int i=0;i<li.size();i++){
	    	if(i+1==li.size()){
	    		if(StringUtils.isBlank(attr.get(li.get(i)))){
	    			value+="0";
	    		}else{
	    			value+=attr.get(li.get(i));
	    		}
	    	}else{
	    		if(StringUtils.isBlank(attr.get(li.get(i)))){
	    			value+="0,";
	    		}else{
	    			value+=attr.get(li.get(i))+",";
	    		}
	    	}
	    }
		return value;
	}
	
	public Integer getBrandId(HttpServletRequest request){
		String brandId = RequestUtils.getQueryParam(request, "brandId");
		Integer id = null;
		if (!StringUtils.isBlank(brandId)) {
			id = Integer.parseInt(brandId);
		}
		if(id==null){
			id = 0;
		}
		return id;
	}
	
	
	@Autowired
	private KeyWordMng keyWordMng;
	@Autowired
	private ExendedMng exendedMng;
	@Autowired
	private AddressMng addressMng;
	@Autowired
	private BrandMng brandMng;

}
