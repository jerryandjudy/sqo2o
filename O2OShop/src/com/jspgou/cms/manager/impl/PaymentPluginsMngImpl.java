package com.jspgou.cms.manager.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jspgou.common.hibernate3.Updater;
import com.jspgou.cms.dao.PaymentPluginsDao;
import com.jspgou.cms.entity.PaymentPlugins;
import com.jspgou.cms.manager.PaymentPluginsMng;
/**
* This class should preserve.
* @preserve
*/
@Service
@Transactional
public class PaymentPluginsMngImpl implements PaymentPluginsMng {

	@Transactional(readOnly = true)
	public List<PaymentPlugins> getList() {
		return paymentPluginsDao.getList();
	}
	
	public PaymentPlugins[] updatePriority(Long[] ids, Integer[] priority) {
		PaymentPlugins[] beans = new PaymentPlugins[ids.length];
		for (int i = 0, len = ids.length; i < len; i++) {
			beans[i] = findById(ids[i]);
			beans[i].setPriority(priority[i]);
		}
		return beans;
	}

	public PaymentPlugins findByCode(String code){
		return paymentPluginsDao.findByCode(code);
	}
	
	@Transactional(readOnly = true)
	public PaymentPlugins findById(Long id) {
		PaymentPlugins entity = paymentPluginsDao.findById(id);
		return entity;
	}

	public PaymentPlugins save(PaymentPlugins bean) {
		paymentPluginsDao.save(bean);
		return bean;
	}

	public PaymentPlugins update(PaymentPlugins bean) {
		Updater<PaymentPlugins> updater = new Updater<PaymentPlugins>(bean);
		PaymentPlugins entity = paymentPluginsDao.updateByUpdater(updater);
		return entity;
	}

	public PaymentPlugins deleteById(Long id) {
		PaymentPlugins bean = paymentPluginsDao.deleteById(id);
		return bean;
	}

	public PaymentPlugins[] deleteByIds(Long[] ids) {
		PaymentPlugins[] beans = new PaymentPlugins[ids.length];
		for (int i = 0, len = ids.length; i < len; i++) {
			beans[i] = deleteById(ids[i]);
		}
		return beans;
	}
	@Autowired
	private PaymentPluginsDao paymentPluginsDao;
	
}