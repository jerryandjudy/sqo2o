package com.jspgou.cms.dao;

import java.util.List;

import com.jspgou.common.hibernate3.Updater;
import com.jspgou.common.page.Pagination;
import com.jspgou.cms.entity.ShopArticle;

/**
* This class should preserve.
* @preserve
*/
public interface ShopArticleDao {
	public Pagination getPage(Long channelId,Long webId, int pageNo, int pageSize);
	public Pagination getPage(Boolean hasTitleImg,Long channelId,Long webId, int pageNo, int pageSize);

	public Pagination getPageByChannel(Long channelId, int pageNo,
			int pageSize, boolean cacheable);

	public Pagination getPageByWebsite(Long webId, int pageNo, int pageSize,
			boolean cacheable);

	public List<ShopArticle> getListByChannel(Long channelId, int firstResult,
			int maxResults, boolean cacheable);
	public List<ShopArticle> getListByChannel(Long channelId,Long webId, int firstResult,
			int maxResults, boolean cacheable);
	public List<ShopArticle> getListByChannel(Boolean hasTitleImg,Long channelId,Long webId, int firstResult,
			int maxResults, boolean cacheable);

	public List<ShopArticle> getListByWebsite(Long webId, int firstResult,
			int maxResults, boolean cacheable);
	public List<ShopArticle> getListByWebsite(Boolean hasTitleImg,Long webId, int firstResult,
			int maxResults, boolean cacheable);

	public ShopArticle findById(Long id);

	public ShopArticle save(ShopArticle bean);

	public ShopArticle updateByUpdater(Updater<ShopArticle> updater);

	public ShopArticle deleteById(Long id);
}