package com.jspgou.cms.action.admin.main;

import static com.jspgou.common.page.SimplePage.cpn;

import java.io.IOException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.Random;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sourceforge.pinyin4j.format.exception.BadHanyuPinyinOutputFormatCombination;

import org.apache.commons.io.FilenameUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.json.JSONException;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.jspgou.cms.entity.Address;
import com.jspgou.cms.entity.Gys;
import com.jspgou.cms.entity.Jysp;
import com.jspgou.cms.entity.KetaUser;
import com.jspgou.cms.entity.Organization;
import com.jspgou.cms.entity.ProductSite;
import com.jspgou.cms.entity.ProductType;
import com.jspgou.cms.entity.Psqy;
import com.jspgou.cms.entity.ShopAdmin;
import com.jspgou.cms.entity.ShopDictionary;
import com.jspgou.cms.manager.AddressMng;
import com.jspgou.cms.manager.CategoryMng;
import com.jspgou.cms.manager.CmsFileMng;
import com.jspgou.cms.manager.CmsLogMng;
import com.jspgou.cms.manager.GysMng;
import com.jspgou.cms.manager.JyspMng;
import com.jspgou.cms.manager.KetaUserMng;
import com.jspgou.cms.manager.OrganizationMng;
import com.jspgou.cms.manager.ProductSiteMng;
import com.jspgou.cms.manager.ProductTypeMng;
import com.jspgou.cms.manager.PsqyMng;
import com.jspgou.cms.manager.ShopDictionaryMng;
import com.jspgou.cms.web.threadvariable.AdminThread;
import com.jspgou.common.page.Pagination;
import com.jspgou.common.upload.FileRepository;
import com.jspgou.common.web.CookieUtils;
import com.jspgou.common.web.RequestUtils;
import com.jspgou.common.web.ResponseUtils;
import com.jspgou.core.entity.Account;
import com.jspgou.core.entity.User;
import com.jspgou.core.entity.Website;
import com.jspgou.core.manager.WebsiteMng;
import com.jspgou.core.security.CmsAuthorizingRealm;
import com.jspgou.core.security.CmsAuthorizingRealm.HashPassword;
import com.jspgou.core.web.ChangeZhongWenToPinYin;
import com.jspgou.core.web.SiteUtils;
import com.jspgou.core.web.WebErrors;

/**
* This class should preserve.
* @preserve
*/
@Controller
public class GysAct {
	private static final Logger log = LoggerFactory.getLogger(GysAct.class);

