package com.jspgou.cms.manager;

import com.jspgou.cms.entity.ShopPay;
/**
* This class should preserve.
* @preserve
*/
public interface ShopPayMng {
	public ShopPay findById(Integer id);

	public ShopPay save(ShopPay bean);

	public ShopPay updateByUpdater(ShopPay updater);

	public ShopPay deleteById(Integer id);
	
	public ShopPay[] deleteByIds(Integer[] ids);
}