package com.jspgou.core.dao.impl;

import com.jspgou.common.hibernate3.HibernateBaseDao;
import com.jspgou.common.page.Pagination;
import com.jspgou.core.dao.MemberDao;
import com.jspgou.core.entity.Member;
import org.hibernate.criterion.Criterion;
import org.springframework.stereotype.Repository;
/**
* This class should preserve.
* @preserve
*/
@Repository
public class MemberDaoImpl extends HibernateBaseDao<Member, Long>
    implements MemberDao{
	
    public Member getByUserId(Long webId, Long userId){
        String s = "from Member bean where bean.website.id=? and bean.user.id=?";
        return (Member)findUnique(s, new Object[] {
        		webId, userId
        });
    }
    public Member getByUserId(Long userId){
    	String s = "from Member bean where  bean.user.id=?";
    	return (Member)findUnique(s, new Object[] {
    			 userId
    	});
    }
    
    public Member getByUserIdAndActive(String activationCode, Long userId){
        String s = "from Member bean where bean.activationCode=? and bean.user.id=?";
        return (Member)findUnique(s, new Object[] {
        		activationCode, userId
        });
    }

    public Pagination getPage(int pageNo, int pageSize){
        org.hibernate.Criteria criteria = createCriteria(new Criterion[0]);
        Pagination pagination = findByCriteria(criteria, pageNo, pageSize);
        return pagination;
    }

    public Member findById(Long id){
        Member entity =get(id);
        return entity;
    }

    public Member save(Member bean){
        getSession().save(bean);
        return bean;
    }

    public Member deleteById(Long id){
        Member entity =super.get(id);
        if(entity != null)
            getSession().delete(entity);
        return entity;
    }
    
	@Override
	protected Class<Member> getEntityClass() {
		return Member.class;
	}
}
