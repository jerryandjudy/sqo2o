package com.jspgou.cms.entity;

import java.util.ArrayList;
import java.util.List;

import com.jspgou.cms.entity.base.BaseProductExt;
/**
* This class should preserve.
* @preserve
*/
public class ProductExt extends BaseProductExt {
	private static final long serialVersionUID = 1L;

	public void init() {
		if (getWeight() == null) {
			setWeight(0);
		}
		if (getUnit() == null) {
			setUnit("");
		}
	}

	/* [CONSTRUCTOR MARKER BEGIN] */
	public ProductExt () {
		super();
	}

	/**
	 * Constructor for primary key
	 */
	public ProductExt (java.lang.Long id) {
		super(id);
	}

	/**
	 * Constructor for required fields
	 */
	public ProductExt (
		java.lang.Long id,
		java.lang.Integer weight,
		java.lang.String unit) {

		super (
			id,
			weight,
			unit);
	}
	
	public List<String> getImgs() {
		String imgs = this.getProductImgs();
		List<String> t = new ArrayList<String>();
		if(imgs!=null && !imgs.equals("")){
			String[] c = imgs.split("@@");
			for (int i = 0; i < c.length; i++) {
				if(c[i].indexOf("/")!=-1){
					t.add(c[i]);
				}
			}
		}
		
		return t;
	}
	
	public List<String> getImgsDesc() {
		String imgs = this.getProductImgDesc();
		List<String> t = new ArrayList<String>();
		String[] c = imgs.split("@@");
		for (int i = 0; i < c.length; i++) {
//			if(c[i].indexOf("/")!=-1){
				t.add(c[i]);
//			}
		}
		return t;
	}
}