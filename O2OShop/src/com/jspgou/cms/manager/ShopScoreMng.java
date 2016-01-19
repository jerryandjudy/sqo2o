package com.jspgou.cms.manager;

import java.util.Date;
import java.util.List;

import com.jspgou.cms.entity.ShopScore;
import com.jspgou.common.page.Pagination;
/**
* This class should preserve.
* @preserve
*/
public interface ShopScoreMng {
	public Pagination getPage(Long memberId,Boolean status,Boolean useStatus,
			Date startTime,Date endTime,Integer pageSize,Integer pageNo);
	
	public List<ShopScore> getlist(String code);

	public ShopScore findById(Long id);

	public ShopScore save(ShopScore bean);

	public ShopScore update(ShopScore bean);

	public ShopScore deleteById(Long id);
	
	public ShopScore[] deleteByIds(Long[] ids);
}