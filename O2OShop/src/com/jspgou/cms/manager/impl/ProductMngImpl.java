package com.jspgou.cms.manager.impl;

import static com.jspgou.common.web.Constants.POINT;
import static com.jspgou.common.web.Constants.SPT;

import java.io.File;
import java.util.Arrays;
import java.util.Date;
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

import com.jspgou.cms.dao.ProductDao;
import com.jspgou.cms.dao.ProductFashionDao;
import com.jspgou.cms.dao.ProductSiteDao;
import com.jspgou.cms.entity.Category;
import com.jspgou.cms.entity.Collect;
import com.jspgou.cms.entity.Consult;
import com.jspgou.cms.entity.Gys;
import com.jspgou.cms.entity.Jysp;
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
import com.jspgou.cms.manager.JyspMng;
import com.jspgou.cms.manager.ProductExtMng;
import com.jspgou.cms.manager.ProductFashionMng;
import com.jspgou.cms.manager.ProductMng;
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
import com.jspgou.core.entity.User;
import com.jspgou.core.entity.Website;
import com.jspgou.core.manager.WebsiteMng;
/**
* This class should preserve.
* @preserve
*/
@Service
@Transactional
public class ProductMngImpl implements ProductMng {
	
	public List<Product> getList(Long typeId,Long brandId,String productName){
		return productDao.getList(typeId, brandId, productName, true);
	}
	
	public void resetSaleTop(){
		List<Product> list = getList(null,null,null);
		for(Product product:list){
			product.setSaleCount(0);
			update(product);
		}
	}
	
	public void resetProfitTop(){
		List<Product> list = getList(null,null,null);
		for(Product product:list){
			product.setLiRun(0.0);
			update(product);
		}
	}
	
	public void updateViewCount(Product product){
		product.setViewCount(product.getViewCount()+1);
	}
	
	@Transactional(readOnly = true)
	public Pagination getPage(Long webId, Long ctgId, String productName,String brandName,
			Boolean isOnSale,Boolean isRecommend,Boolean isSpecial,Boolean isHotsale,
			Boolean isNewProduct,Long typeId,Double startSalePrice,Double endSalePrice,
			Integer startStock,Integer endStock,int pageNo, int pageSize) {
		Pagination page;
		if (ctgId != null) {
			page = productDao.getPageByCategory(ctgId, productName,brandName,isOnSale,
					isRecommend, isSpecial, isHotsale,isNewProduct,typeId,
					startSalePrice,endSalePrice,startStock,endStock,pageNo,pageSize, false,null);
		} else {
			page = productDao.getPageByWebsite(webId,productName,brandName,isOnSale,
					isRecommend, isSpecial, isHotsale,isNewProduct,typeId,
					startSalePrice,endSalePrice,startStock,endStock, pageNo,
					pageSize, false);
		}
		return page;
	}
	@Transactional(readOnly = true)
	public Pagination getPage(Long ctgId, String productName,String brandName,
			Boolean isOnSale,Boolean isRecommend,Boolean isSpecial,Boolean isHotsale,
			Boolean isNewProduct,Long typeId,Double startSalePrice,Double endSalePrice,
			Integer startStock,Integer endStock,int pageNo, int pageSize) {
		Pagination page;
		if (ctgId != null) {
			page = productDao.getPageByCategory(ctgId, productName,brandName,isOnSale,
					isRecommend, isSpecial, isHotsale,isNewProduct,typeId,
					startSalePrice,endSalePrice,startStock,endStock,pageNo,pageSize, false,null);
		} else {
			page = productDao.getPage(productName,brandName,isOnSale,
					isRecommend, isSpecial, isHotsale,isNewProduct,typeId,
					startSalePrice,endSalePrice,startStock,endStock, pageNo,
					pageSize, false,null);
		}
		return page;
	}
	@Transactional(readOnly = true)
	public Pagination getPage( Long ctgId,String productName,String brandName,
 			Boolean isOnSale,Boolean isRecommend,
 			Boolean isSpecial,Boolean isHotsale,Boolean isNewProduct,
 			Long typeId,Double startSalePrice,Double endSalePrice,
 			Integer startStock,Integer endStock,int pageNo, int pageSize,Long parentId) {
		Pagination page;
		if (ctgId != null) {
			page = productDao.getPageByCategory(ctgId, productName,brandName,isOnSale,
					isRecommend, isSpecial, isHotsale,isNewProduct,typeId,
					startSalePrice,endSalePrice,startStock,endStock,pageNo,pageSize, false,parentId);
		} else {
			page = productDao.getPage(productName,brandName,isOnSale,
					isRecommend, isSpecial, isHotsale,isNewProduct,typeId,
					startSalePrice,endSalePrice,startStock,endStock, pageNo,
					pageSize, false,parentId);
		}
		return page;
	}
	@Transactional(readOnly = true)
	public Pagination getNotHasPage(Long siteId,Long ctgId, String productName,String brandName,
			Boolean isOnSale,Boolean isRecommend,Boolean isSpecial,Boolean isHotsale,
			Boolean isNewProduct,Long typeId,Double startSalePrice,Double endSalePrice,
			Integer startStock,Integer endStock,int pageNo, int pageSize) {
		Pagination page;
		if (ctgId != null) {
			page = productDao.getNotHasPageByCategory(siteId,ctgId, productName,brandName,isOnSale,
					isRecommend, isSpecial, isHotsale,isNewProduct,typeId,
					startSalePrice,endSalePrice,startStock,endStock,pageNo,pageSize, false);
		} else {
			page = productDao.getPage(productName,brandName,isOnSale,
					isRecommend, isSpecial, isHotsale,isNewProduct,typeId,
					startSalePrice,endSalePrice,startStock,endStock, pageNo,
					pageSize, false,null);
		}
		return page;
	}
	
