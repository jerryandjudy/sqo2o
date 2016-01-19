package com.jspgou.core.dao.impl;

import com.jspgou.common.hibernate3.HibernateBaseDao;
import com.jspgou.core.dao.AdminDao;
import com.jspgou.core.entity.Admin;
import org.springframework.stereotype.Repository;
/**
* This class should preserve.
* @preserve
*/
@Repository
public class AdminDaoImpl extends HibernateBaseDao<Admin, Long> 
         implements AdminDao{
    public Admin getByUserId(Long userId, Long webId){
        String s = "from Admin bean where bean.user.id=:userId and bean.website.id=:webId";
        return (Admin)getSession().createQuery(s).setParameter("userId", userId).setParameter("webId", webId).uniqueResult();
    }
    public Admin getByUserId(Long userId){
    	String s = "from Admin bean where bean.user.id=:userId ";
    	return (Admin)getSession().createQuery(s).setParameter("userId", userId).uniqueResult();
    }

    public Admin findById(Long id){
        Admin entity = get(id);
        return entity;
    }

    public Admin save(Admin bean){
        this.getSession().save(bean);
        return bean;
    }

    public Admin deleteById(Long id){
        Admin entity =super.get(id);
        if(entity != null){
            getSession().delete(entity);
        }
        return entity;
    }
    
	@Override
	protected Class<Admin> getEntityClass() {
		return Admin.class;
	}
}
