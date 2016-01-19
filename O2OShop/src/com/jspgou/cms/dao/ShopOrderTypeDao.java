package com.jspgou.cms.dao;

import java.util.List;

import com.jspgou.cms.entity.ShopOrderType;
import com.jspgou.common.hibernate3.Updater;
import com.jspgou.common.page.Pagination;

/**
* This class should preserve.
* @preserve
*/
public interface ShopOrderTypeDao {
	public List<ShopOrderType> getAllList();

	public ShopOrderType findById(Long id);

	public ShopOrderType findByTypeCode(String typeCode);
	
	public ShopOrderType save(ShopOrderType bean);

	public ShopOrderType updateByUpdater(Updater<ShopOrderType> updater);

	public ShopOrderType deleteById(Long id);
	
	
	
}