/**
* 吉林省艾利特信息技术有限公司
* O2OShop系统
* @date 2015年7月21日
* @author liuwang
* @version 1.0
*/

package com.jspgou.cms.manager.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jspgou.cms.dao.ZfbWyDao;
import com.jspgou.cms.entity.ZfbWy;
import com.jspgou.cms.manager.ZfbWyMng;
@Service
@Transactional
public class ZfbWyMngImpl implements ZfbWyMng {
@Autowired
public ZfbWyDao zfbWyDao;
	@Override
	public List<ZfbWy> getList() {
		// TODO Auto-generated method stub
		return zfbWyDao.getList();
	}

}
