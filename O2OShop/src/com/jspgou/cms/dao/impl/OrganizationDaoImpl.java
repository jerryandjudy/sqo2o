package com.jspgou.cms.dao.impl;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Order;
import org.springframework.stereotype.Repository;

import com.jspgou.cms.dao.OrganizationDao;
import com.jspgou.cms.entity.KetaUser;
import com.jspgou.cms.entity.Organization;
import com.jspgou.common.hibernate3.Finder;
import com.jspgou.common.hibernate3.HibernateBaseDao;
import com.jspgou.common.page.Pagination;

/**
* This class should preserve.
* @preserve
*/
@Repository
public class OrganizationDaoImpl extends HibernateBaseDao<Organization, Long> implements OrganizationDao {

	@Override
	public List<Organization> getAllList() {
		// TODO Auto-generated method stub
		Criteria crit = createCriteria();
		crit.addOrder(Order.desc(Organization.PROP_PRIORITY));
		List<Organization> list = crit.list();
		return list;
	}

	@Override
	public Organization findById(Long id) {
		// TODO Auto-generated method stub
		Organization entity = get(id);
		return entity;
	}
	@Override
	public Organization findByName(String name) {
		// TODO Auto-generated method stub
		String s = "from Organization bean where bean.name=:name";
        return (Organization)getSession().createQuery(s).setParameter("name", name).uniqueResult();
	}

	@Override
	public Organization save(Organization bean) {
		// TODO Auto-generated method stub
		getSession().save(bean);
		return bean;
	}


	@Override
	public Organization deleteById(Long id) {
		// TODO Auto-generated method stub
		Organization bean = findById(id);
		getSession().delete(bean);
		return bean;
	}

	@Override
	public Pagination getPageByIsDisabled(Boolean isDisabled, int pageNo,
			int pageSize) {
		// TODO Auto-generated method stub
		Finder f = Finder
				.create("from Organization bean where 1=1");
		if(isDisabled!=null){
		f.append(" and bean.isDisabled="+isDisabled);
		}
		f.append(" order by bean.id desc");
		return find(f, pageNo, pageSize);
	}

	@Override
	protected Class<Organization> getEntityClass() {
		// TODO Auto-generated method stub
		return Organization.class;
	}

	

	
	
}