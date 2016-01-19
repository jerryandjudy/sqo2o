package com.jspgou.cms.manager;

import static com.jspgou.common.page.SimplePage.cpn;

import java.util.List;

import com.jspgou.cms.entity.Accountcz;
import com.jspgou.common.hibernate3.Updater;
import com.jspgou.common.page.Pagination;
import com.jspgou.common.web.CookieUtils;
import com.jspgou.core.entity.Website;

/**
* This class should preserve.
* @preserve
*/
public interface AccountczMng {
	public List<Accountcz> getAllList();
	public Accountcz czSuccess(Accountcz bean) ;
	public Accountcz findById(Long id);
	
	
	
	public Accountcz save(Accountcz bean);

	public Accountcz updateByUpdater(Accountcz bean);

	public Accountcz deleteById(Long id);
	public Pagination getPage(String payStatus,String username,String siteId,Long organizationId,String createTime, int pageNo, int pageSize);	
	
	public Pagination getPageByOrganization(Long siteId,Long organizationId, int pageNo, int pageSize);	
	
	
	public Pagination getPage(
			String queryUsername, String queryName,  int pageNo, String jmftypeIdstr, int pageSize);
	
	
}