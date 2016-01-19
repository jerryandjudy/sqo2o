package com.jspgou.core.entity;


import com.jspgou.core.entity.base.BaseWebsite;

import java.util.Date;

import org.apache.commons.lang.StringUtils;
/**
* This class should preserve.
* @preserve
*/
public class Website extends BaseWebsite{
    private static final long serialVersionUID = 1L;
    
    public static final String RES_BASE = "r/gou/www";
    public static final String RES_SYSTEM = "res";
    public static final String USER_BASE = "t";
    public static final String UPLOAD_PATH = "u";
    public static final String TEMPLATE_PATH = "gou/tpl";
    public static final String DEFAULT = "default";
    public static final String TPL_SUFFIX = ".html";
    public static final String TPL_PREFIX_SYS = "sys_";
    public static final String TPL_PREFIX_TAG = "tag_";
    public static final String TPL_BASE = "/WEB-INF/t/gou/tpl";
	
	/* [CONSTRUCTOR MARKER BEGIN] */
    public Website(){
    	super();
    }

	/**
	 * Constructor for primary key
	 */
    public Website(Long id){
        super(id);
    }

	/**
	 * Constructor for required fields
	 */
    public Website(Long id, Global global, String domain, String name,
    		String currentSystem, String suffix, Integer lft, Integer rgt, Date createTime, 
            Boolean close, Boolean relativePath, String frontEncoding, String frontContentType, 
            String localeFront, String localeAdmin, Integer controlNameMinlen, String company, 
            String copyright, String recordCode, String email, String phone, String mobile){
        super(id, global, domain,  name, currentSystem, suffix, lft, 
        		rgt, createTime, close, relativePath, frontEncoding, frontContentType, localeFront,
        		localeAdmin, controlNameMinlen, company, copyright,recordCode, email, phone, mobile);
    }

    public String getTemplate(String dir, String name, String s2, String s3){
        StringBuilder stringbuilder = getTemplateRelBuff().append("/").append(dir).append("/");
        if(!StringUtils.isBlank(s3)){
            stringbuilder.append(s3);
        }
        stringbuilder.append(name);
        if(!StringUtils.isBlank(s2)){
            stringbuilder.append("_").append(s2);
        }
        return stringbuilder.append(TPL_SUFFIX).toString();
    }

    public String getUrl(){
        return getUrlBuff(false).append("/").toString();
    }

    public String getUploadRel(String s){
        StringBuilder stringbuilder = (new StringBuilder("/")).append(UPLOAD_PATH);
        if(!StringUtils.isBlank(s)){
            if(!s.startsWith("/")){
                stringbuilder.append("/");
            }
            stringbuilder.append(s);
        }
        return stringbuilder.toString();
    }
    

    public String getUploadUrls(String s){
        StringBuilder stringbuilder = getResBaseUrlBuff().append("/").append(UPLOAD_PATH);
        if(!StringUtils.isBlank(s)){
            if(!s.startsWith("/")){
                stringbuilder.append("/");
            }
            stringbuilder.append(s);
        }
        return stringbuilder.toString();
    }
    
    public String getUploadUrl(String s){
        StringBuilder stringbuilder = getResBaseUrlBuff();
        if(!StringUtils.isBlank(s)){
            if(!s.startsWith("/")){
                stringbuilder.append("/");
            }
            stringbuilder.append(s);
        }
        return stringbuilder.toString();
    }
    /**
	 * 获得上传路径。如：/u/jeecms/path
	 * 
	 * @return
	 */
	public String getUploadPath() {
		return UPLOAD_PATH ;
	}
   
    /**
	 * 获得模板路径。如：/WEB-INF/t/gou/tpl
	 * 
	 * @return
	 */
	public String getTplPath() {
		return TPL_BASE;
	}

    public String getTemplateRel(String s){
        StringBuilder stringbuilder = getTemplateRelBuff();
        if(!StringUtils.isBlank(s)){
            if(!s.startsWith("/")){
                stringbuilder.append("/");
            }
            stringbuilder.append(s);
        }
        return stringbuilder.toString();
    }

    public String getResBaseRel(String s){
        StringBuilder stringbuilder = getResBaseRelBuff();
        if(!StringUtils.isBlank(s)){
            if(!s.startsWith("/")){
                stringbuilder.append("/");
            }
            stringbuilder.append(s);
        }
        return stringbuilder.toString();
    }

    public String getResBaseUrl(String s){
        StringBuilder stringbuilder = getResBaseUrlBuff();
        if(!StringUtils.isBlank(s)){
            if(!s.startsWith("/")){
                stringbuilder.append("/");
            }
            stringbuilder.append(s);
        }
        return stringbuilder.toString();
    }

    private StringBuilder getUserBaseRelBuff(){
        return (new StringBuilder("/")).append("WEB-INF").append("/").append(USER_BASE);
    }

    private StringBuilder getResBaseRelBuff(){
        return (new StringBuilder("/")).append(RES_BASE);
    }

    private StringBuilder getResBaseUrlBuff(){
        return getUrlBuff(false).append("/").append(RES_BASE);
    }
    private StringBuilder getResSystemUrlBuff(){
    	return getUrlBuff(false).append("/").append(RES_SYSTEM);
    }

    public String getResBaseUrl(){
        return getResBaseUrlBuff().toString();
    }
    public String getResSystemUrl(){
    	return getResSystemUrlBuff().toString();
    }

    public StringBuilder getTemplateRelBuff(){
        StringBuilder stringbuilder = getUserBaseRelBuff().append("/").append(TEMPLATE_PATH);
        return stringbuilder;
    }

    public String getTemplateRel(){
        return getTemplateRel(null);
    }

    public StringBuilder getUrlBuff(boolean flag){
        StringBuilder stringbuilder = new StringBuilder();
        if(flag || !getRelativePath().booleanValue()){
            stringbuilder = (new StringBuilder("http://")).append(getDomain());
            Integer integer = getGlobal().getPort();
            if(integer != null && integer.intValue() != 80){
                stringbuilder.append(":").append(integer);
            }
        }
        if(getContextPath()!=null){
        stringbuilder.append(getContextPath());
        }
        return stringbuilder;
    }

    public String getResBaseRel(){
        return getResBaseRelBuff().toString();
    }

    public String getUploadRel(){
        return getUploadRel(null);
    }

    public String getUploadUrl(){
        return getUploadUrl(null);
    }

    public String getTemplate(String dir, String name){
        return getTemplate(dir, name, null, null);
    }

    public String getTplSys(String s, String s1){
        return getTemplate(s, s1, null, null);
    }

    public String getTplTag(String s, String s1, String s2){
          return getTemplate(s, s1, s2, null);
    }

    public String getContextPath(){
        String s = getGlobal().getContextPath();
        return StringUtils.isBlank(s) ? "" : s;
    }
  
    public Integer getPort(){
    	return getGlobal().getPort();
    }

    public String[] getDomainAliasArray(){
        String s = getDomainAlias();
        if(!StringUtils.isBlank(s)){
            return s.split(",");
        }else{
            return null;
        }
    }

}
