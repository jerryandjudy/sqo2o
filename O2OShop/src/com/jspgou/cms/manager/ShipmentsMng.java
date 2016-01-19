package com.jspgou.cms.manager;

import java.util.List;

import com.jspgou.common.page.Pagination;
import com.jspgou.cms.entity.Shipments;
/**
* This class should preserve.
* @preserve
*/
public interface ShipmentsMng {
	public Pagination getPage(int pageNo, int pageSize);
	
	public List<Shipments> getlist(Long orderId);
	
	public void deleteByorderId(Long orderId);

	public Shipments findById(Long id);

	public Shipments save(Shipments bean);

	public Shipments update(Shipments bean);

	public Shipments deleteById(Long id);
	
	public Shipments[] deleteByIds(Long[] ids);
}