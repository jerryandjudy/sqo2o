package com.jspgou.cms.dao;

import com.jspgou.common.hibernate3.Updater;
import com.jspgou.common.page.Pagination;
import com.jspgou.cms.entity.PopularityGroup;

public interface PopularityGroupDao {
	public Pagination getPage(int pageNo, int pageSize);

	public PopularityGroup findById(Long id);

	public PopularityGroup save(PopularityGroup bean);

	public PopularityGroup updateByUpdater(Updater<PopularityGroup> updater);

	public PopularityGroup deleteById(Long id);
}