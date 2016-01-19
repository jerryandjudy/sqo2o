package com.jspgou.cms.manager.impl;

import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jspgou.cms.dao.GysDao;
import com.jspgou.cms.dao.KhgxDao;
import com.jspgou.cms.dao.LsfcDao;
import com.jspgou.cms.dao.OrganizationDao;
import com.jspgou.cms.entity.Gys;
import com.jspgou.cms.entity.Khgx;
import com.jspgou.cms.entity.Lsfc;
import com.jspgou.cms.entity.Order;
import com.jspgou.cms.manager.GysMng;
import com.jspgou.cms.manager.KhgxMng;
import com.jspgou.cms.manager.LsfcMng;
import com.jspgou.common.hibernate3.Updater;
import com.jspgou.common.page.Pagination;
import com.jspgou.core.dao.AccountDao;
import com.jspgou.core.dao.WebsiteDao;
import com.jspgou.core.entity.Account;
import com.jspgou.core.entity.Website;
/**
* This class should preserve.
* @preserve
*/
@Service
@Transactional
public class KhgxMngImpl implements KhgxMng {

	@Autowired
	public KhgxDao khgxDao;
	@Transactional(readOnly = true)
	@Override
	public Khgx findById(Long id) {
		// TODO Auto-generated method stub
		return khgxDao.findById(id);
	}

	@Override
	public Khgx save(Khgx bean) {
		// TODO Auto-generated method stub
		return khgxDao.save(bean);
	}

	@Override
	public Khgx updateByUpdater(Khgx bean) {
		Updater<Khgx> updater = new Updater<Khgx>(bean);
		return khgxDao.updateByUpdater(updater);
	}
	@Override
	public List getListByGysAndBldId(Gys gys,String[] ids) {
		return khgxDao.getListByGysAndBldId(gys, ids);
	}

	@Override
	public Khgx deleteById(Long id) {
		// TODO Auto-generated method stub
		return khgxDao.deleteById(id);
	}
	@Transactional(readOnly = true)
	@Override
	public Pagination getPage(Khgx khgx, int pageNo, int pageSize) {
		// TODO Auto-generated method stub
		return khgxDao.getPage(khgx, pageNo, pageSize);
	}
	
}