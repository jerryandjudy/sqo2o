package com.jspgou.cms.manager.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jspgou.cms.dao.BldDao;
import com.jspgou.cms.dao.KetaUserDao;
import com.jspgou.cms.entity.Bld;
import com.jspgou.cms.entity.Gys;
import com.jspgou.cms.entity.KetaUser;
import com.jspgou.cms.entity.Order;
import com.jspgou.cms.entity.OrderItem;
import com.jspgou.cms.manager.BldMng;
import com.jspgou.common.hibernate3.Updater;
import com.jspgou.common.page.Pagination;
import com.jspgou.core.dao.AccountDao;
import com.jspgou.core.dao.WebsiteDao;
import com.jspgou.core.entity.Account;
import com.jspgou.core.entity.Website;
import com.jspgou.core.security.CmsAuthorizingRealm;
import com.jspgou.core.security.CmsAuthorizingRealm.HashPassword;
/**
* This class should preserve.
* @preserve
*/
@Service
@Transactional
public class BldMngImpl implements BldMng {

	@Override
	@Transactional(readOnly = true)
	public List<Bld> getAllList() {
		// TODO Auto-generated method stub
		return bldDao.getAllList();
	}
	@Override
	@Transactional(readOnly = true)
	public List<Bld> getBldBySiteAndPsqy(Long siteId,Long province,Long city,Long country,Long street){
		// TODO Auto-generated method stub
		return bldDao.getBldBySiteAndPsqy( siteId, province, city, country, street);
	}
	@Override
	@Transactional(readOnly = true)
	public List<Bld> getBldByOrder(Order order){
		// TODO Auto-generated method stub
		List<Bld> list=bldDao.getBldByOrder(order);
		return list;
	}
	@Override
	@Transactional(readOnly = true)
	public Bld getBldByOrderAndOrderItem(Order order,OrderItem orderItem){
		// TODO Auto-generated method stub
		Bld bld=bldDao.getBldByOrderAndOrderItem( order, orderItem);
		return bld;
	}
	@Override
	@Transactional(readOnly = true)
	public Pagination getBldByOrder(Order order, int pageNo, int pageSize){
		// TODO Auto-generated method stub
		String[] psqys=order.getReceiveAddress().split(" ");
		String psqy=psqys[0]+psqys[1]+psqys[2]+psqys[3];
		return bldDao.getBldByOrder(order,  pageNo,  pageSize);
	}
	@Override
	@Transactional(readOnly = true)
	public Bld getBldByOrder(Order order,String bldId){
		// TODO Auto-generated method stub
		return bldDao.getBldByOrder(order,bldId);
	}

	@Override
	@Transactional(readOnly = true)
	public Bld findById(String id) {
		// TODO Auto-generated method stub
		return bldDao.findById(id);
	}
	@Override
	@Transactional(readOnly = true)
	public Bld findByKetaUserId(Long id){
		return bldDao.findByKetaUserId(id);
	}
	@Override
	@Transactional(readOnly = true)
	public Bld findByIsDef(Boolean isDef,Long siteId) {
		// TODO Auto-generated method stub
		return bldDao.findByIsDef( isDef, siteId);
	}

	@Override
	public Bld save(Bld bean) {
		// TODO Auto-generated method stub
		Account account=accountDao.save(bean.getAccount());
    	bean.setAccount(account);
		return bldDao.save(bean);
	}
	@Override
	public Bld register(Bld bean,Account accounts,KetaUser ketaUser) {
		// TODO Auto-generated method stub
		KetaUser ketaUsers = ketaUserDao.save(ketaUser);
		Account account=accountDao.save(accounts);
		bean.setAccount(account);
		bean.setKetaUser(ketaUsers);
		return bldDao.save(bean);
	}

	@Override
	public Bld updateByUpdater(Bld bean) {
		// TODO Auto-generated method stub
		Updater<Bld> updater = new Updater<Bld>(bean);
		return bldDao.updateByUpdater(updater);
	}

	@Override
	public Bld deleteById(String id) {
		// TODO Auto-generated method stub
		return bldDao.deleteById(id);
	}

	@Override
	@Transactional(readOnly = true)
	public Pagination getPageByIsDisabled(String organizationName,Long siteId,String username,String companyName,Boolean isDisabled, int pageNo,
			int pageSize) {
		// TODO Auto-generated method stub
		return bldDao.getPageByIsDisabled(organizationName, siteId, username, companyName,isDisabled, pageNo, pageSize);
	}
	@Override
	@Transactional(readOnly = true)
	public Pagination getPageByIsDisabled(Boolean isDisabled, int pageNo,
			int pageSize) {
		// TODO Auto-generated method stub
		return bldDao.getPageByIsDisabled( isDisabled, pageNo, pageSize);
	}
	@Override
	@Transactional(readOnly = true)
	public Pagination getPageBySiteId(Long siteId, int pageNo, int pageSize) {
		// TODO Auto-generated method stub
		return bldDao.getPageBySiteId(siteId, pageNo, pageSize);
	}
	
	@Autowired
	private BldDao bldDao;
	@Autowired
	private KetaUserDao ketaUserDao;
	@Autowired
	private WebsiteDao sitedao;
	private AccountDao accountDao;
	@Autowired
	public void setAccountDao(AccountDao accountDao) {
		this.accountDao = accountDao;
	}
	@Override
	public List<Website> getListNotHasBld() {
		// TODO Auto-generated method stub
		return sitedao.getListNotHasBld();
	}
	@Transactional(readOnly = true)
	@Override
	public Pagination getPageByGysNotHas(Bld bld,Gys gys, int pageNo, int pageSize){
		// TODO Auto-generated method stub
		return bldDao.getPageByGysNotHas(bld, gys, pageNo, pageSize);
	}
	
}