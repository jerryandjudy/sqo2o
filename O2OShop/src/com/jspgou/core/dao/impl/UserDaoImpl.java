package com.jspgou.core.dao.impl;

import org.hibernate.Criteria;
import org.hibernate.criterion.Criterion;
import org.springframework.stereotype.Repository;

import com.jspgou.common.hibernate3.HibernateBaseDao;
import com.jspgou.common.page.Pagination;
import com.jspgou.core.dao.UserDao;
import com.jspgou.core.entity.User;
/**
* This class should preserve.
* @preserve
*/
@Repository
public class UserDaoImpl extends HibernateBaseDao<User, Long>
             implements UserDao{
    public User getByUsername(String username){
        return (User)findUniqueByProperty("username", username);
    }

    public User getByEmail(String email){
        return (User)findUniqueByProperty("email", email);
    }

    public Pagination getPage(int pageNo, int pageSize){
        Criteria criteria = createCriteria(new Criterion[0]);
        Pagination page = findByCriteria(criteria, pageNo,pageSize);
        return page;
    }

    public User findById(Long id){
        User entity =get(id);
        return entity;
    }

    public User save(User bean){
        getSession().save(bean);
        return bean;
    }

    public User deleteById(Long id){
        User entity =super.get(id);
        if(entity != null)
            getSession().delete(entity);
        return entity;
    }

	@Override
	protected Class<User> getEntityClass() {
		return User.class;
	}
}
