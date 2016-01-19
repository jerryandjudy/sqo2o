package com.jspgou.cms.dao.impl;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.jspgou.cms.dao.FwsDao;
import com.jspgou.cms.dao.OrderItemDao;
import com.jspgou.cms.dao.ShopOrderTypeDao;
import com.jspgou.cms.entity.Fws;
import com.jspgou.cms.entity.Gys;
import com.jspgou.cms.entity.Logistics;
import com.jspgou.cms.entity.OrderItem;
import com.jspgou.cms.entity.ShopOrderType;
import com.jspgou.cms.manager.AddressMng;
import com.jspgou.common.hibernate3.Finder;
import com.jspgou.common.hibernate3.HibernateBaseDao;
import com.jspgou.common.hibernate3.Updater;
import com.jspgou.common.page.Pagination;
import com.jspgou.core.entity.Website;

/**
 * This class should preserve.
 * 
 * @preserve
 */
@Repository
public class FwsDaoImpl extends HibernateBaseDao<Fws, String> implements FwsDao {

	@Override
	public List<Fws> getAllList() {
		// TODO Auto-generated method stub
		Criteria crit = createCriteria();
		crit.addOrder(Order.desc(Fws.PROP_CREATETIME));
		List<Fws> list = crit.list();
		return list;
	}

	@Override
	public List<Fws> getFwsBySiteAndPsqy(Long siteId, Long province, Long city,
			Long country, Long street) {
		// TODO Auto-generated method stub
		Finder f = Finder
				.create("select bean.bld from Psqy bean where  bean.bld is not null and bean.bld.isDisabled=false  ");
		if (siteId != null) {
			f.append(" and bean.bld.website.id=" + siteId);
		}
		if (province != null) {
			f.append(" and bean.province.id=" + province);
		}
		if (city != null) {
			f.append(" and bean.city.id=" + city);
		}
		if (country != null) {
			f.append(" and bean.country.id=" + country);
		}
		if (street != null) {
			f.append(" and bean.street.id=" + street);
		}
		f.append(" order by bean.id desc");
		return find(f);
	}

	
	@Override
	public Fws findById(String id) {
		// TODO Auto-generated method stub
		Fws entity = get(id);
		return entity;
	}

	@Override
	public Fws save(Fws bean) {
		// TODO Auto-generated method stub
		getSession().save(bean);
		return bean;
	}

	@Override
	public Fws deleteById(String id) {
		// TODO Auto-generated method stub
		Fws bean = findById(id);
		getSession().delete(bean);
		return bean;
	}

	@Override
	public Pagination getPageByIsDisabled(Boolean isDisabled, int pageNo,
			int pageSize) {
		// TODO Auto-generated method stub
		Finder f = Finder.create("from Fws bean where 1=1");
		if (isDisabled != null) {
			f.append(" and bean.isDisabled=" + isDisabled);
		}
		f.append(" order by bean.id desc");
		return find(f, pageNo, pageSize);
	}
	@Override
	public Pagination getPageByIsDisabled(String organizationName,Long siteId,String username,String companyName,Boolean isDisabled, int pageNo,
			int pageSize) {
		// TODO Auto-generated method stub
		Finder f = Finder.create("from Fws bean where 1=1");
		if (isDisabled != null) {
			f.append(" and bean.isDisabled=" + isDisabled);
		}
		if(username!=null){
			f.append(" and bean.ketaUser.realname like'%"+username+"%'");
		}
		if(organizationName!=null && organizationName.length()>0){
			f.append(" and bean.ketaUser.organization.name='"+organizationName+"'");
		}
		if(siteId!=null){
			f.append(" and bean.website.id="+siteId);
		}
		if(companyName!=null){
			f.append(" and bean.companyName like'%"+companyName+"%'");
		}
		f.append(" order by bean.createTime desc");
		return find(f, pageNo, pageSize);
	}
	@Override
	public Fws findByIsDef(Boolean isDef,Long siteId){
		// TODO Auto-generated method stub
//		Finder f = Finder.create(");
		StringBuilder f=new StringBuilder("from Fws bean where 1=1");
		if (isDef != null) {
			f.append(" and bean.isDef=" + isDef);
		}
		f.append(" order by bean.id desc");
		return (Fws) findUnique(f.toString());
	}
	@Override
	@Transactional(readOnly = true)
	public Fws findByKetaUserId(Long id) {
		// TODO Auto-generated method stub
		String hql="from Fws bean where bean.ketaUser.id=?";
		return (Fws) this.findUnique(hql, id);
	}
	@Override
	public Pagination getPageBySiteId(Long siteId, int pageNo, int pageSize) {
		// TODO Auto-generated method stub
		Finder f = Finder.create("from Fws bean where 1=1");
		if (siteId != null) {
			f.append(" and bean.website.id=" + siteId);
		}
		f.append(" and bean.isDisabled=" + false);
		f.append(" order by bean.id desc");
		return find(f, pageNo, pageSize);
	}

	@Override
	protected Class<Fws> getEntityClass() {
		// TODO Auto-generated method stub
		return Fws.class;
	}

	

	
	
	@Autowired
	private OrderItemDao orderItemDao;





}