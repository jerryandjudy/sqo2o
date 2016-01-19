package com.jspgou.cms.manager;

import java.util.List;

import com.jspgou.cms.entity.CustomerService;
import com.jspgou.common.page.Pagination;
/**
* This class should preserve.
* @preserve
*/
public interface CustomerServiceMng {
	
	public Pagination getPagination(Boolean disable,int pageNo, int pageSize);
	public Pagination getPagination(Boolean disable,int pageNo, int pageSize,Long siteId);
	
	public List<CustomerService> getList();
	
	public CustomerService findById(Long id);
	
	public CustomerService update(CustomerService bean);
	
	/**
	 * 更新排列顺序
	 * 
	 * @param ids
	 *            待排列ID数组
	 * @param priority
	 *            排列顺序
	 * @return 排序后产品对象数组
	 */
	public CustomerService[] updatePriority(Long[] ids, Integer[] priority);
	
	public CustomerService[] deleteByIds(Long[] ids);
	
	public CustomerService save(CustomerService bean);
}

