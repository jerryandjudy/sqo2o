package com.jspgou.cms.dao;

import java.util.List;

import com.jspgou.common.hibernate3.Updater;
import com.jspgou.common.page.Pagination;
import com.jspgou.core.entity.Website;
import com.jspgou.cms.entity.Advertise;

/**
* This class should preserve.
* @preserve
*/
public interface AdvertiseDao {
	public Pagination getPage(Long categoryId, Integer adspaceId,
			Boolean enabled, int pageNo, int pageSize,Integer count);
	public Pagination getPage(Website website, Integer adspaceId,
			Boolean enabled, int pageNo, int pageSize,Integer count);

	public List<Advertise> getList(Integer adspaceId, Boolean enabled);

	public Advertise findById(Integer id);

	public Advertise save(Advertise bean);

	public Advertise updateByUpdater(Updater<Advertise> updater);

	public Advertise deleteById(Integer id);
}