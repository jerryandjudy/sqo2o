package com.jspgou.cms.dao;

import static com.jspgou.common.page.SimplePage.cpn;

import java.util.List;

import com.jspgou.cms.entity.JmfRecharge;
import com.jspgou.cms.entity.KetaUser;
import com.jspgou.common.hibernate3.Updater;
import com.jspgou.common.page.Pagination;
import com.jspgou.common.web.CookieUtils;

/**
* This class should preserve.
* @preserve
*/
public interface JmfRechargeDao {
	public List<JmfRecharge> getAllList();

	public JmfRecharge findById(Long id);

	public Pagination findAllUsers(Long siteId,String jmftypeIdstr, int pageNo, int pageSize);
	
	public JmfRecharge save(JmfRecharge bean);

	public JmfRecharge updateByUpdater(Updater<JmfRecharge> updater);

	public JmfRecharge deleteById(Long id);
	public Pagination getPageByIsDisabled(Boolean isDisabled, int pageNo, int pageSize);

	public List findFC(String jmftype_id);

	public Pagination getPage(String  queryUsername, String  queryName,  int pageNo,  String jmftypeIdstr, int pageSize);
	
}