package com.jspgou.cms.action.directive;

import static com.jspgou.cms.Constants.SHOP_SYS;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;

import com.jspgou.common.web.freemarker.DirectiveUtils;
import com.jspgou.core.entity.Website;
import com.jspgou.core.manager.WebsiteMng;
import com.jspgou.cms.action.directive.abs.WebDirective;
import com.jspgou.cms.entity.ShopArticle;
import com.jspgou.cms.manager.ShopArticleMng;

import freemarker.core.Environment;
import freemarker.template.ObjectWrapper;
import freemarker.template.TemplateDirectiveBody;
import freemarker.template.TemplateException;
import freemarker.template.TemplateModel;
import freemarker.template.TemplateModelException;
import freemarker.template.TemplateNumberModel;

/**
* This class should preserve.
* @preserve
*/
public class ArticleListDirective extends WebDirective {
	/**
	 * 模板名称
	 */
	public static final String TPL_NAME = "ArticleList";
	/**
	 * 输入参数，父类别ID。
	 */
	public static final String PARAM_CHANNEL_ID = "channelId";

	@SuppressWarnings("unchecked")
	public void execute(Environment env, Map params, TemplateModel[] loopVars,
			TemplateDirectiveBody body) throws TemplateException, IOException {
		Website web = getWeb(env, params, websiteMng);
		Long channelId = getChannelId(params);
		Boolean hasTitleImg=getBool("hasTitleImg", params);
		List<ShopArticle> list = shopArticleMng.getListForTag(hasTitleImg,web.getId(),channelId, 0, getCount(params));
		Map<String, TemplateModel> paramWrap = new HashMap<String, TemplateModel>(
				params);
		paramWrap.put(OUT_LIST, ObjectWrapper.DEFAULT_WRAPPER.wrap(list));
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

	private Long getChannelId(Map<String, TemplateModel> params)
			throws TemplateException {
		TemplateModel parentId = params.get(PARAM_CHANNEL_ID);
		if (parentId == null) {
			return null;
		}
		if (parentId instanceof TemplateNumberModel) {
			return ((TemplateNumberModel) parentId).getAsNumber().longValue();
		} else {
			throw new TemplateModelException("The '" + PARAM_CHANNEL_ID
					+ "' parameter must be a number.");
		}
	}

	private ShopArticleMng shopArticleMng;
	private WebsiteMng websiteMng;

	@Autowired
	public void setShopArticleMng(ShopArticleMng shopArticleMng) {
		this.shopArticleMng = shopArticleMng;
	}

	@Autowired
	public void setWebsiteMng(WebsiteMng websiteMng) {
		this.websiteMng = websiteMng;
	}

}
