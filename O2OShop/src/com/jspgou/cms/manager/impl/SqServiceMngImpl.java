/**
* 吉林省艾利特信息技术有限公司
* O2OShop系统
* @date 2015年6月5日
* @author liuwang
* @version 1.0
*/
package com.jspgou.cms.manager.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jspgou.cms.dao.SqServiceDao;
import com.jspgou.cms.entity.SqService;
import com.jspgou.cms.manager.SqServiceMng;
import com.jspgou.common.page.Pagination;
@Service
@Transactional
public class SqServiceMngImpl implements SqServiceMng {

	@Override
	public List<SqService> getList(Long ketaUserId,Long siteId,Long ctId, Boolean groom, Boolean status,
			Long provinceId,Long cityId,Long countryId,Long streetId, String name, Integer count) {
		// TODO Auto-generated method stub
		return sqServiceDao.getList(ketaUserId,siteId,ctId, groom, status,  provinceId, cityId, countryId, streetId, name, count);
	}
	
@Autowired
private SqServiceDao sqServiceDao;
@Override
public SqService getById(Long id) {
	// TODO Auto-generated method stub
	return sqServiceDao.getById(id);
}
@Override
public Pagination getPage(Long ketaUserId, Long siteId, Long ctId,
		Boolean groom, Boolean status, Long provinceId, Long cityId,
		Long countryId, Long streetId, String name, int pageNo, int pageSize) {
	// TODO Auto-generated method stub
	return sqServiceDao.getPage(ketaUserId, siteId, ctId, groom, status, provinceId, cityId, countryId, streetId, name, pageNo, pageSize);
}
}
