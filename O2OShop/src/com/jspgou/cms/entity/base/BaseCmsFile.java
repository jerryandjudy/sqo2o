package com.jspgou.cms.entity.base;

import java.io.Serializable;

import com.jspgou.cms.entity.CmsFile;
import com.jspgou.cms.entity.Gys;


/**
 * This is an object that contains data related to the jc_file table.
 * Do not modify this class because it will be overwritten if the configuration file
 * related to this class is modified.
 *
 * @hibernate.class
 *  table="jc_file"
 */

public abstract class BaseCmsFile  implements Serializable {

	public static String REF = "CmsFile";
	public static String PROP_FILE_ISVALID = "fileIsvalid";
	public static String PROP_FILE_NAME = "fileName";
	public static String PROP_FILE_PATH = "filePath";
	public static String PROP_CONTENT = "content";


	// constructors
	public BaseCmsFile () {
		initialize();
	}

	/**
	 * Constructor for primary key
	 */
	public BaseCmsFile (java.lang.String filePath) {
		this.setFilePath(filePath);
		initialize();
	}

	/**
	 * Constructor for required fields
	 */
	public BaseCmsFile (
		java.lang.String filePath,
		boolean fileIsvalid) {

		this.setFilePath(filePath);
		this.setFileIsvalid(fileIsvalid);
		initialize();
	}

	protected void initialize () {}



	private int hashCode = Integer.MIN_VALUE;

	// primary key
	private java.lang.String filePath;

	// fields
	private java.lang.String fileName;
	private boolean fileIsvalid;

	// many to one
	private  Gys gys;



	/**
	 * Return the unique identifier of this class
     * @hibernate.id
     *  generator-class="assigned"
     *  column="file_path"
     */
	public java.lang.String getFilePath () {
		return filePath;
	}

	/**
	 * Set the unique identifier of this class
	 * @param filePath the new ID
	 */
	public void setFilePath (java.lang.String filePath) {
		this.filePath = filePath;
		this.hashCode = Integer.MIN_VALUE;
	}




	/**
	 * Return the value associated with the column: file_name
	 */
	public java.lang.String getFileName () {
		return fileName;
	}

	/**
	 * Set the value related to the column: file_name
	 * @param fileName the file_name value
	 */
	public void setFileName (java.lang.String fileName) {
		this.fileName = fileName;
	}


	/**
	 * Return the value associated with the column: file_isvalid
	 */
	public boolean isFileIsvalid () {
		return fileIsvalid;
	}

	/**
	 * Set the value related to the column: file_isvalid
	 * @param fileIsvalid the file_isvalid value
	 */
	public void setFileIsvalid (boolean fileIsvalid) {
		this.fileIsvalid = fileIsvalid;
	}





	public Gys getGys() {
		return gys;
	}

	public void setGys(Gys gys) {
		this.gys = gys;
	}

	public boolean equals (Object obj) {
		if (null == obj) return false;
		if (!(obj instanceof CmsFile)) return false;
		else {
			CmsFile cmsFile = (CmsFile) obj;
			if (null == this.getFilePath() || null == cmsFile.getFilePath()) return false;
			else return (this.getFilePath().equals(cmsFile.getFilePath()));
		}
	}

	public int hashCode () {
		if (Integer.MIN_VALUE == this.hashCode) {
			if (null == this.getFilePath()) return super.hashCode();
			else {
				String hashStr = this.getClass().getName() + ":" + this.getFilePath().hashCode();
				this.hashCode = hashStr.hashCode();
			}
		}
		return this.hashCode;
	}


	public String toString () {
		return super.toString();
	}


}