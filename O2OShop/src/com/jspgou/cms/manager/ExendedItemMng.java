package com.jspgou.cms.manager;

import com.jspgou.cms.entity.ExendedItem;
import com.jspgou.common.page.Pagination;

/**
* This class should preserve.
* @preserve
*/
public interface ExendedItemMng {
	public Pagination getPage(int pageNo, int pageSize);

	public ExendedItem findById(Long id);

	public ExendedItem save(ExendedItem item);

	public ExendedItem update(ExendedItem item);

	public ExendedItem deleteById(Long id);

	public ExendedItem[] deleteByIds(Long[] ids);

}