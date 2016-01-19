package com.jspgou.cms.manager.impl;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jspgou.cms.dao.OrderReturnDao;
import com.jspgou.cms.entity.Order;
import com.jspgou.cms.entity.OrderReturn;
import com.jspgou.cms.entity.OrderReturn.OrderReturnStatus;
import com.jspgou.cms.manager.OrderReturnMng;
import com.jspgou.cms.manager.ShopDictionaryMng;
import com.jspgou.common.hibernate3.Updater;
import com.jspgou.common.page.Pagination;
/**
* This class should preserve.
* @preserve
*/
@Service
@Transactional
public class OrderReturnMngImpl implements OrderReturnMng {
	@Transactional(readOnly = true)
	public Pagination getPage(Integer status,int pageNo, int pageSize) {
		Pagination page = orderReturnDao.getPage(status,pageNo, pageSize);
		return page;
	}

	@Transactional(readOnly = true)
	public OrderReturn findById(Long id) {
		OrderReturn entity = orderReturnDao.findById(id);
		return entity;
	}
	
	public OrderReturn findByOrderId(Long orderId){
		List<OrderReturn> list=orderReturnDao.findByOrderId(orderId);
		if(list.size()>0){
			return list.get(0);
		}else{
			return null;
		}
	}

	public OrderReturn save(OrderReturn bean) {
		orderReturnDao.save(bean);
		return bean;
	}
	
	public OrderReturn save(OrderReturn bean,Order order, Long reasonId, Boolean delivery, String[] picPaths, String[] picDescs){
		bean.setOrder(order);
		bean.setShopDictionary(shopDictionaryMng.findById(reasonId));
//		if(delivery){
			bean.setReturnType(OrderReturnStatus.SELLER_DELIVERY_RETURN.getValue());
//		}else{
//			bean.setReturnType(OrderReturnStatus.SELLER_DELIVERY_RETURN.getValue());
//		} 
//		Long date=new Date().getTime()+order.getId();
		SimpleDateFormat df = new SimpleDateFormat("yyMMdd");
		String code=df.format(new Date())+order.getId();
		bean.setCode(code);
		bean.setStatus(1);
		bean.setPayType(Integer.parseInt(order.getPayment().getId().toString()));
		bean.setAlipayId("");
		bean.setCreateTime(new Date());
		// 保存图片集
		if (picPaths != null && picPaths.length > 0) {
			for (int i = 0, len = picPaths.length; i < len; i++) {
				if (!StringUtils.isBlank(picPaths[i])) {
					bean.addToPictures(picPaths[i], picDescs[i]);
				}
			}
		}
		bean=orderReturnDao.save(bean);
		return bean;
	}

	public OrderReturn update(OrderReturn bean) {
		Updater<OrderReturn> updater = new Updater<OrderReturn>(bean);
		OrderReturn entity = orderReturnDao.updateByUpdater(updater);
		return entity;
	}

	public OrderReturn deleteById(Long id) {
		OrderReturn bean = orderReturnDao.deleteById(id);
		return bean;
	}
	
	public OrderReturn[] deleteByIds(Long[] ids) {
		OrderReturn[] beans = new OrderReturn[ids.length];
		for (int i = 0,len = ids.length; i < len; i++) {
			beans[i] = deleteById(ids[i]);
		}
		return beans;
	}
	@Autowired
	private OrderReturnDao orderReturnDao;
	@Autowired
	private ShopDictionaryMng shopDictionaryMng;

	
}