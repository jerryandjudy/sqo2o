package com.jspgou.cms.manager.impl;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jspgou.common.hibernate3.Updater;
import com.jspgou.common.page.Pagination;
import com.jspgou.cms.dao.GiftExchangeDao;
import com.jspgou.cms.entity.Gift;
import com.jspgou.cms.entity.GiftExchange;
import com.jspgou.cms.entity.ShopMember;
import com.jspgou.cms.entity.ShopMemberAddress;
import com.jspgou.cms.entity.ShopScore;
import com.jspgou.cms.entity.ShopScore.ScoreTypes;
import com.jspgou.cms.manager.GiftExchangeMng;
import com.jspgou.cms.manager.ShopScoreMng;

@Service
@Transactional
public class GiftExchangeMngImpl implements GiftExchangeMng {
	@Transactional(readOnly = true)
	public Pagination getPage(int pageNo, int pageSize) {
		Pagination page = giftExchangeDao.getPage(pageNo, pageSize);
		return page;
	}
	
	public List<GiftExchange> getlist(Long memberId){
		return giftExchangeDao.getlist(memberId);
	}

	@Transactional(readOnly = true)
	public GiftExchange findById(Long id) {
		GiftExchange entity = giftExchangeDao.findById(id);
		return entity;
	}

	public GiftExchange save(GiftExchange bean) {
		giftExchangeDao.save(bean);
		return bean;
	}
	
	public GiftExchange save(Gift gift,ShopMemberAddress shopMemberAddress,ShopMember member,Integer count){
		GiftExchange giftExchange = new GiftExchange();
		Date now = new Timestamp(System.currentTimeMillis());
		giftExchange.setAmount(count);
		giftExchange.setCreateTime(now);
		giftExchange.setDetailaddress(shopMemberAddress.getUsername()+","+shopMemberAddress.getTel()+","+shopMemberAddress.getDetailaddress()+","+shopMemberAddress.getPostCode());
		giftExchange.setGift(gift);
		giftExchange.setMember(member);
		giftExchange.setScore(gift.getGiftScore());
		giftExchange.setTotalScore(gift.getGiftScore()*count);
		giftExchange.setStatus(1);
		
		ShopScore shopScore=new ShopScore();
		shopScore.setMember(member);
		shopScore.setName(gift.getGiftName());
		shopScore.setScoreTime(new Date());
		shopScore.setStatus(true);
		shopScore.setUseStatus(true);
		shopScore.setScoreType(ScoreTypes.ORDER_SCORE.getValue());
		shopScore.setScore(gift.getGiftScore()*count);
		shopScoreMng.save(shopScore);
		member.setScore(member.getScore()-gift.getGiftScore()*count);
		return save(giftExchange);
	}

	public GiftExchange update(GiftExchange bean) {
		Updater<GiftExchange> updater = new Updater<GiftExchange>(bean);
		GiftExchange entity = giftExchangeDao.updateByUpdater(updater);
		return entity;
	}

	public GiftExchange deleteById(Long id) {
		GiftExchange bean = giftExchangeDao.deleteById(id);
		return bean;
	}
	
	public GiftExchange[] deleteByIds(Long[] ids) {
		GiftExchange[] beans = new GiftExchange[ids.length];
		for (int i = 0,len = ids.length; i < len; i++) {
			beans[i] = deleteById(ids[i]);
		}
		return beans;
	}

	@Autowired
	private GiftExchangeDao giftExchangeDao;
	@Autowired
	private ShopScoreMng shopScoreMng;
}