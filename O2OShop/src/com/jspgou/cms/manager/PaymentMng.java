package com.jspgou.cms.manager;

import java.util.List;
import java.util.Map;

import com.jspgou.cms.entity.Payment;
/**
* This class should preserve.
* @preserve
*/
public interface PaymentMng {
	public List<Payment> getListForCart(Long webId);
	
	
	public Payment[] updatePriority(Long[] ids, Integer[] priority);

	public List<Payment> getList(Long webId, boolean all);

	public List<Payment> getByCode(String code, Long webId);

	public Payment findById(Long id);

	public Payment save(Payment bean);

	public Payment update(Payment bean);

	public Payment deleteById(Long id);

	public Payment[] deleteByIds(Long[] ids);
	
	public void addShipping(Payment Payment,long shippingIds[]);
	
	public void updateShipping(Payment Payment,long shippingIds[]);
}