package com.jspgou.cms.manager;

import java.util.List;

import com.jspgou.common.page.Pagination;
import com.jspgou.cms.entity.ShopArticle;
/**
* This class should preserve.
* @preserve
*/
public interface ShopArticleMng {
	
	public Pagination getPage(Long channelId,Long webId, int pageNo, int pageSize);

	public Pagination getPageForTag(Long webId, Long channelId, int pageNo,
			int pageSize);
	
	public List<ShopArticle> getListForTag(Long webId, Long channelId,
			int firstResult, int maxResults);
	
	public Pagination getPage(Boolean hasTitleImg,Long channelId,Long webId, int pageNo, int pageSize);
	public List<ShopArticle> getListForTag(Boolean hasTitleImg,Long webId, Long channelId,
			int firstResult, int maxResults);

	public ShopArticle findById(Long id);

	public ShopArticle save(ShopArticle bean, Long channelId, String content);

	public ShopArticle update(ShopArticle bean, Long channelId, String content);

	public ShopArticle deleteById(Long id);

	public ShopArticle[] deleteByIds(Long[] ids);
}