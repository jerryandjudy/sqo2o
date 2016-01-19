package com.jspgou.cms.manager.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jspgou.cms.dao.ShopMessageDao;
import com.jspgou.cms.entity.ShopMessage;
import com.jspgou.cms.manager.ShopMemberMng;
import com.jspgou.cms.manager.ShopMessageMng;
import com.jspgou.common.page.Pagination;
/**
* This class should preserve.
* @preserve
*/
@Service
@Transactional
public class ShopMessageMngImpl implements ShopMessageMng {
	@Transactional(readOnly = true)
	public ShopMessage findById(Long id) {
		ShopMessage entity = shopMessageDao.findById(id);
		return entity;
	}

	public ShopMessage saveOrUpdate(ShopMessage bean){
		shopMessageDao.saveOrUpdate(bean);
		return bean;
		
	}
	
	
	public ShopMessage update(ShopMessage ShopMessage) {
		return shopMessageDao.update(ShopMessage);
	}
	
	public ShopMessage[] deleteByIds(Long[] ids){
		ShopMessage[] beans = new ShopMessage[ids.length];
		for (int i = 0, len = ids.length; i < len; i++) {
			beans[i] = deleteById(ids[i]);
		}
		return beans;
	}

	public ShopMessage deleteById(Long id) {
		ShopMessage bean = shopMessageDao.deleteById(id);
		return bean;
	}
	
	public Pagination getPage(int pageNo,int pageSize){
		return shopMessageDao.getPage(pageNo, pageSize);
	}

	@Autowired
	private ShopMemberMng shopMemberMng;
	@Autowired
	public ShopMessageDao shopMessageDao;
	
}