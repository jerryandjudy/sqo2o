package com.jspgou.cms.action.admin.main;

import static com.jspgou.common.page.SimplePage.cpn;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.json.JSONException;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import com.jspgou.cms.entity.Address;
import com.jspgou.cms.entity.Category;
import com.jspgou.cms.entity.Fws;
import com.jspgou.cms.entity.Gys;
import com.jspgou.cms.entity.KetaUser;
import com.jspgou.cms.entity.Organization;
import com.jspgou.cms.entity.Psqy;
import com.jspgou.cms.manager.AddressMng;
import com.jspgou.cms.manager.CategoryMng;
import com.jspgou.cms.manager.CmsLogMng;
import com.jspgou.cms.manager.FwsMng;
import com.jspgou.cms.manager.KetaUserMng;
import com.jspgou.cms.manager.OrganizationMng;
import com.jspgou.cms.manager.PsqyMng;
import com.jspgou.cms.manager.SqServiceMng;
import com.jspgou.common.hibernate3.Updater;
import com.jspgou.common.page.Pagination;
import com.jspgou.common.web.CookieUtils;
import com.jspgou.common.web.ResponseUtils;
import com.jspgou.core.entity.Account;
import com.jspgou.core.entity.User;
import com.jspgou.core.entity.Website;
import com.jspgou.core.manager.WebsiteMng;
import com.jspgou.core.security.CmsAuthorizingRealm;
import com.jspgou.core.security.CmsAuthorizingRealm.HashPassword;
import com.jspgou.core.web.SiteUtils;
import com.jspgou.core.web.WebErrors;

/**
* This class should preserve.
* @preserve
*/
@Controller
public class FwsAct {
	private static final Logger log = LoggerFactory.getLogger(FwsAct.class);

	@RequestMapping("/fws/v_list.do")
	public String list(Long siteId,String organizationName,String username,String companyName,Integer pageNo, HttpServletRequest request,ModelMap model) {
		
			Website website=SiteUtils.getWeb(request);
			
//			 = manager.getPageByIsDisabledAndWebsiteId(website.getId(), false, cpn(pageNo), CookieUtils.getPageSize(request));
	   Pagination pagination = fwsMng.getPageByIsDisabled(organizationName,siteId,username,companyName,false, cpn(pageNo), CookieUtils.getPageSize(request));
		model.addAttribute("pagination", pagination);
		List siteList=websiteMng.getAllList();
		model.addAttribute("siteId", siteId);
		model.addAttribute("organizationName", organizationName);
		model.addAttribute("siteList", siteList);
		model.addAttribute("companyName", companyName);
		return "fws/list";
	}
	@RequestMapping("/fws/v_fwlist.do")
	public String fwlist(Long ketaUserId,Integer pageNo, HttpServletRequest request,ModelMap model) {
		Website website=SiteUtils.getWeb(request);
		List list=new ArrayList();
		if(ketaUserId!=null){
		list=sqServiceMng.getList(ketaUserId,null, null, null,null,null,null, null, null, null, null);
		}
		model.put("list", list);
		return "fws/fwlist";
	}
	@RequestMapping("/fws/v_mapadd.do")
	public String add(HttpServletRequest request,Fws bean, ModelMap model) {
		if(bean!=null&&bean.getId()!=null&&bean.getId().length()>0){
			bean = fwsMng.findById(bean.getId());
			}else{
				bean= new Fws();
			}
		model.put("fws", bean);
		return "fws/mapadd";
	}
	@RequestMapping("/fws/v_add.do")
	public String add(HttpServletRequest request, ModelMap model) {
		List<Website> allsite = websiteMng.getAllList();
		List<Category> categoryList=categoryMng.getChildList(2l, false, null, 584l, null, true);
		model.put("allsite", allsite);
		model.put("categoryList", categoryList);
		return "fws/add";
	}
		
	@RequestMapping("/fws/v_checkUserName.do")
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
	@RequestMapping("/fws/v_edit.do")
	public String edit(String id, HttpServletRequest request, ModelMap model) {
		WebErrors errors = validateEdit(id, request);
		if (errors.hasErrors()) {
			return errors.showErrorPage(model);
		}
		List<Category> categoryList=categoryMng.getChildList(2l, false, null, 584l, null, true);
		model.put("categoryList", categoryList);
		Fws fws=fwsMng.findById(id);
		List categoryIdList=new ArrayList();
		if(fws.getFwsCategory()!=null&& fws.getFwsCategory().trim().length()>0){
		 String[] ids=fws.getFwsCategory().split(",");
		 if(ids!=null && ids.length>0){
			 for(String idd:ids){
              categoryIdList.add(Integer.parseInt(idd));
			 }
		 }
		}
		model.addAttribute("categoryIdList", categoryIdList);
		model.addAttribute("fws", fws);
		return "fws/edit";
	}
	@RequestMapping("/fws/v_fwssh.do")
	public String fwssh(String id, HttpServletRequest request, ModelMap model) {
		WebErrors errors = validateEdit(id, request);
		if (errors.hasErrors()) {
			return errors.showErrorPage(model);
		}
		model.addAttribute("fws", fwsMng.findById(id));
		return "fws/fwssh";
	}
	
