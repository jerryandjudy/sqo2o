package com.jspgou.cms.dao;

import java.util.List;

import com.jspgou.cms.entity.OrderReturn;
import com.jspgou.common.hibernate3.Updater;
import com.jspgou.common.page.Pagination;

/**
* This class should preserve.
* @preserve
*/
public interface OrderReturnDao {
	public Pagination getPage(Integer status,int pageNo, int pageSize);

	public OrderReturn findById(Long id);

	public OrderReturn save(OrderReturn bean);
	
	public List<OrderReturn> findByOrderId(Long orderId);

	public OrderReturn updateByUpdater(Updater<OrderReturn> updater);

	public OrderReturn deleteById(Long id);
}