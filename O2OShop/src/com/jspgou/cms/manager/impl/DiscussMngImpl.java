package com.jspgou.cms.manager.impl;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jspgou.common.page.Pagination;
import com.jspgou.cms.dao.DiscussDao;
import com.jspgou.cms.entity.Discuss;
import com.jspgou.cms.manager.DiscussMng;
import com.jspgou.cms.manager.ProductMng;
import com.jspgou.cms.manager.ProductSiteMng;
import com.jspgou.cms.manager.ShopMemberMng;
import com.jspgou.cms.manager.SqServiceMng;
/**
* This class should preserve.
* @preserve
*/
@Service
@Transactional
public class DiscussMngImpl implements DiscussMng {
	@Transactional(readOnly = true)
	public Discuss findById(Long id) {
		Discuss entity = discussDao.findById(id);
		return entity;
	}

	public Discuss saveOrUpdate(Long productId,String content,Long memberId){
		Discuss bean=new Discuss();
		bean.setContent(content);
		bean.setMember(shopMemberMng.findById(memberId));
		bean.setProductSite(productSiteMng.findById(productId));
		bean.setTime(new Date());
		discussDao.saveOrUpdate(bean);
		return bean;
		
	}
	public Discuss saveOrUpdateSer(Long sqServiceId,String content,Long memberId){
		Discuss bean=new Discuss();
		bean.setContent(content);
		bean.setMember(shopMemberMng.findById(memberId));
		bean.setSqService(sqServiceMng.getById(sqServiceId));
		bean.setTime(new Date());
		discussDao.saveOrUpdate(bean);
		return bean;
		
	}
	
	public Pagination getPage(Long productId,String userName,String productName,
			Date startTime,Date endTime,int pageNo,int pageSize,boolean cache){
		return discussDao.getPageByProduct(productId,userName,productName,
				startTime,endTime,pageNo, pageSize, cache);
	}
	public Pagination getPageSer(Long sqServiceId,String userName,String productName,
			Date startTime,Date endTime,int pageNo,int pageSize,boolean cache){
		return discussDao.getPageBySqService(sqServiceId,userName,productName,
				startTime,endTime,pageNo, pageSize, cache);
	}
	public Pagination getPageByMember(Long memberId,int pageNo,int pageSize,boolean cache){
		return discussDao.getPageByMember(memberId, pageNo, pageSize, cache);
	}
	
	public Discuss update(Discuss Discuss) {
		return discussDao.update(Discuss);
	}
	
	public Discuss[] deleteByIds(Long[] ids){
		Discuss[] beans = new Discuss[ids.length];
		for (int i = 0, len = ids.length; i < len; i++) {
			beans[i] = deleteById(ids[i]);
		}
		return beans;
	}

	public Discuss deleteById(Long id) {
		Discuss bean = discussDao.deleteById(id);
		return bean;
	}

	@Autowired
	private ShopMemberMng shopMemberMng;
	@Autowired
	private SqServiceMng sqServiceMng;
	@Autowired
	private ProductSiteMng productSiteMng;
	@Autowired
	public DiscussDao discussDao;
	
}