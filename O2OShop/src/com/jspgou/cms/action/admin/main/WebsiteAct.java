package com.jspgou.cms.action.admin.main;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import com.jspgou.cms.entity.ShopConfig;
import com.jspgou.cms.entity.ShopMemberGroup;
import com.jspgou.cms.manager.CmsLogMng;
import com.jspgou.cms.manager.ShopConfigMng;
import com.jspgou.cms.manager.ShopMemberGroupMng;
import com.jspgou.common.web.ResponseUtils;
import com.jspgou.core.entity.Account;
import com.jspgou.core.entity.User;
import com.jspgou.core.entity.Website;
import com.jspgou.core.manager.AccountMng;
import com.jspgou.core.manager.GlobalMng;
import com.jspgou.core.manager.WebsiteMng;
import com.jspgou.core.web.SiteUtils;
import com.jspgou.core.web.WebErrors;


@Controller
public class WebsiteAct {
	private static final Logger log = LoggerFactory.getLogger(WebsiteAct.class);
	
	@RequestMapping("/site/site_main.do")
	public String siteMain(ModelMap model) {
		return "site/site_main";
	}
	
	@RequestMapping("/site/v_left.do")
	public String left() {
		return "site/left";
	}
	
	@RequestMapping(value = "/site/v_tree.do")
	public String selectParent(String root, HttpServletRequest request,
			HttpServletResponse response, ModelMap model) {
		log.debug("tree path={}", root);
		boolean isRoot;
		// jquery treeview的根请求为root=source
		if (StringUtils.isBlank(root) || "source".equals(root)) {
			isRoot = true;
		} else {
			isRoot = false;
		}
		model.addAttribute("isRoot", isRoot);
		List<Website> siteList;
		if(isRoot){
			siteList= websiteMng.getTopList();
		}else{
			siteList = websiteMng.getListByParent(Integer.parseInt(root));
		}
		model.addAttribute("list", siteList);
		response.setHeader("Cache-Control", "no-cache");
		response.setContentType("text/json;charset=UTF-8");
		return "site/tree";
	}
	
//	@RequiresPermissions("site:v_list")
	@RequestMapping("/site/v_list.do")
	public String list(Integer root, HttpServletRequest request,
			ModelMap model) {
		List<Website> list;
		if (root == null) {
			list = websiteMng.getTopList();
		} else {
			list = websiteMng.getListByParent(root);
		}
		model.addAttribute("root", root);
		model.addAttribute("list", list);
		return "site/list";
	}
//
//	@RequiresPermissions("site:v_add")
	@RequestMapping("/site/v_add.do")
	public String add(Integer root,ModelMap model) {
		Website parent = null;
		if (root != null) {
			parent = websiteMng.findById(root.longValue());
			model.addAttribute("parent", parent);
			model.addAttribute("root", root);
		}
//		model.addAttribute("config", configMng.get());
		return "site/add";
	}

//	@RequiresPermissions("site:v_edit")
	@RequestMapping("/site/v_edit.do")
	public String edit(Long id,Integer root, HttpServletRequest request, ModelMap model) {
		WebErrors errors = validateEdit(id, request);
		if (errors.hasErrors()) {
			return errors.showErrorPage(model);
		}
		model.addAttribute("website", websiteMng.findById(id));
		return "site/edit";
	}

//	@RequiresPermissions("site:o_save")
	@RequestMapping("/site/o_save.do")
	public String save(Long root,Website bean, Integer uploadFtpId,
			HttpServletRequest request, ModelMap model) throws IOException {
		Website site = SiteUtils.getWeb(request);
		User user = SiteUtils.getUser(request);
		Account account=new Account();
		account.setMoney(0d);
		account.setUsername(user.getUsername());
		account.setStatus(0);
		ShopConfig config=new ShopConfig();
//		WebErrors errors = validateSave(bean, uploadFtpId, request);
//		if (errors.hasErrors()) {
//			return errors.showErrorPage(model);
//		}
		if(root!=null){
			Website parent=websiteMng.findById(root);
			if(parent!=null){
				bean.setParent(parent);
			}
		}
		bean.setAccount(account);
		bean.setGlobal(globalMng.findById(1l));
		bean = websiteMng.save(bean);
		config.setWebsite(bean);
		ShopMemberGroup registerGroup = shopMemberGroupMng.findById(1l);
		config.setRegisterGroup(registerGroup);
		config.setRegisterAuto(true);
		config.setMarketPriceName("市场价格");
		config.setFavoriteSize(200);
		config.setShopPriceName("商城价格");
		shopConfigMng.save(config);
		log.info("save CmsSite id={}", bean.getId());
		cmsLogMng.operating(request, "cmsSite.log.save", "id=" + bean.getId()
				+ ";name=" + bean.getName()+";账户号="+bean.getAccount().getId());
		model.addAttribute("root", root);
		return "redirect:v_list.do";
	}
//
//	@RequiresPermissions("site:o_update")
	@RequestMapping("/site/o_update.do")
	public String update(Integer root, Website bean, Integer uploadFtpId, Integer pageNo,
			HttpServletRequest request, ModelMap model) {
//		WebCoreErrors errors = validateUpdate(bean.getId(), uploadFtpId, request);
//		if (errors.hasErrors()) {
//			return errors.showErrorPage(model);
//		}
		bean = websiteMng.update(bean);
		log.info("update Website id={}.", bean.getId());
		return list(root, request, model);
	}
//
//	@RequiresPermissions("site:o_delete")
	@RequestMapping("/site/o_delete.do")
	public String delete(Long[] ids, Integer pageNo,
			HttpServletRequest request, ModelMap model) {
		WebErrors errors = validateDelete(ids, request);
		if (errors.hasErrors()) {
			return errors.showErrorPage(model);
		}
		Website[] beans = websiteMng.deleteByIds(ids);
		for (Website bean : beans) {
//			log.info("delete Website id={}", bean.getId());
		}
		return list(pageNo, request, model);
	}
//	
//	@RequiresPermissions("site:v_refer")
//	@RequestMapping(value = "/site/v_refer.do")
//	public String siteReferTree(Integer id,HttpServletRequest request,HttpServletResponse response, ModelMap model) {
//		Website site=websiteMng.findById(id);
//		List<Website> siteList= websiteMng.getTopList();
//		Integer[]referSiteIds=Website.fetchIds(site.getRefers());
//		model.addAttribute("siteList", siteList);
//		model.addAttribute("referSiteIds", referSiteIds);
//		response.setHeader("Cache-Control", "no-cache");
//		response.setContentType("text/json;charset=UTF-8");
//		return "site/sites_refer";
//	}
//	
//	@RequiresPermissions("site:o_refer")
//	@RequestMapping("/site/o_refer.do")
//	public void ajaxRefer(Integer siteId,Integer[] referIds, HttpServletResponse response) {
//		String pass;
//		if(siteId==null){
//			ResponseUtils.renderJson(response, "false");
//		}
//		Website site=websiteMng.findById(siteId);
//		if(site==null){
//			ResponseUtils.renderJson(response, "false");
//		}
//		if(referIds==null){
//			pass="false";
//		}else{
//			websiteMng.updateRefers(siteId, referIds);
//			pass="true";
//		}
//		ResponseUtils.renderJson(response, pass);
//	}
//
//	@RequiresPermissions("site:v_checkDomain")
	@RequestMapping("/site/v_checkDomain.do")
	public void checkDomainJson(Integer siteId,String domain, HttpServletResponse response) {
		String pass;
	
			if (StringUtils.isBlank(domain)) {
				pass = "false";
			} else {
				Website s=websiteMng.findByDomain(domain);
				if(s==null){
					pass= "true";
				}else{
					if(s.getId().equals(siteId)){
						pass= "true";
					}else{
						pass= "false";
					}
				}
			}
		ResponseUtils.renderJson(response, pass);
	}
//	
//	@RequiresPermissions("site:v_checkAccessPath")
//	@RequestMapping("/site/v_checkAccessPath.do")
//	public void checkAccessPathJson(Integer siteId,String accessPath, HttpServletResponse response) {
//		Global config=configMng.get();
//		String pass;
//		//外网不检查路径唯一性
//		if(!config.getInsideSite()){
//			pass="true";
//		}else{
//			if (StringUtils.isBlank(accessPath)) {
//				pass = "false";
//			} else {
//				Website s=websiteMng.findByAccessPath(accessPath);
//				if(s==null){
//					pass= "true";
//				}else{
//					if(s.getId().equals(siteId)){
//						pass= "true";
//					}else{
//						pass= "false";
//					}
//				}
//			}
//		}
//		ResponseUtils.renderJson(response, pass);
//	}
//	
//	@RequiresPermissions("site:v_checkMaster")
//	@RequestMapping("/site/v_checkMaster.do")
//	public void checkSiteMasterJson(Integer siteId,HttpServletResponse response) {
//		String pass = "true";
//		List<Website>siteList=websiteMng.getListByMaster(true);
//		if(siteList.size()>0){
//			pass="false";
//		}
//		if(siteId!=null){
//			Website site=websiteMng.findById(siteId);
//			if(siteList.contains(site)){
//				pass="true";
//			}
//		}
//		ResponseUtils.renderJson(response, pass);
//	}
//
//	private WebErrors validateSave(Website bean, Integer uploadFtpId,
//			HttpServletRequest request) {
//		WebErrors errors = WebErrors.create(request);
//		if (vldFtpExist(uploadFtpId, errors)) {
//			return errors;
//		}
//		// 加上config信息
//		bean.setConfig(configMng.get());
//		return errors;
//	}
//
	private WebErrors validateEdit(Long id, HttpServletRequest request) {
		WebErrors errors = WebErrors.create(request);
		if (vldExist(id, errors)) {
			return errors;
		}
		return errors;
	}
//
//	private WebCoreErrors validateUpdate(Integer id, Integer uploadFtpId,
//			HttpServletRequest request) {
//		WebCoreErrors errors = WebCoreErrors.create(request);
//		if (vldExist(id, errors)) {
//			return errors;
//		}
//		if (vldFtpExist(uploadFtpId, errors)) {
//			return errors;
//		}
//		return errors;
//	}
//
	private WebErrors validateDelete(Long[] ids, HttpServletRequest request) {
		WebErrors errors = WebErrors.create(request);
		errors.ifEmpty(ids, "ids");
		for (Long id : ids) {
			vldExist(id, errors);
		}
		return errors;
	}
//
////	private boolean vldFtpExist(Integer id, WebCoreErrors errors) {
////		if (id == null) {
////			return false;
////		}
////		Ftp entity = ftpMng.findById(id);
////		return errors.ifNotExist(entity, Ftp.class, id);
////	}
//
	private boolean vldExist(Long id, WebErrors errors) {
		if (errors.ifNull(id, "id")) {
			return true;
		}
		Website entity = websiteMng.findById(id);
		if (errors.ifNotExist(entity, Website.class, id)) {
			return true;
		}
		return false;
	}

	@Autowired
	private GlobalMng globalMng;
	@Autowired
	private ShopMemberGroupMng shopMemberGroupMng;
	@Autowired
	private WebsiteMng websiteMng;
	@Autowired
	private CmsLogMng cmsLogMng;
	@Autowired
	private ShopConfigMng shopConfigMng;
}