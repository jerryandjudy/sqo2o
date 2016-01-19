package com.jspgou.cms.manager;

import java.util.List;

import com.jspgou.cms.entity.Coupon;
import com.jspgou.common.page.Pagination;

/**
* This class should preserve.
* @preserve
*/
public interface CouponMng {
	public Pagination getPage(int pageNo, int pageSize);
	
	public Pagination getPageByUsing(int pageNo, int pageSize);
	
	public List<Coupon> getList();
	
	public Coupon findById(Long id);
	
	public Coupon save(Coupon bean,Long webId);
	
	public Coupon update(Coupon bean);
	
	public Coupon deleteById(Long id,String url);
	
	public Coupon[] deleteByIds(Long[] ids,String url);
	
}