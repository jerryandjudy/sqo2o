package com.jspgou.cms.manager;

import com.jspgou.cms.entity.Brand;
import com.jspgou.cms.entity.BrandText;

/**
* This class should preserve.
* @preserve
*/
public interface BrandTextMng {
	public BrandText save(Brand brand, String text);

	public BrandText update(BrandText bean);
}