package com.jspgou.cms.dao;

import java.util.List;

import com.jspgou.cms.entity.Jmf;
import com.jspgou.common.hibernate3.Updater;
import com.jspgou.common.page.Pagination;

/**
* This class should preserve.
* @preserve
*/
public interface JmfDao {
	public List<Jmf> getAllList();

	public Jmf findById(Long id);

	public Jmf findByJmftypeId(Long jmftypeId,  Long websiteId);
	
	public Jmf save(Jmf bean);

	public Jmf updateByUpdater(Updater<Jmf> updater);

	public Jmf deleteById(Long id);
	
	public Pagination getPageByIsDisabled(Boolean isDisabled, int pageNo, int pageSize);
	
	public Pagination getPageByIsDisabledAndWebsiteId(Long websiteId, Boolean isDisabled, int pageNo, int pageSize);
	
	public Jmf changeIsDisabledById(Long id);
	
	
}