package com.jspgou.cms.dao;

import com.jspgou.common.hibernate3.Updater;
import com.jspgou.cms.entity.ProductExt;

/**
* This class should preserve.
* @preserve
*/
public interface ProductExtDao {
	public ProductExt findById(Long id);

	public ProductExt save(ProductExt bean);

	public ProductExt updateByUpdater(Updater<ProductExt> updater);

}