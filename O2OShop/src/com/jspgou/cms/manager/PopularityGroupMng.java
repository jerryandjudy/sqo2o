package com.jspgou.cms.manager;

import com.jspgou.common.page.Pagination;
import com.jspgou.cms.entity.PopularityGroup;

public interface PopularityGroupMng {
	public Pagination getPage(int pageNo, int pageSize);

	public PopularityGroup findById(Long id);

	public PopularityGroup save(PopularityGroup bean);

	public PopularityGroup update(PopularityGroup bean);

	public PopularityGroup deleteById(Long id);
	
	public PopularityGroup[] deleteByIds(Long[] ids);
	
	public void addProduct(PopularityGroup bean,Long productIds[]);
	
	public void updateProduct(PopularityGroup bean,Long productIds[]);
}