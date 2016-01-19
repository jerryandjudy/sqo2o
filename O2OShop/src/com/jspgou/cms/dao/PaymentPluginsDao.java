package com.jspgou.cms.dao;

import java.util.List;

import com.jspgou.cms.entity.PaymentPlugins;
import com.jspgou.common.hibernate3.Updater;

/**
 * @author liufang
 * This class should preserve.
 * @preserve
 */
public interface PaymentPluginsDao {
	
	public List<PaymentPlugins> getList();
	
	public PaymentPlugins findByCode(String code);

	public PaymentPlugins findById(Long id);

	public PaymentPlugins save(PaymentPlugins bean);

	public PaymentPlugins updateByUpdater(Updater<PaymentPlugins> updater);

	public PaymentPlugins deleteById(Long id);
}