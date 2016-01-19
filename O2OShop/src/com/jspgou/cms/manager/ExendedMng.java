package com.jspgou.cms.manager;

import java.util.List;

import com.jspgou.cms.entity.Exended;
import com.jspgou.common.page.Pagination;
/**
* This class should preserve.
* @preserve
*/
public interface ExendedMng {
	public Pagination getPage(int pageNo, int pageSize);

	public Exended findById(Long id);
	
	public List<Exended> getList();
	
	public List<Exended> getList(Long typeId);

	public Exended save(Exended bean,Long[] typeIds);

	public Exended update(Exended bean,Long[] typeIds);

	public Exended deleteById(Long id);
	
	public Exended[] deleteByIds(Long[] ids);

	public Exended[] updatePriority(Long[] wids, Integer[] priority);
}