	@RequestMapping("/gys/v_list.do")
	public String list(Long siteId,String organizationName,String username,String companyName,Integer pageNo, HttpServletRequest request,ModelMap model) {
		
			Website website=SiteUtils.getWeb(request);
//			 = manager.getPageByIsDisabledAndWebsiteId(website.getId(), false, cpn(pageNo), CookieUtils.getPageSize(request));
	   Pagination pagination = gysMng.getPageByIsDisabled(organizationName,siteId,username,companyName,false, cpn(pageNo), CookieUtils.getPageSize(request));
		model.addAttribute("pagination", pagination);
		List siteList=websiteMng.getAllList();
		model.addAttribute("organizationName", organizationName);
		model.addAttribute("username", username);
		model.addAttribute("siteId", siteId);
		model.addAttribute("siteList", siteList);
		model.addAttribute("companyName", companyName);
		return "gys/list";
	}
	@RequestMapping("/gys/v_gysps_list.do")
	public String psqyList(Long siteId,String username,String companyName,Integer pageNo, HttpServletRequest request,ModelMap model) {
		
		Website website=SiteUtils.getWeb(request);
		List siteList=websiteMng.getAllList();
		Pagination pagination =psqyMng.getGysPage(siteId, username, companyName,cpn(pageNo), CookieUtils.getPageSize(request));
		model.addAttribute("pagination", pagination);
		model.addAttribute("username", username);
		model.addAttribute("companyName", companyName);
		model.addAttribute("siteList", siteList);
		model.addAttribute("siteId", siteId);
		return "gys/gyspsqy_list";
	}
	@RequestMapping("/gys/v_gysjysp_list.do")
	public String jyspList(Long siteId,String username,String companyName,String productName,Long typeId,Integer pageNo, HttpServletRequest request,ModelMap model) {
		
		Website website=SiteUtils.getWeb(request);
		List siteList=websiteMng.getAllList();
		Pagination pagination =jyspMng.getJyspPage(siteId, username, companyName, productName, typeId, cpn(pageNo), CookieUtils.getPageSize(request));
		model.addAttribute("pagination", pagination);
		model.addAttribute("username", username);
		model.addAttribute("typeId", typeId);
		List<ProductType> typeList = productTypeMng.getList();
		model.addAttribute("typeList", typeList);
		model.addAttribute("companyName", companyName);
		model.addAttribute("productName", productName);
		model.addAttribute("siteList", siteList);
		model.addAttribute("siteId", siteId);
		return "gys/gysjysp_list";
	}
	@RequestMapping("/gys/v_gysps_add.do")
	public String psqyAdd(Gys gys,Long siteId,String username,String companyName,Integer pageNo, HttpServletRequest request,ModelMap model) {
		
//		Website website=SiteUtils.getWeb(request);
		//所处省份
		List<Address> plist=addressMng.getListById(null);
		Pagination pagination =psqyMng.getGysPageByGys(gys, username, companyName,cpn(pageNo), CookieUtils.getPageSize(request));
		model.addAttribute("pagination", pagination);
		model.addAttribute("plist", plist);
		model.addAttribute("username", username);
		model.addAttribute("companyName", companyName);
		model.addAttribute("gys", gys);
		Website website=SiteUtils.getWeb(request);
		model.addAttribute("website", website);
		return "gys/gyspsqy_add";
	}
	@RequestMapping("/gys/v_gysjysp_add.do")
	public String jyspAdd(String productName,String brandName, Long ctgId,Boolean isOnSale,Boolean isRecommend,
			Boolean isSpecial,Boolean isHotsale,Boolean isNewProduct,
			Long typeId,Double startSalePrice,Double endSalePrice,
			Integer startStock,Integer endStock,Jysp jysp,Long siteId,String username,String companyName,Integer pageNo, HttpServletRequest request,ModelMap model) {
		Gys gys=gysMng.findById(jysp.getGys().getId());
		Pagination pagination =jyspMng.getPageByGysId(gys, cpn(pageNo), CookieUtils.getPageSize(request));
		Website web = SiteUtils.getWeb(request);
		if (ctgId != null) {
			model.addAttribute("category", categoryMng.findById(ctgId));
		}
		Pagination sitepropage = productSiteMng.getPageNotJy(gys,gys.getWebsite().getId(),
				ctgId, productName,brandName,true, isRecommend, isSpecial,isHotsale,isNewProduct,typeId,
				startSalePrice,endSalePrice,startStock,endStock,
				cpn(pageNo), CookieUtils.getPageSize(request));
		model.addAttribute("pagination", pagination);
		model.addAttribute("sitepropage", sitepropage);
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
		model.addAttribute("ctgId", ctgId);
		model.addAttribute("gys", gys);
		return "gys/gysjysp_add";
	}
	@RequestMapping("/gys/o_gysjysp_save.do")
	public String jyspSave(Long[] ids,Long ctgId,Boolean isOnSale,Boolean isRecommend,
			Boolean isSpecial,Boolean isHotsale,Boolean isNewProduct,
			Long typeId,Double startSalePrice,Double endSalePrice,
			Integer startStock,Integer endStock,Jysp jysp,Long siteId,String username,String companyName,Integer pageNo, HttpServletRequest request,ModelMap model) {
		if(ids!=null && ids.length>0){
		Gys gys=gysMng.findById(jysp.getGys().getId());
		
		User user=SiteUtils.getUser(request);
		
		
		for(Long id:ids){
	    ProductSite	productSite=productSiteMng.findById(id);
	    if(productSite!=null &&productSite.getId()>0){
	    	jysp=jyspMng.findByProductIdAndKetaUserId(productSite.getId(), gys.getKetaUser().getId());
	    	if(jysp==null || jysp.getId()==null){
	    				jysp=new Jysp();
	    				jysp.setUsername(user.getUsername());
	    				jysp.setGys(gys);
	    				Timestamp ts = new Timestamp(System.currentTimeMillis());  
	    				jysp.setCreateTime(ts);
	    				jysp.setProductSite(productSite);
	    	}
		jysp=jyspMng.save(jysp);
		String content="操作人："+user.getUsername()+";id:"+jysp.getId()+";添加("+jysp.getGys().getCompanyName()+")经营商品："+jysp.getProductName();
		cmsLogMng.operating(request, "gysjysp.log.save", content);
	    }
		}
		}
		return this.jyspAdd(null,null,ctgId, isOnSale, isRecommend, isSpecial, isHotsale, isNewProduct, typeId, startSalePrice, endSalePrice, startStock, endStock, jysp, siteId, username, companyName, pageNo, request, model);
	}
	@RequestMapping("/gys/o_gysjysp_delete.do")
	public String jyspSave(Long ctgId,Boolean isOnSale,Boolean isRecommend,
			Boolean isSpecial,Boolean isHotsale,Boolean isNewProduct,
			Long typeId,Double startSalePrice,Double endSalePrice,
			Integer startStock,Integer endStock,Jysp jysp,Long siteId,String username,String companyName,Integer pageNo, HttpServletRequest request,ModelMap model) {
		User user=SiteUtils.getUser(request);
		WebErrors errors = WebErrors.create(request);
		errors.ifNull(jysp.getId(), "id");		
		Jysp entity = jyspMng.findById(jysp.getId());
		errors.ifNotExist(entity, Jysp.class, jysp.getId());
		if(errors.hasErrors()){
			return errors.showErrorPage(model); 
		}
		jysp=jyspMng.delete(jysp.getId());
		String content="操作人："+user.getUsername()+";id:"+jysp.getId()+";删除("+jysp.getGys().getCompanyName()+")经营商品："+jysp.getProductName();
		cmsLogMng.operating(request, "gysjysp.log.delete", content);
		return this.jyspAdd(null,null,ctgId, isOnSale, isRecommend, isSpecial, isHotsale, isNewProduct, typeId, startSalePrice, endSalePrice, startStock, endStock, jysp, siteId, username, companyName, pageNo, request, model);
	}
	
