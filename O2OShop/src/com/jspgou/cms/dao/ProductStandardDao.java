package com.jspgou.cms.dao;

import java.util.List;

import com.jspgou.common.hibernate3.Updater;
import com.jspgou.common.page.Pagination;
import com.jspgou.cms.entity.ProductStandard;

/**
* This class should preserve.
* @preserve
*/
public interface ProductStandardDao {
	public Pagination getPage(int pageNo, int pageSize);

	public ProductStandard findById(Long id);
	
	public List<ProductStandard> findByProductIdAndStandardId(Long productId,Long standardId);
	
	public List<ProductStandard> findByProductId(Long productId);

	public ProductStandard save(ProductStandard bean);

	public ProductStandard updateByUpdater(Updater<ProductStandard> updater);

	public ProductStandard deleteById(Long id);
}