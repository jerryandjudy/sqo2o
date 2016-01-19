package com.jspgou.cms.action.admin.main;

import static com.jspgou.common.page.SimplePage.cpn;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import com.jspgou.cms.entity.Jmf;
import com.jspgou.cms.entity.ShopAdmin;
import com.jspgou.cms.entity.ShopDictionary;
import com.jspgou.cms.manager.CmsLogMng;
import com.jspgou.cms.manager.JmfMng;
import com.jspgou.cms.manager.ShopDictionaryMng;
import com.jspgou.cms.web.threadvariable.AdminThread;
import com.jspgou.common.page.Pagination;
import com.jspgou.common.web.CookieUtils;
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
public class JmfAct {
	private static final Logger log = LoggerFactory.getLogger(JmfAct.class);

	@RequestMapping("/jmf/jmf_list.do")
	public String list(Integer pageNo, HttpServletRequest request,ModelMap model) {
		
		ShopAdmin admin = AdminThread.get();
		boolean isSuper=false;
		if(admin.getAdmin().getIsSuper()&&admin.getAdmin().isSuper()){
			isSuper=true;
		}
		Pagination pagination=null;
		if(isSuper){
			//如果登录用户是超级管理员，显示所有的站点流水分成信息
			pagination = jmfMng.getPageByIsDisabled(false, cpn(pageNo), CookieUtils.getPageSize(request));
		}else{
			Website website=SiteUtils.getWeb(request);
			
			pagination = jmfMng.getPageByIsDisabledAndWebsiteId(website.getId(), false, cpn(pageNo), CookieUtils.getPageSize(request));
		}
		

		
		
		model.addAttribute("pagination", pagination);
		return "jmf/jmf_list";
	}

	@RequestMapping("/jmf/v_add.do")
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
		
		List<ShopDictionary> jmfDictList=shopDictionaryMng.getListByType((long)12);
		model.put("allsite", allsite);
		model.put("jmfDictList", jmfDictList);
		
		return "jmf/add";
	}
	
	@RequestMapping("/jmf/o_save.do")
	public String save(Jmf bean,Long siteId, String text, Long[] typeIds, Long jmftypeIdstr,
			HttpServletRequest request, ModelMap model) {
		bean.setWebsite(websiteMng.findById(siteId));
		bean.setJmftypeId(shopDictionaryMng.findById(jmftypeIdstr));
		WebErrors errors = validateSave(bean, request);
		if (errors.hasErrors()) {
			return errors.showErrorPage(model);
		}
		Website website=SiteUtils.getWeb(request);
		User user = SiteUtils.getUser(request);
		bean.setIsDisabled(false);
		bean.setUser(user);
		bean.setCreateTime(new Date());
		bean = jmfMng.save(bean);
		log.info("save brand. id={}", bean.getId());
		
		
		
		
		String content="";
		//ProductSite oldbean=null;
		//oldbean=sitejmfMng.findById(bean.getId());
		content="Id="+bean.getId()+";JmftypeId="+bean.getJmftypeId().getId()+"";
		cmsLogMng.operating(request, "jmf.log.save", content);
		return "redirect:jmf_list.do";
	}
	private WebErrors validateSave(Jmf bean, HttpServletRequest request) {
		WebErrors errors = WebErrors.create(request);
		Jmf jmf=jmfMng.findByJmftypeId(bean.getJmftypeId().getId(), bean.getWebsite().getId());
		if(jmf!=null && jmf.getId()>0){
		errors.addError("该站点已经存在'"+bean.getJmftypeId().getName()+"'类型");
		}
		return errors;
	}
	
	
		
	@RequestMapping("/jmf/v_edit.do")
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
		
		
		
		
		
		List<ShopDictionary> jmfTypeList=shopDictionaryMng.getListByType((long)12);
		model.put("allsite", allsite);
		model.addAttribute("jmf", jmfMng.findById(id));
		model.put("jmfTypeList", jmfTypeList);
		return "jmf/edit";
	}
	
	private WebErrors validateEdit(Long id, HttpServletRequest request) {
		WebErrors errors = WebErrors.create(request);
		errors.ifNull(id, "id");
		vldExist(id, errors);
		return errors;
	}
	
	private boolean vldExist(Long id, WebErrors errors) {
		Jmf entity = jmfMng.findById(id);
		return errors.ifNotExist(entity, Jmf.class, id);
	}

	
	
	
	
	@RequestMapping("/jmf/o_update.do")
	public String update(Jmf bean, String text,HttpServletRequest request, ModelMap model) {
		WebErrors errors = validateUpdate(bean, request);
		if (errors.hasErrors()) {
			return errors.showErrorPage(model);
		}
		String content="";
		String s="";
		Jmf oldbean=null;
		oldbean=jmfMng.findById(bean.getId());
		if(oldbean.getJe() != bean.getJe()){
			s = s + "Je from " + oldbean.getJe() + " to " + bean.getJe()+";";
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
			cmsLogMng.operating(request, "jmf.log.update", content);
		}
		
		
		bean = jmfMng.update(bean);
		log.info("update brand. id={}.", bean.getId());
		
		
		
		return list(1, request, model);
	}
	private WebErrors validateUpdate(Jmf bean, HttpServletRequest request) {
		WebErrors errors = WebErrors.create(request);
		Long id = bean.getId();
		
		errors.ifNull(id, "id");
		vldExist(id, errors);
		return errors;
	}
	
	@RequestMapping("/jmf/o_delete.do")
	public String delete(Long[] ids,
			HttpServletRequest request, ModelMap model) {
		WebErrors errors = validateDelete(ids, request);
		if (errors.hasErrors()) {
			return errors.showErrorPage(model);
		}
		String content="";
		String s="";
//		Jmf[] beans = jmfMng.changeIsDisabledByIds(ids);
		for(Long id:ids){
			Jmf jmfs = jmfMng.findById(id);
			jmfs.setIsDisabled(true);
			jmfMng.update(jmfs);
			log.info("changeIsDisabledByIds brand. id={}", jmfs.getId());
			s= s + "Id="+id+";";
			content="delete:"+s+"";
		}
		cmsLogMng.operating(request, "jmf.log.delete", content);
		return "redirect:jmf_list.do";
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
		jmfMng.updatePriority(wids, priority);
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
	/*@RequestMapping("/lsfc/v_checkFctypeId.do")
	public void checkFctypeIdJson(Long fctypeId, Long websiteId, HttpServletResponse response) {
		String pass;
	
			if (StringUtils.isBlank(fctypeId+"") || StringUtils.isBlank(websiteId+"")) {
				pass = "false";
			} else {
				Lsfc sot=jmfMng.findByFctypeId(fctypeId, websiteId);
				if(sot==null){
					pass= "true";
				}else{
					pass= "false";
					
				}
			}
		ResponseUtils.renderJson(response, pass);
	}
	*/
	
	
	@Autowired
	private JmfMng jmfMng;
	
	@Autowired
	private WebsiteMng websiteMng;
	
	@Autowired
	private CmsLogMng cmsLogMng;
	
	@Autowired
	private ShopDictionaryMng shopDictionaryMng;
	
}