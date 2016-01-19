package com.jspgou.cms.manager;

import java.util.List;

import com.jspgou.common.page.Pagination;
import com.jspgou.cms.entity.Cart;
import com.jspgou.cms.entity.PopularityItem;

public interface PopularityItemMng {
	public Pagination getPage(int pageNo, int pageSize);
	
	public List<PopularityItem> getlist(Long cartId,Long popularityGroupId);

	public PopularityItem findById(Long id);
	
	public PopularityItem findById(Long cartId,Long popularityId);

	public PopularityItem save(PopularityItem bean);

	public PopularityItem update(PopularityItem bean);

	public PopularityItem deleteById(Long id);
	
	public PopularityItem[] deleteByIds(Long[] ids);

	public void save(Cart cart, Long popularityId);
}