package com.jspgou.cms.manager;

import com.jspgou.cms.entity.Product;
import com.jspgou.cms.entity.ProductExt;
/**
* This class should preserve.
* @preserve
*/
public interface ProductExtMng {
	public ProductExt save(ProductExt ext, Product product);

	public ProductExt saveOrUpdate(ProductExt ext, Product product);
}