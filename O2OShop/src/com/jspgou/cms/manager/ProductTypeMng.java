package com.jspgou.cms.manager;

import java.util.List;

import com.jspgou.cms.entity.ProductType;

/**
* This class should preserve.
* @preserve
*/
public interface ProductTypeMng {
	/**
	 * 获得所有的商品类型列表
	 * 
	 * @param webId
	 *            站点ID
	 * @return
	 */
	public List<ProductType> getList(Long webId);
	/**
	 * 获得所有的商品类型列表
	 * 
	 * @param webId
	 *            站点ID
	 * @return
	 */
	public List<ProductType> getList();

	/**
	 * 查找商品类型
	 * 
	 * @param id
	 *            商品类型ID
	 * @return
	 */
	public ProductType findById(Long id);

	/**
	 * 保存商品类型
	 * 
	 * @param bean
	 *            待保存对象
	 * @param brandIds
	 *            关联的品牌ID数组，可以为null或空
	 * @return
	 */
	public ProductType save(ProductType bean);

	/**
	 * 更新商品类型
	 * 
	 * @param bean
	 *            待更新对象
	 * @param brandIds
	 *            品牌ID数组
	 * @return
	 */
	public ProductType update(ProductType bean);

	/**
	 * 删除商品类型
	 * 
	 * @param id
	 *            商品类型ID
	 * @return
	 */
	public ProductType deleteById(Long id);

	/**
	 * 商城商品类型
	 * 
	 * @param ids
	 *            商品类型ID数组
	 * @return
	 */
	public ProductType[] deleteByIds(Long[] ids);
}