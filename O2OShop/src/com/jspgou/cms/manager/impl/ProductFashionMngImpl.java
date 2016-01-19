package com.jspgou.cms.manager.impl;

import static com.jspgou.common.web.Constants.POINT;
import static com.jspgou.common.web.Constants.SPT;

import java.io.File;
import java.util.List;

import org.apache.commons.io.FilenameUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.jspgou.common.file.FileNameUtils;
import com.jspgou.common.hibernate3.Updater;
import com.jspgou.common.image.ImageScale;
import com.jspgou.common.image.ImageUtils;
import com.jspgou.common.page.Pagination;
import com.jspgou.common.web.springmvc.RealPathResolver;
import com.jspgou.core.entity.Website;
import com.jspgou.core.manager.WebsiteMng;
import com.jspgou.cms.dao.ProductFashionDao;
import com.jspgou.cms.entity.Category;
import com.jspgou.cms.entity.Product;
import com.jspgou.cms.entity.ProductFashion;
import com.jspgou.cms.entity.ProductType;
import com.jspgou.cms.manager.CategoryMng;
import com.jspgou.cms.manager.ProductFashionMng;
import com.jspgou.cms.manager.StandardMng;
/**
* This class should preserve.
* @preserve
*/
@Service
@Transactional
public class ProductFashionMngImpl implements ProductFashionMng{
	
	public ProductFashion deleteById(Long id) {
		return productFashionDao.deleteById(id);
	}

	public ProductFashion[] deleteByIds(Long[] ids) {
		ProductFashion[] beans = new ProductFashion[ids.length];
		for (int i = 0, len = ids.length; i < len; i++) {
			beans[i] = deleteById(ids[i]);
		}
		return beans;
	}

	public ProductFashion findById(Long id) {
		return productFashionDao.findById(id);
	}
	
	public List<ProductFashion> findByProductId(Long productId){
		return productFashionDao.findByProductId(productId);
	}

	public Pagination getPage(Long productId, int pageNo, int pageSize) {
		return productFashionDao.getPage(productId,pageNo, pageSize);
	}
	
	public ProductFashion save(ProductFashion bean,String[] arr){
		String attitude = "";
		for(String a:arr){
			attitude =attitude +" "+standardMng.findById(Long.parseLong(a)).getName();
		}
		bean.setAttitude(attitude);
		bean.init();
		return productFashionDao.save(bean);
	}

	public void addStandard(ProductFashion bean,String[] arr){
		for(String a:arr){
			bean.addTostandards(standardMng.findById(Long.parseLong(a)));
		}
	}
	
	
	public void updateStandard(ProductFashion bean,String[] arr){
		bean.getStandards().clear();
		for(String a:arr){
			bean.addTostandards(standardMng.findById(Long.parseLong(a)));
		}
	}
	
	public void deleteImage(ProductFashion entity, String uploadPath) {
		/*String detail = entity.getDetailImg();
		if (!StringUtils.isBlank(detail)) {
			new File(uploadPath + detail).delete();
		}
		String list = entity.getListImg();
		if (!StringUtils.isBlank(list)) {
			new File(uploadPath + list).delete();
		}
		String min = entity.getMinImg();
		if (!StringUtils.isBlank(min)) {
			new File(uploadPath + min).delete();
		}*/
	}
	
	public ProductFashion update(ProductFashion bean,String[] arr) {
		String attitude = "";
		for(String a:arr){
			attitude =attitude +" "+standardMng.findById(Long.parseLong(a)).getName();
		}
		bean.setAttitude(attitude);
		Updater<ProductFashion> updater = new Updater<ProductFashion>(bean);
		ProductFashion entity = productFashionDao.updateByUpdater(updater);
		return entity;
	}
	
	public ProductFashion update(ProductFashion bean) {
		Updater<ProductFashion> updater = new Updater<ProductFashion>(bean);
		ProductFashion entity = productFashionDao.updateByUpdater(updater);
		return entity;
	}

	public Pagination productLack(Integer status,Integer count, int pageNo, int pageSize) {
		return productFashionDao.productLack(status,count, pageNo, pageSize);
	}

	public Integer productLackCount(Integer status,Integer count) {
		return productFashionDao.productLackCount(status,count);
	}

	public Pagination getSaleTopPage(int pageNo, int pageSize) {
		return productFashionDao.getSaleTopPage(pageNo, pageSize);
	}
	
	public Boolean getOneFash(Long productId){
		return productFashionDao.getOneFashion(productId);
	}
	
	@Autowired
	private ProductFashionDao productFashionDao;
	
	@Autowired
	private WebsiteMng websiteMng;
	@Autowired
	private RealPathResolver realPathResolver;
	@Autowired
	private CategoryMng categoryMng;
	@Autowired
	private ImageScale imageScale;
	@Autowired
	private StandardMng standardMng;

}

