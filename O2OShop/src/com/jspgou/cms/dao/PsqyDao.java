package com.jspgou.cms.dao;

import java.util.List;

import com.jspgou.cms.entity.Bld;
import com.jspgou.cms.entity.Gys;
import com.jspgou.cms.entity.Psqy;
import com.jspgou.common.hibernate3.Updater;
import com.jspgou.common.page.Pagination;

/**
* This class should preserve.
* @preserve
*/
public interface PsqyDao {
	public List<Psqy> getAllList();

	public Psqy findById(Long id);

	
	public Psqy save(Psqy bean);


	public Psqy deleteById(Long id);
	public Pagination getGysPage( int pageNo, int pageSize);
	public Pagination getGysPage(Long siteId,String username,String companyName, int pageNo, int pageSize);
	public Pagination getBldPage( int pageNo, int pageSize);
	public Pagination getBldPage(Long siteId,String username,String companyName, int pageNo, int pageSize);
	public Pagination getGysPageByGys(Gys gys,String username,String companyName, int pageNo, int pageSize);
	public Pagination getBldPageByBld(Bld bld, String username,
			String companyName, int pageNo, int pageSize);
	
	
}