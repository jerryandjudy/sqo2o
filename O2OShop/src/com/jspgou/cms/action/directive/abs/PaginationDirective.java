package com.jspgou.cms.action.directive.abs;

import static com.jspgou.common.web.Constants.UTF8;
import static com.jspgou.cms.web.FrontUtils.PARAM_SYS_PAGE;
import static com.jspgou.cms.web.FrontUtils.PARAM_USER_PAGE;
import static com.jspgou.cms.Constants.TPL_SUFFIX;
import static com.jspgou.cms.Constants.TPL_STYLE_PAGE_CONTENT;
import java.io.IOException;
import java.util.Map;

import org.apache.commons.lang.StringUtils;

import com.jspgou.cms.web.FrontUtils;
import com.jspgou.common.web.freemarker.DirectiveUtils;
import com.jspgou.cms.action.directive.abs.WebDirective;

import freemarker.core.Environment;
import freemarker.template.TemplateDirectiveBody;
import freemarker.template.TemplateException;
import freemarker.template.TemplateModel;

/**
* This class should preserve.
* @preserve
*/
public class PaginationDirective extends WebDirective{

    public static final String PAGINATION_PATH = "/WEB-INF/tag/style_pagination/style";
    public static final String PARAM_SYTLE_NAME = "style";
    
	/**
	 * 是否为内容分页。1：内容分页；0：栏目分页。默认栏目分页。
	 */
	public static final String PARAM_CONTENT = "content";

    @SuppressWarnings("unchecked")
	public void execute(Environment env, Map params,
    		TemplateModel[] atemplatemodel, TemplateDirectiveBody body)
        throws TemplateException, IOException{
        Map model = DirectiveUtils.addParamsToVariable(env, params);
        String content =DirectiveUtils.getString(PARAM_CONTENT, params);
		if ("1".equals(content)) {
			String sysPage = DirectiveUtils.getString(PARAM_SYS_PAGE, params);
			String userPage = DirectiveUtils.getString(PARAM_USER_PAGE, params);
			if (!StringUtils.isBlank(sysPage)) {
				String tpl = TPL_STYLE_PAGE_CONTENT + sysPage + TPL_SUFFIX;
				env.include(tpl, UTF8, true);
			} else if (!StringUtils.isBlank(userPage)) {
				String tpl = TPL_STYLE_PAGE_CONTENT + userPage + TPL_SUFFIX;
				env.include(tpl, UTF8, true);
			} else {
				// 没有包含分页
			}
		} else {
			FrontUtils.includePagination(params, env);
		}
        DirectiveUtils.removeParamsFromVariable(env, params, model);
    }


}
