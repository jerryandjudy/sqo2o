package com.jspgou.cms.entity;

import java.util.Collection;

import com.jspgou.cms.entity.base.BaseShipping;
import com.jspgou.core.entity.Role;
/**
* This class should preserve.
* @preserve
*/
public class Shipping extends BaseShipping {
	private static final long serialVersionUID = 1L;
	private Double price;
	/**
	 * 统一计价
	 */
	public static final int UNIFORM = 1;
	/**
	 * 按重量计价
	 */
	public static final int BY_WEIGHT = 2;
	/**
	 * 按国家计费(作废)
	 */
	public static final int BY_COUNTRY = 3;

	/**
	 * 计算运费，并保存在price属性中。
	 * 
	 * @param countryId
	 * @param weight
	 * @param count
	 * @return 运费
	 */
	public Double calPrice(Double weight) {
		Double p;
		int m = getMethod();
		if (m == 1) {
			p = getUniformPrice();
		} else if (m == 2) {
			// 重量为0则免运费
			if (weight <= 0) {
				p =0.0;
			} else {
				p = byWeight(weight);
			}
		} else {
			throw new RuntimeException("Shipping method not supported: " + m);
		}
		return p;
	}
	
	
	public static Long[] fetchIds(Collection<Shipping> shippings) {
		if (shippings == null) {
			return null;
		}
		Long[] ids = new Long[shippings.size()];
		int i = 0;
		for (Shipping s : shippings) {
			ids[i++] = s.getId();
		}
		return ids;
	}

	/**
	 * 按重量计算运费
	 * 
	 * @param weight
	 * @return
	 */
	public Double byWeight(Double weight) {
		Double price = getFirstPrice();
		Double ap = getAdditionalPrice();
		int fw = getFirstWeight();
		int aw = getAdditionalWeight();
		weight -= fw;
		while(weight > 0) {
			weight -= aw;
			price+=ap;
		}
		return price;
	}

	

	/* [CONSTRUCTOR MARKER BEGIN] */
	public Shipping() {
		super();
	}

	/**
	 * Constructor for primary key
	 */
	public Shipping(java.lang.Long id) {
		super(id);
	}

	/**
	 * Constructor for required fields
	 */
	public Shipping(java.lang.Long id, com.jspgou.core.entity.Website website,
			java.lang.String name, java.lang.Integer method,
			java.lang.Integer priority, java.lang.Boolean disabled) {

		super(id, website, name, method, priority, disabled);
	}

	/* [CONSTRUCTOR MARKER END] */

	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}
}