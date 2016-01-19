package com.jspgou.cms.entity;

import com.jspgou.cms.entity.base.BaseShipments;


/**
* This class should preserve.
* @preserve
*/
public class Shipments extends BaseShipments {
	private static final long serialVersionUID = 1L;

/*[CONSTRUCTOR MARKER BEGIN]*/
	public Shipments () {
		super();
	}

	/**
	 * Constructor for primary key
	 */
	public Shipments (java.lang.Long id) {
		super(id);
	}

	/**
	 * Constructor for required fields
	 */
	public Shipments (
		java.lang.Long id,
		com.jspgou.cms.entity.Order indent,
		com.jspgou.cms.entity.ShopAdmin shopAdmin,
		java.lang.String waybill,
		java.lang.String receiving,
		java.lang.String comment) {

		super (
			id,
			indent,
			shopAdmin,
			waybill,
			receiving,
			comment);
	}

/*[CONSTRUCTOR MARKER END]*/


}