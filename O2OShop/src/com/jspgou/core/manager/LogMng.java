package com.jspgou.core.manager;

import com.jspgou.common.page.Pagination;
import com.jspgou.core.entity.Log;
/**
* This class should preserve.
* @preserve
*/
public interface LogMng {
	public Pagination getPage(int pageNo, int pageSize);

	public Log findById(Long id);

	public Log save(Log bean);
	
	public void save(String versions,String updatelog);

	public Log update(Log bean);

	public Log deleteById(Long id);
	
	public Log[] deleteByIds(Long[] ids);
}