package com.jspgou.cms.dao;

import java.util.Date;

import com.jspgou.common.hibernate3.Updater;
import com.jspgou.common.page.Pagination;
import com.jspgou.cms.entity.ShopMoney;

/**
* This class should preserve.
* @preserve
*/
public interface ShopMoneyDao {
	public Pagination getPage(int pageNo, int pageSize);
	
	public Pagination getPage(Long memberId,Boolean status,
			Date startTime,Date endTime,Integer pageSize,Integer pageNo);

	public ShopMoney findById(Long id);

	public ShopMoney save(ShopMoney bean);

	public ShopMoney updateByUpdater(Updater<ShopMoney> updater);

	public ShopMoney deleteById(Long id);
}