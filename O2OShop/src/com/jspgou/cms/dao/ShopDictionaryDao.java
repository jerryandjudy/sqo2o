package com.jspgou.cms.dao;

import java.util.List;

import com.jspgou.common.hibernate3.Updater;
import com.jspgou.common.page.Pagination;
import com.jspgou.cms.entity.ShopDictionary;

/**
* This class should preserve.
* @preserve
*/
public interface ShopDictionaryDao {
	
	public Pagination getPage(int pageNo, int pageSize);
	
	public Pagination getPage(String name,Long typeId,int pageNo, int pageSize);

	public ShopDictionary findById(Long id);
	
	public List<ShopDictionary> getListByType(Long typeId);

	public ShopDictionary save(ShopDictionary bean);

	public ShopDictionary updateByUpdater(Updater<ShopDictionary> updater);

	public ShopDictionary deleteById(Long id);
}