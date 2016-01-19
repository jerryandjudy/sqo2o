package com.jspgou.cms.dao;

import java.util.List;
import com.jspgou.cms.entity.OrderItem;
import com.jspgou.common.hibernate3.Updater;
import com.jspgou.common.page.Pagination;

/**
* This class should preserve.
* @preserve
*/
public interface OrderItemDao {
	
	public List<Object[]> profitTop(Long ctgid,Long typeid,Integer pageNo,Integer pageSize);
	
	public Integer totalCount(Long ctgid,Long typeid);
	
	public List<OrderItem> getOrderItemByOrderId(Long orderId);
	public List<Object[]> getOrderItem();
	
	public OrderItem findByMember(Long memberId,Long productId);
	
	public OrderItem findById(Long id);
	
	public OrderItem updateByUpdater(Updater<OrderItem> updater);
	
	public Pagination getPageForMember(Long memberId, Integer status,int pageNo, int pageSize);
	
	public Pagination getPageForProuct(Long productId,int pageNo, int pageSize); 
}

