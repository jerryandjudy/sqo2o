/*
*pengyangchun
*/
package com.jspgou.cms.action.directive;

import static com.jspgou.cms.Constants.SHOP_SYS;
import static freemarker.template.ObjectWrapper.DEFAULT_WRAPPER;

import org.apache.commons.lang.StringUtils;   

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;

import com.jspgou.cms.action.directive.abs.WebDirective;
import com.jspgou.cms.entity.ShopMember;
import com.jspgou.cms.manager.OrderMng;
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
public class OrderPageDirective extends WebDirective{
	
	/**
	 * 模板名称
	 */
	public static final String TPL_NAME = "ArticlePage";
	public static final String ALL = "all";
	public static final String PENDING = "pending";
	public static final String PROSESSING = "processing";
	public static final String DELIVERED = "delivered";
	public static final String COMPLETE = "complete";

	@SuppressWarnings("unchecked")
	public void execute(Environment env, Map params, TemplateModel[] loopVars,
			TemplateDirectiveBody body) throws TemplateException, IOException {
		ShopMember member = MemberThread.get();
		Website web = getWeb(env, params, websiteMng);
		Integer count = getCount(params);
		Integer status = getInt("status", params);
		String userName=getString("userName", params);
		String productName=getString("productName", params);
		Long code =getLong("code", params);
		Long paymentId=getLong("paymentId",params);
		Long shippingId=getLong("shippingId",params);
	    SimpleDateFormat df=new SimpleDateFormat("yyyy-MM-dd");
	    String startTime=getString("startTime", params);
	    String endTime=getString("endTime", params);
	    Integer pageNo=this.getInt("pageNos", params);
		if(pageNo==null||pageNo<2){
			pageNo=getPageNo(env);
		}
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
	    Double startOrderTotal=getDouble("startOrderTotal", params);
	    Double endOrderTotal=getDouble("endOrderTotal", params);    
		Pagination pagination;
		pagination = orderMng.getPage(web.getId(), member.getId(),productName,userName, paymentId, 
					shippingId, start, end, startOrderTotal, endOrderTotal,status,code,pageNo, count);
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

	private OrderMng orderMng;
	private WebsiteMng websiteMng;

	@Autowired
	public void setOrderMng(OrderMng orderMng) {
		this.orderMng = orderMng;
	}

	@Autowired
	public void setWebsiteMng(WebsiteMng websiteMng) {
		this.websiteMng = websiteMng;
	}
}
