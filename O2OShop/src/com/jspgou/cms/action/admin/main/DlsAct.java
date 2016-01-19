package com.jspgou.cms.action.admin.main;

import static com.jspgou.common.page.SimplePage.cpn;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.swing.text.StyledEditorKit.BoldAction;

import net.sourceforge.pinyin4j.format.exception.BadHanyuPinyinOutputFormatCombination;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import com.jspgou.cms.entity.Dls;
import com.jspgou.cms.entity.KetaUser;
import com.jspgou.cms.entity.Organization;
import com.jspgou.cms.entity.ShopAdmin;
import com.jspgou.cms.entity.ShopDictionary;
import com.jspgou.cms.manager.CmsLogMng;
import com.jspgou.cms.manager.DlsMng;
import com.jspgou.cms.manager.DlsMng;
import com.jspgou.cms.manager.KetaUserMng;
import com.jspgou.cms.manager.OrganizationMng;
import com.jspgou.cms.manager.ShopDictionaryMng;
import com.jspgou.cms.web.threadvariable.AdminThread;
import com.jspgou.common.page.Pagination;
import com.jspgou.common.web.CookieUtils;
import com.jspgou.common.web.ResponseUtils;
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
public class DlsAct {
	private static final Logger log = LoggerFactory.getLogger(DlsAct.class);

	@RequestMapping("/dls/v_list.do")
	public String list(Integer pageNo, HttpServletRequest request,ModelMap model) {
		
			Website website=SiteUtils.getWeb(request);
			
//			 = manager.getPageByIsDisabledAndWebsiteId(website.getId(), false, cpn(pageNo), CookieUtils.getPageSize(request));
	   Pagination pagination = dlsMng.getPageByIsDisabled(false, cpn(pageNo), CookieUtils.getPageSize(request));
		model.addAttribute("pagination", pagination);
		return "dls/list";
	}

	@RequestMapping("/dls/v_add.do")
	public String add(HttpServletRequest request, ModelMap model) {
		List<Website> allsite = dlsMng.getListNotHasDls();
		model.put("allsite", allsite);
		return "dls/add";
	}
		
	@RequestMapping("/dls/v_edit.do")
	public String edit(String id, HttpServletRequest request, ModelMap model) {
		WebErrors errors = validateEdit(id, request);
		if (errors.hasErrors()) {
			return errors.showErrorPage(model);
		}
		model.addAttribute("dls", dlsMng.findById(id));
		return "dls/edit";
	}
	
	private WebErrors validateEdit(String id, HttpServletRequest request) {
		WebErrors errors = WebErrors.create(request);
		errors.ifNull(id, "id");
		vldExist(id, errors);
		return errors;
	}
	
	private boolean vldExist(String id, WebErrors errors) {
		Dls entity = dlsMng.findById(id);
		return errors.ifNotExist(entity, Dls.class, id);
	}

	
	@RequestMapping("/dls/o_save.do")
	public String save(Dls bean,Long siteId,  
			HttpServletRequest request, ModelMap model) {
		Organization organization=organizationMng.findByName("代理商");
		WebErrors errors = validateSave(organization, request);
		if (errors.hasErrors()) {
			return errors.showErrorPage(model);
		}
		Website website=websiteMng.findById(bean.getWebsite().getId());
		
		String pinyin="";
		try {
			pinyin=ChangeZhongWenToPinYin.getPinYin(website.getName());
		} catch (BadHanyuPinyinOutputFormatCombination e) {
			// TODO Auto-generated catch block
			pinyin=getStringRandom(6);
		}finally{
			
		}
		String content="";
		KetaUser ketaUser=bean.getKetaUser();
		KetaUser oldketaUser=ketaUserMng.findByUserName(pinyin);
		if(oldketaUser!=null &&oldketaUser.getId()>0){
			oldketaUser.setUsername(pinyin+oldketaUser.getId());
			oldketaUser.setStatus("disabled");
			ketaUserMng.updateByUpdater(oldketaUser);
			content="原代理商："+pinyin+"-to-"+pinyin+oldketaUser.getId()+";status:enabled-to-disabled;";
		}
		
		ketaUser.setUsername(pinyin);
		ketaUser.setOrganization(organization);
		HashPassword hashPassword = CmsAuthorizingRealm.encryptPassword(ketaUser.getPainPass());
		ketaUser.setSalt(hashPassword.salt);
		ketaUser.setPassword(hashPassword.password);
		ketaUserMng.save(ketaUser);
		User user = SiteUtils.getUser(request);
		bean.setUser(user);
		bean.setCreateTime(new Date());
		bean.setWebsite(website);
		bean = dlsMng.save(bean);
		content=content+"id:"+bean.getId()+";新增代理商用户："+bean.getKetaUser().getRealname()+";站点id:"+website.getId()+"";
		cmsLogMng.operating(request, "dls.log.save", content);
		return "redirect:v_list.do";
	}
	private WebErrors validateSave(Organization organization,HttpServletRequest request) {
		WebErrors errors = WebErrors.create(request);
//		Dls lsfc=dlsMng.findByFctypeId(bean.getFctypeId().getId(), bean.getWebsite().getId());
		if(organization==null || organization.getId()<1){
		errors.addError("代理商组织机构不存在");
		}
		return errors;
	}
	
	
	
