package com.jspgou.cms.entity;

import com.jspgou.cms.entity.base.BaseProductStandard;


/**
* This class should preserve.
* @preserve
*/
public class ProductStandard extends BaseProductStandard {
	private static final long serialVersionUID = 1L;

/*[CONSTRUCTOR MARKER BEGIN]*/
	public ProductStandard () {
		super();
	}

	/**
	 * Constructor for primary key
	 */
	public ProductStandard (java.lang.Long id) {
		super(id);
	}

	/**
	 * Constructor for required fields
	 */
	public ProductStandard (
		java.lang.Long id,
		com.jspgou.cms.entity.Product product,
		com.jspgou.cms.entity.Standard standard,
		com.jspgou.cms.entity.StandardType type,
		java.lang.String imgPath,
		boolean dataType) {

		super (
			id,
			product,
			standard,
			type,
			imgPath,
			dataType);
	}

/*[CONSTRUCTOR MARKER END]*/


}