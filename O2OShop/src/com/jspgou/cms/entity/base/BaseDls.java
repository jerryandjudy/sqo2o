package com.jspgou.cms.entity.base;

import java.io.Serializable;
import java.util.Date;

import com.jspgou.cms.entity.KetaUser;
import com.jspgou.cms.entity.Organization;
import com.jspgou.core.entity.User;
import com.jspgou.core.entity.Website;

public  abstract class BaseDls implements Serializable{
	private static final long serialVersionUID = 1L;
	// primary key
	private String id;
	private String companyName;
	private Website website;
	private KetaUser ketaUser;
	private User user;
	private Boolean isDisabled;
	private Date createTime;
	
	
	private String companyAddr;//办公地址
	private String legalPerson;//法人
	private String contact;//联系人
	private String contactTel;//联系方式
	private String orgCodeCert;//组织机构代码证号，编号
	
	private String fjLlrAuthorize;//联络人有效授权书
	private String fjShDqdlsqb;//商户签署的《地区代理申请表》
	private String fjDqsqqrs;//地区授权确认书
	private String fjDlht;//代理合同
	private String fjDlbzs;//代理保证书
	private String fjQyyyzz;//企业营业执照
	private String fjZzjgdm;//组织机构代码证，这个是附件
	private String fjSwdjz;//税务登记证
	private String fjKhxkz;//开户许可证
	private String fjFrFfrSfz;//法人及非法人身份证正反面
	private String fjQyxyjt;//企业信用截图
	private String fjRzkzfm;//入账卡正反面
	private String fjRzsqs;//入账授权书
	private String fjFrFfrYhk;//法人银行卡及非法人银行卡正反面
	private String fjCsjmht;//超市加盟合同
	private String khh;
	private String khhzh;
	private String hm;
	
	
	
	
	
	
	public static String PROP_CREATETIME = "createTime";

	// constructors
			public BaseDls () {
				initialize();
			}
			protected void initialize () {}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getCompanyName() {
		return companyName;
	}
	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}
	public Website getWebsite() {
		return website;
	}
	public void setWebsite(Website website) {
		this.website = website;
	}
	public KetaUser getKetaUser() {
		return ketaUser;
	}
	public void setKetaUser(KetaUser ketaUser) {
		this.ketaUser = ketaUser;
	}
	public Boolean getIsDisabled() {
		if(isDisabled==null){
			isDisabled=false;
		}
		return isDisabled;
	}
	public void setIsDisabled(Boolean isDisabled) {
		this.isDisabled = isDisabled;
	}
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	public String getCompanyAddr() {
		return companyAddr;
	}
	public void setCompanyAddr(String companyAddr) {
		this.companyAddr = companyAddr;
	}
	public String getLegalPerson() {
		return legalPerson;
	}
	public void setLegalPerson(String legalPerson) {
		this.legalPerson = legalPerson;
	}
	public String getContact() {
		return contact;
	}
	public void setContact(String contact) {
		this.contact = contact;
	}
	public String getContactTel() {
		return contactTel;
	}
	public void setContactTel(String contactTel) {
		this.contactTel = contactTel;
	}
	public String getOrgCodeCert() {
		return orgCodeCert;
	}
	public void setOrgCodeCert(String orgCodeCert) {
		this.orgCodeCert = orgCodeCert;
	}
	public String getFjLlrAuthorize() {
		return fjLlrAuthorize;
	}
	public void setFjLlrAuthorize(String fjLlrAuthorize) {
		this.fjLlrAuthorize = fjLlrAuthorize;
	}
	public String getFjShDqdlsqb() {
		return fjShDqdlsqb;
	}
	public void setFjShDqdlsqb(String fjShDqdlsqb) {
		this.fjShDqdlsqb = fjShDqdlsqb;
	}
	
	public String getFjDlht() {
		return fjDlht;
	}
	public void setFjDlht(String fjDlht) {
		this.fjDlht = fjDlht;
	}
	public String getFjDlbzs() {
		return fjDlbzs;
	}
	public void setFjDlbzs(String fjDlbzs) {
		this.fjDlbzs = fjDlbzs;
	}
	public String getFjQyyyzz() {
		return fjQyyyzz;
	}
	public void setFjQyyyzz(String fjQyyyzz) {
		this.fjQyyyzz = fjQyyyzz;
	}
	public String getFjZzjgdm() {
		return fjZzjgdm;
	}
	public void setFjZzjgdm(String fjZzjgdm) {
		this.fjZzjgdm = fjZzjgdm;
	}
	public String getFjSwdjz() {
		return fjSwdjz;
	}
	public void setFjSwdjz(String fjSwdjz) {
		this.fjSwdjz = fjSwdjz;
	}
	public String getFjKhxkz() {
		return fjKhxkz;
	}
	public void setFjKhxkz(String fjKhxkz) {
		this.fjKhxkz = fjKhxkz;
	}
	public String getFjFrFfrSfz() {
		return fjFrFfrSfz;
	}
	public void setFjFrFfrSfz(String fjFrFfrSfz) {
		this.fjFrFfrSfz = fjFrFfrSfz;
	}
	public String getFjQyxyjt() {
		return fjQyxyjt;
	}
	public void setFjQyxyjt(String fjQyxyjt) {
		this.fjQyxyjt = fjQyxyjt;
	}
	public String getFjRzkzfm() {
		return fjRzkzfm;
	}
	public void setFjRzkzfm(String fjRzkzfm) {
		this.fjRzkzfm = fjRzkzfm;
	}
	public String getFjRzsqs() {
		return fjRzsqs;
	}
	public void setFjRzsqs(String fjRzsqs) {
		this.fjRzsqs = fjRzsqs;
	}
	public String getFjFrFfrYhk() {
		return fjFrFfrYhk;
	}
	public void setFjFrFfrYhk(String fjFrFfrYhk) {
		this.fjFrFfrYhk = fjFrFfrYhk;
	}
	public String getFjCsjmht() {
		return fjCsjmht;
	}
	public void setFjCsjmht(String fjCsjmht) {
		this.fjCsjmht = fjCsjmht;
	}
	public String getFjDqsqqrs() {
		return fjDqsqqrs;
	}
	public void setFjDqsqqrs(String fjDqsqqrs) {
		this.fjDqsqqrs = fjDqsqqrs;
	}
	public String getKhh() {
		return khh;
	}
	public void setKhh(String khh) {
		this.khh = khh;
	}
	public String getKhhzh() {
		return khhzh;
	}
	public void setKhhzh(String khhzh) {
		this.khhzh = khhzh;
	}
	public String getHm() {
		return hm;
	}
	public void setHm(String hm) {
		this.hm = hm;
	}
	
	
	

}
