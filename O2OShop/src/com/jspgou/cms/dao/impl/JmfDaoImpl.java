package com.jspgou.cms.dao.impl;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import com.jspgou.cms.dao.JmfDao;
import com.jspgou.cms.entity.Jmf;
import com.jspgou.common.hibernate3.Finder;
import com.jspgou.common.hibernate3.HibernateBaseDao;
import com.jspgou.common.hibernate3.Updater;
import com.jspgou.common.page.Pagination;

/**
* This class should preserve.
* @preserve
*/
@Repository
public class JmfDaoImpl extends HibernateBaseDao<Jmf, Long> implements JmfDao {
	@SuppressWarnings("unchecked")
	public List<Jmf> getAllList() {
		Criteria crit = createCriteria();
		crit.add(Restrictions.eq("isDisabled", false));
		List<Jmf> list = crit.list();
		return list;
	}
	public Pagination getPageByIsDisabled(Boolean isDisabled, int pageNo, int pageSize) {//会员中心(我的王成订单)
		Finder f = Finder
				.create("from Jmf bean where 1=1");
		if(isDisabled!=null){
		f.append(" and bean.isDisabled="+isDisabled);
		}
		f.append(" order by bean.id desc");
		return find(f, pageNo, pageSize);
	}
	

	public Jmf findById(Long id) {
		Jmf entity = get(id);
		return entity;
	}

	public Jmf save(Jmf bean) {
		getSession().save(bean);
		return bean;
	}

	@Override
	protected Class<Jmf> getEntityClass() {
		return Jmf.class;
	}

	@Override
	public Jmf deleteById(Long id) {
		Jmf entity = super.get(id);
		if (entity != null) {
			getSession().delete(entity);
		}
		return entity;
	}



	@Override
	public Jmf findByJmftypeId(Long jmftypeId,  Long websiteId) {
		// TODO Auto-generated method stub
		String s = "from Jmf bean where bean.jmftypeId.id=:jmftypeId and bean.website.id=:websiteId and bean.isDisabled="+false;
        return (Jmf)getSession().createQuery(s).setParameter("websiteId", websiteId).setParameter("jmftypeId", jmftypeId).uniqueResult();
	}
	
	@Override
	public Jmf changeIsDisabledById(Long id) {
		// TODO Auto-generated method stub
		Jmf entity =this.findById(id);
		entity.setIsDisabled(true);
		if(entity != null){
			getSession().update(entity);
			
		}
		return entity;
	}
	@Override
	public Pagination getPageByIsDisabledAndWebsiteId(Long websiteId,
			Boolean isDisabled, int pageNo, int pageSize) {
		Finder f = Finder
				.create("from Jmf bean where 1=1");
		if(isDisabled!=null){
		f.append(" and bean.isDisabled="+isDisabled);
		}
		if(websiteId != null){
			f.append(" and bean.website.id="+websiteId);
		}
		f.append(" order by bean.id desc");
		return find(f, pageNo, pageSize);
	}

	

	
	
}