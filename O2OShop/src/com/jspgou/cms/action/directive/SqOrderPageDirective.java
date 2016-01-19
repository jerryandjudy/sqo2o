package com.jspgou.cms.action.directive;

import static com.jspgou.cms.Constants.TPLDIR_TAG;

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
import com.jspgou.cms.action.directive.abs.WebDirective;
import com.jspgou.cms.entity.Category;
import com.jspgou.cms.entity.ShopMember;
import com.jspgou.cms.entity.SqService;
import com.jspgou.cms.manager.CategoryMng;
import com.jspgou.cms.manager.SqOrderMng;
import com.jspgou.cms.manager.SqServiceMng;
import com.jspgou.cms.web.threadvariable.MemberThread;

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
public class SqOrderPageDirective extends WebDirective {
	/**
	 * 模板名称
	 */
	public static final String TPL_NAME = "sqorder_page";

	@SuppressWarnings("unchecked")
	public void execute(Environment env, Map params, TemplateModel[] loopVars,
			TemplateDirectiveBody body) throws TemplateException, IOException {
		Integer count = getCount(params);
		Integer status=this.getInt("status", params);
		ShopMember member = MemberThread.get();
		Website web = getWeb(env, params, websiteMng);
		 Integer pageNo=this.getInt("pageNos", params);
			if(pageNo==null||pageNo<2){
				pageNo=getPageNo(env);
			}
		Pagination pagination= sqOrderMng.getPage(web.getId(), member.getId(), null, null, null, null, status, null, pageNo, count);
		Map<String, TemplateModel> paramsWrap = new HashMap<String, TemplateModel>(
				params);
		paramsWrap.put(OUT_PAGINATION, ObjectWrapper.DEFAULT_WRAPPER
				.wrap(pagination));
		paramsWrap.put(OUT_LIST, ObjectWrapper.DEFAULT_WRAPPER.wrap(pagination.getList()));
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
	@Autowired
	private SqOrderMng sqOrderMng;
	private WebsiteMng websiteMng;


	@Autowired
	public void setWebsiteMng(WebsiteMng websiteMng) {
		this.websiteMng = websiteMng;
	}
}
