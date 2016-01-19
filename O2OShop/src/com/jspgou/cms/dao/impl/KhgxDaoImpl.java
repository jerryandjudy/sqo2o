package com.jspgou.cms.dao.impl;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Order;
import org.springframework.stereotype.Repository;

import com.jspgou.cms.dao.GysDao;
import com.jspgou.cms.dao.KhgxDao;
import com.jspgou.cms.dao.ShopOrderTypeDao;
import com.jspgou.cms.entity.Gys;
import com.jspgou.cms.entity.Khgx;
import com.jspgou.cms.entity.Logistics;
import com.jspgou.cms.entity.ShopOrderType;
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
public class KhgxDaoImpl extends HibernateBaseDao<Khgx, Long> implements KhgxDao {


	@Override
	protected Class<Khgx> getEntityClass() {
		// TODO Auto-generated method stub
		return Khgx.class;
	}

	@Override
	public Khgx findById(Long id) {
		// TODO Auto-generated method stub
		return get(id);
	}

	@Override
	public Khgx save(Khgx bean) {
		// TODO Auto-generated method stub
		getSession().save(bean);
		return bean;
	}

	@Override
	public Khgx deleteById(Long id) {
		// TODO Auto-generated method stub
		Khgx bean = this.findById(id);
		getSession().delete(bean);
		return bean;
	}
	@Override
	public List getListByGysAndBldId(Gys gys,String[] ids){
		Finder f = Finder.create("from Khgx bean where bean.gys.id='"+gys.getId()+"'");
		if(ids!=null && ids.length>0){
			String id=" and bean.bld.id in(";
		for(String idd:ids){
			id+="'"+idd+"',";
		}
		id=id.substring(0,id.length()-1)+")";
		f.append(id);
		}
		return this.find(f);
		
		
	}
	@Override
	public Pagination getPage(Khgx khgx, int pageNo, int pageSize) {
		// TODO Auto-generated method stub
		Finder f = Finder
				.create("from Khgx bean where 1=1");
		if(khgx!=null){
		if(khgx.getGys()!=null && khgx.getGys().getCompanyName()!=null){
		f.append(" and bean.gys.companyName like'%"+khgx.getGys().getCompanyName()+"%'");
		}
		if(khgx.getGys()!=null && khgx.getGys().getId()!=null){
			f.append(" and bean.gys.id ='"+khgx.getGys().getId()+"'");
		}
		if(khgx.getBld()!=null && khgx.getBld().getCompanyName()!=null){
			f.append(" and bean.bld.companyName  like'%"+khgx.getBld().getCompanyName()+"%'");
		}
		if(khgx.getBld()!=null && khgx.getBld().getId()!=null){
			f.append(" and bean.bld.id  ='"+khgx.getBld().getId()+"'");
		}
		if(khgx.getWebsite()!=null &&khgx.getWebsite().getId()!=null ){
			f.append(" and bean.website.id="+khgx.getWebsite().getId());
		}
		}
		f.append(" order by bean.createTime desc");
		return find(f, pageNo, pageSize);
	}

	
	
}