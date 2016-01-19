package com.jspgou.cms.action.directive;

import static com.jspgou.cms.Constants.SHOP_SYS;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import com.jspgou.common.web.freemarker.DirectiveUtils;
import com.jspgou.core.entity.Website;
import com.jspgou.core.manager.WebsiteMng;
import com.jspgou.cms.action.directive.abs.WebDirective;
import com.jspgou.cms.entity.ShopChannel;
import com.jspgou.cms.manager.ShopChannelMng;

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
public class ChannelListDirective extends WebDirective {
	/**
	 * 模板名称
	 */
	public static final String TPL_NAME = "TopChannel";

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
		Long parentId = DirectiveUtils.getLong(PARAM_PARENT_ID, params);
		List<ShopChannel> list;
		if (parentId != null) {
			ShopChannel channel = shopChannelMng.findById(parentId);
			if (channel != null) {
				list = new ArrayList<ShopChannel>(channel.getChild());
			} else {
				list = new ArrayList<ShopChannel>();
			}
		}else{
			list = shopChannelMng.getTopListForTag(web.getId(),this.getCount(params));
		}
		Map<String, TemplateModel> paramsWrap = new HashMap<String, TemplateModel>(
				params);
		paramsWrap.put(OUT_LIST, ObjectWrapper.DEFAULT_WRAPPER.wrap(list));
		Map<String, TemplateModel> origMap = DirectiveUtils
				.addParamsToVariable(env, paramsWrap);
		if (isInvokeTpl(params)) {
			includeTpl(SHOP_SYS, TPL_NAME, web, params, env);
		} else {
			renderBody(env, loopVars, body);
		}
		DirectiveUtils.removeParamsFromVariable(env, paramsWrap, origMap);
	}

	private void renderBody(Environment env, TemplateModel[] loopVars,
			TemplateDirectiveBody body) throws TemplateException, IOException {
		body.render(env.getOut());
	}

	private ShopChannelMng shopChannelMng;
	private WebsiteMng websiteMng;

	@Autowired
	public void setShopChannelMng(ShopChannelMng shopChannelMng) {
		this.shopChannelMng = shopChannelMng;
	}

	public void setWebsiteMng(WebsiteMng websiteMng) {
		this.websiteMng = websiteMng;
	}
}
