package com.jspgou.cms.dao;

import com.jspgou.cms.entity.ShopMessage;
import com.jspgou.common.page.Pagination;

/**
* This class should preserve.
* @preserve
*/
public interface ShopMessageDao {
	public ShopMessage findById(Long id);

	public ShopMessage saveOrUpdate(ShopMessage bean);

	public ShopMessage update(ShopMessage bean);

	public ShopMessage deleteById(Long id);
	public Pagination getPage(int pageNo,int pageSize);
	
}