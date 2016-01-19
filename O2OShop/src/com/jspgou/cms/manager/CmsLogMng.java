package com.jspgou.cms.manager;

import javax.servlet.http.HttpServletRequest;

import com.jspgou.cms.entity.CmsLog;
import com.jspgou.common.page.Pagination;
import com.jspgou.core.entity.User;


public interface CmsLogMng {
	public Pagination getPage(Integer category, Long siteId,
			String username, String title, String ip, int pageNo, int pageSize);

	public CmsLog findById(Integer id);

	public CmsLog operating(HttpServletRequest request, String title,
			String content);

	public CmsLog loginFailure(HttpServletRequest request,String content);

	public CmsLog loginSuccess(HttpServletRequest request, User user);

	public CmsLog save(CmsLog bean);

	public CmsLog deleteById(Integer id);

	public CmsLog[] deleteByIds(Integer[] ids);

	public int deleteBatch(Integer category, Integer siteId, Integer days);
}