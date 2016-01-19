/**
* 吉林省艾利特信息技术有限公司
* O2OShop系统
* @date 2015年7月21日
* @author liuwang
* @version 1.0
*/
package com.jspgou.cms.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.jspgou.cms.dao.ZfbWyDao;
import com.jspgou.cms.entity.ZfbWy;
import com.jspgou.common.hibernate3.Finder;
import com.jspgou.common.hibernate3.HibernateBaseDao;
@Repository
public class ZfbWyDaoImpl extends HibernateBaseDao<ZfbWy, Integer> implements ZfbWyDao{

	@Override
	public List<ZfbWy> getList() {
		// TODO Auto-generated method stub
		Finder f = Finder.create("from ZfbWy bean ");
		f.append(" order by bean.priority");
		return find(f);
	}

	@Override
	protected Class<ZfbWy> getEntityClass() {
		// TODO Auto-generated method stub
		return null;
	}

}