	private WebErrors validateEdit(String id, HttpServletRequest request) {
		WebErrors errors = WebErrors.create(request);
		errors.ifNull(id, "id");
		vldExist(id, errors);
		return errors;
	}
	
	private boolean vldExist(String id, WebErrors errors) {
		Fws entity = fwsMng.findById(id);
		return errors.ifNotExist(entity, Fws.class, id);
	}

	
	/**
	 * 保存入库
	 * @param bean
	 * @param siteId
	 * @param request
	 * @param model
	 * @return
	 */
	@RequestMapping("/fws/o_save.do")
	public String save(Fws bean,Long siteId,  
			HttpServletRequest request, ModelMap model) {
		Organization organization=organizationMng.findByName("服务商");
		WebErrors errors = validateSave(organization, request);
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
		ketaUser.setPhone(bean.getKetaUser().getPhone());
		ketaUserMng.save(ketaUser);
		User user = SiteUtils.getUser(request);
		bean.setUser(user);
		bean.setCreateTime(new Date());
		bean.setWebsite(website);
		Account account=new Account();
		account.setMoney(0d);
		bean.setPhone(ketaUser.getPhone());
		account.setUsername(user.getUsername());
		account.setStatus(0);
		bean.setAccount(account);
		bean = fwsMng.save(bean);
		content=content+"id:"+bean.getId()+";新增服务商用户："+bean.getKetaUser().getRealname()+";站点id:"+website.getId()+"";
		cmsLogMng.operating(request, "fws.log.save", content);
		return "redirect:v_list.do";
	}
	private WebErrors validateSave(Organization organization,HttpServletRequest request) {
		WebErrors errors = WebErrors.create(request);
//		Dls lsfc=fwsMng.findByFctypeId(bean.getFctypeId().getId(), bean.getWebsite().getId());
		if(organization==null || organization.getId()<1){
		errors.addError("服务商组织机构不存在");
		}
		return errors;
	}
	
	
	
	/**
	 * 保存更新
	 * @param bean
	 * @param text
	 * @param request
	 * @param model
	 * @return
	 */
	@RequestMapping("/fws/o_update.do")
	public String update(Fws bean, String text,HttpServletRequest request, ModelMap model) {
		WebErrors errors = validateUpdate(bean, request);
		if (errors.hasErrors()) {
			return errors.showErrorPage(model);
		}
		String content="";
		String s="";
		Fws oldbean=null;
		oldbean=fwsMng.findById(bean.getId());
		User user = SiteUtils.getUser(request);
			content="操作人："+user.getUsername()+";Id="+bean.getId()+";服务商名称："+oldbean.getKetaUser().getRealname()+"-to-"+bean.getKetaUser().getRealname()+";服务商电话："+oldbean.getKetaUser().getPhone()+"-to-"+bean.getKetaUser().getPhone()+";";
			cmsLogMng.operating(request, "fws.log.update", content);
			KetaUser ketaUser=oldbean.getKetaUser();
			ketaUser.setPhone(bean.getKetaUser().getPhone());
			ketaUser.setRealname(bean.getKetaUser().getRealname());
			ketaUserMng.updateByUpdater(ketaUser);
			oldbean.setCompanyName(bean.getCompanyName());
			oldbean.setMapX(bean.getMapX());
			oldbean.setMapY(bean.getMapY());
			oldbean.setFwsAddr(bean.getFwsAddr());
			oldbean.setLegalPerson(bean.getLegalPerson());
			oldbean.setContact(bean.getContact());
			oldbean.setPhone(ketaUser.getPhone());
			oldbean.setContactTel(bean.getContactTel());
			oldbean.setOrgCodeCert(bean.getOrgCodeCert());
			oldbean.setFjHzyxs(bean.getFjHzyxs());
			oldbean.setFjBzs(bean.getFjBzs());
			oldbean.setFjKhxkz(bean.getFjKhxkz());
			oldbean.setFjRzkzfm(bean.getFjRzkzfm());
			oldbean.setFjRzsqs(bean.getFjRzsqs());
			oldbean.setFjJmht(bean.getFjJmht());
			oldbean.setFwsType(bean.getFwsType());
			oldbean.setFwsCategory(bean.getFwsCategory());
			Updater updater=new Updater(oldbean);
		bean = fwsMng.updateByUpdater(updater);
		
		log.info("update brand. id={}.", bean.getId());
		
		return "redirect:v_list.do";
	}
	/**
	 * 审核通过
	 * @param bean
	 * @param text
	 * @param request
	 * @param model
	 * @return
	 */
	@RequestMapping("/fws/o_update_tg.do")
	public String updatetg(Fws bean, String text,HttpServletRequest request, ModelMap model) {
		WebErrors errors = validateUpdate(bean, request);
		if (errors.hasErrors()) {
			return errors.showErrorPage(model);
		}
		String content="";
		String s="";
		Fws oldbean=null;
		oldbean=fwsMng.findById(bean.getId());
		User user = SiteUtils.getUser(request);
		content="操作人："+user.getUsername()+";Id="+bean.getId()+";服务商名称："+oldbean.getKetaUser().getRealname()+"-to-"+bean.getKetaUser().getRealname()+";服务商电话："+oldbean.getKetaUser().getPhone()+"-to-"+bean.getKetaUser().getPhone()+";";
		cmsLogMng.operating(request, "fws.log.update", content);
		KetaUser ketaUser=oldbean.getKetaUser();
		ketaUser.setPhone(bean.getKetaUser().getPhone());
		ketaUser.setRealname(bean.getKetaUser().getRealname());
		ketaUser.setOrganization(organizationMng.findByName("服务商"));
		ketaUserMng.updateByUpdater(ketaUser);
		oldbean.setCompanyName(bean.getCompanyName());
		oldbean.setMapX(bean.getMapX());
		oldbean.setMapY(bean.getMapY());
		oldbean.setFwsAddr(bean.getFwsAddr());
		oldbean.setLegalPerson(bean.getLegalPerson());
		oldbean.setContact(bean.getContact());
		oldbean.setContactTel(bean.getContactTel());
		oldbean.setOrgCodeCert(bean.getOrgCodeCert());
		
		oldbean.setFjHzyxs(bean.getFjHzyxs());
		oldbean.setFjBzs(bean.getFjBzs());
		oldbean.setFjKhxkz(bean.getFjKhxkz());
		oldbean.setFjRzkzfm(bean.getFjRzkzfm());
		oldbean.setFjRzsqs(bean.getFjRzsqs());
		oldbean.setFjJmht(bean.getFjJmht());
		Updater updater=new Updater(oldbean);
		bean = fwsMng.updateByUpdater(updater);
		
		log.info("update brand. id={}.", bean.getId());
		
		return "redirect:v_list.do";
	}
	private WebErrors validateUpdate(Fws bean, HttpServletRequest request) {
		WebErrors errors = WebErrors.create(request);
		String id = bean.getId();
		
		errors.ifNull(id, "id");
		vldExist(id, errors);
		return errors;
	}
	
