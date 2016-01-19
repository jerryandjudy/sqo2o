package com.jspgou.cms.dao;

import com.jspgou.common.hibernate3.Updater;
import com.jspgou.cms.entity.ProductText;

/**
* This class should preserve.
* @preserve
*/
public interface ProductTextDao {
	public ProductText updateByUpdater(Updater<ProductText> updater);

	public ProductText save(ProductText text);
}