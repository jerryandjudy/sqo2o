package com.jspgou.cms.entity.base;

import java.io.Serializable;


/**
 * This is an object that contains data related to the jc_shop_product_ext table.
 * Do not modify this class because it will be overwritten if the configuration file
 * related to this class is modified.
 *
 * @hibernate.class
 *  table="jc_shop_product_ext"
 * This class should preserve.
 * @preserve
 */

public abstract class BaseProductExt  implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public static String REF = "ProductExt";
	public static String PROP_SECKILLPRICE = "seckillprice";
	public static String PROP_DESCRIPTION = "description";
	public static String PROP_PRODUCT = "product";
	public static String PROP_DETAIL_IMG = "detailImg";
	public static String PROP_MTITLE = "mtitle";
	public static String PROP_MIN_IMG = "minImg";
	public static String PROP_MDESCRIPTION = "mdescription";
	public static String PROP_LIST_IMG = "listImg";
	public static String PROP_UNIT = "unit";
	public static String PROP_PRODUCT_IMG_DESC = "productImgDesc";
	public static String PROP_PRODUCT_IMGS = "productImgs";
	public static String PROP_TIME_LIMIT = "timeLimit";
	public static String PROP_WEIGHT = "weight";
	public static String PROP_PRODUCT_PROPERTY = "productProperty";
	public static String PROP_ID = "id";
	public static String PROP_ISLIMIT_TIME = "islimitTime";
	public static String PROP_MKEYWORDS = "mkeywords";


	// constructors
	public BaseProductExt () {
		initialize();
	}

	/**
	 * Constructor for primary key
	 */
	public BaseProductExt (java.lang.Long id) {
		this.setId(id);
		initialize();
	}

	/**
	 * Constructor for required fields
	 */
	public BaseProductExt (
		java.lang.Long id,
		java.lang.Integer weight,
		java.lang.String unit) {

		this.setId(id);
		this.setWeight(weight);
		this.setUnit(unit);
		initialize();
	}

	protected void initialize () {}



	private int hashCode = Integer.MIN_VALUE;

	// primary key
	private java.lang.Long id;

	// fields
	private java.lang.Long code;
	private java.lang.String mtitle;
	private java.lang.String mkeywords;
	private java.lang.String mdescription;
	private java.lang.String detailImg;
	private java.lang.String listImg;
	private java.lang.String minImg;
	private java.lang.String coverImg;
	private java.lang.Integer weight;
	private java.lang.String unit;
	private java.lang.Boolean islimitTime;
	private java.util.Date timeLimit;
	private java.lang.Double seckillprice;
	private java.lang.String productImgs;
	private java.lang.String productImgDesc;

	// one to one
	private com.jspgou.cms.entity.Product product;



	/**
	 * Return the unique identifier of this class
     * @hibernate.id
     *  generator-class="foreign"
     *  column="product_id"
     */
	public java.lang.Long getId () {
		return id;
	}

	/**
	 * Set the unique identifier of this class
	 * @param id the new ID
	 */
	public void setId (java.lang.Long id) {
		this.id = id;
		this.hashCode = Integer.MIN_VALUE;
	}
	
	/**
	 * Return the value associated with the column: code
	 */
	public java.lang.Long getCode () {
		return code;
	}

	/**
	 * Set the value related to the column: code
	 * @param code the code value
	 */
	public void setCode (java.lang.Long code) {
		this.code = code;
	}


	/**
	 * Return the value associated with the column: mtitle
	 */
	public java.lang.String getMtitle () {
		return mtitle;
	}

	/**
	 * Set the value related to the column: mtitle
	 * @param mtitle the mtitle value
	 */
	public void setMtitle (java.lang.String mtitle) {
		this.mtitle = mtitle;
	}


	/**
	 * Return the value associated with the column: mkeywords
	 */
	public java.lang.String getMkeywords () {
		return mkeywords;
	}

	/**
	 * Set the value related to the column: mkeywords
	 * @param mkeywords the mkeywords value
	 */
	public void setMkeywords (java.lang.String mkeywords) {
		this.mkeywords = mkeywords;
	}


	/**
	 * Return the value associated with the column: mdescription
	 */
	public java.lang.String getMdescription () {
		return mdescription;
	}

	/**
	 * Set the value related to the column: mdescription
	 * @param mdescription the mdescription value
	 */
	public void setMdescription (java.lang.String mdescription) {
		this.mdescription = mdescription;
	}


	/**
	 * Return the value associated with the column: detail_img
	 */
	public java.lang.String getDetailImg () {
		return detailImg;
	}

	/**
	 * Set the value related to the column: detail_img
	 * @param detailImg the detail_img value
	 */
	public void setDetailImg (java.lang.String detailImg) {
		this.detailImg = detailImg;
	}


	/**
	 * Return the value associated with the column: list_img
	 */
	public java.lang.String getListImg () {
		return listImg;
	}

	/**
	 * Set the value related to the column: list_img
	 * @param listImg the list_img value
	 */
	public void setListImg (java.lang.String listImg) {
		this.listImg = listImg;
	}


	/**
	 * Return the value associated with the column: min_img
	 */
	public java.lang.String getMinImg () {
		return minImg;
	}

	/**
	 * Set the value related to the column: min_img
	 * @param minImg the min_img value
	 */
	public void setMinImg (java.lang.String minImg) {
		this.minImg = minImg;
	}
	
	/**
	 * Return the value associated with the column: cover_img
	 */
	public java.lang.String getCoverImg () {
		return coverImg;
	}

	/**
	 * Set the value related to the column: cover_img
	 * @param minImg the cover_img value
	 */
	public void setCoverImg (java.lang.String coverImg) {
		this.coverImg = coverImg;
	}


	/**
	 * Return the value associated with the column: weight
	 */
	public java.lang.Integer getWeight () {
		return weight;
	}

	/**
	 * Set the value related to the column: weight
	 * @param weight the weight value
	 */
	public void setWeight (java.lang.Integer weight) {
		this.weight = weight;
	}


	/**
	 * Return the value associated with the column: unit
	 */
	public java.lang.String getUnit () {
		return unit;
	}

	/**
	 * Set the value related to the column: unit
	 * @param unit the unit value
	 */
	public void setUnit (java.lang.String unit) {
		this.unit = unit;
	}


	/**
	 * Return the value associated with the column: is_limitTime
	 */
	public java.lang.Boolean getIslimitTime () {
		return islimitTime;
	}

	/**
	 * Set the value related to the column: is_limitTime
	 * @param islimitTime the is_limitTime value
	 */
	public void setIslimitTime (java.lang.Boolean islimitTime) {
		this.islimitTime = islimitTime;
	}


	/**
	 * Return the value associated with the column: limitTime
	 */
	public java.util.Date getTimeLimit () {
		return timeLimit;
	}

	/**
	 * Set the value related to the column: limitTime
	 * @param timeLimit the limitTime value
	 */
	public void setTimeLimit (java.util.Date timeLimit) {
		this.timeLimit = timeLimit;
	}


	/**
	 * Return the value associated with the column: seckillprice
	 */
	public java.lang.Double getSeckillprice () {
		return seckillprice;
	}

	/**
	 * Set the value related to the column: seckillprice
	 * @param seckillprice the seckillprice value
	 */
	public void setSeckillprice (java.lang.Double seckillprice) {
		this.seckillprice = seckillprice;
	}


	/**
	 * Return the value associated with the column: product_imgs
	 */
	public java.lang.String getProductImgs () {
		return productImgs;
	}

	/**
	 * Set the value related to the column: product_imgs
	 * @param productImgs the product_imgs value
	 */
	public void setProductImgs (java.lang.String productImgs) {
		this.productImgs = productImgs;
	}


	/**
	 * Return the value associated with the column: product_img_desc
	 */
	public java.lang.String getProductImgDesc () {
		return productImgDesc;
	}

	/**
	 * Set the value related to the column: product_img_desc
	 * @param productImgDesc the product_img_desc value
	 */
	public void setProductImgDesc (java.lang.String productImgDesc) {
		this.productImgDesc = productImgDesc;
	}


	/**
	 * Return the value associated with the column: product
	 */
	public com.jspgou.cms.entity.Product getProduct () {
		return product;
	}

	/**
	 * Set the value related to the column: product
	 * @param product the product value
	 */
	public void setProduct (com.jspgou.cms.entity.Product product) {
		this.product = product;
	}



	public boolean equals (Object obj) {
		if (null == obj) return false;
		if (!(obj instanceof com.jspgou.cms.entity.ProductExt)) return false;
		else {
			com.jspgou.cms.entity.ProductExt productExt = (com.jspgou.cms.entity.ProductExt) obj;
			if (null == this.getId() || null == productExt.getId()) return false;
			else return (this.getId().equals(productExt.getId()));
		}
	}

	public int hashCode () {
		if (Integer.MIN_VALUE == this.hashCode) {
			if (null == this.getId()) return super.hashCode();
			else {
				String hashStr = this.getClass().getName() + ":" + this.getId().hashCode();
				this.hashCode = hashStr.hashCode();
			}
		}
		return this.hashCode;
	}


	public String toString () {
		return super.toString();
	}


}