	// 获得所选择省份的全部城市
		@RequestMapping("/gys/findAllCity.do")
		public void findAllCity(Long id,String gysId, HttpServletRequest request,
				HttpServletResponse response, ModelMap model) {
			// 所属省份的城市
			List<Address> clist =addressMng.getListByIdNotGys(id, gysId);
			Long ids[] = new Long[clist.size()];
			String citys[] = new String[clist.size()];
			for (int i=0,j=clist.size(); i<j; i++) {
				Address city = clist.get(i);
				ids[i] = city.getId();
				citys[i] = city.getName();
			}
			JSONObject json = new JSONObject();
			try {
				json.put("ids", ids);
				json.put("citys", citys);
				json.put("success", true);
			} catch (JSONException e) {
				try {
					json.put("success", false);
				} catch (JSONException e1) {
					e1.printStackTrace();
				}
				e.printStackTrace();
			}
			ResponseUtils.renderJson(response, json.toString());
		}
		
		// 获得所选择市的全部县
		@RequestMapping("/gys/findAllCountry.do")
		public void findAllArea(Long id,String gysId, HttpServletRequest request,
				HttpServletResponse response, ModelMap model) {
			// 所选择市的全部县
			List<Address> alist =addressMng.getListByIdNotGys(id, gysId);
			Long ids[] = new Long[alist.size()];
			String areas[] = new String[alist.size()];
			for (int i=0,j=alist.size(); i<j; i++) {
				Address area = alist.get(i);
				ids[i] = area.getId();
				areas[i] = area.getName();
			}
			JSONObject json = new JSONObject();
			try {
				json.put("ids", ids);
				json.put("areas", areas);
				json.put("success", true);
			} catch (JSONException e) {
				try {
					json.put("success", false);
				} catch (JSONException e1) {
					e1.printStackTrace();
				}
				e.printStackTrace();
			}
			ResponseUtils.renderJson(response, json.toString());
		}
	@RequestMapping("/gys/v_checkUserName.do")
	public void checkUserNameJson(Gys bean, HttpServletResponse response) {
		String pass;
		KetaUser ketaUser = bean.getKetaUser();
			if (StringUtils.isBlank(ketaUser.getUsername())) {
				pass = "false";
			} else {
				KetaUser s=ketaUserMng.findByUserName(ketaUser.getUsername());
				if(s==null){
					pass= "true";
				}else{
					pass= "false";
				}
			}
		ResponseUtils.renderJson(response, pass);
	}
	@RequestMapping("/gys/v_add.do")
	public String add(HttpServletRequest request, ModelMap model) {
		List<Website> allsite = websiteMng.getAllList();
		model.put("allsite", allsite);
		return "gys/add";
	}
	@RequestMapping("/gys/v_mapadd.do")
	public String add(HttpServletRequest request,Gys bean, ModelMap model) {
		if(bean!=null&&bean.getId()!=null&&bean.getId().length()>0){
			bean = gysMng.findById(bean.getId());
			}else{
				bean= new Gys();
			}
		model.put("gys", bean);
		return "gys/mapadd";
	}
	@RequestMapping("/gys/o_upload_attachment.do")
	public String uploadAttachment(
			@RequestParam(value = "attachmentFile", required = false) MultipartFile file,
			String attachmentNum, HttpServletRequest request, ModelMap model) {
		Website site=SiteUtils.getWeb(request);
		User user= SiteUtils.getUser(request);
		String origName = file.getOriginalFilename();
		String ext = FilenameUtils.getExtension(origName).toLowerCase(
				Locale.ENGLISH);
		WebErrors errors = validateUpload(file,request);
		if (errors.hasErrors()) {
			model.addAttribute("error", errors.getErrors().get(0));
			return "gys/attachment_iframe";
		}
		// TODO 检查允许上传的后缀
		try {
			String fileUrl;
	
				String ctx = request.getContextPath();
				fileUrl = fileRepository.storeByExt(site.getUploadUrl(), ext,
						file);
				// 加上部署路径
				fileUrl = ctx + fileUrl;
//			cmsUserMng.updateUploadSize(user.getId(), Integer.parseInt(String.valueOf(file.getSize()/1024)));
				cmsFileMng.saveFileByPath(fileUrl, origName, false);
			model.addAttribute("attachmentPath", fileUrl);
			model.addAttribute("attachmentName", origName);
			model.addAttribute("attachmentNum", attachmentNum);
		} catch (IllegalStateException e) {
			model.addAttribute("error", e.getMessage());
			log.error("upload file error!", e);
		} catch (IOException e) {
			model.addAttribute("error", e.getMessage());
			log.error("upload file error!", e);
		}
		return "gys/attachment_iframe";
	}	
	private WebErrors validateUpload(MultipartFile file,
			HttpServletRequest request) {
		String origName = file.getOriginalFilename();
		User user= SiteUtils.getUser(request);
		String ext = FilenameUtils.getExtension(origName).toLowerCase(Locale.ENGLISH);
		int fileSize = (int) (file.getSize() / 1024);
		WebErrors errors = WebErrors.create(request);
		if (errors.ifNull(file, "file")) {
			return errors;
		}
//		//非允许的后缀
//		if(!user.isAllowSuffix(ext)){
//			errors.addErrorCode("upload.error.invalidsuffix", ext);
//			return errors;
//		}
//		//超过附件大小限制
//		if(!user.isAllowMaxFile((int)(file.getSize()/1024))){
//			errors.addErrorCode("upload.error.toolarge",origName,user.getGroup().getAllowMaxFile());
//			return errors;
//		}
//		//超过每日上传限制
//		if (!user.isAllowPerDay(fileSize)) {
//			long laveSize=user.getGroup().getAllowPerDay()-user.getUploadSize();
//			if(laveSize<0){
//				laveSize=0;
//			}
//			errors.addErrorCode("upload.error.dailylimit", laveSize);
//		}
		return errors;
	}
	@RequestMapping("/gys/v_edit.do")
	public String edit(String id, HttpServletRequest request, ModelMap model) {
		WebErrors errors = validateEdit(id, request);
		if (errors.hasErrors()) {
			return errors.showErrorPage(model);
		}
		List<Website> allsite = websiteMng.getAllList();
		model.put("allsite", allsite);
		model.addAttribute("gys", gysMng.findById(id));
		return "gys/edit";
	}
	@RequestMapping("/gys/v_gyssh.do")
	public String gyssh(String id, HttpServletRequest request, ModelMap model) {
		WebErrors errors = validateEdit(id, request);
		if (errors.hasErrors()) {
			return errors.showErrorPage(model);
		}
		List<Website> allsite = websiteMng.getAllList();
		model.put("allsite", allsite);
		model.addAttribute("gys", gysMng.findById(id));
		return "gys/gyssh";
	}
	
