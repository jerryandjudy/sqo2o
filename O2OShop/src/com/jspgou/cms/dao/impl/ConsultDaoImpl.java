package com.jspgou.cms.dao.impl;

import java.util.Date;
import java.util.Iterator;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Repository;

import com.jspgou.common.hibernate3.Finder;
import com.jspgou.common.hibernate3.HibernateBaseDao;
import com.jspgou.common.page.Pagination;
import com.jspgou.cms.dao.ConsultDao;
import com.jspgou.cms.entity.Consult;

/**
* This class should preserve.
* @preserve
*/
@Repository
public class ConsultDaoImpl extends HibernateBaseDao<Consult, Long> implements
		ConsultDao {
	public Consult findById(Long id) {
		Consult entity = get(id);
		return entity;
	}

	public Consult saveOrUpdate(Consult bean) {
		getSession().saveOrUpdate(bean);
		return bean;
	}
	
	@SuppressWarnings("unchecked")
	public List<Consult> findByProductId(Long productId) {
		Finder f = Finder.create("from Consult bean where bean.product.id=:id").setParam("id", productId);
		return find(f);
	}
	
	@SuppressWarnings("unchecked")
	public Consult getSameConsult(Long memberId) {
		 Iterator<Consult> it=this.getSession().createQuery("from Consult bean where bean.member.id=:id order by bean.id desc")
		.setParameter("id", memberId).setMaxResults(1).iterate();
		 if(it.hasNext()){
			 return it.next();
		 }else{
			 return null;
		 }
	}
	
	public Pagination getPage(Long productId,String userName,String productName,
			Date startTime,Date endTime,int pageNo,int pageSize,boolean cache){
		Finder f=Finder.create("from Consult bean where 1=1 ");
	    if(productId!=null){
	    	f.append(" and bean.productSite.id=:id");
	    	f.setParam("id", productId);
	    }
		if (!StringUtils.isBlank(userName)) {
			f.append(" and bean.member.member.user.username like:userName");
			f.setParam("userName", "%"+userName+"%");
		}
	   if (!StringUtils.isBlank(productName)) {
		f.append(" and bean.product.name like:productName");
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
	
	//会员中心的查咨询
	public Pagination getPageByMember(Long memberId,int pageNo,int pageSize,boolean cache){
		Finder f=Finder.create("from Consult bean");
	    if(memberId!=null){
	    	f.append(" where bean.member.id=:id");
	    	f.setParam("id", memberId);
	    }
	    f.append(" order by bean.id desc");
		return this.find(f, pageNo, pageSize);
	}

	public Consult update(Consult bean) {
		getSession().update(bean);
		return bean;
	}

	public Consult deleteById(Long id) {
		Consult entity = super.get(id);
		if (entity != null) {
			getSession().delete(entity);
		}
		return entity;
	}

	protected Class<Consult> getEntityClass() {
		return Consult.class;
	}
}