package com.jspgou.cms.dao;

import java.util.List;

import com.jspgou.cms.entity.Lsfc;
import com.jspgou.cms.entity.ShopOrderType;
import com.jspgou.common.hibernate3.Updater;
import com.jspgou.common.page.Pagination;

/**
* This class should preserve.
* @preserve
*/
public interface LsfcDao {
	public List<Lsfc> getAllList();

	public Lsfc findById(Long id);

	public Lsfc findByFctypeId(Long fctypeId,  Long websiteId);
	
	public Lsfc save(Lsfc bean);

	public Lsfc updateByUpdater(Updater<Lsfc> updater);

	public Lsfc deleteById(Long id);
	
	public Pagination getPageByIsDisabled(Boolean isDisabled, int pageNo, int pageSize);
	
	public Pagination getPageByIsDisabledAndWebsiteId(Long websiteId, Boolean isDisabled, int pageNo, int pageSize);
	
	public Lsfc changeIsDisabledById(Long id);
	
	
}