	private WebErrors validateEdit(String id, HttpServletRequest request) {
		WebErrors errors = WebErrors.create(request);
		errors.ifNull(id, "id");
		vldExist(id, errors);
		return errors;
	}
	
	private boolean vldExist(String id, WebErrors errors) {
		Gys entity = gysMng.findById(id);
		return errors.ifNotExist(entity, Gys.class, id);
	}
	private boolean vldPsqyExist(Long id, WebErrors errors) {
		Psqy entity = psqyMng.findById(id);
		return errors.ifNotExist(entity, Psqy.class, id);
	}

	
	@RequestMapping("/gys/o_save.do")
	public String save(Gys bean,Long siteId, String[] picPaths, String[] picDescs, 
			HttpServletRequest request, ModelMap model) {
		Organization organization=organizationMng.findByName("供应商");
		WebErrors errors = validateSave( bean,organization, request);
		if (errors.hasErrors()) {
			return errors.showErrorPage(model);
		}
		Website website=websiteMng.findById(bean.getWebsite().getId());
		String content="";
		KetaUser ketaUser=bean.getKetaUser();
		ketaUser.setUsername(bean.getKetaUser().getUsername());
		ketaUser.setOrganization(organization);
		HashPassword hashPassword = CmsAuthorizingRealm.encryptPassword(ketaUser.getPainPass());
		ketaUser.setSalt(hashPassword.salt);
		ketaUser.setPassword(hashPassword.password);
		ketaUser.setRealname(bean.getCompanyName());
		ketaUser.setPhone(bean.getPhone());
		ketaUserMng.save(ketaUser);
		User user = SiteUtils.getUser(request);
		bean.setUser(user);
		bean.setCreateTime(new Date());
		bean.setWebsite(website);
		Website site = SiteUtils.getWeb(request);
		Account account=new Account();
		account.setMoney(0d);
		account.setUsername(user.getUsername());
		account.setStatus(0);
		bean.setAccount(account);
		bean = gysMng.save(bean,picPaths, picDescs);
		//处理附件
		cmsFileMng.updateFileByPaths(null,picPaths,null,null,null,null,true,bean);
		content=content+"id:"+bean.getId()+";新增供应商用户："+bean.getKetaUser().getRealname()+";站点id:"+website.getId()+";账户号="+bean.getAccount().getId();
		cmsLogMng.operating(request, "gys.log.save", content);
		return "redirect:v_list.do";
	}
	@RequestMapping("/gys/o_gys_psqy_save.do")
	public String savepsyqy(Psqy bean, Long provinceId,Long cityId,
			Long countryId,Long streetId,
			HttpServletRequest request, ModelMap model) {
		User user = SiteUtils.getUser(request);
		Address province = addressMng.findById(provinceId);
		Address city = addressMng.findById(cityId);
		Address country = addressMng.findById(countryId);
//		Address street = addressMng.findById(streetId);
		bean.setProvince(province);
		bean.setCity(city);
		bean.setCountry(country);
//		bean.setProvince(street);
		Timestamp ts = new Timestamp(System.currentTimeMillis());  
		bean.setCreateTime(ts);
		bean.setGys(gysMng.findById(bean.getGys().getId()));
		bean.setUser(user);
		bean=psqyMng.save(bean);
		String content="操作人："+user.getUsername()+";id:"+bean.getId()+";新增("+bean.getGys().getCompanyName()+")配送区域："+bean.getProvince().getName()+"/"+bean.getCity().getName()+"/"+bean.getCountry().getName();
		cmsLogMng.operating(request, "gyspsyqy.log.save", content);
		return psqyAdd(bean.getGys(), null,  null, null, null, request, model);
	}
	@RequestMapping("/gys/o_gys_psqy_delete.do")
	public String deletepsyqy(Psqy bean, 
			HttpServletRequest request, ModelMap model) {
		User user = SiteUtils.getUser(request);
		WebErrors errors = validatePsqy(bean, request);
		if (errors.hasErrors()) {
			return errors.showErrorPage(model);
		}
		bean=psqyMng.findById(bean.getId());
		String content="操作人："+user.getUsername()+";id:"+bean.getId()+";删除("+bean.getGys().getCompanyName()+")配送区域："+bean.getProvince().getName()+"/"+bean.getCity().getName()+"/"+bean.getCountry().getName();
		bean=psqyMng.deleteById(bean.getId());
		cmsLogMng.operating(request, "gyspsyqy.log.delete", content);
		return psqyAdd(bean.getGys(), null,  null, null, null, request, model);
	}
	private WebErrors validateSave(Gys bean,Organization organization,HttpServletRequest request) {
		WebErrors errors = WebErrors.create(request);
//		Dls lsfc=manager.findByFctypeId(bean.getFctypeId().getId(), bean.getWebsite().getId());
		if(organization==null || organization.getId()<1){
		errors.addError("供应商组织机构不存在");
		}
		String pass="";
		KetaUser ketaUser = bean.getKetaUser();
			if (StringUtils.isBlank(ketaUser.getUsername())) {
				pass = "用户名已存在请重新输入";
			} else {
				KetaUser s=ketaUserMng.findByUserName(ketaUser.getUsername());
				if(s==null){
				}else{
				pass= "用户名已存在请重新输入";
				}
			}
			if(pass.length()>0){
				errors.addError(pass);
				}
		return errors;
	}
	
	
	
