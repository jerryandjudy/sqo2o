package com.jspgou.cms.manager;

import java.util.List;

import com.jspgou.common.page.Pagination;
import com.jspgou.cms.entity.ShopDictionaryType;
/**
* This class should preserve.
* @preserve
*/
public interface ShopDictionaryTypeMng {
	
	public Pagination getPage(int pageNo, int pageSize);

	public ShopDictionaryType findById(Long id);
	
	public List<ShopDictionaryType> findAll();

	public ShopDictionaryType save(ShopDictionaryType bean);

	public ShopDictionaryType update(ShopDictionaryType bean);

	public ShopDictionaryType deleteById(Long id);
	
	public ShopDictionaryType[] deleteByIds(Long[] ids);
}