package com.jspgou.cms.manager;

import java.util.List;

import com.jspgou.cms.entity.Bld;
import com.jspgou.cms.entity.Gys;
import com.jspgou.cms.entity.KetaUser;
import com.jspgou.cms.entity.Order;
import com.jspgou.cms.entity.OrderItem;
import com.jspgou.common.hibernate3.Updater;
import com.jspgou.common.page.Pagination;
import com.jspgou.core.entity.Account;
import com.jspgou.core.entity.Website;

/**
* This class should preserve.
* @preserve
*/
public interface GysMng {
	public List<Gys> getAllList();
	public List<Website> getListNotHasGys();
	public List<Gys> getListBySiteIdAndProductSiteId(Long siteId,Long buyProductSiteId,Long giveProductSiteId);
	public Gys getGysByOrderAndOrderItem(Order order,OrderItem orderItem);
	public Gys findById(String id);
	public Gys findByKetaUserId(Long id);
	public List<Gys> getGysByOrder(Order order);
	
	public Gys save(Gys bean);
	public Gys register(Gys bean,Account accounts,KetaUser ketaUser);
	public Gys save(Gys bean,String[]picPaths, String[] picDescs);

	public Gys updateByUpdater(Gys bean);
	public Gys updateByUpdater(Gys bean,String[]picPaths, String[] picDescs);
	public Gys getGysByOrder(Order order,String gysId);
	public Gys deleteById(String id);
	public Pagination getPageByIsDisabled(Boolean isDisabled, int pageNo, int pageSize);	
	public Pagination getPageByIsDisabled(String organizationName,Long siteId,String username,String companyName,Boolean isDisabled, int pageNo, int pageSize);	
	public Pagination getPageBySiteAndNotGysId(String username,String companyName,Long siteId,String id, int pageNo, int pageSize);	
}