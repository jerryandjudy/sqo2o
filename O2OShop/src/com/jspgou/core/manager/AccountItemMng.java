/**
* 吉林省艾利特信息技术有限公司
* 进销存管理系统
* @date 2015年4月11日
* @author liuwang
* @version 1.0
*/
package com.jspgou.core.manager;

import com.jspgou.common.hibernate3.Updater;
import com.jspgou.common.page.Pagination;
import com.jspgou.core.entity.AccountItem;

public interface AccountItemMng {
	public AccountItem findById(Long id);
	public AccountItem deleteById(Long id);
	public AccountItem findByAccountIdAndOrderId(String accountId,Long ordertId);
	public Pagination getPageByAccountId(String accountId, int pageNo,
			int pageSize) ;
    public AccountItem save(AccountItem bean);

    public AccountItem updateByUpdater(Updater<AccountItem> updater);
}
