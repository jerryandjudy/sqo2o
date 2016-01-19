package com.jspgou.cms.manager.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jspgou.common.hibernate3.Updater;
import com.jspgou.cms.dao.ShopPayDao;
import com.jspgou.cms.entity.ShopPay;
import com.jspgou.cms.manager.ShopPayMng;

/**
* This class should preserve.
* @preserve
*/
@Service
@Transactional
public class ShopPayMngImpl implements ShopPayMng {

	public ShopPay deleteById(Integer id) {
		return shopPayDao.deleteById(id);
	}

	public ShopPay[] deleteByIds(Integer[] ids) {
		ShopPay[] beans = new ShopPay[ids.length];
		for (int i = 0, len = ids.length; i < len; i++) {
			beans[i] = deleteById(ids[i]);
		}
		return beans;
		
	}

	public ShopPay findById(Integer id) {
		return shopPayDao.findById(id);
	}


	public ShopPay save(ShopPay bean) {
		return shopPayDao.save(bean);
	}
	
	

	public ShopPay updateByUpdater(ShopPay bean) {
		Updater<ShopPay> updater = new Updater<ShopPay>(bean);
			
			return shopPayDao.updateByUpdater(updater);
		
	}
	
      @Autowired
      private ShopPayDao shopPayDao;
}