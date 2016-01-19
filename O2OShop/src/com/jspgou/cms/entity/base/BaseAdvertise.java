package com.jspgou.cms.entity.base;

import java.io.Serializable;


/**
 * This is an object that contains data related to the jc_shop_advertise table.
 * Do not modify this class because it will be overwritten if the configuration file
 * related to this class is modified.
 *
 * @hibernate.class
 *  table="jc_shop_advertise"
 * This class should preserve.
 * @preserve
 */

public abstract class BaseAdvertise  implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public static String REF = "Advertise";
	public static String PROP_NAME = "name";
	public static String PROP_WEIGHT = "weight";
	public static String PROP_ADSPACE = "adspace";
	public static String PROP_ENABLE = "enable";
	public static String PROP_CLICK_COUNT = "clickCount";
	public static String PROP_DSIPLAY_COUNT = "displayCount";
	public static String PROP_ID = "id";
	public static String PROP_END_TIME = "endTime";
	public static String PROP_START_TIME = "startTime";


	// constructors
	public BaseAdvertise () {
		initialize();
	}

	/**
	 * Constructor for primary key
	 */
	public BaseAdvertise (java.lang.Integer id) {
		this.setId(id);
		initialize();
	}

	/**
	 * Constructor for required fields
	 */
	public BaseAdvertise (
		java.lang.Integer id,
		com.jspgou.cms.entity.Adspace adspace) {

		this.setId(id);
		this.setAdspace(adspace);
		initialize();
	}

	protected void initialize () {}



	private int hashCode = Integer.MIN_VALUE;

	// primary key
	private java.lang.Integer id;

	// fields
	private java.lang.String name;
	private java.lang.Integer weight;
	private java.lang.Integer displayCount;
	private java.lang.Integer clickCount;
	private java.util.Date startTime;
	private java.util.Date endTime;
	private java.lang.Boolean enabled;
	private java.lang.Integer priority;
	// many to one
	private com.jspgou.cms.entity.Adspace adspace;
	private java.lang.Long categoryId;

	// collections
	private java.util.Map<java.lang.String, java.lang.String> attr;



	/**
	 * Return the unique identifier of this class
     * @hibernate.id
     *  generator-class="identity"
     *  column="id"
     */
	public java.lang.Integer getId () {
		return id;
	}

	/**
	 * Set the unique identifier of this class
	 * @param id the new ID
	 */
	public void setId (java.lang.Integer id) {
		this.id = id;
		this.hashCode = Integer.MIN_VALUE;
	}




	/**
	 * Return the value associated with the column: name
	 */
	public java.lang.String getName () {
		return name;
	}

	/**
	 * Set the value related to the column: name
	 * @param name the name value
	 */
	public void setName (java.lang.String name) {
		this.name = name;
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
	 * Return the value associated with the column: display_count
	 */
	public java.lang.Integer getDisplayCount () {
		return displayCount;
	}

	/**
	 * Set the value related to the column: display_count
	 * @param dsiplayCount the display_count value
	 */
	public void setDisplayCount (java.lang.Integer displayCount) {
		this.displayCount = displayCount;
	}


	/**
	 * Return the value associated with the column: click_count
	 */
	public java.lang.Integer getClickCount () {
		return clickCount;
	}

	/**
	 * Set the value related to the column: click_count
	 * @param clickCount the click_count value
	 */
	public void setClickCount (java.lang.Integer clickCount) {
		this.clickCount = clickCount;
	}


	/**
	 * Return the value associated with the column: starttime
	 */
	public java.util.Date getStartTime () {
		return startTime;
	}

	/**
	 * Set the value related to the column: starttime
	 * @param startTime the starttime value
	 */
	public void setStartTime (java.util.Date startTime) {
		this.startTime = startTime;
	}


	/**
	 * Return the value associated with the column: endTime
	 */
	public java.util.Date getEndTime () {
		return endTime;
	}

	/**
	 * Set the value related to the column: endTime
	 * @param endTime the endTime value
	 */
	public void setEndTime (java.util.Date endTime) {
		this.endTime = endTime;
	}


	/**
	 * Return the value associated with the column: is_enable
	 */
	public java.lang.Boolean getEnabled () {
		return enabled;
	}

	public java.lang.Long getCategoryId() {
		return categoryId;
	}

	public void setCategoryId(java.lang.Long categoryId) {
		this.categoryId = categoryId;
	}

	/**
	 * Set the value related to the column: is_enable
	 * @param enable the is_enable value
	 */
	public void setEnabled (java.lang.Boolean enabled) {
		this.enabled = enabled;
	}


	/**
	 * Return the value associated with the column: adspace_id
	 */
	public com.jspgou.cms.entity.Adspace getAdspace () {
		return adspace;
	}

	/**
	 * Set the value related to the column: adspace_id
	 * @param adspace the adspace_id value
	 */
	public void setAdspace (com.jspgou.cms.entity.Adspace adspace) {
		this.adspace = adspace;
	}


	/**
	 * Return the value associated with the column: attr
	 */
	public java.util.Map<java.lang.String, java.lang.String> getAttr () {
		return attr;
	}

	/**
	 * Set the value related to the column: attr
	 * @param attr the attr value
	 */
	public void setAttr (java.util.Map<java.lang.String, java.lang.String> attr) {
		this.attr = attr;
	}

	public java.lang.Integer getPriority() {
		return priority;
	}

	public void setPriority(java.lang.Integer priority) {
		this.priority = priority;
	}

	public boolean equals (Object obj) {
		if (null == obj) return false;
		if (!(obj instanceof com.jspgou.cms.entity.Advertise)) return false;
		else {
			com.jspgou.cms.entity.Advertise advertise = (com.jspgou.cms.entity.Advertise) obj;
			if (null == this.getId() || null == advertise.getId()) return false;
			else return (this.getId().equals(advertise.getId()));
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