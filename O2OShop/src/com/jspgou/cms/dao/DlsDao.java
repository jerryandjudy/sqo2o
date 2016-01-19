package com.jspgou.cms.dao;

import java.util.List;

import com.jspgou.cms.entity.Dls;
import com.jspgou.common.hibernate3.Updater;
import com.jspgou.common.page.Pagination;

/**
* This class should preserve.
* @preserve
*/
public interface DlsDao {
	public List<Dls> getAllList();

	public Dls findById(String id);

	
	public Dls save(Dls bean);

	public Dls updateByUpdater(Updater<Dls> updater);

	public Dls deleteById(String id);
	public Pagination getPageByIsDisabled(Boolean isDisabled, int pageNo, int pageSize);
	
	public Dls findByKetaUserId(String id);
	
	
	
}