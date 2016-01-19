package com.jspgou.cms.dao;

import static com.jspgou.common.page.SimplePage.cpn;

import java.util.List;

import com.jspgou.cms.entity.Accountcz;
import com.jspgou.cms.entity.KetaUser;
import com.jspgou.common.hibernate3.Updater;
import com.jspgou.common.page.Pagination;
import com.jspgou.common.web.CookieUtils;

/**
* This class should preserve.
* @preserve
*/
public interface AccountczDao {
	public List<Accountcz> getAllList();

	public Accountcz findById(Long id);

	public Pagination findAllUsers(Long siteId,Long organizationId, int pageNo, int pageSize);
	
	public Accountcz save(Accountcz bean);

	public Accountcz updateByUpdater(Updater<Accountcz> updater);

	public Accountcz deleteById(Long id);
	public Pagination getPage(String payStatus,String username,String siteId,Long organizationId,String createTime, int pageNo, int pageSize);


	public Pagination getPage(String  queryUsername, String  queryName,  int pageNo,  String jmftypeIdstr, int pageSize);
	
}