package com.jspgou.cms.manager.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jspgou.common.hibernate3.Updater;
import com.jspgou.common.page.Pagination;
import com.jspgou.cms.dao.PopularityItemDao;
import com.jspgou.cms.entity.Cart;
import com.jspgou.cms.entity.PopularityItem;
import com.jspgou.cms.manager.PopularityGroupMng;
import com.jspgou.cms.manager.PopularityItemMng;

@Service
@Transactional
public class PopularityItemMngImpl implements PopularityItemMng {
	@Transactional(readOnly = true)
	public Pagination getPage(int pageNo, int pageSize) {
		Pagination page = popularityItemDao.getPage(pageNo, pageSize);
		return page;
	}
	
	public List<PopularityItem> getlist(Long cartId,Long popularityGroupId){
		return popularityItemDao.getlist(cartId,popularityGroupId);
		
	}
	
	public PopularityItem findById(Long cartId,Long popularityId){
		return popularityItemDao.findById(cartId, popularityId);
		
	}
	
	@Transactional(readOnly = true)
	public PopularityItem findById(Long id) {
		PopularityItem entity = popularityItemDao.findById(id);
		return entity;
	}

	public PopularityItem save(PopularityItem bean) {
		popularityItemDao.save(bean);
		return bean;
	}

	public PopularityItem update(PopularityItem bean) {
		Updater<PopularityItem> updater = new Updater<PopularityItem>(bean);
		PopularityItem entity = popularityItemDao.updateByUpdater(updater);
		return entity;
	}

	public PopularityItem deleteById(Long id) {
		PopularityItem bean = popularityItemDao.deleteById(id);
		return bean;
	}
	
	public PopularityItem[] deleteByIds(Long[] ids) {
		PopularityItem[] beans = new PopularityItem[ids.length];
		for (int i = 0,len = ids.length; i < len; i++) {
			beans[i] = deleteById(ids[i]);
		}
		return beans;
	}
	
	public void save(Cart cart, Long popularityId){
		if(popularityId!=null){
			PopularityItem bean =findById(cart.getId(),popularityId);
			if(bean!=null){
				bean.setCount(bean.getCount()+1);
				update(bean);
			}else{
				bean =new PopularityItem();
				bean.setCart(cart);
				bean.setPopularityGroup(popularityGroupMng.findById(popularityId));
				bean.setCount(1);
				save(bean);
			}
			
		}
	}
	@Autowired
	private PopularityItemDao popularityItemDao;

	@Autowired
	private PopularityGroupMng popularityGroupMng;
}