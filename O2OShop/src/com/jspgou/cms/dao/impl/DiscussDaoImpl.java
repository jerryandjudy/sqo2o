package com.jspgou.cms.dao.impl;

import java.util.Date;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Repository;

import com.jspgou.common.hibernate3.Finder;
import com.jspgou.common.hibernate3.HibernateBaseDao;
import com.jspgou.common.page.Pagination;
import com.jspgou.cms.dao.DiscussDao;
import com.jspgou.cms.entity.Discuss;

/**
* This class should preserve.
* @preserve
*/
@Repository
public class DiscussDaoImpl extends HibernateBaseDao<Discuss, Long> implements
		DiscussDao {
	public Discuss findById(Long id) {
		Discuss entity = get(id);
		return entity;
	}

	public Discuss saveOrUpdate(Discuss bean) {
		getSession().saveOrUpdate(bean);
		return bean;
	}

	public Discuss update(Discuss bean) {
		getSession().update(bean);
		return bean;
	}
	
	public Pagination getPageByProduct(Long productId,String userName,String productName,
			Date startTime,Date endTime,int pageNo,int pageSize,boolean cache){
		Finder f=Finder.create("from Discuss bean where 1=1 ");
		if(productId!=null){
			f.append(" and bean.productSite.id=:id");
			f.setParam("id", productId);
		}
		if (!StringUtils.isBlank(userName)) {
			f.append(" and bean.member.member.user.username like:userName");
			f.setParam("userName", "%"+userName+"%");
		}
		if (!StringUtils.isBlank(productName)) {
			f.append(" and bean.productSite.product.name like:productName");
			f.setParam("productName", "%"+productName+"%");
		}
		if(startTime!=null){
			f.append(" and bean.time>:startTime");
			f.setParam("startTime", startTime);
		}
		if(endTime!=null){
			f.append(" and bean.time<:endTime");
			f.setParam("endTime", endTime);
		}
		f.append(" order by bean.id desc");
		return find(f, pageNo, pageSize);
	}
	public Pagination getPageBySqService(Long sqServiceId,String userName,String productName,
			Date startTime,Date endTime,int pageNo,int pageSize,boolean cache){
		Finder f=Finder.create("from Discuss bean where 1=1 ");
		if(sqServiceId!=null){
			f.append(" and bean.sqService.id=:id");
			f.setParam("id", sqServiceId);
		}
		if (!StringUtils.isBlank(userName)) {
			f.append(" and bean.member.member.user.username like:userName");
			f.setParam("userName", "%"+userName+"%");
		}
		if (!StringUtils.isBlank(productName)) {
			f.append(" and bean.productSite.product.name like:productName");
			f.setParam("productName", "%"+productName+"%");
		}
		if(startTime!=null){
			f.append(" and bean.time>:startTime");
			f.setParam("startTime", startTime);
		}
		if(endTime!=null){
			f.append(" and bean.time<:endTime");
			f.setParam("endTime", endTime);
		}
		f.append(" order by bean.id desc");
		return find(f, pageNo, pageSize);
	}
	
	public Pagination getPageByMember(Long memberId,int pageNo,int pageSize,boolean cache){
		Finder f=Finder.create("from Discuss bean");
		if(memberId!=null){
			f.append(" where bean.member.id=:id");
			f.setParam("id", memberId);
		}
		f.append(" order by bean.id desc");
		return this.find(f, pageNo, pageSize);
	}


	public Discuss deleteById(Long id) {
		Discuss entity = super.get(id);
		if (entity != null) {
			getSession().delete(entity);
		}
		return entity;
	}

	protected Class<Discuss> getEntityClass() {
		return Discuss.class;
	}
}