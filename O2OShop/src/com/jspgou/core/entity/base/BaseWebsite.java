package com.jspgou.core.entity.base;

import com.jspgou.core.entity.*;
import java.io.Serializable;
import java.util.*;

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

public abstract class BaseWebsite  implements Serializable{
	
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public static String REF = "Website";
    public static String PROP_CONTROL_NAME_MINLEN = "controlNameMinlen";
    public static String PROP_RGT = "rgt";
    public static String PROP_CONTROL_ADMIN_IPS = "controlAdminIps";
    public static String PROP_LOCALE_ADMIN = "localeAdmin";
    public static String PROP_CREATE_TIME = "createTime";
    public static String PROP_CLOSE = "close";
    public static String PROP_CURRENT_SYSTEM = "currentSystem";
    public static String PROP_SUFFIX = "suffix";
    public static String PROP_FRONT_CONTENT_TYPE = "frontContentType";
    public static String PROP_PASSWORD = "password";
    public static String PROP_MOBILE = "mobile";
    public static String PROP_DOMAIN_ALIAS = "domainAlias";
    public static String PROP_LOCALE_FRONT = "localeFront";
    public static String PROP_NAME = "name";
    public static String PROP_CONTROL_FRONT_IPS = "controlFrontIps";
    public static String PROP_GLOBAL = "global";
    public static String PROP_HOST = "host";
    public static String PROP_DOMAIN = "domain";
    public static String PROP_RES_PATH = "resPath";
    public static String PROP_BASE_DOMAIN = "baseDomain";
    public static String PROP_PHONE = "phone";
    public static String PROP_CLOSE_REASON = "closeReason";
    public static String PROP_COPYRIGHT = "copyright";
    public static String PROP_RECORD_CODE = "recordCode";
    public static String PROP_EMAIL = "email";
    public static String PROP_ENCODING = "encoding";
    public static String PROP_FRONT_ENCODING = "frontEncoding";
    public static String PROP_SHORT_NAME = "shortName";
    public static String PROP_LFT = "lft";
    public static String PROP_PARENT = "parent";
    public static String PROP_COMPANY = "company";
    public static String PROP_PERSONAL = "personal";
    public static String PROP_CONTROL_RESERVED = "controlReserved";
    public static String PROP_ADMIN = "admin";
    public static String PROP_ID = "id";
    public static String PROP_USERNAME = "username";
    public static String PROP_RELATIVE_PATH = "relativePath";

	// constructors
    public BaseWebsite(){
        initialize();
    }

	/**
	 * Constructor for primary key
	 */
    public BaseWebsite(Long id){
        this.setId(id);
        initialize();
    }

	/**
	 * Constructor for required fields
	 */
    public BaseWebsite(Long id, Global global,
    		String domain, String name, String currentSystem,
    		String suffix, Integer lft, Integer rgt, Date createTime, Boolean close, 
            Boolean relativePath, String frontEncoding, String frontContentType, 
            String localeFront, String localeAdmin, Integer controlNameMinlen, String company, String copyright, 
            String recordCode, String email, String phone, String mobile){
        this.setId(id);
        this.setGlobal(global);
        this.setDomain(domain);
        this.setName(name);
        this.setCurrentSystem(currentSystem);
        this.setSuffix(suffix);
        this.setLft(lft);
        this.setRgt(rgt);
        this.setCreateTime(createTime);
        this.setClose(close);
        this.setRelativePath(relativePath);
        this.setFrontEncoding(frontEncoding);
        this.setFrontContentType(frontContentType);
        this.setLocaleFront(localeFront);
        this.setLocaleAdmin(localeAdmin);
        this.setControlNameMinlen(controlNameMinlen);
        this.setCompany(company);
        this.setCopyright(copyright);
        this.setRecordCode(recordCode);
        this.setEmail(email);
        this.setPhone(phone);
        this.setMobile(mobile);
        initialize();
    }

    protected void initialize(){}
    
	private int hashCode = Integer.MIN_VALUE;
	
	// primary key
    private Long id;
    
	// fields
    private String domain;
    private String name;
    private String additionalTitle;
    private String currentSystem;
    private String suffix;
    private Integer lft;
    private Integer rgt;
    private Date createTime;
    private String baseDomain;
    private String domainAlias;
    private String shortName;
    private String closeReason;
    private Boolean close;
    private Boolean relativePath;
    private String frontEncoding;
    private String frontContentType;
    private String localeFront;
    private String localeAdmin;
    private String controlReserved;
    private Integer controlNameMinlen;
    private String controlFrontIps;
    private String controlAdminIps;
    private String company;
    private String copyright;
    private String recordCode;
    private String email;
    private String phone;
    private String mobile;
    private String version;
    private Boolean restart;
    EmailSender emailSender;
    private Admin admin;
    private Website parent;
    private Account account;
    private Global global;
    private Set<Website> child;
    private Map<String, MessageTemplate> messages;

    public Long getId(){
        return id;
    }

    public void setId(Long id) {
        this.id = id;
		this.hashCode = Integer.MIN_VALUE;
    }

    public String getDomain(){
        return domain;
    }

    public void setDomain(String domain){
        this.domain = domain;
    }

    public String getName(){
        return name;
    }

    public void setName(String name){
        this.name = name;
    }

    public String getAdditionalTitle() {
		return additionalTitle;
	}

	public void setAdditionalTitle(String additionalTitle) {
		this.additionalTitle = additionalTitle;
	}

	public String getCurrentSystem(){
        return currentSystem;
    }

    public void setCurrentSystem(String currentSystem){
        this.currentSystem = currentSystem;
    }

