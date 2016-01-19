package com.jspgou.cms.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.jspgou.common.hibernate3.Finder;
import com.jspgou.common.hibernate3.HibernateBaseDao;
import com.jspgou.cms.dao.PaymentPluginsDao;
import com.jspgou.cms.entity.PaymentPlugins;

/**
* This class should preserve.
* @preserve
*/
@Repository
public class PaymentPluginsDaoImpl extends HibernateBaseDao<PaymentPlugins, Long> implements
 PaymentPluginsDao {

	@SuppressWarnings("unchecked")
	public List<PaymentPlugins> getList() {
		Finder f = Finder.create("from PaymentPlugins bean where 1=1");
		f.append(" order by bean.priority");
		return find(f);
	}

	public PaymentPlugins findById(Long id) {
		PaymentPlugins entity = get(id);
		return entity;
	}

	public PaymentPlugins findByCode(String code) {
		return findUniqueByProperty("code", code);
	}

	public PaymentPlugins save(PaymentPlugins bean) {
		getSession().save(bean);
		return bean;
	}

	public PaymentPlugins deleteById(Long id) {
		PaymentPlugins entity = super.get(id);
		if (entity != null) {
			getSession().delete(entity);
		}
		return entity;
	}

	@Override
	protected Class<PaymentPlugins> getEntityClass() {
		return PaymentPlugins.class;
	}
}