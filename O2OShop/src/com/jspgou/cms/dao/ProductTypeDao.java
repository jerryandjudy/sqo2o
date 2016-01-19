package com.jspgou.cms.dao;

import java.util.List;

import com.jspgou.common.hibernate3.Updater;
import com.jspgou.cms.entity.ProductType;

/**
* This class should preserve.
* @preserve
*/
public interface ProductTypeDao {
	public List<ProductType> getList(Long webId);
	public List<ProductType> getList();

	public ProductType findById(Long id);

	public ProductType save(ProductType bean);

	public ProductType updateByUpdater(Updater<ProductType> updater);

	public ProductType deleteById(Long id);
}