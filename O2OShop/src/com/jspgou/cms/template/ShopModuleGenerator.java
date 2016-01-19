package com.jspgou.cms.template;

import com.jspgou.common.developer.ModuleGenerator;

public class ShopModuleGenerator {
	private static String packName = "com.jspgou.cms.template";
	private static String fileName = "jspgou.properties";

	public static void main(String[] args) {
		new ModuleGenerator(packName, fileName).generate();
	}
}
