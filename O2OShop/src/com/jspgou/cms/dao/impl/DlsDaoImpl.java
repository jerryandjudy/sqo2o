package com.jspgou.cms.dao.impl;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Order;
import org.springframework.stereotype.Repository;

import com.jspgou.cms.dao.DlsDao;
import com.jspgou.cms.dao.ShopOrderTypeDao;
import com.jspgou.cms.entity.Dls;
import com.jspgou.cms.entity.KetaUser;
import com.jspgou.cms.entity.Logistics;
import com.jspgou.cms.entity.ShopOrderType;
import com.jspgou.common.hibernate3.Finder;
import com.jspgou.common.hibernate3.HibernateBaseDao;
import com.jspgou.common.hibernate3.Updater;
import com.jspgou.common.page.Pagination;
import com.jspgou.core.entity.Website;

/**
* This class should preserve.
* @preserve
*/
@Repository
public class DlsDaoImpl extends HibernateBaseDao<Dls, String> implements DlsDao {

	@Override
	public List<Dls> getAllList() {
		// TODO Auto-generated method stub
		Criteria crit = createCriteria();
		crit.addOrder(Order.desc(Dls.PROP_CREATETIME));
		List<Dls> list = crit.list();
		return list;
	}

	@Override
	public Dls findById(String id) {
		// TODO Auto-generated method stub
		Dls entity = get(id);
		return entity;
	}

	@Override
	public Dls save(Dls bean) {
		// TODO Auto-generated method stub
		getSession().save(bean);
		return bean;
	}


	@Override
	public Dls deleteById(String id) {
		// TODO Auto-generated method stub
		Dls bean = findById(id);
		getSession().delete(bean);
		return bean;
	}

	@Override
	public Pagination getPageByIsDisabled(Boolean isDisabled, int pageNo,
			int pageSize) {
		// TODO Auto-generated method stub
		Finder f = Finder
				.create("from Dls bean where 1=1");
		if(isDisabled!=null){
		f.append(" and bean.isDisabled="+isDisabled);
		}
		f.append(" order by bean.id desc");
		return find(f, pageNo, pageSize);
	}

	@Override
	protected Class<Dls> getEntityClass() {
		// TODO Auto-generated method stub
		return Dls.class;
	}

	@Override
	public Dls findByKetaUserId(String id) {
		// TODO Auto-generated method stub
		//Dls bean = findByProperty(KetaUser.id, id);
		Finder f = Finder
				.create("from Dls bean where bean.ketaUser.id='"+id+"'");
		
		
		return (Dls)find(f).get(0);
	}

	

	
	
}