package com.jspgou.cms.action.front;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.security.SignatureException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Set;
import java.util.SortedMap;
import java.util.TreeMap;
import java.util.UUID;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.jspgou.cms.Alipay;
import com.jspgou.cms.action.member.SendMsg_webchinese;
import com.jspgou.cms.entity.Address;
import com.jspgou.cms.entity.Area;
import com.jspgou.cms.entity.Cart;
import com.jspgou.cms.entity.CartItem;
import com.jspgou.cms.entity.Category;
import com.jspgou.cms.entity.Message;
import com.jspgou.cms.entity.Order;
import com.jspgou.cms.entity.OrderReturn;
import com.jspgou.cms.entity.Payment;
import com.jspgou.cms.entity.PaymentPlugins;
import com.jspgou.cms.entity.PopularityItem;
import com.jspgou.cms.entity.ProductSite;
import com.jspgou.cms.entity.Shipping;
import com.jspgou.cms.entity.ShopChannel;
import com.jspgou.cms.entity.ShopConfig;
import com.jspgou.cms.entity.ShopDictionary;
import com.jspgou.cms.entity.ShopMember;
import com.jspgou.cms.entity.ShopMemberAddress;
import com.jspgou.cms.entity.ShopMessage;
import com.jspgou.cms.entity.SqOrder;
import com.jspgou.cms.entity.SqService;
import com.jspgou.cms.entity.ZfbWy;
import com.jspgou.cms.manager.AddressMng;
import com.jspgou.cms.manager.AreaMng;
import com.jspgou.cms.manager.BrandMng;
import com.jspgou.cms.manager.CartItemMng;
import com.jspgou.cms.manager.CartMng;
import com.jspgou.cms.manager.CategoryMng;
import com.jspgou.cms.manager.ExendedMng;
import com.jspgou.cms.manager.MemberCouponMng;
import com.jspgou.cms.manager.MessageMng;
import com.jspgou.cms.manager.OrderMng;
import com.jspgou.cms.manager.OrderReturnMng;
import com.jspgou.cms.manager.PaymentMng;
import com.jspgou.cms.manager.PaymentPluginsMng;
import com.jspgou.cms.manager.PopularityGroupMng;
import com.jspgou.cms.manager.PopularityItemMng;
import com.jspgou.cms.manager.ProductFashionMng;
import com.jspgou.cms.manager.ProductMng;
import com.jspgou.cms.manager.ProductSiteMng;
import com.jspgou.cms.manager.ProductStandardMng;
import com.jspgou.cms.manager.ShippingMng;
import com.jspgou.cms.manager.ShopArticleMng;
import com.jspgou.cms.manager.ShopChannelMng;
import com.jspgou.cms.manager.ShopDictionaryMng;
import com.jspgou.cms.manager.ShopMemberAddressMng;
import com.jspgou.cms.manager.ShopMemberMng;
import com.jspgou.cms.manager.ShopMessageMng;
import com.jspgou.cms.manager.SqOrderMng;
import com.jspgou.cms.manager.SqServiceMng;
import com.jspgou.cms.manager.StandardTypeMng;
import com.jspgou.cms.manager.ZfbWyMng;
import com.jspgou.cms.service.LoginSvc;
import com.jspgou.cms.service.ShoppingSvc;
import com.jspgou.cms.web.ShopFrontHelper;
import com.jspgou.cms.web.SiteUtils;
import com.jspgou.cms.web.threadvariable.AdminThread;
import com.jspgou.cms.web.threadvariable.GroupThread;
import com.jspgou.cms.web.threadvariable.MemberThread;
import com.jspgou.common.hibernate3.Updater;
import com.jspgou.common.security.BadCredentialsException;
import com.jspgou.common.security.UserNotAcitveException;
import com.jspgou.common.security.UsernameNotFoundException;
import com.jspgou.common.security.encoder.PwdEncoder;
import com.jspgou.common.web.RequestUtils;
import com.jspgou.common.web.ResponseUtils;
import com.jspgou.common.web.session.SessionProvider;
import com.jspgou.core.entity.User;
import com.jspgou.core.entity.Website;
import com.jspgou.core.manager.UserMng;
import com.jspgou.core.manager.WebsiteMng;
import com.jspgou.core.web.WebErrors;
import com.jspgou.core.web.front.FrontHelper;
import com.jspgou.core.web.front.URLHelper;
import com.octo.captcha.service.CaptchaService;
import com.octo.captcha.service.CaptchaServiceException;
import com.tencent.utils.GetWxOrderno;
import com.tencent.utils.RequestHandler;
import com.tencent.utils.WxPayCodeRes;

/**
 * wap动态系统Action
 * @author liuwang
 * This class should preserve.
 * @preserve
 */
