package com.jspgou.cms.manager.impl;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jspgou.common.hibernate3.Updater;
import com.jspgou.cms.dao.ShopChannelDao;
import com.jspgou.cms.entity.ShopChannel;
import com.jspgou.cms.entity.ShopChannelContent;
import com.jspgou.cms.manager.ShopChannelContentMng;
import com.jspgou.cms.manager.ShopChannelMng;

/**
* This class should preserve.
* @preserve
*/
@Service
@Transactional
public class ShopChannelMngImpl implements ShopChannelMng {
	
	/**
	 * 获得根列表
	 * 
	 * @param webId
	 * @return
	 */
	public List<ShopChannel> getTopList(Long webId){
		return shopChannelDao.getTopList(webId, false,null);
	}
	
	/**
	 * 获得parentId子列表
	 * 
	 * @param webId
	 * 
	 * @param parentId
	 * @return
	 */
	public List<ShopChannel> getChildList(Long wegId,Long parentId){
		return shopChannelDao.getChildList(wegId, parentId);
	}
	
	@Transactional(readOnly = true)
	public List<ShopChannel> getList(Long webId) {
		List<ShopChannel> list = shopChannelDao.getTopList(webId, false,null);
		List<ShopChannel> allList = new ArrayList<ShopChannel>();
		addChildToList(allList, list);
		return allList;
	}

	@Transactional(readOnly = true)
	public List<ShopChannel> getArticleList(Long webId) {
		return shopChannelDao.getList(webId, ShopChannel.ARTICLE);
	}
	/*
	 * 获取单页栏目(wang ze wu)
	 */
	@Transactional(readOnly = true)
	public List<ShopChannel> getAloneChannelList(Long webId) {
		return shopChannelDao.getList(webId, ShopChannel.ALONE);
	}
	//获取页脚栏目
	public List<ShopChannel> getList(Long webId,Long idBegin,Long idEnd){
		return shopChannelDao.getList(webId,  ShopChannel.ARTICLE, idBegin, idEnd);
		
	}

	@Transactional(readOnly = true)
	public List<ShopChannel> getListForParent(Long webId, Long currId) {
		List<ShopChannel> allList = getList(webId);
		List<ShopChannel> childList = shopChannelDao.getListForChild(webId, currId);
		allList.removeAll(childList);
		return allList;
	}

	@Transactional(readOnly = true)
	public List<ShopChannel> getListForParentNoSort(Long webId, Long currId) {
		return shopChannelDao.getListForParent(webId, currId);
	}

	@Transactional(readOnly = true)
	public List<ShopChannel> getTopListForTag(Long webId,Integer count) {
		return shopChannelDao.getTopList(webId, true,count);
	}

	private void addChildToList(List<ShopChannel> to,
			Collection<ShopChannel> from) {
		Collection<ShopChannel> child;
		for (ShopChannel chnl : from) {
			to.add(chnl);
			child = chnl.getChild();
			if (child != null && child.size() > 0) {
				addChildToList(to, child);
			}
		}
	}

	public ShopChannel getByPath(Long webId, String path) {
		return shopChannelDao.getByPath(webId, path);
	}

	@Transactional(readOnly = true)
	public ShopChannel findById(Long id) {
		ShopChannel entity = shopChannelDao.findById(id);
		return entity;
	}

	public ShopChannel save(ShopChannel bean, Long parentId, String content) {
		ShopChannel parent = null;
		if (parentId != null) {
			parent = findById(parentId);
			bean.setParent(parent);
		}
		
		shopChannelDao.save(bean);
		if (content != null) {
			shopChannelContentMng.save(content, bean);
		}
		return bean;
	}

	public ShopChannel update(ShopChannel bean, Long parentId, String content) {
		ShopChannel entity = findById(bean.getId());
		ShopChannelContent c = entity.getChannelContent();
		if (c != null) {
			c.setContent(content);
		} else {
			shopChannelContentMng.save(content, entity);
		}
		if (parentId != null) {
			entity.setParent(findById(parentId));
		} else {
			entity.setParent(null);
		}
		Updater<ShopChannel> updater = new Updater<ShopChannel>(bean);
		entity = shopChannelDao.updateByUpdater(updater);
		return entity;
	}

	public ShopChannel deleteById(Long id) {
		ShopChannel bean = shopChannelDao.deleteById(id);
		return bean;
	}

	public ShopChannel[] deleteByIds(Long[] ids) {
		ShopChannel[] beans = new ShopChannel[ids.length];
		for (int i = 0, len = ids.length; i < len; i++) {
			beans[i] = deleteById(ids[i]);
		}
		return beans;
	}
	
	/**
	 * 更新排列顺序
	 * 
	 * @param ids
	 *            待排列ID数组
	 * @param priority
	 *            排列顺序
	 * @return 排序后产品对象数组
	 */
	public ShopChannel[] updatePriority(Long[] ids, Integer[] priority){
		ShopChannel[] beans = new ShopChannel[ids.length];
		for (int i = 0, len = ids.length; i < len; i++) {
			beans[i] = findById(ids[i]);
			beans[i].setPriority(priority[i]);
		}
		return beans;
	}

	private ShopChannelContentMng shopChannelContentMng;
	@Autowired
	private ShopChannelDao shopChannelDao;

	@Autowired
	public void setShopChannelContentMng(
			ShopChannelContentMng shopChannelContentMng) {
		this.shopChannelContentMng = shopChannelContentMng;
	}
}