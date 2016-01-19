package com.jspgou.cms.dao;

import com.jspgou.common.hibernate3.Updater;
import com.jspgou.common.page.Pagination;
import com.jspgou.cms.entity.Gift;

/**
* This class should preserve.
* @preserve
*/
public interface GiftDao {
	
	public Pagination getPageGift( int pageNo, int pageSize) ;
	
	public Gift findById(Long id);

	public Gift save(Gift bean);

	public Gift updateByUpdater(Updater<Gift> updater);

	public Gift deleteById(Long id);
}