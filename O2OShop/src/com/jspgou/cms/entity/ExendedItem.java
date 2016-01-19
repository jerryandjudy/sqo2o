package com.jspgou.cms.entity;

import com.jspgou.cms.entity.base.BaseExendedItem;


/**
* This class should preserve.
* @preserve
*/
public class ExendedItem extends BaseExendedItem {
	private static final long serialVersionUID = 1L;

/*[CONSTRUCTOR MARKER BEGIN]*/
	public ExendedItem () {
		super();
	}

	/**
	 * Constructor for primary key
	 */
	public ExendedItem (java.lang.Long id) {
		super(id);
	}

	/**
	 * Constructor for required fields
	 */
	public ExendedItem (
		java.lang.Long id,
		com.jspgou.cms.entity.Exended exended,
		java.lang.String name) {

		super (
			id,
			exended,
			name);
	}

/*[CONSTRUCTOR MARKER END]*/


}