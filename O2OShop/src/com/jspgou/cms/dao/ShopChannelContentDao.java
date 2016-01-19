package com.jspgou.cms.dao;

import com.jspgou.common.hibernate3.Updater;
import com.jspgou.common.page.Pagination;
import com.jspgou.cms.entity.ShopChannelContent;

/**
* This class should preserve.
* @preserve
*/
public interface ShopChannelContentDao {
	public Pagination getPage(int pageNo, int pageSize);

	public ShopChannelContent findById(Long id);

	public ShopChannelContent save(ShopChannelContent bean);

	public ShopChannelContent updateByUpdater(Updater<ShopChannelContent> updater);

	public ShopChannelContent deleteById(Long id);
}