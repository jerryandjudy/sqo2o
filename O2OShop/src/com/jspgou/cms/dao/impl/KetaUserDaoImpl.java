package com.jspgou.cms.dao.impl;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Order;
import org.springframework.stereotype.Repository;

import com.jspgou.cms.dao.KetaUserDao;
import com.jspgou.cms.entity.Jmf;
import com.jspgou.cms.entity.KetaUser;
import com.jspgou.common.hibernate3.HibernateBaseDao;

/**
* This class should preserve.
* @preserve
*/
@Repository
public class KetaUserDaoImpl extends HibernateBaseDao<KetaUser, Long> implements KetaUserDao {

	@Override
	public List<KetaUser> getAllList() {
		// TODO Auto-generated method stub
		Criteria crit = createCriteria();
		crit.addOrder(Order.desc(KetaUser.PROP_CREATETIME));
		List<KetaUser> list = crit.list();
		return list;
	}

	@Override
	public KetaUser findById(Long id) {
		// TODO Auto-generated method stub
		KetaUser entity = get(id);
		return entity;
	}

	@Override
	public KetaUser save(KetaUser bean) {
		// TODO Auto-generated method stub
		getSession().save(bean);
		return bean;
	}


	@Override
	public KetaUser deleteById(Long id) {
		// TODO Auto-generated method stub
		KetaUser bean = findById(id);
		getSession().delete(bean);
		return bean;
	}


	@Override
	protected Class<KetaUser> getEntityClass() {
		// TODO Auto-generated method stub
		return KetaUser.class;
	}

	@Override
	public KetaUser findByUserName(String username) {
				String s = "from KetaUser bean where bean.username=:username";
		        return (KetaUser)getSession().createQuery(s).setParameter("username", username).uniqueResult();
	}


	

	
	
}