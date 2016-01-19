package com.jspgou.cms.manager;

import java.util.List;

import com.jspgou.cms.entity.Bld;
import com.jspgou.cms.entity.Dls;
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
public interface BldMng {
	public List<Bld> getAllList();
	public List<Website> getListNotHasBld();
	public List<Bld> getBldBySiteAndPsqy(Long siteId,Long province,Long city,Long country,Long street);
	public List<Bld> getBldByOrder(Order order);
	public Pagination getBldByOrder(Order order, int pageNo, int pageSize);
	public Bld getBldByOrder(Order order,String bldId);
	public Bld getBldByOrderAndOrderItem(Order order,OrderItem orderItem);
	public Bld findById(String id);
	public Bld findByIsDef(Boolean isDef,Long siteId);
	public Bld findByKetaUserId(Long id);
	
	public Bld save(Bld bean);
	public Bld register(Bld bean,Account accounts,KetaUser ketaUser);

	public Bld updateByUpdater(Bld bean);

	public Bld deleteById(String id);
	public Pagination getPageByIsDisabled(Boolean isDisabled, int pageNo, int pageSize);	
	public Pagination getPageByIsDisabled(String organizationName,Long siteId,String username,String companyName,Boolean isDisabled, int pageNo, int pageSize);	
	public Pagination getPageBySiteId(Long siteId, int pageNo, int pageSize);	
	public Pagination getPageByGysNotHas(Bld bld,Gys gys, int pageNo, int pageSize);	
}