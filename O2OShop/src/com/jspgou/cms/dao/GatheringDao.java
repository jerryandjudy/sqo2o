package com.jspgou.cms.dao;

import java.util.List;

import com.jspgou.common.hibernate3.Finder;
import com.jspgou.common.hibernate3.Updater;
import com.jspgou.common.page.Pagination;
import com.jspgou.cms.entity.Gathering;

/**
* This class should preserve.
* @preserve
*/
public interface GatheringDao {
	public Pagination getPage(int pageNo, int pageSize);
	
	public List<Gathering> getlist(Long orderId);

	public Gathering findById(Long id);

	public Gathering save(Gathering bean);

	public Gathering updateByUpdater(Updater<Gathering> updater);

	public Gathering deleteById(Long id);
}