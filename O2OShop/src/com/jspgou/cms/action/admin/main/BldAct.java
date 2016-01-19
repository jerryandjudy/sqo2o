package com.jspgou.cms.action.admin.main;

import static com.jspgou.common.page.SimplePage.cpn;

import java.sql.Timestamp;
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
import com.jspgou.cms.entity.Bld;
import com.jspgou.cms.entity.Gys;
import com.jspgou.cms.entity.JxcBldGoodsNum;
import com.jspgou.cms.entity.KetaUser;
import com.jspgou.cms.entity.Organization;
import com.jspgou.cms.entity.Psqy;
import com.jspgou.cms.manager.AddressMng;
import com.jspgou.cms.manager.BldMng;
import com.jspgou.cms.manager.CmsLogMng;
import com.jspgou.cms.manager.JxcBldGoodsNumMng;
import com.jspgou.cms.manager.KetaUserMng;
import com.jspgou.cms.manager.OrganizationMng;
import com.jspgou.cms.manager.PsqyMng;
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
public class BldAct {
	private static final Logger log = LoggerFactory.getLogger(BldAct.class);

	@RequestMapping("/bld/v_list.do")
	public String list(Long siteId,String organizationName,String username,String companyName,Integer pageNo, HttpServletRequest request,ModelMap model) {
		
			Website website=SiteUtils.getWeb(request);
			
//			 = manager.getPageByIsDisabledAndWebsiteId(website.getId(), false, cpn(pageNo), CookieUtils.getPageSize(request));
	   Pagination pagination = bldMng.getPageByIsDisabled(organizationName,siteId,username,companyName,false, cpn(pageNo), CookieUtils.getPageSize(request));
		model.addAttribute("pagination", pagination);
		List siteList=websiteMng.getAllList();
		model.addAttribute("siteId", siteId);
		model.addAttribute("organizationName", organizationName);
		model.addAttribute("siteList", siteList);
		model.addAttribute("companyName", companyName);
		return "bld/list";
	}
	@RequestMapping("/bld/v_productkc_list")
	public String productkc(Long siteId,String username,String companyName,Integer pageNo, HttpServletRequest request,ModelMap model) {
		Website website=SiteUtils.getWeb(request);
		Pagination pagination = jxcBldGoodsNumMng.getPage(siteId, username, companyName, cpn(pageNo), CookieUtils.getPageSize(request));
		model.addAttribute("pagination", pagination);
		List siteList=websiteMng.getAllList();
		model.addAttribute("siteId", siteId);
		model.addAttribute("siteList", siteList);
		model.addAttribute("companyName", companyName);
		return "bld/productkclist";
	}
	@RequestMapping("/bld/v_mapadd.do")
	public String add(HttpServletRequest request,Bld bean, ModelMap model) {
		if(bean!=null&&bean.getId()!=null&&bean.getId().length()>0){
			bean = bldMng.findById(bean.getId());
			}else{
				bean= new Bld();
			}
		model.put("bld", bean);
		return "bld/mapadd";
	}
	@RequestMapping("/bld/v_add.do")
	public String add(HttpServletRequest request, ModelMap model) {
		List<Website> allsite = websiteMng.getAllList();
		model.put("allsite", allsite);
		return "bld/add";
	}
		
	@RequestMapping("/bld/v_checkUserName.do")
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
	@RequestMapping("/bld/v_edit.do")
	public String edit(String id, HttpServletRequest request, ModelMap model) {
		WebErrors errors = validateEdit(id, request);
		if (errors.hasErrors()) {
			return errors.showErrorPage(model);
		}
		model.addAttribute("bld", bldMng.findById(id));
		return "bld/edit";
	}
	@RequestMapping("/bld/v_bldsh.do")
	public String bldsh(String id, HttpServletRequest request, ModelMap model) {
		WebErrors errors = validateEdit(id, request);
		if (errors.hasErrors()) {
			return errors.showErrorPage(model);
		}
		model.addAttribute("bld", bldMng.findById(id));
		return "bld/bldsh";
	}
	@RequestMapping("/bld/v_edit_mr.do")
	public String editmr(String id, HttpServletRequest request, ModelMap model) {
		WebErrors errors = validateEdit(id, request);
		if (errors.hasErrors()) {
			return errors.showErrorPage(model);
		}
		Bld bld = bldMng.findById(id);
		Bld defbld = bldMng.findByIsDef(true, bld.getWebsite().getId());
		if(defbld!=null){
		defbld.setIsDef(false);
		bldMng.updateByUpdater(defbld);
		}
		bld.setIsDef(true);
		bldMng.updateByUpdater(bld);
		return this.list(null, null, null,null, 0, request, model);
	}
	
