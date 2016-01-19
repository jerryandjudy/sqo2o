package com.jspgou.cms.entity;

import com.jspgou.cms.entity.base.BaseDataBackup;


/**
* This class should preserve.
* @preserve
*/
public class DataBackup extends BaseDataBackup {
	private static final long serialVersionUID = 1L;

/*[CONSTRUCTOR MARKER BEGIN]*/
	public DataBackup () {
		super();
	}

	/**
	 * Constructor for primary key
	 */
	public DataBackup (java.lang.Integer id) {
		super(id);
	}

	/**
	 * Constructor for required fields
	 */
	public DataBackup (
		java.lang.Integer id,
		java.lang.String dataBasePath,
		java.lang.String address,
		java.lang.String dataBaseName,
		java.lang.String username,
		java.lang.String password) {

		super (
			id,
			dataBasePath,
			address,
			dataBaseName,
			username,
			password);
	}

/*[CONSTRUCTOR MARKER END]*/


}