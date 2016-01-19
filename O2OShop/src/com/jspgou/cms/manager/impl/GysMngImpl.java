package com.jspgou.cms.manager.impl;

import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jspgou.cms.dao.GysDao;
import com.jspgou.cms.dao.KetaUserDao;
import com.jspgou.cms.dao.LsfcDao;
import com.jspgou.cms.dao.OrganizationDao;
import com.jspgou.cms.entity.Bld;
import com.jspgou.cms.entity.Gys;
import com.jspgou.cms.entity.KetaUser;
import com.jspgou.cms.entity.Lsfc;
import com.jspgou.cms.entity.Order;
import com.jspgou.cms.entity.OrderItem;
import com.jspgou.cms.manager.GysMng;
import com.jspgou.cms.manager.LsfcMng;
import com.jspgou.common.hibernate3.Updater;
import com.jspgou.common.page.Pagination;
import com.jspgou.core.dao.AccountDao;
import com.jspgou.core.dao.WebsiteDao;
import com.jspgou.core.entity.Account;
import com.jspgou.core.entity.Website;
/**
* This class should preserve.
* @preserve
*/
@Service
@Transactional
public class GysMngImpl implements GysMng {

	@Override
	@Transactional(readOnly = true)
	public List<Gys> getAllList() {
		// TODO Auto-generated method stub
		return gysDao.getAllList();
	}
	@Override
	@Transactional(readOnly = true)
	public Gys getGysByOrderAndOrderItem(Order order,OrderItem orderItem){
		// TODO Auto-generated method stub
		Gys gys=gysDao.getGysByOrderAndOrderItem( order, orderItem);
		return gys;
	}
	@Override
	@Transactional(readOnly = true)
	public Gys findById(String id) {
		// TODO Auto-generated method stub
		return gysDao.findById(id);
	}
	@Override
	@Transactional(readOnly = true)
	public Gys findByKetaUserId(Long id) {
		// TODO Auto-generated method stub
		return gysDao.findByKetaUserId(id);
	}
	@Override
	@Transactional(readOnly = true)
	public List<Gys> getGysByOrder(Order order){
		// TODO Auto-generated method stub
		return gysDao.getGysByOrder(order);
	}

	@Override
	public Gys save(Gys bean) {
		// TODO Auto-generated method stub
		Account account=accountDao.save(bean.getAccount());
    	bean.setAccount(account);
		return gysDao.save(bean);
	}
	@Override
	public Gys register(Gys bean,Account accounts,KetaUser ketaUser) {
		// TODO Auto-generated method stub
		KetaUser ketaUsers = ketaUserDao.save(ketaUser);
		Account account=accountDao.save(accounts);
		bean.setAccount(account);
		bean.setKetaUser(ketaUsers);
		return gysDao.save(bean);
	}
	@Override
	public Gys save(Gys bean,String[]picPaths, String[] picDescs) {
		// TODO Auto-generated method stub
		Account account=accountDao.save(bean.getAccount());
		bean.setAccount(account);
		// 保存图片集
				if (picPaths != null && picPaths.length > 0) {
					for (int i = 0, len = picPaths.length; i < len; i++) {
						if (!StringUtils.isBlank(picPaths[i])) {
							bean.addToPictures(picPaths[i], picDescs[i]);
						}
					}
				}
				// 执行监听器
//				afterSave(bean);
		return gysDao.save(bean);
	}

	@Override
	public Gys updateByUpdater(Gys bean) {
		// TODO Auto-generated method stub
		Updater<Gys> updater = new Updater<Gys>(bean);
		return gysDao.updateByUpdater(updater);
	}
	@Override
	public Gys updateByUpdater(Gys bean,String[]picPaths, String[] picDescs) {
		// TODO Auto-generated method stub
		Updater<Gys> updater = new Updater<Gys>(bean);
		bean=gysDao.updateByUpdater(updater);
		// 更新图片集
		bean.getPictures().clear();
				if (picPaths != null && picPaths.length > 0) {
					for (int i = 0, len = picPaths.length; i < len; i++) {
						if (!StringUtils.isBlank(picPaths[i])) {
							bean.addToPictures(picPaths[i], picDescs[i]);
						}
					}
				}
		return bean;
	}

	@Override
	public Gys getGysByOrder(Order order,String gysId) {
		// TODO Auto-generated method stub
		return gysDao.getGysByOrder(order,gysId);
	}
	@Override
	public Gys deleteById(String id) {
		// TODO Auto-generated method stub
		return gysDao.deleteById(id);
	}

	@Override
	@Transactional(readOnly = true)
	public Pagination getPageByIsDisabled(Boolean isDisabled, int pageNo,
			int pageSize) {
		// TODO Auto-generated method stub
		return gysDao.getPageByIsDisabled(isDisabled, pageNo, pageSize);
	}
	@Override
	@Transactional(readOnly = true)
	public Pagination getPageByIsDisabled(String organizationName,Long siteId,String username,String companyName,Boolean isDisabled, int pageNo,
			int pageSize) {
		// TODO Auto-generated method stub
		return gysDao.getPageByIsDisabled(organizationName,siteId,username, companyName,isDisabled, pageNo, pageSize);
	}
	@Override
	@Transactional(readOnly = true)
	public Pagination getPageBySiteAndNotGysId(String username,String companyName,Long siteId,String id, int pageNo, int pageSize){
		// TODO Auto-generated method stub
		return gysDao.getPageBySiteAndNotGysId( username, companyName,siteId, id, pageNo, pageSize);
	}
	
	@Autowired
	public GysDao gysDao;
	@Autowired
	public WebsiteDao websiteDao;
	@Autowired
	public AccountDao accountDao;
	@Autowired
	private KetaUserDao ketaUserDao;
	@Override
	public List<Website> getListNotHasGys() {
		// TODO Auto-generated method stub
		return websiteDao.getListNotHasGys();
	}
	@Override
	public List<Gys> getListBySiteIdAndProductSiteId(Long siteId,Long buyProductSiteId,Long giveProductSiteId){
		return gysDao.getListBySiteIdAndProductSiteId(siteId, buyProductSiteId, giveProductSiteId);
	}
}