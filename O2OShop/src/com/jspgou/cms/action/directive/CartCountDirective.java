package com.jspgou.cms.action.directive;

import static com.jspgou.cms.Constants.SHOP_SYS;
import static com.jspgou.cms.Constants.TPLDIR_TAG;
import static freemarker.template.ObjectWrapper.DEFAULT_WRAPPER;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;

import com.jspgou.common.page.Pagination;
import com.jspgou.common.web.freemarker.DirectiveUtils;
import com.jspgou.core.entity.Website;
import com.jspgou.core.manager.WebsiteMng;
import com.jspgou.core.web.SiteUtils;
import com.jspgou.cms.action.directive.abs.WebDirective;
import com.jspgou.cms.entity.Cart;
import com.jspgou.cms.entity.Category;
import com.jspgou.cms.entity.ShopMember;
import com.jspgou.cms.manager.BldMng;
import com.jspgou.cms.manager.CartItemMng;
import com.jspgou.cms.manager.CartMng;
import com.jspgou.cms.manager.CategoryMng;
import com.jspgou.cms.manager.ShopMemberMng;

import freemarker.core.Environment;
import freemarker.template.ObjectWrapper;
import freemarker.template.TemplateDirectiveBody;
import freemarker.template.TemplateException;
import freemarker.template.TemplateModel;
import freemarker.template.TemplateModelException;

/**
* This class should preserve.
* @preserve
*/
public class CartCountDirective extends WebDirective {
	/**
	 * 模板名称
	 */
	public static final String TPL_NAME = "cart_list";

	@SuppressWarnings("unchecked")
	public void execute(Environment env, Map params, TemplateModel[] loopVars,
			TemplateDirectiveBody body) throws TemplateException, IOException {
		Long webId = getWebId(params);
//		Integer count = getCount(params);
		Website web;
		if (webId == null) {
			web = getWeb(env, params, websiteMng);
		} else {
			web = websiteMng.findById(webId);
		}
		if (web == null) {
			throw new TemplateModelException("webId=" + webId + " not exist!");
		}
		Cart cart=new Cart();
		Long userId = DirectiveUtils.getLong("userId", params);
		if(userId!=null && userId>0){
		ShopMember member = shopMemberMng.getByUserId(null, userId);
		if(member!=null ){
		cart=cartMng.findById(member.getId(),web.getId());
		
		}
		if(cart==null){
			cart=new Cart();
		}
		}
		Map<String, TemplateModel> paramWrap = new HashMap<String, TemplateModel>(
				params);
		paramWrap.put(OUT_BEAN, DEFAULT_WRAPPER.wrap(cart));
		Map<String, TemplateModel> origMap = DirectiveUtils
				.addParamsToVariable(env, paramWrap);
		if (isInvokeTpl(params)) {
			includeTpl(SHOP_SYS, TPL_NAME, web, params, env);
		} else {
			renderBody(env, loopVars, body);
		}
		DirectiveUtils.removeParamsFromVariable(env, paramWrap, origMap);
	}

	private void renderBody(Environment env, TemplateModel[] loopVars,
			TemplateDirectiveBody body) throws TemplateException, IOException {
		body.render(env.getOut());
	}
	@Autowired
	private BldMng bldMng;
	@Autowired
	private CartMng cartMng;
	@Autowired
	private ShopMemberMng shopMemberMng;
	@Autowired
	private CartItemMng cartItemMng;
	private WebsiteMng websiteMng;


	@Autowired
	public void setWebsiteMng(WebsiteMng websiteMng) {
		this.websiteMng = websiteMng;
	}
}
