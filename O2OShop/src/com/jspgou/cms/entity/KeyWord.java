package com.jspgou.cms.entity;

import com.jspgou.cms.entity.base.BaseKeyWord;


/**
* This class should preserve.
* @preserve
*/
public class KeyWord extends BaseKeyWord {
	private static final long serialVersionUID = 1L;

/*[CONSTRUCTOR MARKER BEGIN]*/
	public KeyWord () {
		super();
	}

	/**
	 * Constructor for primary key
	 */
	public KeyWord (java.lang.Integer id) {
		super(id);
	}

	/**
	 * Constructor for required fields
	 */
	public KeyWord (
		java.lang.Integer id,
		java.lang.String keyword,
		java.lang.Integer times) {

		super (
			id,
			keyword,
			times);
	}

/*[CONSTRUCTOR MARKER END]*/


}