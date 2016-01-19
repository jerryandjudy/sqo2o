package com.jspgou.cms.dao;

import java.util.List;

import com.jspgou.common.hibernate3.Updater;
import com.jspgou.cms.entity.Adspace;

/**
* This class should preserve.
* @preserve
*/
public interface AdspaceDao {
	public Adspace findById(Integer id);

	public Adspace save(Adspace bean);

	public Adspace updateByUpdater(Updater<Adspace> updater);

	public Adspace deleteById(Integer id);
	public List<Adspace> getList();
}