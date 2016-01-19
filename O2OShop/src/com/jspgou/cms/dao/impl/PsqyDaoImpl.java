package com.jspgou.cms.dao.impl;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Order;
import org.springframework.stereotype.Repository;

import com.jspgou.cms.dao.PsqyDao;
import com.jspgou.cms.entity.Bld;
import com.jspgou.cms.entity.Gys;
import com.jspgou.cms.entity.Psqy;
import com.jspgou.common.hibernate3.Finder;
import com.jspgou.common.hibernate3.HibernateBaseDao;
import com.jspgou.common.hibernate3.Updater;
import com.jspgou.common.page.Pagination;
import com.jspgou.core.entity.Website;

/**
* This class should preserve.
* @preserve
*/
@Repository
public class PsqyDaoImpl extends HibernateBaseDao<Psqy, Long> implements PsqyDao {

	@Override
	public List<Psqy> getAllList() {
		// TODO Auto-generated method stub
		Criteria crit = createCriteria();
		crit.addOrder(Order.desc(Psqy.PROP_CREATETIME));
		List<Psqy> list = crit.list();
		return list;
	}


	@Override
	public Psqy save(Psqy bean) {
		// TODO Auto-generated method stub
		getSession().save(bean);
		return bean;
	}
	@Override
	public Psqy findById(Long id) {
		// TODO Auto-generated method stub
		Psqy bean = get(id);
		return bean;
	}


	@Override
	public Psqy deleteById(Long id) {
		// TODO Auto-generated method stub
		Psqy bean = findById(id);
		getSession().delete(bean);
		return bean;
	}

	@Override
	public Pagination getGysPage( int pageNo,int pageSize) {
		// TODO Auto-generated method stub
		Finder f = Finder
				.create("from Psqy bean where 1=1");
		f.append(" and bean.bld.id is  null");
		f.append(" order by bean.gys.id desc");
		return find(f, pageNo, pageSize);
	}
	@Override
	public Pagination getGysPage(Long siteId,String username,String companyName, int pageNo,int pageSize) {
		// TODO Auto-generated method stub
		Finder f = Finder
				.create("from Psqy bean where bean.gys.isDisabled=false");
		f.append(" and bean.bld.id is  null");
		if(username!=null){
			f.append(" and bean.gys.ketaUser.realname like'%"+username+"%'");
		}
		if(siteId!=null){
			f.append(" and bean.gys.website.id ="+siteId);
		}
		if(companyName!=null){
			f.append(" and bean.gys.companyName like'%"+companyName+"%'");
		}
		f.append(" order by bean.gys.id desc");
		return find(f, pageNo, pageSize);
	}
	@Override
	public Pagination getBldPage( int pageNo,int pageSize) {
		// TODO Auto-generated method stub
		Finder f = Finder
				.create("from Psqy bean where 1=1");
		f.append(" and bean.gys.id is  null");
		f.append(" order by bean.bld.id desc");
		return find(f, pageNo, pageSize);
	}
	@Override
	public Pagination getBldPage(Long siteId,String username,String companyName, int pageNo,int pageSize) {
		// TODO Auto-generated method stub
		Finder f = Finder
				.create("from Psqy bean where 1=1");
		if(username!=null){
			f.append(" and bean.bld.ketaUser.realname like'%"+username+"%'");
		}
		if(siteId!=null){
			f.append(" and bean.bld.website.id ="+siteId);
		}
		if(companyName!=null){
			f.append(" and bean.bld.companyName like'%"+companyName+"%'");
		}
		f.append(" and bean.gys.id is  null");
		f.append(" order by bean.bld.id desc");
		return find(f, pageNo, pageSize);
	}

	@Override
	protected Class<Psqy> getEntityClass() {
		// TODO Auto-generated method stub
		return Psqy.class;
	}


	@Override
	public Pagination getGysPageByGys(Gys gys, String username,
			String companyName, int pageNo, int pageSize) {
		// TODO Auto-generated method stub
		Finder f = Finder
				.create("from Psqy bean where 1=1");
		f.append(" and bean.bld.id is  null");
		if(username!=null){
			f.append(" and bean.gys.ketaUser.realname like'%"+username+"%'");
		}
		if(gys!=null&&gys.getId()!=null){
			f.append(" and bean.gys.id ='"+gys.getId()+"'");
		}
		if(companyName!=null){
			f.append(" and bean.gys.companyName like'%"+companyName+"%'");
		}
		f.append(" order by bean.gys.id desc");
		return find(f, pageNo, pageSize);
	}


	@Override
	public Pagination getBldPageByBld(Bld bld, String username,
			String companyName, int pageNo, int pageSize) {
		// TODO Auto-generated method stub
		Finder f = Finder
				.create("from Psqy bean where 1=1");
		f.append(" and bean.gys.id is  null");
		if(username!=null){
			f.append(" and bean.bld.ketaUser.realname like'%"+username+"%'");
		}
		if(bld!=null&&bld.getId()!=null){
			f.append(" and bean.bld.id ='"+bld.getId()+"'");
		}
		if(companyName!=null){
			f.append(" and bean.bld.companyName like'%"+companyName+"%'");
		}
		f.append(" order by bean.bld.id desc");
		return find(f, pageNo, pageSize);
	}

	

	
	
}