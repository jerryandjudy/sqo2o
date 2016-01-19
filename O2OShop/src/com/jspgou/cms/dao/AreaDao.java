/**
* 吉林省艾利特信息技术有限公司
* O2OShop系统
* @date 2015年6月10日
* @author liuwang
* @version 1.0
*/
package com.jspgou.cms.dao;

import com.jspgou.cms.entity.Area;
import com.jspgou.common.hibernate3.Updater;

public interface AreaDao {
	public Area findByImei(String imei);

	public Area save(Area bean);

	public Area updateByUpdater(Updater<Area> updater);
}
