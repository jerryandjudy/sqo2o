package com.jspgou.core.dao;

import com.jspgou.common.hibernate3.Updater;
import com.jspgou.core.entity.Account;

public interface AccountDao {

	public Account findById(String id);
	public Account findByStatus(Integer id);
    public Account save(Account bean);

    public Account updateByUpdater(Updater<Account> updater);
}
