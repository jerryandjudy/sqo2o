package com.jspgou.cms.entity;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.apache.commons.lang.StringUtils;

import com.jspgou.cms.entity.base.BaseProductFashion;


/**
* This class should preserve.
* @preserve
*/
public class ProductFashion extends BaseProductFashion {
	private static final long serialVersionUID = 1L;

/*[CONSTRUCTOR MARKER BEGIN]*/
	public ProductFashion () {
		super();
	}

	/**
	 * Constructor for primary key
	 */
	public ProductFashion (java.lang.Long id) {
		super(id);
	}

	/**
	 * Constructor for required fields
	 */
	public ProductFashion (
		java.lang.Long id,
		java.lang.Boolean m_default) {

		super (
			id,
			m_default);
	}

	public void init() {
		if (getSaleCount() == null) {
			setSaleCount(0);
		}
		if (getStockCount() == null) {
			setStockCount(0);
		}
		if (getMarketPrice() == null) {
			setMarketPrice(0.0);
		}
		if (getSalePrice() == null) {
			setSalePrice(0.0);
		}
		if (getCostPrice() == null) {
			setCostPrice(0.0);
		}
		
		setCreateTime(new Timestamp(System.currentTimeMillis()));
	}
	
	
	
/*[CONSTRUCTOR MARKER END]*/

	public List<String> getPropertysName() {
		String propertys = this.getFashionStyle();
		List<String> t = new ArrayList<String>();
		String[] c = propertys.split("@");
		for (int i = 0; i < c.length; i++) {
			if(c[i].indexOf("??")!=-1){
				t.add(c[i].substring(0,c[i].indexOf("??")));
			}
		}
		return t;
	}
	public List<String> getPropertysValue() {
		String propertys = this.getFashionStyle();
		List<String> u = new ArrayList<String>();
		String[] c = propertys.split("@");
		for (int i = 0; i < c.length; i++) {
			if(c[i].indexOf("??")!=-1){
				u.add(c[i].substring(c[i].indexOf("??")+2));
			}
		}
		return u;
	}
	
	public String getFashPic(){//获得商品切换图片url
		return this.getImageUrl(this.getFashionPic());
	}
	
	private String getImageUrl(String img) {
		if (StringUtils.isBlank(img)) {
			return null;
		}
		return this.getProductId().getWebsite().getUploadUrl(img);
	}
	
	public void addTostandards(Standard standard) {
		Set<Standard> set = getStandards();
		if (set == null) {
			set = new HashSet<Standard>();
			setStandards(set);
		}
		set.add(standard);
		standard.addToProductFashions(this);
	}

}