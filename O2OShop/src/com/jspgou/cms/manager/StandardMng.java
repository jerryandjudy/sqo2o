package com.jspgou.cms.manager;

import java.util.List;

import com.jspgou.common.page.Pagination;
import com.jspgou.cms.entity.Standard;
/**
* This class should preserve.
* @preserve
*/
public interface StandardMng {
	public Pagination getPage(int pageNo, int pageSize);

	public Standard findById(Long id);
	
	public List<Standard> findByTypeId(Long typeId);

	public Standard save(Standard bean);

	public Standard update(Standard bean);

	public Standard deleteById(Long id);
	
	public Standard[] deleteByIds(Long[] ids);
	
	public Standard[] updatePriority(Long[] ids, Integer[] priority);
}