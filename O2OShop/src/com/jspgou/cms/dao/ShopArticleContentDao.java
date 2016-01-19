package com.jspgou.cms.dao;

import com.jspgou.common.hibernate3.Updater;
import com.jspgou.common.page.Pagination;
import com.jspgou.cms.entity.ShopArticleContent;

/**
* This class should preserve.
* @preserve
*/
public interface ShopArticleContentDao {
	public Pagination getPage(int pageNo, int pageSize);

	public ShopArticleContent findById(Long id);

	public ShopArticleContent save(ShopArticleContent bean);

	public ShopArticleContent updateByUpdater(Updater<ShopArticleContent> updater);

	public ShopArticleContent deleteById(Long id);
}