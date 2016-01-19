package com.jspgou.cms.manager;

import com.jspgou.cms.entity.ShopMessage;
import com.jspgou.common.page.Pagination;
/**
* This class should preserve.
* @preserve
*/
public interface ShopMessageMng  {
	public ShopMessage findById(Long id);

	public ShopMessage update(ShopMessage ShopMessage);

	public ShopMessage deleteById(Long id);
	
	public ShopMessage saveOrUpdate(ShopMessage bean);
	
	public Pagination getPage(int pageNo,int pageSize);
	
	
}