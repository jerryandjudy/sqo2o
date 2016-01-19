package com.jspgou.cms.action.front;

import static com.jspgou.cms.Constants.GIFT;
import static com.jspgou.cms.Constants.SHOP_SYS;
import static com.jspgou.common.page.SimplePage.cpn;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import com.jspgou.common.page.Pagination;
import com.jspgou.common.web.RequestUtils;
import com.jspgou.common.web.ResponseUtils;
import com.jspgou.common.web.springmvc.MessageResolver;
import com.jspgou.core.entity.User;
import com.jspgou.core.entity.Website;
import com.jspgou.core.web.front.FrontHelper;
import com.jspgou.core.web.front.URLHelper;
import com.jspgou.cms.entity.Consult;
import com.jspgou.cms.entity.Discuss;
import com.jspgou.cms.entity.OrderItem;
import com.jspgou.cms.entity.Product;
import com.jspgou.cms.entity.ProductSite;
import com.jspgou.cms.entity.ShopMember;
import com.jspgou.cms.entity.SqService;
import com.jspgou.cms.manager.ConsultMng;
import com.jspgou.cms.manager.DiscussMng;
import com.jspgou.cms.manager.OrderItemMng;
import com.jspgou.cms.manager.ProductMng;
import com.jspgou.cms.manager.ProductSiteMng;
import com.jspgou.cms.manager.SqOrderMng;
import com.jspgou.cms.manager.SqServiceMng;
import com.jspgou.cms.web.ShopFrontHelper;
import com.jspgou.cms.web.SiteUtils;
import com.jspgou.cms.web.threadvariable.MemberThread;

/**
* This class should preserve.
* @preserve
*/
@Controller
public class ProductFormAct {

	/**
	 * 查询 买家评论(分页)
	 */
	@RequestMapping(value = "/searchDiscussPage*.jspx")
	public String searchDiscussPage(Long productId,Long sqServiceId,Integer pageNo,
			HttpServletResponse response, HttpServletRequest request,ModelMap model) {
		Website web = SiteUtils.getWeb(request);
		Pagination page =null;
		if(productId!=null&&productSiteMng.findById(productId)!=null){
			page = discussMng.getPage(productId,null,null,null,null, cpn(pageNo), 10, true);
			ProductSite bean = productSiteMng.findById(productId);
			model.addAttribute("types", "1");
			model.addAttribute("bean", bean);
		}else if(sqServiceId!=null&&sqServiceMng.getById(sqServiceId)!=null){
			page = discussMng.getPageSer(sqServiceId,null,null,null,null, cpn(pageNo), 10, true);
			SqService bean = sqServiceMng.getById(sqServiceId);
			model.addAttribute("types", "2");
			model.addAttribute("bean", bean);
		}else{
			return FrontHelper.pageNotFound(web, model, request);
		} 
		
		model.addAttribute("pagination", page);
		ShopFrontHelper.setCommonData(request, model, web, 1);
		ShopFrontHelper.setDynamicPageData(request, model, web, "","searchDiscussPage", ".jspx", cpn(pageNo));
		return web.getTemplate(SHOP_SYS, MessageResolver.getMessage(request,"tpl.discussContentPage"));
	}

	/**
	 * 发表评论
	 */
	@RequestMapping(value = "/haveDiscuss.jspx")
	public String haveDiscuss(Long productId,Long sqServiceId,HttpServletRequest request,
			HttpServletResponse response,ModelMap model) {
		Website web = SiteUtils.getWeb(request);
		ShopMember member = MemberThread.get();
		User user = SiteUtils.getUser(request);
		if(member == null || member.getUsername()==null||user==null){
			ResponseUtils.renderJson(response, "denru");
			return null;
		}
		Object bean =null;
		
		if(productId!=null&&productSiteMng.findById(productId)!=null){
			
			bean = orderItemMng.findByMember(member.getId(), productId);
		}else if(sqServiceId!=null&&sqServiceMng.getById(sqServiceId)!=null){
			//bean = orderItemMng.findByMember(member.getId(), productId);
			bean=sqOrderMng.findByMember(member.getId(), sqServiceId);
			
		}else{
			return FrontHelper.pageNotFound(web, model, request);
		}
		if (bean != null) {
			ResponseUtils.renderJson(response, "success");
			return null;
		} else {
			ResponseUtils.renderJson(response, "false");
			return null;
		}
	}

	/**
	 * 查询 购买咨询(分页)
	 */
	@RequestMapping(value = "/consultProduct*.jspx")
	public String consultProduct(Long productId,Integer pageNo, 
			HttpServletResponse response, HttpServletRequest request,
			ModelMap model) {
		Website web = SiteUtils.getWeb(request);
		if(productId==null||productSiteMng.findById(productId)==null){
			return FrontHelper.pageNotFound(web, model, request);
		}
		ShopFrontHelper.setCommonData(request, model, web, cpn(pageNo));
		ProductSite bean = productSiteMng.findById(productId);
		Pagination page = consultMng.getPage(productId,null,null,null,null, cpn(pageNo), 5, true);
		model.addAttribute("product", bean);
		model.addAttribute("pagination", page);
		ShopFrontHelper.setDynamicPageData(request, model, web, "","consultProduct", ".jspx", cpn(pageNo));
		return web.getTemplate(SHOP_SYS, MessageResolver.getMessage(request,"tpl.consultProduct"));
	}
	
