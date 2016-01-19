package com.jspgou.cms.manager.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jspgou.common.hibernate3.Updater;
import com.jspgou.common.page.Pagination;
import com.jspgou.cms.dao.ShopScoreDao;
import com.jspgou.cms.entity.ShopScore;
import com.jspgou.cms.manager.ShopScoreMng;

/**
* This class should preserve.
* @preserve
*/
@Service
@Transactional
public class ShopScoreMngImpl implements ShopScoreMng {
	@Transactional(readOnly = true)
	public Pagination getPage(Long memberId,Boolean status,Boolean useStatus,
			Date startTime,Date endTime,Integer pageSize,Integer pageNo){
		Pagination page = shopScoreDao.getPage(memberId,status,useStatus,
				startTime,endTime,pageNo, pageSize);
		return page;
	}
	
	@Transactional(readOnly = true)
	public List<ShopScore> getlist(String code){
		return shopScoreDao.getlist(code);
	}

	@Transactional(readOnly = true)
	public ShopScore findById(Long id) {
		ShopScore entity = shopScoreDao.findById(id);
		return entity;
	}

	public ShopScore save(ShopScore bean) {
		shopScoreDao.save(bean);
		return bean;
	}

	public ShopScore update(ShopScore bean) {
		Updater<ShopScore> updater = new Updater<ShopScore>(bean);
		ShopScore entity = shopScoreDao.updateByUpdater(updater);
		return entity;
	}

	public ShopScore deleteById(Long id) {
		ShopScore bean = shopScoreDao.deleteById(id);
		return bean;
	}
	
	public ShopScore[] deleteByIds(Long[] ids) {
		ShopScore[] beans = new ShopScore[ids.length];
		for (int i = 0,len = ids.length; i < len; i++) {
			beans[i] = deleteById(ids[i]);
		}
		return beans;
	}

	@Autowired
	private ShopScoreDao shopScoreDao;
}