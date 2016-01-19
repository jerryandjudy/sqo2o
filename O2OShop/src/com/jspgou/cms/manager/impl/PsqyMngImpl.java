package com.jspgou.cms.manager.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jspgou.cms.dao.PsqyDao;
import com.jspgou.cms.entity.Bld;
import com.jspgou.cms.entity.Gys;
import com.jspgou.cms.entity.Psqy;
import com.jspgou.cms.manager.PsqyMng;
import com.jspgou.common.page.Pagination;
/**
* This class should preserve.
* @preserve
*/
@Service
@Transactional
public class PsqyMngImpl implements PsqyMng {

	@Autowired
	private PsqyDao psqyDao;
	@Transactional(readOnly = true)
	@Override
	public Pagination getGysPage(int pageNo, int pageSize) {
		// TODO Auto-generated method stub
		return psqyDao.getGysPage(pageNo, pageSize);
	}
	@Transactional(readOnly = true)
	@Override
	public Pagination getGysPage(Long siteId,String username,String companyName,int pageNo, int pageSize) {
		// TODO Auto-generated method stub
		return psqyDao.getGysPage(siteId,username,companyName,pageNo, pageSize);
	}
	@Transactional(readOnly = true)
	@Override
	public Pagination getBldPage(int pageNo, int pageSize) {
		// TODO Auto-generated method stub
		return psqyDao.getBldPage(pageNo, pageSize);
	}
	@Transactional(readOnly = true)
	@Override
	public Pagination getBldPage(Long siteId,String username,String companyName,int pageNo, int pageSize) {
		// TODO Auto-generated method stub
		return psqyDao.getBldPage(siteId, username, companyName,pageNo, pageSize);
	}
	@Transactional(readOnly = true)
	@Override
	public List<Psqy> getAllList() {
		// TODO Auto-generated method stub
		return psqyDao.getAllList();
	}
	@Transactional(readOnly = true)
	@Override
	public Psqy findById(Long id) {
		// TODO Auto-generated method stub
		return psqyDao.findById(id);
	}
	@Override
	public Psqy save(Psqy bean) {
		// TODO Auto-generated method stub
		return psqyDao.save(bean);
	}
	@Transactional(readOnly = true)
	@Override
	public Pagination getGysPageByGys(Gys gys, String username,
			String companyName, int pageNo, int pageSize) {
		// TODO Auto-generated method stub
		return psqyDao.getGysPageByGys( gys,  username,
				 companyName,  pageNo,  pageSize);
	}
	@Transactional(readOnly = true)
	@Override
	public Pagination getBldPageByBld(Bld bld, String username,
			String companyName, int pageNo, int pageSize) {
		// TODO Auto-generated method stub
		return psqyDao.getBldPageByBld( bld,  username,
				companyName,  pageNo,  pageSize);
	}
	@Override
	public Psqy deleteById(Long id) {
		// TODO Auto-generated method stub
		return psqyDao.deleteById(id);
	}
	
}