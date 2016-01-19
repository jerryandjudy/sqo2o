package com.jspgou.cms.action.admin.main;

import static com.jspgou.common.page.SimplePage.cpn;

import java.sql.Timestamp;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import com.jspgou.cms.entity.Bld;
import com.jspgou.cms.entity.Gys;
import com.jspgou.cms.entity.Jysp;
import com.jspgou.cms.entity.Khgx;
import com.jspgou.cms.manager.BldMng;
import com.jspgou.cms.manager.CmsLogMng;
import com.jspgou.cms.manager.GysMng;
import com.jspgou.cms.manager.KhgxMng;
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
public class KhgxAct {
	private static final Logger log = LoggerFactory.getLogger(KhgxAct.class);
	@RequestMapping("/khgx/v_khgx_list.do")
	public String list(Khgx khgx,Integer pageNo, HttpServletRequest request,ModelMap model) {
		
			Website website=SiteUtils.getWeb(request);
			List siteList=websiteMng.getAllList();
//			 = manager.getPageByIsDisabledAndWebsiteId(website.getId(), false, cpn(pageNo), CookieUtils.getPageSize(request));
	   Pagination pagination = khgxMng.getPage(khgx, cpn(pageNo), CookieUtils.getPageSize(request));
	   model.addAttribute("pagination", pagination);
		model.addAttribute("khgx", khgx);
		model.addAttribute("siteList", siteList);
		return "khgx/list";
	}
	@RequestMapping("/gys/v_gys_add_bld.do")
	public String v_gys_add_bld(Khgx khgx,Integer pageNo, HttpServletRequest request,ModelMap model) {
		Pagination pagination = khgxMng.getPage(khgx, cpn(pageNo), CookieUtils.getPageSize(request));
		Pagination paginationbld=bldMng.getPageByGysNotHas(khgx.getBld(), khgx.getGys(), cpn(pageNo), CookieUtils.getPageSize(request));
		model.addAttribute("paginationbld", paginationbld);
		model.addAttribute("pagination", pagination);
		model.addAttribute("khgx", khgx);
		return "khgx/gys_add_bld";
	}
	@RequestMapping("/gys/o_gys_th_bld.do")
	public String o_gys_th_bld(String gysId,String newCompanyId,String[] ids, HttpServletRequest request,ModelMap model) {
		WebErrors errors = WebErrors.create(request);
		Khgx khgxs=new Khgx();
		if(newCompanyId==null || newCompanyId.length()<1){
			errors.addErrorString("未选择供应商！！");
		}
		if(errors.hasErrors()){
			return errors.showErrorPage(model); 
		}
		if(ids!=null&& ids.length>0){
			User user=SiteUtils.getUser(request);	
		Gys gys=gysMng.findById(newCompanyId);
		Gys oldgys=gysMng.findById(gysId);
		khgxs.setGys(oldgys);
		List<Khgx> list=khgxMng.getListByGysAndBldId(gys, ids);
		List<Khgx> oldlist=khgxMng.getListByGysAndBldId(oldgys, ids);
		int i=0;
		for(String idd:ids){
			khgxMng.deleteById(oldlist.get(i).getId());
			i++;
			boolean flag=true;
			for(Khgx khgx:list){
			if(idd.equals(khgx.getBld().getId())){
				flag=false;
				break;
			}
			}
			if(!flag){
				break;
			}
			Khgx bean = new Khgx();
			Bld bld = bldMng.findById(idd);
			Timestamp ts = new Timestamp(System.currentTimeMillis());  
			bean.setCreateTime(ts);
			bean.setUser(user);
			bean.setWebsite(gys.getWebsite());
			bean.setGys(gys);
			bean.setBld(bld);
			khgxMng.save(bean);	
		}
		}
		
		
		 return this.v_gys_th_bld(khgxs, 1, request, model);
	}
	@RequestMapping("/gys/v_gys_th_bld.do")
	public String v_gys_th_bld(Khgx khgx,Integer pageNo, HttpServletRequest request,ModelMap model) {
		Gys gys=gysMng.findById(khgx.getGys().getId());
		Pagination pagination = khgxMng.getPage(khgx, cpn(pageNo), CookieUtils.getPageSize(request));
		model.addAttribute("gys", gys);
		model.addAttribute("pagination", pagination);
		return "khgx/gys_th_bld";
	}
	@RequestMapping("/gys/v_gys_list.do")
	public String v_gys_list(Khgx khgx,String username,String companyName,Integer pageNo, HttpServletRequest request,ModelMap model) {
		Gys gys=gysMng.findById(khgx.getGys().getId());
		Pagination pagination = gysMng.getPageBySiteAndNotGysId(username,companyName,gys.getWebsite().getId(), gys.getId(), cpn(pageNo), CookieUtils.getPageSize(request));
		model.addAttribute("gyss", gys);
		model.addAttribute("username", username);
		model.addAttribute("companyName", companyName);
		model.addAttribute("pagination", pagination);
		return "khgx/v_gys_list";
	}
	@RequestMapping("/gys/o_gys_save_bld.do")
	public String gys_save_bld(Khgx khgx,String[] ids,Integer pageNo, HttpServletRequest request,ModelMap model) {
		WebErrors errors = WebErrors.create(request);
		
		
		if(ids!=null && ids.length>0){
			if(khgx.getGys()!=null && khgx.getGys().getId()!=null){
		    User user=SiteUtils.getUser(request);	
			Gys gys = gysMng.findById(khgx.getGys().getId());
			for(String id:ids){
				Khgx bean = new Khgx();
				Bld bld = bldMng.findById(id);
				Timestamp ts = new Timestamp(System.currentTimeMillis());  
				bean.setCreateTime(ts);
				bean.setUser(user);
				bean.setWebsite(gys.getWebsite());
				bean.setGys(gys);
				bean.setBld(bld);
				khgxMng.save(bean);
				String content="操作人："+user.getUsername()+";id:"+bean.getId()+";添加("+gys.getCompanyName()+")关联："+bld.getCompanyName();
				cmsLogMng.operating(request, "gysbld.log.save", content);
			}
			}else{
				errors.addErrorString("未选择供应商！！");
			}
		}else{
			errors.addErrorString("请选择便利店！！");
		}
		if(errors.hasErrors()){
			return errors.showErrorPage(model); 
		}
		return v_gys_add_bld(khgx, pageNo, request, model);
	}
	@RequestMapping("/gys/o_gys_delete_bld.do")
	public String gys_delete_bld(Khgx khgx,Integer pageNo, HttpServletRequest request,ModelMap model) {
		WebErrors errors = WebErrors.create(request);
		if(khgx==null||khgx.getId()==null){
			errors.ifNull(khgx, "id")	;
		}
		Khgx bean = khgxMng.findById(khgx.getId());
		if(bean==null){
			errors.ifNotExist(bean, Khgx.class, "id");
		}
		if(!bean.getGys().getId().equals(khgx.getGys().getId())){
			errors.addError("删除错误");
		}
		if(errors.hasErrors()){
			return errors.showErrorPage(model); 
		}
		khgxMng.deleteById(khgx.getId());
		User user=SiteUtils.getUser(request);	
		String content="操作人："+user.getUsername()+";id:"+bean.getId()+";删除("+bean.getGys().getCompanyName()+")关联："+bean.getBld().getCompanyName();
		cmsLogMng.operating(request, "gysbld.log.delete", content);
		khgx.setId(null);
		return v_gys_add_bld(khgx, pageNo, request, model);
	}
	@Autowired
	private CmsLogMng cmsLogMng;
	@Autowired
	private WebsiteMng websiteMng;
	@Autowired
	private KhgxMng khgxMng;
	@Autowired
	private BldMng bldMng;
	@Autowired
	private GysMng gysMng;
}