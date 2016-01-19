package com.jspgou.cms.dao;

import java.util.List;

import com.jspgou.common.hibernate3.Updater;
import com.jspgou.cms.entity.Brand;

/**
* This class should preserve.
* @preserve
*/
public interface BrandDao {
	public List<Brand> getAllList();
	
	public List<Brand> getList();

	public List<Brand> getList(Long webId, int firstResult, int maxResults,
			boolean cacheable);

	public Brand findById(Long id);

	public Brand save(Brand bean);

	public Brand updateByUpdater(Updater<Brand> updater);

	public Brand deleteById(Long id);
	
	public List<Brand> getListByCate(Long categoryId);//获得某类型的品牌
	public List<Brand> getListByCateSearchName(String searchName);//获得某类型的品牌
	public Brand getsiftBrand();//获得精选品牌;
	
	/**
	 * 获得某个品牌名称的个数
	 * 
	 * @param 
	 *         品牌名称
	 * @return int
	 */
	public int countByBrandName(String brandName);
}