package com.jspgou.cms.manager;

import com.jspgou.cms.entity.Logistics;
import com.jspgou.cms.entity.LogisticsText;
/**
* This class should preserve.
* @preserve
*/
public interface LogisticsTextMng {
	public LogisticsText save(Logistics logistics, String text);

	public LogisticsText update(LogisticsText bean);
}