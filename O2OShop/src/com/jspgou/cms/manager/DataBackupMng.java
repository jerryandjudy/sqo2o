
/**
 * @author	胡涛
 *@version 创建时间：2012-2-1 下午03:08:17

 * 
 */
package com.jspgou.cms.manager;

import com.jspgou.cms.entity.DataBackup;
/**
* This class should preserve.
* @preserve
*/
public interface DataBackupMng {
	public DataBackup update(DataBackup bean);
	public DataBackup getDataBackup();
}

