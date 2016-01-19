package com.jspgou.cms.entity.base;

import java.io.Serializable;


/**
 * This is an object that contains data related to the jc_popularity_group table.
 * Do not modify this class because it will be overwritten if the configuration file
 * related to this class is modified.
 *
 * @hibernate.class
 *  table="jc_popularity_group"
 */

public abstract class BasePopularityGroup  implements Serializable {

	public static String REF = "PopularityGroup";
	public static String PROP_NAME = "name";
	public static String PROP_DESCRIPTION = "description";
	public static String PROP_BEGIN_TIME = "beginTime";
	public static String PROP_PRICE = "price";
	public static String PROP_PRIVILEGE = "privilege";
	public static String PROP_ID = "id";
	public static String PROP_END_TIME = "endTime";


	// constructors
	public BasePopularityGroup () {
		initialize();
	}

	/**
	 * Constructor for primary key
	 */
	public BasePopularityGroup (java.lang.Long id) {
		this.setId(id);
		initialize();
	}

	/**
	 * Constructor for required fields
	 */
	public BasePopularityGroup (
		java.lang.Long id,
		java.lang.String name,
		java.lang.Double price,
		java.lang.Double privilege) {

		this.setId(id);
		this.setName(name);
		this.setPrice(price);
		this.setPrivilege(privilege);
		initialize();
	}

	protected void initialize () {}



	private int hashCode = Integer.MIN_VALUE;

	// primary key
	private java.lang.Long id;

	// fields
	private java.lang.String name;
	private java.lang.Double price;
	private java.lang.Double privilege;
	private java.lang.String description;

	// collections
	private java.util.Set<com.jspgou.cms.entity.Product> products;



	/**
	 * Return the unique identifier of this class
     * @hibernate.id
     *  generator-class="native"
     *  column="id"
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
	 * Return the value associated with the column: price
	 */
	public java.lang.Double getPrice () {
		return price;
	}

	/**
	 * Set the value related to the column: price
	 * @param price the price value
	 */
	public void setPrice (java.lang.Double price) {
		this.price = price;
	}


	/**
	 * Return the value associated with the column: privilege
	 */
	public java.lang.Double getPrivilege () {
		return privilege;
	}

	/**
	 * Set the value related to the column: privilege
	 * @param privilege the privilege value
	 */
	public void setPrivilege (java.lang.Double privilege) {
		this.privilege = privilege;
	}


	/**
	 * Return the value associated with the column: description
	 */
	public java.lang.String getDescription () {
		return description;
	}

	/**
	 * Set the value related to the column: description
	 * @param description the description value
	 */
	public void setDescription (java.lang.String description) {
		this.description = description;
	}


	/**
	 * Return the value associated with the column: products
	 */
	public java.util.Set<com.jspgou.cms.entity.Product> getProducts () {
		return products;
	}

	/**
	 * Set the value related to the column: products
	 * @param products the products value
	 */
	public void setProducts (java.util.Set<com.jspgou.cms.entity.Product> products) {
		this.products = products;
	}



	public boolean equals (Object obj) {
		if (null == obj) return false;
		if (!(obj instanceof com.jspgou.cms.entity.PopularityGroup)) return false;
		else {
			com.jspgou.cms.entity.PopularityGroup popularityGroup = (com.jspgou.cms.entity.PopularityGroup) obj;
			if (null == this.getId() || null == popularityGroup.getId()) return false;
			else return (this.getId().equals(popularityGroup.getId()));
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