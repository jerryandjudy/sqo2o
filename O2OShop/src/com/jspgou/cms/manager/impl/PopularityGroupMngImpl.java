package com.jspgou.cms.manager.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jspgou.common.hibernate3.Updater;
import com.jspgou.common.page.Pagination;
import com.jspgou.cms.dao.PopularityGroupDao;
import com.jspgou.cms.entity.PopularityGroup;
import com.jspgou.cms.manager.PopularityGroupMng;
import com.jspgou.cms.manager.ProductMng;

@Service
@Transactional
public class PopularityGroupMngImpl implements PopularityGroupMng {
	@Transactional(readOnly = true)
	public Pagination getPage(int pageNo, int pageSize) {
		Pagination page = popularityGroupDao.getPage(pageNo, pageSize);
		return page;
	}

	@Transactional(readOnly = true)
	public PopularityGroup findById(Long id) {
		PopularityGroup entity = null ;
		if(id!=null){
			entity = popularityGroupDao.findById(id);
		}
		return entity;
	}

	public PopularityGroup save(PopularityGroup bean) {
		bean.init();
		popularityGroupDao.save(bean);
		return bean;
	}

	public PopularityGroup update(PopularityGroup bean) {
		Updater<PopularityGroup> updater = new Updater<PopularityGroup>(bean);
		PopularityGroup entity = popularityGroupDao.updateByUpdater(updater);
		return entity;
	}

	public PopularityGroup deleteById(Long id) {
		PopularityGroup bean = findById(id);
		bean.getProducts().clear();
		popularityGroupDao.deleteById(id);
		return bean;
	}
	
	public PopularityGroup[] deleteByIds(Long[] ids) {
		PopularityGroup[] beans = new PopularityGroup[ids.length];
		for (int i = 0,len = ids.length; i < len; i++) {
			beans[i] = deleteById(ids[i]);
		}
		return beans;
	}

	 public void addProduct(PopularityGroup bean,Long productIds[]){
		double price = 0.0;
	    	if (productIds != null) {
				for (long productId : productIds) {
					bean.addToProducts(productMng.findById(productId));
//					price = price + productMng.findById(productId).getPrice();
				}
			}
	    bean.setPrice(price); 
	  }
	
	  public void updateProduct(PopularityGroup bean,Long productIds[]){
		  double price = 0.0;
		  bean.getProducts().clear();
	    	if (productIds != null) {
	    		for (long productId : productIds) {
					bean.addToProducts(productMng.findById(productId));
//					price = price + productMng.findById(productId).getPrice();
				}
			}
	      bean.setPrice(price);   
	    }

	  @Autowired
	private PopularityGroupDao popularityGroupDao;

	@Autowired
	private ProductMng productMng;
	
}