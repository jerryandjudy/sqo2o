package com.jspgou.cms.dao;

import java.util.List;

import com.jspgou.common.hibernate3.Updater;
import com.jspgou.common.page.Pagination;
import com.jspgou.cms.entity.GiftExchange;

public interface GiftExchangeDao {
	public Pagination getPage(int pageNo, int pageSize);
	
	public List<GiftExchange> getlist(Long memberId);

	public GiftExchange findById(Long id);

	public GiftExchange save(GiftExchange bean);

	public GiftExchange updateByUpdater(Updater<GiftExchange> updater);

	public GiftExchange deleteById(Long id);
}