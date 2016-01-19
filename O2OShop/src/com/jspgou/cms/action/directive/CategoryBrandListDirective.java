package com.jspgou.cms.action.directive;

import static com.jspgou.cms.Constants.TPLDIR_TAG;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;

import com.jspgou.common.web.freemarker.DirectiveUtils;
import com.jspgou.core.entity.Website;
import com.jspgou.core.manager.WebsiteMng;
import com.jspgou.cms.action.directive.abs.WebDirective;
import com.jspgou.cms.entity.Brand;
import com.jspgou.cms.entity.Category;
import com.jspgou.cms.manager.CategoryMng;

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
public class CategoryBrandListDirective extends WebDirective {
	/**
	 * 模板名称
	 */
	public static final String TPL_NAME = "category_brand";

	@SuppressWarnings("unchecked")
	public void execute(Environment env, Map params, TemplateModel[] loopVars,
			TemplateDirectiveBody body) throws TemplateException, IOException {
		Long webId = getWebId(params);
		Website web;
		if (webId == null) {
			web = getWeb(env, params, websiteMng);
		} else {
			web = websiteMng.findById(webId);
		}
		if (web == null) {
			throw new TemplateModelException("webId=" + webId + " not exist!");
		}
		Long id = DirectiveUtils.getLong(PARAM_ID, params);
		List<Brand>	list = new ArrayList<Brand>(categoryMng.findById(id).getBrands());
		Map<String, TemplateModel> paramsWrap = new HashMap<String, TemplateModel>(
				params);
		paramsWrap.put(OUT_LIST, ObjectWrapper.DEFAULT_WRAPPER.wrap(list));
		Map<String, TemplateModel> origMap = DirectiveUtils
				.addParamsToVariable(env, paramsWrap);
		if (isInvokeTpl(params)) {
			includeTpl(TPLDIR_TAG, TPL_NAME, web, params, env);
		} else {
			renderBody(env, loopVars, body);
		}
		DirectiveUtils.removeParamsFromVariable(env, paramsWrap, origMap);
	}

	private void renderBody(Environment env, TemplateModel[] loopVars,
			TemplateDirectiveBody body) throws TemplateException, IOException {
		body.render(env.getOut());
	}

	private CategoryMng categoryMng;
	private WebsiteMng websiteMng;

	@Autowired
	public void setCategoryMng(CategoryMng categoryMng) {
		this.categoryMng = categoryMng;
	}

	@Autowired
	public void setWebsiteMng(WebsiteMng websiteMng) {
		this.websiteMng = websiteMng;
	}
}
