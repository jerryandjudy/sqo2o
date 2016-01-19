/**
* 吉林省艾利特信息技术有限公司
* 进销存管理系统
* @date 2015年4月11日
* @author liuwang
* @version 1.0
*/
package com.jspgou.core.manager.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.jspgou.common.hibernate3.Updater;
import com.jspgou.common.page.Pagination;
import com.jspgou.core.dao.AccountDao;
import com.jspgou.core.dao.AccountItemDao;
import com.jspgou.core.entity.AccountItem;
import com.jspgou.core.manager.AccountItemMng;
import com.jspgou.core.manager.AccountMng;

@Repository
public class AccountItemMngImpl implements AccountItemMng{

	@Override
	public AccountItem findById(Long id) {
		// TODO Auto-generated method stub
		return accountItemDao.findById(id);
	}
	@Override
	public AccountItem deleteById(Long id){
		AccountItem item=findById(id);
		return accountItemDao.deleteByBean(item);
	}
	@Override
	public AccountItem findByAccountIdAndOrderId(String accountId,Long ordertId) {
		// TODO Auto-generated method stub
		return accountItemDao.findByAccountIdAndOrderId(accountId, ordertId);
	}

	@Override
	public Pagination getPageByAccountId(String accountId, int pageNo,
			int pageSize) {
		// TODO Auto-generated method stub
		return accountItemDao.getPageByAccountId(accountId, pageNo, pageSize);
	}

	@Override
	public AccountItem save(AccountItem bean) {
		// TODO Auto-generated method stub
		return accountItemDao.save(bean);
	}

	@Override
	public AccountItem updateByUpdater(Updater<AccountItem> updater) {
		// TODO Auto-generated method stub
		return accountItemDao.updateByUpdater(updater);
	}
	@Autowired
	public AccountItemDao accountItemDao;
}
