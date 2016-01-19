package com.jspgou.cms.dao;

import java.util.List;

import com.jspgou.common.hibernate3.Updater;
import com.jspgou.common.page.Pagination;
import com.jspgou.cms.entity.ShopDictionaryType;

/**
* This class should preserve.
* @preserve
*/
public interface ShopDictionaryTypeDao {
	public Pagination getPage(int pageNo, int pageSize);

	public ShopDictionaryType findById(Long id);
	
	public List<ShopDictionaryType> findAll();

	public ShopDictionaryType save(ShopDictionaryType bean);

	public ShopDictionaryType updateByUpdater(Updater<ShopDictionaryType> updater);

	public ShopDictionaryType deleteById(Long id);
}