package com.jspgou.cms.entity.base;

import java.io.Serializable;
import java.util.Date;

import com.jspgou.cms.entity.Gys;
import com.jspgou.cms.entity.ProductSite;
import com.jspgou.cms.entity.Repertory;

public  abstract class BaseJysp implements Serializable{
	private static final long serialVersionUID = 1L;
	// primary key
	private Long id;
	
	private Long goodsMaxNum;
	private Long goodsNum;
	private Long goodsMinNum;
	private Double goodsPrice;
	private String username;
	private ProductSite productSite;
	private Gys gys;
	private Repertory repertory;
	private Date createTime;
	public static String PROP_CREATETIME = "createTime";

	// constructors
			public BaseJysp () {
				initialize();
			}
			protected void initialize () {}
			public Long getId() {
				return id;
			}
			public void setId(Long id) {
				this.id = id;
			}
			public Long getGoodsMaxNum() {
				if(goodsMaxNum==null){
					goodsMaxNum=0l;
				}
				return goodsMaxNum;
			}
			public void setGoodsMaxNum(Long goodsMaxNum) {
				this.goodsMaxNum = goodsMaxNum;
			}
			public Long getGoodsNum() {
				if(goodsNum==null){
					goodsNum=0l;
				}
				return goodsNum;
			}
			public void setGoodsNum(Long goodsNum) {
				this.goodsNum = goodsNum;
			}
			public Long getGoodsMinNum() {
				if(goodsMinNum==null){
					goodsMinNum=0l;
				}
				return goodsMinNum;
			}
			public void setGoodsMinNum(Long goodsMinNum) {
				this.goodsMinNum = goodsMinNum;
			}
			public Double getGoodsPrice() {
				if(goodsPrice==null){
					goodsPrice=0d;
				}
				return goodsPrice;
			}
			public void setGoodsPrice(Double goodsPrice) {
				this.goodsPrice = goodsPrice;
			}
			public String getUsername() {
				return username;
			}
			public void setUsername(String username) {
				this.username = username;
			}
			public Gys getGys() {
				return gys;
			}
			public void setGys(Gys gys) {
				this.gys = gys;
			}
			public Date getCreateTime() {
				return createTime;
			}
			public void setCreateTime(Date createTime) {
				this.createTime = createTime;
			}
			public ProductSite getProductSite() {
				return productSite;
			}
			public void setProductSite(ProductSite productSite) {
				this.productSite = productSite;
			}
			public Repertory getRepertory() {
				return repertory;
			}
			public void setRepertory(Repertory repertory) {
				this.repertory = repertory;
			}

}
