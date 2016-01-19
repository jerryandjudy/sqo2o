package com.jspgou.cms.action.admin.main;

import static com.jspgou.common.page.SimplePage.cpn;
import static com.jspgou.common.web.Constants.LIST_SPLIT;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.FilenameUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.lucene.index.CorruptIndexException;
import org.apache.lucene.store.LockObtainFailedException;
import org.json.JSONException;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.context.ServletContextAware;
import org.springframework.web.multipart.MultipartFile;

import com.jspgou.cms.Constants;
import com.jspgou.cms.entity.Category;
import com.jspgou.cms.entity.Exended;
import com.jspgou.cms.entity.Product;
import com.jspgou.cms.entity.ProductExended;
import com.jspgou.cms.entity.ProductExt;
import com.jspgou.cms.entity.ProductFashion;
import com.jspgou.cms.entity.ProductSite;
import com.jspgou.cms.entity.ProductSiteList;
import com.jspgou.cms.entity.ProductStandard;
import com.jspgou.cms.entity.ProductType;
import com.jspgou.cms.entity.ProductTypeProperty;
import com.jspgou.cms.entity.ShopAdmin;
import com.jspgou.cms.entity.Standard;
import com.jspgou.cms.entity.StandardType;
import com.jspgou.cms.lucene.LuceneProductSvc;
import com.jspgou.cms.manager.CategoryMng;
import com.jspgou.cms.manager.CmsLogMng;
import com.jspgou.cms.manager.ExendedMng;
import com.jspgou.cms.manager.JyspMng;
import com.jspgou.cms.manager.ProductFashionMng;
import com.jspgou.cms.manager.ProductMng;
import com.jspgou.cms.manager.ProductSiteMng;
import com.jspgou.cms.manager.ProductStandardMng;
import com.jspgou.cms.manager.ProductTagMng;
import com.jspgou.cms.manager.ProductTypeMng;
import com.jspgou.cms.manager.ProductTypePropertyMng;
import com.jspgou.cms.manager.StandardMng;
import com.jspgou.cms.manager.StandardTypeMng;
import com.jspgou.cms.web.AdminContextInterceptor;
import com.jspgou.cms.web.SiteUtils;
import com.jspgou.cms.web.threadvariable.AdminThread;
import com.jspgou.common.image.ImageUtils;
import com.jspgou.common.page.Pagination;
import com.jspgou.common.util.ImageCompressUtil;
import com.jspgou.common.web.CookieUtils;
import com.jspgou.common.web.RequestUtils;
import com.jspgou.common.web.ResponseUtils;
import com.jspgou.common.web.springmvc.MessageResolver;
import com.jspgou.core.entity.Website;
import com.jspgou.core.manager.WebsiteMng;
import com.jspgou.core.web.WebErrors;

/**
* This class should preserve.
* @preserve
*/
@Controller
public class ProductAct implements ServletContextAware {
	private static final Logger log = LoggerFactory.getLogger(ProductAct.class);

	@RequestMapping("/product/v_list.do")
	public String list(Long ctgId,Boolean isOnSale,Boolean isRecommend,
			Boolean isSpecial,Boolean isHotsale,Boolean isNewProduct,
			Long typeId,Double startSalePrice,Double endSalePrice,
			Integer startStock,Integer endStock,Integer pageNo, String productName,String brandName,
			HttpServletRequest request, ModelMap model) {
		Website web = SiteUtils.getWeb(request);
		if (ctgId != null) {
			model.addAttribute("category", categoryMng.findById(ctgId));
		}
		Pagination pagination = productMng.getPage(ctgId, productName,brandName,isOnSale, isRecommend, isSpecial,isHotsale,isNewProduct,typeId,
				startSalePrice,endSalePrice,startStock,endStock,
				cpn(pageNo), CookieUtils.getPageSize(request));
		List siteList=websiteMng.getAllList();
		ShopAdmin admin = AdminThread.get();
		boolean isSuper=false;
		if(admin.getAdmin().getIsSuper()&&admin.getAdmin().isSuper()){
			isSuper=true;
		}
		model.addAttribute("isSuper", isSuper);
		model.addAttribute("siteList", siteList);
		List<ProductType> typeList = productTypeMng.getList();
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
		return "product/list";
	}
	@RequestMapping("/product/v_checklist.do")
	public String checklist(Long ctgId,Long parentId,Boolean isOnSale,Boolean isRecommend,
			Boolean isSpecial,Boolean isHotsale,Boolean isNewProduct,
			Long typeId,Double startSalePrice,Double endSalePrice,
			Integer startStock,Integer endStock,Integer pageNo, String productName,String brandName,
			HttpServletRequest request, ModelMap model) {
		Website web = SiteUtils.getWeb(request);
		if (ctgId != null) {
			model.addAttribute("category", categoryMng.findById(ctgId));
		}
		Pagination pagination = productMng.getPage(ctgId, productName,brandName,isOnSale, isRecommend, isSpecial,isHotsale,isNewProduct,typeId,
				startSalePrice,endSalePrice,startStock,endStock,
				cpn(pageNo), CookieUtils.getPageSize(request),parentId);
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
		return "product/checklist";
	}
	@RequestMapping("/product/v_site_list.do")
	public String sitelist(String productName,String brandName,Long ctgId,Boolean isOnSale,Boolean isRecommend,
			Boolean isSpecial,Boolean isHotsale,Boolean isNewProduct,
			Long typeId,Double startSalePrice,Double endSalePrice,
			Integer startStock,Integer endStock,Integer pageNo, 
			HttpServletRequest request, ModelMap model) {
		Website web = SiteUtils.getWeb(request);
//		if (ctgId != null) {
//			model.addAttribute("category", categoryMng.findById(ctgId));
//		}
		if (typeId != null) {
			model.addAttribute("typeId", typeId);
		}
		Pagination pagination = productSiteMng.getPage(SiteUtils.getWebId(request),
				ctgId, productName,brandName,isOnSale, isRecommend, isSpecial,isHotsale,isNewProduct,typeId,
				startSalePrice,endSalePrice,startStock,endStock,
				cpn(pageNo), CookieUtils.getPageSize(request));
		List<ProductType> typeList = productTypeMng.getList();
		List siteList=websiteMng.getAllList();
		ShopAdmin admin = AdminThread.get();
		boolean isSuper=false;
		if(admin.getAdmin().getIsSuper()&&admin.getAdmin().isSuper()){
			isSuper=true;
		}
		model.addAttribute("isSuper", isSuper);
		model.addAttribute("siteList", siteList);
		Website site = SiteUtils.getWeb(request);
		model.addAttribute("siteParam", AdminContextInterceptor.SITE_PARAM);
		model.addAttribute("site", site);
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
		return "product/sitelist";
	}

