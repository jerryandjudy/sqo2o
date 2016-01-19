package com.jspgou.cms.dao;

import java.util.Date;
import java.util.List;

import com.jspgou.common.hibernate3.Updater;
import com.jspgou.common.page.Pagination;
import com.jspgou.cms.entity.Coupon;

/**
* This class should preserve.
* @preserve
*/
public interface CouponDao {
	public Pagination getPage(int pageNo, int pageSize);
	
	public Pagination getPageByUsing(Date newTime,int pageNo, int pageSize);
	
	public List<Coupon> getList();
	
	public Coupon findById(Long id);

	public Coupon save(Coupon bean);

	public Coupon updateByUpdater(Updater<Coupon> updater);

	public Coupon deleteById(Long id);
	
}