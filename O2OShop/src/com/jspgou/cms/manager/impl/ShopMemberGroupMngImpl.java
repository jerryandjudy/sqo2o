package com.jspgou.cms.manager.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jspgou.common.hibernate3.Updater;
import com.jspgou.cms.dao.ShopMemberGroupDao;
import com.jspgou.cms.entity.ShopMemberGroup;
import com.jspgou.cms.manager.ShopMemberGroupMng;

/**
* This class should preserve.
* @preserve
*/
@Service
@Transactional
public class ShopMemberGroupMngImpl implements ShopMemberGroupMng {
	/**
	 * @see ShopMemberGroupMng#findGroupByScore(Long, int)
	 */
	public ShopMemberGroup findGroupByScore(Long webId, int score) {
		List<ShopMemberGroup> groupList = shopMemberGroupDao.getList(webId, true);
		int size = groupList.size();
		if (size < 1) {
			throw new IllegalStateException(
					"ShopMmeberGroup not found in website id=" + webId);
		} else if (size == 1) {
			return groupList.get(0);
		}
		ShopMemberGroup group = groupList.get(0);
		ShopMemberGroup temp;
		for (int i = size - 1; i > 0; i--) {
			temp = groupList.get(i);
			if (score > temp.getScore()) {
				group = temp;
				break;
			}
		}
		return group;
	}

	@Transactional(readOnly = true)
	public List<ShopMemberGroup> getList(Long webId) {
		return shopMemberGroupDao.getList(webId, false);
	}

	@Transactional(readOnly = true)
	public ShopMemberGroup findById(Long id) {
		ShopMemberGroup entity = shopMemberGroupDao.findById(id);
		return entity;
	}

	public ShopMemberGroup save(ShopMemberGroup bean) {
		shopMemberGroupDao.save(bean);
		return bean;
	}

	public ShopMemberGroup update(ShopMemberGroup bean) {
		Updater<ShopMemberGroup> updater = new Updater<ShopMemberGroup>(bean);
		ShopMemberGroup entity = shopMemberGroupDao.updateByUpdater(updater);
		return entity;
	}

	public ShopMemberGroup deleteById(Long id) {
		ShopMemberGroup bean = shopMemberGroupDao.deleteById(id);
		return bean;
	}

	public ShopMemberGroup[] deleteByIds(Long[] ids) {
		ShopMemberGroup[] beans = new ShopMemberGroup[ids.length];
		for (int i = 0, len = ids.length; i < len; i++) {
			beans[i] = deleteById(ids[i]);
		}
		return beans;
	}

	@Autowired
	private ShopMemberGroupDao shopMemberGroupDao;

}