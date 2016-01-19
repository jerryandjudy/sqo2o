package com.jspgou.cms.web.threadvariable;

import com.jspgou.cms.entity.ShopMemberGroup;
/**
* This class should preserve.
* @preserve
*/
public class GroupThread {
	private static ThreadLocal<ShopMemberGroup> instance = new ThreadLocal<ShopMemberGroup>();

	public static ShopMemberGroup get() {
		return instance.get();
	}

	public static void set(ShopMemberGroup group) {
		instance.set(group);
	}

	public static void remove() {
		instance.remove();
	}
}
