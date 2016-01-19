/*
*pengyangchun
*/
package com.jspgou.cms.action.directive;

import static com.jspgou.cms.Constants.SHOP_SYS;
import static freemarker.template.ObjectWrapper.DEFAULT_WRAPPER;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;

import com.jspgou.cms.action.directive.abs.WebDirective;
import com.jspgou.cms.entity.ShopMember;
import com.jspgou.cms.manager.ShopMoneyMng;
import com.jspgou.cms.manager.ShopScoreMng;
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
public class MoneyPageDirective extends WebDirective{
	
	public static final String TPL_NAME = "ShopScorePage";
	
	@SuppressWarnings("unchecked")
	public void execute(Environment env, Map params, TemplateModel[] loopVars,
			TemplateDirectiveBody body) throws TemplateException, IOException {
		ShopMember member = MemberThread.get();
		Website web = getWeb(env, params, websiteMng);
		Integer count=getCount(params);
		Boolean status=getBool("status", params);
		SimpleDateFormat df=new SimpleDateFormat("yyyy-MM-dd");
	    String startTime=getString("startTime", params);
	    String endTime=getString("endTime", params);
	    Date start=null;
	    Date end=null;
	    try {
	    	
	       if(!StringUtils.isBlank(startTime)){
			  start=df.parse(startTime);
	      }else{
	    	  start=null;
	       } 
        if(!StringUtils.isBlank(endTime)){
	    	end=df.parse(endTime);
	    }else{
	    	end=null;
	    }
		} catch (ParseException e) {
			e.printStackTrace();
		}
	
		Pagination pagination =shopMoneyMng.getPage(member.getId(),status, start,end,getPageNo(env), count);
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
	private ShopMoneyMng shopMoneyMng;
	@Autowired
	private WebsiteMng websiteMng;
}
