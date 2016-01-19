package com.jspgou.cms.entity;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import com.jspgou.cms.entity.base.BaseStandardType;

/**
* This class should preserve.
* @preserve
*/
public class StandardType extends BaseStandardType {
	private static final long serialVersionUID = 1L;
	
	/**
	 * 从集合中提取ID数组
	 * 
	 * @param groups
	 * @return
	 */
	public static Long[] fetchIds(Collection<StandardType> standardTypes) {
		Long[] ids = new Long[standardTypes.size()];
		int i = 0;
		for (StandardType sdt : standardTypes) {
			ids[i++] = sdt.getId();
		}
		return ids;
	}
	
	public void removeFromCategorys(Category category) {
		Set<Category> set = getCategorys();
		if (set != null) {
			set.remove(category);
		}
	}
	
	public void addToCategory(Category category) {
		Set<Category> set = getCategorys();
		if (set == null) {
			set = new HashSet<Category>();
			this.setCategorys(set);
		}
		set.add(category);
	}

/*[CONSTRUCTOR MARKER BEGIN]*/
	public StandardType () {
		super();
	}

	/**
	 * Constructor for primary key
	 */
	public StandardType (java.lang.Long id) {
		super(id);
	}

	/**
	 * Constructor for required fields
	 */
	public StandardType (
		java.lang.Long id,
		java.lang.String name,
		java.lang.String field,
		boolean dataType) {

		super (
			id,
			name,
			field,
			dataType);
	}

/*[CONSTRUCTOR MARKER END]*/


}