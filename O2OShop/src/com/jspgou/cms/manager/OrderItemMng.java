package com.jspgou.cms.manager;

import java.util.List;

import com.jspgou.common.page.Pagination;
import com.jspgou.cms.entity.OrderItem;
/**
* This class should preserve.
* @preserve
*/
public interface OrderItemMng {
	
	public List<Object[]> profitTop(Long ctgid,Long typeid,Integer pageNo,Integer pageSize);
	
	public Integer totalCount(Long ctgid,Long typeid);
	
	public List<Object[]> getOrderItem();
	
	public Pagination getPageByMember(Integer status,Long memberId,int pageNo,int pageSize);
	
	public OrderItem findByMember(Long memberId,Long productId);
	
	public OrderItem findById(Long id);
	
	public OrderItem updateByUpdater(OrderItem updater);
	
	public Pagination getOrderItem(Integer pageNo,Integer pageSize,Long productId);
}

