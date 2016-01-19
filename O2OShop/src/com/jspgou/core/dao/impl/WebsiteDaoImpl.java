package com.jspgou.core.dao.impl;

import com.jspgou.common.hibernate3.Finder;
import com.jspgou.common.hibernate3.HibernateBaseDao;
import com.jspgou.common.page.Pagination;
import com.jspgou.core.dao.WebsiteDao;
import com.jspgou.core.entity.Website;

import java.util.List;

import org.hibernate.*;
import org.springframework.stereotype.Repository;
/**
* This class should preserve.
* @preserve
*/
@Repository
public class WebsiteDaoImpl extends HibernateBaseDao<Website, Long>
        implements WebsiteDao{
    public Website getUniqueWebsite(){
        String hql= "select bean from Website bean";
        return (Website)getSession().createQuery(hql).uniqueResult();
    }

    public Website findByDomain(String s){
        return (Website)findUniqueByProperty(Website.PROP_DOMAIN, s);
    }
    public Website findByName(String name){
    	return (Website)findUniqueByProperty(Website.PROP_NAME, name);
    }

    public int countWebsite(){
        String hql = "select count(*) from Website";
        return ((Number)getSession().createQuery(hql).iterate().next()).intValue();
    }
    @SuppressWarnings("unchecked")
	public List<Website> getList(boolean cacheable) {
		String hql = "from Website bean where bean.close="+false+" order by bean.id asc";
		return getSession().createQuery(hql).setCacheable(cacheable).list();
	}
    public Pagination getAllPage(int pageNo, int pageSize){
        Criteria crit = createCriteria();
        Pagination page = findByCriteria(crit, pageNo, pageSize);
        return page;
    }

    @SuppressWarnings("unchecked")
	public List<Website> getAllList(){
        Criteria crit = createCriteria();
        List<Website> list = crit.list();
        return list;
    }
    @Override
	public List<Website> getTopList() {
		// TODO Auto-generated method stub
		String hql = "from Website bean where bean.parent.id is null ";
		Finder f=Finder.create(hql);
		return find(f);
	}
    @Override
    public List<Website> getAllNotCloseList() {
    	// TODO Auto-generated method stub
    	String hql = "from Website bean where bean.close="+false;
    	Finder f=Finder.create(hql);
    	return find(f);
    }
    @Override
    public List<Website> getListByParent(Integer parentId) {
    	// TODO Auto-generated method stub
    	String hql = "from Website bean where bean.parent.id ="+parentId+" ";
    	Finder f=Finder.create(hql);
    	return find(f);
    }
    public Website findById(Long id){
        Website entity =get(id);
        Hibernate.initialize(entity);
        return entity;
    }

    public Website save(Website bean){
        getSession().save(bean);
        return bean;
    }

    public Website deleteById(Long id) {
        Website entity =super.get(id);
        if(entity != null){
            getSession().delete(entity);
        }
        return entity;
    }

	@Override
	protected Class<Website> getEntityClass() {
		return Website.class;
	}

	@Override
	public List<Website> getListNotHasDls() {
		// TODO Auto-generated method stub
		String hql = "from Website bean where bean.id not in(select dls.website.id from Dls dls where dls.isDisabled=false ) order by bean.id asc";
		return getSession().createQuery(hql).list();
	}
	@Override
	public List<Website> getListNotHasBld() {
		// TODO Auto-generated method stub
		String hql = "from Website bean where bean.id not in(select bld.website.id from Bld bld where bld.isDisabled=false ) order by bean.id asc";
		return getSession().createQuery(hql).list();
	}
	@Override
	public List<Website> getListNotHasGys() {
		// TODO Auto-generated method stub
		String hql = "from Website bean where bean.id not in(select gys.website.id from Gys gys where gys.isDisabled=false ) order by bean.id asc";
		return getSession().createQuery(hql).list();
	}

	
}
