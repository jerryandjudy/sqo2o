package com.jspgou.cms.dao;

import java.util.List;

import com.jspgou.common.hibernate3.Updater;
import com.jspgou.common.page.Pagination;
import com.jspgou.cms.entity.Exended;

/**
* This class should preserve.
* @preserve
*/
public interface ExendedDao {
	public Pagination getPage(int pageNo, int pageSize);

	public Exended findById(Long id);
	
	public List<Exended> getList();
	
	public List<Exended> getList(Long typeId);

	public Exended save(Exended bean);

	public Exended updateByUpdater(Updater<Exended> updater);

	public Exended deleteById(Long id);
}