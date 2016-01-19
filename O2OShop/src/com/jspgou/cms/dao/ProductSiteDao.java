package com.jspgou.cms.dao;

import java.io.IOException;
import java.util.Date;
import java.util.HashSet;
import java.util.List;

import org.apache.lucene.index.CorruptIndexException;
import org.apache.lucene.index.IndexWriter;

import com.jspgou.common.hibernate3.Updater;
import com.jspgou.common.page.Pagination;
import com.jspgou.cms.entity.Gys;
import com.jspgou.cms.entity.Product;
import com.jspgou.cms.entity.ProductSite;
import com.jspgou.cms.entity.ProductTag;

/**
* This class should preserve.
* @preserve
*/
public interface ProductSiteDao {
	
	public List<ProductSite> getList(Long typeId,Long brandId,String productName,boolean cacheable);
	
	public Pagination getPage(int orderBy,int pageNo, int pageSize, boolean cacheable);

	public Pagination getPageByCategory(Long ctgId, String productName,String brandName,
			Boolean isOnSale,Boolean isRecommend,Boolean isSpecial,Boolean isHotsale,
			Boolean isNewProduct,Long typeId,Double startSalePrice,Double endSalePrice,
			Integer startStock,Integer endStock, int pageNo, int pageSize, boolean cacheable);

	public Pagination getPageByWebsite(Long webId,String productName,String brandName,
			Boolean isOnSale,Boolean isRecommend,Boolean isSpecial,Boolean isHotsale,
			Boolean isNewProduct,Long typeId,Double startSalePrice,Double endSalePrice,
			Integer startStock,Integer endStock, int pageNo, int pageSize, boolean cacheable);
	public Pagination getPageByWebsite(String area,Long webId,String productName,String brandName,
			Boolean isOnSale,Boolean isRecommend,Boolean isSpecial,Boolean isHotsale,
			Boolean isNewProduct,Long typeId,Double startSalePrice,Double endSalePrice,
			Integer startStock,Integer endStock, int pageNo, int pageSize, boolean cacheable);
	
	public Pagination getPageByWebsite(String area,Long webId,String productName,Long brandId,
			Boolean isOnSale,Boolean isRecommend,Boolean isSpecial,Boolean isHotsale,
			Boolean isNewProduct,Long typeId,Double startSalePrice,Double endSalePrice,
			Integer startStock,Integer endStock, int pageNo, int pageSize, boolean cacheable);
	
	public Pagination getPageByWebsite(int orderBy,String area,Long webId,String productName,Long brandId,
			Boolean isOnSale,Boolean isRecommend,Boolean isSpecial,Boolean isHotsale,
			Boolean isNewProduct,Long typeId,Double startSalePrice,Double endSalePrice,
			Integer startStock,Integer endStock, int pageNo, int pageSize, boolean cacheable);
	
	public Pagination getPage(Long webId,Long ctgId,String productName,String brandName,
			Boolean isOnSale,Boolean isRecommend,Boolean isSpecial,Boolean isHotsale,
			Boolean isNewProduct,Long typeId,Double startSalePrice,Double endSalePrice,
			Integer startStock,Integer endStock, int pageNo, int pageSize, boolean cacheable);
	
	public Pagination getPageByTag(Long tagId, Long ctgId, Boolean isRecommend,
			Boolean isSpecial, int pageNo, int pageSize, boolean cacheable);
	public Pagination getPageByTag(String productName,String area,Long tagId, Long ctgId, Boolean isRecommend,
			Boolean isSpecial, int pageNo, int pageSize, boolean cacheable);
	
	public Pagination getPageByStockWarning(Long webId,Integer count,Boolean status,int pageNo, int pageSize);

	public List<ProductSite> getListByCategory(Long ctgId, Boolean isRecommend,
			Boolean isSpecial, int firstResult, int maxResults,
			boolean cacheable);
	public List<ProductSite> getListByCategory(Long webId,Long ctgId, Boolean isRecommend,
			Boolean isSpecial, int firstResult, int maxResults,
			boolean cacheable);

	public List<ProductSite> getListByWebsite(Long webId, Boolean isRecommend,
			Boolean isSpecial, int firstResult, int maxResults,
			boolean cacheable);

	public List<ProductSite> getListByTag(Long tagId, Long ctgId,
			Boolean isRecommend, Boolean isSpecial, int firstResult,
			int maxResults, boolean cacheable);
	public List<ProductSite> getListByTag(Long webId,Long tagId, Long ctgId,
			Boolean isRecommend, Boolean isSpecial, int firstResult,
			int maxResults, boolean cacheable);

	public int luceneWriteIndex(IndexWriter writer, Long webId, Date start,
			Date end) throws CorruptIndexException, IOException;
	public List<ProductSite> getListByWebsite1(Long webId, Boolean isRecommend,
			Boolean isSpecial,Boolean isHostSale,Boolean isNewProduct, int firstResult, int maxResults,
			boolean cacheable) ;
	public int deleteTagAssociation(ProductTag[] tags);

	public ProductSite findById(Long id);

	public ProductSite save(ProductSite bean);

	public ProductSite updateByUpdater(Updater<ProductSite> updater);

	public ProductSite deleteById(Long id);
	
	public Pagination getPageByCategoryChannel(Long brandId, Long ctgId,
			Boolean isRecommend, String[] names,String[] values,//栏目页商品分页manager(wangzewu)
			Boolean isSpecial,int orderBy, int pageNo, int pageSize, boolean cacheable);
	public Pagination getPageByCategoryChannel(Long webId,Long brandId, Long ctgId,
			Boolean isRecommend, String[] names,String[] values,//栏目页商品分页manager(wangzewu)
			Boolean isSpecial,int orderBy, int pageNo, int pageSize, boolean cacheable);
	public Pagination getPageByCategoryChannel(String productName,String area,Long webId,Long brandId, Long ctgId,
			Boolean isRecommend, String[] names,String[] values,//栏目页商品分页manager(wangzewu)
			Boolean isSpecial,int orderBy, int pageNo, int pageSize, boolean cacheable);
	public List<ProductSite> getIsRecommend(Boolean isRecommend,int count);
	public List<ProductSite> getIsRecommend(Boolean isRecommend,int count,Long webId);
	public List<ProductSite> getListBySiteId(Long siteId);
	public Integer[] getProductSiteByTag(Long webId);
	public ProductSite getProductSiteByProductIdAndWebId(Long productId,Long webId);
	
	public List<ProductSite> getHistoryProductSite(HashSet<Long> set,Integer count);
	public List<ProductSite> getChildListByIdAndSiteId(Long id,Long siteId);
	public Pagination getPageNotJy(Gys gys,Long webId, Long ctgId, String productName,
			String brandName, Boolean isOnSale, Boolean isRecommend,
			Boolean isSpecial, Boolean isHotsale, Boolean isNewProduct,
			Long typeId, Double startSalePrice, Double endSalePrice,
			Integer startStock, Integer endStock, int pageNo, int pageSize,
			boolean cacheable);

}