/**
* 吉林省艾利特信息技术有限公司
* O2OShop系统
* @date 2015年8月4日
* @author liuwang
* @version 1.0
*/

package com.jspgou.cms.action.admin.main;

import static com.jspgou.common.page.SimplePage.cpn;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import com.jspgou.cms.entity.Activitys;
import com.jspgou.cms.entity.ActivitysGys;
import com.jspgou.cms.entity.Gys;
import com.jspgou.cms.entity.ProductType;
import com.jspgou.cms.entity.ShopAdmin;
import com.jspgou.cms.manager.ActivitysMng;
import com.jspgou.cms.manager.GysMng;
import com.jspgou.cms.manager.ProductSiteMng;
import com.jspgou.cms.manager.ProductTypeMng;
import com.jspgou.cms.web.AdminContextInterceptor;
import com.jspgou.cms.web.threadvariable.AdminThread;
import com.jspgou.common.page.Pagination;
import com.jspgou.common.web.CookieUtils;
import com.jspgou.core.entity.Website;
import com.jspgou.core.manager.WebsiteMng;
import com.jspgou.core.web.SiteUtils;
import com.jspgou.core.web.WebErrors;
@Controller
public class ActivitysAct {
	@RequestMapping("/activitys/v_list.do")
	public String list(Long siteId, Boolean isusing, String name,
			Integer pageNo, HttpServletRequest request,
			ModelMap model) {
		Website web = SiteUtils.getWeb(request);
		List siteList=websiteMng.getAllList();
		ShopAdmin admin = AdminThread.get();
		boolean isSuper=false;
		if(admin.getAdmin().getIsSuper()&&admin.getAdmin().isSuper()){
			isSuper=true;
		}
		if(!isSuper){
			siteId=web.getId();
		}
		Pagination pagination =activitysMng.getPage(siteId, isusing, name, cpn(pageNo), CookieUtils
				.getPageSize(request));
		model.addAttribute("pagination", pagination);
		model.addAttribute("siteList", siteList);
		model.addAttribute("siteId", siteId);
		model.addAttribute("isusing", isusing);
		model.addAttribute("name", name);
		model.addAttribute("siteParam", AdminContextInterceptor.SITE_PARAM);
		model.addAttribute("site", web);
		
		return "activitys/list";
	}
	@RequestMapping("/activitys/buyProductSiteList.do")
	public String buyProductSiteList(String productName,String brandName, Long ctgId,Boolean isOnSale,Boolean isRecommend,
			Boolean isSpecial,Boolean isHotsale,Boolean isNewProduct,
			Long typeId,Double startSalePrice,Double endSalePrice,
			Integer startStock,Integer endStock,Long siteId, HttpServletRequest request,Integer pageNo, 
			ModelMap model) {
		WebErrors errors = WebErrors.create(request);
		errors.ifNull(siteId, "siteId");
		if (errors.hasErrors()) {
			return errors.showErrorPage(model);
		}
		
		Pagination pagination =productSiteMng.getPage(siteId, ctgId, productName, brandName, isOnSale, isRecommend, isSpecial, isHotsale, isNewProduct, typeId, startSalePrice, endSalePrice, startStock, endStock,cpn(pageNo), CookieUtils.getPageSize(request));
		List<ProductType> typeList = productTypeMng.getList();
		model.addAttribute("siteId", siteId);
		model.addAttribute("typeList", typeList);
		model.addAttribute("productName", productName);
		model.addAttribute("brandName",brandName);
		model.addAttribute("isOnSale", isOnSale);
		model.addAttribute("isRecommend", isRecommend);
		model.addAttribute("isSpecial",isSpecial);
		model.addAttribute("isHotsale", isHotsale);
		model.addAttribute("isNewProduct", isNewProduct);
		model.addAttribute("typeId",typeId);
		model.addAttribute("startSalePrice", startSalePrice);
		model.addAttribute("endSalePrice", endSalePrice);
		model.addAttribute("startStock",startStock);
		model.addAttribute("endStock", endStock);
		model.addAttribute("pagination",pagination);
		model.addAttribute("ctgId", ctgId);
		return "activitys/productSiteList";
	}
	@RequestMapping("/activitys/giveProductSiteList.do")
	public String giveProductSiteList(String productName,String brandName, Long ctgId,Boolean isOnSale,Boolean isRecommend,
			Boolean isSpecial,Boolean isHotsale,Boolean isNewProduct,
			Long typeId,Double startSalePrice,Double endSalePrice,
			Integer startStock,Integer endStock,Long siteId, HttpServletRequest request,Integer pageNo, 
			ModelMap model) {
		WebErrors errors = WebErrors.create(request);
		errors.ifNull(siteId, "siteId");
		if (errors.hasErrors()) {
			return errors.showErrorPage(model);
		}
		
		Pagination pagination =productSiteMng.getPage(siteId, ctgId, productName, brandName, isOnSale, isRecommend, isSpecial, isHotsale, isNewProduct, typeId, startSalePrice, endSalePrice, startStock, endStock,cpn(pageNo), CookieUtils.getPageSize(request));
		List<ProductType> typeList = productTypeMng.getList();
		model.addAttribute("siteId", siteId);
		model.addAttribute("typeList", typeList);
		model.addAttribute("productName", productName);
		model.addAttribute("brandName",brandName);
		model.addAttribute("isOnSale", isOnSale);
		model.addAttribute("isRecommend", isRecommend);
		model.addAttribute("isSpecial",isSpecial);
		model.addAttribute("isHotsale", isHotsale);
		model.addAttribute("isNewProduct", isNewProduct);
		model.addAttribute("typeId",typeId);
		model.addAttribute("startSalePrice", startSalePrice);
		model.addAttribute("endSalePrice", endSalePrice);
		model.addAttribute("startStock",startStock);
		model.addAttribute("endStock", endStock);
		model.addAttribute("pagination",pagination);
		model.addAttribute("ctgId", ctgId);
		return "activitys/giveproductSiteList";
	}
	@RequestMapping("/activitys/o_delete.do")
	public String delete(Long id, HttpServletRequest request,Integer pageNo, 
			ModelMap model) {
		WebErrors errors = validateEdit(id,request);
		if (errors.hasErrors()) {
			return errors.showErrorPage(model);
		}
		activitysMng.delete(activitysMng.findById(id));
		
		return "redirect:v_list.do";
	}
	@RequestMapping("/activitys/o_updateisuse.do")
	public String updateisuse(Long id, HttpServletRequest request,Integer pageNo, 
			ModelMap model) {
		WebErrors errors = validateEdit(id,request);
		if (errors.hasErrors()) {
			return errors.showErrorPage(model);
		}
		Activitys bean = activitysMng.findById(id);
		if(bean.getIsusing()){
			bean.setIsusing(false);
			bean.setAddTime(new Date());
		}else{
			bean.setAddTime(new Date());
			bean.setIsusing(true);
		}
		activitysMng.update(bean);
		return "redirect:v_list.do";
	}
	private boolean vldExist(Long id, WebErrors errors) {
		Activitys entity = activitysMng.findById(id);
		return errors.ifNotExist(entity, Activitys.class, id);
	}


	
	private WebErrors validateEdit(Long id, HttpServletRequest request) {
		WebErrors errors = WebErrors.create(request);
		if(errors.ifNull(id, "id")){
			return errors;
		}
		if (vldExist(id,  errors)) {
			return errors;
		}
		return errors;
	}


