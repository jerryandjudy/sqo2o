package com.jspgou.cms.dao;

import com.jspgou.common.hibernate3.Updater;
import com.jspgou.common.page.Pagination;
import com.jspgou.cms.entity.ShopMember;

/**
* This class should preserve.
* @preserve
*/
public interface ShopMemberDao {
	public Pagination getPage(Long webId, int pageNo, int pageSize);

	public ShopMember findById(Long id);

	public ShopMember save(ShopMember bean);

	public ShopMember updateByUpdater(Updater<ShopMember> updater);

	public ShopMember deleteById(Long id);
}