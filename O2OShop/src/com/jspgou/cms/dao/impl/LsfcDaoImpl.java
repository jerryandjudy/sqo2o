package com.jspgou.cms.dao.impl;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.springframework.stereotype.Repository;

import com.jspgou.cms.dao.LsfcDao;
import com.jspgou.cms.entity.Lsfc;
import com.jspgou.common.hibernate3.Finder;
import com.jspgou.common.hibernate3.HibernateBaseDao;
import com.jspgou.common.page.Pagination;
import com.jspgou.core.entity.Account;

/**
* This class should preserve.
* @preserve
*/
@Repository
public class LsfcDaoImpl extends HibernateBaseDao<Lsfc, Long> implements LsfcDao {
	@SuppressWarnings("unchecked")
	public List<Lsfc> getAllList() {
		Criteria crit = createCriteria();
		
		List<Lsfc> list = crit.list();
		return list;
	}
	public Pagination getPageByIsDisabled(Boolean isDisabled, int pageNo, int pageSize) {//会员中心(我的王成订单)
		Finder f = Finder
				.create("from Lsfc bean where 1=1");
		if(isDisabled!=null){
		f.append(" and bean.isDisabled="+isDisabled);
		}
		f.append(" order by bean.id desc");
		return find(f, pageNo, pageSize);
	}
	

	public Lsfc findById(Long id) {
		Lsfc entity = get(id);
		return entity;
	}

	public Lsfc save(Lsfc bean) {
		getSession().save(bean);
		return bean;
	}

	@Override
	protected Class<Lsfc> getEntityClass() {
		return Lsfc.class;
	}

	@Override
	public Lsfc deleteById(Long id) {
		Lsfc entity = super.get(id);
		if (entity != null) {
			getSession().delete(entity);
		}
		return entity;
	}



	@Override
	public Lsfc findByFctypeId(Long fctypeId,  Long websiteId) {
		// TODO Auto-generated method stub
		String s = "from Lsfc bean where bean.fctypeId.id=:fctypeId and bean.website.id=:websiteId and bean.isDisabled="+false;
        return (Lsfc)getSession().createQuery(s).setParameter("websiteId", websiteId).setParameter("fctypeId", fctypeId).uniqueResult();
	}
	
	@Override
	public Lsfc changeIsDisabledById(Long id) {
		// TODO Auto-generated method stub
		Lsfc entity = super.get(id);
		if(entity != null){
			String sql = "update Lsfc set is_disabled=1 where id='"+entity.getId()+"'";
			
			
			Query query=getSession().createQuery(sql);
			query.executeUpdate();
			
		}
		return entity;
	}
	@Override
	public Pagination getPageByIsDisabledAndWebsiteId(Long websiteId,
			Boolean isDisabled, int pageNo, int pageSize) {
		Finder f = Finder
				.create("from Lsfc bean where 1=1");
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