	private WebErrors validateEdit(String id, HttpServletRequest request) {
		WebErrors errors = WebErrors.create(request);
		errors.ifNull(id, "id");
		vldExist(id, errors);
		return errors;
	}
	
	private boolean vldExist(String id, WebErrors errors) {
		Bld entity = bldMng.findById(id);
		return errors.ifNotExist(entity, Bld.class, id);
	}

	
	/**
	 * 保存入库
	 * @param bean
	 * @param siteId
	 * @param request
	 * @param model
	 * @return
	 */
	@RequestMapping("/bld/o_save.do")
	public String save(Bld bean,Long siteId,  
			HttpServletRequest request, ModelMap model) {
		Organization organization=organizationMng.findByName("便利店");
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
		bean = bldMng.save(bean);
		content=content+"id:"+bean.getId()+";新增便利店用户："+bean.getKetaUser().getRealname()+";站点id:"+website.getId()+"";
		cmsLogMng.operating(request, "bld.log.save", content);
		return "redirect:v_list.do";
	}
	private WebErrors validateSave(Organization organization,HttpServletRequest request) {
		WebErrors errors = WebErrors.create(request);
//		Dls lsfc=bldMng.findByFctypeId(bean.getFctypeId().getId(), bean.getWebsite().getId());
		if(organization==null || organization.getId()<1){
		errors.addError("便利店组织机构不存在");
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
	@RequestMapping("/bld/o_update.do")
	public String update(Bld bean, String text,HttpServletRequest request, ModelMap model) {
		WebErrors errors = validateUpdate(bean, request);
		if (errors.hasErrors()) {
			return errors.showErrorPage(model);
		}
		String content="";
		String s="";
		Bld oldbean=null;
		oldbean=bldMng.findById(bean.getId());
		User user = SiteUtils.getUser(request);
			content="操作人："+user.getUsername()+";Id="+bean.getId()+";便利店名称："+oldbean.getKetaUser().getRealname()+"-to-"+bean.getKetaUser().getRealname()+";便利店电话："+oldbean.getKetaUser().getPhone()+"-to-"+bean.getKetaUser().getPhone()+";";
			cmsLogMng.operating(request, "bld.log.update", content);
			KetaUser ketaUser=oldbean.getKetaUser();
			ketaUser.setPhone(bean.getKetaUser().getPhone());
			ketaUser.setRealname(bean.getKetaUser().getRealname());
			ketaUserMng.updateByUpdater(ketaUser);
			oldbean.setCompanyName(bean.getCompanyName());
			oldbean.setMapX(bean.getMapX());
			oldbean.setMapY(bean.getMapY());
			oldbean.setBldAddr(bean.getBldAddr());
			oldbean.setChainInfo(bean.getChainInfo());
			oldbean.setLegalPerson(bean.getLegalPerson());
			oldbean.setContact(bean.getContact());
			oldbean.setContactTel(bean.getContactTel());
			oldbean.setOrgCodeCert(bean.getOrgCodeCert());
			
			oldbean.setFjLlrAuthorize(bean.getFjLlrAuthorize());
			oldbean.setFjHzyxs(bean.getFjHzyxs());
			oldbean.setFjBzs(bean.getFjBzs());
			oldbean.setFjQyyyzz(bean.getFjQyyyzz());
			oldbean.setFjZzzgdm(bean.getFjZzzgdm());
			oldbean.setFjSwdjz(bean.getFjSwdjz());
			oldbean.setFjKhxkz(bean.getFjKhxkz());
			oldbean.setFjFrffrSfz(bean.getFjFrffrSfz());
			oldbean.setFjShzp(bean.getFjShzp());
			oldbean.setFjRzkzfm(bean.getFjRzkzfm());
			oldbean.setFjRzsqs(bean.getFjRzsqs());
			oldbean.setFjFrFfrYhk(bean.getFjFrFfrYhk());
			oldbean.setFjJmht(bean.getFjJmht());
			
			
			
			
			
		bean = bldMng.updateByUpdater(oldbean);
		
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
	@RequestMapping("/bld/o_update_tg.do")
	public String updatetg(Bld bean, String text,HttpServletRequest request, ModelMap model) {
		WebErrors errors = validateUpdate(bean, request);
		if (errors.hasErrors()) {
			return errors.showErrorPage(model);
		}
		String content="";
		String s="";
		Bld oldbean=null;
		oldbean=bldMng.findById(bean.getId());
		User user = SiteUtils.getUser(request);
		content="操作人："+user.getUsername()+";Id="+bean.getId()+";便利店名称："+oldbean.getKetaUser().getRealname()+"-to-"+bean.getKetaUser().getRealname()+";便利店电话："+oldbean.getKetaUser().getPhone()+"-to-"+bean.getKetaUser().getPhone()+";";
		cmsLogMng.operating(request, "bld.log.update", content);
		KetaUser ketaUser=oldbean.getKetaUser();
		ketaUser.setPhone(bean.getKetaUser().getPhone());
		ketaUser.setRealname(bean.getKetaUser().getRealname());
		ketaUser.setOrganization(organizationMng.findByName("便利店"));
		ketaUserMng.updateByUpdater(ketaUser);
		oldbean.setCompanyName(bean.getCompanyName());
		oldbean.setMapX(bean.getMapX());
		oldbean.setMapY(bean.getMapY());
		oldbean.setBldAddr(bean.getBldAddr());
		oldbean.setChainInfo(bean.getChainInfo());
		oldbean.setLegalPerson(bean.getLegalPerson());
		oldbean.setContact(bean.getContact());
		oldbean.setContactTel(bean.getContactTel());
		oldbean.setOrgCodeCert(bean.getOrgCodeCert());
		
		oldbean.setFjLlrAuthorize(bean.getFjLlrAuthorize());
		oldbean.setFjHzyxs(bean.getFjHzyxs());
		oldbean.setFjBzs(bean.getFjBzs());
		oldbean.setFjQyyyzz(bean.getFjQyyyzz());
		oldbean.setFjZzzgdm(bean.getFjZzzgdm());
		oldbean.setFjSwdjz(bean.getFjSwdjz());
		oldbean.setFjKhxkz(bean.getFjKhxkz());
		oldbean.setFjFrffrSfz(bean.getFjFrffrSfz());
		oldbean.setFjShzp(bean.getFjShzp());
		oldbean.setFjRzkzfm(bean.getFjRzkzfm());
		oldbean.setFjRzsqs(bean.getFjRzsqs());
		oldbean.setFjFrFfrYhk(bean.getFjFrFfrYhk());
		oldbean.setFjJmht(bean.getFjJmht());
		
		
		
		
		
		bean = bldMng.updateByUpdater(oldbean);
		
		log.info("update brand. id={}.", bean.getId());
		
		return "redirect:v_list.do";
	}
	private WebErrors validateUpdate(Bld bean, HttpServletRequest request) {
		WebErrors errors = WebErrors.create(request);
		String id = bean.getId();
		
		errors.ifNull(id, "id");
		vldExist(id, errors);
		return errors;
	}
	
	@RequestMapping("/bld/o_delete.do")
	public String delete(Bld bean,
			HttpServletRequest request, ModelMap model) {
		WebErrors errors = validateUpdate(bean, request);
		if (errors.hasErrors()) {
			return errors.showErrorPage(model);
		}
		String content="";
		String s="";
		Bld oldbean=null;
		oldbean=bldMng.findById(bean.getId());
		User user = SiteUtils.getUser(request);
			content="操作人："+user.getUsername()+";Id="+bean.getId()+";便利店名称："+oldbean.getKetaUser().getRealname();
			cmsLogMng.operating(request, "bld.log.delete", content);
			oldbean.setIsDisabled(true);
			KetaUser ketaUser=oldbean.getKetaUser();
			ketaUser.setStatus("disabled");
			ketaUserMng.updateByUpdater(ketaUser);
		bean = bldMng.updateByUpdater(oldbean);
		
		log.info("update brand. id={}.", bean.getId());
		
		return "redirect:v_list.do";
	}
	@RequestMapping("/bld/v_bldps_list.do")
	public String psqyList(Long siteId,String username,String companyName,Integer pageNo, HttpServletRequest request,ModelMap model) {
		
		Website website=SiteUtils.getWeb(request);
		Pagination pagination =psqyMng.getBldPage(siteId, username, companyName,cpn(pageNo), CookieUtils.getPageSize(request));
		List siteList=websiteMng.getAllList();
		model.addAttribute("siteList", siteList);
		model.addAttribute("pagination", pagination);
		model.addAttribute("username", username);
		model.addAttribute("companyName", companyName);
		model.addAttribute("siteId", siteId);
		
		return "bld/bldpsqy_list";
	}
	@RequestMapping("/bld/v_bldps_add.do")
	public String psqyAdd(Bld bld,Long siteId,String username,String companyName,Integer pageNo, HttpServletRequest request,ModelMap model) {
		
		List<Address> plist=addressMng.getListById(null);
		Pagination pagination =psqyMng.getBldPageByBld(bld, username, companyName,cpn(pageNo), CookieUtils.getPageSize(request));
		model.addAttribute("pagination", pagination);
		model.addAttribute("plist", plist);
		model.addAttribute("username", username);
		model.addAttribute("companyName", companyName);
		model.addAttribute("bld", bld);
		Website website=SiteUtils.getWeb(request);
		model.addAttribute("website", website);
		return "bld/bldpsqy_add";
	}
	@RequestMapping("/bld/o_bld_psqy_save.do")
	public String savepsyqy(Psqy bean, Long provinceId,Long cityId,
			Long countryId,Long streetId,
			HttpServletRequest request, ModelMap model) {
		User user = SiteUtils.getUser(request);
		Address province = addressMng.findById(provinceId);
		Address city = addressMng.findById(cityId);
		Address country = addressMng.findById(countryId);
		Address street = addressMng.findById(streetId);
		bean.setProvince(province);
		bean.setCity(city);
		bean.setCountry(country);
		bean.setStreet(street);
		Timestamp ts = new Timestamp(System.currentTimeMillis());  
		bean.setCreateTime(ts);
		bean.setBld(bldMng.findById(bean.getBld().getId()));
		bean.setUser(user);
		bean=psqyMng.save(bean);
		String content="操作人："+user.getUsername()+";id:"+bean.getId()+";新增("+bean.getBld().getCompanyName()+")配送区域："+bean.getProvince().getName()+"/"+bean.getCity().getName()+"/"+bean.getCountry().getName()+"/"+bean.getStreet().getName();
		cmsLogMng.operating(request, "bldpsyqy.log.save", content);
		return psqyAdd(bean.getBld(), null,  null, null, null, request, model);
	}
	@RequestMapping("/bld/o_bld_psqy_delete.do")
	public String deletepsyqy(Psqy bean, 
			HttpServletRequest request, ModelMap model) {
		User user = SiteUtils.getUser(request);
		WebErrors errors = validatePsqy(bean, request);
		if (errors.hasErrors()) {
			return errors.showErrorPage(model);
		}
		bean=psqyMng.findById(bean.getId());
		String content="操作人："+user.getUsername()+";id:"+bean.getId()+";删除("+bean.getBld().getCompanyName()+")配送区域："+bean.getProvince().getName()+"/"+bean.getCity().getName()+"/"+bean.getCountry().getName()+"/"+bean.getStreet().getName();
		bean=psqyMng.deleteById(bean.getId());
		cmsLogMng.operating(request, "bldpsyqy.log.delete", content);
		return psqyAdd(bean.getBld(), null,  null, null, null, request, model);
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
			@RequestMapping("/bld/findAllCity.do")
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
			@RequestMapping("/bld/findAllCountry.do")
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
			// 获得所选市县所有街道
			@RequestMapping("/bld/findAllStreet.do")
			public void findAllStreet(Long id,String bldId, HttpServletRequest request,
					HttpServletResponse response, ModelMap model) {
				// 所选择市的全部县
				List<Address> alist =addressMng.getListByIdNotBld(id, bldId);
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
					json.put("streets", areas);
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
			@RequestMapping("/bld/findAllBuilding.do")
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
	private BldMng bldMng;
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
	private JxcBldGoodsNumMng jxcBldGoodsNumMng;
	
	
}