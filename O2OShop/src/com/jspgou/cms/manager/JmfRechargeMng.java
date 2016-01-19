package com.jspgou.cms.manager;

import static com.jspgou.common.page.SimplePage.cpn;

import java.util.List;

import com.jspgou.cms.entity.JmfRecharge;
import com.jspgou.common.hibernate3.Updater;
import com.jspgou.common.page.Pagination;
import com.jspgou.common.web.CookieUtils;
import com.jspgou.core.entity.Website;

/**
* This class should preserve.
* @preserve
*/
public interface JmfRechargeMng {
	public List<JmfRecharge> getAllList();

	public JmfRecharge findById(Long id);
	
	
	
	public JmfRecharge save(JmfRecharge bean);

	public JmfRecharge updateByUpdater(JmfRecharge bean);

	public JmfRecharge deleteById(Long id);
	public Pagination getPageByIsDisabled(Boolean isDisabled, int pageNo, int pageSize);	
	
	public Pagination getPageByOrganization(Long siteId,String jmftypeIdstr, int pageNo, int pageSize);	
	
	public List getFcList(String jmftypeIdstr);
	
	public Pagination getPage(
			String queryUsername, String queryName,  int pageNo, String jmftypeIdstr, int pageSize);
	
	
}