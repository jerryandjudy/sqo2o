package com.jspgou.cms.dao.impl;

import static com.jspgou.common.page.SimplePage.cpn;

import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.hibernate.Criteria;
import org.hibernate.criterion.Order;
import org.springframework.stereotype.Repository;

import com.jspgou.cms.dao.AccountczDao;
import com.jspgou.cms.dao.ShopOrderTypeDao;
import com.jspgou.cms.entity.Accountcz;
import com.jspgou.cms.entity.KetaUser;
import com.jspgou.cms.entity.Logistics;
import com.jspgou.cms.entity.ShopOrderType;
import com.jspgou.common.hibernate3.Finder;
import com.jspgou.common.hibernate3.HibernateBaseDao;
import com.jspgou.common.hibernate3.Updater;
import com.jspgou.common.page.Pagination;
import com.jspgou.common.web.CookieUtils;
import com.jspgou.core.entity.Website;

/**
* This class should preserve.
* @preserve
*/
@Repository
public class AccountczDaoImpl extends HibernateBaseDao<Accountcz, Long> implements AccountczDao {

	@Override
	public List<Accountcz> getAllList() {
		// TODO Auto-generated method stub
		Criteria crit = createCriteria();
		crit.addOrder(Order.desc(Accountcz.PROP_CREATETIME));
		List<Accountcz> list = crit.list();
		return list;
	}

	@Override
	public Accountcz findById(Long id) {
		// TODO Auto-generated method stub
		Accountcz entity = get(id);
		return entity;
	}

	@Override
	public Accountcz save(Accountcz bean) {
		// TODO Auto-generated method stub
		getSession().save(bean);
		return bean;
	}


	@Override
	public Accountcz deleteById(Long id) {
		// TODO Auto-generated method stub
		Accountcz bean = findById(id);
		getSession().delete(bean);
		return bean;
	}

	@Override
	public Pagination getPage(String payStatus,String username,String siteId,Long organizationId,String createTime, int pageNo,
			int pageSize) {
		// TODO Auto-generated method stub
		Finder f = Finder
				.create("from Accountcz bean where 1=1");
		if(username!=null && username.length()>0){
		f.append(" and bean.username='"+username+"' ");
		}
		if(siteId!=null && siteId.length()>0){
			f.append(" and bean.website.id="+siteId+" ");
		}
		if(organizationId!=null && organizationId>0){
			f.append(" and bean.ketaUser.organization.id="+organizationId+" ");
		}
		if(createTime!=null && createTime.length()>0){
			f.append(" and bean.addTime like '"+createTime+"%' ");
		}
		if(payStatus!=null && payStatus.length()>0){
			f.append(" and bean.payStatus = '"+payStatus+"' ");
		}
		f.append(" order by bean.id desc");
		return find(f, pageNo, pageSize);
	}

	@Override
	protected Class<Accountcz> getEntityClass() {
		// TODO Auto-generated method stub
		return Accountcz.class;
	}

	@Override
	public Pagination findAllUsers(Long siteId,Long organizationId,int pageNo,
			int pageSize) {
		String dataTable = "";
		
		
		Finder f = Finder
				.create("from  KetaUser bean where bean.status='enabled'");
		if(siteId!=null){
			f.append(" and bean.website.id="+siteId);
		}
		if(organizationId!=null){
			f.append(" and bean.organization.id="+organizationId);
		}
		f.append(" order by bean.id desc");
		return find(f, pageNo, pageSize);
	}
	
	
	@Override
	public Pagination getPage(String  queryUsername, String  queryName,  int pageNo, String jmftypeIdstr, int pageSize) {
		String dataTable = "";
		if("39".equals(jmftypeIdstr)){//供应商加盟费
			dataTable = "Gys";
		}else if("40".equals(jmftypeIdstr)){//便利店加盟费
			dataTable = "Bld";
		}else if("42".equals(jmftypeIdstr)){//代理商加盟费
			dataTable = "Bls";
		}
		
		
		Finder f = Finder
				.create("from "+dataTable+" bean where 1=1");
		
		
		
		if (queryUsername != null && queryUsername.trim().length()>0) {
			f.append(" and bean.ketaUser.username like :username");
			f.setParam("username", "%" + queryUsername + "%");
		}
		if (queryName != null && queryName.trim().length()>0) {
			f.append(" and bean.ketaUser.realname like :realname");
			f.setParam("realname", "%" + queryName + "%");
		}
		
		f.append(" order by bean.id desc");
		return find(f, pageNo, pageSize);
	}



	
	
}