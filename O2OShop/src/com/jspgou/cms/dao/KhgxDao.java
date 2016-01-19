package com.jspgou.cms.dao;

import java.util.List;

import com.jspgou.cms.entity.Gys;
import com.jspgou.cms.entity.Khgx;
import com.jspgou.common.hibernate3.Updater;
import com.jspgou.common.page.Pagination;

/**
* This class should preserve.
* @preserve
*/
public interface KhgxDao {

	public Khgx findById(Long id);

	
	public Khgx save(Khgx bean);

	public Khgx updateByUpdater(Updater<Khgx> updater);
	public List getListByGysAndBldId(Gys gys,String[] ids);
	public Khgx deleteById(Long id);
	public Pagination getPage(Khgx khgx, int pageNo, int pageSize);
	
	
	
}