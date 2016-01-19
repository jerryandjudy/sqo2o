package com.jspgou.cms.dao;

import java.util.List;

import com.jspgou.common.hibernate3.Updater;
import com.jspgou.common.page.Pagination;
import com.jspgou.cms.entity.ProductFashion;

/**
* This class should preserve.
* @preserve
*/
public interface ProductFashionDao {
	
	public Pagination getPage(Long productId, int pageNo, int pageSize);
	
	public ProductFashion save(ProductFashion bean);
	
	public ProductFashion deleteById(Long id);
	
	public ProductFashion updateByUpdater(Updater<ProductFashion> updater);
	
	public ProductFashion findById(Long id);
	
	public List<ProductFashion> findByProductId(Long productId);
	
	public Integer productLackCount(Integer status,Integer count);
	
	public Pagination productLack(Integer status,Integer count,int pageNo, int pageSize);
	
	public Pagination getSaleTopPage(int pageNo,int pageSize);
	
	public ProductFashion getPfashion(Long productId,Long fashId);
	
	public Boolean getOneFashion(Long productId);
};