	@RequestMapping("/product/v_left.do")
	public String left(HttpServletRequest request, ModelMap model) {
		List<Category> list = categoryMng.getTopList();
		// 如果没有商品类别，则不传递数据到视图层，由视图层给出相应提示。
		if (list.size() > 0) {
			Category treeRoot = new Category();
			treeRoot.setName(MessageResolver.getMessage(request,
					"product.allCategory"));
			treeRoot.setChild(new LinkedHashSet<Category>(list));
			model.addAttribute("treeRoot", treeRoot);
		}
		return "product/left";
	}
	@RequestMapping("/product/v_site_left.do")
	public String siteleft(HttpServletRequest request, ModelMap model) {
		List<Category> list = categoryMng.getTopList();
		// 如果没有商品类别，则不传递数据到视图层，由视图层给出相应提示。
		if (list.size() > 0) {
			Category treeRoot = new Category();
			treeRoot.setName(MessageResolver.getMessage(request,
					"product.allCategory"));
			treeRoot.setChild(new LinkedHashSet<Category>(list));
			model.addAttribute("treeRoot", treeRoot);
		}
		return "product/siteleft";
	}

	@SuppressWarnings("unchecked")
	@RequestMapping("/product/v_add.do")
	public String add(Long ctgId, HttpServletRequest request, ModelMap model) {
		Category category= categoryMng.findById(ctgId);
		// 模型项列表
		List<ProductTypeProperty> itemList = productTypePropertyMng.getList(category.getType().getId(), false);
		List<StandardType> standardTypeList =standardTypeMng.getList(category.getId());
		model.addAttribute("ctgId", ctgId);
		model.addAttribute("category", category);
		model.addAttribute("categoryList", categoryMng.getListForProduct(SiteUtils.getWebId(request), ctgId));
		model.addAttribute("tagList", productTagMng.getList());
		model.addAttribute("standardTypeList", standardTypeList);
		model.addAttribute("itemList", itemList);
		List<Exended> exendeds = exendedMng.getList(category.getType().getId());
		Map map= new HashMap(); 
		Map map1= new HashMap(); 
		int num = exendeds.size();
	    for(int i=0;i<num;i++){
			map.put(exendeds.get(i).getId().toString(), exendeds.get(i).getItems());
			map1.put(exendeds.get(i).getId().toString(), exendeds.get(i));
		}
		model.addAttribute("map", map);
		model.addAttribute("map1", map1);
		return "product/add";
	}
	@SuppressWarnings("unchecked")
	@RequestMapping("/product/v_add_list.do")
	public String addlist(Long ctgId,Boolean isOnSale,Boolean isRecommend,
			Boolean isSpecial,Boolean isHotsale,Boolean isNewProduct,
			Long typeId,Double startSalePrice,Double endSalePrice,
			Integer startStock,Integer endStock,Integer pageNo, 
			HttpServletRequest request, ModelMap model) {
		String productName = RequestUtils.getQueryParam(request, "productName");
		productName = StringUtils.trim(productName);
		String brandName = RequestUtils.getQueryParam(request, "brandName");
		brandName = StringUtils.trim(brandName);
		Website web = SiteUtils.getWeb(request);
		if (ctgId != null) {
			model.addAttribute("category", categoryMng.findById(ctgId));
		}
		Pagination pagination = productMng.getNotHasPage(web.getId(),ctgId, productName,brandName,isOnSale, true, isSpecial,isHotsale,isNewProduct,typeId,
				startSalePrice,endSalePrice,startStock,endStock,
				cpn(pageNo), CookieUtils.getPageSize(request));
		List<ProductType> typeList = productTypeMng.getList();
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
		return "product/addlist";
	}
	@SuppressWarnings("unchecked")
	@RequestMapping("/product/v_edit.do")
	public String edit(Long id, Long ctgId, HttpServletRequest request,
			ModelMap model) {
		WebErrors errors = validateProEdit(id, request);
		if (errors.hasErrors()) {
			return errors.showErrorPage(model);
		}
		Product product = productMng.findById(id);
		List<ProductStandard> psList = productStandardMng.findByProductId(id);
		String productKeywords = StringUtils.join(product.getKeywords(),LIST_SPLIT);// 模型项列表
		Category category= product.getCategory(); 	
		List<StandardType> standardTypeList = standardTypeMng.getList(category.getId());
		List<ProductTypeProperty> itemList = productTypePropertyMng.getList(category.getType().getId(), false);
		List<ProductExended> pelist=product.getExendeds();
		List<Exended> exendeds = exendedMng.getList(category.getType().getId());
		Map map= new HashMap(); 
		Map map1= new HashMap(); 
		int num = exendeds.size();
	    for(int i=0;i<num;i++){
			map.put(exendeds.get(i).getId().toString(), exendeds.get(i).getItems());
			map1.put(exendeds.get(i).getId().toString(), exendeds.get(i));
		}
	    Map map2=new HashMap();
      	for(int i=0;i<pelist.size();i++){
		   map2.put(pelist.get(i).getName(), pelist.get(i).getValue());
	    }
	    model.addAttribute("map2", map2);
		model.addAttribute("map", map);
		model.addAttribute("map1", map1);
		model.addAttribute("productKeywords", productKeywords);
		model.addAttribute("tagList", productTagMng.getList());
		model.addAttribute("categoryList", categoryMng.getList());
		model.addAttribute("standardTypeList",standardTypeList);
		model.addAttribute("category", category);
		model.addAttribute("product", product);
		model.addAttribute("ctgId", ctgId);
		model.addAttribute("isLimit", product.getProductExt().getIslimitTime());
		model.addAttribute("itemList", itemList);
		model.addAttribute("psList",psList);
		return "product/edit";
	}
	@SuppressWarnings("unchecked")
	@RequestMapping("/product/v_site_edit.do")
	public String siteedit(Long id, Long ctgId, HttpServletRequest request,
			ModelMap model) {
		WebErrors errors = validateEdit(id, request);
		if (errors.hasErrors()) {
			return errors.showErrorPage(model);
		}
		ProductSite productSite=productSiteMng.findById(id);
		Product product = productSite.getProduct();
		List<ProductStandard> psList = productStandardMng.findByProductId(product.getId());
		String productKeywords = StringUtils.join(product.getKeywords(),LIST_SPLIT);// 模型项列表
		Category category= product.getCategory(); 	
		List<StandardType> standardTypeList = standardTypeMng.getList(category.getId());
		List<ProductTypeProperty> itemList = productTypePropertyMng.getList(category.getType().getId(), false);
		List<ProductExended> pelist=product.getExendeds();
		List<Exended> exendeds = exendedMng.getList(category.getType().getId());
		Map map= new HashMap(); 
		Map map1= new HashMap(); 
		int num = exendeds.size();
		for(int i=0;i<num;i++){
			map.put(exendeds.get(i).getId().toString(), exendeds.get(i).getItems());
			map1.put(exendeds.get(i).getId().toString(), exendeds.get(i));
		}
		Map map2=new HashMap();
		for(int i=0;i<pelist.size();i++){
			map2.put(pelist.get(i).getName(), pelist.get(i).getValue());
		}
		model.addAttribute("map2", map2);
		model.addAttribute("map", map);
		model.addAttribute("map1", map1);
		model.addAttribute("productKeywords", productKeywords);
		model.addAttribute("tagList", productTagMng.getList());
		model.addAttribute("categoryList", categoryMng.getList());
		model.addAttribute("standardTypeList",standardTypeList);
		model.addAttribute("category", category);
		model.addAttribute("product", product);
		model.addAttribute("productsite", productSite);
		model.addAttribute("ctgId", ctgId);
		model.addAttribute("isLimit", product.getProductExt().getIslimitTime());
		model.addAttribute("itemList", itemList);
		model.addAttribute("psList",psList);
		return "product/siteedit";
	}
	
	
	
	
	//修改变为详情
	@SuppressWarnings("unchecked")
	@RequestMapping("/product/v_site_edit_view.do")
	public String siteeditView(Long id, Long ctgId, HttpServletRequest request,
			ModelMap model) {
		WebErrors errors = validateEdit(id, request);
		if (errors.hasErrors()) {
			return errors.showErrorPage(model);
		}
		ProductSite productSite=productSiteMng.findById(id);
		Product product = productSite.getProduct();
		List<ProductStandard> psList = productStandardMng.findByProductId(product.getId());
		String productKeywords = StringUtils.join(product.getKeywords(),LIST_SPLIT);// 模型项列表
		Category category= product.getCategory(); 	
		List<StandardType> standardTypeList = standardTypeMng.getList(category.getId());
		List<ProductTypeProperty> itemList = productTypePropertyMng.getList(category.getType().getId(), false);
		List<ProductExended> pelist=product.getExendeds();
		List<Exended> exendeds = exendedMng.getList(category.getType().getId());
		Map map= new HashMap(); 
		Map map1= new HashMap(); 
		int num = exendeds.size();
		for(int i=0;i<num;i++){
			map.put(exendeds.get(i).getId().toString(), exendeds.get(i).getItems());
			map1.put(exendeds.get(i).getId().toString(), exendeds.get(i));
		}
		Map map2=new HashMap();
		for(int i=0;i<pelist.size();i++){
			map2.put(pelist.get(i).getName(), pelist.get(i).getValue());
		}
		model.addAttribute("map2", map2);
		model.addAttribute("map", map);
		model.addAttribute("map1", map1);
		model.addAttribute("productKeywords", productKeywords);
		model.addAttribute("tagList", productTagMng.getList());
		model.addAttribute("categoryList", categoryMng.getList());
		model.addAttribute("standardTypeList",standardTypeList);
		model.addAttribute("category", category);
		model.addAttribute("product", product);
		model.addAttribute("productsite", productSite);
		model.addAttribute("ctgId", ctgId);
		model.addAttribute("isLimit", product.getProductExt().getIslimitTime());
		model.addAttribute("itemList", itemList);
		model.addAttribute("psList",psList);
		return "product/siteeditView";
	}
	
	
	
	
	@SuppressWarnings("unchecked")
	@RequestMapping("/product/v_view.do")
	public String view(String returnUrl,Long id, Long ctgId, HttpServletRequest request,
			ModelMap model) {
		WebErrors errors = validateProEdit(id, request);
		if (errors.hasErrors()) {
			return errors.showErrorPage(model);
		}
		Product product = productMng.findById(id);
		List<ProductStandard> psList = productStandardMng.findByProductId(id);
		String productKeywords = StringUtils.join(product.getKeywords(),LIST_SPLIT);// 模型项列表
		Category category= product.getCategory(); 	
		List<StandardType> standardTypeList = standardTypeMng.getList(category.getId());
		List<ProductTypeProperty> itemList = productTypePropertyMng.getList(category.getType().getId(), false);
		List<ProductExended> pelist=product.getExendeds();
		List<Exended> exendeds = exendedMng.getList(category.getType().getId());
		Map map= new HashMap(); 
		Map map1= new HashMap(); 
		int num = exendeds.size();
		for(int i=0;i<num;i++){
			map.put(exendeds.get(i).getId().toString(), exendeds.get(i).getItems());
			map1.put(exendeds.get(i).getId().toString(), exendeds.get(i));
		}
		Map map2=new HashMap();
		for(int i=0;i<pelist.size();i++){
			map2.put(pelist.get(i).getName(), pelist.get(i).getValue());
		}
		model.addAttribute("map2", map2);
		model.addAttribute("map", map);
		model.addAttribute("map1", map1);
		model.addAttribute("returnUrl", returnUrl);
		model.addAttribute("productKeywords", productKeywords);
		model.addAttribute("tagList", productTagMng.getList());
		model.addAttribute("categoryList", categoryMng.getList());
		model.addAttribute("standardTypeList",standardTypeList);
		model.addAttribute("category", category);
		model.addAttribute("product", product);
		model.addAttribute("ctgId", ctgId);
		model.addAttribute("isLimit", product.getProductExt().getIslimitTime());
		model.addAttribute("itemList", itemList);
		model.addAttribute("psList",psList);
		return "product/view";
	}

