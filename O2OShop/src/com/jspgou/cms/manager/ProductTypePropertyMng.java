package com.jspgou.cms.manager;

import java.util.List;
import com.jspgou.common.page.Pagination;
import com.jspgou.cms.entity.ProductTypeProperty;
/**
* This class should preserve.
* @preserve
*/
public interface ProductTypePropertyMng {
	
	public Pagination getList(int pageNo,int pageSize,Long typeid,Boolean isCategory,String searchType,String searchContent);
	
	public List<ProductTypeProperty> getList(Long typeId,Boolean isCategory);
	
	public void saveList(List<ProductTypeProperty> list);

	public ProductTypeProperty findById(Long id);

	public ProductTypeProperty save(ProductTypeProperty bean);
	
	public void updatePriority(Long[] wids, Integer[] sort,String[] propertyName, Boolean[] single);

//	public ProductTypeProperty updateByUpdater(Updater<ProductTypeProperty> updater);

	public ProductTypeProperty deleteById(Long id);
	
	public ProductTypeProperty[] deleteByIds(Long[] ids);
	
	public ProductTypeProperty update(ProductTypeProperty bean);
	/**
	 * 根据属性名称或则商品类型 查找属性列表
	 * @param searchType
	 * @param searchContent
	 * @return
	 */
	public List<ProductTypeProperty> findBySearch(String searchType,String searchContent);
	
	/**
	 * 根据类型Id查找属性list
	 * @param typeid
	 * @return
	 */
	public List<ProductTypeProperty> findListByTypeId(Long typeid);
	
	/**
	 * 根据类型Id查找属性list
	 * @param typeid
	 * @param isCategory
	 * @return
	 */
	public List<ProductTypeProperty> getList(Long typeId,boolean isCategory);
}

