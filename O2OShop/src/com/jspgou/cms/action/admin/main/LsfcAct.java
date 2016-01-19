package com.jspgou.cms.action.admin.main;

import static com.jspgou.common.page.SimplePage.cpn;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import com.jspgou.cms.entity.Lsfc;
import com.jspgou.cms.entity.ShopAdmin;
import com.jspgou.cms.entity.ShopDictionary;
import com.jspgou.cms.manager.CmsLogMng;
import com.jspgou.cms.manager.LsfcMng;
import com.jspgou.cms.manager.ShopDictionaryMng;
import com.jspgou.cms.web.threadvariable.AdminThread;
import com.jspgou.common.page.Pagination;
import com.jspgou.common.web.CookieUtils;
import com.jspgou.common.web.ResponseUtils;
import com.jspgou.core.entity.User;
import com.jspgou.core.entity.Website;
import com.jspgou.core.manager.WebsiteMng;
import com.jspgou.core.web.SiteUtils;
import com.jspgou.core.web.WebErrors;

/**
* This class should preserve.
* @preserve
*/
@Controller
public class LsfcAct {
	private static final Logger log = LoggerFactory.getLogger(LsfcAct.class);

	@RequestMapping("/lsfc/fc_list.do")
	public String list(Integer pageNo, HttpServletRequest request,ModelMap model) {
		
		ShopAdmin admin = AdminThread.get();
		boolean isSuper=false;
		if(admin.getAdmin().getIsSuper()&&admin.getAdmin().isSuper()){
			isSuper=true;
		}
		Pagination pagination=null;
		if(isSuper){
			//如果登录用户是超级管理员，显示所有的站点流水分成信息
			pagination = lsfcMng.getPageByIsDisabled(false, cpn(pageNo), CookieUtils.getPageSize(request));
		}else{
			Website website=SiteUtils.getWeb(request);
			
			pagination = lsfcMng.getPageByIsDisabledAndWebsiteId(website.getId(), false, cpn(pageNo), CookieUtils.getPageSize(request));
		}
		

		
		
		model.addAttribute("pagination", pagination);
		return "lsfc/fc_list";
	}

	@RequestMapping("/lsfc/v_add.do")
	public String add(HttpServletRequest request, ModelMap model) {
		ShopAdmin admin = AdminThread.get();
		boolean isSuper=false;
		if(admin.getAdmin().getIsSuper()&&admin.getAdmin().isSuper()){
			isSuper=true;
		}
		Website website=null;
		List<Website> allsite = null;
		if(isSuper){
			website=SiteUtils.getWeb(request);
			allsite = websiteMng.getAllList();
			
		}else{
			website = websiteMng.findById(SiteUtils.getWeb(request).getId());
			allsite = new ArrayList<Website>();
			allsite.add(website);
		}
		
		List<ShopDictionary> fcTypeList=shopDictionaryMng.getListByType((long)11);
		model.put("allsite", allsite);
		model.put("fcTypeList", fcTypeList);
		
		return "lsfc/add";
	}
		
	@RequestMapping("/lsfc/v_edit.do")
	public String edit(Long id, HttpServletRequest request, ModelMap model) {
		WebErrors errors = validateEdit(id, request);
		if (errors.hasErrors()) {
			return errors.showErrorPage(model);
		}
		
		
		ShopAdmin admin = AdminThread.get();
		boolean isSuper=false;
		if(admin.getAdmin().getIsSuper()&&admin.getAdmin().isSuper()){
			isSuper=true;
		}
		Website website=null;
		List<Website> allsite = null;
		if(isSuper){
			website=SiteUtils.getWeb(request);
			allsite = websiteMng.getAllList();
			
		}else{
			website = websiteMng.findById(SiteUtils.getWeb(request).getId());
			allsite = new ArrayList<Website>();
			allsite.add(website);
		}
		
		
		
		
		
		List<ShopDictionary> fcTypeList=shopDictionaryMng.getListByType((long)11);
		model.put("allsite", allsite);
		model.addAttribute("lsfc", lsfcMng.findById(id));
		model.put("fcTypeList", fcTypeList);
		return "lsfc/edit";
	}
	
	private WebErrors validateEdit(Long id, HttpServletRequest request) {
		WebErrors errors = WebErrors.create(request);
		errors.ifNull(id, "id");
		vldExist(id, errors);
		return errors;
	}
	
	private boolean vldExist(Long id, WebErrors errors) {
		Lsfc entity = lsfcMng.findById(id);
		return errors.ifNotExist(entity, Lsfc.class, id);
	}

	
	@RequestMapping("/lsfc/o_save.do")
	public String save(Lsfc bean,Long siteId, String text, Long[] typeIds, Long fctypeIdstr,
			HttpServletRequest request, ModelMap model) {
		bean.setWebsite(websiteMng.findById(siteId));
		bean.setFctypeId(shopDictionaryMng.findById(fctypeIdstr));
		WebErrors errors = validateSave(bean, request);
		if (errors.hasErrors()) {
			return errors.showErrorPage(model);
		}
		Website website=SiteUtils.getWeb(request);
		User user = SiteUtils.getUser(request);
		bean.setEd(0.0);
		bean.setUser(user);
		bean.setCreateTime(new Date());
		bean = lsfcMng.save(bean);
		log.info("save brand. id={}", bean.getId());
		
		
		
		
		String content="";
		//ProductSite oldbean=null;
		//oldbean=sitelsfcMng.findById(bean.getId());
		content="Id="+bean.getId()+";FctypeId="+bean.getFctypeId().getId()+"";
		cmsLogMng.operating(request, "lsfc.log.save", content);
		return "redirect:fc_list.do";
	}
	private WebErrors validateSave(Lsfc bean, HttpServletRequest request) {
		WebErrors errors = WebErrors.create(request);
		Lsfc lsfc=lsfcMng.findByFctypeId(bean.getFctypeId().getId(), bean.getWebsite().getId());
		if(lsfc!=null && lsfc.getId()>0){
		errors.addError("该站点已经存在'"+bean.getFctypeId().getName()+"'分成类型");
		}
		return errors;
	}
	
	
	