	@SuppressWarnings("unchecked")
	@RequestMapping("/product/o_save.do")
	public String save(Product bean, ProductExt ext,Long categoryId, Long brandId,
			Long[] tagIds, String productKeywords,String[] nature,Long[] picture,String[] colorImg,Long[] character, 
			@RequestParam(value = "file", required = false) MultipartFile file,
			String fashionSwitchPic[],String fashionBigPic[],String fashionAmpPic[],
			Boolean[] isDefaults,Long[] colors,Long[] measures,Integer stockCounts[],
			Double[] salePrices,Double[] marketPrices,Double[] costPrices,Long ctgId,
			HttpServletRequest request, ModelMap model) {
		WebErrors errors = validateSave(bean, file, request);
		if(bean.getParent()!=null&&bean.getParent().getId()!=null&&bean.getParentCount()==null){
			errors.addError("对应单品数量不能为空!");
		}
		if (errors.hasErrors()) {
			return errors.showErrorPage(model);
		}
		productKeywords = StringUtils.replace(productKeywords, MessageResolver.getMessage(request, "product.keywords.split"), LIST_SPLIT);
		String[] keywords = StringUtils.split(productKeywords, LIST_SPLIT);
		Website web = SiteUtils.getWeb(request);
		Map<String, String> exended = RequestUtils.getRequestMap(request, "exended_");
	    List li=new ArrayList(exended.keySet());
	    String[] names = new String[li.size()];
	    String[] values =  new String[li.size()];
//	    if(stockCounts!=null){
//	    	  Integer  stockCount =0;
//	    	  for(Integer s:stockCounts){
//	    		  stockCount+=s; 
//	    	  }
//	    	  bean.setStockCount(stockCount);
//	    }
	    for(int i=0;i<li.size();i++){
	    	names[i]=(String) li.get(i);
	    	values[i]=exended.get(li.get(i));
	    }
	    bean.setAttr( RequestUtils.getRequestMap(request, "attr_"));
	    if(ext.getCoverImg()!=null  && ext.getCoverImg().length()>0 &&(ext.getMinImg()==null||ext.getMinImg().length()<1 )){
//			String oldimg=servletContext.getRealPath("/")+ext.getCoverImg().replace(request.getContextPath().replace("\\","/")+"/", "").replace("/", "\\");
			String oldimg=servletContext.getRealPath("/")+ext.getCoverImg();
	    	 String newwapimg=ImageCompressUtil.zipImageFile(oldimg, 200, 200, 1f, "wap");
	    	 String contextpath=request.getContextPath();
	    	 if("/".equals(contextpath)){
	    		 contextpath="";
	    	 }
	    	 newwapimg=(contextpath+newwapimg.replace(servletContext.getRealPath("/"), "")).replace("\\", "/");
	    	 ext.setMinImg(newwapimg);
	    }
		bean = productMng.save(SiteUtils.getUser(request),bean, ext, web.getId(), categoryId, brandId, tagIds,
			keywords,names,values,fashionSwitchPic,fashionBigPic,fashionAmpPic,file);
//		String path = servletContext.getRealPath(Constants.LUCENE_JSPGOU);
//		  try {
//			int count = luceneProductSvc.index(path, null, null, null);
//		} catch (CorruptIndexException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		} catch (LockObtainFailedException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		} catch (IOException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
	   if(picture!=null){
			for(int i =0;i<picture.length;i++){
				ProductStandard ps=new ProductStandard();
				ps.setImgPath(colorImg[i]);
				ps.setType(standardMng.findById(picture[i]).getType());
				ps.setProduct(bean);
				ps.setStandard(standardMng.findById(picture[i]));
				ps.setDataType(true);
				productStandardMng.save(ps);
			}
		}
	   if(character!=null){
		   for(int i =0;i<character.length;i++){
				ProductStandard ps=new ProductStandard();
				ps.setType(standardMng.findById(character[i]).getType());
				ps.setProduct(bean);
				ps.setStandard(standardMng.findById(character[i]));
				ps.setDataType(false);
				productStandardMng.save(ps);
			}
	    }
	    saveProductFash(bean,nature,isDefaults,stockCounts,salePrices,marketPrices,costPrices);
		log.info("save Product. id={}", bean.getId());
		model.addAttribute("message", "global.success");
		model.addAttribute("brandId", brandId);
		return add(ctgId, request, model);
	}
	@SuppressWarnings("unchecked")
	@RequestMapping("/product/o_site_save.do")
	public String sitesave(ProductSite bean,String[] ids,Long ctgId,Boolean isOnSale,Boolean isRecommend,
			Boolean isSpecial,Boolean isHotsale,Boolean isNewProduct,
			Long typeId,Double startSalePrice,Double endSalePrice,
			Integer startStock,Integer endStock,Integer pageNo, 
			HttpServletRequest request, ModelMap model) {
		if(ids!=null&& ids.length>0){
			for(String id:ids){
				bean=new ProductSite();
		Website web = SiteUtils.getWeb(request);
		bean.setProduct(productMng.findById(Long.parseLong(id)));
		bean.setCreateTime(new Date());
		bean.setWebsite(web);
		productSiteMng.save(bean);
		log.info("add Product ProductSite. id={}", bean.getId());
			}
		}
//		String path = servletContext.getRealPath(Constants.LUCENE_JSPGOU);
//		  try {
//			int count = luceneProductSvc.index(path, null, null, null);
//		} catch (CorruptIndexException e) {
			// TODO Auto-generated catch block
//			e.printStackTrace();
//		} catch (LockObtainFailedException e) {
			// TODO Auto-generated catch block
//			e.printStackTrace();
//		} catch (IOException e) {
			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
			model.addAttribute("ctgId", ctgId);
		return this.addlist(ctgId, isOnSale, isRecommend, isSpecial, isHotsale, isNewProduct, typeId, startSalePrice, endSalePrice, startStock, endStock, pageNo, request, model);
	}
	@SuppressWarnings("unchecked")
	@RequestMapping("/product/o_site_deletes.do")
	public String sitedeletes(ProductSite bean,Long[] ids,Long ctgId,Boolean isOnSale,Boolean isRecommend,
			Boolean isSpecial,Boolean isHotsale,Boolean isNewProduct,
			Long typeId,Double startSalePrice,Double endSalePrice,
			Integer startStock,Integer endStock,Integer pageNo, 
			HttpServletRequest request, ModelMap model) {
		if(ids!=null && ids.length>0){
			WebErrors errors = validateDelete(ids, request);
			if (errors.hasErrors()) {
				return errors.showErrorPage(model);
			}
		String content="";
		ProductSite oldbean=null;
		for(Long id:ids){
			oldbean=productSiteMng.findById(id);
			content="name="+oldbean.getProduct().getName()+";product.Id="+oldbean.getProduct().getId()+";Id="+oldbean.getId()+"";
		}
		cmsLogMng.operating(request, "productSite.log.delete", content);
		productSiteMng.deleteByIds(ids);
		model.addAttribute("ctgId", ctgId);
		}
		return this.sitelist(null,null,ctgId, isOnSale, isRecommend, isSpecial, isHotsale, isNewProduct, typeId, startSalePrice, endSalePrice, startStock, endStock, pageNo, request, model);
	}
	@SuppressWarnings("unchecked")
	@RequestMapping("/product/o_site_delete.do")
	public String sitedelete(ProductSite bean,Long ctgId,Boolean isOnSale,Boolean isRecommend,
			Boolean isSpecial,Boolean isHotsale,Boolean isNewProduct,
			Long typeId,Double startSalePrice,Double endSalePrice,
			Integer startStock,Integer endStock,Integer pageNo, 
			HttpServletRequest request, ModelMap model) {
		String content="";
		ProductSite oldbean=null;
		Long[] ids=new Long[1];
		ids[0]=bean.getId();
		WebErrors errors = validateDelete(ids, request);
		if (errors.hasErrors()) {
			return errors.showErrorPage(model);
		}
		oldbean=productSiteMng.findById(bean.getId());
		content="name="+oldbean.getProduct().getName()+";product.Id="+oldbean.getProduct().getId()+";Id="+oldbean.getId()+"";
		cmsLogMng.operating(request, "productSite.log.delete", content);
		productSiteMng.deleteById(bean.getId());
		/*String path = servletContext.getRealPath(Constants.LUCENE_JSPGOU);
		  try {
			int count = luceneProductSvc.index(path, null, null, null);
		} catch (CorruptIndexException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (LockObtainFailedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}*/
		model.addAttribute("ctgId", ctgId);
		return this.sitelist(null,null,ctgId, isOnSale, isRecommend, isSpecial, isHotsale, isNewProduct, typeId, startSalePrice, endSalePrice, startStock, endStock, pageNo, request, model);
	}

