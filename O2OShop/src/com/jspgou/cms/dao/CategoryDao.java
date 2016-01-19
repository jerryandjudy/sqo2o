package com.jspgou.cms.dao;

import java.util.List;

import com.jspgou.common.hibernate3.Updater;
import com.jspgou.cms.entity.Brand;
import com.jspgou.cms.entity.Category;

/**
 * 商品类别DAO接口
 * 
 * @author liufang
 * This class should preserve.
 * @preserve
 */
public interface CategoryDao {
	/**
	 * 获得商品类别。通过访问路径。
	 * 
	 * @param webId
	 *            站点ID
	 * @param path
	 *            访问路径
	 * @param cacheable
	 *            是否
	 * @return 没有找到则返回null
	 */
	public Category getByPath(Long webId, String path, boolean cacheable);

	/**
	 * 获得商品类别列表，过滤自身及自身的子节点。
	 * 
	 * @param webId
	 *            站点ID
	 * @param ctgId
	 *            商品类别ID
	 * @return
	 */
	public List<Category> getListForParent(Long webId, Long ctgId);

	/**
	 * 获得商品类别列表，包含自身及自身的子节点
	 * 
	 * @param webId
	 *            站点ID
	 * @param ctgId
	 *            商品类别ID
	 * @return
	 */
	public List<Category> getListForChild(Long webId, Long ctgId);
	/**
	 * 获得商品类别列表，包含自身及自身的子节点
	 * 
	 * @param ctgId
	 *            商品类别ID
	 * @return
	 */
	public List<Category> getListForChild( Long ctgId);

	/**
	 * 获得商品类别列表
	 * 
	 * @param webId
	 *            站点ID
	 * @param cacheable
	 *            是否开启查询缓存
	 * @return
	 */
	public List<Category> getTopList(Long webId, boolean cacheable);
	
	/**
	 * 获得商品类别列表
	 * 
	 * @param cacheable
	 *            是否开启查询缓存
	 * @return
	 */
	public List<Category> getTopList(boolean cacheable);
	
	/**
	 * 获得商品类别列表
	 * 
	 * @param webId
	 *            站点ID
	 * @param parentId
	 *            类别ID
	 * @param cacheable
	 *            是否开启查询缓存
	 * @return
	 */
	public List<Category> getChildList(Long webId, Long parentId);
	/**
	 * 获得商品类别列表
	 * 
	 * @param parentId
	 *            类别ID
	 * @return
	 */
	public List<Category> getChildList( Long parentId);
	/**
	 * 获得商品类别列表
	 * 
	 * @param parentIds
	 *            类别ID
	 * @return
	 */
	public List<Category> getChildList( String parentIds);
	/**
	 * 获得商品类别列表
	 * 
	 * @param parentId
	 *            类别ID
	 * @param cacheable
	 *            是否开启查询缓存
	 * @return
	 */
	public List<Category> getChildList( Long parentId,Integer count,Boolean isDisplay);
	/**
	 * 获得商品类别列表
	 * 
	 * @param parentId
	 *            类别ID
	 * @param cacheable
	 *            是否开启查询缓存
	 * @return
	 */
	public List<Category> getChildList( Long Level, Boolean loadChild,Boolean isJp,Long parentId,Integer count,Boolean isDisplay);
	
	/**
	 * 获得品牌列表
	 * @param categoryId
	 * @param categoryOption
	 * @param count
	 * @param isDisplay
	 * @param isJp
	 * @return
	 */
	public List<Brand> getBrandList(Long categoryId,Long categoryOption,Integer count,Boolean isDisplay,Boolean isJp);

	/**
	 * 统计路径个数
	 * 
	 * @param webId
	 *            站点ID
	 * @param path
	 *            路径
	 * @return
	 */
	public int countPath(Long webId, String path);

	public Category findById(Long id);

	public Category save(Category bean);

	public Category updateByUpdater(Updater<Category> updater);

	public Category deleteById(Long id);
	public List<Category> getListByptype(Long webId,Long pTypeId,Integer count);
}