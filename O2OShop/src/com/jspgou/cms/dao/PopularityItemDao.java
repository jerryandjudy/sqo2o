package com.jspgou.cms.dao;

import java.util.List;

import com.jspgou.common.hibernate3.Updater;
import com.jspgou.common.page.Pagination;
import com.jspgou.cms.entity.PopularityItem;

public interface PopularityItemDao {
	public Pagination getPage(int pageNo, int pageSize);
	
	public List<PopularityItem> getlist(Long cartId,Long popularityGroupId);

	public PopularityItem findById(Long cartId,Long popularityId);
	
	public PopularityItem findById(Long id);

	public PopularityItem save(PopularityItem bean);

	public PopularityItem updateByUpdater(Updater<PopularityItem> updater);

	public PopularityItem deleteById(Long id);
}