package com.jspgou.cms.manager;

import java.util.List;
import java.util.Map;

import com.jspgou.cms.entity.Brand;
import com.jspgou.cms.entity.Category;

/**
* This class should preserve.
* @preserve
*/
public interface CategoryMng {
	/**
	 * 获得商品类别。通过访问路径。
	 * 
	 * @param webId
	 *            站点ID
	 * @param path
	 *            访问路径
	 * @return 没有找到则返回null
	 */
	public Category getByPath(Long webId, String path);

	/**
	 * 获得商品类别。通过访问路径。供标签使用，开启查询缓存。
	 * 
	 * @param webId
	 *            站点ID
	 * @param path
	 *            访问路径
	 * @return 没有找到则返回null
	 */
	public Category getByPathForTag(Long webId, String path);

	/**
	 * 获得列表。用于选择父节点。按树型结构排列。
	 * 
	 * 能过滤掉不能选择为父节点的数据
	 * 
	 * @param webId
	 *            站点ID
	 * @param ctgId
	 *            当前类别ID。为null则不过滤。
	 * @return
	 */
	public List<Category> getListForParent(Long webId, Long ctgId);
	/**
	 * 获得列表。用于选择父节点。按树型结构排列。
	 * 
	 * 能过滤掉不能选择为父节点的数据
	 * 
	 * @param webId
	 *            站点ID
	 * @param ctgId
	 *            当前类别ID。为null则不过滤。
	 * @return
	 */
	public List<Category> getListForParent( Long ctgId);

	/**
	 * 获得列表。用于产品选择类别。按树形结构排列。
	 * 
	 * 能过滤不同类型的类别
	 * 
	 * @param webId
	 *            站点ID
	 * @param ctgId
	 *            产品类别ID
	 * @return
	 */
	public List<Category> getListForProduct(Long webId, Long ctgId);

	/**
	 * 获得根列表
	 * 
	 * @param webId
	 * @return
	 */
	public List<Category> getTopList(Long webId);
	/**
	 * 获得根列表
	 * 
	 * @param webId
	 * @return
	 */
	public List<Category> getTopList();
	
	/**
	 * 获得parentId子列表
	 * 
	 * @param webId
	 * 
	 * @param parentId
	 * @return
	 */
	public List<Category> getChildList(Long wegId,Long parentId);
	/**
	 * 获得parentId子列表
	 * 
	 * @param webId
	 * 
	 * @param parentId
	 * @return
	 */
	public List<Category> getChildList(String parentIds);
	/**
	 * 获得parentId子列表
	 * 
	 * @param parentId
	 * @return
	 */
	public List<Category> getChildList(Long parentId);
	/**
	 * 获得parentId子列表
	 * 
	 * @param parentId
	 * @return
	 */
	public List<Category> getChildList(Long Level, Boolean loadChild,Boolean isJp,Long parentId,Integer count,Boolean isDisplay);
	/**
	 * 获得品牌列表
	 * @param categoryId
	 * @param categoryOption
	 * @param count
	 * @param isDisplay
	 * @param isJp
	 * @return
	 */
	public List<Brand> getBrandList(Long categoryId,Long categoryOption, Integer count,Boolean isDisplay,Boolean isJp);
	/**
	 * 获得parentId子列表
	 * 
	 * @param parentId
	 * @return
	 */
	public List<Category> getChildList(Long parentId,Integer count,Boolean isDisplay);

	/**
	 * 获得根列表，开启查询缓存。
	 * 
	 * @param webId
	 * @return
	 */
	public List<Category> getTopListForTag(Long webId);

	/**
	 * 获得列表。按树型结构排列。
	 * 
	 * @param webId
	 *            站点ID
	 * @return
	 */
	public List<Category> getList(Long webId);
	/**
	 * 获得列表。按树型结构排列。
	 * 
	 * @param webId
	 *            站点ID
	 * @return
	 */
	public List<Category> getList();

	/**
	 * 检查path是否可用
	 * 
	 * @param webId
	 * @param path
	 * @return
	 */
	public boolean checkPath(Long webId, String path);

	/**
	 * 获得产品类别
	 * 
	 * @param id
	 *            产品类别ID
	 * @return
	 */
	public Category findById(Long id);

	/**
	 * 保存产品类别
	 * 
	 * @param bean
	 *            待保存类别对象
	 * @param parentId
	 *            父节点ID
	 * @param typeId
	 *            商品类别ID
	 * @return 被保存产品类别对象
	 */
	public Category save(Category bean, Long parentId, Long typeId, Long[] brandIds,Long[] standardTypeIds);

	/**
	 * 更新产品类别
	 * 
	 * @param bean
	 *            待更新类别对象
	 * @param parentId
	 *            父节点ID
	 * @param typeId
	 *            商品类别ID
	 * @return 被更新产品类别对象
	 */
	public Category update(Category bean, Long parentId, Long typeId, Long[] brandIds,Map<String, String> attr,Long[] standardTypeIds);

	/**
	 * 删除产品类别
	 * 
	 * @param id
	 *            待删除产品类别ID
	 * @return 被删除产品类别对象
	 */
	public Category deleteById(Long id);

	/**
	 * 删除产品类别
	 * 
	 * @param ids
	 *            待删除产品类别ID数组
	 * @return 被删除的产品类别
	 */
	public Category[] deleteByIds(Long[] ids);

	/**
	 * 更新排列顺序
	 * 
	 * @param ids
	 *            待排列ID数组
	 * @param priority
	 *            排列顺序
	 * @return 排序后产品对象数组
	 */
	public Category[] updatePriority(Long[] ids, Integer[] priority);
	
	public List<Category> getListBypType(Long webId,Long typeId,Integer count);
	
	public List<Brand> getBrandByCate(Long categoryId);
	public List<Brand> getBrandByCateSearchName(String searchName);
	
}