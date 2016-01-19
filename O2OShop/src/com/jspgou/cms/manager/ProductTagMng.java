package com.jspgou.cms.manager;

import java.util.List;

import com.jspgou.cms.entity.ProductTag;
/**
* This class should preserve.
* @preserve
*/
public interface ProductTagMng {
	public List<ProductTag> getList(Long webId);
	public List<ProductTag> getList();

	public ProductTag findById(Long id);

	public ProductTag save(ProductTag bean);

	/**
	 * 更新标签名称
	 * 
	 * ids.length == tagNames.length的条件必须满足
	 * 
	 * @param ids
	 *            ID数组，不能为null或空。
	 * @param tagNames
	 *            标签名称数组，不能为null或空。
	 * @return 更新后的对象数组
	 */
	public ProductTag[] updateTagName(Long[] ids, String[] tagNames);

	public ProductTag[] deleteByIds(Long[] ids);
}