package com.jspgou.cms.manager;

import java.util.List;

import com.jspgou.cms.entity.PaymentPlugins;
/**
* This class should preserve.
* @preserve
*/
public interface PaymentPluginsMng {

	public PaymentPlugins[] updatePriority(Long[] ids, Integer[] priority);

	public List<PaymentPlugins> getList();

	public PaymentPlugins findById(Long id);
	
	public PaymentPlugins findByCode(String code);

	public PaymentPlugins save(PaymentPlugins bean);

	public PaymentPlugins update(PaymentPlugins bean);

	public PaymentPlugins deleteById(Long id);

	public PaymentPlugins[] deleteByIds(Long[] ids);
	
}