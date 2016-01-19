package com.jspgou.cms.dao;

import com.jspgou.common.hibernate3.Updater;
import com.jspgou.common.page.Pagination;
import com.jspgou.cms.entity.ShopPay;

/**
* This class should preserve.
* @preserve
*/
public interface ShopPayDao {
	public Pagination getPageShopPay( int pageNo, int pageSize) ;
	
	public ShopPay findById(Integer id);

	public ShopPay save(ShopPay bean);

	public ShopPay updateByUpdater(Updater<ShopPay> updater);

	public ShopPay deleteById(Integer id);
}