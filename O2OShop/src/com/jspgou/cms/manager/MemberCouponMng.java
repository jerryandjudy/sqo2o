package com.jspgou.cms.manager;

import java.math.BigDecimal;
import java.util.List;
import com.jspgou.cms.entity.MemberCoupon;

/**
* This class should preserve.
* @preserve
*/
public interface MemberCouponMng {
	public MemberCoupon findByCoupon(Long memberId,Long couponId);
	
	public MemberCoupon findById(Long id);
	
	public List<MemberCoupon> getList(Long memberId,BigDecimal price);
	
	public List<MemberCoupon> getList(Long memberId);

	public MemberCoupon save(MemberCoupon bean);
	
	public MemberCoupon update(MemberCoupon bean);
	
}