package com.jspgou.cms.dao;

import java.util.Date;
import java.util.List;

import com.jspgou.common.hibernate3.Updater;
import com.jspgou.common.page.Pagination;
import com.jspgou.cms.entity.ShopScore;

/**
* This class should preserve.
* @preserve
*/
public interface ShopScoreDao {
	public Pagination getPage(Long memberId,Boolean status,Boolean useStatus,
			Date startTime,Date endTime,Integer pageSize,Integer pageNo);
	
	public List<ShopScore> getlist(String code);

	public ShopScore findById(Long id);

	public ShopScore save(ShopScore bean);

	public ShopScore updateByUpdater(Updater<ShopScore> updater);

	public ShopScore deleteById(Long id);
}