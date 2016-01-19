package com.jspgou.cms.manager.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jspgou.common.hibernate3.Updater;
import com.jspgou.common.page.Pagination;
import com.jspgou.cms.dao.ShopChannelContentDao;
import com.jspgou.cms.entity.ShopChannel;
import com.jspgou.cms.entity.ShopChannelContent;
import com.jspgou.cms.manager.ShopChannelContentMng;

/**
* This class should preserve.
* @preserve
*/
@Service
@Transactional
public class ShopChannelContentMngImpl implements ShopChannelContentMng {
	@Transactional(readOnly = true)
	public Pagination getPage(int pageNo, int pageSize) {
		Pagination page = shopChannelContentDao.getPage(pageNo, pageSize);
		return page;
	}

	@Transactional(readOnly = true)
	public ShopChannelContent findById(Long id) {
		ShopChannelContent entity = shopChannelContentDao.findById(id);
		return entity;
	}

	public ShopChannelContent save(String content, ShopChannel channel) {
		ShopChannelContent bean = new ShopChannelContent();
		bean.setContent(content);
		bean.setChannel(channel);
		shopChannelContentDao.save(bean);
		channel.setChannelContent(bean);
		return bean;
	}

	public ShopChannelContent update(ShopChannelContent bean) {
		Updater<ShopChannelContent> updater = new Updater<ShopChannelContent>(
				bean);
		ShopChannelContent entity = shopChannelContentDao.updateByUpdater(updater);
		return entity;
	}

	public ShopChannelContent deleteById(Long id) {
		ShopChannelContent bean = shopChannelContentDao.deleteById(id);
		return bean;
	}

	public ShopChannelContent[] deleteByIds(Long[] ids) {
		ShopChannelContent[] beans = new ShopChannelContent[ids.length];
		for (int i = 0, len = ids.length; i < len; i++) {
			beans[i] = deleteById(ids[i]);
		}
		return beans;
	}

	@Autowired
	private ShopChannelContentDao shopChannelContentDao;
}