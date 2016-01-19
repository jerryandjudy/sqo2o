package com.jspgou.cms.manager;

import java.util.List;

import com.jspgou.cms.entity.Organization;
import com.jspgou.common.hibernate3.Updater;
import com.jspgou.common.page.Pagination;

/**
* This class should preserve.
* @preserve
*/
public interface OrganizationMng {
	public List<Organization> getAllList();

	public Organization findById(Long id);
	public Organization findByName(String  name);

	
	public Organization save(Organization bean);

	public Organization updateByUpdater(Organization bean);

	public Organization deleteById(Long id);
	public Pagination getPageByIsDisabled(Boolean isDisabled, int pageNo, int pageSize);	
}