package com.jspgou.cms.entity.base;

import java.io.Serializable;


/**
 * This is an object that contains data related to the jc_data_backup table.
 * Do not modify this class because it will be overwritten if the configuration file
 * related to this class is modified.
 *
 * @hibernate.class
 *  table="jc_data_backup"
 * This class should preserve.
 * @preserve
 */

public abstract class BaseDataBackup  implements Serializable {

	public static String REF = "DataBackup";
	public static String PROP_PASSWORD = "password";
	public static String PROP_USERNAME = "username";
	public static String PROP_ADDRESS = "address";
	public static String PROP_ID = "id";
	public static String PROP_DATA_BASE_NAME = "dataBaseName";
	public static String PROP_DATA_BASE_PATH = "dataBasePath";


	// constructors
	public BaseDataBackup () {
		initialize();
	}

	/**
	 * Constructor for primary key
	 */
	public BaseDataBackup (java.lang.Integer id) {
		this.setId(id);
		initialize();
	}

	/**
	 * Constructor for required fields
	 */
	public BaseDataBackup (
		java.lang.Integer id,
		java.lang.String dataBasePath,
		java.lang.String address,
		java.lang.String dataBaseName,
		java.lang.String username,
		java.lang.String password) {

		this.setId(id);
		this.setDataBasePath(dataBasePath);
		this.setAddress(address);
		this.setDataBaseName(dataBaseName);
		this.setUsername(username);
		this.setPassword(password);
		initialize();
	}

	protected void initialize () {}



	private int hashCode = Integer.MIN_VALUE;

	// primary key
	private java.lang.Integer id;

	// fields
	private java.lang.String dataBasePath;
	private java.lang.String address;
	private java.lang.String dataBaseName;
	private java.lang.String username;
	private java.lang.String password;



	/**
	 * Return the unique identifier of this class
     * @hibernate.id
     *  generator-class="native"
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
	 * Return the value associated with the column: dataBasePath
	 */
	public java.lang.String getDataBasePath () {
		return dataBasePath;
	}

	/**
	 * Set the value related to the column: dataBasePath
	 * @param dataBasePath the dataBasePath value
	 */
	public void setDataBasePath (java.lang.String dataBasePath) {
		this.dataBasePath = dataBasePath;
	}


	/**
	 * Return the value associated with the column: address
	 */
	public java.lang.String getAddress () {
		return address;
	}

	/**
	 * Set the value related to the column: address
	 * @param address the address value
	 */
	public void setAddress (java.lang.String address) {
		this.address = address;
	}


	/**
	 * Return the value associated with the column: dataBaseName
	 */
	public java.lang.String getDataBaseName () {
		return dataBaseName;
	}

	/**
	 * Set the value related to the column: dataBaseName
	 * @param dataBaseName the dataBaseName value
	 */
	public void setDataBaseName (java.lang.String dataBaseName) {
		this.dataBaseName = dataBaseName;
	}


	/**
	 * Return the value associated with the column: username
	 */
	public java.lang.String getUsername () {
		return username;
	}

	/**
	 * Set the value related to the column: username
	 * @param username the username value
	 */
	public void setUsername (java.lang.String username) {
		this.username = username;
	}


	/**
	 * Return the value associated with the column: password
	 */
	public java.lang.String getPassword () {
		return password;
	}

	/**
	 * Set the value related to the column: password
	 * @param password the password value
	 */
	public void setPassword (java.lang.String password) {
		this.password = password;
	}



	public boolean equals (Object obj) {
		if (null == obj) return false;
		if (!(obj instanceof com.jspgou.cms.entity.DataBackup)) return false;
		else {
			com.jspgou.cms.entity.DataBackup dataBackup = (com.jspgou.cms.entity.DataBackup) obj;
			if (null == this.getId() || null == dataBackup.getId()) return false;
			else return (this.getId().equals(dataBackup.getId()));
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