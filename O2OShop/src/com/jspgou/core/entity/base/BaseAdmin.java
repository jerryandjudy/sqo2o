package com.jspgou.core.entity.base;

import com.jspgou.core.entity.*;

import java.io.Serializable;
import java.util.Date;
import java.util.Set;

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

public abstract class BaseAdmin implements Serializable{
	
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public static String REF = "Admin";
    public static String PROP_WEBSITE = "website";
    public static String PROP_CREATE_TIME = "createTime";
    public static String PROP_DISABLED = "disabled";
    public static String PROP_IS_SUPER = "isSuper";
    public static String PROP_USER = "user";
    public static String PROP_ID = "id";
    
	// constructors
    public BaseAdmin(){
        initialize();
    }

	/**
	 * Constructor for primary key
	 */
    public BaseAdmin(Long id){
        this.setId(id);
        initialize();
    }

	/**
	 * Constructor for required fields
	 */
    public BaseAdmin(Long id, 
    		         User user,
    		         Website website, 
    		         Date date,
    		         Boolean disabled){
        this.setId(id);
        this.setUser(user);
        this.setWebsite(website);
        this.setCreateTime(date);
        this.setDisabled(disabled);
        initialize();
    }

    protected void initialize(){}
    
	private int hashCode = Integer.MIN_VALUE;

	// primary key
    private Long id;
	// fields
    private Date createTime;
    private Boolean disabled;
    private Boolean viewonlyAdmin;
    private User user;
    private Website website;
    private java.util.Set<com.jspgou.core.entity.Role> roles;
    private Boolean isSuper;

    public Long getId(){
        return id;
    }

    public void setId(Long id){
        this.id = id;
        this.hashCode = Integer.MIN_VALUE;
    }

    public Date getCreateTime(){
        return createTime;
    }

    public void setCreateTime(Date createTime){
        this.createTime = createTime;
    }

    public Boolean getDisabled(){
        return disabled;
    }

    public void setDisabled(Boolean disabled){
        this.disabled = disabled;
    }

    public User getUser(){
        return user;
    }

    public void setUser(User user){
        this.user = user;
    }

    public Website getWebsite(){
        return website;
    }

    public void setWebsite(Website website){
        this.website = website;
    }

   

    public boolean equals(Object obj){
        if(null == obj) return false;
        if(!(obj instanceof com.jspgou.core.entity.Admin)) return false;
        else{
        	com.jspgou.core.entity.Admin admin = (com.jspgou.core.entity.Admin)obj;
             if(null == getId() || null == admin.getId()) return false;
             else return (getId().equals(admin.getId()));
        }
    }

    public int hashCode(){
        if(Integer.MIN_VALUE  == this.hashCode){
            if(null == getId()) return super.hashCode();
            else{
                 String hashStr=this.getClass().getName()+":"+this.getId().hashCode();
                 this.hashCode = hashStr.hashCode();
            }
        }
        return this.hashCode;
    }

    public String toString(){
        return super.toString();
    }

	public void setRoles(java.util.Set<com.jspgou.core.entity.Role> roles) {
		this.roles = roles;
	}

	public java.util.Set<com.jspgou.core.entity.Role> getRoles() {
		return roles;
	}

	public void setViewonlyAdmin(Boolean viewonlyAdmin) {
		this.viewonlyAdmin = viewonlyAdmin;
	}

	public Boolean getViewonlyAdmin() {
		return viewonlyAdmin;
	}
	public Boolean getIsSuper() {
		if(isSuper==null){
			isSuper=false;
		}
		return isSuper;
	}

	public void setIsSuper(Boolean isSuper) {
		this.isSuper = isSuper;
	}

}
