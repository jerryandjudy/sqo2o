package com.jspgou.cms.entity;

import com.jspgou.cms.entity.base.BaseProductTag;
/**
* This class should preserve.
* @preserve
*/
public class ProductTag extends BaseProductTag {
	private static final long serialVersionUID = 1L;

	/**
	 * 商品数量加1
	 */
	public void increaseCount() {
		addCount(1);
	}

	/**
	 * 商品数量减1
	 */
	public void reduceCount() {
		addCount(-1);
	}

	/**
	 * 增加商品数量
	 * 
	 * @param count
	 */
	public void addCount(int count) {
		Integer c = getCount();
		if (c != null) {
			count += c;
		}
		if (count < 0) {
			count = 0;
		}
		setCount(count);
	}

	/**
	 * 初始化对象
	 */
	public void init() {
		setCount(0);
	}

	/* [CONSTRUCTOR MARKER BEGIN] */
	public ProductTag () {
		super();
	}

	/**
	 * Constructor for primary key
	 */
	public ProductTag (java.lang.Long id) {
		super(id);
	}

	/**
	 * Constructor for required fields
	 */
	public ProductTag (
		java.lang.Long id,
		com.jspgou.core.entity.Website website,
		java.lang.String name,
		java.lang.Integer count) {

		super (
			id,
			website,
			name,
			count);
	}

	/* [CONSTRUCTOR MARKER END] */
}