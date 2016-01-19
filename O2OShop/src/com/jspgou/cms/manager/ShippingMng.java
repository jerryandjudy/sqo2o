package com.jspgou.cms.manager;

import java.util.List;

import com.jspgou.cms.entity.Shipping;
/**
* This class should preserve.
* @preserve
*/
public interface ShippingMng {
	/**
	 * 获得发货方式列表
	 * 
	 * @param webId
	 *            站点ID
	 * @param all
	 *            是否包含所有发货方式（包括禁用的发货方式）
	 * @return
	 */
	public List<Shipping> getList(Long webId, boolean all);

	public List<Shipping> getListForCart(Long webId, Long countryId,
			int weight, int count);

	public Shipping findById(Long id);

	public Shipping save(Shipping bean);

	public Shipping update(Shipping bean);

	public Shipping deleteById(Long id);

	public Shipping[] deleteByIds(Long[] ids);

	public Shipping[] updatePriority(Long[] ids, Integer[] priority);
}