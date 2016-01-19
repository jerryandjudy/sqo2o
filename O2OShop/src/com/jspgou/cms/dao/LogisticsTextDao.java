package com.jspgou.cms.dao;

import com.jspgou.cms.entity.LogisticsText;
import com.jspgou.common.hibernate3.Updater;

/**
* This class should preserve.
* @preserve
*/
public interface LogisticsTextDao {
	public LogisticsText save(LogisticsText bean);

	public LogisticsText updateByUpdater(Updater<LogisticsText> updater);
}