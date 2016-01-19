package com.jspgou.cms.dao;

import java.util.List;

import com.jspgou.common.hibernate3.Updater;
import com.jspgou.common.page.Pagination;
import com.jspgou.cms.entity.ProductTypeProperty;

/**
* This class should preserve.
* @preserve
*/
public interface ProductTypePropertyDao {
	public Pagination getList(int pageNo,int pageSize,Long typeid,Boolean isCategory,String searchType,String searchContent);

	public List<ProductTypeProperty> getList(Long typeId,Boolean isCategory);
	
	public ProductTypeProperty findById(Long id);

	public ProductTypeProperty save(ProductTypeProperty bean);

	public ProductTypeProperty updateByUpdater(Updater<ProductTypeProperty> updater);

	public ProductTypeProperty deleteById(Long id);
	
	public List<ProductTypeProperty> findBySearch(String searchType,String searchContent);
	
	public List<ProductTypeProperty> findListByTypeId(Long typeid);
	
	public List<ProductTypeProperty> getList(Long typeId,boolean isCategory);
}