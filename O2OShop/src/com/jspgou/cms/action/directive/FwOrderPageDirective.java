/**
* 吉林省艾利特信息技术有限公司
* O2OShop系统
* @date 2015年6月9日
* @author liuwang
* @version 1.0
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
import com.jspgou.cms.manager.SqOrderMng;
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

public class FwOrderPageDirective extends WebDirective{
	public static final String TPL_NAME = "ArticlePage";
	@Override
	public void execute(Environment env, Map params, TemplateModel[] loopVars,
			TemplateDirectiveBody body) throws TemplateException, IOException{
		// TODO Auto-generated method stub
		ShopMember member = MemberThread.get();
		Website web = getWeb(env, params, websiteMng);
		Integer count = getCount(params);
		Integer status = getInt("status", params);
		Long code=this.getLong("code", params);
		Integer pageNo=this.getInt("pageNos", params);
		if(pageNo==null||pageNo<2){
			pageNo=getPageNo(env);
		}
		Pagination pagination=sqOrderMng.getPage(web.getId(), member.getId(), null, null, null, null, status, code,pageNo, count);
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
	@Autowired
	private WebsiteMng websiteMng;
	@Autowired
	private SqOrderMng sqOrderMng;

}
