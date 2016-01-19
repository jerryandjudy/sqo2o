package com.jspgou.cms.dao;

import java.util.List;

import com.jspgou.common.hibernate3.Updater;
import com.jspgou.common.page.Pagination;
import com.jspgou.cms.entity.CustomerService;

/**
* This class should preserve.
* @preserve
*/
public interface CustomerServiceDao {
	
	public Pagination getPagination(Boolean disable,int pageNo, int pageSize);
	public Pagination getPagination(Boolean disable,int pageNo, int pageSize,Long siteId);
	
	public List<CustomerService> getList(Boolean disable);
	
	public CustomerService findById(Long id);
	
	public CustomerService updateByUpdater(Updater<CustomerService> updater);
	
	public CustomerService deleteById(Long id);
	
	public CustomerService save(CustomerService bean);
}

