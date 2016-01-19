package com.jspgou.cms.dao;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import com.jspgou.common.hibernate3.Updater;
import com.jspgou.cms.entity.MemberCoupon;

/**
* This class should preserve.
* @preserve
*/
public interface MemberCouponDao {
	public MemberCoupon findByCoupon(Long memberId,Long couponId);
	
	public MemberCoupon findById(Long id);

	public MemberCoupon save(MemberCoupon bean);

	public MemberCoupon updateByUpdater(Updater<MemberCoupon> updater);

	public MemberCoupon deleteById(Long id);
	
	public List<MemberCoupon> getList(Long memberId,Date newTime,BigDecimal price);
	
	public List<MemberCoupon> getList(Long memberId);
	
	public MemberCoupon update(MemberCoupon bean);
}