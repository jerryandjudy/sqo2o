package com.jspgou.cms.manager.impl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import com.jspgou.cms.dao.BrandDao;
import com.jspgou.cms.dao.CategoryDao;
import com.jspgou.cms.entity.Brand;
import com.jspgou.cms.entity.Category;
import com.jspgou.cms.entity.StandardType;
import com.jspgou.cms.manager.BrandMng;
import com.jspgou.cms.manager.CategoryMng;
import com.jspgou.cms.manager.ProductTypeMng;
import com.jspgou.cms.manager.StandardTypeMng;
import com.jspgou.common.hibernate3.Updater;

/**
* This class should preserve.
* @preserve
*/
@Service
@Transactional
public class CategoryMngImpl implements CategoryMng {
	public Category getByPath(Long webId, String path) {
		return categoryDao.getByPath(webId, path, false);
	}

	public Category getByPathForTag(Long webId, String path) {
		return categoryDao.getByPath(webId, path, true);
	}

	/**
	 * @see CategoryMng#getListForParent(Long, Long)
	 */
	@Transactional(readOnly = true)
	public List<Category> getListForParent(Long webId, Long ctgId) {
		List<Category> allList = getList(webId);
		if (ctgId != null) {
			List<Category> list = categoryDao.getListForChild(webId, ctgId);
			allList.removeAll(list);
		}
		return allList;
	}
	/**
	 * @see CategoryMng#getListForParent(Long, Long)
	 */
	@Transactional(readOnly = true)
	public List<Category> getListForParent( Long ctgId) {
		List<Category> allList = getList();
		if (ctgId != null) {
			List<Category> list = categoryDao.getListForChild( ctgId);
			allList.removeAll(list);
		}
		return allList;
	}

	/**
	 * @see CategoryMng#getListForProduct(Long, Long)
	 */
	@Transactional(readOnly = true)
	public List<Category> getListForProduct(Long webId, Long ctgId) {
		List<Category> list = new ArrayList<Category>();
		Category category = findById(ctgId);
		addAllChildToList(list, Arrays.asList(category), category.getType()
				.getId());
		return list;
	}

	/**
	 * @see CategoryMng#getTopList(Long)
	 */
	@Transactional(readOnly = true)
	public List<Category> getTopList(Long webId) {
		return categoryDao.getTopList(webId, false);
	}
	/**
	 * @see CategoryMng#getTopList(Long)
	 */
	@Transactional(readOnly = true)
	public List<Category> getTopList() {
		return categoryDao.getTopList( false);
	}
	
	public List<Category> getChildList(Long wegId,Long parentId){
		return categoryDao.getChildList(wegId, parentId);
	}
	public List<Category> getChildList(Long parentId){
		return categoryDao.getChildList(parentId);
	}
	public List<Category> getChildList(String parentIds){
		return categoryDao.getChildList(parentIds);
	}
	public List<Category> getChildList(Long parentId,Integer count,Boolean isDisplay){
		return categoryDao.getChildList(parentId, count, isDisplay);
	}
	public List<Category> getChildList(Long Level, Boolean loadChild,Boolean isJp,Long parentId,Integer count,Boolean isDisplay){
		return categoryDao.getChildList( Level,  loadChild,isJp,parentId, count, isDisplay);
	}
	public List<Brand> getBrandList(Long categoryId,Long categoryOption,Integer count,Boolean isDisplay,Boolean isJp){
		return categoryDao.getBrandList(categoryId, categoryOption, count, isDisplay,isJp);
	}

	/**
	 * @see CategoryMng#getTopListForTag(Long)
	 */
	public List<Category> getTopListForTag(Long webId) {
		return categoryDao.getTopList(webId, true);
	}

	/**
	 * @see CategoryMng#getList(Long)
	 */
	@Transactional(readOnly = true)
	public List<Category> getList(Long webId) {
		List<Category> list = categoryDao.getTopList(webId, false);
		List<Category> allList = new ArrayList<Category>();
		addAllChildToList(allList, list, null);
		return allList;
	}
	/**
	 * @see CategoryMng#getList(Long)
	 */
	@Transactional(readOnly = true)
	public List<Category> getList() {
		List<Category> list = categoryDao.getTopList( false);
		List<Category> allList = new ArrayList<Category>();
		addAllChildToList(allList, list, null);
		return allList;
	}

	/**
	 * @see CategoryMng#checkPath(Long, String)
	 */
	public boolean checkPath(Long webId, String path) {
		return categoryDao.countPath(webId, path) <= 0;
	}

	/**
	 * @see CategoryMng#findById(Long)
	 */
	@Transactional(readOnly = true)
	public Category findById(Long id) {
		Category entity = categoryDao.findById(id);
		return entity;
	}

