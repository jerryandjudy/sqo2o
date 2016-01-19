package com.jspgou.cms.manager.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jspgou.common.hibernate3.Updater;
import com.jspgou.common.page.Pagination;
import com.jspgou.cms.dao.CustomerServiceDao;
import com.jspgou.cms.entity.CustomerService;
import com.jspgou.cms.manager.CustomerServiceMng;
/**
* This class should preserve.
* @preserve
*/
@Service
@Transactional
public class CustomerServiceMngImpl implements CustomerServiceMng{

	public CustomerService findById(Long id) {
		return customerServiceDao.findById(id);
	}

	public Pagination getPagination(Boolean disable,int pageNo, int pageSize){
		return customerServiceDao.getPagination(disable, pageNo, pageSize);
	}
	public Pagination getPagination(Boolean disable,int pageNo, int pageSize,Long siteId){
		return customerServiceDao.getPagination(disable, pageNo, pageSize,siteId);
	}
	
	public List<CustomerService> getList(){
		return customerServiceDao.getList(false);
	}

	public CustomerService update(CustomerService bean) {
		Updater<CustomerService> updater = new Updater<CustomerService>(bean);
		CustomerService entity = customerServiceDao.updateByUpdater(updater);
		return entity;
	}

	public CustomerService save(CustomerService bean) {
		return customerServiceDao.save(bean);
	}
	
	public CustomerService[] updatePriority(Long[] ids, Integer[] priority) {
		CustomerService[] beans = new CustomerService[ids.length];
		for (int i = 0, len = ids.length; i < len; i++) {
			beans[i] = findById(ids[i]);
			beans[i].setPriority(priority[i]);
		}
		return beans;
	}
	
	public CustomerService[] deleteByIds(Long[] ids) {
		CustomerService[] beans = new CustomerService[ids.length];
		for (int i = 0, len = ids.length; i < len; i++) {
			beans[i] = customerServiceDao.deleteById(ids[i]);
		}
		return beans;
	}
	
	@Autowired
	public CustomerServiceDao customerServiceDao;
}

