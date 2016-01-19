package com.jspgou.cms.manager.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jspgou.common.page.Pagination;
import com.jspgou.cms.dao.ConsultDao;
import com.jspgou.cms.entity.Consult;
import com.jspgou.cms.manager.ConsultMng;
import com.jspgou.cms.manager.ProductMng;
import com.jspgou.cms.manager.ProductSiteMng;
import com.jspgou.cms.manager.ShopMemberMng;
/**
* This class should preserve.
* @preserve
*/
@Service
@Transactional
public class ConsultMngImpl implements ConsultMng {
	@Transactional(readOnly = true)
	public Consult findById(Long id) {
		Consult entity = consultDao.findById(id);
		return entity;
	}

	public Consult saveOrUpdate(Long productId,String content,Long memberId){
		Consult bean=new Consult();
		bean.setConsult(content);
		bean.setMember(shopMemberMng.findById(memberId));
		bean.setTime(new Date());
		bean.setProductSite(productSiteMng.findById(productId));
		Consult consult=consultDao.getSameConsult(memberId);
		Long time=System.currentTimeMillis();
		if(consult==null){
			return consultDao.saveOrUpdate(bean);
		}else{
			if((time-consult.getTime().getTime())/1000<30){
				return null;
			}else{
				return consultDao.saveOrUpdate(bean);
			}
		}
		
	}
	
	public List<Consult> findByProductId(Long productId) {
		return consultDao.findByProductId(productId);
	}
	
	public Pagination getPageByMember(Long memberId,int pageNo,int pageSize,boolean cache){
		return consultDao.getPageByMember(memberId, pageNo, pageSize, cache);
	}
	
	public Pagination getPage(Long productId,String userName,String productName,
			Date startTime,Date endTime,int pageNo,int pageSize,Boolean cache){
		return consultDao.getPage(productId,userName,productName,startTime,endTime,pageNo, pageSize, cache);
	}

	public Consult update(Consult Consult) {
		return consultDao.update(Consult);
	}

	public Consult deleteById(Long id) {
		Consult bean = consultDao.deleteById(id);
		return bean;
	}
	
	public Consult[] deleteByIds(Long[] ids){
		Consult[] beans = new Consult[ids.length];
		for (int i = 0, len = ids.length; i < len; i++) {
			beans[i] = deleteById(ids[i]);
		}
		return beans;
	}
	
	@Autowired
	private ShopMemberMng shopMemberMng;
	@Autowired
	private ProductSiteMng productSiteMng;
	@Autowired
	private ConsultDao consultDao;
	

}