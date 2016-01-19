package com.jspgou.cms.manager.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jspgou.common.hibernate3.Updater;
import com.jspgou.common.page.Pagination;
import com.jspgou.cms.dao.ShopArticleContentDao;
import com.jspgou.cms.entity.ShopArticle;
import com.jspgou.cms.entity.ShopArticleContent;
import com.jspgou.cms.manager.ShopArticleContentMng;

/**
* This class should preserve.
* @preserve
*/
@Service
@Transactional
public class ShopArticleContentMngImpl implements ShopArticleContentMng {
	@Transactional(readOnly = true)
	public Pagination getPage(int pageNo, int pageSize) {
		Pagination page = shopArticleContentDao.getPage(pageNo, pageSize);
		return page;
	}

	@Transactional(readOnly = true)
	public ShopArticleContent findById(Long id) {
		ShopArticleContent entity = shopArticleContentDao.findById(id);
		return entity;
	}

	public ShopArticleContent save(String content, ShopArticle article) {
		ShopArticleContent bean = new ShopArticleContent();
		bean.setContent(content);
		bean.setArticle(article);
		shopArticleContentDao.save(bean);
		article.setArticleContent(bean);
		return bean;
	}

	public ShopArticleContent update(ShopArticleContent bean) {
		Updater<ShopArticleContent> updater = new Updater<ShopArticleContent>(
				bean);
		ShopArticleContent entity = shopArticleContentDao.updateByUpdater(updater);
		return entity;
	}

	public ShopArticleContent deleteById(Long id) {
		ShopArticleContent bean = shopArticleContentDao.deleteById(id);
		return bean;
	}

	public ShopArticleContent[] deleteByIds(Long[] ids) {
		ShopArticleContent[] beans = new ShopArticleContent[ids.length];
		for (int i = 0, len = ids.length; i < len; i++) {
			beans[i] = deleteById(ids[i]);
		}
		return beans;
	}
	@Autowired
	private ShopArticleContentDao shopArticleContentDao;

}