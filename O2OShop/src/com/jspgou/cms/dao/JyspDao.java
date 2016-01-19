package com.jspgou.cms.dao;

import java.util.List;

import com.jspgou.cms.entity.Gys;
import com.jspgou.cms.entity.Jysp;
import com.jspgou.cms.entity.Product;
import com.jspgou.common.hibernate3.Updater;
import com.jspgou.common.page.Pagination;

/**
* This class should preserve.
* @preserve
*/
public interface JyspDao {
	public List<Jysp> getAllList();
	public List<Jysp> getChildListByIdAndSiteId(Product proParent,Long id,Long siteId);
	public Jysp findById(Long id);
	public Jysp findByProductIdAndKetaUserId(Long productId,Long ketaUserId);
	public List<Gys> findGysByProductId(Long productId);
	public List<Jysp> findByProductId(Long parentId,Long productId);
	public Jysp save(Jysp bean);

	public Jysp updateByUpdater(Updater<Jysp> updater);

	public Jysp deleteById(Long id);
	public Pagination getPageByIsDisabled(Boolean isDisabled, int pageNo, int pageSize);
	public Pagination getPageByIsDisabled(Long siteId,String username,String companyName,Boolean isDisabled, int pageNo, int pageSize);
	public Pagination getPageByGysId(Gys gys, int pageNo, int pageSize);
	public Pagination getJyspPage(Long siteId, String username,
			String companyName,String productName,Long typeId, int pageNo, int pageSize);	
	
}