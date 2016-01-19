package com.jspgou.cms.web;

import static com.jspgou.cms.Constants.REQUEST_SHOP_CONFIG_KEY;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.jspgou.core.entity.Website;
import com.jspgou.core.web.SiteUtils;
import com.jspgou.cms.entity.ShopConfig;
import com.jspgou.cms.entity.ShopMember;
import com.jspgou.cms.manager.ShopConfigMng;
import com.jspgou.cms.service.LoginSvc;
import com.jspgou.cms.web.threadvariable.GroupThread;
import com.jspgou.cms.web.threadvariable.MemberThread;

/**
 * 商城信息拦截器
 * 
 * 处理Config、和登录信息
 * 
 * @author liufang
* This class should preserve.
* @preserve
*/
public class FrontContextInterceptor extends HandlerInterceptorAdapter {

	@Override
	public boolean preHandle(HttpServletRequest request,
			HttpServletResponse response, Object handler)
			throws ServletException {
		Website web = SiteUtils.getWeb(request);
		ShopConfig config = shopConfigMng.findById(web.getId());
		if (config == null) {
			throw new IllegalStateException(
					"no ShopConfig found in Website id=" + web.getId());
		}
		request.setAttribute(REQUEST_SHOP_CONFIG_KEY, config);
		ShopMember member = loginSvc.getMember(request, response, web);
		// 将会员、会员组信息放入ThreadLocal，便于调用。
		if (member != null) {
			MemberThread.set(member);
			GroupThread.set(member.getGroup());
		}
		return true;
	}

	@Override
	public void afterCompletion(HttpServletRequest request,
			HttpServletResponse response, Object handler, Exception ex)
			throws Exception {
		MemberThread.remove();
		GroupThread.remove();
	}

	@Autowired
	private ShopConfigMng shopConfigMng;
	@Autowired
	private LoginSvc loginSvc;
}
