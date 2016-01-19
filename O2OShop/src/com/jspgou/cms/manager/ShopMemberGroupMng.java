package com.jspgou.cms.manager;

import java.util.List;

import com.jspgou.cms.entity.ShopMemberGroup;

/**
* This class should preserve.
* @preserve
*/
public interface ShopMemberGroupMng {

	/**
	 * 根据积分获得会员等级
	 * 
	 * @param webId
	 * @param score
	 * @return
	 */
	public ShopMemberGroup findGroupByScore(Long webId, int score);

	public List<ShopMemberGroup> getList(Long webId);

	public ShopMemberGroup findById(Long id);

	public ShopMemberGroup save(ShopMemberGroup bean);

	public ShopMemberGroup update(ShopMemberGroup bean);

	public ShopMemberGroup deleteById(Long id);

	public ShopMemberGroup[] deleteByIds(Long[] ids);
}