	/**
	 * 查询成交记录(分页)
	 */
	@RequestMapping(value = "/bargain*.jspx")
	public String list(Long productId,HttpServletRequest request, ModelMap model) {
		Website web = SiteUtils.getWeb(request);
		if(productId==null||productSiteMng.findById(productId)==null){
			return FrontHelper.pageNotFound(web, model, request);
		}
		int pageNo = URLHelper.getPageNo(request);
		ProductSite bean = productSiteMng.findById(productId);
		Pagination page = orderItemMng.getOrderItem(cpn(pageNo), 4, productId);
		model.addAttribute("pagination", page);
		model.addAttribute("productSite", bean);
		ShopFrontHelper.setCommonData(request, model, web,pageNo);
		ShopFrontHelper.setDynamicPageData(request, model, web, RequestUtils.getLocation(request),"bargain", ".jspx", pageNo);
		return web.getTplSys(SHOP_SYS, MessageResolver.getMessage(request,"tpl.bargain"));
	}
	
	/**
	 * 发布购买咨询
	 */
	@RequestMapping(value = "/insertConsult.jspx")
	public String insertConsult(Long productId, String content,
			HttpServletResponse response, HttpServletRequest request,
			ModelMap model) {
		Website web = SiteUtils.getWeb(request);
		ShopMember member = MemberThread.get();
		User user = SiteUtils.getUser(request);
		if(member == null || member.getUsername()==null||user==null){
			ResponseUtils.renderJson(response, "false");
			return null;
		}
		if(productId==null||productSiteMng.findById(productId)==null){
			return FrontHelper.pageNotFound(web, model, request);
		}
		Consult bean = consultMng.saveOrUpdate(productId, content, member.getId());
		if (bean == null) {
			ResponseUtils.renderJson(response, "sameUsually");
			return null;
		}
		ResponseUtils.renderJson(response, "success");
		return null;
	}

	/**
	 * 发布评论
	 */
	@RequestMapping(value = "/insertDiscuss.jspx")
	public String insertDiscuss(Long productId,Long sqServiceId, String disCon,
			HttpServletResponse response, HttpServletRequest request,ModelMap model) {
		Website web = SiteUtils.getWeb(request);
		ShopMember member = MemberThread.get();
		User user = SiteUtils.getUser(request);
		if(member == null || member.getUsername()==null||user==null){
			ResponseUtils.renderJson(response, "false");
			return null;
		}
		Discuss bean =null;
		if(productId!=null&&productSiteMng.findById(productId)!=null){
			bean = discussMng.saveOrUpdate(productId, disCon, member.getId());
		}else if(sqServiceId!=null&&sqServiceMng.getById(sqServiceId)!=null){
			bean = discussMng.saveOrUpdateSer(sqServiceId, disCon, member.getId());
		}else{
			return FrontHelper.pageNotFound(web, model, request);
		} 
		if(bean == null) {
			ResponseUtils.renderJson(response, "sameUsually");
			return null;
		}
		ResponseUtils.renderJson(response, "success");
		return null;
	}

	/**
	 * 会员浏览历史记录
	 */
	@RequestMapping(value = "/historyRecord.jspx") 
	public String historyRecord(Long productId, HttpServletResponse response,HttpServletRequest request,ModelMap model) {
		Website web = SiteUtils.getWeb(request);
		ProductSite productSite = productSiteMng.findById(productId);
//		Product product = productSite.getProduct();
//		product.setViewCount(product.getViewCount()+1);
//		productMng.update(product);
//		productSite.setViewCount(productSite.getViewCount()+1);
//		productSite.setProduct(product);
//		productSiteMng.update(productSite);
		ShopMember member = MemberThread.get();
		User user = SiteUtils.getUser(request);
		if(member == null || member.getUsername()==null||user==null){
			ResponseUtils.renderJson(response, "false");
			return null;
		}
		if(productId==null||productSite==null){
			return FrontHelper.pageNotFound(web, model, request);
		}
		String str = "";
		Cookie[] cookeis = request.getCookies();
		int num = cookeis.length;
		for (int i = 0; i < num; i++) {
			if (cookeis[i].getName().equals("shop_record")) {
				str = ',' + cookeis[i].getValue();
				break;
			}
		}
		str = productId + str;
		int n = 0, m = 0;
		int j = str.length();
		for (int i = 0; i < j; i++) {
			if (str.charAt(i) == ',') {
				n++;
			}
			if (n == 6) {
				break;
			}
			m = i + 1;
		}
		Cookie cook = new Cookie("shop_record", str.substring(0,m));
		String path = request.getContextPath();
		cook.setPath(StringUtils.isBlank(path) ? "/" : path);
		response.addCookie(cook);
		return null;
	}

	@Autowired
	private ProductMng productMng;
	@Autowired
	private ProductSiteMng productSiteMng;
	@Autowired
	private SqServiceMng sqServiceMng;
	@Autowired
	private SqOrderMng sqOrderMng;
	@Autowired
	private ConsultMng consultMng;
	@Autowired
	private OrderItemMng orderItemMng;
	@Autowired
	private DiscussMng discussMng;
}
