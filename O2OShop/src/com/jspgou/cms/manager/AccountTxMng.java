package com.jspgou.cms.manager;

import com.jspgou.cms.entity.AccountTx;
import com.jspgou.cms.entity.Accountcz;
import com.jspgou.common.page.Pagination;

/**
* This class should preserve.
* @preserve
*/
public interface AccountTxMng {

	public AccountTx findById(Long id);
	
	public AccountTx update(AccountTx bean);
	
	public AccountTx save(AccountTx bean);


	public AccountTx deleteById(Long id);
	
	public Pagination findAll(Long siteId, String payStatus, String paySuccessTime, String username, String organizationType, int pageNo, int pageSize);



	
	
	
	
}