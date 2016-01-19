package com.jspgou.cms.manager.impl;

import static com.jspgou.common.web.Constants.POINT;
import static com.jspgou.common.web.Constants.SPT;

import java.io.File;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.commons.io.FilenameUtils;
import org.apache.commons.lang.ArrayUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.jspgou.cms.dao.ProductSiteDao;
import com.jspgou.cms.dao.ProductFashionDao;
import com.jspgou.cms.entity.Category;
import com.jspgou.cms.entity.Collect;
import com.jspgou.cms.entity.Consult;
import com.jspgou.cms.entity.Gys;
import com.jspgou.cms.entity.Product;
import com.jspgou.cms.entity.ProductExt;
import com.jspgou.cms.entity.ProductFashion;
import com.jspgou.cms.entity.ProductSite;
import com.jspgou.cms.entity.ProductStandard;
import com.jspgou.cms.entity.ProductTag;
import com.jspgou.cms.entity.ProductText;
import com.jspgou.cms.entity.ProductType;
import com.jspgou.cms.manager.BrandMng;
import com.jspgou.cms.manager.CartItemMng;
import com.jspgou.cms.manager.CategoryMng;
import com.jspgou.cms.manager.CollectMng;
import com.jspgou.cms.manager.ConsultMng;
import com.jspgou.cms.manager.ProductExtMng;
import com.jspgou.cms.manager.ProductFashionMng;
import com.jspgou.cms.manager.ProductMng;
import com.jspgou.cms.manager.ProductSiteMng;
import com.jspgou.cms.manager.ProductStandardMng;
import com.jspgou.cms.manager.ProductTagMng;
import com.jspgou.cms.manager.ProductTextMng;
import com.jspgou.cms.manager.ShopConfigMng;
import com.jspgou.common.file.FileNameUtils;
import com.jspgou.common.hibernate3.Updater;
import com.jspgou.common.image.ImageScale;
import com.jspgou.common.image.ImageUtils;
import com.jspgou.common.page.Pagination;
import com.jspgou.common.web.springmvc.RealPathResolver;
import com.jspgou.core.entity.Website;
import com.jspgou.core.manager.WebsiteMng;
/**
* This class should preserve.
* @preserve
*/
@Service
@Transactional
public class ProductSiteMngImpl implements ProductSiteMng {
	
	public List<ProductSite> getList(Long typeId,Long brandId,String productName){
		return productSiteDao.getList(typeId, brandId, productName, true);
	}
	
	public void resetSaleTop(){
		List<ProductSite> list = getList(null,null,null);
		for(ProductSite product:list){
			product.setSaleCount(0);
			update(product);
		}
	}
	
	public void resetProfitTop(){
		List<ProductSite> list = getList(null,null,null);
		for(ProductSite product:list){
			product.setLiRun(0.0);
			update(product);
		}
	}
	
	public void updateViewCount(ProductSite product){
		Product beans=product.getProduct();
		beans.setViewCount(product.getProduct().getViewCount()+1);
		product.setProduct(beans);
		product.setViewCount(product.getViewCount()+1);
	}
	
	@Transactional(readOnly = true)
	public Pagination getPage(Long webId, Long ctgId, String productName,String brandName,
			Boolean isOnSale,Boolean isRecommend,Boolean isSpecial,Boolean isHotsale,
			Boolean isNewProduct,Long typeId,Double startSalePrice,Double endSalePrice,
			Integer startStock,Integer endStock,int pageNo, int pageSize) {
		Pagination page;
			page = productSiteDao.getPage(webId,ctgId,productName,brandName,isOnSale,
					isRecommend, isSpecial, isHotsale,isNewProduct,typeId,
					startSalePrice,endSalePrice,startStock,endStock, pageNo,
					pageSize, false);
		return page;
	}
	@Transactional(readOnly = true)
	public Pagination getPageNotJy(Gys gys,Long webId, Long ctgId, String productName,String brandName,
			Boolean isOnSale,Boolean isRecommend,Boolean isSpecial,Boolean isHotsale,
			Boolean isNewProduct,Long typeId,Double startSalePrice,Double endSalePrice,
			Integer startStock,Integer endStock,int pageNo, int pageSize) {
		Pagination page;
		page = productSiteDao.getPageNotJy( gys,webId,ctgId,productName,brandName,isOnSale,
				isRecommend, isSpecial, isHotsale,isNewProduct,typeId,
				startSalePrice,endSalePrice,startStock,endStock, pageNo,
				pageSize, false);
		return page;
	}
	
	
	public Pagination getPage(int orderBy,int pageNo, int pageSize){
		return productSiteDao.getPage(orderBy, pageNo, pageSize, true);
	}

