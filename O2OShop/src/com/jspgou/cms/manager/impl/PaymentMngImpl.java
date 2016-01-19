package com.jspgou.cms.manager.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jspgou.common.hibernate3.Updater;
import com.jspgou.cms.dao.PaymentDao;
import com.jspgou.cms.entity.Payment;
import com.jspgou.cms.manager.PaymentMng;
import com.jspgou.cms.manager.ShippingMng;
/**
* This class should preserve.
* @preserve
*/
@Service
@Transactional
public class PaymentMngImpl implements PaymentMng {
	public List<Payment> getListForCart(Long webId) {
		return paymentDao.getListForCart(webId, true);
	}

	@Transactional(readOnly = true)
	public List<Payment> getList(Long webId, boolean all) {
		return paymentDao.getList(webId, all);
	}
	
	public Payment[] updatePriority(Long[] ids, Integer[] priority) {
		Payment[] beans = new Payment[ids.length];
		for (int i = 0, len = ids.length; i < len; i++) {
			beans[i] = findById(ids[i]);
			beans[i].setPriority(priority[i]);
		}
		return beans;
	}

	@Transactional(readOnly = true)
	public List<Payment> getByCode(String code, Long webId) {
		return paymentDao.getByCode(code, webId);
	}

	@Transactional(readOnly = true)
	public Payment findById(Long id) {
		Payment entity = paymentDao.findById(id);
		return entity;
	}

	public Payment save(Payment bean) {
		paymentDao.save(bean);
		return bean;
	}

	public Payment update(Payment bean) {
		Updater<Payment> updater = new Updater<Payment>(bean);
		Payment entity = paymentDao.updateByUpdater(updater);
		return entity;
	}

	public Payment deleteById(Long id) {
		Payment bean = paymentDao.deleteById(id);
		return bean;
	}

	public Payment[] deleteByIds(Long[] ids) {
		Payment[] beans = new Payment[ids.length];
		for (int i = 0, len = ids.length; i < len; i++) {
			beans[i] = deleteById(ids[i]);
		}
		return beans;
	}
	
	
	
	
	
	  public void addShipping(Payment Payment,long shippingIds[]){
	    	if (shippingIds != null) {
				for (long shippingId : shippingIds) {
					Payment.addToShippings(shippingMng.findById(shippingId));
				}
			}
	        
	    }
	
	  public void updateShipping(Payment Payment,long shippingIds[]){
		  Payment.getShippings().clear();
	    	if (shippingIds != null) {
				for (long shippingId : shippingIds) {
					Payment.addToShippings(shippingMng.findById(shippingId));
				}
			}
	        
	    }
	
	
	
	
	  @Autowired
	private PaymentDao paymentDao;
	
	@Autowired
	private ShippingMng shippingMng;
}