package com.jspgou.cms.dao;

import com.jspgou.cms.entity.Activitys;
import com.jspgou.common.page.Pagination;

/**
* This class should preserve.
* @preserve
*/
public interface ActivitysDao {
	
	
	public Activitys save(Activitys bean);
	public Activitys update(Activitys bean);
	public Activitys delete(Activitys bean);
	public Activitys findById(Long id);
	public Pagination getPage(Long siteId, Boolean isusing, String name,
			int pageNo, int pageSize);
	
}