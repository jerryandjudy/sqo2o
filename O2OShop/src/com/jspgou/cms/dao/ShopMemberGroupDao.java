package com.jspgou.cms.dao;

import java.util.List;

import com.jspgou.common.hibernate3.Updater;
import com.jspgou.cms.entity.ShopMemberGroup;

/**
 * 会员组DAO
 * 
 * @author liufang
 * This class should preserve.
 * @preserve
 */
public interface ShopMemberGroupDao {
	/**
	 * 获得会员组列表。按积分升序排列。
	 * 
	 * @param webId
	 * @param cacheable
	 * @return
	 */
	public List<ShopMemberGroup> getList(Long webId, boolean cacheable);

	public ShopMemberGroup findById(Long id);

	public ShopMemberGroup save(ShopMemberGroup bean);

	public ShopMemberGroup updateByUpdater(Updater<ShopMemberGroup> updater);

	public ShopMemberGroup deleteById(Long id);
}