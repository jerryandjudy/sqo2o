package com.jspgou.cms.action.directive;


import static com.jspgou.cms.Constants.SHOP_SYS;
import static freemarker.template.ObjectWrapper.DEFAULT_WRAPPER;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;

import com.jspgou.cms.action.directive.abs.WebDirective;
import com.jspgou.cms.entity.Adspace;
import com.jspgou.cms.entity.Advertise;
import com.jspgou.cms.manager.AdspaceMng;
import com.jspgou.cms.manager.AdvertiseMng;
import com.jspgou.common.page.Pagination;
import com.jspgou.common.web.freemarker.DirectiveUtils;
import com.jspgou.core.entity.Website;
import com.jspgou.core.manager.WebsiteMng;
import com.jspgou.core.web.SiteUtils;

import freemarker.core.Environment;
import freemarker.template.TemplateDirectiveBody;
import freemarker.template.TemplateDirectiveModel;
import freemarker.template.TemplateException;
import freemarker.template.TemplateModel;

/**
 * 广告对象标签
 */
public class AdvertiseSpaceDirective extends WebDirective {
    /**
     * 输入参数，广告ID。
     */
    public static final String PARAM_ID = "id";
    /**
	 * 模板名称
	 */
	public static final String TPL_NAME = "advertising_space";


    @SuppressWarnings("unchecked")
    public void execute(Environment env, Map params, TemplateModel[] loopVars,
            TemplateDirectiveBody body) throws TemplateException, IOException {
        Integer id = DirectiveUtils.getInt(PARAM_ID, params);
        Integer siteId = DirectiveUtils.getInt("siteId", params);
        Integer pageNo = DirectiveUtils.getInt("pageNo", params);
        Integer pageSize = DirectiveUtils.getInt("pageSize", params);
        Long categoryId=DirectiveUtils.getLong("categoryId", params);
        Website web = getWeb(env, params, websiteMng);
        Pagination page = null;
        Adspace adspace;
            
            //ad = cmsAdvertisingMng.findById(id);
//        	page= advertiseingMng.getPage(siteId,id, true,pageNo,pageSize);
        Integer s=getCount(params);
        	page=advertiseMng.getPage(categoryId,id, true, getPageNo(env), 0,getCount(params));
        Map<String, TemplateModel> paramWrap = new HashMap<String, TemplateModel>(
				params);
		paramWrap.put(OUT_PAGINATION, DEFAULT_WRAPPER.wrap(page));
		paramWrap.put(OUT_LIST, DEFAULT_WRAPPER.wrap(page.getList()));
		Map<String, TemplateModel> origMap = DirectiveUtils
				.addParamsToVariable(env, paramWrap);
		if (isInvokeTpl(params)) {
			includeTpl(SHOP_SYS, TPL_NAME, web, params, env);
		} else {
			renderBody(env, loopVars, body);
		}


		DirectiveUtils.removeParamsFromVariable(env, paramWrap, origMap);
    }
    protected void renderBody(Environment env, TemplateModel[] loopVars,
			TemplateDirectiveBody body) throws TemplateException, IOException {
		body.render(env.getOut());
	}

    @Autowired
    private AdvertiseMng advertiseMng;
    @Autowired
    private AdspaceMng adspaceMng;
    private WebsiteMng websiteMng;
    @Autowired
	public void setWebsiteMng(WebsiteMng websiteMng) {
		this.websiteMng = websiteMng;
	}
}