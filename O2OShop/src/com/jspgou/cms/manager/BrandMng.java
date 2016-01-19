package com.jspgou.cms.manager;

import java.util.List;

import com.jspgou.cms.entity.Brand;

/**
* This class should preserve.
* @preserve
*/
public interface BrandMng {
	/**
	 * 获得所有品牌列表
	 * 
	 * @return
	 */
	public List<Brand> getAllList();
	
	
	public List<Brand> getList();

	/**
	 * 获得产品列表，供标签使用。
	 * 
	 * @param webId
	 * @param firstResult
	 * @param maxResults
	 * @return
	 */
	public List<Brand> getListForTag(Long webId, int firstResult, int maxResults);

	/**
	 * 根据ID获得品牌
	 * 
	 * @param id
	 *            品牌ID
	 * @return 品牌持久化对象，如不存在则返回null。
	 */
	public Brand findById(Long id);

	/**
	 * 保存品牌
	 * 
	 * @param bean
	 *            待保存品牌对象
	 * @param text
	 *            品牌详情
	 * @param typeIds
	 *            关联的商品类型ID数组。可以为null或空。
	 * @return 被保存的品牌持久化对象
	 */
	public Brand save(Brand bean, String text);

	/**
	 * 更新品牌
	 * 
	 * @param bean
	 *            待更新的品牌对象
	 * @param text
	 *            商品详情
	 * @param typeIds
	 *            关联的商品类型ID数组。如果为null或空，将删除关联的商品类型。
	 * @return 被更新的持久化品牌对象
	 */
	public Brand update(Brand bean, String text);

	/**
	 * 删除品牌
	 * 
	 * @param id
	 *            待删除品牌ID
	 * @return 被删除的品牌
	 */
	public Brand deleteById(Long id);

	/**
	 * 删除品牌
	 * 
	 * @param ids
	 *            待删除品牌ID数组
	 * @return 被删除的品牌
	 */
	public Brand[] deleteByIds(Long[] ids);

	/**
	 * 更新排列顺序
	 * 
	 * @param ids
	 *            待更新的品牌ID数组
	 * @param priority
	 *            待更新的排列顺序
	 * @return 被更新的对象
	 */
	public Brand[] updatePriority(Long[] ids, Integer[] priority);
	//获得精选品牌;
	public Brand getsiftBrand();
	
	/**
	 * 判断品牌名称是否存在
	 * 
	 * @param 
	 *         品牌名称
	 * @return true or false
	 */
	public boolean brandNameNotExist(String brandName);
}