package com.jspgou.cms.manager;

import com.jspgou.common.page.Pagination;
import com.jspgou.cms.entity.Order;
import com.jspgou.cms.entity.OrderReturn;
/**
* This class should preserve.
* @preserve
*/
public interface OrderReturnMng {
	public Pagination getPage(Integer status,int pageNo, int pageSize);

	public OrderReturn findById(Long id);
	
	public OrderReturn findByOrderId(Long orderId);

	public OrderReturn save(OrderReturn bean);
	
	public OrderReturn save(OrderReturn bean,Order order, Long reasonId, Boolean delivery, String[] picPaths, String[] picDescs);

	public OrderReturn update(OrderReturn bean);

	public OrderReturn deleteById(Long id);
	
	public OrderReturn[] deleteByIds(Long[] ids);
}