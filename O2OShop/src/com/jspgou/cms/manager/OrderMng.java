package com.jspgou.cms.manager;

import java.io.InputStream;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import com.jspgou.cms.entity.Cart;
import com.jspgou.cms.entity.Order;
import com.jspgou.cms.entity.OrderItem;
import com.jspgou.cms.entity.ShopMember;
import com.jspgou.common.page.Pagination;
/**
* This class should preserve.
* @preserve
*/
public interface OrderMng {

	public void  updateSaleCount(Long id);
	
	public void  updateliRun(Long id);
	
	public List<Order> getlist();
	
	public void abolishOrder();
	
	public Pagination getPageForOrderReturn(Long memberId, int pageNo, int pageSize);
	//生成订单
	public Order createOrder(Cart cart,Long[] cartItemId, Long shippingMethodId,Long deliveryInfo,Long paymentMethodId,
			String comments, String ip,ShopMember member, Long webId,String memberCouponId);

	public Pagination getPageForMember(Long memberId, int pageNo, int pageSize);
	
	public Pagination getPageForMember2(Long memberId, int pageNo, int pageSize);
	
	public Pagination getPageForMember1(Long memberId, int pageNo, int pageSize);

	public Pagination getPageForMember3(Long memberId, int pageNo, int pageSize) ;
	
	public Pagination getPage(Long webId,Long memberId,String productName,String userName,Long paymentId,Long shippingId, 
			Date startTime,Date endTime,Double startOrderTotal,Double endOrderTotal,Integer status,Integer paymentStatus,Integer shippingStatus,Long code,int pageNo, int pageSize);
	
	public Pagination getPage(Integer ordeRType,Long webId,Long memberId,String productName,String userName,Long paymentId,Long shippingId, 
			Date startTime,Date endTime,Double startOrderTotal,Double endOrderTotal,Integer status,Integer paymentStatus,Integer shippingStatus,Long code,int pageNo, int pageSize);
	
	public Pagination getPage(Long webId,Long memberId,String productName,String userName,Long paymentId,Long shippingId, 
			Date startTime,Date endTime,Double startOrderTotal,Double endOrderTotal,Integer status,Long code,int pageNo, int pageSize);


	public Order findById(Long id);
	public Order findByCode(Long code);
	public Order findByTradeNo(String tradeNo);

	public List<OrderItem> cfdd(Order order);
	public Order save(Order bean);
	
	public Order updateByUpdater(Order updater);

	public Order deleteById(Long id);

	public Order[] deleteByIds(Long[] ids);
	
	public  List<Object> getTotlaOrder(Long webId);
	
	public BigDecimal getMemberMoneyByYear(Long memberId);
	
	public Pagination getOrderByReturn(Long memberId,Integer pageNo,Integer pageSize);

	public InputStream exportExcel(Integer ordeRType,Long webId,Long memberId,String productName,String userName,Long paymentId,Long shippingId, 
			Date startTime,Date endTime,Double startOrderTotal,Double endOrderTotal,Integer status,Integer paymentStatus,Integer shippingStatus,Long code) throws Exception;
	
}