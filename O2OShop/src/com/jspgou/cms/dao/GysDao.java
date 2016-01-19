package com.jspgou.cms.dao;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import com.jspgou.cms.entity.Gys;
import com.jspgou.cms.entity.Order;
import com.jspgou.cms.entity.OrderItem;
import com.jspgou.common.hibernate3.Updater;
import com.jspgou.common.page.Pagination;

/**
* This class should preserve.
* @preserve
*/
public interface GysDao {
	public List<Gys> getAllList();
    public List<Gys> getListBySiteIdAndProductSiteId(Long siteId,Long buyProductSiteId,Long giveProductSiteId);
	public Gys findById(String id);
	public Gys findByKetaUserId(Long id) ;
	public Gys getGysByOrder(Order order,String gysId);
	public List<Gys> getGysByOrder(Order order);
	public Gys save(Gys bean);
	public Gys getGysByOrderAndOrderItem(Order order,OrderItem orderItem);
	public Gys updateByUpdater(Updater<Gys> updater);
	public Pagination getPageBySiteAndNotGysId(String username,String companyName,Long siteId,String id, int pageNo, int pageSize);
	public Gys deleteById(String id);
	public Pagination getPageByIsDisabled(Boolean isDisabled, int pageNo, int pageSize);
	public Pagination getPageByIsDisabled(String organizationName,Long siteId,String username,String companyName,Boolean isDisabled, int pageNo, int pageSize);
	
	
	
}