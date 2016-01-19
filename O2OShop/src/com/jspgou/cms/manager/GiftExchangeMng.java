package com.jspgou.cms.manager;

import java.util.List;

import com.jspgou.common.page.Pagination;
import com.jspgou.cms.entity.Gift;
import com.jspgou.cms.entity.GiftExchange;
import com.jspgou.cms.entity.ShopMember;
import com.jspgou.cms.entity.ShopMemberAddress;

public interface GiftExchangeMng {
	public Pagination getPage(int pageNo, int pageSize);
	
	public GiftExchange save(Gift gift,ShopMemberAddress shopMemberAddress,ShopMember member,Integer count);

	public List<GiftExchange> getlist(Long memberId);
	
	public GiftExchange findById(Long id);

	public GiftExchange save(GiftExchange bean);

	public GiftExchange update(GiftExchange bean);

	public GiftExchange deleteById(Long id);
	
	public GiftExchange[] deleteByIds(Long[] ids);
}