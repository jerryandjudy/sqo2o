package com.jspgou.cms.manager.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jspgou.common.hibernate3.Updater;
import com.jspgou.common.page.Pagination;
import com.jspgou.cms.dao.ShopArticleDao;
import com.jspgou.cms.entity.ShopArticle;
import com.jspgou.cms.entity.ShopArticleContent;
import com.jspgou.cms.entity.ShopChannel;
import com.jspgou.cms.manager.ShopArticleContentMng;
import com.jspgou.cms.manager.ShopArticleMng;
import com.jspgou.cms.manager.ShopChannelMng;

/**
* This class should preserve.
* @preserve
*/
@Service
@Transactional
public class ShopArticleMngImpl implements ShopArticleMng {
	@Transactional(readOnly = true)
	public Pagination getPage(Long channelId,Long webId, int pageNo, int pageSize) {
		Pagination page = shopArticleDao.getPage(channelId,webId, pageNo, pageSize);
		return page;
	}
	@Transactional(readOnly = true)
	public Pagination getPage(Boolean hasTitleImg,Long channelId,Long webId, int pageNo, int pageSize) {
		Pagination page = shopArticleDao.getPage( hasTitleImg,channelId,webId, pageNo, pageSize);
		return page;
	}

	@Transactional(readOnly = true)
	public Pagination getPageForTag(Long webId, Long channelId, int pageNo,
			int pageSize) {
		Pagination page;
		if (channelId != null) {
			page = shopArticleDao.getPageByChannel(channelId, pageNo, pageSize, true);
		} else {
			page = shopArticleDao.getPageByWebsite(webId, pageNo, pageSize, true);
		}
		return page;
	}

	public List<ShopArticle> getListForTag(Long webId, Long channelId,
			int firstResult, int maxResults) {
		List<ShopArticle> list;
		if (channelId != null) {
			list = shopArticleDao.getListByChannel(channelId,webId, firstResult, maxResults,
					false);
		} else {
			list = shopArticleDao.getListByWebsite(webId, firstResult, maxResults, false);
		}
		return list;
	}
	public List<ShopArticle> getListForTag(Boolean hasTitleImg,Long webId, Long channelId,
			int firstResult, int maxResults) {
		List<ShopArticle> list;
		if (channelId != null) {
			list = shopArticleDao.getListByChannel(hasTitleImg,channelId,webId, firstResult, maxResults,
					false);
		} else {
			list = shopArticleDao.getListByWebsite(hasTitleImg,webId, firstResult, maxResults, false);
		}
		return list;
	}

	@Transactional(readOnly = true)
	public ShopArticle findById(Long id) {
		ShopArticle entity = shopArticleDao.findById(id);
		return entity;
	}

	public ShopArticle save(ShopArticle bean, Long channelId, String content) {
		ShopChannel channel = shopChannelMng.findById(channelId);
		bean.setChannel(channel);
		bean.init();
		shopArticleDao.save(bean);
		if (content != null) {
			shopArticleContentMng.save(content, bean);
		}
		return bean;
	}

	public ShopArticle update(ShopArticle bean, Long channelId, String content) {
		ShopArticle entity = findById(bean.getId());
		ShopArticleContent c = entity.getArticleContent();
		if (c != null) {
			c.setContent(content);
		} else {
			shopArticleContentMng.save(content, entity);
		}
		if (channelId != null) {
			entity.setChannel(shopChannelMng.findById(channelId));
		}
		Updater<ShopArticle> updater = new Updater<ShopArticle>(bean);
		entity = shopArticleDao.updateByUpdater(updater);
		return entity;
	}

	public ShopArticle deleteById(Long id) {
		ShopArticle bean = shopArticleDao.deleteById(id);
		return bean;
	}

	public ShopArticle[] deleteByIds(Long[] ids) {
		ShopArticle[] beans = new ShopArticle[ids.length];
		for (int i = 0, len = ids.length; i < len; i++) {
			beans[i] = deleteById(ids[i]);
		}
		return beans;
	}

	private ShopArticleContentMng shopArticleContentMng;
	private ShopChannelMng shopChannelMng;
	@Autowired
	private ShopArticleDao shopArticleDao;


	@Autowired
	public void setShopChannelMng(ShopChannelMng shopChannelMng) {
		this.shopChannelMng = shopChannelMng;
	}

	@Autowired
	public void setShopArticleContentMng(
			ShopArticleContentMng shopArticleContentMng) {
		this.shopArticleContentMng = shopArticleContentMng;
	}

}