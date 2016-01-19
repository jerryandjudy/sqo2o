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
import com.jspgou.cms.entity.SqService;
import com.jspgou.cms.manager.CategoryMng;
import com.jspgou.cms.manager.SqServiceMng;

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
public class SqServicePageDirective extends WebDirective {
	/**
	 * 模板名称
	 */
	public static final String TPL_NAME = "sqservice_page";

	@SuppressWarnings("unchecked")
	public void execute(Environment env, Map params, TemplateModel[] loopVars,
			TemplateDirectiveBody body) throws TemplateException, IOException {
		Long webId = getWebId(params);
		Integer count = getCount(params);
		Long ctId=this.getLong("ctId", params);
		Long provinceId=this.getLong("provinceId", params);
		if(provinceId!=null&& provinceId<0){
			provinceId=null;
		}
		Long cityId=this.getLong("cityId", params);
		if(cityId!=null&& cityId<0){
			cityId=null;
		}
		Long countryId=this.getLong("countryId", params);
		if(countryId!=null&& countryId<0){
			countryId=null;
		}
		Long streetId=this.getLong("streetId", params);
		if(streetId!=null&& streetId<0){
			streetId=null;
		}
		Boolean groom=getBool("groom", params);
		Boolean status=getBool("status", params);
		String name=this.getString("name", params);
		Website web;
		if (webId == null) {
			web = getWeb(env, params, websiteMng);
		} else {
			web = websiteMng.findById(webId);
		}
		if (web == null) {
			throw new TemplateModelException("webId=" + webId + " not exist!");
		}
		Integer pageNo=this.getInt("pageNos", params);
		if(pageNo==null||pageNo<2){
			pageNo=getPageNo(env);
		}
		Pagination pagination= sqServiceMng.getPage(null,web.getId(),ctId, groom, status,  provinceId, cityId, countryId, streetId, name, pageNo, count);
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
	private SqServiceMng sqServiceMng;
	private WebsiteMng websiteMng;


	@Autowired
	public void setWebsiteMng(WebsiteMng websiteMng) {
		this.websiteMng = websiteMng;
	}
}
