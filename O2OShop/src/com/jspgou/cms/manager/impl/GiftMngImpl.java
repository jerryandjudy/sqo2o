package com.jspgou.cms.manager.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jspgou.common.hibernate3.Updater;
import com.jspgou.common.page.Pagination;
import com.jspgou.cms.dao.GiftDao;
import com.jspgou.cms.dao.ShopMemberDao;
import com.jspgou.cms.entity.Gift;
import com.jspgou.cms.entity.ShopMember;
import com.jspgou.cms.manager.GiftMng;
/**
* This class should preserve.
* @preserve
*/
@Service
@Transactional
public class GiftMngImpl implements GiftMng {

	public Gift deleteById(Long id) {
		return giftDao.deleteById(id);
	}

	public Gift[] deleteByIds(Long[] ids) {
		Gift[] beans = new Gift[ids.length];
		for (int i = 0, len = ids.length; i < len; i++) {
			beans[i] = deleteById(ids[i]);
		}
		return beans;
		
	}

	public Gift findById(Long id) {
		return giftDao.findById(id);
	}

	public Pagination getPageGift(int pageNo, int pageSize) {
		return giftDao.getPageGift(pageNo, pageSize);
	}

	public Gift save(Gift bean) {
		return giftDao.save(bean);
	}
	
	public Gift updateByGiftnumb(Long giftId,Integer giftNumb,Long shopMemberId){
		Gift gift=giftDao.findById(giftId);
		ShopMember smber=shopMemberDao.findById(shopMemberId);
		Integer stock=gift.getGiftStock();
		Integer totalScore=gift.getGiftScore()*giftNumb;
		if(stock<giftNumb){
			return null;
		}else if(totalScore>smber.getScore()){
			return null;
		}else{
			gift.setGiftStock(stock-giftNumb);
			smber.setScore(smber.getScore()-totalScore);
		}
		return gift;
	}

	public Gift updateByUpdater(Gift bean) {
		  Updater<Gift> updater = new Updater<Gift>(bean);
			return giftDao.updateByUpdater(updater);
	}
	
      @Autowired
      private GiftDao giftDao;
      @Autowired
      private ShopMemberDao shopMemberDao;
}