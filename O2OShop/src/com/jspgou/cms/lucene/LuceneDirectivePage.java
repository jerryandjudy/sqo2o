package com.jspgou.cms.lucene;

import static com.jspgou.cms.Constants.LUCENE_JSPGOU;
import static com.jspgou.cms.Constants.SHOP_SYS;
import static freemarker.template.ObjectWrapper.DEFAULT_WRAPPER;

import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletContext;

import org.apache.lucene.queryParser.ParseException;
import org.springframework.beans.factory.annotation.Autowired;

import com.jspgou.common.page.Pagination;
import com.jspgou.common.web.freemarker.DirectiveUtils;
import com.jspgou.core.entity.Website;
import com.jspgou.core.manager.WebsiteMng;
import com.jspgou.core.web.SiteUtils;

import freemarker.core.Environment;
import freemarker.template.ObjectWrapper;
import freemarker.template.TemplateDirectiveBody;
import freemarker.template.TemplateException;
import freemarker.template.TemplateModel;
import freemarker.template.TemplateNumberModel;

/**
* This class should preserve.
* @preserve
*/
public class LuceneDirectivePage extends LuceneDirectiveAbstract{
	/**
	 * 模板名称
	 */
	public static final String TPL_NAME = "ProductPage";

	@SuppressWarnings("unchecked")
	public void execute(Environment env, Map params, TemplateModel[] loopVars,
			TemplateDirectiveBody body) throws TemplateException, IOException {
		Website web = getWeb(env, params, websiteMng);
//		Website web = websiteMng.findById((long)1);
		String query = getQuery(params);
		Long ctgId = getCtgId(params);
		Long brandId =getBrandId(params);
		@SuppressWarnings("unused")
		Long type=getPtypeId(params);
		Date start=getStartDate(params);
		Date end=getEndDate(params);
		Integer orderBy=getInt("orderBy",params);
		int pageNo =((TemplateNumberModel) env.getGlobalVariable(PAGE_NO)).getAsNumber().intValue();
		Pagination pagination; 
		try {
			String path = servletContext.getRealPath(LUCENE_JSPGOU);
			pagination = luceneProductSvc.search(path, query, web.getId(), ctgId, brandId,start, end, orderBy,pageNo,getCount(params));
		} catch (ParseException e) {
			throw new RuntimeException(e);
		}
		Map<String, TemplateModel> paramWrap = new HashMap<String, TemplateModel>(
				params);
		paramWrap.put(OUT_PAGINATION, ObjectWrapper.DEFAULT_WRAPPER.wrap(pagination));
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

	@Autowired
	private LuceneProductSvc luceneProductSvc;
	@Autowired
	private ServletContext servletContext;
	
	protected WebsiteMng websiteMng;
	
	@Autowired
	public void setWebsiteMng(WebsiteMng websiteMng) {
		this.websiteMng = websiteMng;
	}
}
