package com.jspgou.cms.dao;

import java.util.List;

import com.jspgou.cms.entity.Logistics;
import com.jspgou.common.hibernate3.Updater;

/**
* This class should preserve.
* @preserve
*/
public interface LogisticsDao {
	public List<Logistics> getAllList();

	public Logistics findById(Long id);

	public Logistics save(Logistics bean);

	public Logistics updateByUpdater(Updater<Logistics> updater);

	public Logistics deleteById(Long id);
	
	
}