	public Pagination getPage(int orderBy,int pageNo, int pageSize){
		return productDao.getPage(orderBy, pageNo, pageSize, true);
	}

	@Transactional(readOnly = true)
	public Pagination getPageForTag(Long webId, Long ctgId, Long tagId,
			Boolean isRecommend, Boolean isSpecial, int pageNo, int pageSize) {
		Pagination page;
		if (tagId != null) {
			page = productDao.getPageByTag(tagId, ctgId, isRecommend, isSpecial,
					pageNo, pageSize, true);
		} else {
			if (ctgId != null) {
				page = productDao.getPageByCategory(ctgId, null,null,true,isRecommend, isSpecial,
						null,null,null,null,null,null,null,pageNo, pageSize, true,null);
			} else {
				page = productDao.getPageByWebsite(webId, null,null,true,isRecommend, isSpecial,
						null,null,null,null,null,null,null,pageNo, pageSize, true);
			}
		}
		return page;
	}
	
	public Pagination getPageByStockWarning(Long webId,Integer count,Boolean status,int pageNo, int pageSize){
		return productDao.getPageByStockWarning(webId,count, status,pageNo, pageSize);
	}
	
	@Transactional(readOnly = true)
	public Pagination getPageForTagChannel(Long brandId,Long webId, Long ctgId, Long tagId,//栏目页商品分页manager(wangzewu)
			Boolean isRecommend, String[] names,String[] values, Boolean isSpecial, int orderBy,int pageNo, int pageSize) {
		Pagination page;
		if (tagId != null) {
			page = productDao.getPageByTag(tagId, ctgId, isRecommend, isSpecial,pageNo, pageSize, true);
		} else {
			if (ctgId != null) {
				page = productDao.getPageByCategoryChannel(brandId, ctgId, isRecommend,names,values,isSpecial,orderBy,pageNo, pageSize, true);
			} else {
				page = productDao.getPageByWebsite(webId, null,null,true,isRecommend, isSpecial,null,null,null,null,null,null,null,pageNo, pageSize, true);
			}
		}
		return page;
	}