	@RequestMapping("/dls/o_update.do")
	public String update(Dls bean, String text,HttpServletRequest request, ModelMap model) {
		WebErrors errors = validateUpdate(bean, request);
		if (errors.hasErrors()) {
			return errors.showErrorPage(model);
		}
		String content="";
		String s="";
		Dls oldbean=null;
		oldbean=dlsMng.findById(bean.getId());
		User user = SiteUtils.getUser(request);
			content="操作人："+user.getUsername()+";Id="+bean.getId()+";代理商名称："+oldbean.getKetaUser().getRealname()+"-to-"+bean.getKetaUser().getRealname()+";代理商电话："+oldbean.getKetaUser().getPhone()+"-to-"+bean.getKetaUser().getPhone()+";";
			cmsLogMng.operating(request, "dls.log.update", content);
			KetaUser ketaUser=oldbean.getKetaUser();
			ketaUser.setPhone(bean.getKetaUser().getPhone());
			ketaUser.setRealname(bean.getKetaUser().getRealname());
			ketaUserMng.updateByUpdater(ketaUser);
			oldbean.setCompanyName(bean.getCompanyName());
		  
			
			
			
			oldbean.setCompanyAddr(bean.getCompanyAddr());
			oldbean.setLegalPerson(bean.getLegalPerson());
			oldbean.setContact(bean.getContact());
			oldbean.setContactTel(bean.getContactTel());
			oldbean.setOrgCodeCert(bean.getOrgCodeCert());
			oldbean.setKhh(bean.getKhh());
			oldbean.setKhhzh(bean.getKhhzh());
			oldbean.setHm(bean.getHm());
			oldbean.setFjLlrAuthorize(bean.getFjLlrAuthorize());
			oldbean.setFjShDqdlsqb(bean.getFjShDqdlsqb());
			
			oldbean.setFjDqsqqrs(bean.getFjDqsqqrs());
			oldbean.setFjDlht(bean.getFjDlht());
			oldbean.setFjDlbzs(bean.getFjDlbzs());
			oldbean.setFjQyyyzz(bean.getFjQyyyzz());
			oldbean.setFjZzjgdm(bean.getFjZzjgdm());
			oldbean.setFjSwdjz(bean.getFjSwdjz());
			oldbean.setFjKhxkz(bean.getFjKhxkz());
			oldbean.setFjFrFfrSfz(bean.getFjFrFfrSfz());
			oldbean.setFjQyxyjt(bean.getFjQyxyjt());
			oldbean.setFjRzkzfm(bean.getFjRzkzfm());
			oldbean.setFjRzsqs(bean.getFjRzsqs());
			oldbean.setFjFrFfrYhk(bean.getFjFrFfrYhk());
			oldbean.setFjCsjmht(bean.getFjCsjmht());
			 bean = dlsMng.updateByUpdater(oldbean);
			
			
			
			
			
			
			
		
		log.info("update brand. id={}.", bean.getId());
		
		return "redirect:v_list.do";
	}
	private WebErrors validateUpdate(Dls bean, HttpServletRequest request) {
		WebErrors errors = WebErrors.create(request);
		String id = bean.getId();
		
		errors.ifNull(id, "id");
		vldExist(id, errors);
		return errors;
	}
	
	@RequestMapping("/dls/o_delete.do")
	public String delete(Dls bean,
			HttpServletRequest request, ModelMap model) {
		WebErrors errors = validateUpdate(bean, request);
		if (errors.hasErrors()) {
			return errors.showErrorPage(model);
		}
		String content="";
		String s="";
		Dls oldbean=null;
		oldbean=dlsMng.findById(bean.getId());
		User user = SiteUtils.getUser(request);
			content="操作人："+user.getUsername()+";Id="+bean.getId()+";代理商名称："+oldbean.getKetaUser().getRealname();
			cmsLogMng.operating(request, "dls.log.delete", content);
			oldbean.setIsDisabled(true);
			KetaUser ketaUser=oldbean.getKetaUser();
			ketaUser.setStatus("disabled");
			ketaUserMng.updateByUpdater(ketaUser);
		bean = dlsMng.updateByUpdater(oldbean);
		
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
	private DlsMng dlsMng;
	@Autowired
	private KetaUserMng ketaUserMng;
	
	@Autowired
	private WebsiteMng websiteMng;
	
	@Autowired
	private CmsLogMng cmsLogMng;
	@Autowired
	private OrganizationMng organizationMng;
	
	
}