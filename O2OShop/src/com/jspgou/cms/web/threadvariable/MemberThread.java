 package com.jspgou.cms.web.threadvariable;

import com.jspgou.cms.entity.ShopMember;
/**
* This class should preserve.
* @preserve
*/
public class MemberThread {
	private static ThreadLocal<ShopMember> instance = new ThreadLocal<ShopMember>();

	public static ShopMember get() {
		return instance.get();
	}

	public static void set(ShopMember member) {
		instance.set(member);
	}

	public static void remove() {
		instance.remove();
	}
}
