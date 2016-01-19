package com.jspgou.cms.dao;

import java.util.List;

import com.jspgou.common.hibernate3.Updater;
import com.jspgou.common.page.Pagination;
import com.jspgou.cms.entity.Standard;

/**
* This class should preserve.
* @preserve
*/
public interface StandardDao {
	public Pagination getPage(int pageNo, int pageSize);

	public Standard findById(Long id);
	
	public List<Standard> findByTypeId(Long typeId);

	public Standard save(Standard bean);

	public Standard updateByUpdater(Updater<Standard> updater);

	public Standard deleteById(Long id);
}