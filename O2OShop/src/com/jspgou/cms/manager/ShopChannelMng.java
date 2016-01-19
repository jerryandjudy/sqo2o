package com.jspgou.cms.manager;

import java.util.List;
import com.jspgou.cms.entity.ShopChannel;
/**
* This class should preserve.
* @preserve
*/
public interface ShopChannelMng {
	/**
	 * 获得根列表
	 * 
	 * @param webId
	 * @return
	 */
	public List<ShopChannel> getTopList(Long webId);
	
	/**
	 * 获得parentId子列表
	 * 
	 * @param webId
	 * 
	 * @param parentId
	 * @return
	 */
	public List<ShopChannel> getChildList(Long wegId,Long parentId);
	
	public List<ShopChannel> getList(Long webId);

	public List<ShopChannel> getArticleList(Long webId);

	public List<ShopChannel> getListForParent(Long webId, Long currId);

	public List<ShopChannel> getListForParentNoSort(Long webId, Long currId);

	public List<ShopChannel> getTopListForTag(Long webId,Integer count);

	public ShopChannel getByPath(Long webId, String path);

	public ShopChannel findById(Long id);

	public ShopChannel save(ShopChannel bean, Long parentId, String content);

	public ShopChannel update(ShopChannel bean, Long parentId, String content);

	public ShopChannel deleteById(Long id);

	public ShopChannel[] deleteByIds(Long[] ids);
	
	/**
	 * 更新排列顺序
	 * 
	 * @param ids
	 *            待排列ID数组
	 * @param priority
	 *            排列顺序
	 * @return 排序后产品对象数组
	 */
	public ShopChannel[] updatePriority(Long[] ids, Integer[] priority);
	
	public List<ShopChannel> getAloneChannelList(Long webId);
	
	public List<ShopChannel> getList(Long webId,Long idBegin,Long idEnd);
}