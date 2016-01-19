package com.jspgou.core.entity.base;

import com.jspgou.core.entity.*;
import java.io.Serializable;
import java.util.Date;

/**
 * This is an object that contains data related to the jc_core_member table.
 * Do not modify this class because it will be overwritten if the configuration file
 * related to this class is modified.
 *
 * @hibernate.class
 *  table="jc_core_member"
 * This class should preserve.
 * @preserve
*/

public abstract class BaseMember  implements Serializable{
	
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public static String REF = "Member";
    public static String PROP_WEBSITE = "website";
    public static String PROP_CREATE_TIME = "createTime";
    public static String PROP_DISABLED = "disabled";
    public static String PROP_USER = "user";
    public static String PROP_ID = "id";

	// constructors
    public BaseMember(){
        initialize();
    }

	/**
	 * Constructor for primary key
	 */
    public BaseMember(Long id){
        this.setId(id);
        initialize();
    }

	/**
	 * Constructor for required fields
	 */
    public BaseMember(Long id, 
    		          User user,
    		          Website website,
    		          Date createTime,
    		          Boolean disabled,
    		          Boolean active,
    		          String activationCode){
        this.setId(id);
        this.setUser(user);
        this.setWebsite(website);
        this.setCreateTime(createTime);
        this.setDisabled(disabled);
        this.setActive(active);
        this.setActivationCode(activationCode);
        initialize();
    }

    protected void initialize() {}
    
	private int hashCode = Integer.MIN_VALUE;
	
	// primary key
    private Long id;
    
	// fields
    private Date createTime;
    private Boolean disabled;
	private Boolean active;
	private String activationCode;
    
    private User user;
    private Website website;
    

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
    
    
    
	/**
	 * Return the value associated with the column: active
	 */
	public Boolean getActive () {
		return active;
	}

	/**
	 * Set the value related to the column: active
	 * @param active the activation value
	 */
	public void setActive(Boolean active) {
		this.active = active;
	}

	/**
	 * Return the value associated with the column: activation_code
	 */
	public java.lang.String getActivationCode () {
		return activationCode;
	}

	/**
	 * Set the value related to the column: activation_code
	 * @param activationCode the activation_code value
	 */
	public void setActivationCode (java.lang.String activationCode) {
		this.activationCode = activationCode;
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
        if(!(obj instanceof Member)) return false;
        else{
        Member member = (Member)obj;
        if(null == this.getId() || null == member.getId()) return false;
        else return (this.getId().equals(member.getId()));
        }
    }

    public int hashCode(){
        if(Integer.MIN_VALUE == hashCode){
            if(null == getId()) return super.hashCode();
            String hashStr = this.getClass().getName()+":"+this.getId().hashCode();
            this.hashCode = hashStr.hashCode();
        }
        return this.hashCode;
    }

    public String toString(){
        return super.toString();
    }

}
