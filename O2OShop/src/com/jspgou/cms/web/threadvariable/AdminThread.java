package com.jspgou.cms.web.threadvariable;

import com.jspgou.cms.entity.ShopAdmin;
/**
* This class should preserve.
* @preserve
*/
public class AdminThread {
	private static ThreadLocal<ShopAdmin> instance = new ThreadLocal<ShopAdmin>();

	public static ShopAdmin get() {
		return instance.get();
	}

	public static void set(ShopAdmin group) {
		instance.set(group);
	}

	public static void remove() {
		instance.remove();
	}
}
