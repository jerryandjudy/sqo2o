package com.jspgou.cms.manager.impl;

import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jspgou.common.hibernate3.Updater;
import com.jspgou.common.page.Pagination;
import com.jspgou.core.entity.Admin;
import com.jspgou.core.entity.Website;
import com.jspgou.core.manager.AdminMng;
import com.jspgou.core.manager.UserMng;
import com.jspgou.core.manager.WebsiteMng;
import com.jspgou.cms.dao.ShopAdminDao;
import com.jspgou.cms.entity.ShopAdmin;
import com.jspgou.cms.manager.ShopAdminMng;

/**
* This class should preserve.
* @preserve
*/
@Service
@Transactional
public class ShopAdminMngImpl implements ShopAdminMng {
	public ShopAdmin getByUserId(Long userId, Long webId) {
		Admin admin = adminMng.getByUserId(userId, webId);
		if (admin == null) {
			return null;
		}
		return findById(admin.getId());
	}

	public ShopAdmin register(String username, String password,Boolean viewonlyAdmin,String email,
			String ip, boolean disabled, Long webId, 
			ShopAdmin shopAdmin) {
		Website web = websiteMng.findById(webId);
		Admin admin = adminMng.register(username, password, email, ip,disabled,web,viewonlyAdmin);
		shopAdmin.setAdmin(admin);
		shopAdmin.setWebsite(web);
		return save(shopAdmin);
	}
	public ShopAdmin register(String username, String password,Boolean viewonlyAdmin,String email,
			String ip, boolean disabled, Long webId, 
			ShopAdmin shopAdmin,Boolean isSuper) {
		Website web = websiteMng.findById(webId);
		Admin admin = adminMng.register(username, password, email, ip,disabled,web,viewonlyAdmin,isSuper);
		shopAdmin.setAdmin(admin);
		shopAdmin.setWebsite(web);
		return save(shopAdmin);
	}

	@Transactional(readOnly = true)
	public Pagination getPage(Long webId, int pageNo, int pageSize) {
		Pagination page = shopAdminDao.getPage(webId, pageNo, pageSize);
		return page;
	}

	@Transactional(readOnly = true)
	public ShopAdmin findById(Long id) {
		ShopAdmin entity = shopAdminDao.findById(id);
		return entity;
	}

	public ShopAdmin save(ShopAdmin bean) {
		shopAdminDao.save(bean);
		return bean;
	}

	public ShopAdmin update(ShopAdmin bean, String password, Boolean disabled,
			String email,Boolean viewonlyAdmin) {
		ShopAdmin entity = findById(bean.getId());
		Admin admin = entity.getAdmin();
		userMng.updateUser(admin.getUser().getId(), password, email);
		if (disabled != null) {
			admin.setDisabled(disabled);
		}
		admin.setViewonlyAdmin(viewonlyAdmin);
		Updater<ShopAdmin> updater = new Updater<ShopAdmin>(bean);
		entity = shopAdminDao.updateByUpdater(updater);
		return entity;
	}
	public ShopAdmin update(ShopAdmin bean, String password, Boolean disabled,
			String email,Boolean viewonlyAdmin,Boolean isSuper,Long siteId) {
		ShopAdmin entity = findById(bean.getId());
		Admin admin = entity.getAdmin();
		userMng.updateUser(admin.getUser().getId(), password, email);
		if (disabled != null) {
			admin.setDisabled(disabled);
		}
		if (isSuper != null) {
			admin.setIsSuper(isSuper);
		}
		Website web = websiteMng.findById(siteId);
		admin.setWebsite(web);
		admin.setViewonlyAdmin(viewonlyAdmin);
		entity.setWebsite(web);
		Updater<ShopAdmin> updater = new Updater<ShopAdmin>(bean);
		entity = shopAdminDao.updateByUpdater(updater);
		return entity;
	}

	public ShopAdmin deleteById(Long id) {
		ShopAdmin bean = shopAdminDao.deleteById(id);
		return bean;
	}

	public ShopAdmin[] deleteByIds(Long[] ids) {
		ShopAdmin[] beans = new ShopAdmin[ids.length];
		for (int i = 0, len = ids.length; i < len; i++) {
			beans[i] = deleteById(ids[i]);
		}
		return beans;
	}

	private UserMng userMng;
	private WebsiteMng websiteMng;
	private AdminMng adminMng;
	@Autowired
	private ShopAdminDao shopAdminDao;

	@Autowired
	public void setAdminMng(AdminMng adminMng) {
		this.adminMng = adminMng;
	}

	@Autowired
	public void setWebsiteMng(WebsiteMng websiteMng) {
		this.websiteMng = websiteMng;
	}

	@Autowired
	public void setUserMng(UserMng userMng) {
		this.userMng = userMng;
	}
}