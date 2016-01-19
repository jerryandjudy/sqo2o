package com.jspgou.cms.dao;

import java.util.List;

import com.jspgou.common.hibernate3.Updater;
import com.jspgou.cms.entity.ProductTag;

/**
* This class should preserve.
* @preserve
*/
public interface ProductTagDao {
	public List<ProductTag> getList(Long webId);

	public List<ProductTag> getList();
	public ProductTag findById(Long id);

	public ProductTag save(ProductTag bean);

	public ProductTag updateByUpdater(Updater<ProductTag> updater);

	public ProductTag deleteById(Long id);
}