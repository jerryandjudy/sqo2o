package com.jspgou.cms.action.front;

import static com.jspgou.cms.Constants.SHOP_SYS;
import static com.jspgou.cms.Constants.TPLDIR_INDEX;
import static com.jspgou.core.web.front.URLHelper.INDEX;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.jspgou.cms.entity.Address;
import com.jspgou.cms.entity.Brand;
import com.jspgou.cms.entity.Category;
import com.jspgou.cms.entity.Exended;
import com.jspgou.cms.entity.Product;
import com.jspgou.cms.entity.ProductSite;
import com.jspgou.cms.entity.ProductStandard;
import com.jspgou.cms.entity.ShopArticle;
import com.jspgou.cms.entity.ShopChannel;
import com.jspgou.cms.entity.SqService;
import com.jspgou.cms.entity.StandardType;
import com.jspgou.cms.manager.AddressMng;
import com.jspgou.cms.manager.BrandMng;
import com.jspgou.cms.manager.CategoryMng;
import com.jspgou.cms.manager.ExendedMng;
import com.jspgou.cms.manager.FwsMng;
import com.jspgou.cms.manager.ProductMng;
import com.jspgou.cms.manager.ProductSiteMng;
import com.jspgou.cms.manager.ProductStandardMng;
import com.jspgou.cms.manager.ShopArticleMng;
import com.jspgou.cms.manager.ShopChannelMng;
import com.jspgou.cms.manager.SqServiceMng;
import com.jspgou.cms.manager.StandardTypeMng;
import com.jspgou.cms.web.ShopFrontHelper;
import com.jspgou.cms.web.SiteUtils;
import com.jspgou.common.web.RequestUtils;
import com.jspgou.common.web.ResponseUtils;
import com.jspgou.common.web.springmvc.MessageResolver;
import com.jspgou.core.entity.Global;
import com.jspgou.core.entity.Website;
import com.jspgou.core.web.WebErrors;
import com.jspgou.core.web.front.FrontHelper;
import com.jspgou.core.web.front.URLHelper;
import com.jspgou.core.web.front.URLHelper.URLInfo;

/**
 * JspGou动态系统Action
 * @author liufang
 * This class should preserve.
 * @preserve
 */
@Controller
public class DynamicPageAct {
	/**
	 * 首页
	 */
	public static final String TPL_INDEX = "tpl.index";
	public static final String TPL_CLOSE_INDEX = "tpl.closeIndex";
	
	/**
	 * 品牌模板
	 */
	private static final String BRAND = "tpl.brand";
	/**
	 * 品牌详情模板
	 */
	private static final String BRAND_DETAIL = "tpl.brandDetail";
	
