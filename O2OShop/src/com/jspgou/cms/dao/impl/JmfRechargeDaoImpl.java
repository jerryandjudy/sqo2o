package com.jspgou.cms.dao.impl;

import static com.jspgou.common.page.SimplePage.cpn;

import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.hibernate.Criteria;
import org.hibernate.criterion.Order;
import org.springframework.stereotype.Repository;

import com.jspgou.cms.dao.JmfRechargeDao;
import com.jspgou.cms.dao.ShopOrderTypeDao;
import com.jspgou.cms.entity.JmfRecharge;
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
public class JmfRechargeDaoImpl extends HibernateBaseDao<JmfRecharge, Long> implements JmfRechargeDao {

	@Override
	public List<JmfRecharge> getAllList() {
		// TODO Auto-generated method stub
		Criteria crit = createCriteria();
		crit.addOrder(Order.desc(JmfRecharge.PROP_CREATETIME));
		List<JmfRecharge> list = crit.list();
		return list;
	}

	@Override
	public JmfRecharge findById(Long id) {
		// TODO Auto-generated method stub
		JmfRecharge entity = get(id);
		return entity;
	}

	@Override
	public JmfRecharge save(JmfRecharge bean) {
		// TODO Auto-generated method stub
		getSession().save(bean);
		return bean;
	}


	@Override
	public JmfRecharge deleteById(Long id) {
		// TODO Auto-generated method stub
		JmfRecharge bean = findById(id);
		getSession().delete(bean);
		return bean;
	}

	@Override
	public Pagination getPageByIsDisabled(Boolean isDisabled, int pageNo,
			int pageSize) {
		// TODO Auto-generated method stub
		Finder f = Finder
				.create("from JmfRecharge bean where 1=1");
		if(isDisabled!=null){
		f.append(" and 2=2 ");
		}
		f.append(" order by bean.id desc");
		return find(f, pageNo, pageSize);
	}

	@Override
	protected Class<JmfRecharge> getEntityClass() {
		// TODO Auto-generated method stub
		return JmfRecharge.class;
	}

	@Override
	public Pagination findAllUsers(Long siteId,String jmftypeIdstr,int pageNo,
			int pageSize) {
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
		if(siteId!=null){
			f.append(" and bean.website.id="+siteId);
		}
		f.append(" order by bean.id desc");
		return find(f, pageNo, pageSize);
	}
	
	public List findFC(String jmftypeIdstr){
		Finder f = Finder.create("from Jmf bean where bean.isDisabled = false and bean.jmftypeId ='"+jmftypeIdstr+"'");
		return find(f);
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