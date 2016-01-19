package com.jspgou.cms.manager;

import java.util.List;

import com.jspgou.cms.entity.ShopMemberAddress;
/**
* This class should preserve.
* @preserve
*/
public interface ShopMemberAddressMng {
	public List<ShopMemberAddress> getList(Long memberId);
	public List<ShopMemberAddress> getList(Long memberId,Long siteId);
	
	public ShopMemberAddress findByMemberDefault(Long memberId,Boolean isDefault);
	
	public List<ShopMemberAddress> findByMemberId(Long memberId);

	public ShopMemberAddress findById(Long id);

	public ShopMemberAddress save(ShopMemberAddress bean);

	public ShopMemberAddress updateByUpdater(ShopMemberAddress bean);

	public ShopMemberAddress deleteById(Long id, Long memberId);
}