	@RequestMapping("/lsfc/o_update.do")
	public String update(Lsfc bean, String text,HttpServletRequest request, ModelMap model) {
		WebErrors errors = validateUpdate(bean, request);
		if (errors.hasErrors()) {
			return errors.showErrorPage(model);
		}
		String content="";
		String s="";
		Lsfc oldbean=null;
		oldbean=lsfcMng.findById(bean.getId());
		if(oldbean.getEd() != bean.getEd()){
			s = s + "Ed from " + oldbean.getEd() + " to " + bean.getEd()+";";
		}
		if(oldbean.getPtFc() != bean.getPtFc()){
			s = s + "PtFc from " + oldbean.getPtFc() + " to " + bean.getPtFc()+";";
		}
		if(oldbean.getDlsFc() != bean.getDlsFc()){
			s = s + "DlsFc from " + oldbean.getDlsFc() + " to " + bean.getDlsFc()+";";
		}
		if(oldbean.getBl() != bean.getBl()){
			s = s + "Bl from " + oldbean.getBl() + " to " + bean.getBl()+";";
		}
		
		if(s.length()>1){
			content="Id="+bean.getId()+":"+s;
			cmsLogMng.operating(request, "lsfc.log.update", content);
		}
		
		
		bean = lsfcMng.update(bean);
		log.info("update brand. id={}.", bean.getId());
		
		
		
		return list(1, request, model);
	}
	private WebErrors validateUpdate(Lsfc bean, HttpServletRequest request) {
		WebErrors errors = WebErrors.create(request);
		Long id = bean.getId();
		
		errors.ifNull(id, "id");
		vldExist(id, errors);
		return errors;
	}
	
	@RequestMapping("/lsfc/o_delete.do")
	public String delete(Long[] ids,
			HttpServletRequest request, ModelMap model) {
		WebErrors errors = validateDelete(ids, request);
		if (errors.hasErrors()) {
			return errors.showErrorPage(model);
		}
		Lsfc[] beans = lsfcMng.changeIsDisabledByIds(ids);
		for (Lsfc bean : beans) {
			log.info("changeIsDisabledByIds brand. id={}", bean.getId());
		}

		String content="";
		String s="";
			for(int i=0; i<ids.length; i++){
				s= s + "Id="+ids[i]+";";
			}
		content="delete:"+s+"";
		cmsLogMng.operating(request, "lsfc.log.delete", content);
		return "redirect:fc_list.do";
	}
	
	private WebErrors validateDelete(Long[] ids, HttpServletRequest request) {
		WebErrors errors = WebErrors.create(request);
		errors.ifEmpty(ids, "ids");
		for (Long id : ids) {
			vldExist(id, errors);
		}
		return errors;
	}
	/*
	@RequestMapping("/lsfc/o_priority.do")
	public String priority(Long[] wids, Integer[] priority,
			HttpServletRequest request, ModelMap model) {
		WebErrors errors = validatePriority(wids, priority, request);
		if (errors.hasErrors()) {
			return errors.showErrorPage(model);
		}
		lsfcMng.updatePriority(wids, priority);
		model.addAttribute("message", "global.success");
		return list(request, model);
	}
	
	private WebErrors validatePriority(Long[] wids, Integer[] priority,
			HttpServletRequest request) {
		WebErrors errors = WebErrors.create(request);
		if (errors.ifEmpty(wids, "wids")) {
			return errors;
		}
		if (errors.ifEmpty(priority, "priority")) {
			return errors;
		}
		if (wids.length != priority.length) {
			errors.addErrorString("wids length not equals priority length");
			return errors;
		}
		for (int i = 0, len = wids.length; i < len; i++) {
			vldExist(wids[i], errors);
			if (priority[i] == null) {
				priority[i] = 0;
			}
		}
		return errors;
	}
	*/
	@RequestMapping("/lsfc/v_checkFctypeId.do")
	public void checkFctypeIdJson(Long fctypeId, Long websiteId, HttpServletResponse response) {
		String pass;
	
			if (StringUtils.isBlank(fctypeId+"") || StringUtils.isBlank(websiteId+"")) {
				pass = "false";
			} else {
				Lsfc sot=lsfcMng.findByFctypeId(fctypeId, websiteId);
				if(sot==null){
					pass= "true";
				}else{
					pass= "false";
					
				}
			}
		ResponseUtils.renderJson(response, pass);
	}
	
	
	
	@Autowired
	private LsfcMng lsfcMng;
	
	@Autowired
	private WebsiteMng websiteMng;
	
	@Autowired
	private CmsLogMng cmsLogMng;
	
	@Autowired
	private ShopDictionaryMng shopDictionaryMng;
	
}