	/**
	 * WEBLOGIC的默认路径
	 * 
	 * @param request
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/index.jhtml", method = RequestMethod.GET)
	public String indexForWeblogic(HttpServletRequest request, ModelMap model) {
		return index(request, model);
	}

	/**
	 * TOMCAT的默认路径
	 * 
	 * @param request
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String index(HttpServletRequest request, ModelMap model) {
		Website web = SiteUtils.getWeb(request);
//		Subject subject = SecurityUtils.getSubject();
		ShopFrontHelper.setCommonData(request, model, web, 1);
		String page=TPL_INDEX;
		if(web.getClose()){
			page=TPL_CLOSE_INDEX;
		}
		return web.getTemplate(TPLDIR_INDEX, MessageResolver.getMessage(request,page));
	}
	// 获得所选择省份的全部城市
		@RequestMapping("findAllCity.jspx")
		public void findAllCity(Long id, HttpServletRequest request,
				HttpServletResponse response, ModelMap model) {
			// 所属省份的城市
			Website web = SiteUtils.getWeb(request);
			List<Address> plist =addressMng.getListBySiteId(web.getId());
			JSONObject json = new JSONObject();
			for(Address provice:plist){
			List<Address> clist =addressMng.getListById(provice.getId());
			JSONObject[] json1 = new JSONObject[clist.size()];
			for (int i=0,j=clist.size(); i<j; i++) {
				Address city = clist.get(i);
					try {
						json1[i]= new JSONObject();
						json1[i].put("id", city.getId());
						json1[i].put("name", city.getName());
					} catch (JSONException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
			}
			try {
				json.put(""+provice.getId()+"", json1);
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			}
			ResponseUtils.renderJson(response, json.toString());
		}
		// 获得所选择省份的全部城市
		@RequestMapping("findAllProvice.jspx")
		public void findAllProvice(Long id, HttpServletRequest request,
				HttpServletResponse response, ModelMap model) {
			// 所属站点省份
			Website web = SiteUtils.getWeb(request);
			List<Address> plist =addressMng.getListBySiteId(web.getId());
//			JSONObject json = new JSONObject();
			String htmls="";
			for (int i=0,j=plist.size(); i<j; i++) {
				Address city = plist.get(i);
				htmls=htmls+"<li><a href=\"#none\" data-value=\""+city.getId()+"\">"+city.getName()+"</a></li>";
			}
//			String json="{";
//			for (int i=0,j=plist.size(); i<j; i++) {
//				Address city = plist.get(i);
//				List<Address> list = addressMng.getListById(city.getId());
//				json=json+"\""+city.getName()+"\":{id:\""+city.getId()+"\",root:0,djd:1";
//				if(list!=null&&list.size()>0){
//					json=json+",c:"+list.get(0).getId();
//				}
//				json=json+"},";
//			}
//			json=json+"}";
			ResponseUtils.renderJson(response, htmls.toString());
		}
		
		// 获得所选择市的全部县
		@RequestMapping("findAllCountry.jspx")
		public void findAllArea(Long id,String callback, HttpServletRequest request,
				HttpServletResponse response, ModelMap model) {
			// 所选择市的全部县
			List<Address> alist =addressMng.getListById(id);
			JSONObject json = new JSONObject();
			Long ids[] = new Long[alist.size()];
			String areas[] = new String[alist.size()];
				List<Address> clist =addressMng.getListById(id);
				JSONObject[] json1 = new JSONObject[clist.size()];
				for (int i=0,j=clist.size(); i<j; i++) {
					Address city = clist.get(i);
						try {
							json1[i]= new JSONObject();
							json1[i].put("id", city.getId());
							json1[i].put("name", city.getName());
						} catch (JSONException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
				}
				try {
					json.put("countrys", json1);
				} catch (JSONException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
//			System.out.println(callback+"("+json.toString()+")");
			ResponseUtils.renderJson(response, callback+"("+json.toString()+")");
		}
		// 获得所选市县所有街道
					@RequestMapping("findAllStreet.jspx")
					public void findAllStreet(Long id,String callback,String bldId, HttpServletRequest request,
							HttpServletResponse response, ModelMap model) {
						// 所选择市的全部县
						List<Address> alist =addressMng.getListById(id);
						JSONObject json = new JSONObject();
						Long ids[] = new Long[alist.size()];
						String areas[] = new String[alist.size()];
							List<Address> clist =addressMng.getListById(id);
							JSONObject[] json1 = new JSONObject[clist.size()];
							for (int i=0,j=clist.size(); i<j; i++) {
								Address city = clist.get(i);
									try {
										json1[i]= new JSONObject();
										json1[i].put("id", city.getParent().getParent().getParent().getId()+","+city.getParent().getParent().getId()+","+city.getParent().getId()+","+city.getId());
										json1[i].put("name", city.getName());
									} catch (JSONException e) {
										// TODO Auto-generated catch block
										e.printStackTrace();
									}
							}
							try {
								json.put("streets", json1);
							} catch (JSONException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
//						System.out.println(callback+"("+json.toString()+")");
						ResponseUtils.renderJson(response, callback+"("+json.toString()+")");
					}
	/**
	 * 动态页入口
	 */
	@RequestMapping(value = "/**/*.*", method = RequestMethod.GET)
	public String excute(HttpServletRequest request,HttpServletResponse response, ModelMap model) {
		String url = request.getRequestURL().toString();
		Global global=SiteUtils.getWeb(request).getGlobal();
		model.addAttribute("global", global);
		URLInfo info = URLHelper.getURLInfo(url, request.getContextPath());
		String queryString = request.getQueryString();
		Website web = SiteUtils.getWeb(request);
		ShopFrontHelper.setDynamicPageData(request, model, web, url, info.getUrlPrefix(),info.getUrlSuffix(), info.getPageNo());
		ShopFrontHelper.setCommonData(request, model, web, 1);
		String[] paths = info.getPaths();
		String[] params = info.getParams();
		int pageNo = info.getPageNo();
		int len = paths.length;
        if (len == 1) {
			// 单页
			return channel(paths[0], params, pageNo, queryString, url, web,request, response, model);
		} else if (len == 2) {
			if (paths[1].equals(INDEX)) {
				// 栏目页
				return channel(paths[0], params, pageNo, queryString, url, web,request, response, model);
			}
			try {
				Long id = Long.parseLong(paths[1]);
				// 内容页
				return content(paths[0], id, params, pageNo, queryString, url,web, request, response, model);
			} catch (NumberFormatException e) {
			}
		}
		return FrontHelper.pageNotFound(web, model, request);
	}
	
	/**
	 * 栏目页
	 */
	@SuppressWarnings("unchecked")
	public String channel(String path, String[] params, int pageNo,String queryString, String url, Website web,
			HttpServletRequest request, HttpServletResponse response,ModelMap model) {
		Category category = categoryMng.getByPath(web.getId(), path);
		if (category != null) {
			List<Exended> exendeds = exendedMng.getList(category.getType().getId());
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
			model.addAttribute("category", category);
			model.addAttribute("area", getArea(request));
			model.addAttribute("provinceId",getProvinceId(request));
			model.addAttribute("cityId",getCityId(request));
			model.addAttribute("countryId", getCountryId(request));
			model.addAttribute("streetId", getStreetId(request));
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
			return category.getTplChannelRel();
		} else {
			ShopChannel channel = shopChannelMng.getByPath(null,path);
			if (channel != null) {
				model.addAttribute("channel", channel);
				return channel.getTplChannelRel();
			}
		}
		return FrontHelper.pageNotFound(web, model, request);
	}