	@Transactional(readOnly = true)
	public Pagination getPageForTag(Long webId, Long ctgId, Long tagId,
			Boolean isRecommend, Boolean isSpecial, int pageNo, int pageSize) {
		Pagination page;
		if (tagId != null) {
			page = productSiteDao.getPageByTag(tagId, ctgId, isRecommend, isSpecial,
					pageNo, pageSize, true);
		} else {
			if (ctgId != null) {
				page = productSiteDao.getPageByCategory(ctgId, null,null,true,isRecommend, isSpecial,
						null,null,null,null,null,null,null,pageNo, pageSize, true);
			} else {
				page = productSiteDao.getPageByWebsite(webId, null,null,true,isRecommend, isSpecial,
						null,null,null,null,null,null,null,pageNo, pageSize, true);
			}
		}
		return page;
	}
	
	public Pagination getPageByStockWarning(Long webId,Integer count,Boolean status,int pageNo, int pageSize){
		return productSiteDao.getPageByStockWarning(webId,count, status,pageNo, pageSize);
	}
	
	@Transactional(readOnly = true)
	public Pagination getPageForTagChannel(Long brandId,Long webId, Long ctgId, Long tagId,//栏目页商品分页manager(wangzewu)
			Boolean isRecommend, String[] names,String[] values, Boolean isSpecial, int orderBy,int pageNo, int pageSize) {
		Pagination page;
		if (tagId != null) {
			page = productSiteDao.getPageByTag(tagId, ctgId, isRecommend, isSpecial,pageNo, pageSize, true);
		} else {
			if (ctgId != null) {
				page = productSiteDao.getPageByCategoryChannel(webId,brandId, ctgId, isRecommend,names,values,isSpecial,orderBy,pageNo, pageSize, true);
			} else {
				page = productSiteDao.getPageByWebsite(webId, null,null,true,isRecommend, isSpecial,null,null,null,null,null,null,null,pageNo, pageSize, true);
			}
		}
		return page;
	}
	@Transactional(readOnly = true)
	public Pagination getPageForTagChannel(String productName,String area,Long brandId,Long webId, Long ctgId, Long tagId,//栏目页商品分页manager(wangzewu)
			Boolean isRecommend, String[] names,String[] values, Boolean isSpecial, int orderBy,int pageNo, int pageSize) {
		Pagination page;
		if (tagId != null) {
			page = productSiteDao.getPageByTag(productName,area,tagId, ctgId, isRecommend, isSpecial,pageNo, pageSize, false);
		} else {
			if (ctgId != null) {
				page = productSiteDao.getPageByCategoryChannel(productName,area,webId,brandId, ctgId, isRecommend,names,values,isSpecial,orderBy,pageNo, pageSize, false);
			} else {
				page = productSiteDao.getPageByWebsite(orderBy,area,webId, productName,brandId,true,isRecommend, isSpecial,null,null,null,null,null,null,null,pageNo, pageSize, false);
			}
		}
		return page;
	}

	public List<ProductSite> getListForTag(Long webId, Long ctgId, Long tagId,Boolean isRecommend, Boolean isSpecial,Boolean isHostSale,Boolean isNewProduct, int firstResult,int maxResults) {
		List<ProductSite> list;
		if (tagId != null) {
			list = productSiteDao.getListByTag(webId,tagId, ctgId, isRecommend, isSpecial,firstResult, maxResults, false);
		} else {
			if (ctgId != null) {
				list = productSiteDao.getListByCategory(webId,ctgId, isRecommend, isSpecial,firstResult, maxResults, false);
			} else {
				list = productSiteDao.getListByWebsite1(webId, isRecommend, isSpecial,isHostSale,isNewProduct,firstResult, maxResults, false);
			}
		}
		return list;
	}
	
