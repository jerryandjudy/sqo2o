package com.jspgou.cms.manager;

import java.util.Set;

import com.jspgou.common.page.Pagination;
import com.jspgou.cms.entity.ShopAdmin;
/**
* This class should preserve.
* @preserve
*/
public interface ShopAdminMng {
	public ShopAdmin getByUserId(Long userId, Long webId);

	public ShopAdmin register(String username, String password,Boolean viewonlyAdmin, String email,
			String ip, boolean disabled, Long webId, 
			ShopAdmin shopAdmin);
	public ShopAdmin register(String username, String password,Boolean viewonlyAdmin, String email,
			String ip, boolean disabled, Long webId, 
			ShopAdmin shopAdmin,Boolean isSuper);

	public Pagination getPage(Long webId, int pageNo, int pageSize);

	public ShopAdmin findById(Long id);

	public ShopAdmin save(ShopAdmin bean);

	public ShopAdmin update(ShopAdmin bean, String password, Boolean disabled,
			String email,Boolean viewonlyAdmin);
	public ShopAdmin update(ShopAdmin bean, String password, Boolean disabled,
			String email,Boolean viewonlyAdmin,Boolean isSuper,Long siteId);

	public ShopAdmin deleteById(Long id);

	public ShopAdmin[] deleteByIds(Long[] ids);
}