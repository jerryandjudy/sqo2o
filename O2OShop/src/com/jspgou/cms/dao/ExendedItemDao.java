package com.jspgou.cms.dao;

import com.jspgou.cms.entity.ExendedItem;
import com.jspgou.common.hibernate3.Updater;
import com.jspgou.common.page.Pagination;

/**
* This class should preserve.
* @preserve
*/
public interface ExendedItemDao {
	public Pagination getPage(int pageNo, int pageSize);

	public ExendedItem findById(Long id);

	public ExendedItem save(ExendedItem bean);

	public ExendedItem updateByUpdater(Updater<ExendedItem> updater);

	public ExendedItem deleteById(Long id);
}