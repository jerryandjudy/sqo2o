package com.jspgou.cms.dao;

import com.jspgou.common.hibernate3.Updater;
import com.jspgou.cms.entity.ShopConfig;

/**
* This class should preserve.
* @preserve
*/
public interface ShopConfigDao {
	public ShopConfig findById(Long id);

	public ShopConfig save(ShopConfig bean);

	public ShopConfig updateByUpdater(Updater<ShopConfig> updater);

	public ShopConfig deleteById(Long id);
}