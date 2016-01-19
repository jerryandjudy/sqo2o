package com.jspgou.cms.manager.impl;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jspgou.common.hibernate3.Updater;
import com.jspgou.common.page.Pagination;
import com.jspgou.cms.dao.ShopMoneyDao;
import com.jspgou.cms.entity.ShopMoney;
import com.jspgou.cms.manager.ShopMoneyMng;

/**
* This class should preserve.
* @preserve
*/
@Service
@Transactional
public class ShopMoneyMngImpl implements ShopMoneyMng {
	@Transactional(readOnly = true)
	public Pagination getPage(int pageNo, int pageSize) {
		Pagination page = shopMoneyDao.getPage(pageNo, pageSize);
		return page;
	}
	
	public Pagination getPage(Long memberId,Boolean status,
			Date startTime,Date endTime,Integer pageSize,Integer pageNo){
		Pagination page = shopMoneyDao.getPage(memberId,status,
				startTime,endTime,pageNo, pageSize);
		return page;
	}

	@Transactional(readOnly = true)
	public ShopMoney findById(Long id) {
		ShopMoney entity = shopMoneyDao.findById(id);
		return entity;
	}

	public ShopMoney save(ShopMoney bean) {
		shopMoneyDao.save(bean);
		return bean;
	}

	public ShopMoney update(ShopMoney bean) {
		Updater<ShopMoney> updater = new Updater<ShopMoney>(bean);
		ShopMoney entity = shopMoneyDao.updateByUpdater(updater);
		return entity;
	}

	public ShopMoney deleteById(Long id) {
		ShopMoney bean = shopMoneyDao.deleteById(id);
		return bean;
	}
	
	public ShopMoney[] deleteByIds(Long[] ids) {
		ShopMoney[] beans = new ShopMoney[ids.length];
		for (int i = 0,len = ids.length; i < len; i++) {
			beans[i] = deleteById(ids[i]);
		}
		return beans;
	}

	@Autowired
	private ShopMoneyDao shopMoneyDao;
}