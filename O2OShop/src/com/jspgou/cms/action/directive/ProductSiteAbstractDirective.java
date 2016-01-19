package com.jspgou.cms.action.directive;

import java.io.IOException;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;

import com.jspgou.core.manager.WebsiteMng;
import com.jspgou.cms.action.directive.abs.WebDirective;
import com.jspgou.cms.manager.ProductMng;
import com.jspgou.cms.manager.ProductSiteMng;

import freemarker.core.Environment;
import freemarker.template.TemplateDirectiveBody;
import freemarker.template.TemplateException;
import freemarker.template.TemplateModel;

/**
* This class should preserve.
* @preserve
*/
public abstract class ProductSiteAbstractDirective extends WebDirective {

	/**
	 * 输入参数，父类别ID。null or Long
	 */
	public static final String PARAM_CATEGORY_ID = "categoryId";
	/**
	 * 输入参数，TAG ID。null or Long
	 */
	public static final String PARAM_TAG_ID = "tagId";
	/**
	 * 输入参数，是否推荐。null or 0 or 1
	 */
	public static final String PARAM_RECOMMEND = "recommend";
	/**
	 * 输入参数，是否特价。null or 0 or 1
	 */
	public static final String PARAM_SPECIAL = "special";

	protected Long getCategoryId(Map<String, TemplateModel> params)
			throws TemplateException {
		return getLong(PARAM_CATEGORY_ID, params);
	}

	protected Long getPtypeId(Map<String, TemplateModel> params)
			throws TemplateException {
		return getLong("ptypeId", params);
	}

	protected Long getTagId(Map<String, TemplateModel> params)
			throws TemplateException {
		return getLong(PARAM_TAG_ID, params);
	}

	protected Boolean isRecommend(Map<String, TemplateModel> params)
			throws TemplateException {
		return getBool(PARAM_RECOMMEND, params);
	}

	protected Boolean isSpecial(Map<String, TemplateModel> params)
			throws TemplateException {
		return getBool(PARAM_SPECIAL, params);
	}

	protected Boolean isHostSale(Map<String, TemplateModel> params)
			throws TemplateException {
		return getBool("hostSale", params);
	}

	protected Boolean isNewProduct(Map<String, TemplateModel> params)
			throws TemplateException {
		return getBool("newProduct", params);
	}

	protected void renderBody(Environment env, TemplateModel[] loopVars,
			TemplateDirectiveBody body) throws TemplateException, IOException {
		body.render(env.getOut());
	}

	protected ProductSiteMng productSiteMng;
	protected WebsiteMng websiteMng;

	@Autowired
	public void setProductSiteMng(ProductSiteMng productSiteMng) {
		this.productSiteMng = productSiteMng;
	}

	@Autowired
	public void setWebsiteMng(WebsiteMng websiteMng) {
		this.websiteMng = websiteMng;
	}
}
