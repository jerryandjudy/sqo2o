package com.jspgou.cms.dao;

import java.util.List;

import com.jspgou.common.hibernate3.Updater;
import com.jspgou.cms.entity.ShopMemberAddress;

/**
* This class should preserve.
* @preserve
*/
public interface ShopMemberAddressDao {
	public List<ShopMemberAddress> getList(Long memberId);
	public List<ShopMemberAddress> getList(Long memberId,Long siteId);
	
	public List<ShopMemberAddress> findByMemberDefault(Long memberId,Boolean isDefault);

	public ShopMemberAddress findById(Long id);

	public ShopMemberAddress save(ShopMemberAddress bean);

	public ShopMemberAddress updateByUpdater(Updater<ShopMemberAddress> updater);

	public ShopMemberAddress deleteById(Long id);
}