	/**
	 * @see CategoryMng#save(Category, Long, Long)
	 */
	public Category save(Category bean, Long parentId, Long typeId, Long[] brandIds,Long[] standardTypeIds) {
		Category parent = null;
		if (parentId != null) {
			parent = findById(parentId);
			bean.setParent(parent);
		}
		if (typeId != null) {
			bean.setType(productTypeMng.findById(typeId));
		}
		Category entity=categoryDao.save(bean);
		if(brandIds!=null && brandIds.length>0){//-------wang ze wu s
			for(Long brandId : brandIds){
				entity.addToBrands(brandMng.findById(brandId));
			}
		}else{
			entity.setBrands(new HashSet<Brand>());
		}
		if (parent != null) {
			parent.addToChild(bean);
		}
		if(standardTypeIds!=null&&standardTypeIds.length>0){
			for(Long sid:standardTypeIds){
				entity.addToStandardTypes(standardTypeMng.findById(sid));
			}
		}
		return bean;
	}
	
	public List<Brand> getBrandByCate(Long categoryId){//获得某类型的品牌
		return brandDao.getListByCate(categoryId);
	}
	
	
	
	public List<Brand> getBrandByCateSearchName(String searchName){//根据搜索名称获得品牌
		return brandDao.getListByCateSearchName(searchName);
	}

	/**
	 * @see CategoryMng#update(Category, Long, Long)wang ze wu 改动
	 */
	public Category update(Category bean, Long parentId, Long typeId, Long[] brandIds,Map<String, String> attr,Long[] standardTypeIds) {
		Assert.notNull(bean);
		Category entity = findById(bean.getId());
		Category origParent = entity.getParent();
		Category parent = null;
		if (parentId != null) {
			parent = findById(parentId);
			bean.setParent(parent);
		} else {
			bean.setParent(null);
		}
		Updater<Category> updater = new Updater<Category>(bean);
		updater.include(Category.PROP_PARENT);
		entity = categoryDao.updateByUpdater(updater);
		if (origParent != null) {
			origParent.removeFromChild(entity);
		}
		if (parent != null) {
			parent.addToChild(entity);
		}
		//清空关联品牌
		Set<Brand> brands=entity.getBrands();
		for(Brand brand : brands){
			brand.removeFromCategorys(entity);
		}
		brands.clear();
		if(brandIds!=null && brandIds.length>0){
			for(Long brandId : brandIds){
				entity.addToBrands(brandMng.findById(brandId));
			}
		}else{
			entity.setBrands(new HashSet<Brand>());
		}
		Set<StandardType> standardTypes = entity.getStandardType();
		for(StandardType t: standardTypes){
			t.removeFromCategorys(entity);
		} 
		standardTypes.clear();
		if(standardTypeIds!=null&&standardTypeIds.length>0){
			for(Long sid:standardTypeIds){
				entity.addToStandardTypes(standardTypeMng.findById(sid));
			}
		}
		// 更新属性表
		if (attr != null) {
			Map<String, String> attrOrig = entity.getAttr();
			attrOrig.clear();
			attrOrig.putAll(attr);
		}
		
		return entity;
	}

	/**
	 * @see CategoryMng#deleteById(Long)
	 */
	public Category deleteById(Long id) {
		Category parent = findById(id).getParent();
		Category bean = categoryDao.deleteById(id);
		if (parent != null) {
			parent.removeFromChild(bean);
		}
		return bean;
	}

	/**
	 * @see CategoryMng#deleteByIds(Long[])
	 */
	public Category[] deleteByIds(Long[] ids) {
		Category[] beans = new Category[ids.length];
		for (int i = 0, len = ids.length; i < len; i++) {
			beans[i] = categoryDao.deleteById(ids[i]);
		}
		Category parent;
		for (Category bean : beans) {
			parent = bean.getParent();
			if (parent != null) {
				parent.removeFromChild(bean);
			}
		}
		return beans;
	}

	public Category[] updatePriority(Long[] ids, Integer[] priority) {
		Category[] beans = new Category[ids.length];
		for (int i = 0, len = ids.length; i < len; i++) {
			beans[i] = findById(ids[i]);
			beans[i].setPriority(priority[i]);
		}
		return beans;
	}

	/**
	 * 将coll及其子类别加入至allList
	 * 
	 * @param allList
	 * @param coll
	 * @param typeId
	 *            类型ID，如果不为null，则只添加同一type的child。
	 */
	private void addAllChildToList(List<Category> allList,
			Collection<Category> coll, Long typeId) {
		Collection<Category> child;
		for (Category ctg : coll) {
			// 如果类型ID不为null，则只添加同一type的child。
			if (typeId != null) {
				if (typeId.equals(ctg.getType().getId())) {
					allList.add(ctg);
				}
			} else {
				allList.add(ctg);
			}
			child = ctg.getChild();
			if (child != null && child.size() > 0) {
				addAllChildToList(allList, child, typeId);
			}
		}
	}
	
	public List<Category> getListBypType(Long webId,Long typeId,Integer count){
		return categoryDao.getListByptype(webId, typeId,count);
	}
	
	@Autowired
	private BrandDao brandDao;
	@Autowired
	private BrandMng brandMng;
	@Autowired
	private StandardTypeMng standardTypeMng;
	@Autowired
	private ProductTypeMng productTypeMng;
	@Autowired
	private CategoryDao categoryDao;


}