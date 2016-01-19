package com.jspgou.cms.web;

import static com.jspgou.common.web.Constants.UTF8;
import static com.jspgou.cms.Constants.COMMON;
import static com.jspgou.cms.Constants.TPL_SUFFIX;
import static com.jspgou.cms.Constants.TPL_STYLE_PAGE_CHANNEL;

import java.io.IOException;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.springframework.ui.ModelMap;

import com.jspgou.common.web.RequestUtils;
import com.jspgou.common.web.freemarker.DirectiveUtils;
import com.jspgou.common.web.springmvc.MessageResolver;
import com.jspgou.core.entity.User;
import com.jspgou.core.entity.Website;

import freemarker.core.Environment;
import freemarker.template.TemplateException;
import freemarker.template.TemplateModel;
import freemarker.template.TemplateModelException;
import freemarker.template.TemplateNumberModel;

/**
 * 前台工具类
 * 
 * @author pengyangchun
 * This class should preserve.
 * @preserve
*/
public class FrontUtils {
	/**
	 * 传入参数，系统预定义翻页。
	 */
	public static final String PARAM_SYS_PAGE = "sysPage";
	/**
	 * 传入参数，用户自定义翻页。
	 */
	public static final String PARAM_USER_PAGE = "userPage";
	/**
	 * 总条数
	 */
	public static final String COUNT = "count";
	/**
	 * 开始条数
	 */
	public static final String FIRST = "first";
	/**
	 * 页码
	 */
	public static final String PAGE_NO = "pageNo";
	/**
	 * 提示信息
	 */
	public static final String MESSAGE = "message";
	/**
	 * 国际化参数
	 */
	public static final String ARGS = "args";
	
	/**
	 * 返回页面
	 */
	public static final String RETURN_URL = "returnUrl";
	
	/**
	 * 标签参数中获得条数。
	 * 
	 * @param params
	 * @return 如果不存在，或者小于等于0，或者大于5000则返回5000；否则返回条数。
	 * @throws TemplateException
	 */
	public static int getCount(Map<String, TemplateModel> params)
			throws TemplateException {
		Integer count = DirectiveUtils.getInt(COUNT, params);
		if (count == null || count <= 0 || count >= 5000) {
			return 5000;
		} else {
			return count;
		}
	}
	
	/**
	 * 标签中获得LIST开始条数
	 * 
	 * @param env
	 * @return
	 * @throws TemplateException
	 */
	public static int getFirst(Map<String, TemplateModel> params)
	         throws TemplateException {
       Integer first = DirectiveUtils.getInt(FIRST, params);
       if (first == null || first <= 0) {
	       return 0;
       } else {
	       return first - 1;
       }
     }
	
	/**
	 * 标签中获得页码
	 * 
	 * @param env
	 * @return
	 * @throws TemplateException
	 */
	public static int getPageNo(Environment env) throws TemplateException {
		TemplateModel pageNo = env.getGlobalVariable(PAGE_NO);
		if (pageNo instanceof TemplateNumberModel) {
			return ((TemplateNumberModel) pageNo).getAsNumber().intValue();
		} else {
			throw new TemplateModelException("'" + PAGE_NO
					+ "' not found in DataModel.");
		}
	}

	public static void includePagination(
			Map<String, TemplateModel> params, Environment env)
			throws TemplateException, IOException {
		String sysPage = DirectiveUtils.getString(PARAM_SYS_PAGE, params);
		String userPage = DirectiveUtils.getString(PARAM_USER_PAGE, params);
		if (!StringUtils.isBlank(sysPage)) {
			String tpl = TPL_STYLE_PAGE_CHANNEL + sysPage + TPL_SUFFIX;
			env.include(tpl, UTF8, true);
		} else if (!StringUtils.isBlank(userPage)) {
//			String tpl = getTplPath(site.getSolutionPath(), TPLDIR_STYLE_LIST,
//					userPage);
//			env.include(tpl, UTF8, true);
		} else {
			// 没有包含分页
		}
	}
	
	/**
	 * 信息提示页面
	 * 
	 * @param request
	 * @param model
	 * @param message
	 *            进行国际化处理
	 * @return
	 */
	public static String showMessage(HttpServletRequest request,
			ModelMap model, String message) {
		Website web = SiteUtils.getWeb(request);
		
		ShopFrontHelper.setCommonData(request, model, web, 1);
		model.put(MESSAGE, message);
		return web.getTplSys(COMMON, MessageResolver.getMessage(request,"tpl.messagePage"));	
	}
	
	/**
	 * 显示登录页面
	 * 
	 * @param request
	 * @param model
	 * @param site
	 * @param message
	 * @return
	 */
	public static String showLogin(HttpServletRequest request,
			ModelMap model) {
		StringBuilder buff = new StringBuilder("redirect:");
		buff.append("/login.jspx").append("?");
		buff.append(RETURN_URL).append("=");
		buff.append(RequestUtils.getLocation(request));
		return buff.toString();
	}
	

}
