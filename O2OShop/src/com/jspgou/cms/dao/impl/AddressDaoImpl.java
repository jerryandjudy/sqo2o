package com.jspgou.cms.dao.impl;

import java.util.List;

import org.hibernate.Criteria;
import org.springframework.stereotype.Repository;

import com.jspgou.common.hibernate3.Finder;
import com.jspgou.common.hibernate3.HibernateBaseDao;
import com.jspgou.common.page.Pagination;
import com.jspgou.cms.dao.AddressDao;
import com.jspgou.cms.entity.Address;

/**
* This class should preserve.
* @preserve
*/
@Repository
public class AddressDaoImpl extends HibernateBaseDao<Address, Long> implements AddressDao {
	@SuppressWarnings("unchecked")
	public List<Address> getListById(Long parentId){
		Finder f=Finder.create("from Address bean where 1=1 ");
		if(parentId==null){
			f.append(" and bean.parent.id is null");
		}else{
			f.append(" and bean.parent.id=:parentId");
			f.setParam("parentId", parentId);
		}
		return find(f);
	}
	@SuppressWarnings("unchecked")
	public List<Address> getListBySiteId(Long siteId){
		Finder f=Finder.create("from Address bean where 1=1 ");
			f.append(" and bean.parent.id is null");
			f.append(" and bean.website.id=:siteId");
			f.setParam("siteId", siteId);
		return find(f);
	}
	
	public Pagination getPage(int pageNo, int pageSize) {
		Criteria crit = createCriteria();
		Pagination page = findByCriteria(crit, pageNo, pageSize);
		return page;
	}
	
	public Pagination getPageByParentId(Long parentId,int pageNo, int pageSize){
		Finder f=Finder.create("from Address bean where 1=1 ");
		if(parentId!=null){
			f.append(" and bean.parent.id=:id");
			f.setParam("id", parentId);
		}else{
			f.append(" and bean.parent.id is null");
		}
		f.append(" order by bean.priority");
		return find(f, pageNo, pageSize);
	}
	public Pagination getPageByParentIdAndSiteId(Long siteId,Long parentId,int pageNo, int pageSize){
		Finder f=Finder.create("from Address bean where 1=1 ");
		if(parentId!=null){
			f.append(" and bean.parent.id=:id");
			f.setParam("id", parentId);
		}else{
			f.append(" and bean.parent.id is null");
		}
		if(siteId!=null){
			f.append(" and bean.website.id=:siteId");
			f.setParam("siteId", siteId);
		}
		f.append(" order by bean.priority");
		return find(f, pageNo, pageSize);
	}

	public Address findById(Long id) {
		Address entity = get(id);
		return entity;
	}

	public Address save(Address bean) {
		getSession().save(bean);
		return bean;
	}

	public Address deleteById(Long id) {
		Address entity = super.get(id);
		if (entity != null) {
			getSession().delete(entity);
		}
		return entity;
	}
	
	@Override
	protected Class<Address> getEntityClass() {
		return Address.class;
	}

	@Override
	public List<Address> getListByIdNotGys(Long parentId, String gysId) {
		// TODO Auto-generated method stub
		Finder f=Finder.create("from Address bean where 1=1 ");
		if(parentId==null){
			f.append(" and bean.parent.id is null");
		}else{
			f.append(" and bean.parent.id=:parentId");
			f.setParam("parentId", parentId);
		}
		if(gysId!=null){
			f.append(" and bean.id not in(select psqy.country.id from Psqy psqy where psqy.gys.id=:gysId)");
			f.setParam("gysId", gysId);
		}
		return find(f);
	}

	@Override
	public List<Address> getListByIdNotBld(Long parentId, String bldId) {
		// TODO Auto-generated method stub
		Finder f=Finder.create("from Address bean where 1=1 ");
		if(parentId==null){
			f.append(" and bean.parent.id is null");
		}else{
			f.append(" and bean.parent.id=:parentId");
			f.setParam("parentId", parentId);
		}
		if(bldId!=null){
			f.append(" and bean.id not in(select psqy.street.id from Psqy psqy where psqy.bld.id=:bldId)");
			f.setParam("bldId", bldId);
		}
		return find(f);
	}
}