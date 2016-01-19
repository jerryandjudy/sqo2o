package com.jspgou.cms.dao;

import java.util.Date;

import com.jspgou.cms.entity.CmsLog;
import com.jspgou.common.page.Pagination;

public interface CmsLogDao {
	public Pagination getPage(Integer category, Long siteId, Integer userId,
			String title, String ip, int pageNo, int pageSize);

	public CmsLog findById(Integer id);

	public CmsLog save(CmsLog bean);

	public CmsLog deleteById(Integer id);

	public int deleteBatch(Integer category, Integer siteId, Date before);
}