	public List<ProductSite> getIsRecommend(Boolean isRecommend,int count){
		return productSiteDao.getIsRecommend(isRecommend, count);
	}
	
	public List<ProductSite> getIsRecommend(Boolean isRecommend,int count,Long webId){
		return productSiteDao.getIsRecommend(isRecommend, count,webId);
	}
	public List<ProductSite> getListBySiteId(Long siteId){
		return productSiteDao.getListBySiteId(siteId);
	}
	public Integer[] getProductSiteByTag(Long webId){
		return productSiteDao.getProductSiteByTag(webId);
	}

	public int deleteTagAssociation(ProductTag[] tags) {
		if (ArrayUtils.isEmpty(tags)) {
			return 0;
		}
		return productSiteDao.deleteTagAssociation(tags);
	}

	@Transactional(readOnly = true)
	public ProductSite findById(Long id) {
		ProductSite entity = productSiteDao.findById(id);
		return entity;
	}
	@Transactional(readOnly = true)
	public List<ProductSite> getChildListByIdAndSiteId(Long id,Long siteId) {
		return productSiteDao.getChildListByIdAndSiteId(id,siteId);
	}

	
	public ProductSite updateByUpdater(ProductSite bean) {
		Updater<ProductSite> updater = new Updater<ProductSite>(bean);
		ProductSite entity = productSiteDao.updateByUpdater(updater);
		return entity;
	}


	@Autowired
	private ProductFashionDao productFashionDao;
	@Autowired
	private ProductFashionMng productFashionMng;
	@Autowired
	private CollectMng collectMng;
	@Autowired
	private ConsultMng consultMng;
	@Autowired
	private CartItemMng cartItemMng;
	@Autowired
	private RealPathResolver realPathResolver;
	@Autowired
	private WebsiteMng websiteMng;
	@Autowired
	private CategoryMng categoryMng;
	@Autowired
	private ProductTagMng productTagMng;
	@Autowired
	private ProductTextMng productTextMng;
	@Autowired
	private ShopConfigMng shopConfigMng;
	@Autowired
	private BrandMng brandMng;
	@Autowired
	private ImageScale imageScale;
	@Autowired
	private ProductExtMng productExtMng;
	@Autowired
	private ProductStandardMng productStandardMng;
	@Autowired
	private ProductSiteDao productSiteDao;

	@Override
	public ProductSite save(ProductSite bean) {
		// TODO Auto-generated method stub
		return productSiteDao.save(bean);
	}

	@Override
	public ProductSite update(ProductSite bean, ProductExt ext, Long ctgId,
			Long brandId, Long[] tagIds, String[] keywords, String[] names,
			String[] values, Map<String, String> attr,
			String[] fashionSwitchPic, String[] fashionBigPic,
			String[] fashionAmpPic, MultipartFile file) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ProductSite update(ProductSite bean) {
		// TODO Auto-generated method stub
		Updater<ProductSite> updater = new Updater<ProductSite>(bean);
		return productSiteDao.updateByUpdater(updater);
	}

	@Override
	public ProductSite[] deleteByIds(Long[] ids) {
		ProductSite[] beans=new ProductSite[ids.length];
		for(int i=0;i<ids.length;i++){
			beans[i] = productSiteDao.deleteById(ids[i]);
		}
		// TODO Auto-generated method stub
		return beans;
	}
	@Override
	public ProductSite deleteById(Long id) {
		// TODO Auto-generated method stub
		return productSiteDao.deleteById(id);
	}

	@Override
	public Integer getStoreByProductSitePattern(Long id, Long fashId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Integer getTotalStore(Long productId) {
		// TODO Auto-generated method stub
		return null;
	}


	@Override
	public List<ProductSite> getHistoryProductSite(HashSet<Long> set,
			Integer count) {
		// TODO Auto-generated method stub
		return productSiteDao.getHistoryProductSite(set,count);
	}

	@Override
	public ProductSite save(ProductSite bean, ProductExt ext, Long webId,
			Long categoryId, Long brandId, Long[] tagIds, String[] keywords,
			String[] names, String[] values, String[] fashionSwitchPic,
			String[] fashionBigPic, String[] fashionAmpPic, MultipartFile file) {
		// TODO Auto-generated method stub
		return null;
	}

}