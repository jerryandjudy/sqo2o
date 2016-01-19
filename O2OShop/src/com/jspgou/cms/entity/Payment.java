package com.jspgou.cms.entity;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import org.apache.commons.lang.StringUtils;
import org.json.JSONException;
import org.json.JSONObject;

import com.jspgou.cms.entity.base.BasePayment;
import com.jspgou.core.entity.Role;

/**
* This class should preserve.
* @preserve
*/
public class Payment extends BasePayment {
	
	
	public void addToShippings(Shipping shipping) {
		if (shipping == null) {
			return;
		}
		Set<Shipping> set = getShippings();
		if (set == null) {
			set = new HashSet<Shipping>();
			setShippings(set);
		}
		set.add(shipping);
	}
	
	  public Long[] getShippingIds() {
			Set<Shipping> shippings = getShippings();
			return Shipping.fetchIds(shippings);
		}
	
	

	/* [CONSTRUCTOR MARKER BEGIN] */
	public Payment() {
		super();
	}

	/**
	 * Constructor for primary key
	 */
	public Payment(java.lang.Long id) {
		super(id);
	}

	/**
	 * Constructor for required fields
	 */
	public Payment(java.lang.Long id, com.jspgou.core.entity.Website website,
			java.lang.String name,java.lang.Integer priority, java.lang.Boolean disabled) {

		super(id, website, name, priority, disabled);
	}

	/* [CONSTRUCTOR MARKER END] */

}