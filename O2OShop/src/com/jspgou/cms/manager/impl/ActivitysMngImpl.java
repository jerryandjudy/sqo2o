/**
* 吉林省艾利特信息技术有限公司
* O2OShop系统
* @date 2015年8月4日
* @author liuwang
* @version 1.0
*/
package com.jspgou.cms.manager.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jspgou.cms.dao.ActivitysDao;
import com.jspgou.cms.entity.Activitys;
import com.jspgou.cms.manager.ActivitysMng;
import com.jspgou.common.page.Pagination;
@Service
@Transactional
public class ActivitysMngImpl implements ActivitysMng {

	@Override
	public Activitys save(Activitys bean) {
		// TODO Auto-generated method stub
		return activitysDao.save(bean);
	}
	@Override
	public Activitys update(Activitys bean) {
		// TODO Auto-generated method stub
		return activitysDao.update(bean);
	}

	@Override
	public Activitys delete(Activitys bean) {
		// TODO Auto-generated method stub
		return activitysDao.delete(bean);
	}

	@Override
	public Activitys findById(Long id) {
		// TODO Auto-generated method stub
		return activitysDao.findById(id);
	}
@Autowired
private ActivitysDao activitysDao;
@Override
public Pagination getPage(Long siteId, Boolean isusing, String name,
		int pageNo, int pageSize) {
	// TODO Auto-generated method stub
	return activitysDao.getPage(siteId, isusing, name, pageNo, pageSize);
}
}
