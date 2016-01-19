package com.jspgou.cms.dao;

import java.util.List;

import com.jspgou.cms.entity.Fws;
import com.jspgou.cms.entity.Gys;
import com.jspgou.cms.entity.Order;
import com.jspgou.cms.entity.OrderItem;
import com.jspgou.common.hibernate3.Updater;
import com.jspgou.common.page.Pagination;

/**
* This class should preserve.
* @preserve
*/
public interface FwsDao {
	public List<Fws> getAllList();

	public Fws findById(String id);
	public List<Fws> getFwsBySiteAndPsqy(Long siteId,Long province,Long city,Long country,Long street);
	public Fws findByIsDef(Boolean isDef,Long siteId);
	public Fws save(Fws bean);
	public Fws updateByUpdater(Updater<Fws> updater);
	public Fws findByKetaUserId(Long id);
	public Fws deleteById(String id);
	public Pagination getPageBySiteId(Long siteId, int pageNo, int pageSize);
	public Pagination getPageByIsDisabled(Boolean isDisabled, int pageNo, int pageSize);
	public Pagination getPageByIsDisabled(String organizationName,Long siteId,String username,String companyName,Boolean isDisabled, int pageNo, int pageSize);
}