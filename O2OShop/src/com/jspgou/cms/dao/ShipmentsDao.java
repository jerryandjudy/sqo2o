package com.jspgou.cms.dao;

import java.util.List;

import com.jspgou.common.hibernate3.Updater;
import com.jspgou.common.page.Pagination;
import com.jspgou.cms.entity.Shipments;

/**
* This class should preserve.
* @preserve
*/
public interface ShipmentsDao {
	public Pagination getPage(int pageNo, int pageSize);
	
	public List<Shipments> getlist(Long orderId);

	public Shipments findById(Long id);

	public Shipments save(Shipments bean);

	public Shipments updateByUpdater(Updater<Shipments> updater);

	public Shipments deleteById(Long id);
}