package com.jspgou.core.manager;

import com.jspgou.common.hibernate3.Updater;
import com.jspgou.core.entity.Account;

public interface AccountMng {

	public Account findById(String id);
	public Account findByStatus(Integer status);

    public Account save(Account bean);

    public Account updateByUpdater(Updater<Account> updater);
}
