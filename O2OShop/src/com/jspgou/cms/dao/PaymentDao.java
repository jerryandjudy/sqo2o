package com.jspgou.cms.dao;

import java.util.List;

import com.jspgou.common.hibernate3.Updater;
import com.jspgou.cms.entity.Payment;

/**
 * 支付方式实体类Payment的DAO接口
 * 
 * @author liufang
 * @preserve
 */
public interface PaymentDao {
	/**
	 * 获得支付方式列表，供购物车使用。
	 * 
	 * 被禁用的支付方式将不显示。
	 * 
	 * @param webId
	 *            站点ID
	 * @param cacheable
	 *            是否使用查询缓存
	 * @return 支付方式列表
	 */
	public List<Payment> getListForCart(Long webId, boolean cacheable);

	public List<Payment> getList(Long webId, boolean all);

	public List<Payment> getByCode(String code, Long webId);

	public Payment findById(Long id);

	public Payment save(Payment bean);

	public Payment updateByUpdater(Updater<Payment> updater);

	public Payment deleteById(Long id);
}