package com.jspgou.cms.manager.impl;

import java.sql.Timestamp;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jspgou.cms.dao.JyspDao;
import com.jspgou.cms.dao.ProductDao;
import com.jspgou.cms.dao.ProductSiteDao;
import com.jspgou.cms.entity.Gys;
import com.jspgou.cms.entity.Jysp;
import com.jspgou.cms.entity.ProductSite;
import com.jspgou.cms.manager.JyspMng;
import com.jspgou.common.hibernate3.Updater;
import com.jspgou.common.page.Pagination;

/**
* This class should preserve.
* @preserve
*/
@Service
@Transactional
public class JyspMngImpl implements JyspMng {

	@Autowired
	public JyspDao jyspDao;
	@Autowired
	public ProductSiteDao productSiteDao;
	@Autowired
	public ProductDao productDao;

	@Override
	public Pagination getPageByGysId(Gys gys, int pageNo, int pageSize) {
		// TODO Auto-generated method stub
		return jyspDao.getPageByGysId(gys,  pageNo,  pageSize);
	}

	@Override
	public Jysp save(Jysp jysp) {
		List<ProductSite> productSiteList=productSiteDao.getChildListByIdAndSiteId(jysp.getProductSite().getId(),jysp.getGys().getWebsite().getId());
		if(productSiteList!=null&&productSiteList.size()>0){
			for(ProductSite productSite:productSiteList){
			Jysp jysps=new Jysp();
			jysps.setUsername(jysp.getUsername());
			jysps.setGys(jysp.getGys());
	    	Timestamp ts = new Timestamp(System.currentTimeMillis());  
	    	jysps.setCreateTime(ts);
	    	jysps.setProductSite(productSite);
	    	jyspDao.save(jysps);
			}
		}
		if(jysp.getId()==null){
			jysp=jyspDao.save(jysp);
		}
		return jysp;
	}
	@Override
	public Jysp update(Jysp bean) {
		// TODO Auto-generated method stub
		Updater<Jysp> updater = new Updater<Jysp>(bean);
		return jyspDao.updateByUpdater(updater);
	}
	@Override
	public Jysp delete(Long id) {
		// TODO Auto-generated method stub
		Jysp jysp=jyspDao.findById(id);
		List<Jysp> jysps=jyspDao.getChildListByIdAndSiteId(productDao.findById(jysp.getProductSite().getProduct().getId()),id, jysp.getGys().getWebsite().getId());
		if(jysps!=null&&jysps.size()>0){
			for(Jysp bean:jysps){
				jyspDao.deleteById(bean.getId());
			}
		}
		return jyspDao.deleteById(id);
	}

	@Override
	public Jysp findById(Long id) {
		// TODO Auto-generated method stub
		return jyspDao.findById(id);
	}
	@Override
	public Jysp findByProductIdAndKetaUserId(Long productId,Long ketaUserId){
		// TODO Auto-generated method stub
		return jyspDao.findByProductIdAndKetaUserId( productId, ketaUserId);
	}
	@Override
	public List<Gys> findGysByProductId(Long productId){
		// TODO Auto-generated method stub
		return jyspDao.findGysByProductId( productId);
	}
	@Override
	public List<Jysp> findByProductId(Long parentId,Long productId){
		// TODO Auto-generated method stub
		return jyspDao.findByProductId(parentId, productId);
	}

	@Override
	public Pagination getJyspPage(Long siteId, String username,
			String companyName,String productName,Long typeId, int pageNo, int pageSize) {
		// TODO Auto-generated method stub
		return jyspDao.getJyspPage(siteId, username, companyName, productName, typeId, pageNo, pageSize);
	}
	
}