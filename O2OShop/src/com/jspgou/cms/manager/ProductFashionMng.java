package com.jspgou.cms.manager;

import java.util.List;

import com.jspgou.cms.entity.ProductFashion;
import com.jspgou.common.page.Pagination;
/**
* This class should preserve.
* @preserve
*/
public interface ProductFashionMng {
	
//	public ProductFashion save(ProductFashion bean);
	
	public Pagination getPage(Long productId,int pageNo, int pageSize);
	
	public ProductFashion deleteById(Long id);
	
	public ProductFashion[] deleteByIds(Long[] ids);
	
	public ProductFashion findById(Long id);
	
	public List<ProductFashion> findByProductId(Long productId);
	
	public ProductFashion update(ProductFashion bean);
	
	public ProductFashion update(ProductFashion bean,String[] arr);
	
	public Integer productLackCount(Integer status,Integer count);
	
	public Pagination productLack(Integer status,Integer count,int pageNo, int pageSize);
	
	public Pagination getSaleTopPage(int pageNo, int pageSize);
	
	public ProductFashion save(ProductFashion bean,String[] arr);
	
	public Boolean getOneFash(Long productId);
	
	
	
	public void addStandard(ProductFashion bean,String[] arr);
	
	public void updateStandard(ProductFashion bean,String[] arr);
}

