package com.jspgou.cms.manager;

import java.util.List;

import com.jspgou.common.page.Pagination;
import com.jspgou.cms.entity.ShopDictionary;
/**
* This class should preserve.
* @preserve
*/
public interface ShopDictionaryMng {
	
	public Pagination getPage(int pageNo, int pageSize);
	
	public Pagination getPage(String name,Long typeId,int pageNo, int pageSize);

	public ShopDictionary findById(Long id);
	
	public List<ShopDictionary> getListByType(Long typeId);

	public ShopDictionary save(ShopDictionary bean);

	public ShopDictionary update(ShopDictionary bean);

	public ShopDictionary deleteById(Long id);
	
	public ShopDictionary[] deleteByIds(Long[] ids);
	
	public ShopDictionary[] updatePriority(Long[] ids, Integer[] priority);
}