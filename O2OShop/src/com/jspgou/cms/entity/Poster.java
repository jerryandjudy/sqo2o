package com.jspgou.cms.entity;

import com.jspgou.cms.entity.base.BasePoster;


/**
* This class should preserve.
* @preserve
*/
public class Poster extends BasePoster {
	private static final long serialVersionUID = 1L;

/*[CONSTRUCTOR MARKER BEGIN]*/
	public Poster () {
		super();
	}

	/**
	 * Constructor for primary key
	 */
	public Poster (java.lang.Integer id) {
		super(id);
	}

	/**
	 * Constructor for required fields
	 */
	public Poster (
		java.lang.Integer id,
		java.lang.String picUrl) {

		super (
			id,
			picUrl);
	}

/*[CONSTRUCTOR MARKER END]*/


}