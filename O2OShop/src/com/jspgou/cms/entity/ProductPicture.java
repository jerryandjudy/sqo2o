package com.jspgou.cms.entity;

import com.jspgou.cms.entity.base.BaseProductPicture;


/**
* This class should preserve.
* @preserve
*/
public class ProductPicture extends BaseProductPicture {
	private static final long serialVersionUID = 1L;

/*[CONSTRUCTOR MARKER BEGIN]*/
	public ProductPicture () {
		super();
	}

	/**
	 * Constructor for required fields
	 */
	public ProductPicture (
		java.lang.String picturePath,
		java.lang.String bigPicturePath,
		java.lang.String appPicturePath) {

		super (
			picturePath,
			bigPicturePath,
			appPicturePath);
	}

/*[CONSTRUCTOR MARKER END]*/


}