/**
* 吉林省艾利特信息技术有限公司
* O2OShop系统
* @date 2015年6月3日
* @author liuwang
* @version 1.0
*/
package com.jspgou.cms.entity.base;

import java.io.Serializable;
import java.util.Date;

import com.jspgou.cms.entity.KetaUser;
import com.jspgou.cms.entity.ShopMember;
import com.jspgou.cms.entity.SqService;

public class BaseSqOrder implements Serializable {
	// primary key
    private java.lang.Long id;
	private long code;

	private int status;// 1 订单已生效 ，等待卖家接单2 卖家已接单，交易结束

	private String totalPrice;

	private Date createTime;

	private Date finishedTime;

	private String comments;

	private SqService sqService;

	private KetaUser seller;

	private String receiveName;//收货人姓名

	private String	receiveAddress;//收货人地址

	private String	receiveTel;//收货人联系方式
	private ShopMember member;
	private com.jspgou.core.entity.Website website;

	public long getCode() {
		return code;
	}

	public void setCode(long code) {
		this.code = code;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public String getTotalPrice() {
		return totalPrice;
	}

	public void setTotalPrice(String totalPrice) {
		this.totalPrice = totalPrice;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public Date getFinishedTime() {
		return finishedTime;
	}

	public void setFinishedTime(Date finishedTime) {
		this.finishedTime = finishedTime;
	}

	public String getComments() {
		return comments;
	}

	public void setComments(String comments) {
		this.comments = comments;
	}

	public SqService getSqService() {
		return sqService;
	}

	public void setSqService(SqService sqService) {
		this.sqService = sqService;
	}


	public KetaUser getSeller() {
		return seller;
	}

	public void setSeller(KetaUser seller) {
		this.seller = seller;
	}



	public String getReceiveName() {
		return receiveName;
	}

	public void setReceiveName(String receiveName) {
		this.receiveName = receiveName;
	}

	public String getReceiveAddress() {
		return receiveAddress;
	}

	public void setReceiveAddress(String receiveAddress) {
		this.receiveAddress = receiveAddress;
	}

	public String getReceiveTel() {
		return receiveTel;
	}

	public void setReceiveTel(String receiveTel) {
		this.receiveTel = receiveTel;
	}

	public ShopMember getMember() {
		return member;
	}

	public void setMember(ShopMember member) {
		this.member = member;
	}

	public java.lang.Long getId() {
		return id;
	}

	public void setId(java.lang.Long id) {
		this.id = id;
	}

	public com.jspgou.core.entity.Website getWebsite() {
		return website;
	}

	public void setWebsite(com.jspgou.core.entity.Website website) {
		this.website = website;
	}
	
}
