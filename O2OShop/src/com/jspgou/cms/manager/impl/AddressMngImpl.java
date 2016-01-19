package com.jspgou.cms.manager.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jspgou.common.hibernate3.Updater;
import com.jspgou.common.page.Pagination;
import com.jspgou.cms.dao.AddressDao;
import com.jspgou.cms.entity.Address;
import com.jspgou.cms.manager.AddressMng;
/**
* This class should preserve.
* @preserve
*/
@Service
@Transactional
public class AddressMngImpl implements AddressMng {
	public List<Address> getListById(Long parentId){
		return addressDao.getListById(parentId);
	}
	public List<Address> getListBySiteId(Long SiteId){
		return addressDao.getListBySiteId(SiteId);
	}
	
	@Transactional(readOnly = true)
	public Pagination getPage(int pageNo, int pageSize) {
		Pagination page = addressDao.getPage(pageNo, pageSize);
		return page;
	}
	
	public Pagination getPageByParentId(Long parentId,int pageNo, int pageSize){
		Pagination page = addressDao.getPageByParentId(parentId,pageNo, pageSize);
		return page;
	}
	public Pagination getPageByParentIdAndSiteId(Long siteId,Long parentId,int pageNo, int pageSize){
		Pagination page = addressDao.getPageByParentIdAndSiteId(siteId,parentId,pageNo, pageSize);
		return page;
	}

	@Transactional(readOnly = true)
	public Address findById(Long id) {
		Address entity = addressDao.findById(id);
		return entity;
	}

	public Address save(Address bean) {
		addressDao.save(bean);
		return bean;
	}

	public Address update(Address bean) {
		Updater<Address> updater = new Updater<Address>(bean);
		Address entity = addressDao.updateByUpdater(updater);
		return entity;
	}

	public Address deleteById(Long id) {
		Address bean = addressDao.deleteById(id);
		return bean;
	}
	
	public Address[] deleteByIds(Long[] ids) {
		Address[] beans = new Address[ids.length];
		for (int i = 0,len = ids.length; i < len; i++) {
			beans[i] = deleteById(ids[i]);
		}
		return beans;
	}
	
	public Address[] updatePriority(Long[] ids, Integer[] priority) {
		Address[] beans = new Address[ids.length];
		for (int i = 0, len = ids.length; i < len; i++) {
			beans[i] = findById(ids[i]);
			beans[i].setPriority(priority[i]);
		}
		return beans;
	}
	@Autowired
	private AddressDao addressDao;
	@Transactional(readOnly = true)
	@Override
	public List<Address> getListByIdNotGys(Long parentId, String gysId) {
		// TODO Auto-generated method stub
		return addressDao.getListByIdNotGys(parentId, gysId);
	}
	@Transactional(readOnly = true)
	@Override
	public List<Address> getListByIdNotBld(Long parentId, String bldId) {
		// TODO Auto-generated method stub
		return addressDao.getListByIdNotBld(parentId, bldId);
	}

	
}