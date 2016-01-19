package com.jspgou.cms.dao;

import java.util.List;

import com.jspgou.cms.entity.Bld;
import com.jspgou.cms.entity.Gys;
import com.jspgou.cms.entity.Order;
import com.jspgou.cms.entity.OrderItem;
import com.jspgou.common.hibernate3.Updater;
import com.jspgou.common.page.Pagination;

/**
* This class should preserve.
* @preserve
*/
public interface BldDao {
	public List<Bld> getAllList();

	public Bld findById(String id);
	public List<Bld> getBldBySiteAndPsqy(Long siteId,Long province,Long city,Long country,Long street);
	public Bld findByIsDef(Boolean isDef,Long siteId);
	public Bld save(Bld bean);
	public Bld getBldByOrderAndOrderItem(Order order,OrderItem orderItem);
	public Bld updateByUpdater(Updater<Bld> updater);
	public Bld findByKetaUserId(Long id);
	public Bld deleteById(String id);
	public Pagination getPageBySiteId(Long siteId, int pageNo, int pageSize);
	public Pagination getPageByIsDisabled(Boolean isDisabled, int pageNo, int pageSize);
	public Pagination getPageByIsDisabled(String organizationName,Long siteId,String username,String companyName,Boolean isDisabled, int pageNo, int pageSize);
	public Pagination getPageByGysNotHas(Bld bld,Gys gys, int pageNo, int pageSize);
	public Pagination getBldByOrder(Order order, int pageNo, int pageSize);
	public Bld getBldByOrder(Order order,String bldId);
	public List<Bld> getBldByOrder(com.jspgou.cms.entity.Order order);
}