	/**
	 * 内容页
	 */
	public String content(String chnlName, Long id, String[] params,
			int pageNo, String queryString, String url, Website web,
			HttpServletRequest request, HttpServletResponse response,
			ModelMap model) {
		Category category = categoryMng.getByPath(web.getId(), chnlName);
		if (category != null) {//商品内容
			ProductSite productSite = productSiteMng.findById(id);
			SqService sqService = sqServiceMng.getById(id);
			
			if (productSite != null ) {
				if(productSite.getProduct().getProductFashion()!=null){
					String[] arr = null;
					arr=productSite.getProduct().getProductFashion().getNature().split("_");
					model.addAttribute("arr", arr);
				}
				Product product=productSite.getProduct();
				List<ProductStandard> psList = productStandardMng.findByProductId(product.getId());
				List<StandardType> standardTypeList = standardTypeMng.getList(category.getId());
				productSiteMng.updateViewCount(productSite);
				model.addAttribute("standardTypeList",standardTypeList);
				model.addAttribute("psList",psList);
				model.addAttribute("category", category);
				model.addAttribute("productSite", productSite);
				return category.getTplContentRel();
			}else if(sqService!=null){
				model.addAttribute("category", category);
				model.addAttribute("sqService", sqService);
				model.addAttribute("fws", fwsMng.findByKetaUserId(sqService.getKetaUser().getId()));
				return category.getTplContentRel();
			} else {
				return FrontHelper.pageNotFound(web, model, request);
			}
		} else {//文章内容
			ShopArticle article = shopArticleMng.findById(id);
			if (article != null) {
				ShopChannel channel = article.getChannel();
				model.addAttribute("article", article);
				model.addAttribute("channel", channel);
				return channel.getTplContentRel();
			} else {
				return FrontHelper.pageNotFound(web, model, request);
			}
		}
	}

	
	//GET提交方式导致enter键提交 出现分页问题
	@RequestMapping(value = "/brand.jspx", method = RequestMethod.GET)
	public String brand(Long id, HttpServletRequest request, ModelMap model) {
		String tpl;
		Website web = SiteUtils.getWeb(request);
		WebErrors errors = validateBrand(id, request);
		if (errors.hasErrors()) {
			return FrontHelper.showError(errors, web, model, request);
		}
		if (id != null) {
			model.addAttribute("brand", brandMng.findById(id));
			tpl = MessageResolver.getMessage(request, BRAND_DETAIL);
		} else {
			tpl = MessageResolver.getMessage(request, BRAND);
		}
		ShopFrontHelper.setCommonData(request, model, web, 1);
		return web.getTplSys(SHOP_SYS, tpl);
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
			pagesize = 12;
		}
		return pagesize;
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
	public String getArea(HttpServletRequest request){
		String areas= RequestUtils.getQueryParam(request, "area");
		String area = null;
		if (!StringUtils.isBlank(areas)) {
			area=areas.replace("#none", "");
		}
		return area;
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
	public Long getProvinceId(HttpServletRequest request){
		String provinceIds = RequestUtils.getQueryParam(request, "provinceId");
		Long provinceId = null;
		if (!StringUtils.isBlank(provinceIds)) {
			provinceId = Long.parseLong(provinceIds);
		}
		return provinceId;
	}
	public Long getCityId(HttpServletRequest request){
		String cityIds = RequestUtils.getQueryParam(request, "cityId");
		Long cityId = null;
		if (!StringUtils.isBlank(cityIds)) {
			cityId = Long.parseLong(cityIds);
		}
		return cityId;
	}
	public Long getCountryId(HttpServletRequest request){
		String countryIds = RequestUtils.getQueryParam(request, "countryId");
		Long countryId = null;
		if (!StringUtils.isBlank(countryIds)) {
			countryId = Long.parseLong(countryIds);
		}
		return countryId;
	}
	public Long getStreetId(HttpServletRequest request){
		String streetIds = RequestUtils.getQueryParam(request, "streetId");
		Long streetId = null;
		if (!StringUtils.isBlank(streetIds)) {
			streetId = Long.parseLong(streetIds);
		}
		return streetId;
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
	
   private WebErrors validateBrand(Long id, HttpServletRequest request) {
		WebErrors errors = WebErrors.create(request);
		if (id != null) {
			Brand brand = brandMng.findById(id);
			if (errors.ifNotExist(brand, Brand.class, id)) {
				return errors;
			}
		}
		return errors;
	}

	@Autowired
	private CategoryMng categoryMng;
	@Autowired
	private ProductMng productMng;
	@Autowired
	private ProductSiteMng productSiteMng;
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
	private SqServiceMng sqServiceMng;
	@Autowired
	private FwsMng fwsMng;

}