@Controller
public class WapPageAct extends Alipay  {
	/**
	 * 首页
	 */
	public static final String INDEX = "/wap/index.html";
	public static final String LOGIN = "/wap/login.html";
	public static final String LOGINOUT = "/wap/loginout.html";
	public static final String PRODUCTSELF = "/wap/productself.html";
	public static final String SQFWSELF = "/wap/sqfwself.html";
	public static final String ARTICLESELF = "/wap/articleself.html";
	public static final String PRODUCTLIST = "/wap/productlist.html";
	public static final String PRODUCTPAGE = "/wap/productpage.html";
	public static final String SQFWLIST = "/wap/sqfwlist.html";
	public static final String TZGGLIST = "/wap/tzgglist.html";
	public static final String SQFWPAGE = "/wap/sqfwpage.html";
	public static final String TZGGPAGE = "/wap/tzggpage.html";
	public static final String CLOSE_INDEX = "/wap/wapcloseIndex.html";
	public static final String FORGOT_PASSWORD = "/wap/reset_pw.html";
	public static final String REGISTER = "/wap/register.html";
	public static final String CARTSELF = "/wap/cartself.html";
	public static final String ADDRADD = "/wap/newaddress.html";
	public static final String REPPRICE = "/wap/reprice.html";
	public static final String CHECKOUT = "/wap/checkout.html";
	public static final String ADDRLIST = "/wap/wapaddrlist.html";
	public static final String ORDERLIST = "/wap/waporderlist.html";
	public static final String ORDERFWLIST = "/wap/wapfworderlist.html";
	public static final String ORDERPAGE = "/wap/waporderpage.html";
	public static final String ORDERFWPAGE = "/wap/wapfworderpage.html";
	public static final String FWORDERSELF = "/wap/fworderself.html";
	public static final String CONECTUS = "/wap/conectUs.html";
	public static final String WAPMESSAGE = "/wap/wapmessage.html";
	public static final String ZFFS = "/wap/zffs.html";
	private static final String NODELIVERY_ORDER_RETURN = "/wap/sqtk.html";	
	private static final String NODELIVERY_RETURN_MONEY_SUCCESS = "/wap/sqtksuccess.html";
	private static final String MESSAGES = "/wap/messages.html";	
	/**
	 * 手机app首页
	 * 
	 * @param request
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/wap/wapindex.jspx", method = RequestMethod.GET)
	public String index(String imei,HttpServletRequest request,HttpServletResponse response, ModelMap model) {
		if(request.getParameter("sqbz")!=null || request.getParameter("code")!=null){//判断是否需要授权
			//System.out.println();
			if(request.getSession().getAttribute("access_token")==null && request.getParameter("code")==null){//授权
				PaymentPlugins paymentPlugins = paymentPluginsMng.findByCode("wxPay");
				model.put("appid", paymentPlugins.getSellerKey());
				return "/wap/sqpage.html";
			}else if (request.getSession().getAttribute("openid")==null && request.getParameter("code")!=null){//获取access_token
				PaymentPlugins paymentPlugins = paymentPluginsMng.findByCode("wxPay");
				String url="https://api.weixin.qq.com/sns/oauth2/access_token?appid="+paymentPlugins.getSellerKey()+"&secret="+paymentPlugins.getSellerEmail()+"&code="+request.getParameter("code")+"&grant_type=authorization_code";
				Map<String,String> map=GetWxOrderno.getOpenIdAndAccess_token(url);
			  if(!map.isEmpty()){
				request.getSession().setAttribute("openid", map.get("openid"));
				request.getSession().setAttribute("access_token", map.get("access_token"));
				request.getSession().setAttribute("refresh_token", map.get("refresh_token"));
			 }
			}
		 }
		Website web = SiteUtils.getWeb(request);
		List<Website> sitelist = websiteMng.getAllList();
		model.put("sitelist", sitelist);
		model.put("headerTitle", "便利店("+web.getName()+")");
		if(request.getSession().getAttribute("imei")==null ||(imei!=null&&imei.length()>0)){
			request.getSession().setAttribute("imei", imei);
		}else{
			imei="33333";
			request.getSession().setAttribute("imei", imei);	
		}
		if(imei!=null&&areaMng.findByImei(imei)!=null){
			request.getSession().setAttribute("defsite",areaMng.findByImei(imei).getWebsite());
		if(areaMng.findByImei(imei).getStreet()!=null){
			request.getSession().setAttribute("defstreet",areaMng.findByImei(imei).getStreet());
		}
		}
		Subject subject = SecurityUtils.getSubject();
		ShopFrontHelper.setCommonData(request, model, web, 1);
		String page=INDEX;
		if(web.getClose()){
			page=CLOSE_INDEX;
		}
		return page;
	}
	
	
	/**
	 * 获取定位信息站点
	 * 
	 * @param request
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/wap/gpssite.jspx")
	public void gpssite(String latitude,String longitude,HttpServletRequest request,HttpServletResponse responses, ModelMap model) {
		Website web = SiteUtils.getWeb(request);
		Object city=null;
		if(longitude==null || latitude==null || latitude.length()<1 ||longitude.length()<1){
//			ResponseUtils.renderText(responses, "");
//			return;
		} 
//		System.out.println("http://api.map.baidu.com/geocoder/v2/?ak=oaugvH2uGm7FOtGHGt4g4MTk&location="+latitude+","+longitude+"&output=json");
		// 创建HttpClient实例     
		 DefaultHttpClient httpclient = new DefaultHttpClient();  
		// 创建Get方法实例     
//		HttpGet httpgets = new HttpGet("http://api.map.baidu.com/geocoder/v2/?ak=oaugvH2uGm7FOtGHGt4g4MTk&location="+latitude+","+longitude+"&output=json");    
		HttpGet httpgets = new HttpGet("http://api.map.baidu.com/location/ip?ak=oaugvH2uGm7FOtGHGt4g4MTk");    
		HttpResponse response = null;
		try {
			response = httpclient.execute(httpgets);
		} catch (ClientProtocolException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}    
		HttpEntity entity = response.getEntity();    
		if (entity != null) {    
		InputStream instreams = null;
		try {
			instreams = entity.getContent();
		} catch (IllegalStateException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}    
		String str = convertStreamToString(instreams); 
		JSONObject a = null;
		try {
			a = new JSONObject(str);
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}  
		
		try {
			//System.out.println("==============================2"+str);
			//System.out.println(((JSONObject)((JSONObject)a.get("result")).get("addressComponent")).get("city"));
//			city= ((JSONObject)((JSONObject)a.get("result")).get("addressComponent")).get("city");
			city= ((JSONObject)((JSONObject)a.get("content")).get("address_detail")).get("city");
			//System.out.println("=============================="+city);
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} // d  

		          // Do not need the rest    
		httpgets.abort();    
		}  

		ShopFrontHelper.setCommonData(request, model, web, 1);
		String res="";
		if(city!=null){
			res=city.toString();
		}
		Website website = websiteMng.findByName(res);
		JSONObject json=new JSONObject();
		if(website!=null){
			try {
				json.put("id", website.getId());
				json.put("name", website.getName());
				json.put("url", "http://"+website.getDomain()+":"+website.getGlobal().getPort()+website.getContextPath()+"/wap/wapindex.jspx?imei="+request.getSession().getAttribute("imei"));
				ResponseUtils.renderJson(responses, json.toString());
				return;
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
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

	/**
	 * 订单列表
	 * @param request
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/wap/waporderpage.jspx", method = RequestMethod.GET)
	public String waporderpage(Integer status,String code,String userName, Long paymentId,
			Long shippingId, String startTime,String endTime,Double startOrderTotal,
			Double endOrderTotal,HttpServletRequest request, ModelMap model) {
		Website web = SiteUtils.getWeb(request);
		model.put("headerTitle", "订单列表("+web.getName()+")");
		ShopMember member = MemberThread.get();
		User user = SiteUtils.getUser(request);
		if(member == null || member.getUsername()==null||user==null){
			 return "redirect:/wap/waplogin.jspx?returnUrl="+web.getGlobal().getContextPath()+"/wap/waporderlist.jspx";
		}
		if(status==null){
			status=0;
		}
		model.addAttribute("pageNos", getpageNo(request));
		model.addAttribute("status", status);
		ShopFrontHelper.setCommonData(request, model, web, 1);
		return ORDERPAGE;
	}	
	/**
	 * 服务订单分页数据
	 * @param request
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/wap/wapfworderpage.jspx", method = RequestMethod.GET)
	public String waporderpage(Integer status,HttpServletRequest request, ModelMap model) {
		Website web = SiteUtils.getWeb(request);
		model.put("headerTitle", "订单列表("+web.getName()+")");
		ShopMember member = MemberThread.get();
		User user = SiteUtils.getUser(request);
		if(member == null || member.getUsername()==null||user==null){
			return "redirect:/wap/waplogin.jspx?returnUrl="+web.getGlobal().getContextPath()+"/wap/wapfworderlist.jspx";
		}
		model.addAttribute("pageNos", getpageNo(request));
		model.addAttribute("status", status);
		ShopFrontHelper.setCommonData(request, model, web, 1);
		return ORDERFWPAGE;
	}	
	/**
	 * 添加留言
	 * @param request
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/wap/wapmessage.jspx", method = RequestMethod.GET)
	public String wapmessage(HttpServletRequest request, ModelMap model) {
		Website web = SiteUtils.getWeb(request);
		model.put("headerTitle", "留言("+web.getName()+")");
		ShopFrontHelper.setCommonData(request, model, web, 1);
		return WAPMESSAGE;
	}	
	/**
	 * 保存留言
	 * @param request
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/wap/wapmessagesave.jspx", method = RequestMethod.POST)
	public void wapmessagesave(ShopMessage bean,HttpServletResponse response,HttpServletRequest request, ModelMap model) {
		Website web = SiteUtils.getWeb(request);
		JSONObject json=new JSONObject();
        try{
        	bean.setTime(new Date());
        	shopMessageMng.saveOrUpdate(bean);
		}catch(Exception e){
			try {
				json.put("info", "留言失败");
				json.put("status", false);
				ResponseUtils.renderJson(response, json.toString());
				return;
			} catch (JSONException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
        try {
			json.put("info", "留言成功");
			json.put("status", true);
			ResponseUtils.renderJson(response, json.toString());
			return;
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        
	}	
	
	/**
	 * 订单列表
	 * @param request
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/wap/waporderlist.jspx", method = RequestMethod.GET)
	public String waporderlist(Integer status,String code,String userName, Long paymentId,
			Long shippingId, String startTime,String endTime,Double startOrderTotal,
			Double endOrderTotal,HttpServletRequest request, ModelMap model) {
		Website web = SiteUtils.getWeb(request);
		model.put("headerTitle", "订单列表("+web.getName()+")");
		ShopMember member = MemberThread.get();
		User user = SiteUtils.getUser(request);
		if(member == null || member.getUsername()==null||user==null){
			return "redirect:/wap/waplogin.jspx?returnUrl="+web.getGlobal().getContextPath()+"/wap/waporderlist.jspx";
		}
		ShopFrontHelper.setCommonData(request, model, web, 1);
		if(StringUtils.isBlank(userName)){
			userName = null;
		}
		if(StringUtils.isBlank(startTime)){
			startTime = null;
		}
		if(StringUtils.isBlank(endTime)){
			endTime = null;
		}
		List<Shipping> shippingList = shippingMng.getList(web.getId(), true);
		List<Payment> paymentList = paymentMng.getList(web.getId(), true);
		model.addAttribute("historyProductIds", getHistoryProductIds(request));
		model.addAttribute("paymentList", paymentList);
		model.addAttribute("shippingList", shippingList);
		if(status==null){
//			status=0;//状态为0时数据查询不准确
		}
		model.addAttribute("status", status);
		model.addAttribute("code",code);
		model.addAttribute("userName", userName);
		model.addAttribute("startTime", startTime);
		model.addAttribute("endTime", endTime);
		model.addAttribute("paymentId", paymentId);
		model.addAttribute("shippingId", shippingId);
		model.addAttribute("startOrderTotal",startOrderTotal);
		model.addAttribute("endOrderTotal", endOrderTotal);
		model.addAttribute("pageNos", getpageNo(request));
		Integer pageNo = URLHelper.getPageNo(request);
		ShopFrontHelper.setCommonData(request, model, web, 1);
		ShopFrontHelper.setDynamicPageData(request, model, web, "", "waporderlist",".jspx", pageNo);
		return ORDERLIST;
	}	
	/**
	 * 服务订单列表
	 * @param request
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/wap/wapfworderlist.jspx", method = RequestMethod.GET)
	public String wapfworderlist(Integer status,HttpServletRequest request, ModelMap model) {
		Website web = SiteUtils.getWeb(request);
		model.put("headerTitle", "订单列表("+web.getName()+")");
		ShopMember member = MemberThread.get();
		User user = SiteUtils.getUser(request);
		if(member == null || member.getUsername()==null||user==null){
			return "redirect:/wap/waplogin.jspx?returnUrl="+web.getGlobal().getContextPath()+"/wap/wapfworderlist.jspx";
		}
		model.addAttribute("status", status);
		model.addAttribute("pageNos", getpageNo(request));
		Integer pageNo = URLHelper.getPageNo(request);
		ShopFrontHelper.setCommonData(request, model, web, 1);
		ShopFrontHelper.setDynamicPageData(request, model, web, "", "wapfworderlist",".jspx", pageNo);
		return ORDERFWLIST;
	}	
	/**
	 * 加入服务订单
	 */
	@RequestMapping(value = "/wap/fworder.jspx", method = RequestMethod.GET)
	public String fworder(Long sqServiceId,String returnUrl,HttpServletRequest request,HttpServletResponse response, ModelMap model) {
		Website web = SiteUtils.getWeb(request);
		ShopMember member = MemberThread.get();
		User user = SiteUtils.getUser(request);
		if(member == null || member.getUsername()==null||user==null){
			return "redirect:/wap/waplogin.jspx?returnUrl="+web.getGlobal().getContextPath()+"/wap/fworder.jspx";
		}
		WebErrors errors = WebErrors.create(request);
		if(sqServiceId==null || sqServiceMng.getById(sqServiceId)==null){
			return "redirect:/wap/wapindex.jspx?imei="+session.getAttribute(request, "imei");
		}
		//所处省份
		List<Address> plist=addressMng.getListBySiteId(web.getId());
		//会员地址
		List<ShopMemberAddress> smalist = shopMemberAddressMng.getList(member.getId(),web.getId());
		model.put("headerTitle", "订单列表("+web.getName()+")");
		model.addAttribute("plist", plist);
		model.addAttribute("sqService", sqServiceMng.getById(sqServiceId));
		model.addAttribute("smalist", smalist);
		ShopFrontHelper.setCommonData(request, model, web, 1);
		return FWORDERSELF;
	}
	public String getAddress(ShopMemberAddress address) {
		String str = "";
		if (address.getProvince() != null) {
			str = str + address.getProvince().getName() + " ";
		}
		if (address.getCity() != null) {
			str = str + address.getCity().getName() + " ";
		}
		if (address.getCountry() != null) {
			str = str + address.getCountry().getName() + " ";
		}
		if(address.getStreet()!=null){
			str = str + address.getStreet().getName() + " ";
		}
		str = str + address.getDetailaddress();
		return str;
	}
	/**
	 * 生成服务订单
	 */
	@RequestMapping(value = "/wap/addSqOrder.jspx", method = RequestMethod.POST)
	public void addSqOrder(Long deliveryInfo,Long sqServiceId,String comments, HttpServletRequest request,HttpServletResponse response, ModelMap model) {
		JSONObject json=new JSONObject();
		Website web = SiteUtils.getWeb(request);
		ShopMember member = MemberThread.get();
		User user = SiteUtils.getUser(request);
		if(member == null || member.getUsername()==null||user==null){
			try {
				json.put("returnUrl", "/wap/fworderself.jspx?sqServiceId="+sqServiceId);
				//json.put("info", "未登录请登录");
				json.put("info", "nologin");
				json.put("status", false);
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			ResponseUtils.renderJson(response, json.toString());
			return;
		}
		WebErrors errors = WebErrors.create(request);
		SqOrder sqOrder = new SqOrder();
		
		if(sqServiceId!=null && sqServiceMng.getById(sqServiceId)!=null ){
			SimpleDateFormat df = new SimpleDateFormat("yyMMdd");
			SqService sqService = sqServiceMng.getById(sqServiceId);
			String code=df.format(new Date())+(new Date().getTime() + member.getId());
			Long date = Long.parseLong(code);
			ShopMemberAddress address = shopMemberAddressMng.findById(deliveryInfo);
			sqOrder.setCode(date);
			sqOrder.setComments(comments);
			sqOrder.setCreateTime(new Date());
			sqOrder.setMember(member);
			sqOrder.setReceiveName(address.getUsername());
			sqOrder.setReceiveAddress(getAddress(address));
			sqOrder.setReceiveTel(address.getTel());
			sqOrder.setSeller(sqService.getKetaUser());
			sqOrder.setSqService(sqService);
			sqOrder.setStatus(1);
			sqOrder.setTotalPrice(sqService.getPrice());
			sqOrder.setWebsite(web);
			sqOrderMng.save(sqOrder);
		}else{
			try {
				json.put("returnUrl", "/wap/fworderself.jspx?sqServiceId="+sqServiceId);
				json.put("info", "该服务不存在，请重新选择");
				json.put("status", false);
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			ResponseUtils.renderJson(response, json.toString());
			return;
		}

		ShopFrontHelper.setCommonData(request, model, web, 1);
		try {
			json.put("returnUrl", "/wap/wapfworderlist.jspx");
			json.put("info", "加入订单成功");
			json.put("status", true);
			ResponseUtils.renderJson(response, json.toString());
			return;
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
//		return web.getTplSys(MEMBER_SYS, MessageResolver.getMessage(request,SUCCESSFULLY_ORDER));
	}
	
	/**
	 * 购买历史cookie
	 * @param request
	 * @return
	 */
	public String getHistoryProductIds(HttpServletRequest request){
		String str = null ;
		Cookie[] cookie = request.getCookies();
		int num = cookie.length;
		for (int i = 0; i < num; i++) {
			if (cookie[i].getName().equals("shop_record")) {
				str = cookie[i].getValue();
				break;
			}
		}
		return str;
	}
	/**
	 * 收货地址列表列表
	 * @param request
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/wap/wapaddrlist.jspx", method = RequestMethod.GET)
	public String wapaddrlist(HttpServletRequest request, ModelMap model) {
		Website web = SiteUtils.getWeb(request);
		model.put("headerTitle", "收货地址("+web.getName()+")");
		ShopMember member=MemberThread.get();
		User user = SiteUtils.getUser(request);
		if(member == null || member.getUsername()==null||user==null){
			 return "redirect:/wap/waplogin.jspx?returnUrl="+web.getGlobal().getContextPath()+"/wap/wapaddrlist.jspx";
		}
		//会员地址
				List<ShopMemberAddress> smalist = shopMemberAddressMng.getList(member.getId(),web.getId());
				//所处省份
				List<Address> plist=addressMng.getListBySiteId(web.getId());
				//付款方式
				List<Payment> paylist=paymentMng.getList((long)1, true);
				model.addAttribute("smalist", smalist);
				model.addAttribute("plist", plist);
		ShopFrontHelper.setCommonData(request, model, web, 1);
		return ADDRLIST;
	}	
	/**
	 * 收货地址删除
	 * @param request
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/wap/wapaddrdel.jspx", method = RequestMethod.GET)
	public void wapaddrdel(Long id,HttpServletRequest request,HttpServletResponse response, ModelMap model) {
		Website web = SiteUtils.getWeb(request);
		ShopMember member=MemberThread.get();
		JSONObject json=new JSONObject();
		User user = SiteUtils.getUser(request);
		if(member == null || member.getUsername()==null||user==null){
			try {
				json.put("info", "nologin");
				json.put("url", "");
				json.put("status", false);
				ResponseUtils.renderJson(response, json.toString());
				return;
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
			
		
		ShopMemberAddress addr = shopMemberAddressMng.findById(id);
		if(addr.getMember().getId()!=member.getId()){
			try {
				json.put("info", "删除失败");
				json.put("url", "");
				json.put("status", false);
				ResponseUtils.renderJson(response, json.toString());
				return;
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		try {
			shopMemberAddressMng.deleteById(id, member.getId());
			json.put("info", "");
			json.put("url", "");
			json.put("status", true);
			ResponseUtils.renderJson(response, json.toString());
			return;
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		ShopFrontHelper.setCommonData(request, model, web, 1);
	}	
	/**
	 * 商品详细页面
	 * 
	 * @param request
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/wap/productself.jspx", method = RequestMethod.GET)
	public String productself(Long id,HttpServletRequest request, ModelMap model) {
		Website web = SiteUtils.getWeb(request);
		ProductSite productSite=productSiteMng.findById(id);
		model.put("productSite", productSite);
		productSiteMng.updateViewCount(productSite);
		model.put("web", web);
		model.put("headerTitle", "商品详情");
		ShopFrontHelper.setCommonData(request, model, web, 1);
		return PRODUCTSELF;
	}
	/**
	 * 添加收货地址
	 * 
	 * @param request
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/wap/addradd.jspx", method = RequestMethod.GET)
	public String addradd(Long id,String returnUrl,HttpServletRequest request, ModelMap model) {
		Website web = SiteUtils.getWeb(request);
		List<Address> plist=addressMng.getListBySiteId(web.getId());
		model.put("returnUrl", returnUrl);
		model.put("plist", plist);
		model.put("web", web);
		model.put("headerTitle", "添加地址");
		ShopFrontHelper.setCommonData(request, model, web, 1);
		return ADDRADD;
	}
	/**
	 * 联系我们
	 * 
	 * @param request
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/wap/conectus.jspx", method = RequestMethod.GET)
	public String conectUs(Long id,HttpServletRequest request, ModelMap model) {
		Website web = SiteUtils.getWeb(request);
		ShopChannel channel = shopChannelMng.findById(7l);
		model.put("channel", channel);
		model.put("web", web);
		model.put("headerTitle", "联系我们");
		ShopFrontHelper.setCommonData(request, model, web, 1);
		return CONECTUS;
	}
	
	/**
	 * 购物车结算
	 * 
	 * @param request
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/wap/cartself.jspx", method = RequestMethod.GET)
	public String cartself(Long id,HttpServletRequest request, ModelMap model) {
		Website web = SiteUtils.getWeb(request);
		model.put("headerTitle", "购物车结算("+web.getName()+")");
		ShopMember member=MemberThread.get();
		User user = SiteUtils.getUser(request);
		if(member == null || member.getUsername()==null||user==null){
			 return "redirect:/wap/waplogin.jspx?returnUrl="+web.getGlobal().getContextPath()+"/wap/cartself.jspx";
		}
		Cart cart = shoppingSvc.getCart(member.getId(), web.getId());
		
		model.put("cart",cart);
		if (cart == null || cart.getItems().isEmpty()) {
			return "redirect:/wap/checkout.jspx";
		}
		List<PopularityItem> popularityItems = null;
		Double  popularityPrice =0.0;
		if(cart!=null){
			popularityItems=popularityItemMng.getlist(cart.getId(),null);
			for(PopularityItem popularityItem:popularityItems){
				popularityPrice+=popularityItem.getPopularityGroup().getPrivilege()*popularityItem.getCount();
			}
		}
       //配送方式
		List<Shipping> splist=shippingMng.getList(null, false);
		//会员地址
		List<ShopMemberAddress> smalist = shopMemberAddressMng.getList(member.getId(),web.getId());
		//所处省份
		List<Address> plist=addressMng.getListBySiteId(web.getId());
		//付款方式
		List<Payment> paylist=paymentMng.getList(null, true);
		model.addAttribute("smalist", smalist);
		model.addAttribute("plist", plist);
		model.addAttribute("paylist", paylist);
		model.addAttribute("splist", splist);
		model.addAttribute("popularityPrice", popularityPrice);
		ShopFrontHelper.setCommonData(request, model, web, 1);
		model.put("web",web);
		return CARTSELF;
	}
	/**
	 * 支付
	 * 
	 * @param request
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/wap/zffs.jspx", method = RequestMethod.GET)
	public String zffs(Long orderId,HttpServletRequest request,HttpServletResponse response, ModelMap model) {
		Website web = SiteUtils.getWeb(request);
		model.put("headerTitle", "支付("+web.getName()+")");
		ShopMember member=MemberThread.get();
		User user = SiteUtils.getUser(request);
		if(member == null || member.getUsername()==null||user==null){
			return "redirect:/wap/waplogin.jspx?returnUrl="+web.getGlobal().getContextPath()+"/wap/zffs.jspx";
		}
		WebErrors errors = validateOrderView(orderId, member, request);
		model.addAttribute("errors", errors.getErrors());
		if(errors.hasErrors()){
		ShopFrontHelper.setCommonData(request, model, web, 1);
		return ZFFS;
		}
		shoppingSvc.clearCookie(request, response);
		Order order=orderMng.findById(orderId);
		List<PaymentPlugins> list = paymentPluginsMng.getList();
		List<ZfbWy> wylist =zfbWyMng.getList();
		model.addAttribute("wylist", wylist);
		model.addAttribute("list", list);
		model.addAttribute("order", order);
		ShopFrontHelper.setCommonData(request, model, web, 1);
		return ZFFS;
	}
	
	private WebErrors validateOrderView(Long orderId, ShopMember member,
			HttpServletRequest request) {
		WebErrors errors = WebErrors.create(request);
		if (errors.ifNull(orderId, "orderId")) {
			return errors;
		}
		Order order = orderMng.findById(orderId);
		if (errors.ifNotExist(order, Order.class, orderId)) {
			return errors;
		}
		if (!order.getMember().getId().equals(member.getId())) {
			errors.noPermission(Order.class, orderId);
			return errors;
		}
		if(order.getPayment().getId()!=1){
			errors.addError("该订单支付类型不为网上支付！！");
			return errors;
		}
		if(order.getPaymentStatus()==2){
			errors.addError("该订单已经支付！！");
			return errors;
		}
		return errors;
	}
	private WebErrors validateOrderTk(Long orderId, ShopMember member,
			HttpServletRequest request) {
		WebErrors errors = WebErrors.create(request);
		if (errors.ifNull(orderId, "orderId")) {
			return errors;
		}
		Order order = orderMng.findById(orderId);
		if (errors.ifNotExist(order, Order.class, orderId)) {
			return errors;
		}
		if (!order.getMember().getId().equals(member.getId())) {
			errors.noPermission(Order.class, orderId);
			return errors;
		}
		if(order.getPayment().getId()!=1){
			errors.addError("该订单支付类型不为网上支付！！");
			return errors;
		}
		if(order.getPaymentStatus()!=2){
			errors.addError("该订单未支付！！");
			return errors;
		}
		if(order.getReturnOrder()!=null){
			errors.addError("该订单已经申请过退款！！");
			return errors;
		}
		return errors;
	}
	/**
	 * 购物车无商品提示
	 * 
	 * @param request
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/wap/checkout.jspx", method = RequestMethod.GET)
	public String checkout(Long id,HttpServletRequest request, ModelMap model) {
		Website web = SiteUtils.getWeb(request);
		model.put("headerTitle", "购物车("+web.getName()+")");
		ShopFrontHelper.setCommonData(request, model, web, 1);
		model.put("web",web);
		return CHECKOUT;
	}
	
	@RequestMapping(value = "/wap/register.jspx",method = RequestMethod.POST)
	public void registerSubmit(String code, String account,
			String email, String password, HttpServletRequest request,
			HttpServletResponse response, ModelMap model) {
		Website web = SiteUtils.getWeb(request);
		ShopConfig config = SiteUtils.getConfig(request);
		WebErrors errors = validate(code, account, email, password,request, response);
		JSONObject json=new JSONObject();
		String error="";
		if (errors.hasErrors()) {  //验证失败
			List list=errors.getErrors();
			error= list.toString();
			try {
				json.put("info", error);
				json.put("url", "");
				json.put("status", false);
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			ResponseUtils.renderJson(response, json.toString());
			return;
		}
		ShopFrontHelper.setCommonData(request, model, web, 1);
		String uuid = StringUtils.remove(UUID.randomUUID().toString(), '-');
		ShopMember member = shopMemberMng.register(account, password, email,true,uuid,
				request.getRemoteAddr(), false, web.getId(), config.getRegisterGroup().getId());
		try {
			json.put("url", ""+web.getGlobal().getContextPath()+"/wap/waplogin.jspx");
			json.put("info", "");
			json.put("status", true);
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		ResponseUtils.renderJson(response, json.toString());
		
	}
	@RequestMapping(value = "/wap/bldregister.jspx",method = RequestMethod.POST)
	public void registerbldSubmit(String code, String account,
			String email, String password, HttpServletRequest request,
			HttpServletResponse response, ModelMap model) {
		Website web = SiteUtils.getWeb(request);
		ShopConfig config = SiteUtils.getConfig(request);
		WebErrors errors = validate(code, account, email, password,request, response);
		JSONObject json=new JSONObject();
		String error="";
		if (errors.hasErrors()) {  //验证失败
			List list=errors.getErrors();
			error= list.toString();
			try {
				json.put("info", error);
				json.put("url", "");
				json.put("status", false);
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			ResponseUtils.renderJson(response, json.toString());
			return;
		}
		ShopFrontHelper.setCommonData(request, model, web, 1);
		String uuid = StringUtils.remove(UUID.randomUUID().toString(), '-');
		ShopMember member = shopMemberMng.register(account, password, email,true,uuid,
				request.getRemoteAddr(), false, web.getId(), config.getRegisterGroup().getId());
		try {
			json.put("url", ""+web.getGlobal().getContextPath()+"/wap/waplogin.jspx");
			json.put("info", "");
			json.put("status", true);
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		ResponseUtils.renderJson(response, json.toString());
		
	}
	/**
	 * 登录页面
	 * 
	 * @param request
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/wap/waplogin.jspx", method = RequestMethod.GET)
	public String waplogin(String returnUrl,String imei,HttpServletRequest request,HttpServletResponse response, ModelMap model) {
		AdminThread.remove();
		GroupThread.remove();
		MemberThread.remove();
		Website web = SiteUtils.getWeb(request);
		model.put("headerTitle", "登录("+web.getName()+")");
		model.put("returnUrl", returnUrl);
		loginSvc.logout(request, response);
		if(request.getSession().getAttribute("imei")==null){
			request.getSession().setAttribute("imei", imei);
		}
		ShopFrontHelper.setCommonData(request, model, web, 1);
		return LOGIN;
	}	
	
	@RequestMapping(value = "/wap/waplogin.jspx", method = RequestMethod.POST)
	public void loginSubmit(String username,String imei, String password,
			String returnUrl, String redirectUrl, HttpServletRequest request,
			HttpServletResponse response, ModelMap model) {
		Website web = SiteUtils.getWeb(request);
		String info="";
		JSONObject json=new JSONObject();
		ShopMember member;
		try {
			if(request.getSession().getAttribute("imei")==null){
				request.getSession().setAttribute("imei", imei);
			}
			String usernames =  (String) SecurityUtils.getSubject().getPrincipal();
			if(usernames!=null){
			member= shopMemberMng.getByUsername(usernames);
			}else{
			member = loginSvc.memberLogin(request, response, web, username,password);
			}
			User user = SiteUtils.getUser(request);
			if(member == null || member.getUsername()==null||user==null){
				json.put("info", "用户名不存在");
				json.put("url", "");
				json.put("status", false);
				ResponseUtils.renderJson(response, json.toString());
				return ;
			}
			request.getSession().setAttribute("user", userMng.getByUsername(member.getUsername()));
			model.addAttribute("member", member);
			ShopFrontHelper.setCommonData(request, model, web, 1);
			if (!StringUtils.isBlank(returnUrl)) {
				json.put("info", "");
				json.put("url", returnUrl);
				json.put("status", true);
				ResponseUtils.renderJson(response, json.toString());
				return;
			} else if (!StringUtils.isBlank(redirectUrl)) {
				json.put("info", "");
				json.put("url", redirectUrl);
				json.put("status", true);
				ResponseUtils.renderJson(response, json.toString());
				return;
			} else {
				
				//登录成功跳转到首页
				json.put("url", "/wap/wapindex.jspx?imei="+imei);
				json.put("status", true);
				ResponseUtils.renderJson(response, json.toString());
				return;
			}
		} catch (UsernameNotFoundException e) {
			info="用户名不存在";
		} catch (BadCredentialsException e) {
			info="密码错误";
		} catch(UserNotAcitveException e){
			info="用户未激活";
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			info="用户异常";
		}	
		model.put("returnUrl", returnUrl);
		model.put("redirectUrl", redirectUrl);
		try {
			json.put("info", info);
			json.put("url", "");
			json.put("status", false);
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		ResponseUtils.renderJson(response, json.toString());
		return ;
	}
	
	
	
	/**
	 * 保存收货地址
	 * @param bean
	 * @param provinceId
	 * @param cityId
	 * @param countryId
	 * @param streetId
	 * @param returnUrl
	 * @param request
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/shopMemberAddress/wap_address_save.jspx", method = RequestMethod.POST)
	public void save(ShopMemberAddress bean,Long provinceId,Long cityId,
			Long countryId,Long streetId,String returnUrl,HttpServletRequest request,HttpServletResponse response, ModelMap model) {
		Website web = SiteUtils.getWeb(request);
		ShopMember member = MemberThread.get();
		List<ShopMemberAddress> list = shopMemberAddressMng.getList(member.getId());
		String info="";
		boolean status=true;
		JSONObject json=new JSONObject();
		model.addAttribute("list", list);
		ShopMemberAddress entity=null;
		try{
		if(bean.getIsDefault()){
			for(int i=0,j=list.size();i<j;i++){
				entity=list.get(i);
				entity.setIsDefault(false);
				shopMemberAddressMng.updateByUpdater(entity);
			}
		}
		bean.setProvince(addressMng.findById(provinceId));
		bean.setCity(addressMng.findById(cityId));
		bean.setCountry(addressMng.findById(countryId));
		bean.setStreet(addressMng.findById(streetId));
		bean.setWebsite(addressMng.findById(provinceId).getWebsite());
		bean.setMember(member);
		shopMemberAddressMng.save(bean);
		ShopFrontHelper.setCommonData(request, model, web, 1);
		}catch(Exception e){
			info="添加失败";
			status=false;
		}
		try {
			json.put("info", info);
			json.put("url", returnUrl);
			json.put("status", status);
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		ResponseUtils.renderJson(response, json.toString());
		return ;
	}
	@RequestMapping(value = "/wap/waploginout.jspx", method = RequestMethod.GET)
	public String loginout(String username, String password,
			String returnUrl, String redirectUrl, HttpServletRequest request,
			HttpServletResponse response, ModelMap model) {
		Website web = SiteUtils.getWeb(request);
		//用户已经登录
				ShopMember member=MemberThread.get();
						if(member != null && member.getUsername()!=null){
							return "redirect:/logout.jspx?returnUrl=/wap/wapindex.jspx";
						}
//						member=MemberThread.get();
		ShopFrontHelper.setCommonData(request, model, web, 1);
		String imei=(String) session.getAttribute(request, "imei");
		return index(imei,request,response, model);
	}
	/**
	 *商品列表
	 * 
	 * @param request
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/wap/productlist.jspx", method = RequestMethod.GET)
	public String productlist(Long categoryId,Integer special,Integer recommend,String orderBy,HttpServletRequest request, ModelMap model) {
		Website web = SiteUtils.getWeb(request);
		model.put("headerTitle", "商品列表("+web.getName()+")");
		Category category = new Category();
		if(categoryId!=null && categoryId>0){
		category = categoryMng.findById(categoryId);
		if (category != null) {
			
			model.addAttribute("brandId",getBrandId(request));
			
//			return category.getTplChannelRel();
		}
		}
		model.addAttribute("recommend", recommend);
		model.addAttribute("special", special);
		model.addAttribute("category", category);
		model.addAttribute("isShow", getIsShow(request));
		model.addAttribute("q", getQ(request));
		model.addAttribute("orderBy",getOrderBy(request));
		model.addAttribute("pageNos", getpageNo(request));
		model.addAttribute("pageSize", getpageSize(request));
		ShopFrontHelper.setCommonData(request, model, web, 1);
		return PRODUCTLIST;
	}	
		
	/**
	 *商品分页数据
	 * 
	 * @param request
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/wap/productpage.jspx", method = RequestMethod.GET)
	public String productpage(Long categoryId,String orderBy,HttpServletRequest request, ModelMap model) {
		Website web = SiteUtils.getWeb(request);
		Category category = new Category();
		if(categoryId!=null && categoryId>0){
			category = categoryMng.findById(categoryId);
			if (category != null) {
				
				model.addAttribute("brandId",getBrandId(request));
				
//			return category.getTplChannelRel();
			}
		}
		model.addAttribute("category", category);
		model.addAttribute("isShow", getIsShow(request));
		model.addAttribute("q", getQ(request));
		model.addAttribute("orderBy",getOrderBy(request));
		model.addAttribute("pageSize", getpageSize(request));
		model.addAttribute("pageNos", getpageNo(request));
		ShopFrontHelper.setCommonData(request, model, web, 1);
		return PRODUCTPAGE;
	}	
	/**
	 *服务分页数据
	 * 
	 * @param request
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/wap/sqfwpage.jspx", method = RequestMethod.GET)
	public String sqfwpage(Long categoryId,String orderBy,HttpServletRequest request, ModelMap model) {
		Website web = SiteUtils.getWeb(request);
		Category category = new Category();
		if(categoryId!=null && categoryId>0){
			category = categoryMng.findById(categoryId);
			if (category != null) {
			}
		}
		model.addAttribute("category", category);
		model.addAttribute("isShow", getIsShow(request));
		model.addAttribute("q", getQ(request));
		model.addAttribute("orderBy",getOrderBy(request));
		model.addAttribute("pageSize", getpageSize(request));
		model.addAttribute("pageNos", getpageNo(request));
		ShopFrontHelper.setCommonData(request, model, web, 1);
		return TZGGPAGE;
	}	
	/**
	 *公告分页数据
	 * 
	 * @param request
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/wap/tzggpage.jspx", method = RequestMethod.GET)
	public String tzggpage(String orderBy,HttpServletRequest request, ModelMap model) {
		Website web = SiteUtils.getWeb(request);
		model.addAttribute("pageSize", getpageSize(request));
		model.addAttribute("pageNo", getpageNo(request));
		ShopFrontHelper.setCommonData(request, model, web, 1);
		return TZGGPAGE;
	}	
	
	/**
	 *服务列表
	 * 
	 * @param request
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/wap/sqfwlist.jspx")
	public String sqfwlistlist(Long categoryId,Long provinceId,Long cityId,Long countryId,Long streetId,String orderBy,HttpServletRequest request, ModelMap model) {
		Website web = SiteUtils.getWeb(request);
		model.put("headerTitle", "服务列表("+web.getName()+")");
		Category category = new Category();
		if(categoryId!=null && categoryId>0){
			category = categoryMng.findById(categoryId);
		}
		//所处省份
		List<Address> plist=addressMng.getListBySiteId(web.getId());
		model.addAttribute("plist", plist);
		model.addAttribute("categoryId", categoryId);
		model.addAttribute("category", category);
		model.addAttribute("provinceId", provinceId);
		model.addAttribute("cityId", cityId);
		model.addAttribute("countryId", countryId);
		model.addAttribute("streetId", streetId);
		model.addAttribute("isShow", getIsShow(request));
		model.addAttribute("pageNos", getpageNo(request));
		model.addAttribute("pageSize", getpageSize(request));
		ShopFrontHelper.setCommonData(request, model, web, 1);
		return SQFWLIST;
	}
	/**
	 *通知公告列表
	 * 
	 * @param request
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/wap/tzgglist.jspx")
	public String tzgglistlist(HttpServletRequest request, ModelMap model) {
		Website web = SiteUtils.getWeb(request);
		model.put("headerTitle", "通知公告("+web.getName()+")");
		model.addAttribute("pageNo", getpageNo(request));
		model.addAttribute("pageSize", getpageSize(request));
		ShopFrontHelper.setCommonData(request, model, web, 1);
		return TZGGLIST;
	}
	
	/**
	 * 服务详细页面
	 * 
	 * @param request
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/wap/sqfwself.jspx", method = RequestMethod.GET)
	public String sqfwself(Long id,HttpServletRequest request, ModelMap model) {
		Website web = SiteUtils.getWeb(request);
		model.put("sqService", sqServiceMng.getById(id));
		model.put("web", web);
		return SQFWSELF;
	}
	/**
	 * 咨询详细页面
	 * 
	 * @param request
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/wap/articleself.jspx", method = RequestMethod.GET)
	public String articleself(Long id,HttpServletRequest request, ModelMap model) {
		Website web = SiteUtils.getWeb(request);
		model.put("headerTitle", "通知公告详细");
		model.put("article", shopArticleMng.findById(id));
		model.put("web", web);
		ShopFrontHelper.setCommonData(request, model, web, 1);
		return ARTICLESELF;
	}
	
	public Integer getBrandId(HttpServletRequest request){
		String brandId = RequestUtils.getQueryParam(request, "brandId");
		Integer id = null;
		if (!StringUtils.isBlank(brandId)) {
			id = Integer.parseInt(brandId);
		}
		return id;
	}
	
	public Integer getpageSize(HttpServletRequest request){
		String pageSize = RequestUtils.getQueryParam(request, "pageSize");
		Integer pagesize = null;
		if (!StringUtils.isBlank(pageSize)) {
			pagesize = Integer.parseInt(pageSize);
		}
		if(pagesize==null){
			pagesize = 10;
		}
		return pagesize;
	}
	public Integer getpageNo(HttpServletRequest request){
		String pageNos = RequestUtils.getQueryParam(request, "pageNos");
		Integer pageNo = null;
		if (!StringUtils.isBlank(pageNos)) {
			pageNo = Integer.parseInt(pageNos);
		}
		if(pageNo==null){
			pageNo = 1;
		}
		return pageNo;
	}

	public Integer getIsShow(HttpServletRequest request){
		String isShow = RequestUtils.getQueryParam(request, "isShow");
		Integer isshow = null;
		if (!StringUtils.isBlank(isShow)) {
			isshow = Integer.parseInt(isShow);
		}
		if(isshow==null){
			isshow = 1;
		}
		return isshow;
	}
	public String getArea(HttpServletRequest request){
		String areas= RequestUtils.getQueryParam(request, "area");
		String area = null;
		if (!StringUtils.isBlank(areas)) {
			area=areas.replace("#none", "");
		}
		return area;
	}
	
	public String getQ(HttpServletRequest request){
		String q= RequestUtils.getQueryParam(request, "q");
		return q;
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
	
	
	
	/**
	 * 找回密码输入页
	 * 
	 * @param request
	 * @param response
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/wap/reset_password.jspx", method = RequestMethod.GET)
	public String fogottenInput(HttpServletRequest request, ModelMap model) {
		Website web = SiteUtils.getWeb(request);
		//用户已经登录
		model.put("headerTitle", "密码修改("+web.getName()+")");
		ShopMember member=MemberThread.get();
				if(member != null && member.getUsername()!=null){
					return "redirect:/logout.jspx?returnUrl=/wap/reset_password.jspx";
				}
		ShopFrontHelper.setCommonData(request, model, web, 1);
		return FORGOT_PASSWORD;
	}
	/**
	 * 注册页
	 * 
	 * @param request
	 * @param response
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/wap/register.jspx", method = RequestMethod.GET)
	public String registerInput(HttpServletRequest request, ModelMap model) {
		Website web = SiteUtils.getWeb(request);
		//用户已经登录
		model.put("headerTitle", "注册("+web.getName()+")");
		ShopMember member=MemberThread.get();
		if(member != null && member.getUsername()!=null){
			return "redirect:/logout.jspx?returnUrl=/wap/register.jspx";
		}
		ShopFrontHelper.setCommonData(request, model, web, 1);
		return REGISTER;
	}
	private WebErrors validate(String checkcode, String username, String email,
			String password, HttpServletRequest request,
			HttpServletResponse response) {
		Website web=SiteUtils.getWeb(request);
		WebErrors errors = WebErrors.create(request);
		if (StringUtils.isBlank(checkcode)) {
			errors.addError("短信验证码不能为空！！");;
			return errors;
		}
		Integer yxmis=web.getGlobal().getYxq();
		Long cus=messageMng.getYzmByTel(username, checkcode, yxmis,1);
		if(cus<=0){
			errors.addError("短信验证码无效,请重新获取！！");;
			return errors;
		}
		if (userMng.usernameExist(username)) {
			errors.addError("该号码已存在！！");;
			return errors;
		}
		return errors;
	}
	private WebErrors validateReset(String checkcode, String username, String email,
			String password, HttpServletRequest request,
			HttpServletResponse response) {
		Website web=SiteUtils.getWeb(request);
		WebErrors errors = WebErrors.create(request);
		if (StringUtils.isBlank(checkcode)) {
			errors.addError("短信验证码不能为空！！");;
			return errors;
		}
		Integer yxmis=web.getGlobal().getYxq();
		Long cus=messageMng.getYzmByTel(username, checkcode, yxmis,1);
		if(cus<=0){
			errors.addError("短信验证码无效,请重新获取！！");;
			return errors;
		}
		return errors;
	}
	@RequestMapping(value = "/wap/reset_password.jspx",method = RequestMethod.POST)
	public void resetSubmit(String code, String account,
			String email, String password, HttpServletRequest request,
			HttpServletResponse response, ModelMap model) {
		Website web = SiteUtils.getWeb(request);
		ShopConfig config = SiteUtils.getConfig(request);
		WebErrors errors = validateReset(code, account, email, password,request, response);
		JSONObject json=new JSONObject();
		String error="";
		if (errors.hasErrors()) {  //验证失败
			List list=errors.getErrors();
			error= list.toString();
			try {
				json.put("info", error);
				json.put("url", "");
				json.put("status", false);
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			ResponseUtils.renderJson(response, json.toString());
			return;
		}
		ShopFrontHelper.setCommonData(request, model, web, 1);
		User user = userMng.getByUsername(account);
		userMng.updateUser(user.getId(), password, email);
//		String uuid = StringUtils.remove(UUID.randomUUID().toString(), '-');
//		ShopMember member = shopMemberMng.register(account, password, email,true,uuid,
//				request.getRemoteAddr(), false, web.getId(), config.getRegisterGroup().getId());
		try {
			json.put("url", "../wap/waplogin.jspx");
			json.put("info", "");
			json.put("status", true);
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		ResponseUtils.renderJson(response, json.toString());
		
	}
	@RequestMapping(value = "/wap/chooseLocation.jspx",method = RequestMethod.GET)
	public void chooseLocation(Long id, HttpServletRequest request,
			HttpServletResponse response, ModelMap model) {
		Website web = SiteUtils.getWeb(request);
		if(id!=null){
		Website site = websiteMng.findById(id);
		Object ob=request.getSession().getAttribute( "imei");
		
		Area area = areaMng.findByImei(request.getSession().getAttribute( "imei").toString());
		if(area==null){
			area=new Area();
			area.setImei(ob.toString());
            area.setLastTime(new Date());
            area.setWebsite(site);
            areaMng.save(area);
		}else{
			    area.setLastTime(new Date());
	            area.setWebsite(site);
	            Updater updater = new Updater(area);
	            areaMng.updateByUpdater(updater);
		}
//		session.setAttribute(request, response, "defsite",area.getWebsite());	
		request.getSession().setAttribute("defsite",area.getWebsite());
		}
		return;
		
	}
	/**
	 * 重置密码发送验证码
	 * @param checkcode
	 * @param username
	 * @param request
	 * @param response
	 * @param model
	 */
	@RequestMapping(value = "/wap/resetsendCode.jspx",method = RequestMethod.POST)
	public void sendCode(String checkcode, String username,
			HttpServletRequest request,
			HttpServletResponse response, ModelMap model) {
		Website web = SiteUtils.getWeb(request);
		ShopConfig config = SiteUtils.getConfig(request);
		
		WebErrors errors = validateUser(checkcode, username, request, response);
		String error="";
		JSONObject json=new JSONObject();
		String id = session.getSessionId(request, response);
		if (errors.hasErrors()) {
			List list=errors.getErrors();
			error= list.toString();
			try {
				json.put("status", false);
				json.put("info",error);
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			ResponseUtils.renderJson(response, json.toString());
			return;
		}else{ //发送短信验证码
			String res="";
			String yzm=((int)((Math.random()*9+1)*100000))+"";  
			Long yxs=messageMng.getTodayCountByTel(username,1);
			Integer yxmis=web.getGlobal().getYxq();
			if(yxs>=web.getGlobal().getCs()){
				res="对不起,您当前号码发送验证码次数已经用完,请明天再试！！";
			}else{
				res=SendMsg_webchinese.sendMsg(username,"您的忘记密码操作的短信验证码是 "+yzm+",有效时间"+yxmis+"分钟 。如果非本人操作,请忽略。");
			}
			if(res.length()>0){
				try {
					json.put("status", false);
					json.put("info",res);
				} catch (JSONException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				ResponseUtils.renderJson(response, json.toString());
				return;
			}
			try {
				Message bean=new Message();
				bean.setTel(username);
				bean.setYzm(yzm);
				
				Calendar cal = Calendar.getInstance();
				cal.setTime(new Date());
				cal.set(Calendar.MINUTE, cal.get(Calendar.MINUTE)+yxmis);
				bean.setYxq(cal.getTime());
				bean.setTypes(1);
				messageMng.save(bean);
				json.put("status", true);
				json.put("info","");
				ResponseUtils.renderJson(response, json.toString());
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
	}
	
	private WebErrors validateUser(String checkcode, String username,  HttpServletRequest request,
			HttpServletResponse response) {
		WebErrors errors = WebErrors.create(request);
		String id = session.getSessionId(request, response);
		// 用户名为空，返回false。
		if (StringUtils.isBlank(checkcode)) {
			errors.addError("验证码不能为空！");
			return errors;
		}
		try {
			if (!captchaService.validateResponseForID(id, checkcode
					.toUpperCase(Locale.ENGLISH))) {
				errors.addErrorCode("error.checkcodeIncorrect");
				return errors;
			}
		} catch (CaptchaServiceException e) {
			e.printStackTrace();
			errors.addErrorCode("error.checkcodeInvalid");
			errors.addErrorString(e.getMessage());
			return errors;
		}
		
		if (StringUtils.isBlank(username)) {
					errors.addError("号码不能为空！");
					return errors;
				}
				// 用户名不存在，返回false。
				if (!userMng.usernameExist(username)) {
					errors.addError("号码不存存在！");
					return errors;
				}
		
		
		return errors;
	}
	
	// 手机在线支付订单
		@RequestMapping(value = "/wap/pay.jspx", method = RequestMethod.POST)
		public String pay(Long orderId,String imei,String wxcode, String code,String wycode, HttpServletRequest request,
				HttpServletResponse response, ModelMap model) {
			Website web = SiteUtils.getWeb(request);
			ShopMember member=MemberThread.get();
			User user = SiteUtils.getUser(request);
			if(member == null || member.getUsername()==null||user==null){
				return "redirect:/wap/waplogin.jspx?returnUrl="+web.getGlobal().getContextPath()+"/wap/zffs.jspx";
			}
			model.put("headerTitle", "支付("+web.getName()+")");
			WebErrors errors = validateOrderView(orderId, member, request);
			model.addAttribute("errors", errors.getErrors());
			if(errors.hasErrors()){
			ShopFrontHelper.setCommonData(request, model, web, 1);
			return ZFFS;
			}
			if (orderId != null && orderMng.findById(orderId) != null) {
				Order order = orderMng.findById(orderId);
				if(wycode!=null && wycode.length()>0){
					code="bankPay";
				}
				if (wxcode != null && wxcode.length() > 0) {
					code = wxcode;
				}
				PaymentPlugins paymentPlugins = paymentPluginsMng.findByCode(code);
				PrintWriter out = null;
				String aliURL = null;
				
				try {
					if (!StringUtils.isBlank(code) && code.equals("alipayPartner")) {
						order.setPaymentCode(code);
//						aliURL = alipayapi(paymentPlugins, web, order, request, model);
					} else if (!StringUtils.isBlank(code) && code.equals("alipay")) {
						order.setPaymentCode(code);
						aliURL = alipay(imei,paymentPlugins, web, order, request,
								model);
					}else if (!StringUtils.isBlank(code) && code.equals("bankPay")) {
						order.setPaymentCode(code);
						//启动定时查询任务
						aliURL =this.alipaywy(imei,paymentPlugins, wycode, web, order, request, model);
					}else if (!StringUtils.isBlank(code) && code.equals("wxPay")) {
						order.setPaymentCode(code);
						//aliURL = wxpay(paymentPlugins, web, order, request, model);
						Map<String,String> zfBw=wxgzhpay(paymentPlugins, web, order, request, model);
						orderMng.updateByUpdater(order);
						model.addAttribute("zfbw", zfBw);
						return "/wap/wxpay.html";
					}
					orderMng.updateByUpdater(order);
					ShopFrontHelper.setCommonData(request, model, web, 1);
					response.setContentType("text/html;charset=UTF-8");
					out = response.getWriter();
					out.print(aliURL);
				} catch (IOException e) {
					e.printStackTrace();
				} finally {
					if(out!=null){
					out.close();
					}
				}
				return null;
			} else {
				return FrontHelper.pageNotFound(web, model, request);
			}
		}
		/**
		 * 扫码支付
		 * 
		 * @param paymentPlugins
		 * @param web
		 * @param order
		 * @param request
		 * @param model
		 * @return
		 */
		private String wxpay(PaymentPlugins paymentPlugins, Website web,
				Order order, HttpServletRequest request, ModelMap model) {
			// 服务器异步通知页面路径
			String notify_url = "http://" + web.getDomain() ;
			if (web.getGlobal() != null && web.getGlobal().getPort() != null
					&& web.getGlobal().getPort() > 0) {
				notify_url = notify_url + ":"+ web.getGlobal().getPort() ;
			}
			if (web.getGlobal() != null && web.getGlobal().getContextPath() != null
					&& web.getGlobal().getContextPath().length() > 0) {
				notify_url = notify_url+ web.getGlobal().getContextPath();
			}
			notify_url = notify_url + "/wxReturn.jspx";
			
			
			// 商户订单号
			String out_trade_no = order.getCode()+"";// 商户网站订单系统中唯一订单号，必填
			
			// 付款金额
			String total_fee = String.valueOf(order.getTotal());// 必填
			// 订单描述
			String body = order.getProductName();
			// 商品展示地址
			String show_url = "http://" + web.getDomain() ;
			if (web.getGlobal() != null && web.getGlobal().getPort() != null
					&& web.getGlobal().getPort() > 0) {
				show_url = show_url + ":"+ web.getGlobal().getPort() ;
			}
			if (web.getGlobal() != null && web.getGlobal().getContextPath() != null
					&& web.getGlobal().getContextPath().length() > 0) {
				show_url = show_url+ web.getGlobal().getContextPath();
			}

			// 客户端的IP地址
			String exter_invoke_ip = RequestUtils.getIpAddr(request);// 非局域网的外网IP地址，如：221.0.0.1
			        // 1 参数
			String appid=paymentPlugins.getSellerKey();
			String appsecret = paymentPlugins.getSellerEmail();
			String partner = paymentPlugins.getPartner();
			   	//这个参数partnerkey是在商户后台配置的一个32位的key,微信商户平台-账户设置-安全设置-api安全
			String partnerkey = paymentPlugins.getPartnerKey();
					// 订单号
					String orderId = order.getCode()+"";
					// 附加数据 原样返回
					String attach = "";
					// 总金额以分为单位，不带小数点
					String totalFee = getMoney(total_fee);
					
					// 订单生成的机器 IP
					String spbill_create_ip = exter_invoke_ip;
					// 这里notify_url是 支付完成后微信发给该链接信息，可以判断会员是否支付成功，改变订单状态等。
					String trade_type = "NATIVE";
					// 商户号
					String mch_id = paymentPlugins.getPartner();
					// 随机字符串
					String nonce_str = getNonceStr();
					SortedMap<String, String> packageParams = new TreeMap<String, String>();
					packageParams.put("appid", appid);
					packageParams.put("mch_id", partner);
					packageParams.put("nonce_str", nonce_str);
					packageParams.put("body", body);
					packageParams.put("attach", attach);
					packageParams.put("out_trade_no", out_trade_no);

					// 这里写的金额为1 分到时修改
					packageParams.put("total_fee", totalFee);
					packageParams.put("spbill_create_ip", spbill_create_ip);
					packageParams.put("notify_url", notify_url);

					packageParams.put("trade_type", trade_type);

					RequestHandler reqHandler = new RequestHandler(null, null);
					reqHandler.init(appid, appsecret, partnerkey);

					String sign = reqHandler.createSign(packageParams);
					String xml = "<xml>" + "<appid>" + appid + "</appid>" + "<mch_id>"
							+ mch_id + "</mch_id>" + "<nonce_str>" + nonce_str
							+ "</nonce_str>" + "<sign>" + sign + "</sign>"
							+ "<body><![CDATA[" + body + "]]></body>" 
							+ "<out_trade_no>" + out_trade_no
							+ "</out_trade_no>" + "<attach>" + attach + "</attach>"
							+ "<total_fee>" + totalFee + "</total_fee>"
							+ "<spbill_create_ip>" + spbill_create_ip
							+ "</spbill_create_ip>" + "<notify_url>" + notify_url
							+ "</notify_url>" + "<trade_type>" + trade_type
							+ "</trade_type>" + "</xml>";
					String createOrderURL = "https://api.mch.weixin.qq.com/pay/unifiedorder";
					String reshtml="<!doctype html><html><meta charset=\"utf-8\"><meta http-equiv=\"X-UA-Compatible\" content=\"IE=edge\"><meta name=\"viewport\" content=\"width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no\"><head><title>微信扫码支付</title><meta name=\"content-type\" content=\"text/html;charset=utf-8\">"
							+ "<link rel=\"stylesheet\" href=\""+show_url+"/wapstyle/assets/css/amazeui.min.css?version="+new Date().getTime()+"\"><link rel=\"stylesheet\" href=\""+show_url+"/wapstyle/assets/css/app.css?version="+new Date().getTime()+"\"><script src=\""+show_url+"/wapstyle/assets/js/jquery.min.js?version="+new Date().getTime()+"\"></script><script src=\""+show_url+"/wapstyle/assets/js/amazeui.min.js?version="+new Date().getTime()+"\"></script><script src=\""+show_url+"/wapstyle/assets/js/app.js?version="+new Date().getTime()+"\"></script>"
							+ "<script src=\""+show_url+"/r/gou/www/assets/js/qrcode.js\"></script></head><body><div align=\"center\" id=\"qrcode\"></div>buttonset</body><script>createscript</script></html>";
					String buttons="";
					WxPayCodeRes wxPayCodeRes = new GetWxOrderno().getCodeUrl(createOrderURL, xml);
					if(wxPayCodeRes.getCode().indexOf("FAIL")!=-1){
						String scrs="var dom=document.createElement('DIV');dom.innerHTML = '"+wxPayCodeRes.getMsg()+"';var element=document.getElementById('qrcode');element.appendChild(dom);";
						reshtml=reshtml.replace("createscript",scrs);
						reshtml=reshtml.replace("buttonset", "<a  class=\"am-btn am-btn-warning am-btn-block\" href='"+show_url+"/wap/waporderlist.jspx'>返回我的订单</>");
					}else{
						String scrs="var url = '"+wxPayCodeRes.getMsg()+"';var qr = qrcode(10, 'M');qr.addData(url);qr.make();var dom=document.createElement('DIV');dom.innerHTML = qr.createImgTag();var element=document.getElementById('qrcode');element.appendChild(dom);";
						reshtml=reshtml.replace("createscript", scrs);
						reshtml=reshtml.replace("buttonset", "<a class=\"am-btn am-btn-warning am-btn-block\" href='"+show_url+"/wap/waporderlist.jspx'>返回我的订单</>");
					}
					return reshtml;
		}
		/**
		 * 支付宝网银支付
		 * @param paymentPlugins
		 * @param wycode
		 * @param web
		 * @param order
		 * @param request
		 * @param model
		 * @return
		 */
		private String alipaywy(String imei,PaymentPlugins paymentPlugins,String wycode, Website web,
				Order order, HttpServletRequest request, ModelMap model) {
			// 支付类型
			String payment_type = "1";// 必填，不能修改
			// 服务器异步通知页面路径
			String notify_url = "http://" + web.getDomain() ;
			if (web.getGlobal() != null && web.getGlobal().getPort() != null
					&& web.getGlobal().getPort() > 0) {
				notify_url = notify_url + ":"+ web.getGlobal().getPort() ;
			}
			if (web.getGlobal() != null && web.getGlobal().getContextPath() != null
					&& web.getGlobal().getContextPath().length() > 0) {
				notify_url = notify_url+ web.getGlobal().getContextPath();
			}
			notify_url = notify_url + "/aliReturn.jspx";
			System.out.println("notify_url==================:"+notify_url);
			// 页面跳转同步通知页面路径
			// String return_url =
			// "http://"+web.getDomain()+":"+web.getGlobal().getPort()+"/aliReturnUrl.jspx";
			String return_url = "http://" + web.getDomain() ;
			if (web.getGlobal() != null && web.getGlobal().getPort() != null
					&& web.getGlobal().getPort() > 0) {
				return_url = return_url + ":"+ web.getGlobal().getPort() ;
			}
			if (web.getGlobal() != null && web.getGlobal().getContextPath() != null
					&& web.getGlobal().getContextPath().length() > 0) {
				return_url = return_url+ web.getGlobal().getContextPath();
			}
			return_url = return_url + "/wap/waporderlist.jspx";
			// 卖家支付宝帐户
			String seller_email = paymentPlugins.getSellerEmail();// 必填
			// 商户订单号
			String out_trade_no = order.getCode()+"";// 商户网站订单系统中唯一订单号，必填
			// 订单名称
			String subject = order.getProductName();// 必填
			if (subject.length() > 128) {
				subject = subject.substring(0, 123) + "...";
			}
			// 付款金额
			String total_fee = String.valueOf(order.getTotal());// 必填
			// 订单描述
			String body = order.getProductName();
			// 商品展示地址
			String show_url = "http://" + web.getDomain();
					if (web.getGlobal() != null && web.getGlobal().getPort() != null
							&& web.getGlobal().getPort() > 0) {
						show_url = show_url + ":"+ web.getGlobal().getPort() ;
					}
					if (web.getGlobal() != null && web.getGlobal().getContextPath() != null
							&& web.getGlobal().getContextPath().length() > 0) {
						show_url = show_url+ web.getGlobal().getContextPath();
					}
					show_url=show_url+"/wap/wapindex.jspx?imei="+imei;
			// 防钓鱼时间戳
			String anti_phishing_key = "";
			String exter_invoke_ip = RequestUtils.getIpAddr(request);// 非局域网的外网IP地址，如：221.0.0.1
			Map<String, String> sParaTemp = new HashMap<String, String>();
			sParaTemp.put("service", "create_direct_pay_by_user");
	        sParaTemp.put("partner", paymentPlugins.getPartner());
	        sParaTemp.put("seller_email", seller_email);
	        sParaTemp.put("_input_charset", "utf-8");
			sParaTemp.put("payment_type", payment_type);
			sParaTemp.put("notify_url", notify_url);
			sParaTemp.put("return_url", return_url);
			sParaTemp.put("out_trade_no", out_trade_no);
			sParaTemp.put("subject", subject);
			sParaTemp.put("total_fee", total_fee);
			sParaTemp.put("body", body);
			sParaTemp.put("paymethod", order.getPaymentCode());
			sParaTemp.put("defaultbank", wycode);
			sParaTemp.put("show_url", show_url);
			sParaTemp.put("anti_phishing_key", anti_phishing_key);
			sParaTemp.put("exter_invoke_ip", exter_invoke_ip);
			
			
			// 建立请求
			String sHtmlText = buildRequest(sParaTemp,
					paymentPlugins.getSellerKey(), "get", "确认");
			return sHtmlText;
		}
		private String alipay(String imei,PaymentPlugins paymentPlugins, Website web,
				Order order, HttpServletRequest request, ModelMap model) {
			// 支付类型
			String payment_type = "1"; // 必填，不能修改
			// 服务器异步通知页面路径
			String notify_url = "http://" + web.getDomain() ;
			if (web.getGlobal() != null && web.getGlobal().getPort() != null
					&& web.getGlobal().getPort() > 0) {
				notify_url = notify_url + ":"+ web.getGlobal().getPort() ;
			}
			if (web.getGlobal() != null && web.getGlobal().getContextPath() != null
					&& web.getGlobal().getContextPath().length() > 0) {
				notify_url = notify_url+ web.getGlobal().getContextPath();
			}
			notify_url = notify_url + "/aliReturn.jspx";
			// 页面跳转同步通知页面路径
			// String return_url =
			// "http://"+web.getDomain()+":"+web.getGlobal().getPort()+"/aliReturnUrl.jspx";
			String return_url = "http://" + web.getDomain() ;
			if (web.getGlobal() != null && web.getGlobal().getPort() != null
					&& web.getGlobal().getPort() > 0) {
				return_url = return_url + ":"+ web.getGlobal().getPort() ;
			}
			if (web.getGlobal() != null && web.getGlobal().getContextPath() != null
					&& web.getGlobal().getContextPath().length() > 0) {
				return_url = return_url+ web.getGlobal().getContextPath();
			}
			return_url = return_url + "/wap/waporderlist.jspx";
			// 卖家支付宝帐户
			String seller_email = paymentPlugins.getSellerEmail();// 必填
			// 商户订单号
			String out_trade_no = order.getCode()+"";// 商户网站订单系统中唯一订单号，必填
			// 订单名称
			String subject = order.getProductName();// 必填
			if (subject.length() > 128) {
				subject = subject.substring(0, 123) + "...";
			}
			// 付款金额
			String price = String.valueOf(order.getTotal());// 必填
			// 商品数量
			String quantity = "1";// 必填，建议默认为1，不改变值，把一次交易看成是一次下订单而非购买一件商品
			// 物流费用
			String logistics_payment = "BUYER_PAY";// 必填，两个值可选：SELLER_PAY（卖家承担运费）、BUYER_PAY（买家承担运费）
			// 订单描述
			String body = order.getProductName();
			// 商品展示地址
						String show_url = "http://" + web.getDomain();
								if (web.getGlobal() != null && web.getGlobal().getPort() != null
										&& web.getGlobal().getPort() > 0) {
									show_url = show_url + ":"+ web.getGlobal().getPort() ;
								}
								if (web.getGlobal() != null && web.getGlobal().getContextPath() != null
										&& web.getGlobal().getContextPath().length() > 0) {
									show_url = show_url+ web.getGlobal().getContextPath();
								}
								show_url=show_url+"/wap/wapindex.jspx?imei="+imei;
			//把请求参数打包成数组
			Map<String, String> sParaTemp = new HashMap<String, String>();
			sParaTemp.put("service", "alipay.wap.create.direct.pay.by.user");
	        sParaTemp.put("partner", paymentPlugins.getPartner());
	        sParaTemp.put("seller_id", paymentPlugins.getSellerEmail());
	        sParaTemp.put("_input_charset", "utf-8");
			sParaTemp.put("payment_type", payment_type);
			sParaTemp.put("notify_url", notify_url);
			sParaTemp.put("return_url", return_url);
			sParaTemp.put("out_trade_no", out_trade_no);
			sParaTemp.put("subject", subject);
			sParaTemp.put("total_fee", price);
			sParaTemp.put("show_url", show_url);
			sParaTemp.put("body", body);
			sParaTemp.put("it_b_pay", "");
			sParaTemp.put("extern_token", "");
			
			//建立请求
			String sHtmlText = buildRequest(sParaTemp,paymentPlugins.getSellerKey(),"get","确认");
			return sHtmlText;
		}
		
		/**
		 * 手机申请订单退款页
		 */
		@RequestMapping(value = "/wap/orderReturn/orderReturn.jspx")
		public String getOrderReturn(Long orderId,Boolean delivery,
				HttpServletRequest request,ModelMap model){
			Website web = SiteUtils.getWeb(request);
			model.put("headerTitle", "退款("+web.getName()+")");
			ShopMember member = MemberThread.get();
			User user = SiteUtils.getUser(request);
			if(member == null || member.getUsername()==null||user==null){
				return "redirect:/wap/waplogin.jspx?returnUrl="+web.getGlobal().getContextPath()+"/wap/orderReturn/orderReturn.jspx";
			}
			WebErrors errors = validateOrderTk(orderId, member, request);
			model.addAttribute("errors", errors.getErrors());
			if(errors.hasErrors()){
			ShopFrontHelper.setCommonData(request, model, web, 1);
			return MESSAGES;
			}
			Order order = orderMng.findById(orderId);
			ShopFrontHelper.setCommonData(request, model, web, 1);
			List<ShopDictionary> ndList=null;
			List<ShopDictionary> returnList=shopDictionaryMng.getListByType((long)9);
			model.addAttribute("returnList", returnList);
			model.addAttribute("order", order);	
			if(delivery==null){
				delivery=true;
			}
			model.addAttribute("delivery", delivery);
			ndList=shopDictionaryMng.getListByType((long)6);
			model.addAttribute("ndList", ndList);	
			return NODELIVERY_ORDER_RETURN;
		}
		/**
		 * 手机订单退款申请提交页
		 */
		@RequestMapping(value = "/wap/orderReturn/orderReturnRefer.jspx", method = RequestMethod.POST)
		public String getOrderReturnRefer(OrderReturn bean,Long orderId,Boolean delivery,
				Long reasonId,String[] picPaths, String[] picDescs,HttpServletRequest request,ModelMap model){
			Website web = SiteUtils.getWeb(request);
			ShopMember member = MemberThread.get();
			User user = SiteUtils.getUser(request);
			if(member == null || member.getUsername()==null||user==null){
				return "redirect:/wap/waplogin.jspx?returnUrl="+web.getGlobal().getContextPath()+"/wap/orderReturn/orderReturn.jspx";
			}
			model.put("headerTitle", "退款("+web.getName()+")");
			WebErrors errors = validateOrderTk(orderId, member, request);
			model.addAttribute("errors", errors.getErrors());
			if(errors.hasErrors()){
			ShopFrontHelper.setCommonData(request, model, web, 1);
			return MESSAGES;
			}
			Order order = orderMng.findById(orderId);
			//生成退款记录
			OrderReturn orderReturn=orderReturnMng.save(bean,order,reasonId,delivery,picPaths,picDescs);
			//修改订单
			order.setReturnOrder(orderReturn);
			orderMng.updateByUpdater(order);
			model.addAttribute("order", order);	
			model.addAttribute("orderReturn", orderReturn);
			ShopFrontHelper.setCommonData(request, model, web, 1);
			return NODELIVERY_RETURN_MONEY_SUCCESS;
		}
		
		/**
	     * 建立请求，以表单HTML形式构造（默认）
	     * @param sParaTemp 请求参数数组
	     * @param strMethod 提交方式。两个值可选：post、get
	     * @param strButtonName 确认按钮显示文字
	     * @return 提交表单HTML文本
	     */
	    public static String buildRequest(Map<String, String> sParaTemp,String key, String strMethod, String strButtonName) {
	    //待请求参数数组
	        Map<String, String> sPara = buildRequestPara(sParaTemp,key);
	        List<String> keys = new ArrayList<String>(sPara.keySet());
	        StringBuffer sbHtml = new StringBuffer();
	        sbHtml.append("<form id=\"alipaysubmit\" name=\"alipaysubmit\" action=\"" + ALIPAY_GATEWAY_NEW + "_input_charset=" + "utf-8" + "\" method=\"" + strMethod + "\">");
	        for (int i = 0; i < keys.size(); i++) {
	            String name = (String) keys.get(i);
	            String value = (String) sPara.get(name);
	            sbHtml.append("<input type=\"hidden\" name=\"" + name + "\" value=\"" + value + "\"/>");
	        }
	        //submit按钮控件请不要含有name属性
	        sbHtml.append("<input type=\"submit\" value=\"" + strButtonName + "\" style=\"display:none;\"></form>");
	        sbHtml.append("<script>document.forms['alipaysubmit'].submit();</script>");
	        return sbHtml.toString();
	    }
	    /**
	     * 生成要请求给支付宝的参数数组
	     * @param sParaTemp 请求前的参数数组
	     * @return 要请求的参数数组
	     */
	     private static Map<String, String> buildRequestPara(Map<String, String> sParaTemp,String key) {
	        //除去数组中的空值和签名参数
	        Map<String, String> sPara = paraFilter(sParaTemp);
	        //生成签名结果
	        String mysign = buildRequestMysign(sPara,key);
	        //签名结果与签名方式加入请求提交参数组中
	        sPara.put("sign", mysign);
	        sPara.put("sign_type", "MD5");
	        return sPara;
	    }   
	    /**
	     * 支付宝提供给商户的服务接入网关URL(新)
	     */
	    private static final String ALIPAY_GATEWAY_NEW = "https://mapi.alipay.com/gateway.do?";
		
	    /**
	     * 生成签名结果
	     * @param sPara 要签名的数组
	     * @return 签名结果字符串
	     */
		public static String buildRequestMysign(Map<String, String> sPara,String key) {
	    	String prestr = createLinkString(sPara); //把数组所有元素，按照“参数=参数值”的模式用“&”字符拼接成字符串
	        String mysign = "";
	        mysign = sign(prestr, key, "utf-8");
	        return mysign;
	    }
		 /**
	     * 签名字符串
	     * @param text 需要签名的字符串
	     * @param key 密钥
	     * @param input_charset 编码格式
	     * @return 签名结果
	     */
	    public static String sign(String text, String key, String input_charset) {
	    	text = text + key;
	        return DigestUtils.md5Hex(getContentBytes(text, input_charset));
	    }
	    /**
	     * @param content
	     * @param charset
	     * @return
	     * @throws SignatureException
	     * @throws UnsupportedEncodingException 
	     */
	    private static byte[] getContentBytes(String content, String charset) {
	        if (charset == null || "".equals(charset)) {
	            return content.getBytes();
	        }
	        try {
	            return content.getBytes(charset);
	        } catch (UnsupportedEncodingException e) {
	            throw new RuntimeException("MD5签名过程中出现错误,指定的编码集不对,您目前指定的编码集是:" + charset);
	        }
	    }
	    /** 
	     * 除去数组中的空值和签名参数
	     * @param sArray 签名参数组
	     * @return 去掉空值与签名参数后的新签名参数组
	     */
	    public static Map<String, String> paraFilter(Map<String, String> sArray) {

	        Map<String, String> result = new HashMap<String, String>();

	        if (sArray == null || sArray.size() <= 0) {
	            return result;
	        }

	        for (String key : sArray.keySet()) {
	            String value = sArray.get(key);
	            if (value == null || value.equals("") || key.equalsIgnoreCase("sign")
	                || key.equalsIgnoreCase("sign_type")) {
	                continue;
	            }
	            result.put(key, value);
	        }

	        return result;
	    }

	    /** 
	     * 把数组所有元素排序，并按照“参数=参数值”的模式用“&”字符拼接成字符串
	     * @param params 需要排序并参与字符拼接的参数组
	     * @return 拼接后字符串
	     */
	    public static String createLinkString(Map<String, String> params) {

	        List<String> keys = new ArrayList<String>(params.keySet());
	        Collections.sort(keys);

	        String prestr = "";

	        for (int i = 0; i < keys.size(); i++) {
	            String key = keys.get(i);
	            String value = params.get(key);

	            if (i == keys.size() - 1) {//拼接时，不包括最后一个&字符
	                prestr = prestr + key + "=" + value;
	            } else {
	                prestr = prestr + key + "=" + value + "&";
	            }
	        }

	        return prestr;
	    }
	public Double getPrice(Set<CartItem> items){
		Double price=0.00;
		 for (CartItem item : items) {
	        	if(item.getProductFash()!=null){
					 price+=item.getProductFash().getSalePrice()*item.getCount();
				}else{
				     price+=item.getProductSite().getSalePrice()*item.getCount();
				}
	        }
		return price;
	}
	


	

	private WebErrors validateFogotten(String checkcode, String username,
			String email, HttpServletRequest request,
			HttpServletResponse response) {
		WebErrors errors = WebErrors.create(request);
		String id = session.getSessionId(request, response);
		if (errors.ifOutOfLength(checkcode, "checkcode", 3, 10)) {
			return errors;
		}
		try {
			if (!captchaService.validateResponseForID(id, checkcode
					.toUpperCase(Locale.ENGLISH))) {
				errors.addErrorCode("error.checkcodeIncorrect");
				return errors;
			}
		} catch (CaptchaServiceException e) {
			errors.addErrorCode("error.checkcodeInvalid");
			errors.addErrorString(e.getMessage());
			return errors;
		}
		if (errors.ifNotEmail(email, "email", 100)) {
			return errors;
		}
		if (errors.ifNotUsername(username, "username", 3, 100)) {
			return errors;
		}
		return errors;
	}

	private WebErrors validateReset(Long uid, String resetKey,
			HttpServletRequest request) {
		WebErrors errors = WebErrors.create(request);
		if (errors.ifNull(uid, "uid")) {
			return errors;
		}
		User user = userMng.findById(uid);
		if (errors.ifNotExist(user, User.class, uid)) {
			return errors;
		}
		if (errors.ifOutOfLength(resetKey, "resetKey", 32, 32)) {
			return errors;
		}
		return errors;
	}
	private Map<String,String> wxgzhpay(PaymentPlugins paymentPlugins, Website web,
			Order order, HttpServletRequest request, ModelMap model) {
		// 服务器异步通知页面路径
		String notify_url = "http://" + web.getDomain() ;
		if (web.getGlobal() != null && web.getGlobal().getPort() != null
				&& web.getGlobal().getPort() > 0) {
			notify_url = notify_url + ":"+ web.getGlobal().getPort() ;
		}
		if (web.getGlobal() != null && web.getGlobal().getContextPath() != null
				&& web.getGlobal().getContextPath().length() > 0) {
			notify_url = notify_url+ web.getGlobal().getContextPath();
		}
		notify_url = notify_url + "/wxReturn.jspx";
		
		
		// 商户订单号
		String out_trade_no = order.getCode()+"";// 商户网站订单系统中唯一订单号，必填
		
		// 付款金额
		String total_fee = String.valueOf(order.getTotal());// 必填
		// 订单描述
		String body = order.getProductName();
		// 商品展示地址
		String show_url = "http://" + web.getDomain() ;
		if (web.getGlobal() != null && web.getGlobal().getPort() != null
				&& web.getGlobal().getPort() > 0) {
			show_url = show_url + ":"+ web.getGlobal().getPort() ;
		}
		if (web.getGlobal() != null && web.getGlobal().getContextPath() != null
				&& web.getGlobal().getContextPath().length() > 0) {
			show_url = show_url+ web.getGlobal().getContextPath();
		}

		// 客户端的IP地址
		String exter_invoke_ip = RequestUtils.getIpAddr(request);// 非局域网的外网IP地址，如：221.0.0.1
		        // 1 参数
		String appid=paymentPlugins.getSellerKey();
		String appsecret = paymentPlugins.getSellerEmail();
		String partner = paymentPlugins.getPartner();
		   	//这个参数partnerkey是在商户后台配置的一个32位的key,微信商户平台-账户设置-安全设置-api安全
		String partnerkey = paymentPlugins.getPartnerKey();
		// 订单号
		String orderId = order.getCode()+"";
		// 附加数据 原样返回
		String attach = "";
		// 总金额以分为单位，不带小数点
		String totalFee = getMoney(total_fee);
		
		// 订单生成的机器 IP
		String spbill_create_ip = exter_invoke_ip;
		// 这里notify_url是 支付完成后微信发给该链接信息，可以判断会员是否支付成功，改变订单状态等。
		String trade_type = "JSAPI";
		// 商户号
		String mch_id = paymentPlugins.getPartner();
		// 随机字符串
		String nonce_str = getNonceStr();
		//model.get("code");
		String openId="";//用户唯一标示
		 Object yhobj=request.getSession().getAttribute("openid");
		Object r_token= request.getSession().getAttribute("refresh_token");
		 if(yhobj !=null){
			 openId=(String) yhobj;
		 }else if(r_token !=null){
		  String r_url="https://api.weixin.qq.com/sns/oauth2/refresh_token?appid=wx3261a0a76cbad713&grant_type=refresh_token&refresh_token="+request.getSession().getAttribute("refresh_token").toString();
		  Map<String,String> map=GetWxOrderno.getOpenIdAndAccess_token(r_url);
		  if(!map.isEmpty()){
			request.getSession().setAttribute("openid", map.get("openid"));
			openId=map.get("openid");
		 }
		 }
		SortedMap<String, String> packageParams = new TreeMap<String, String>();
		packageParams.put("appid", appid);
		packageParams.put("mch_id", partner);
		packageParams.put("nonce_str", nonce_str);
		packageParams.put("body", body);
		packageParams.put("attach", attach);
		packageParams.put("out_trade_no", out_trade_no);

		// 这里写的金额为1 分到时修改
		packageParams.put("total_fee",totalFee);
		packageParams.put("spbill_create_ip", spbill_create_ip);
		packageParams.put("notify_url", notify_url);

		packageParams.put("trade_type", trade_type);
		packageParams.put("openid", openId);
		RequestHandler reqHandler = new RequestHandler(null, null);
		reqHandler.init(appid, appsecret, partnerkey);

		String sign = reqHandler.createSign(packageParams);
		String xml = "<xml>" + "<appid>" + appid + "</appid>" + "<mch_id>"
				+ mch_id + "</mch_id>" + "<nonce_str>" + nonce_str
				+ "</nonce_str>" + "<sign>" + sign + "</sign>"
				+ "<body><![CDATA[" + body + "]]></body>" 
				+ "<out_trade_no>" + out_trade_no
				+ "</out_trade_no>" + "<attach>" + attach + "</attach>"
				+ "<total_fee>" + totalFee + "</total_fee>"
				+ "<spbill_create_ip>" + spbill_create_ip
				+ "</spbill_create_ip>" + "<notify_url>" + notify_url
				+ "</notify_url>" + "<trade_type>" + trade_type
				+ "</trade_type>" + "<openid>"+openId+"</openid>"
				+ "</xml>";
		String prepay_id = "";
		String createOrderURL = "https://api.mch.weixin.qq.com/pay/unifiedorder";		
		
		prepay_id = new GetWxOrderno().getPayNo(createOrderURL, xml);
		Map<String,String> zfBw=new HashMap<String,String>();
		if(StringUtils.isNotBlank(prepay_id)){
			//获取prepay_id后，拼接最后请求支付所需要的package
			SortedMap<String, String> finalpackage = new TreeMap<String, String>();
			String timestamp = String.valueOf(System.currentTimeMillis()/1000);
			String packages = "prepay_id="+prepay_id;
			finalpackage.put("appId", appid);  
			finalpackage.put("timeStamp", timestamp);  
			finalpackage.put("nonceStr", getNonceStr());  
			finalpackage.put("package", packages);  
			finalpackage.put("signType", "MD5");
			//要签名
			String finalsign = reqHandler.createSign(finalpackage);
			zfBw.putAll(finalpackage);
			zfBw.put("paySign", finalsign);
			zfBw.put("fhdd",show_url+"/wap/waporderlist.jspx");
			/*System.out.println("appid"+zfBw.get("appId"));
			System.out.println("finalsign"+zfBw.get("paySign"));
			System.out.println("nonceStr"+zfBw.get("nonceStr"));
			System.out.println("timeStamp"+zfBw.get("timeStamp"));
			System.out.println("package"+zfBw.get("package"));*/
		}
		return zfBw;
	}
	@Autowired
	private CaptchaService captchaService;
	@Autowired
	private SessionProvider session;
	@Autowired
	private MessageMng messageMng;
	
	
	@Autowired
	private CategoryMng categoryMng;
	@Autowired
	private WebsiteMng websiteMng;
	@Autowired
	private ProductMng productMng;
	@Autowired
	private ProductSiteMng productSiteMng;
	@Autowired
	private SqServiceMng sqServiceMng;
	@Autowired
	private SqOrderMng sqOrderMng;
	@Autowired
	private ShopChannelMng shopChannelMng;
	@Autowired
	private ShopArticleMng shopArticleMng;
	@Autowired
	private BrandMng brandMng;
	@Autowired
	private StandardTypeMng standardTypeMng;
	@Autowired
	private ProductStandardMng productStandardMng;
	@Autowired
	private ExendedMng exendedMng;
	@Autowired
	private AddressMng addressMng;
	@Autowired
	private LoginSvc loginSvc;
	@Autowired
	private UserMng userMng;
	@Autowired
	private CartMng cartMng;
	@Autowired
	private ShopMemberMng shopMemberMng;
	@Autowired
	private PwdEncoder pwdEncoder;
	@Autowired
	private OrderMng orderMng;
	@Autowired
	private ShoppingSvc shoppingSvc;

	@Autowired
	private ProductFashionMng productFashionMng;
	@Autowired
	private CartItemMng cartItemMng;

	@Autowired
	private PaymentMng paymentMng;
	@Autowired
	private ShippingMng shippingMng;
	@Autowired
	private ShopMemberAddressMng shopMemberAddressMng;
	@Autowired
	private MemberCouponMng memberCouponMng;
	@Autowired
	private PopularityGroupMng popularityGroupMng;
	@Autowired
	private PopularityItemMng popularityItemMng;
	@Autowired
	private AreaMng areaMng;
	@Autowired
	private PaymentPluginsMng paymentPluginsMng;
	@Autowired
	private ShopMessageMng shopMessageMng;
	@Autowired
	private OrderReturnMng orderReturnMng;
	@Autowired
	private ShopDictionaryMng shopDictionaryMng;
	@Autowired
	private ZfbWyMng zfbWyMng;

}
