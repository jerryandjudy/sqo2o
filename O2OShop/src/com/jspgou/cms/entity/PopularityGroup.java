package com.jspgou.cms.entity;

import java.util.HashSet;
import java.util.Set;

import com.jspgou.cms.entity.base.BasePopularityGroup;



public class PopularityGroup extends BasePopularityGroup {
	private static final long serialVersionUID = 1L;

	
	
	public void init() {
		if (getPrice () == null) {
			setPrice(0.0);
		}
		if (getPrivilege() == null) {
			setPrivilege(0.0);
		}
	}
	
	
	public void addToProducts(Product product) {
		if (product == null) {
			return;
		}
		Set<Product> set = getProducts();
		if (set == null) {
			set = new HashSet<Product>();
			setProducts(set);
		}
		set.add(product);
	}
	
/*[CONSTRUCTOR MARKER BEGIN]*/
	public PopularityGroup () {
		super();
	}

	/**
	 * Constructor for primary key
	 */
	public PopularityGroup (java.lang.Long id) {
		super(id);
	}

	/**
	 * Constructor for required fields
	 */
	public PopularityGroup (
		java.lang.Long id,
		java.lang.String name,
		java.lang.Double price,
		java.lang.Double privilege) {

		super (
			id,
			name,
			price,
			privilege);
	}

/*[CONSTRUCTOR MARKER END]*/


}