	public List<Product> getListForTag(Long webId, Long ctgId, Long tagId,Boolean isRecommend, Boolean isSpecial,Boolean isHostSale,Boolean isNewProduct, int firstResult,int maxResults) {
		List<Product> list;
		if (tagId != null) {
			list = productDao.getListByTag(tagId, ctgId, isRecommend, isSpecial,firstResult, maxResults, true);
		} else {
			if (ctgId != null) {
				list = productDao.getListByCategory(ctgId, isRecommend, isSpecial,firstResult, maxResults, true);
			} else {
				list = productDao.getListByWebsite1(webId, isRecommend, isSpecial,isHostSale,isNewProduct,firstResult, maxResults, true);
			}
		}
		return list;
	}
	
	public List<Product> getIsRecommend(Boolean isRecommend,int count){
		return productDao.getIsRecommend(isRecommend, count);
	}
	
	public Integer[] getProductByTag(Long webId){
		return productDao.getProductByTag(webId);
	}

	public int deleteTagAssociation(ProductTag[] tags) {
		if (ArrayUtils.isEmpty(tags)) {
			return 0;
		}
		return productDao.deleteTagAssociation(tags);
	}

	@Transactional(readOnly = true)
	public Product findById(Long id) {
		Product entity = productDao.findById(id);
		return entity;
	}

	public Product save(User user,Product bean, ProductExt ext, Long webId, Long categoryId,
			Long brandId, Long[] tagIds, String[] keywords,String[] names,
			String[] values,String fashionSwitchPic[],String fashionBigPic[],String fashionAmpPic[],MultipartFile file) {
		ProductText text = bean.getProductText();
		if (text != null) {
			text.setProduct(bean);
		}
		if (bean.getParent() != null && bean.getParent().getId()!=null&& bean.getParent().getId()>0) {
			bean.setParent(productDao.findById(bean.getParent().getId()));
		}else{
			bean.setParent(null);
		}
		
		Category category = categoryMng.findById(categoryId);
		if (brandId != null) {
			bean.setBrand(brandMng.findById(brandId));
		}
		Website web = websiteMng.findById(webId);
		bean.setWebsite(web);
		bean.setConfig(shopConfigMng.findById(webId));
		bean.setCategory(category);
		bean.setType(category.getType());
		if (!ArrayUtils.isEmpty(tagIds)) {
			for (Long tagId : tagIds) {
				bean.addToTags(productTagMng.findById(tagId));
			}
		}
		if (!ArrayUtils.isEmpty(keywords)) {
			bean.setKeywords(Arrays.asList(keywords));
		}
		bean.init();
		productDao.save(bean);
		// 保存商品规格储存
		if (names != null && names.length > 0) {
			for (int i = 0, len = names.length; i < len; i++) {
				if (!StringUtils.isBlank(names[i])) {
					bean.addToExendeds(names[i], values[i]);
				}
			}
		}
		// 保存图片/大图片/放大图片集
		if (fashionSwitchPic != null && fashionSwitchPic.length > 0) {
			for (int i = 0, len = fashionSwitchPic.length; i < len; i++) {
				if (!StringUtils.isBlank(fashionSwitchPic[i])) {
					bean.addToPictures(fashionSwitchPic[i],fashionBigPic[i],fashionAmpPic[i]);
				}
			}
		}
		String uploadPath = realPathResolver.get(web.getUploadRel());
		saveImage(bean, ext, category.getType(), file, uploadPath);
		productExtMng.save(ext, bean);
		if(bean.getParent()!=null&&bean.getParent().getId()!=null){
			
			
//			Jysp  = jyspMng.findById(bean.getParent().getId());
			List<Gys> gyss=jyspMng.findGysByProductId(bean.getParent().getId());
			for(Gys gys:gyss){
				ProductSite productSite = productSiteDao.getProductSiteByProductIdAndWebId(bean.getId(),gys.getWebsite().getId());
				if(productSite==null){ 
				productSite=new ProductSite();
				productSite.setAlertInventory(0);
				productSite.setCostPrice(0d);
				productSite.setCreateTime(new Date());
				productSite.setProduct(bean);
				productSite.setWebsite(gys.getWebsite());
				productSiteDao.save(productSite);
				}
//				Jysp jysp=jyspMng.findByProductIdAndKetaUserId(productSite.getId(), gys.getKetaUser().getId());
				
				Jysp jysp=new Jysp();
				jysp.setGys(gys);
				jysp.setCreateTime(new Date());
				jysp.setGoodsMaxNum(0l);
				jysp.setGoodsMinNum(0l);
				jysp.setGoodsNum(0l);
				jysp.setGoodsPrice(0d);
				jysp.setUsername(user.getUsername());
				jysp.setProductSite(productSite);
				jyspMng.save(jysp);
			}
		}
		return bean;
	}
	
