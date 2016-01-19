package com.jspgou.cms.manager;

import java.util.List;

import com.jspgou.cms.entity.ProductStandard;
import com.jspgou.common.page.Pagination;
/**
* This class should preserve.
* @preserve
*/
public interface ProductStandardMng {
	public Pagination getPage(int pageNo, int pageSize);

	public ProductStandard findById(Long id);
	
	public ProductStandard findByProductIdAndStandardId(Long productId,Long standardId);
	
	public List<ProductStandard> findByProductId(Long productId);

	public ProductStandard save(ProductStandard bean);

	public ProductStandard update(ProductStandard bean);

	public ProductStandard deleteById(Long id);
	
	public ProductStandard[] deleteByIds(Long[] ids);
}