	@RequestMapping("/activitys/gysSiteList.do")
	public String gysSiteList(Long buyProductSiteId,Long giveProductSiteId,Long siteId, HttpServletRequest request, 
			ModelMap model) {
		WebErrors errors = WebErrors.create(request);
		errors.ifNull(siteId, "siteId");
		errors.ifNull(giveProductSiteId, "赠送商品");
		errors.ifNull(buyProductSiteId, "购买商品");
		if (errors.hasErrors()) {
			return errors.showErrorPage(model);
		}
		List<Gys> gyslist=gysMng.getListBySiteIdAndProductSiteId(siteId, buyProductSiteId, giveProductSiteId);
		model.addAttribute("gyslist", gyslist);
		model.addAttribute("siteId", siteId);
		model.addAttribute("buyProductSiteId", buyProductSiteId);
		model.addAttribute("giveProductSiteId", giveProductSiteId);
		return "activitys/gyslist";
	}
	@RequestMapping("/activitys/v_add.do")
	public String add( HttpServletRequest request,
			ModelMap model) {
		Website web = SiteUtils.getWeb(request);
		List siteList=new ArrayList();
		ShopAdmin admin = AdminThread.get();
		boolean isSuper=false;
		if(admin.getAdmin().getIsSuper()&&admin.getAdmin().isSuper()){
			isSuper=true;
			siteList=websiteMng.getAllList();
		}else{
		siteList.add(web);
		}
		model.addAttribute("siteList", siteList);
		return "activitys/add";
	}
	@RequestMapping("/activitys/o_save.do")
	public String save(Activitys activitys,String gysIds, HttpServletRequest request,
			ModelMap model) {
		Website web = SiteUtils.getWeb(request);
		Set gyss=new HashSet();
		WebErrors errors = WebErrors.create(request);
		errors.ifNull(gysIds, "供应商");
		if (errors.hasErrors()) {
			return errors.showErrorPage(model);
		}
		activitys.setWebsite(websiteMng.findById(activitys.getWebsite().getId()));
		String[] gysids=gysIds.split(",");
		for(String gysid:gysids){
	    ActivitysGys activitysGys=new ActivitysGys();	
	    activitysGys.setGys( gysMng.findById(gysid));
	    activitysGys.setActivitys(activitys);
	    gyss.add(activitysGys);
		}
		activitys.setGyss(gyss);
		activitysMng.save(activitys);
		return "redirect:v_list.do";
	}
	@Autowired
	private WebsiteMng websiteMng;
	@Autowired
	private ProductSiteMng productSiteMng;
	@Autowired
	private ActivitysMng activitysMng;
	@Autowired
	private ProductTypeMng productTypeMng;
	@Autowired
	private GysMng gysMng;
}
