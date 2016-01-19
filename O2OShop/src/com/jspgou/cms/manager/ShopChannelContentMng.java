package com.jspgou.cms.manager;

import com.jspgou.common.page.Pagination;
import com.jspgou.cms.entity.ShopChannel;
import com.jspgou.cms.entity.ShopChannelContent;
/**
* This class should preserve.
* @preserve
*/
public interface ShopChannelContentMng {
	public Pagination getPage(int pageNo, int pageSize);

	public ShopChannelContent findById(Long id);

	public ShopChannelContent save(String content, ShopChannel channel);

	public ShopChannelContent update(ShopChannelContent bean);

	public ShopChannelContent deleteById(Long id);

	public ShopChannelContent[] deleteByIds(Long[] ids);
}