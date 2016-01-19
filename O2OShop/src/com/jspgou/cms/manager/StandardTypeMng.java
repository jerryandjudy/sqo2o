package com.jspgou.cms.manager;

import java.util.List;

import com.jspgou.cms.entity.Standard;
import com.jspgou.cms.entity.StandardType;
import com.jspgou.common.page.Pagination;

/**
* This class should preserve.
* @preserve
*/
public interface StandardTypeMng {
	public Pagination getPage(int pageNo, int pageSize);
	
	public StandardType getByField(String field);

	public StandardType findById(Long id);
	
	public List<StandardType> getList();
	
	public List<StandardType> getList(Long categoryId);

	public StandardType save(StandardType bean);

	public StandardType update(StandardType bean);

	public StandardType deleteById(Long id);
	
	public StandardType[] deleteByIds(Long[] ids);

	public StandardType[] updatePriority(Long[] wids, Integer[] priority);
}