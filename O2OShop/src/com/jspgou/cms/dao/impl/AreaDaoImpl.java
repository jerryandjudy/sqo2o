/**
* 吉林省艾利特信息技术有限公司
* O2OShop系统
* @date 2015年6月10日
* @author liuwang
* @version 1.0
*/
package com.jspgou.cms.dao.impl;

import org.springframework.stereotype.Repository;

import com.jspgou.cms.dao.AreaDao;
import com.jspgou.cms.entity.Area;
import com.jspgou.common.hibernate3.HibernateBaseDao;
@Repository
public class AreaDaoImpl extends
HibernateBaseDao<Area, Long> implements AreaDao {

	@Override
	public Area findByImei(String imei) {
		// TODO Auto-generated method stub
		return findUniqueByProperty("imei", imei);
	}

	@Override
	public Area save(Area bean) {
		// TODO Auto-generated method stub
		getSession().save(bean);
		return bean;
	}


	@Override
	protected Class<Area> getEntityClass() {
		// TODO Auto-generated method stub
		return Area.class;
	}

}