	@RequestMapping("/fws/o_delete.do")
	public String delete(Fws bean,
			HttpServletRequest request, ModelMap model) {
		WebErrors errors = validateUpdate(bean, request);
		if (errors.hasErrors()) {
			return errors.showErrorPage(model);
		}
		String content="";
		String s="";
		Fws oldbean=null;
		oldbean=fwsMng.findById(bean.getId());
		User user = SiteUtils.getUser(request);
			content="操作人："+user.getUsername()+";Id="+bean.getId()+";服务商名称："+oldbean.getKetaUser().getRealname();
			cmsLogMng.operating(request, "fws.log.delete", content);
			oldbean.setIsDisabled(true);
			KetaUser ketaUser=oldbean.getKetaUser();
			ketaUser.setStatus("disabled");
			ketaUserMng.updateByUpdater(ketaUser);
			Updater updater=new Updater(oldbean);
			bean = fwsMng.updateByUpdater(updater);
		
		log.info("update brand. id={}.", bean.getId());
		
		return "redirect:v_list.do";
	}
	private WebErrors validatePsqy(Psqy bean, HttpServletRequest request) {
		WebErrors errors = WebErrors.create(request);
		Long id = bean.getId();
		
		errors.ifNull(id, "id");
		vldPsqyExist(id, errors);
		return errors;
	}
	private boolean vldPsqyExist(Long id, WebErrors errors) {
		Psqy entity = psqyMng.findById(id);
		return errors.ifNotExist(entity, Psqy.class, id);
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
	// 获得所选择省份的全部城市
			@RequestMapping("/fws/findAllCity.do")
			public void findAllCity(Long id, HttpServletRequest request,
					HttpServletResponse response, ModelMap model) {
				// 所属省份的城市
				List<Address> clist =addressMng.getListById(id);
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
			@RequestMapping("/fws/findAllCountry.do")
			public void findAllArea(Long id, HttpServletRequest request,
					HttpServletResponse response, ModelMap model) {
				// 所选择市的全部县
				List<Address> alist =addressMng.getListById(id);
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
	
			// 未使用
			@RequestMapping("/fws/findAllBuilding.do")
			public void findAllBuilding(Long id, HttpServletRequest request,
					HttpServletResponse response, ModelMap model) {
				// 所选择市的全部县
				List<Address> alist =addressMng.getListById(id);
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
					json.put("buildings", areas);
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
			
	@Autowired
	private FwsMng fwsMng;
	@Autowired
	private SqServiceMng sqServiceMng;
	@Autowired
	private KetaUserMng ketaUserMng;
	
	@Autowired
	private WebsiteMng websiteMng;
	@Autowired
	private AddressMng addressMng;
	@Autowired
	private CmsLogMng cmsLogMng;
	@Autowired
	private OrganizationMng organizationMng;
	@Autowired
	private PsqyMng psqyMng;
	@Autowired
	private CategoryMng categoryMng;
	
	
}