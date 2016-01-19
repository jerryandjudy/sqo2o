package com.jspgou.cms.dao;

import static com.jspgou.common.page.SimplePage.cpn;

import java.util.List;

import com.jspgou.cms.entity.AccountTx;
import com.jspgou.cms.entity.KetaUser;
import com.jspgou.common.hibernate3.Updater;
import com.jspgou.common.page.Pagination;
import com.jspgou.common.web.CookieUtils;

/**
* This class should preserve.
* @preserve
*/
public interface AccountTxDao {
	public AccountTx update(AccountTx bean);
	public AccountTx findById(Long id);
	
	public AccountTx deleteById(Long id);

	public AccountTx save(AccountTx bean);
	
	public Pagination findAll(Long siteId,String payStatus, String paySuccessTime, String username, String organizationType, int pageNo, int pageSize);
	
}