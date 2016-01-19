/**
* 吉林省艾利特信息技术有限公司
* 进销存管理系统
* @date 2015年4月10日
* @author liuwang
* @version 1.0
*/
package com.jspgou.cms.entity.base;

import com.jspgou.cms.entity.KetaUser;
import com.jspgou.cms.entity.Product;
import com.jspgou.cms.entity.ProductSite;

public class BaseJxcBldGoodsNum {
	private Long id;
	private Long goodsNum;
	private KetaUser ketaUser;
	private Long goodsMaxNum;
	private Long goodsMinNnum;
	private Product product;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
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
	public KetaUser getKetaUser() {
		return ketaUser;
	}
	public void setKetaUser(KetaUser ketaUser) {
		this.ketaUser = ketaUser;
	}
	public Long getGoodsMaxNum() {
		return goodsMaxNum;
	}
	public void setGoodsMaxNum(Long goodsMaxNum) {
		this.goodsMaxNum = goodsMaxNum;
	}
	public Long getGoodsMinNnum() {
		return goodsMinNnum;
	}
	public void setGoodsMinNnum(Long goodsMinNnum) {
		this.goodsMinNnum = goodsMinNnum;
	}
	public Product getProduct() {
		return product;
	}
	public void setProduct(Product product) {
		this.product = product;
	}
	
}
