package com.jspgou.cms.dao.impl;

import java.util.Date;
import java.util.List;
import org.springframework.stereotype.Repository;

import com.jspgou.common.hibernate3.Finder;
import com.jspgou.common.hibernate3.HibernateBaseDao;
import com.jspgou.common.page.Pagination;
import com.jspgou.cms.dao.CouponDao;
import com.jspgou.cms.entity.Coupon;

/**
* This class should preserve.
* @preserve
*/
@Repository
public class CouponDaoImpl extends HibernateBaseDao<Coupon, Long> implements
		CouponDao {

	public Pagination getPage(int pageNo, int pageSize){
		Finder f = Finder.create("from Coupon bean");
		f.append(" order by bean.id desc");
		return find(f, pageNo, pageSize);
	} 

	public Pagination getPageByUsing(Date newTime,int pageNo, int pageSize){
		Finder f = Finder.create("from Coupon bean where bean.isusing=true");
		if(newTime!=null){
			f.append(" and bean.couponEndTime>:newTime");
			f.setParam("newTime", newTime);
		}
		f.append(" order by bean.id asc");
		return find(f, pageNo, pageSize);
	} 

	@SuppressWarnings("unchecked")
	public List<Coupon> getList(){
		String hql="from Coupon bean where 1=1";
		return getSession().createQuery(hql).list();
	}
	
	public Coupon findById(Long id) {
		Coupon entity = get(id);
		return entity;
	}
	
	public Coupon save(Coupon bean) {
		getSession().save(bean);
		return bean;
	}
	
	public Coupon deleteById(Long id) {
		Coupon entity = super.get(id);
		if (entity != null) {
			getSession().delete(entity);
		}
		return entity;
	}

	@Override
	protected Class<Coupon> getEntityClass() {
		return Coupon.class;
	}
}