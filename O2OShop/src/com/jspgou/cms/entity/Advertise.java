package com.jspgou.cms.entity;

import com.jspgou.cms.entity.base.BaseAdvertise;

/**
* This class should preserve.
* @preserve
*/
public class Advertise extends BaseAdvertise {
	private static final long serialVersionUID = 1L;

	public void init() {
		if (getClickCount() == null) {
			setClickCount(0);
		}
		if (this.getDisplayCount() == null) {
			setDisplayCount(0);
		}
		if (getEnabled() == null) {
			setEnabled(true);
		}
		if (getWeight() == null) {
			setWeight(1);
		}
	}

	
/*[CONSTRUCTOR MARKER BEGIN]*/
	public Advertise () {
		super();
	}

	/**
	 * Constructor for primary key
	 */
	public Advertise (java.lang.Integer id) {
		super(id);
	}

	/**
	 * Constructor for required fields
	 */
	public Advertise (
		java.lang.Integer id,
		com.jspgou.cms.entity.Adspace adspace) {

		super (
			id,
			adspace);
	}

/*[CONSTRUCTOR MARKER END]*/


}