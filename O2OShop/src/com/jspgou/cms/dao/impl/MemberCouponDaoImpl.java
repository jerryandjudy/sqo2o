package com.jspgou.cms.dao.impl;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import org.hibernate.Query;
import org.springframework.stereotype.Repository;

import com.jspgou.common.hibernate3.Finder;
import com.jspgou.common.hibernate3.HibernateBaseDao;
import com.jspgou.cms.dao.MemberCouponDao;
import com.jspgou.cms.entity.MemberCoupon;

/**
* This class should preserve.
* @preserve
*/
@Repository
public class MemberCouponDaoImpl extends HibernateBaseDao<MemberCoupon, Long> implements
		MemberCouponDao {

	public MemberCoupon findByCoupon(Long memberId,Long couponId) {
		String hql = "from MemberCoupon bean where bean.member.id=? and bean.coupon.id=?";
		Query query = getSession().createQuery(hql);
		query.setParameter(0, memberId).setParameter(1, couponId);
		// 做一些容错处理，因为毕竟没有在数据库中限定path是唯一的。
		query.setMaxResults(1);
		return (MemberCoupon) query.setCacheable(true).uniqueResult();
	}
	
	@SuppressWarnings("unchecked")
	public List<MemberCoupon> getList(Long memberId,Date newTime,BigDecimal price) {
		Finder f = Finder.create("select bean from MemberCoupon bean where bean.isuse=false");
		if(memberId!=null){
			f.append(" and bean.member.id=:id");
			f.setParam("id", memberId);
		}
		if(newTime!=null){
			f.append(" and bean.coupon.isusing=true");
			f.append(" and bean.coupon.couponEndTime>:newTime");
			f.append(" and bean.coupon.couponTime<:newTime");
			f.setParam("newTime", newTime);
		}
		if(price!=null){
			f.append(" and bean.coupon.leastPrice<=:price");
			f.setParam("price", price);
		}
		return find(f);
	}
	
	@SuppressWarnings("unchecked")
	public List<MemberCoupon> getList(Long memberId){
		String hql="from MemberCoupon bean where bean.member.id=:id";
		return getSession().createQuery(hql).setParameter("id", memberId).list();
		
	}

	public MemberCoupon findById(Long id) {
		MemberCoupon entity = get(id);
		return entity;
	}
	
	public MemberCoupon update(MemberCoupon bean){
		this.getSession().update(bean);
		return bean;
	}

	public MemberCoupon save(MemberCoupon bean) {
		getSession().save(bean);
		return bean;
	}

	public MemberCoupon deleteById(Long id) {
		MemberCoupon entity = super.get(id);
		if (entity != null) {
			getSession().delete(entity);
		}
		return entity;
	}

	@Override
	protected Class<MemberCoupon> getEntityClass() {
		return MemberCoupon.class;
	}
}