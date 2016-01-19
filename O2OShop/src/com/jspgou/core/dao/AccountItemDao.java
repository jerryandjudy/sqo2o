package com.jspgou.core.dao;

import com.jspgou.common.hibernate3.Updater;
import com.jspgou.common.page.Pagination;
import com.jspgou.core.entity.Account;
import com.jspgou.core.entity.AccountItem;

public interface AccountItemDao {

	public AccountItem findById(Long id);
	public AccountItem deleteByBean(AccountItem item);
	public Pagination getPageByAccountId(String accountId, int pageNo,
			int pageSize) ;
    public AccountItem save(AccountItem bean);
    public AccountItem findByAccountIdAndOrderId(String accountId,Long ordertId);
    public AccountItem updateByUpdater(Updater<AccountItem> updater);
}
