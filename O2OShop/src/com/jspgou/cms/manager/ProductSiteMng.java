package com.jspgou.cms.manager;

import java.util.HashSet;
import java.util.List;
import java.util.Map;

import org.springframework.web.multipart.MultipartFile;

import com.jspgou.common.page.Pagination;
import com.jspgou.cms.entity.Gys;
import com.jspgou.cms.entity.ProductSite;
import com.jspgou.cms.entity.ProductExt;
import com.jspgou.cms.entity.ProductTag;

/**
* This class should preserve.
* @preserve
*/
public interface ProductSiteMng {
	public Pagination getPage(int orderBy,int pageNo, int pageSize);
	
	public List<ProductSite> getList(Long typeId,Long brandId,String productName);
	
    //商品列表标签
	public List<ProductSite> getListForTag(Long webId, Long ctgId, Long tagId,
			Boolean isRecommend, Boolean isSpecial,Boolean isHostSale,Boolean isNewProductSite, int firstResult,
			int maxResults);
    //后台
 	public Pagination getPage(Long webId, Long ctgId,String productName,String brandName,
			Boolean isOnSale,Boolean isRecommend,
			Boolean isSpecial,Boolean isHotsale,Boolean isNewProductSite,
			Long typeId,Double startSalePrice,Double endSalePrice,
			Integer startStock,Integer endStock,int pageNo, int pageSize); 
 	//后台
 	public Pagination getPageNotJy(Gys gys,Long webId, Long ctgId,String productName,String brandName,
 			Boolean isOnSale,Boolean isRecommend,
 			Boolean isSpecial,Boolean isHotsale,Boolean isNewProductSite,
 			Long typeId,Double startSalePrice,Double endSalePrice,
 			Integer startStock,Integer endStock,int pageNo, int pageSize); 
	
	//库存预警
	public Pagination getPageByStockWarning(Long webId, Integer count,Boolean status,int pageNo, int pageSize); 

	//商品分页标签
	public Pagination getPageForTag(Long webId, Long ctgId, Long tagId,Boolean isRecommend, Boolean isSpecial, int pageNo, int pageSize);

	public ProductSite findById(Long id);
	public List<ProductSite> getChildListByIdAndSiteId(Long id,Long siteId);
	
	public ProductSite updateByUpdater(ProductSite bean);

	/**
	 * 删除商品级联的tag
	 * 
	 * @param tags
	 * @return 级联的商品数量
	 */
	public int deleteTagAssociation(ProductTag[] tags);

	public ProductSite save(ProductSite bean, ProductExt ext, Long webId, Long categoryId,
			Long brandId, Long[] tagIds, String[] keywords,String[] names,
			String[] values,String fashionSwitchPic[],String fashionBigPic[],String fashionAmpPic[],
			MultipartFile file);
	public ProductSite save(ProductSite bean);

	public ProductSite update(ProductSite bean, ProductExt ext, Long ctgId,
			Long brandId, Long[] tagIds, String[] keywords,String[] names,
			String[] values,Map<String, String> attr,
			String fashionSwitchPic[],String fashionBigPic[],String fashionAmpPic[],
			MultipartFile file);
	
	public ProductSite update(ProductSite bean);
	
	public void resetSaleTop();

	public ProductSite[] deleteByIds(Long[] ids);
	public ProductSite deleteById(Long id);
	
 	public Pagination getPageForTagChannel(Long brandId, Long webId, Long ctgId, Long tagId,//栏目页商品分页manager(wangzewu)
			Boolean isRecommend, String[] names,String[] values,Boolean isSpecial, int orderBy,int pageNo, int pageSize); 
	
 	public Pagination getPageForTagChannel(String productName,String area,Long brandId, Long webId, Long ctgId, Long tagId,//栏目页商品分页manager(wangzewu)
 			Boolean isRecommend, String[] names,String[] values,Boolean isSpecial, int orderBy,int pageNo, int pageSize); 
 	
	public Integer getStoreByProductSitePattern(Long id,Long fashId);
	
	public Integer getTotalStore(Long productId);
	
	public List<ProductSite> getIsRecommend(Boolean isRecommend,int count);
	public List<ProductSite> getIsRecommend(Boolean isRecommend,int count,Long siteId);
	public List<ProductSite> getListBySiteId(Long siteId);
	public Integer[] getProductSiteByTag(Long webId);
	
	public List<ProductSite> getHistoryProductSite(HashSet<Long> set,Integer count);

	public void resetProfitTop();

	public void updateViewCount(ProductSite product);
}