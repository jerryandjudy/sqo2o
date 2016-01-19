package com.jspgou.cms.dao;

import java.util.List;

import com.jspgou.common.hibernate3.Updater;
import com.jspgou.cms.entity.Shipping;

/**
* This class should preserve.
* @preserve
*/
public interface ShippingDao {
	/**
	 * 获得配送方式列表
	 * 
	 * @param webId
	 *            站点ID
	 * @param all
	 *            是否包含被未启用的配送方式
	 * @param cacheable
	 *            是否使用查询缓存
	 * @return
	 */
	public List<Shipping> getList(Long webId, boolean all, boolean cacheable);

	public Shipping findById(Long id);

	public Shipping save(Shipping bean);

	public Shipping updateByUpdater(Updater<Shipping> updater);

	public Shipping deleteById(Long id);
}