	public Product updateByUpdater(Product bean) {
		Updater<Product> updater = new Updater<Product>(bean);
		Product entity = productDao.updateByUpdater(updater);
		return entity;
	}

	public Product update(User user,Product bean, ProductExt ext, Long ctgId,
			Long brandId, Long[] tagIds, String[] keywords,String[] names,
			String[] values,Map<String, String> attr,
			String fashionSwitchPic[],String fashionBigPic[],String fashionAmpPic[],
			MultipartFile file) {
		Product entity = findById(bean.getId());
		if(entity.getParent()!=null&&entity.getParent().getId()!=null && bean.getParent().getId()!=entity.getParent().getId()){
//			Jysp  = jyspMng.findById(bean.getParent().getId());
			List<Jysp> jysps=jyspMng.findByProductId(entity.getParent().getId(), entity.getId());
			for(Jysp jysp:jysps){
				jyspMng.delete(jysp.getId());
			}
		}
		if (bean.getParent() != null && bean.getParent().getId()!=null&& bean.getParent().getId()>0) {
			bean.setParent(productDao.findById(bean.getParent().getId()));
		}else{
			bean.setParent(null);
		}
		ProductText text = bean.getProductText();
		// 先更新ProductText
		if (text != null) {
			ProductText ptext = entity.getProductText();
			if (ptext != null) {
				ptext.setText(text.getText());
				ptext.setText1(text.getText1());
				ptext.setText2(text.getText2());
			} else {
				text.setId(bean.getId());
				text.setProduct(entity);
				entity.setProductText(text);
				productTextMng.save(text);
			}
		}
		// 删除原tag
		entity.removeAllTags();
		// 设置类别、类型，不能为空
		Category category = categoryMng.findById(ctgId);
		entity.setCategory(category);
		entity.setType(category.getType());
//		if(entity.getOnSale()==null){
//			entity.setOnSale(false);
//		}
		// 设置品牌，可以为空
		if (brandId != null) {
			entity.setBrand(brandMng.findById(brandId));
		} else {
			entity.setBrand(null);
		}
		// 设置tag，可以为空
		if (!ArrayUtils.isEmpty(tagIds)) {
			for (Long tagId : tagIds) {
				entity.addToTags(productTagMng.findById(tagId));
			}
		}
		// 设置关键字，可以为空
		if (keywords != null) {
			List<String> kw = entity.getKeywords();
			kw.clear();
			kw.addAll(Arrays.asList(keywords));
		} else {
			entity.getKeywords().clear();
		}
		
		// 更新其他属性
		Updater<Product> updater = new Updater<Product>(bean);
		updater.exclude(Product.PROP_WEBSITE);
		updater.exclude(Product.PROP_PRODUCT_TEXT);
		entity = productDao.updateByUpdater(updater);
		// 更新属性表
		if (attr != null) {
			Map<String, String> attrOrig = entity.getAttr();
			attrOrig.clear();
			attrOrig.putAll(attr);
		}
		// 更新规则值
		entity.getExendeds().clear();
		if (names != null && names.length > 0) {
			for (int i = 0, len = names.length; i < len; i++) {
				if (!StringUtils.isBlank(names[i])) {
					entity.addToExendeds(names[i], values[i]);
				}
			}
		}
		// 更新图片集
		entity.getPictures().clear();
		if (fashionSwitchPic != null && fashionSwitchPic.length > 0) {
			for (int i = 0, len = fashionSwitchPic.length; i < len; i++) {
				if (!StringUtils.isBlank(fashionSwitchPic[i])) {
					entity.addToPictures(fashionSwitchPic[i],fashionBigPic[i],fashionAmpPic[i]);
				}
			}
		}
		String uploadPath = realPathResolver.get(entity.getWebsite().getUploadRel());
		saveImage(entity, ext, category.getType(), file, uploadPath);
		productExtMng.saveOrUpdate(ext, entity);
		if(bean.getParent()!=null&&bean.getParent().getId()!=null){
			Product parent = productDao.findById(entity.getParent().getId());
			
//			Jysp  = jyspMng.findById(bean.getParent().getId());
			List<Gys> gyss=jyspMng.findGysByProductId(entity.getId());
			for(Gys gys:gyss){
				Jysp jysp=new Jysp();
				
				ProductSite productSite = productSiteDao.getProductSiteByProductIdAndWebId(entity.getParent().getId(),gys.getWebsite().getId());
				if(productSite==null){ 
				productSite=new ProductSite();
				productSite.setAlertInventory(0);
				productSite.setCostPrice(0d);
				productSite.setCreateTime(new Date());
				productSite.setProduct(bean);
				productSite.setWebsite(gys.getWebsite());
				productSiteDao.save(productSite);
				}
				jysp.setGys(gys);
				jysp.setCreateTime(new Date());
				jysp.setGoodsMaxNum(0l);
				jysp.setGoodsMinNum(0l);
				jysp.setGoodsNum(0l);
				jysp.setGoodsPrice(0d);
				jysp.setUsername(user.getUsername());
				jysp.setProductSite(productSite);
				jyspMng.save(jysp);
			}
		}
		return entity;
	}
	
	
	public Product update(Product bean) {
		Updater<Product> updater = new Updater<Product>(bean);
		Product entity = productDao.updateByUpdater(updater);
		return entity;
	}

