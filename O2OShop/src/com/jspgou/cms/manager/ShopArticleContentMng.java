package com.jspgou.cms.manager;

import com.jspgou.common.page.Pagination;
import com.jspgou.cms.entity.ShopArticle;
import com.jspgou.cms.entity.ShopArticleContent;
/**
* This class should preserve.
* @preserve
*/
public interface ShopArticleContentMng {
	
	public Pagination getPage(int pageNo, int pageSize);

	public ShopArticleContent findById(Long id);

	public ShopArticleContent save(String content, ShopArticle article);

	public ShopArticleContent update(ShopArticleContent bean);

	public ShopArticleContent deleteById(Long id);

	public ShopArticleContent[] deleteByIds(Long[] ids);
}