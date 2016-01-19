package com.jspgou.cms.entity;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

import com.jspgou.cms.entity.base.BaseBrand;

/**
* This class should preserve.
* @preserve
*/
public class Brand extends BaseBrand {
	private static final long serialVersionUID = 1L;

	/**
	 * 获得品牌详细信息
	 * 
	 * @return
	 */
	public String getText() {
		BrandText brandText = getBrandText();
		if (brandText != null) {
			return brandText.getText();
		}
		return null;
	}

	public BrandText getBrandText() {
		Set<BrandText> set = getBrandTextSet();
		if (set != null) {
			Iterator<BrandText> it = set.iterator();
			if (it.hasNext()) {
				return it.next();
			}
		}
		return null;
	}

	

	
	
	/**
	 * 从categorys中删除(wang ze wu)
	 * 
	 * @param type
	 */
	public void removeFromCategorys(Category category) {
		Set<Category> set = this.getCategorys();
		if (set != null) {
			set.remove(category);
		}
	}
	
	/**
	 * 添加至categorys(wang ze wu)
	 * 
	 * @param type
	 */
	public void addToCategory(Category category) {
		Set<Category> set = getCategorys();
		if (set == null) {
			set = new HashSet<Category>();
			this.setCategorys(set);
		}
		set.add(category);
	}

	

	/* [CONSTRUCTOR MARKER BEGIN] */
	public Brand () {
		super();
	}

	/**
	 * Constructor for primary key
	 */
	public Brand (java.lang.Long id) {
		super(id);
	}

	/**
	 * Constructor for required fields
	 */
	public Brand (
		java.lang.Long id,
		com.jspgou.core.entity.Website website,
		java.lang.String name,
		java.lang.Integer priority) {

		super (
			id,
			website,
			name,
			priority);
	}

	/* [CONSTRUCTOR MARKER END] */

}