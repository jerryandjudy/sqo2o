package com.jspgou.cms.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.jspgou.common.hibernate3.HibernateBaseDao;
import com.jspgou.cms.dao.DataBackupDao;
import com.jspgou.cms.entity.DataBackup;

/**
* This class should preserve.
* @preserve
*/
@Repository
public class DataBackupDaoImpl extends HibernateBaseDao<DataBackup, Integer> implements DataBackupDao{

	protected Class<DataBackup> getEntityClass() {
		return DataBackup.class;
	}

	@SuppressWarnings("unchecked")
	public DataBackup getDataBackup() {
		String hql = " from DataBackup";
		List<DataBackup> list = getSession().createQuery(hql).list();
		if(list.size()==0){
			return null;
		}else{
			return list.get(0);
		}
	}

}

