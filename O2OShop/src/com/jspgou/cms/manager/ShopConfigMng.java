package com.jspgou.cms.manager;

import com.jspgou.cms.entity.ShopConfig;
/**
* This class should preserve.
* @preserve
*/
public interface ShopConfigMng {
	public ShopConfig findById(Long id);

	public ShopConfig save(ShopConfig bean);

	public ShopConfig update(ShopConfig bean);

	public ShopConfig deleteById(Long id);

}