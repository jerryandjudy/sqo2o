package com.jspgou.cms.manager;

import java.util.List;

import com.jspgou.cms.entity.Dls;
import com.jspgou.common.hibernate3.Updater;
import com.jspgou.common.page.Pagination;
import com.jspgou.core.entity.Website;

/**
* This class should preserve.
* @preserve
*/
public interface DlsMng {
	public List<Dls> getAllList();
	public List<Website> getListNotHasDls();

	public Dls findById(String id);

	
	public Dls save(Dls bean);

	public Dls updateByUpdater(Dls bean);

	public Dls deleteById(String id);
	public Pagination getPageByIsDisabled(Boolean isDisabled, int pageNo, int pageSize);
	
	public Dls findByKetaUserId(String id);
	
}