	@RequestMapping("/gys/o_update.do")
	public String update(Gys bean,String[] picPaths, String[] picDescs, String text,HttpServletRequest request, ModelMap model) {
		WebErrors errors = validateUpdate(bean, request);
		if (errors.hasErrors()) {
			return errors.showErrorPage(model);
		}
		String content="";
		String s="";
		Gys oldbean=null;
		oldbean=gysMng.findById(bean.getId());
		User user = SiteUtils.getUser(request);
			content="操作人："+user.getUsername()+";Id="+bean.getId()+";供应商名称："+oldbean.getKetaUser().getRealname()+"-to-"+bean.getCompanyName()+";";
			cmsLogMng.operating(request, "gys.log.update", content);
			KetaUser ketaUser=oldbean.getKetaUser();
			ketaUser.setRealname(bean.getCompanyName());
			ketaUser.setPhone(bean.getPhone());
			ketaUserMng.updateByUpdater(ketaUser);
		    bean = gysMng.updateByUpdater(bean,picPaths, picDescs);
		
		log.info("update brand. id={}.", bean.getId());
		cmsLogMng.operating(request, "gys.log.update", "id=" + bean.getId()
				+ ";操作人="+user.getUsername()+";khh=" + oldbean.getKhh()+"-to-"+bean.getKhh()+";khhzh="+oldbean.getKhhzh()+"-to-"+bean.getKhhzh());
		
		return "redirect:v_list.do";
	}
	@RequestMapping("/gys/o_update_tg.do")
	public String updatetg(Gys bean,String[] picPaths, String[] picDescs, String text,HttpServletRequest request, ModelMap model) {
		WebErrors errors = validateUpdate(bean, request);
		if (errors.hasErrors()) {
			return errors.showErrorPage(model);
		}
		String content="";
		String s="";
		Gys oldbean=null;
		oldbean=gysMng.findById(bean.getId());
		User user = SiteUtils.getUser(request);
		content="操作人："+user.getUsername()+";Id="+bean.getId()+";供应商名称："+oldbean.getKetaUser().getRealname()+"-to-"+bean.getCompanyName()+";";
		cmsLogMng.operating(request, "gys.log.update", content);
		KetaUser ketaUser=oldbean.getKetaUser();
		ketaUser.setOrganization(organizationMng.findByName("供应商"));
		ketaUser.setRealname(bean.getCompanyName());
		ketaUser.setPhone(bean.getPhone());
		ketaUserMng.updateByUpdater(ketaUser);
		bean = gysMng.updateByUpdater(bean,picPaths, picDescs);
		
		log.info("update brand. id={}.", bean.getId());
		cmsLogMng.operating(request, "gys.log.update", "id=" + bean.getId()
				+ ";操作人="+user.getUsername()+";khh=" + oldbean.getKhh()+"-to-"+bean.getKhh()+";khhzh="+oldbean.getKhhzh()+"-to-"+bean.getKhhzh());
		
		return "redirect:v_list.do";
	}
	private WebErrors validateUpdate(Gys bean, HttpServletRequest request) {
		WebErrors errors = WebErrors.create(request);
		String id = bean.getId();
		
		errors.ifNull(id, "id");
		vldExist(id, errors);
		return errors;
	}
	private WebErrors validatePsqy(Psqy bean, HttpServletRequest request) {
		WebErrors errors = WebErrors.create(request);
		Long id = bean.getId();
		
		errors.ifNull(id, "id");
		vldPsqyExist(id, errors);
		return errors;
	}
	
