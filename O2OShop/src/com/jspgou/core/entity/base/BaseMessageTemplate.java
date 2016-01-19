package com.jspgou.core.entity.base;

import java.io.Serializable;

/**
 * This is an object that contains data related to the jo_authentication table.
 * Do not modify this class because it will be overwritten if the configuration file
 * related to this class is modified.
 *
 * @hibernate.class
 *  table="jo_authentication"
 * This class should preserve.
 * @preserve
*/

public abstract class BaseMessageTemplate implements Serializable{
	
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public static String REF = "MessageTemplate";
    public static String PROP_SUBJECT = "subject";
    public static String PROP_TEXT = "text";

	// constructors
    public BaseMessageTemplate(){
        initialize();
    }

    protected void initialize(){}
    
	// fields
    private String subject;
    private String text;
    private String activeTitle;
    private String activeTxt;

    public String getSubject(){
        return subject;
    }

    public void setSubject(String subject){
        this.subject = subject;
    }

    public String getText(){
        return text;
    }

    public void setText(String text){
        this.text = text;
    }

    public String getActiveTitle() {
		return activeTitle;
	}

	public void setActiveTitle(String activeTitle) {
		this.activeTitle = activeTitle;
	}

	public String getActiveTxt() {
		return activeTxt;
	}

	public void setActiveTxt(String activeTxt) {
		this.activeTxt = activeTxt;
	}

	public String toString(){
        return super.toString();
    }

}
