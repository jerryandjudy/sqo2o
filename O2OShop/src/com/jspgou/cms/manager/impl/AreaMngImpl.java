/**
* 吉林省艾利特信息技术有限公司
* O2OShop系统
* @date 2015年6月10日
* @author liuwang
* @version 1.0
*/
package com.jspgou.cms.manager.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jspgou.cms.dao.AreaDao;
import com.jspgou.cms.entity.Area;
import com.jspgou.cms.manager.AreaMng;
import com.jspgou.common.hibernate3.Updater;
@Service
@Transactional
public class AreaMngImpl implements AreaMng {

	@Override
	public Area findByImei(String imei) {
		// TODO Auto-generated method stub
		return areaDao.findByImei(imei);
	}

	@Override
	public Area save(Area bean) {
		// TODO Auto-generated method stub
		return areaDao.save(bean);
	}

	@Override
	public Area updateByUpdater(Updater<Area> updater) {
		// TODO Auto-generated method stub
		return areaDao.updateByUpdater(updater);
	}
	@Autowired
    private AreaDao areaDao;
}
