package com.jspgou.cms.entity;

import java.util.HashSet;
import java.util.Set;

import com.jspgou.cms.entity.base.BaseExended;


/**
* This class should preserve.
* @preserve
*/
public class Exended extends BaseExended {
	private static final long serialVersionUID = 1L;

/*[CONSTRUCTOR MARKER BEGIN]*/
	public Exended () {
		super();
	}

	/**
	 * Constructor for primary key
	 */
	public Exended (java.lang.Long id) {
		super(id);
	}

	/**
	 * Constructor for required fields
	 */
	public Exended (
		java.lang.Long id,
		java.lang.String name,
		java.lang.String field) {

		super (
			id,
			name,
			field);
	}

	public void addToProductTypes(ProductType pType){
		Set<ProductType> types=getProductTypes();
		if(types==null){
			types=new HashSet<ProductType>();
			setProductTypes(types);
		}
		types.add(pType);
	}
	
	
	public Long[] getProductTypeIds() {
		Set<ProductType> set = getProductTypes();
		if (set == null) {
			return null;
		} else {
			Long[] ids = new Long[set.size()];
			int i = 0;
			for (ProductType productType : set) {
				ids[i] = productType.getId();
				i++;
			}
			return ids;
		}
	}
/*[CONSTRUCTOR MARKER END]*/


}