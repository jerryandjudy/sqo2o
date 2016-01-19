package com.jspgou.cms.action.directive;

import static com.jspgou.cms.Constants.SHOP_SYS;

import java.io.IOException;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.support.RequestContext;

import com.jspgou.common.web.freemarker.DirectiveUtils;
import com.jspgou.core.entity.Website;
import com.jspgou.core.manager.WebsiteMng;
import com.jspgou.core.web.Constants;
import com.jspgou.cms.action.directive.abs.WebDirective;

import freemarker.core.Environment;
import freemarker.template.TemplateDirectiveBody;
import freemarker.template.TemplateException;
import freemarker.template.TemplateModel;

/**
* This class should preserve.
* @preserve
*/
public class ShoppingCartDirective extends WebDirective {
	@SuppressWarnings("unchecked")
	public void execute(Environment env, Map params, TemplateModel[] loopVars,
			TemplateDirectiveBody body) throws TemplateException, IOException {
		Website web = getWeb(env, params, websiteMng);
		RequestContext ctx = getContext(env);
		Map<String, TemplateModel> origMap = DirectiveUtils
				.addParamsToVariable(env, params);
		includeTpl(web, ctx, env);
		DirectiveUtils.removeParamsFromVariable(env, params, origMap);
	}

	private void includeTpl(Website web, RequestContext ctx, Environment env)
			throws IOException, TemplateException {
		String tpl = web
				.getTplSys(SHOP_SYS, ctx.getMessage("tpl.shoppingCart"));
		env.include(tpl, Constants.ENCODING, true);
	}

	private WebsiteMng websiteMng;

	@Autowired
	public void setWebsiteMng(WebsiteMng websiteMng) {
		this.websiteMng = websiteMng;
	}
}