	@SuppressWarnings("unchecked")
	@RequestMapping("/product/o_update.do")
	public String update(Product bean, ProductExt ext, Long categoryId,Long brandId,
			Long[] tagIds,String productKeywords,String[] nature,Long[] picture,String[] colorImg,Long[] character,
			@RequestParam(value = "file", required = false) MultipartFile file,
			String fashionSwitchPic[],String fashionBigPic[],String fashionAmpPic[],
			Boolean[] isDefaults,Long[] colors,Long[] measures,Integer stockCounts[],
			Double[] salePrices,Double[] marketPrices,Double[] costPrices,Long[] fashId,
			Long ctgId,Integer pageNo,
			HttpServletRequest request, ModelMap model) {
		WebErrors errors = validateProEdit(bean.getId(),  request);
		if(bean.getParent()!=null&&bean.getParent().getId()!=null&&bean.getParentCount()==null){
			errors.addError("对应单品数量不能为空!");
		}
		if (errors.hasErrors()) {
			return errors.showErrorPage(model);
		}
		productKeywords = StringUtils.replace(productKeywords, MessageResolver
				.getMessage(request, "product.keywords.split"), LIST_SPLIT);
		String[] keywords = StringUtils.split(productKeywords, LIST_SPLIT);
		Map<String, String> exended = RequestUtils.getRequestMap(request, "exended_");
		List li=new ArrayList(exended.keySet());
		String[] names = new String[li.size()];
		String[] values =  new String[li.size()];
		for(int i=0;i<li.size();i++){
		    names[i]=(String) li.get(i);
		    values[i]=exended.get(li.get(i));
		}
		Map<String, String> attr = RequestUtils.getRequestMap(request, "attr_");
		 /*if(stockCounts!=null){
	    	  Integer  stockCount =0;
	    	  for(Integer s:stockCounts){
	    		  stockCount+=s; 
	    	  }
	    	  bean.setStockCount(stockCount);
	    }*/
	    if(ext.getCoverImg()!=null  && ext.getCoverImg().length()>0 &&(ext.getMinImg()==null||ext.getMinImg().length()<1 )){
//			String oldimg=servletContext.getRealPath("/")+ext.getCoverImg().replace(request.getContextPath().replace("\\","/")+"/", "").replace("/", "\\");
			String oldimg=servletContext.getRealPath("/")+ext.getCoverImg();
	    	 String newwapimg=ImageCompressUtil.zipImageFile(oldimg,200, 200 , 1f, "wap");
	    	 String contextpath=request.getContextPath();
	    	 if("/".equals(contextpath)){
	    		 contextpath="";
	    	 }
	    	 newwapimg=(contextpath+newwapimg.replace(servletContext.getRealPath("/"), "")).replace("\\", "/");
	    	 ext.setMinImg(newwapimg);
	    }
		bean = productMng.update(SiteUtils.getUser(request),bean, ext, categoryId, brandId, tagIds, keywords,names,values,attr,
				fashionSwitchPic,fashionBigPic,fashionAmpPic,file);
		List<ProductStandard> pcList=productStandardMng.findByProductId(bean.getId());
		for(int j=0;j<pcList.size();j++){
			productStandardMng.deleteById(pcList.get(j).getId());
		}
		if(picture!=null){
			for(int i =0;i<picture.length;i++){
				ProductStandard ps=new ProductStandard();
				ps.setImgPath(colorImg[i]);
				ps.setType(standardMng.findById(picture[i]).getType());
				ps.setProduct(bean);
				ps.setStandard(standardMng.findById(picture[i]));
				ps.setDataType(true);
				productStandardMng.save(ps);
			}
		}
	   if(character!=null){
		   for(int i =0;i<character.length;i++){
				ProductStandard ps=new ProductStandard();
				ps.setType(standardMng.findById(character[i]).getType());
				ps.setProduct(bean);
				ps.setStandard(standardMng.findById(character[i]));
				ps.setDataType(false);
				productStandardMng.save(ps);
			}
	   }
		
		if(bean.getCategory().getColorsize()){
			java.util.Set<ProductFashion> pfList = bean.getFashions();
			if(fashId!=null){
				for(ProductFashion ps:pfList){
					if(!Arrays.asList(fashId).contains(ps.getId())){
						productFashionMng.deleteById(ps.getId());
					}
				}
			}else{
				for(ProductFashion ps:pfList){
						productFashionMng.deleteById(ps.getId());
				}
			}
			updateProductFash(bean,fashId,nature,isDefaults,stockCounts,salePrices,marketPrices,costPrices);
		}
		
//		String path = servletContext.getRealPath(Constants.LUCENE_JSPGOU);
//		  try {
//			int count = luceneProductSvc.index(path, null, null, null);
//		} catch (IOException e) {
			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
	log.info("update Product. id={}.", bean.getId());
		return list(ctgId,null,null,null, null,null,
				null,null,null,null,null,pageNo,null,null, request, model);
	}
	@SuppressWarnings("unchecked")
	@RequestMapping("/product/o_site_update.do")
	public String siteupdate(ProductSite bean, Long ctgId,Boolean isOnSale,Boolean isRecommend,
			Boolean isSpecial,Boolean isHotsale,Boolean isNewProduct,
			Long typeId,Double startSalePrice,Double endSalePrice,
			Integer startStock,Integer endStock,Integer pageNo, 
			HttpServletRequest request, ModelMap model) {

		/*if(stockCounts!=null){
	    	  Integer  stockCount =0;
	    	  for(Integer s:stockCounts){
	    		  stockCount+=s; 
	    	  }
	    	  bean.setStockCount(stockCount);
	    }*/
//		bean=productSiteMng.findById(bean.getId());
		bean.setLiRun(bean.getLr());
		ProductSite oldbean=productSiteMng.findById(bean.getId());
		String content="id=" + bean.getId()+ ";";
		if(bean.getCostPrice()!=oldbean.getCostPrice()){
			content=content+"costPrice="+oldbean.getCostPrice()+" to "+bean.getCostPrice()+"; ";
		}
		if(bean.getMarketPrice()!=oldbean.getMarketPrice()){
			content=content+"marketPrice="+oldbean.getMarketPrice()+" to "+bean.getMarketPrice()+"; ";
		}
		if(bean.getSalePrice()!=oldbean.getSalePrice()){
			content=content+"salePrice="+oldbean.getSalePrice()+" to "+bean.getSalePrice()+"; ";
		}
		if(bean.getPtFc()!=oldbean.getPtFc()){
			content=content+"ptFc="+oldbean.getPtFc()+" to "+bean.getPtFc()+"; ";
		}
		if(bean.getDlsFc()!=oldbean.getDlsFc()){
			content=content+"dlsFc="+oldbean.getDlsFc()+" to "+bean.getDlsFc()+"; ";
		}
		if(bean.getAlertInventory()!=oldbean.getAlertInventory()){
			content=content+"alertInventory="+oldbean.getAlertInventory()+" to "+bean.getAlertInventory()+"; ";
		}
		
		cmsLogMng.operating(request, "productSite.log.update", content);
		bean = productSiteMng.update(bean);
		/*String path = servletContext.getRealPath(Constants.LUCENE_JSPGOU);
		  try {
			int count = luceneProductSvc.index(path, null, null, null);
		} catch (CorruptIndexException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (LockObtainFailedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
*/		log.info("update Product. id={}.", bean.getId());
		return this.sitelist(null,null,ctgId, isOnSale, isRecommend, isSpecial, isHotsale, isNewProduct, typeId, startSalePrice, endSalePrice, startStock, endStock, pageNo, request, model);
	}

