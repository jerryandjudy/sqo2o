package com.jspgou.cms.manager.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jspgou.cms.dao.CollectDao;
import com.jspgou.cms.entity.Collect;
import com.jspgou.cms.entity.ShopMember;
import com.jspgou.cms.manager.CollectMng;
import com.jspgou.cms.manager.ProductFashionMng;
import com.jspgou.cms.manager.ProductMng;
import com.jspgou.cms.manager.ProductSiteMng;
import com.jspgou.common.hibernate3.Updater;
import com.jspgou.common.page.Pagination;
/**
* This class should preserve.
* @preserve
*/
@Service
@Transactional
public class CollectMngImpl implements CollectMng {
	@Transactional(readOnly = true)
	public Pagination getList(Integer pageSize,Integer pageNo,Long memberId){
		Pagination list = collectDao.getList(pageSize, pageNo, memberId);
		return list;
	}
	@Transactional(readOnly = true)
	public Pagination getList(Long siteId,Integer pageSize,Integer pageNo,Long memberId){
		Pagination list = collectDao.getList(siteId,pageSize, pageNo, memberId);
		return list;
	}
	
	@Transactional(readOnly = true)
	public Collect findById(Integer id) {
		Collect entity = collectDao.findById(id);
		return entity;
	}

	public Collect save(ShopMember bean,Long productId,Long fashionId) {
		Collect entity = new Collect();
		entity.setProductSite(productSiteMng.findById(productId));
		if(fashionId!=null){
		entity.setFashion(productFashionMng.findById(fashionId));
		}
		entity.setMember(bean);
		entity.setTime(new Date());
		collectDao.save(entity);
		return entity;
	}

	public Collect update(Collect bean,Long pTypeid) {
		Updater<Collect> updater = new Updater<Collect>(bean);
		Collect entity = collectDao.updateByUpdater(updater);
		
		return entity;
	}
	
	public List<Collect> findByProductId(Long productId){
		return collectDao.findByProductId(productId);
	}
	
	public Collect findByProductFashionId(Long id) {
		return collectDao.findByProductFashionId(id);
	}
	

	public Collect deleteById(Integer id) {
		
		Collect entity = findById(id);
		
		entity = collectDao.deleteById(id);
		return entity;
	}

	public Collect[] deleteByIds(Integer[] ids) {
		Collect[] beans = new Collect[ids.length];
		for (int i = 0, len = ids.length; i < len; i++) {
			beans[i] = deleteById(ids[i]);
		}
		return beans;
	}
	
	@Autowired
	private ProductSiteMng productSiteMng;
	@Autowired
	private ProductFashionMng productFashionMng;
	@Autowired
	private CollectDao collectDao;
}