    public String getSuffix(){
        return suffix;
    }

    public void setSuffix(String suffix){
        this.suffix = suffix;
    }

    public Integer getLft(){
    	if(lft==null){
    		lft=1;
    	}
        return lft;
    }

    public void setLft(Integer lft){
        this.lft = lft;
    }

    public Integer getRgt(){
    	if(rgt==null){
    		rgt=2;
    	}
        return rgt;
    }

    public void setRgt(Integer rgt){
        this.rgt = rgt;
    }

    public Date getCreateTime() {
    	if(createTime==null){
    		createTime=new Date();
    	}
        return createTime;
    }

    public void setCreateTime(Date createTime){
        this.createTime = createTime;
    }

    public String getBaseDomain(){
        return baseDomain;
    }

    public void setBaseDomain(String baseDomain){
        this.baseDomain = baseDomain;
    }

    public String getDomainAlias(){
        return domainAlias;
    }

    public void setDomainAlias(String domainAlias){
        this.domainAlias = domainAlias;
    }

    public String getShortName(){
        return shortName;
    }

    public void setShortName(String shortName){
        this.shortName = shortName;
    }

    public String getCloseReason(){
        return closeReason;
    }

    public void setCloseReason(String closeReason){
        this.closeReason = closeReason;
    }

    public Boolean getClose(){
        return close;
    }

    public void setClose(Boolean close){
        this.close = close;
    }

    public Boolean getRelativePath(){
        return relativePath;
    }

    public void setRelativePath(Boolean relativePath){
        this.relativePath = relativePath;
    }

    public String getFrontEncoding(){
        return frontEncoding;
    }

    public void setFrontEncoding(String frontEncoding){
        this.frontEncoding = frontEncoding;
    }

    public String getFrontContentType(){
        return frontContentType;
    }

    public void setFrontContentType(String frontContentType){
        this.frontContentType = frontContentType;
    }

    public String getLocaleFront(){
        return localeFront;
    }

    public void setLocaleFront(String localeFront){
        this.localeFront = localeFront;
    }

    public String getLocaleAdmin(){
        return localeAdmin;
    }

    public void setLocaleAdmin(String localeAdmin){
        this.localeAdmin = localeAdmin;
    }

    public String getControlReserved(){
        return controlReserved;
    }

    public void setControlReserved(String controlReserved){
        this.controlReserved = controlReserved;
    }

    public Integer getControlNameMinlen(){
        return controlNameMinlen;
    }

    public void setControlNameMinlen(Integer controlNameMinlen){
    	if(controlNameMinlen==null){
    		controlNameMinlen=4;
    	}
        this.controlNameMinlen = controlNameMinlen;
    }

    public String getControlFrontIps(){
        return controlFrontIps;
    }

    public void setControlFrontIps(String controlFrontIps){
        this.controlFrontIps = controlFrontIps;
    }

    public String getControlAdminIps(){
        return controlAdminIps;
    }

    public void setControlAdminIps(String controlAdminIps){
        this.controlAdminIps = controlAdminIps;
    }

    public String getCompany(){
        return company;
    }

    public void setCompany(String company){
        this.company = company;
    }

    public String getCopyright(){
        return copyright;
    }

    public void setCopyright(String copyright){
        this.copyright = copyright;
    }

    public String getRecordCode(){
        return recordCode;
    }

    public void setRecordCode(String recordCode){
        this.recordCode = recordCode;
    }

    public String getEmail(){
        return email;
    }

    public void setEmail(String email){
        this.email = email;
    }

    public String getPhone(){
        return phone;
    }

    public void setPhone(String phone){
        this.phone = phone;
    }

    public String getMobile(){
        return mobile;
    }

    public void setMobile(String mobile){
        this.mobile = mobile;
    }

    public EmailSender getEmailSender(){
        return emailSender;
    }

    public void setEmailSender(EmailSender emailSender){
        this.emailSender = emailSender;
    }

    public Admin getAdmin(){
        return admin;
    }

    public void setAdmin(Admin admin){
        this.admin = admin;
    }

    public Website getParent(){
        return parent;
    }

    public void setParent(Website parent){
        this.parent = parent;
    }

    public Global getGlobal(){
        return global;
    }

    public void setGlobal(Global global){
        this.global = global;
    }

    public Set<Website> getChild(){
        return child;
    }

    public void setChild(Set<Website> child){
        this.child = child;
    }

    public Map<String, MessageTemplate> getMessages(){
        return messages;
    }

    public void setMessages(Map<String, MessageTemplate> messages){
        this.messages = messages;
    }

    public boolean equals(Object obj){
        if(null == obj) return false;
        if(!(obj instanceof Website)) return false;
        else{
             Website website = (Website)obj;
             if(null == this.getId() || null == website.getId()) return false;
             else return (this.getId().equals(website.getId()));
        }
    }

    public int hashCode(){
        if(Integer.MIN_VALUE == this.hashCode){
            if(null == this.getId()) return super.hashCode();
            else{
               String hashStr = this.getClass().getName()+":"+this.getId().hashCode();
               hashCode = hashStr.hashCode();
            }
        }
        return hashCode;
    }

    public String toString(){
        return super.toString();
    }

	public void setVersion(String version) {
		this.version = version;
	}

	public String getVersion() {
		if(version==null){
			version="1.0";	
		}
		return version;
	}

	public void setRestart(Boolean restart) {
		
		this.restart = restart;
	}

	public Boolean getRestart() {
		if(restart==null){
			restart=false;	
		}
		return restart;
	}

	public Account getAccount() {
		return account;
	}

	public void setAccount(Account account) {
		this.account = account;
	}

}