	@RequestMapping("/product/o_delete.do")
	public String delete(Long[] ids, Long ctgId, Boolean isRecommend,
			Boolean isSpecial, Integer pageNo, HttpServletRequest request,
			ModelMap model) {
		WebErrors errors = validateProDelete(ids, request);
		if (errors.hasErrors()) {
			return errors.showErrorPage(model);
		}
		Product[] beans;
		try {
			beans = productMng.deleteByIds(ids);
//			String path = servletContext.getRealPath(Constants.LUCENE_JSPGOU);
//			  int count = luceneProductSvc.index(path, null, null, null);
//			for (Product bean : beans) {
//				log.info("delete Product. id={}", bean.getId());
//			}
		} catch (Exception e) {
			e.printStackTrace();
			return "redirect:v_error.do";
		}
		return list(ctgId,null,isRecommend, isSpecial, null,null,null,null,null,null,null,pageNo,null,null, request, model);
	}
	
	
	@RequestMapping("/product/v_error.do")
	public String error(HttpServletRequest request, ModelMap model) {
		return "product/error";
	}
	
	@RequestMapping(value = "/product/v_standardTypes_add.do")
	public String standardTypesAdd(Long categoryId,HttpServletRequest request,
			HttpServletResponse response, ModelMap model) {
		    List<StandardType> standardTypeList = standardTypeMng.getList(categoryId);
		    model.addAttribute("standardTypeList", standardTypeList);
		    model.addAttribute("digit", standardTypeList.size());
		    response.setHeader("Cache-Control", "no-cache");
		    response.setContentType("text/json;charset=UTF-8");
		    return "product/standardTypes_add";
	}
	
