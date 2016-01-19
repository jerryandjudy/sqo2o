package com.jspgou.cms.dao;

import java.util.List;

import com.jspgou.common.hibernate3.Updater;
import com.jspgou.cms.entity.ShopChannel;

/**
* This class should preserve.
* @preserve
*/
public interface ShopChannelDao {
	
	/**
	 * 获得根列表
	 * 
	 * @param webId
	 * @return
	 */
	public List<ShopChannel> getTopList(Long webId, boolean cacheable,Integer count);
	
	/**
	 * 获得商品类别列表
	 * 
	 * @param webId
	 *            站点ID
	 * @param parentId
	 *            类别ID
	 * @param cacheable
	 *            是否开启查询缓存
	 * @return
	 */
	public List<ShopChannel> getChildList(Long webId, Long parentId);
	
	public List<ShopChannel> getListForChild(Long webId, Long parentId);

	public List<ShopChannel> getList(Long webId);

	public List<ShopChannel> getList(Long webId, Integer type);

	public List<ShopChannel> getListForParent(Long webId, Long currId);

	public ShopChannel getByPath(Long webId, String path);

	public ShopChannel findById(Long id);

	public ShopChannel save(ShopChannel bean);

	public ShopChannel updateByUpdater(Updater<ShopChannel> updater);

	public ShopChannel deleteById(Long id);
	
	public List<ShopChannel> getList(Long webId,Integer type,Long idBegin,Long idEnd);
}