	@RequestMapping("/gys/o_delete.do")
	public String delete(Gys bean,
			HttpServletRequest request, ModelMap model) {
		WebErrors errors = validateUpdate(bean, request);
		if (errors.hasErrors()) {
			return errors.showErrorPage(model);
		}
		String content="";
		String s="";
		Gys oldbean=null;
		oldbean=gysMng.findById(bean.getId());
		User user = SiteUtils.getUser(request);
			content="操作人："+user.getUsername()+";Id="+bean.getId()+";供应商名称："+oldbean.getKetaUser().getRealname();
			cmsLogMng.operating(request, "gys.log.delete", content);
			oldbean.setIsDisabled(true);
			KetaUser ketaUser=oldbean.getKetaUser();
			ketaUser.setStatus("disabled");
			ketaUserMng.updateByUpdater(ketaUser);
		bean = gysMng.updateByUpdater(oldbean);
		
		log.info("update brand. id={}.", bean.getId());
		
		return "redirect:v_list.do";
	}
	
	private WebErrors validateDelete(String[] ids, HttpServletRequest request) {
		WebErrors errors = WebErrors.create(request);
		errors.ifEmpty(ids, "ids");
		for (String id : ids) {
			vldExist(id, errors);
		}
		return errors;
	}
	 public String getStringRandom(int length) {  
         
	        String val = "";  
	        Random random = new Random();  
	          
	        //参数length，表示生成几位随机数  
	        for(int i = 0; i < length; i++) {  
	              
	            String charOrNum = random.nextInt(2) % 2 == 0 ? "char" : "num";  
	            //输出字母还是数字  
	            if( "char".equalsIgnoreCase(charOrNum) ) {  
	                //输出是大写字母还是小写字母  
	                int temp = random.nextInt(2) % 2 == 0 ? 65 : 97;  
	                val += (char)(random.nextInt(26) + temp);  
	            } else if( "num".equalsIgnoreCase(charOrNum) ) {  
	                val += String.valueOf(random.nextInt(10));  
	            }  
	        }  
	        return val;  
	    } 
	
	
	@Autowired
	private GysMng gysMng;
	@Autowired
	private KetaUserMng ketaUserMng;
	
	@Autowired
	private PsqyMng psqyMng;
	@Autowired
	private JyspMng jyspMng;
	
	@Autowired
	private CmsLogMng cmsLogMng;
	@Autowired
	private CmsFileMng cmsFileMng;
	@Autowired
	private WebsiteMng websiteMng;
	@Autowired
	private OrganizationMng organizationMng;
	@Autowired
	private FileRepository fileRepository;
	@Autowired
	private AddressMng addressMng;
	@Autowired
	private CategoryMng categoryMng;
	@Autowired
	private  ProductSiteMng productSiteMng;
	@Autowired
	private ProductTypeMng productTypeMng;
}