	@RequestMapping(value = "/product/v_standards_add.do")
	public String standards(Long standardTypeId,HttpServletRequest request,
			HttpServletResponse response, ModelMap model) throws JSONException {
		    List<Standard> sList=standardMng.findByTypeId(standardTypeId);
		    model.addAttribute("sList", sList);
		    model.addAttribute("standardType", standardTypeMng.findById(standardTypeId));
		    response.setHeader("Cache-Control", "no-cache");
		    response.setContentType("text/json;charset=UTF-8");
		    return "product/standards_add";
	}

	@RequestMapping("/product/o_create_index.do")
	public String createIndex(HttpServletRequest request,
			HttpServletResponse response, ModelMap model) {
		String path = servletContext.getRealPath(Constants.LUCENE_JSPGOU);
		boolean success = false;
		try {
			int count = luceneProductSvc.index(path, null, null, null);
			model.addAttribute("count", count);
			success = true;
		} catch (CorruptIndexException e) {
			log.error("", e);
		} catch (LockObtainFailedException e) {
			log.error("", e);
		} catch (IOException e) {
			log.error("", e);
		}
		model.addAttribute("success", success);
		return "product/create_index_result";
	}
	
	
	
	@RequestMapping("/product/o_delFashion.do")//异步删除商品款式(wangzewu)
	public String deleFashion(Long id,Long productId,HttpServletResponse response) throws JSONException{
		Boolean b=productFashionMng.getOneFash(productId);
		JSONObject j=new JSONObject();
		if(b!=null&&b){
			productFashionMng.deleteById(id);
			String path = servletContext.getRealPath(Constants.LUCENE_JSPGOU);
			  try {
				int count = luceneProductSvc.index(path, null, null, null);
			} catch (CorruptIndexException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (LockObtainFailedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			j.put("message", "删除成功！");
			j.put("boo", true);
				ResponseUtils.renderJson(response, j.toString());
				return null;
		}else{
			j.put("message", "必须留一款式！");
			j.put("boo", false);
			ResponseUtils.renderJson(response, j.toString());
			return null;
		}
	}

	
	private void saveProductFash(Product bean,String[] nature,Boolean[] isDefaults,Integer[] stockCounts,
			Double[] salePrices,Double[] marketPrices,Double[] costPrices ){
		if(nature!=null){
			for(int i =0;i<nature.length;i++){
				String[] arr;
				ProductFashion pfash=new ProductFashion();
				pfash.setCreateTime(new Date());
				pfash.setIsDefault(isDefaults[i]);
				pfash.setStockCount(stockCounts[i]);
				pfash.setMarketPrice(marketPrices[i]);
				pfash.setSalePrice(salePrices[i]);
				pfash.setCostPrice(costPrices[i]);
				pfash.setProductId(bean);
				pfash.setNature(nature[i]);
				arr=nature[i].split("_");
				ProductFashion fashion = productFashionMng.save(pfash,arr);
				productFashionMng.addStandard(fashion,arr);
				if(isDefaults[i]){
					/*bean.setCostPrice(costPrices[i]);
					bean.setMarketPrice(marketPrices[i]);
					bean.setSalePrice(salePrices[i]);*/
					productMng.update(bean);
				}
			}
			/*String path = servletContext.getRealPath(Constants.LUCENE_JSPGOU);
			  try {
				int count = luceneProductSvc.index(path, null, null, null);
			} catch (CorruptIndexException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (LockObtainFailedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}*/
		}
	}
	
	
	private void updateProductFash(Product bean,Long[] fashId,String[] nature,Boolean[] isDefaults,Integer[] stockCounts,
			Double[] salePrices,Double[] marketPrices,Double[] costPrices ){
		if(nature!=null){
			for(int i =0;i<nature.length;i++){
				String[] arr;
				ProductFashion pfash;
				if(fashId!=null&&i<fashId.length){
					pfash=productFashionMng.findById(fashId[i]);
					pfash.setCreateTime(new Date());
					pfash.setIsDefault(isDefaults[i]);
					pfash.setStockCount(stockCounts[i]);
					pfash.setMarketPrice(marketPrices[i]);
					pfash.setSalePrice(salePrices[i]);
					pfash.setCostPrice(costPrices[i]);
					pfash.setProductId(bean);
					pfash.setNature(nature[i]);
					arr=nature[i].split("_");
					productFashionMng.updateStandard(pfash, arr);
					if(isDefaults[i]){
						/*bean.setCostPrice(costPrices[i]);
						bean.setMarketPrice(marketPrices[i]);
						bean.setSalePrice(salePrices[i]);*/
						productMng.update(bean);
					}
				}else{
					pfash = new ProductFashion();
					pfash.setCreateTime(new Date());
					pfash.setIsDefault(isDefaults[i]);
					pfash.setStockCount(stockCounts[i]);
					pfash.setMarketPrice(marketPrices[i]);
					pfash.setSalePrice(salePrices[i]);
					pfash.setCostPrice(costPrices[i]);
					pfash.setProductId(bean);
					pfash.setNature(nature[i]);
					arr=nature[i].split("_");
					ProductFashion fashion = productFashionMng.save(pfash,arr);
					productFashionMng.addStandard(fashion,arr);
					if(isDefaults[i]){
						/*bean.setCostPrice(costPrices[i]);
						bean.setMarketPrice(marketPrices[i]);
						bean.setSalePrice(salePrices[i]);*/
						productMng.update(bean);
					}
				}
				
			}
			/*String path = servletContext.getRealPath(Constants.LUCENE_JSPGOU);
			  try {
				int count = luceneProductSvc.index(path, null, null, null);
			} catch (CorruptIndexException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (LockObtainFailedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}*/
			
		}
	}

	private WebErrors validateSave(Product bean, MultipartFile file,
			HttpServletRequest request) {
		WebErrors errors = WebErrors.create(request);
		if (file != null && !file.isEmpty()) {
			String name = file.getOriginalFilename();
			vldImage(name, errors);
		}
		bean.setWebsite(SiteUtils.getWeb(request));
		return errors;
	}

	private WebErrors validateEdit(Long id, HttpServletRequest request) {
		WebErrors errors = WebErrors.create(request);
		errors.ifNull(id, "id");
		vldExist(id, errors);
		return errors;
	}
	private WebErrors validateProEdit(Long id, HttpServletRequest request) {
		WebErrors errors = WebErrors.create(request);
		errors.ifNull(id, "id");
		vldProExist(id, errors);
		return errors;
	}

	private WebErrors validateUpdate(Long id, MultipartFile file,
			HttpServletRequest request) {
		WebErrors errors = WebErrors.create(request);
		errors.ifNull(id, "id");
		if (file != null && !file.isEmpty()) {
			String name = file.getOriginalFilename();
			vldImage(name, errors);
			if (errors.hasErrors()) {
				return errors;
			}
		}
		vldExist(id, errors);
		return errors;
	}

	private WebErrors validateProDelete(Long[] ids, HttpServletRequest request) {
		WebErrors errors = WebErrors.create(request);
		errors.ifEmpty(ids, "ids");
		for (Long id : ids) {
			vldProExist(id, errors);
		}
		return errors;
	}
	private WebErrors validateDelete(Long[] ids, HttpServletRequest request) {
		WebErrors errors = WebErrors.create(request);
		errors.ifEmpty(ids, "ids");
		for (Long id : ids) {
			vldExist(id, errors);
		}
		return errors;
	}

	private void vldExist(Long id, WebErrors errors) {
		if (errors.hasErrors()) {
			return;
		}
		ProductSite entity = productSiteMng.findById(id);
		errors.ifNotExist(entity, ProductSite.class, id);
	}
	private void vldProExist(Long id, WebErrors errors) {
		if (errors.hasErrors()) {
			return;
		}
		Product entity = productMng.findById(id);
		errors.ifNotExist(entity, Product.class, id);
	}

	private void vldImage(String filename, WebErrors errors) {
		if (errors.hasErrors()) {
			return;
		}
		String ext = FilenameUtils.getExtension(filename);
		if (!ImageUtils.isImage(ext)) {
			errors.addErrorString("not support image extension:" + filename);
		}
	}
	
	
	
	
	
	@RequestMapping("/product/o_Saveprices.do")
	public String savePrices(ProductSiteList productSiteList,
			HttpServletRequest request, ModelMap model) {
//		WebErrors errors = validatePrices(wids, salePrice, marketPrice, costPrice, onSale, recommend, special, hotsale, newProduct, request);
//		if (errors.hasErrors()) {
//			return errors.showErrorPage(model);
//		}
		if(productSiteList.getProductSiteList()!=null&& productSiteList.getProductSiteList().size()>0){
		productMng.updatePrices(productSiteList.getProductSiteList());
		model.addAttribute("message", "global.success");
//		String path = servletContext.getRealPath(Constants.LUCENE_JSPGOU);
//		try {
//			int count = luceneProductSvc.index(path, null, null, null);
//		} catch (CorruptIndexException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		} catch (LockObtainFailedException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		} catch (IOException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
		}
		return "redirect:v_site_list.do";
		//return null;
	}
	
	private WebErrors validatePrices(Long[] wids, Double[] salePrice, Double[] marketPrice, Double[] costPrice, Boolean[] onSale, Boolean[] recommend,  Boolean[] special,  Boolean[] hotsale,  Boolean[] newProduct,
			HttpServletRequest request) {
		WebErrors errors = WebErrors.create(request);
		if (errors.ifEmpty(wids, "wids")) {
			return errors;
		}
		if (errors.ifEmpty(salePrice, "salePrice")) {
			return errors;
		}
		if (errors.ifEmpty(marketPrice, "marketPrice")) {
			return errors;
		}
		if (errors.ifEmpty(costPrice, "costPrice")) {
			return errors;
		}
				
		if (wids.length != salePrice.length) {
			errors.addErrorString("wids length not equals salePrice length");
			return errors;
		}
		for (int i = 0, len = wids.length; i < len; i++) {
			vldExist(wids[i], errors);
			if (salePrice[i] == null) {
				salePrice[i] = 0.0;
			}
			if (marketPrice[i] == null) {
				marketPrice[i] = 0.0;
			}
			if (costPrice[i] == null) {
				costPrice[i] = 0.0;
			}
			
			/*if (onSale[i] == null) {
				onSale[i] = false;
			}
			if (recommend[i] == null) {
				recommend[i] = false;
			}
			if (special[i] == null) {
				special[i] = false;
			}
			if (hotsale[i] == null) {
				hotsale[i] = false;
			}
			if (newProduct[i] == null) {
				newProduct[i] = false;
			}*/
		}
		return errors;
	}
	
	
	
	
	
	
	
	
	
	@Autowired
	private StandardMng standardMng;
	@Autowired
	private StandardTypeMng standardTypeMng;
	@Autowired
	private ProductFashionMng productFashionMng;
	@Autowired
	private LuceneProductSvc luceneProductSvc;
	@Autowired
	private ProductTagMng productTagMng;
	@Autowired
	private CategoryMng categoryMng;
	@Autowired
	private ProductMng productMng;
	@Autowired
	private ProductSiteMng productSiteMng;
	@Autowired
	private ProductTypePropertyMng productTypePropertyMng;
	@Autowired
	private ProductTypeMng productTypeMng;
	@Autowired
	private ExendedMng exendedMng;
	@Autowired
	private ProductStandardMng productStandardMng;
	@Autowired
	private CmsLogMng cmsLogMng;
	@Autowired
	private JyspMng jyspMng;
	@Autowired
	private WebsiteMng websiteMng;
	private ServletContext servletContext;	
	public void setServletContext(ServletContext servletContext) {
		this.servletContext = servletContext;
	}
}