package com.jspgou.cms.manager;

import java.util.List;

import com.jspgou.common.page.Pagination;
import com.jspgou.cms.entity.Gathering;
/**
* This class should preserve.
* @preserve
*/
public interface GatheringMng {
	public Pagination getPage(int pageNo, int pageSize);
	
	public List<Gathering> getlist(Long orderId);
	
	public void deleteByorderId(Long orderId);

	public Gathering findById(Long id);

	public Gathering save(Gathering bean);

	public Gathering update(Gathering bean);

	public Gathering deleteById(Long id);
	
	public Gathering[] deleteByIds(Long[] ids);
}