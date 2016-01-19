/*
*pengyangchun
*/
package com.jspgou.cms.action.directive;

import static com.jspgou.cms.Constants.SHOP_SYS;
import static freemarker.template.ObjectWrapper.DEFAULT_WRAPPER;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import com.jspgou.cms.action.directive.abs.WebDirective;
import com.jspgou.cms.entity.ShopMember;
import com.jspgou.cms.manager.CollectMng;
import com.jspgou.cms.web.threadvariable.MemberThread;
import com.jspgou.common.page.Pagination;
import com.jspgou.common.web.freemarker.DirectiveUtils;
import com.jspgou.core.entity.Website;
import com.jspgou.core.manager.WebsiteMng;

import freemarker.core.Environment;
import freemarker.template.ObjectWrapper;
import freemarker.template.TemplateDirectiveBody;
import freemarker.template.TemplateException;
import freemarker.template.TemplateModel;

/**
* This class should preserve.
* @preserve
*/
public class CollectPageDirective extends WebDirective{
	/**
	 * 模板名称
	 */
	public static final String TPL_NAME = "ArticlePage";
	/**
	 * 输入参数，父类别ID。
	 */
	public static final String PARAM_CATEGORY_ID = "channelId";

	@SuppressWarnings("unchecked")
	public void execute(Environment env, Map params, TemplateModel[] loopVars,
			TemplateDirectiveBody body) throws TemplateException, IOException {
		ShopMember member = MemberThread.get();
		Website web = getWeb(env, params, websiteMng);
		Integer count=getCount(params);
		Pagination pagination = collectMng.getList(count, getPageNo(env), member.getId());
		Map<String, TemplateModel> paramWrap = new HashMap<String, TemplateModel>(
				params);
		paramWrap.put(OUT_PAGINATION, ObjectWrapper.DEFAULT_WRAPPER
				.wrap(pagination));
		paramWrap.put(OUT_LIST, DEFAULT_WRAPPER.wrap(pagination.getList()));
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

	private CollectMng collectMng;
	private WebsiteMng websiteMng;

	@Autowired
	public void setCollectMng(CollectMng collectMng) {
		this.collectMng = collectMng;
	}

	@Autowired
	public void setWebsiteMng(WebsiteMng websiteMng) {
		this.websiteMng = websiteMng;
	}
}
