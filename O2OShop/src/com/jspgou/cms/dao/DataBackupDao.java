package com.jspgou.cms.dao;

import com.jspgou.common.hibernate3.Updater;
import com.jspgou.cms.entity.DataBackup;

/**
* This class should preserve.
* @preserve
*/
public interface DataBackupDao {
	
	public DataBackup updateByUpdater(Updater<DataBackup> updater);
	
	public DataBackup getDataBackup();
}