	public Product[] deleteByIds(Long[] ids) {
		Product[] beans = new Product[ids.length];
		for (int i = 0, len = ids.length; i < len; i++) {
//			cartItemMng.deleteByProductId(ids[i]);
			List<Collect> clist=collectMng.findByProductId(ids[i]);
			for(Collect collect:clist){
				collectMng.deleteById(collect.getId());
			}
//			List<Consult> consultList= consultMng.findByProductId(ids[i]);
//			for(Consult consult:consultList){
//				consultMng.deleteById(consult.getId());
//			}
			List<ProductStandard> psList = productStandardMng.findByProductId(ids[i]);
			for(ProductStandard ps: psList){
				productStandardMng.deleteById(ps.getId());
			}
			Product product = findById(ids[i]);
			product.getTags().clear();
			product.getFashions().clear();
			product.getKeywords().clear();
			product.getPopularityGroups().clear();
			beans[i] = productDao.deleteById(ids[i]);
		}
		for (Product p : beans) {
			p.removeAllTags();
		}
		return beans;
	}

	private boolean saveImage(Product product, ProductExt bean,
			ProductType type, MultipartFile file, String uploadPath) {
		// 如果没有上传文件，则不处理。
		if (file == null || file.isEmpty()) {
			return false;
		}
		// 先删除图片，如果有的话。
		deleteImage(product, uploadPath);
		// 获得后缀
		String ext = FilenameUtils.getExtension(file.getOriginalFilename());
		// 检查后缀是否允许上传
		if (!ImageUtils.isImage(ext)) {
			return false;
		}
		// 日期目录
		String dateDir = FileNameUtils.genPathName();
		// 创建目录
		File root = new File(uploadPath, dateDir);
		// 相对路径
		String relPath = SPT + dateDir + SPT;
		if (!root.exists()) {
			root.mkdirs();
		}
		// 取文件名
		String name = FileNameUtils.genFileName();
		// 保存为临时文件
		File tempFile = new File(root, name);
		try {
			file.transferTo(tempFile);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
		try {
			// 保存详细图
			String detailName = name + Product.DETAIL_SUFFIX + POINT + ext;
			File detailFile = new File(root, detailName);
			imageScale.resizeFix(tempFile, detailFile,
					type.getDetailImgWidth(), type.getDetailImgHeight());
			bean.setDetailImg(relPath + detailName);
			// 保存列表图
			String listName = name + Product.LIST_SUFFIX + POINT + ext;
			File listFile = new File(root, listName);
			imageScale.resizeFix(tempFile, listFile, type.getListImgWidth(),
					type.getListImgHeight());
			bean.setListImg(relPath + listName);
			// 保存缩略图
			String minName = name + Product.MIN_SUFFIX + POINT + ext;
			File minFile = new File(root, minName);
			imageScale.resizeFix(tempFile, minFile, type.getMinImgWidth(), type
					.getMinImgHeight());
			bean.setMinImg(relPath + minName);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
		// 删除临时文件
		tempFile.delete();
		return true;
	}

	public void deleteImage(Product entity, String uploadPath) {
		String detail = entity.getDetailImg();
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
		}
	}
	
	public Integer getStoreByProductPattern(Long id,Long fashId){//获得商品款式库存wangzewu
		ProductFashion bean=productFashionDao.getPfashion(id, fashId);
		return bean.getStockCount();
	}
	
	public List<Product> getHistoryProduct(HashSet<Long> set,Integer count){
		return productDao.getHistoryProduct(set,count);
	}
	
	public Integer getTotalStore(Long productId){
		Product bean=productDao.findById(productId);
		Set<ProductFashion> set=bean.getFashions();
		Integer store=0;
		if(set!=null){
			for(ProductFashion f : set){
				store=store+f.getStockCount();
			}
		}
		
		return store;
	}
	
	
	
	
	/*public ShopOrderType[] updatePriority(Long[] ids, Integer[] priority) {
		ShopOrderType[] beans = new ShopOrderType[ids.length];
		for (int i = 0, len = ids.length; i < len; i++) {
			beans[i] = findById(ids[i]);
			beans[i].setPriority(priority[i]);
		}
		return beans;
	}*/
	
	@Transactional(readOnly = true)
	public ProductSite findByPsiteId(Long id) {
		ProductSite entity = productSiteDao.findById(id);
		return entity;
	}
	
	@Override
	public ProductSite[] updatePrices(Long[] wids, Double[] salePrice, Double[] marketPrice, Double[] costPrice,  Boolean[] onSale, Boolean[] recommend,  Boolean[] special,  Boolean[] hotsale,  Boolean[] newProduct) {
		// TODO Auto-generated method stub
		ProductSite[] beans = new ProductSite[wids.length];
		for (int i = 0, len = wids.length; i < len; i++) {
			beans[i] = findByPsiteId(wids[i]);
			beans[i].setSalePrice(salePrice[i]);
			beans[i].setMarketPrice(marketPrice[i]);
			beans[i].setCostPrice(costPrice[i]);
			
			if(onSale[i] == null){
				beans[i].setOnSale(false);
			}else{
				beans[i].setOnSale(true);
			}
			
			if(recommend[i] == null){
				beans[i].setRecommend(false);
			}else{
				beans[i].setRecommend(true);
			}
			
			if(special[i] == null){
				beans[i].setSpecial(false);
			}else{
				beans[i].setSpecial(true);
			}
			
			/*beans[i].setRecommend(recommend[i]);
			beans[i].setSpecial(special[i]);*/
			
			if(hotsale[i] == null){
				beans[i].setHotsale(false);
			}else{
				beans[i].setHotsale(true);
			}
			
			if(newProduct[i] == null){
				beans[i].setNewProduct(false);
			}else{
				beans[i].setNewProduct(true);
			}
			/*beans[i].setHotsale(hotsale[i]);
			beans[i].setNewProduct(newProduct[i]);*/
		}
		return beans;
	}
	@Override
	public List<ProductSite> updatePrices(List<ProductSite> productSiteList) {
		// TODO Auto-generated method stub
		for (int i = 0;i < productSiteList.size(); i++) {
			Updater updater= new Updater<ProductSite>(productSiteList.get(i));
			productSiteDao.updateByUpdater(updater);
		}
		return productSiteList;
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
	private ProductDao productDao;
	@Autowired
	private JyspMng jyspMng;
	
	@Autowired
	private ProductSiteDao productSiteDao;

	
}