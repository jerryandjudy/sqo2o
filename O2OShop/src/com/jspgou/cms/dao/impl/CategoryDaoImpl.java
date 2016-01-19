package com.jspgou.cms.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.jspgou.common.hibernate3.Finder;
import com.jspgou.common.hibernate3.HibernateBaseDao;
import com.jspgou.cms.dao.CategoryDao;
import com.jspgou.cms.entity.Brand;
import com.jspgou.cms.entity.Category;

/**
 * CategoryDao实现类
 * @author liufang
 * This class should preserve.
 * @preserve
 */
@Repository
public class CategoryDaoImpl extends HibernateBaseDao<Category, Long> implements
		CategoryDao {

	public Category getByPath(Long webId, String path, boolean cacheable) {
		String hql = "from Category bean where  bean.path=?";
		return (Category) createQuery(hql,  path).setCacheable(cacheable)
				.uniqueResult();
	}

	@SuppressWarnings("unchecked")
	public List<Category> getListForParent(Long webId, Long ctgId) {
		Finder f = Finder.create("select node");
		f.append(" from Category node,Category exclude");
		f.append(" where ex.id=:ctgId and node.website.id=?");
		f.append(" and node.lft<exclude.lft and node.rgt>exclude.rgt");
		f.append(" order by node.priority");
		f.setParam("webId", webId);
		f.setParam("ctgId", ctgId);
		return find(f);
	}

	@SuppressWarnings("unchecked")
	public List<Category> getListForChild(Long webId, Long ctgId) {
		Finder f = Finder.create("select node");
		f.append(" from Category node, Category parent");
		f.append(" where parent.id=:ctgId and node.website.id=:webId");
		f.append(" and node.lft>=parent.lft and node.rgt<=parent.rgt");
		f.setParam("webId", webId);
		f.setParam("ctgId", ctgId);
		return find(f);
	}
	@SuppressWarnings("unchecked")
	public List<Category> getListForChild( Long ctgId) {
		Finder f = Finder.create("select node");
		f.append(" from Category node, Category parent");
		f.append(" where parent.id=:ctgId ");
		f.append(" and node.lft>=parent.lft and node.rgt<=parent.rgt");
//		f.setParam("webId", webId);
		f.setParam("ctgId", ctgId);
		return find(f);
	}

	@SuppressWarnings("unchecked")
	public List<Category> getTopList(Long webId, boolean cacheable) {
		String hql = "from Category bean where bean.website.id=? and bean.parent.id is null order by bean.priority";
		return createQuery(hql, webId).setCacheable(cacheable).list();
	}
	@SuppressWarnings("unchecked")
	public List<Category> getTopList( boolean cacheable) {
		String hql = "from Category bean where  bean.parent.id is null order by bean.priority";
		return createQuery(hql).setCacheable(cacheable).list();
	}
	
	@SuppressWarnings("unchecked")
	public List<Category> getChildList(Long webId, Long parentId){
		Finder f = Finder.create("from Category bean");
		f.append(" where bean.parent.id=:parentId");
		f.setParam("parentId", parentId);
		f.append(" order by bean.priority asc,bean.id asc");
		return find(f);
	}
	@SuppressWarnings("unchecked")
	public List<Category> getChildList(Long parentId){
		Finder f = Finder.create("from Category bean");
		f.append(" where bean.parent.id=:parentId");
		f.setParam("parentId", parentId);
		f.append(" order by bean.priority asc,bean.id asc");
		return find(f);
	}
	@SuppressWarnings("unchecked")
	public List<Category> getChildList(String parentIds){
		Finder f = Finder.create("from Category bean");
		f.append(" where bean.parent.id in ("+parentIds+")");
		f.append(" order by bean.priority asc,bean.id asc");
		return find(f);
	}
	@SuppressWarnings("unchecked")
	public List<Category> getChildList(Long parentId,Integer count,Boolean isDisplay){
		Finder f = Finder.create("from Category bean");
		f.append(" where 1=1");
		if(parentId==null){
		f.append("  and bean.parent.id is null");
		}else{
		f.append(" and bean.parent.id=:parentId");
		f.setParam("parentId", parentId);
		}
		if(isDisplay!=null){
			f.append(" and bean.isDisplay=:isDisplay");
			f.setParam("isDisplay", isDisplay);
		}
		if(count!=null){
		f.setMaxResults(count);
		}
		f.append(" order by bean.priority asc,bean.id asc");
		return find(f);
	}
	@SuppressWarnings("unchecked")
	public List<Category> getChildList(Long Level, Boolean loadChild,Boolean isJp,Long parentId,Integer count,Boolean isDisplay){
		Finder f = Finder.create("from Category bean");
		f.append(" where 1=1");
		if(parentId==null){
			f.append("  and bean.parent.id is null");
		}else{
			if (Level != null && Level>0 && Level<3) {
				f.append(" and ((bean");
				for(int i=0;i<Level;i++){
					f.append(".parent");
				}
				}
				f.append(".id=:categoryId)");
				f.setParam("categoryId", parentId);
				if (loadChild != null && loadChild) {
					if (Level != null && Level>1 && Level<3) {
							f.append(" or (bean");
							for(int i=1;i<Level;i++){
								f.append(".parent");
							}
							f.append(".id=:categoryId)");
							f.setParam("categoryId", parentId);
					}			
				}
			f.append(")");
//			f.setParam("parentId", parentId);
		}
		
		if(isDisplay!=null ){
			f.append(" and bean.isDisplay=:isDisplay");
			f.setParam("isDisplay", isDisplay);
		}
		if(isJp!=null ){
			f.append(" and bean.isJp=:isJp");
			f.setParam("isJp", isJp);
		}
		if(count!=null){
			f.setMaxResults(count);
		}
		f.append(" order by bean.priority asc,bean.id asc");
		return find(f);
	}
	@SuppressWarnings("unchecked")
	public List<Brand> getBrandList(Long categoryId,Long categoryOption,Integer count,Boolean isDisplay,Boolean isJp){
		Finder f = Finder.create("from Category bean");
		f.append(" where 1=1");
		if(categoryOption==null){
			categoryOption=0l;
		}
		if(categoryId!=null){
			if(categoryOption==0){
			f.append(" and  bean.id=:categoryId");
			f.setParam("categoryId", categoryId);
			}else if(categoryOption==1){
				f.append(" and  bean.parent.id=:categoryId");
				f.setParam("categoryId", categoryId);
			}else if(categoryOption==2){
				f.append(" and  bean.parent.parent.id=:categoryId");
				f.setParam("categoryId", categoryId);
			}else if(categoryOption==3){
				f.append(" and  bean.parent.parent.parent.id=:categoryId");
				f.setParam("categoryId", categoryId);
			}
		}
		
		if(isDisplay!=null ){
			f.append(" and bean.isDisplay=:isDisplay");
			f.setParam("isDisplay", isDisplay);
		}
		if(isJp!=null ){
			f.append(" and bean.isJp=:isJp");
			f.setParam("isJp", isJp);
		}
		if(count!=null){
			f.setMaxResults(count);
		}
		f.append(" order by bean.priority asc,bean.id asc");
		return find(f);
	}

	public int countPath(Long webId, String path) {
		String hql = "select count(*) from Category bean where bean.website.id=:webId and bean.path=:path";
		return ((Number) getSession().createQuery(hql).setParameter("webId",
				webId).setParameter("path", path).iterate().next()).intValue();

	}
	
	@SuppressWarnings("unchecked")
	public List<Category> getListByptype(Long webId,Long pTypeId,Integer count){
		String hql="from Category bean where bean.website.id=? and bean.type.id=?";
		if(count!=null&&count!=0){
			
			return this.getSession().createQuery(hql).setParameter(0, webId).setParameter(1, pTypeId).setFirstResult(0)
			.setMaxResults(count).list();
		}
		return this.getSession().createQuery(hql).setParameter(0, webId).setParameter(1, pTypeId).list();
	}

	public Category findById(Long id) {
		Category entity = get(id);
		return entity;
	}

	public Category save(Category bean) {
		getSession().save(bean);
		return bean;
	}

	public Category deleteById(Long id) {
		Category entity = super.get(id);
		if (entity != null) {
			getSession().delete(entity);
		}
		return entity;
	}

	@Override
	protected Class<Category> getEntityClass() {
		return Category.class;
	}
}