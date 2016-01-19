package com.jspgou.cms.entity;

import com.jspgou.cms.entity.base.BaseProductTypeProperty;


/**
* This class should preserve.
* @preserve
*/
public class ProductTypeProperty extends BaseProductTypeProperty {
	private static final long serialVersionUID = 1L;

/*[CONSTRUCTOR MARKER BEGIN]*/
	public ProductTypeProperty () {
		super();
	}

	/**
	 * Constructor for primary key
	 */
	public ProductTypeProperty (java.lang.Long id) {
		super(id);
	}

	/**
	 * Constructor for required fields
	 */
	public ProductTypeProperty (
		java.lang.Long id,
		com.jspgou.cms.entity.ProductType propertyType,
		java.lang.String propertyName,
		java.lang.String field,
		java.lang.Integer isStart,
		java.lang.Integer isNotNull,
		java.lang.Integer sort,
		java.lang.Integer dataType,
		java.lang.Boolean single,
		java.lang.Boolean category,
		java.lang.Boolean custom) {

		super (
			id,
			propertyType,
			propertyName,
			field,
			isStart,
			isNotNull,
			sort,
			dataType,
			single,
			category,
			custom);
	}

/*[CONSTRUCTOR MARKER END]*/


}