package com.jspgou.core.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.jspgou.core.dao.RoleDao;
import com.jspgou.core.entity.Role;
import com.jspgou.common.hibernate3.HibernateBaseDao;
/**
* This class should preserve.
* @preserve
*/
@Repository
public class RoleDaoImpl extends HibernateBaseDao<Role, Integer>
		implements RoleDao {
	@SuppressWarnings("unchecked")
	public List<Role> getList() {
		String hql = "from Role bean order by bean.priority asc";
		return find(hql);
	}
	@SuppressWarnings("unchecked")
	public List<Role> getListByType(Integer roleType) {
		String hql = "from Role bean where 1=1";
		if(roleType!=null && roleType>0){
			hql=hql+" and bean.roleType="+roleType;
		}
		hql=hql+"  order by bean.priority asc";
		return find(hql);
	}

	public Role findById(Integer id) {
		Role entity = get(id);
		return entity;
	}

	public Role save(Role bean) {
		getSession().save(bean);
		return bean;
	}

	public Role deleteById(Integer id) {
		Role entity = super.get(id);
		if (entity != null) {
			getSession().delete(entity);
		}
		return entity;
	}

	@Override
	protected Class<Role> getEntityClass() {
		return Role.class;
	}
}