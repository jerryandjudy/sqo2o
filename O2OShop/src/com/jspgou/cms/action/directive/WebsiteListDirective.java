package com.jspgou.cms.action.directive;

import static com.jspgou.cms.Constants.SHOP_SYS;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.jspgou.cms.action.directive.abs.WebDirective;
import com.jspgou.common.web.freemarker.DirectiveUtils;
import com.jspgou.core.entity.Website;
import com.jspgou.core.manager.WebsiteMng;

import freemarker.core.Environment;
import freemarker.template.ObjectWrapper;
import freemarker.template.TemplateDirectiveBody;
import freemarker.template.TemplateException;
import freemarker.template.TemplateModel;

/**
 * 站点list标签
 */
public class WebsiteListDirective extends WebDirective {
	public static final String TPL_NAME = "WebsiteList";
	@SuppressWarnings("unchecked")
	public void execute(Environment env, Map params, TemplateModel[] loopVars,
			TemplateDirectiveBody body) throws TemplateException, IOException {
		Map<String, TemplateModel> paramWrap = new HashMap<String, TemplateModel>(
				params);
		ServletRequestAttributes attr = (ServletRequestAttributes) RequestContextHolder.currentRequestAttributes();
		HttpServletRequest request = attr.getRequest();
		Website web = getWeb(env, params, websiteMng);
		Website gpssite = gpssite(request);
		List<Website> list=new ArrayList();
		if(gpssite!=null){
			Website newgpssite=new Website();
			BeanUtils.copyProperties(gpssite, newgpssite);;
			newgpssite.setName(gpssite.getName()+"(定位站点)");
			list.add(newgpssite);
		}
		List<Website> list1 = websiteMng.getAllNotCloseList();
		if(list1!=null && list1.size()>0){
			for(Website  site :list1){
				if(gpssite!=null && gpssite.getId()==site.getId()){
				}else {
					list.add(site);
				}
			}
		}
		paramWrap.put(OUT_LIST, ObjectWrapper.DEFAULT_WRAPPER.wrap(list));
		Map<String, TemplateModel> origMap = DirectiveUtils
				.addParamsToVariable(env, paramWrap);
		if (isInvokeTpl(params)) {
			includeTpl(SHOP_SYS, TPL_NAME, web, params, env);
		} else {
			renderBody(env, loopVars, body);
		}
		DirectiveUtils.removeParamsFromVariable(env, paramWrap, origMap);
	}
	private void renderBody(Environment env, TemplateModel[] loopVars,
			TemplateDirectiveBody body) throws TemplateException, IOException {
		body.render(env.getOut());
	}
	/**
	 * 获取定位信息站点
	 * 
	 * @param request
	 * @param model
	 * @return
	 */
	public Website gpssite(HttpServletRequest request) {
		String ip = request.getRemoteAddr();
		// 创建HttpClient实例     
//		DefaultHttpClient httpclient = new DefaultHttpClient();  
		CloseableHttpClient client = HttpClientBuilder.create().build(); 
		// 创建Get方法实例     
//		HttpGet httpgets = new HttpGet("http://api.map.baidu.com/location/ip?ak=oaugvH2uGm7FOtGHGt4g4MTk");    
		HttpGet httpgets = new HttpGet("http://api.map.baidu.com/location/ip?ak=oaugvH2uGm7FOtGHGt4g4MTk&ip="+ip+"&coor=bd09ll");    
		HttpResponse response = null;
		Object  city = null;
		Website website =null;
		try {
			response = client.execute(httpgets);
			HttpEntity entity = response.getEntity();    
			if (entity != null) {    
				InputStream instreams = null;
				instreams = entity.getContent();
				String str = convertStreamToString(instreams); 
				JSONObject a = null;
				a = new JSONObject(str);
				String status=a.get("status").toString();
				if("0".equals(status)){
				city= ((JSONObject)((JSONObject)a.get("content")).get("address_detail")).get("city");
				String res="";
				if(city!=null){
					res=city.toString();
				}
			    website = websiteMng.findByName(res);
				}
			}
			httpgets.abort();  
		} catch (ClientProtocolException e) {
			// TODO Auto-generated catch block
//			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
//			e.printStackTrace();
		} catch (JSONException e) {
			// TODO Auto-generated catch block
//			e.printStackTrace();
		}  
	
		
			
			// Do not need the rest    
			  
		return website;
		
	}
	
	public static String convertStreamToString(InputStream is) {      
	       BufferedReader reader = null;
		try {
			reader = new BufferedReader(new InputStreamReader(is,"utf-8"));
		} catch (UnsupportedEncodingException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}      
	        StringBuilder sb = new StringBuilder();      
	      
	        String line = null;      
	        try {      
	            while ((line = reader.readLine()) != null) {  
	                sb.append(line + "\n");      
	            }      
	        } catch (IOException e) {      
	            e.printStackTrace();      
	        } finally {      
	            try {      
	                is.close();      
	           } catch (IOException e) {      
	               e.printStackTrace();      
	            }      
	        }      
	        return sb.toString();      
	    }  
	@Autowired
	private WebsiteMng websiteMng;
}
