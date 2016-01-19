package com.jspgou.cms.dao;

import java.util.List;

import com.jspgou.common.hibernate3.Updater;
import com.jspgou.common.page.Pagination;
import com.jspgou.cms.entity.StandardType;

/**
* This class should preserve.
* @preserve
*/
public interface StandardTypeDao {
	public Pagination getPage(int pageNo, int pageSize);
	
	public StandardType getByField(String field);

	public StandardType findById(Long id);
	
	public List<StandardType> getList();
	
	public List<StandardType> getList(Long categoryId);

	public StandardType save(StandardType bean);

	public StandardType updateByUpdater(Updater<StandardType> updater);

	public StandardType deleteById(Long id);
}