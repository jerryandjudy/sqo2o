package com.jspgou.core.dao;

import com.jspgou.common.hibernate3.Updater;
import com.jspgou.common.page.Pagination;
import com.jspgou.core.entity.Log;
/**
* This class should preserve.
* @preserve
*/
public interface LogDao {
	public Pagination getPage(int pageNo, int pageSize);

	public Log findById(Long id);

	public Log save(Log bean);

	public Log updateByUpdater(Updater<Log> updater);

	public Log deleteById(Long id);
}