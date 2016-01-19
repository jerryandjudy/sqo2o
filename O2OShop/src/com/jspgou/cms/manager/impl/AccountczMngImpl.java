package com.jspgou.cms.manager.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jspgou.cms.dao.AccountczDao;
import com.jspgou.cms.entity.Accountcz;
import com.jspgou.cms.entity.Bld;
import com.jspgou.cms.entity.Fws;
import com.jspgou.cms.entity.Gys;
import com.jspgou.cms.manager.AccountczMng;
import com.jspgou.cms.manager.BldMng;
import com.jspgou.cms.manager.DlsMng;
import com.jspgou.cms.manager.FwsMng;
import com.jspgou.cms.manager.GysMng;
import com.jspgou.common.hibernate3.Updater;
import com.jspgou.common.page.Pagination;
import com.jspgou.core.dao.WebsiteDao;
import com.jspgou.core.entity.Account;
import com.jspgou.core.entity.AccountItem;
import com.jspgou.core.manager.AccountItemMng;
import com.jspgou.core.manager.AccountMng;
/**
* This class should preserve.
* @preserve
*/
@Service
@Transactional
public class AccountczMngImpl implements AccountczMng {

	@Override
	@Transactional(readOnly = true)
	public List<Accountcz> getAllList() {
		// TODO Auto-generated method stub
		return accountczDao.getAllList();
	}
	
	
	@Transactional(readOnly = true)
	public Pagination getPage(
			String queryUsername, String queryName,  int pageNo, String jmftypeIdstr, int pageSize) {
		Pagination page;
		
				page = accountczDao.getPage(queryUsername, queryName, pageNo, jmftypeIdstr,  pageSize);
		
		return page;
	}

	@Override
	@Transactional(readOnly = true)
	public Accountcz findById(Long id) {
		// TODO Auto-generated method stub
		return accountczDao.findById(id);
	}

	@Override
	public Accountcz save(Accountcz bean) {
		// TODO Auto-generated method stub
		return accountczDao.save(bean);
	}

	@Override
	public Accountcz updateByUpdater(Accountcz bean) {
		// TODO Auto-generated method stub
		Updater<Accountcz> updater = new Updater<Accountcz>(bean);
		return accountczDao.updateByUpdater(updater);
	}
	@Override
	public Accountcz czSuccess(Accountcz bean) {
		// TODO Auto-generated method stub
		Account account=null;
		if("便利店".equals(bean.getOrganizationType())){
			Bld bld = bldMng.findByKetaUserId(bean.getKetaUser().getId());
			account = bld.getAccount();
		}else if("供应商".equals(bean.getOrganizationType())){
			Gys gys = gysMng.findByKetaUserId(bean.getKetaUser().getId());
			account = gys.getAccount();
		}else if("代理商".equals(bean.getOrganizationType())){
			account = bean.getWebsite().getAccount();
		}else if("服务商".equals(bean.getOrganizationType())){
			Fws fws =fwsMng.findByKetaUserId(bean.getKetaUser().getId());
			account =fws.getAccount();
		    }
		account.setMoney(account.getMoney()-bean.getRealValue());
		accountMng.updateByUpdater(new Updater(account));
		AccountItem accountItem = new AccountItem();
		accountItem.setAccount(account);
		accountItem.setMoney(bean.getRealValue());
		accountItem.setMoneyTime(new Date());
		accountItem.setMoneyType(2);
		accountItem.setName("充值");
		accountItem.setRemark(bean.getOrganizationType()+"充值");
		accountItem.setStatus(false);
		accountItem.setUseStatus(true);
		accountItemMng.save(accountItem);
		Updater<Accountcz> updater = new Updater<Accountcz>(bean);
		return accountczDao.updateByUpdater(updater);
	}

	@Override
	public Accountcz deleteById(Long id) {
		// TODO Auto-generated method stub
		//String ids = Long.toString(id);
		return accountczDao.deleteById(id);
	}

	@Override
	@Transactional(readOnly = true)
	public Pagination getPage(String payStatus,String username,String siteId,Long organizationId,String createTime, int pageNo,
			int pageSize) {
		// TODO Auto-generated method stub
		return accountczDao.getPage(payStatus, username, siteId, organizationId, createTime, pageNo, pageSize);
	}
	
	@Override
	public Pagination getPageByOrganization(Long siteId,Long organizationId,int pageNo, int pageSize) {
		// TODO Auto-generated method stub
		return accountczDao.findAllUsers(siteId,organizationId,pageNo, pageSize);
	}
	
	@Autowired
	private AccountItemMng accountItemMng;
	@Autowired
	private AccountMng accountMng;
	@Autowired
	public AccountczDao accountczDao;
	@Autowired
	private WebsiteDao websiteDao;
	@Autowired
	private DlsMng dlsMng;
	@Autowired
	private GysMng gysMng;
	@Autowired
	private BldMng bldMng;
	@Autowired
	private FwsMng fwsMng;
	

	
}