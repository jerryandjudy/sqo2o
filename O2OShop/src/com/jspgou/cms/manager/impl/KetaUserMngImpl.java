package com.jspgou.cms.manager.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jspgou.cms.dao.KetaUserDao;
import com.jspgou.cms.dao.LsfcDao;
import com.jspgou.cms.dao.OrganizationDao;
import com.jspgou.cms.entity.Dls;
import com.jspgou.cms.entity.KetaUser;
import com.jspgou.cms.entity.Lsfc;
import com.jspgou.cms.manager.KetaUserMng;
import com.jspgou.cms.manager.LsfcMng;
import com.jspgou.common.hibernate3.Updater;
import com.jspgou.common.page.Pagination;
/**
* This class should preserve.
* @preserve
*/
@Service
@Transactional
public class KetaUserMngImpl implements KetaUserMng {

	@Override
	@Transactional(readOnly = true)
	public List<KetaUser> getAllList() {
		// TODO Auto-generated method stub
		return ketaUserDao.getAllList();
	}

	@Override
	@Transactional(readOnly = true)
	public KetaUser findById(Long id) {
		// TODO Auto-generated method stub
		return ketaUserDao.findById(id);
	}

	@Override
	public KetaUser save(KetaUser bean) {
		// TODO Auto-generated method stub
		return ketaUserDao.save(bean);
	}

	@Override
	public KetaUser updateByUpdater(KetaUser bean) {
		// TODO Auto-generated method stub
		Updater<KetaUser> updater = new Updater<KetaUser>(bean);
		return ketaUserDao.updateByUpdater(updater);
	}

	@Override
	public KetaUser deleteById(Long id) {
		// TODO Auto-generated method stub
		return ketaUserDao.deleteById(id);
	}
	
	@Autowired
	public KetaUserDao ketaUserDao;

	@Override
	@Transactional(readOnly = true)
	public KetaUser findByUserName(String username) {
		// TODO Auto-generated method stub
		return ketaUserDao.findByUserName(username);
	}
	
}