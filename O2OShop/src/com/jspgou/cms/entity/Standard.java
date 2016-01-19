package com.jspgou.cms.entity;

import java.util.HashSet;
import java.util.Set;

import com.jspgou.cms.entity.base.BaseStandard;


/**
* This class should preserve.
* @preserve
*/
public class Standard extends BaseStandard {
	private static final long serialVersionUID = 1L;

/*[CONSTRUCTOR MARKER BEGIN]*/
	public Standard () {
		super();
	}

	/**
	 * Constructor for primary key
	 */
	public Standard (java.lang.Long id) {
		super(id);
	}

	/**
	 * Constructor for required fields
	 */
	public Standard (
		java.lang.Long id,
		com.jspgou.cms.entity.StandardType type,
		java.lang.String name) {

		super (
			id,
			type,
			name);
	}
	
	
	public void addToProductFashions(ProductFashion productFashion) {
		Set<ProductFashion> set = getFashions();
		if (set == null) {
			set = new HashSet<ProductFashion>();
			setFashions(set);
		}
		set.add(productFashion);
		
	}

/*[CONSTRUCTOR MARKER END]*/


}