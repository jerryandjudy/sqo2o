package com.jspgou.cms.manager.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jspgou.cms.dao.BldDao;
import com.jspgou.cms.dao.JxcBldGoodsNumDao;
import com.jspgou.cms.dao.LsfcDao;
import com.jspgou.cms.dao.OrganizationDao;
import com.jspgou.cms.entity.Bld;
import com.jspgou.cms.entity.Gys;
import com.jspgou.cms.entity.Jmf;
import com.jspgou.cms.entity.JxcBldGoodsNum;
import com.jspgou.cms.entity.Lsfc;
import com.jspgou.cms.entity.Order;
import com.jspgou.cms.manager.BldMng;
import com.jspgou.cms.manager.JxcBldGoodsNumMng;
import com.jspgou.common.hibernate3.Updater;
import com.jspgou.common.page.Pagination;
import com.jspgou.core.dao.AccountDao;
import com.jspgou.core.dao.WebsiteDao;
import com.jspgou.core.entity.Account;
import com.jspgou.core.entity.Website;
/**
* This class should preserve.
* @preserve
*/
@Service
@Transactional
public class JxcBldGoodsNumMngImpl implements JxcBldGoodsNumMng {
	@Override
	@Transactional(readOnly = true)
	public JxcBldGoodsNum findByProductIdAndKetaUserId(Long productId,
			Long ketaUserId) {
		// TODO Auto-generated method stub
		return jxcBldGoodsNumDao.findByProductIdAndKetaUserId(productId, ketaUserId);
	}
 
	@Autowired
	private JxcBldGoodsNumDao jxcBldGoodsNumDao;

	@Override
	public JxcBldGoodsNum update(JxcBldGoodsNum bean) {
		// TODO Auto-generated method stub
		Updater<JxcBldGoodsNum> updater = new Updater<JxcBldGoodsNum>(bean);
		JxcBldGoodsNum entity = jxcBldGoodsNumDao.updateByUpdater(updater);
		return entity;
	}
	@Override
	public Pagination getPage(Long siteId,String username,String companyName, int pageNo, int pageSize){
		return jxcBldGoodsNumDao.getPage(siteId, username, companyName, pageNo, pageSize);
	}
}