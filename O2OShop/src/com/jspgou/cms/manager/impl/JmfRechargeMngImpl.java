package com.jspgou.cms.manager.impl;

import static com.jspgou.common.page.SimplePage.cpn;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jspgou.cms.dao.JmfRechargeDao;
import com.jspgou.cms.dao.LsfcDao;
import com.jspgou.cms.dao.OrganizationDao;
import com.jspgou.cms.entity.CmsLog;
import com.jspgou.cms.entity.JmfRecharge;
import com.jspgou.cms.entity.Lsfc;
import com.jspgou.cms.entity.Order;
import com.jspgou.cms.manager.JmfRechargeMng;
import com.jspgou.cms.manager.LsfcMng;
import com.jspgou.common.hibernate3.Updater;
import com.jspgou.common.page.Pagination;
import com.jspgou.common.web.CookieUtils;
import com.jspgou.core.dao.WebsiteDao;
import com.jspgou.core.entity.User;
import com.jspgou.core.entity.Website;
/**
* This class should preserve.
* @preserve
*/
@Service
@Transactional
public class JmfRechargeMngImpl implements JmfRechargeMng {

	@Override
	@Transactional(readOnly = true)
	public List<JmfRecharge> getAllList() {
		// TODO Auto-generated method stub
		return jmfRechargeDao.getAllList();
	}
	
	
	@Transactional(readOnly = true)
	public Pagination getPage(
			String queryUsername, String queryName,  int pageNo, String jmftypeIdstr, int pageSize) {
		Pagination page;
		
				page = jmfRechargeDao.getPage(queryUsername, queryName, pageNo, jmftypeIdstr,  pageSize);
		
		return page;
	}

	@Override
	@Transactional(readOnly = true)
	public JmfRecharge findById(Long id) {
		// TODO Auto-generated method stub
		return jmfRechargeDao.findById(id);
	}

	@Override
	public JmfRecharge save(JmfRecharge bean) {
		// TODO Auto-generated method stub
		return jmfRechargeDao.save(bean);
	}

	@Override
	public JmfRecharge updateByUpdater(JmfRecharge bean) {
		// TODO Auto-generated method stub
		Updater<JmfRecharge> updater = new Updater<JmfRecharge>(bean);
		return jmfRechargeDao.updateByUpdater(updater);
	}

	@Override
	public JmfRecharge deleteById(Long id) {
		// TODO Auto-generated method stub
		//String ids = Long.toString(id);
		return jmfRechargeDao.deleteById(id);
	}

	@Override
	@Transactional(readOnly = true)
	public Pagination getPageByIsDisabled(Boolean isDisabled, int pageNo,
			int pageSize) {
		// TODO Auto-generated method stub
		return jmfRechargeDao.getPageByIsDisabled(isDisabled, pageNo, pageSize);
	}
	
	@Override
	public Pagination getPageByOrganization(Long siteId,String jmftypeIdstr,int pageNo, int pageSize) {
		// TODO Auto-generated method stub
		return jmfRechargeDao.findAllUsers(siteId,jmftypeIdstr,pageNo, pageSize);
	}
	
	@Override
	public List getFcList(String jmftypeIdstr){
		// TODO Auto-generated method stub
		return jmfRechargeDao.findFC(jmftypeIdstr);
	}
	
	@Autowired
	public JmfRechargeDao jmfRechargeDao;
	@Autowired
	private WebsiteDao websiteDao;
	
	

	
}