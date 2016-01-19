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
public abstract class BaseEmailSender  implements Serializable{
	
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public static String REF = "EmailSender";
    public static String PROP_PASSWORD = "password";
    public static String PROP_HOST = "host";
    public static String PROP_ENCODING = "encoding";
    public static String PROP_PERSONAL = "personal";
    public static String PROP_USERNAME = "username";

	// constructors
    public BaseEmailSender(){
        initialize();
    }

    protected void initialize(){}
    
	// fields
    private String host;
    private String encoding;
    private String username;
    private String password;
    private String personal;

    public String getHost(){
        return host;
    }

    public void setHost(String host){
        this.host = host;
    }

    public String getEncoding(){
        return encoding;
    }

    public void setEncoding(String encoding){
        this.encoding = encoding;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username){
        this.username = username;
    }

    public String getPassword(){
        return password;
    }

    public void setPassword(String password){
        this.password = password;
    }

    public String getPersonal(){
        return personal;
    }

    public void setPersonal(String personal){
        this.personal = personal;
    }

    public String toString(){
        return super.toString();
    }

}
