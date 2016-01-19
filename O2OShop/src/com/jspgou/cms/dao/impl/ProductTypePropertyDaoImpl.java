package com.jspgou.cms.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.jspgou.common.hibernate3.Finder;
import com.jspgou.common.hibernate3.HibernateBaseDao;
import com.jspgou.common.page.Pagination;
import com.jspgou.cms.dao.ProductTypePropertyDao;
import com.jspgou.cms.entity.ProductTypeProperty;

/**
* This class should preserve.
* @preserve
*/
@Repository
public class ProductTypePropertyDaoImpl extends HibernateBaseDao<ProductTypeProperty, Long>
		implements ProductTypePropertyDao {

	public ProductTypeProperty deleteById(Long id) {
		ProductTypeProperty entity = super.get(id);
		if (entity != null) {
			getSession().delete(entity);
		}
		return entity;
	}

	public ProductTypeProperty findById(Long id) {
		ProductTypeProperty entity = get(id);
		return entity;
	}

	public Pagination getList(int pageNo,int pageSize,Long typeid,Boolean isCategory,
			String searchType,String searchContent) {
		Finder f = Finder.create("from ProductTypeProperty bean where 1=1 ");
		if(typeid!=null){
			f.append(" and bean.propertyType.id = :typeid").setParam("typeid", typeid);
		}
		if(searchType!=null){
			if("1".equals(searchType)){
				f.append(" and bean.propertyType.name like :searchContent ");
			}
			//2属性名称查找
			else if("2".equals(searchType)){
				f.append(" and bean.propertyName like :searchContent ");
			}
			f.setParam("searchContent", "%"+searchContent+"%");
		}
		f.append(" and bean.category=:isCategory").setParam("isCategory", isCategory);
		f.append(" order by bean.propertyType.id,bean.sort");
		return find(f, pageNo, pageSize);
	}
	
	@SuppressWarnings("unchecked")
	public List<ProductTypeProperty> getList(Long typeId,Boolean isCategory){
		Finder f = Finder.create("from ProductTypeProperty bean where 1=1 ");
		if(typeId!=null){
			f.append(" and bean.propertyType.id = :typeId").setParam("typeId", typeId);
		}
			f.append(" and bean.category=:isCategory").setParam("isCategory", isCategory);
			f.append(" order by bean.sort asc");
			return find(f);
	}

	public ProductTypeProperty save(ProductTypeProperty bean) {
		getSession().save(bean);
		return bean;
	}

	@Override
	protected Class<ProductTypeProperty> getEntityClass() {
		return ProductTypeProperty.class;
	}

	@SuppressWarnings("unchecked")
	public List<ProductTypeProperty> findBySearch(String searchType,
			String searchContent) {
		String hql = "from ProductTypeProperty bean where 1 = 1 ";
		//1商品类型查找
		if("1".equals(searchType)){
			hql += " and bean.propertyType.name like :searchContent ";
		}
		//2属性名称查找
		else if("2".equals(searchType)){
			hql += " and bean.propertyName like :searchContent ";
		}
		searchContent ="%"+searchContent+"%";
		return getSession().createQuery(hql).setString("searchContent", searchContent).setCacheable(false).list();
	}

	@SuppressWarnings("unchecked")
	public List<ProductTypeProperty> findListByTypeId(Long typeId) {
		String hql = "from ProductTypeProperty bean where bean.propertyType.id = :typeId ";
		return getSession().createQuery(hql).setLong("typeId", typeId).setCacheable(false).list();
	}
	
	@SuppressWarnings("unchecked")
	public List<ProductTypeProperty> getList(Long typeId,boolean isCategory){
		Finder f=Finder.create("from ProductTypeProperty bean where 1=1");
		f.append(" and bean.propertyType.id=:typeId").setParam("typeId", typeId);
		f.append(" and bean.category=:isCategory").setParam("isCategory", isCategory);
		f.append(" order by bean.sort asc");
		return find(f);
	}
}