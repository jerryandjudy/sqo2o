package com.jspgou.core.entity;

import com.jspgou.core.entity.base.BaseLog;


/**
* This class should preserve.
* @preserve
*/
public class Log extends BaseLog {
	private static final long serialVersionUID = 1L;

/*[CONSTRUCTOR MARKER BEGIN]*/
	public Log () {
		super();
	}

	/**
	 * Constructor for primary key
	 */
	public Log (java.lang.Long id) {
		super(id);
	}

	/**
	 * Constructor for required fields
	 */
	public Log (
		java.lang.Long id,
		java.lang.Integer category,
		java.util.Date time) {

		super (
			id,
			category,
			time);
	}

/*[CONSTRUCTOR MARKER END]*/


}