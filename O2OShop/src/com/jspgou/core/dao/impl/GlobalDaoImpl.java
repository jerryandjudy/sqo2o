package com.jspgou.core.dao.impl;

import com.jspgou.common.hibernate3.HibernateBaseDao;
import com.jspgou.core.dao.GlobalDao;
import com.jspgou.core.entity.Global;
import org.springframework.stereotype.Repository;
/**
* This class should preserve.
* @preserve
*/
@Repository
public class GlobalDaoImpl extends HibernateBaseDao<Global, Long>
    implements GlobalDao{
    public Global findById(Long id){
        Global global =get(id);
        return global;
    }

    public Global update(Global bean){
        getSession().update(bean);
        return bean;
    }

	@Override
	protected Class<Global> getEntityClass() {
		return Global.class;
	}
}
