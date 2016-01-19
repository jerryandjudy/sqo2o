package com.jspgou.cms.entity;

import java.sql.Timestamp;
import java.util.Date;
import java.util.LinkedHashSet;
import java.util.Set;

import com.jspgou.cms.entity.base.BaseOrder;
/**
* This class should preserve.
* @preserve
*/
public class Order extends BaseOrder {
	private static final long serialVersionUID = 1L;
	
	/**
	 * 计算重量
	 * 
	 * @return
	 */
	public int calWeight() {
		int weight = 0;
		for (OrderItem item : getItems()) {
			weight += item.getProductSite().getProduct().getWeight();
		}
		return weight;
	}

	/**
	 * 获得购物车中商品总重量，免运费的商品不记重量。
	 * 
	 * @return
	 */
	public Double getWeightForFreight() {
		Double weight = 0.0;
		for (OrderItem item : getItems()) {
			weight += item.getWeightForFreight();
		}
		return weight;
	}

	/**
	 * 获得购物车中商品总数量，免运费的商品不计数量。
	 * 
	 * @return
	 */
	public int getCountForFreight() {
		int count = 0;
		for (OrderItem item : getItems()) {
			count += item.getCountForFreigt();
		}
		return count;
	}

	public void addToItems(OrderItem item) {
		Set<OrderItem> items = getItems();
		if (items == null) {
			items = new LinkedHashSet<OrderItem>();
			setItems(items);
		}
		item.setOrdeR(this);
		items.add(item);
	}

	public void init() {
		Date now = new Timestamp(System.currentTimeMillis());
		if (getCreateTime() == null) {
			setCreateTime(now);
		}
		if (getLastModified() == null) {
			setLastModified(now);
		}
	}

	/* [CONSTRUCTOR MARKER BEGIN] */
	public Order () {
		super();
	}

	/**
	 * Constructor for primary key
	 */
	public Order (java.lang.Long id) {
		super(id);
	}

	/**
	 * Constructor for required fields
	 */
	public Order (
		java.lang.Long id,
		com.jspgou.core.entity.Website website,
		com.jspgou.cms.entity.ShopMember member,
		com.jspgou.cms.entity.Payment payment,
		com.jspgou.cms.entity.Shipping shipping,
		com.jspgou.cms.entity.Shipping shopDirectory,
		java.lang.Long code,
		java.lang.String ip,
		java.util.Date createTime,
		java.util.Date lastModified,
		java.lang.Double total,
		java.lang.Integer score,
		java.lang.Double weight,
		java.lang.Integer status) {

		super (
			id,
			website,
			member,
			payment,
			shipping,
			shopDirectory,
			code,
			ip,
			createTime,
			lastModified,
			total,
			score,
			weight);
	}

	/* [CONSTRUCTOR MARKER END] */

}