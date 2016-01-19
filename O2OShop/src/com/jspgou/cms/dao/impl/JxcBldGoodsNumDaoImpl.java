package com.jspgou.cms.dao.impl;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Order;
import org.springframework.stereotype.Repository;

import com.jspgou.cms.dao.BldDao;
import com.jspgou.cms.dao.JxcBldGoodsNumDao;
import com.jspgou.cms.dao.ShopOrderTypeDao;
import com.jspgou.cms.entity.Bld;
import com.jspgou.cms.entity.Gys;
import com.jspgou.cms.entity.JxcBldGoodsNum;
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
public class JxcBldGoodsNumDaoImpl extends HibernateBaseDao<JxcBldGoodsNum, Long> implements JxcBldGoodsNumDao {

	
	public List<Bld> getBldBySiteAndPsqy(Long siteId,Long province,Long city,Long country,Long street){
		// TODO Auto-generated method stub
		Finder f = Finder
				.create("select bean.bld from Psqy bean where  bean.bld is not null and bean.bld.isDisabled=false  ");
		if(siteId!=null){
		f.append(" and bean.bld.website.id="+siteId);
		}
		if(province!=null){
			f.append(" and bean.province.id="+province);
		}
		if(city!=null){
			f.append(" and bean.city.id="+city);
		}
		if(country!=null){
			f.append(" and bean.country.id="+country);
		}
		if(street!=null){
			f.append(" and bean.street.id="+street);
		}
		f.append(" order by bean.id desc");
		return find(f);
	}
	@Override
	public Pagination getPage(Long siteId,String username,String companyName, int pageNo, int pageSize){
		Finder f = Finder.create("from JxcBldGoodsNum bean where 1=1");
		if(username!=null){
			f.append(" and bean.ketaUser.username ='"+username+"'");
		}
		if(siteId!=null && siteId>0){
			f.append(" and bld.website.id="+siteId);
		}
		if(companyName!=null && companyName.length()>0){
			f.append(" and bean.ketaUser.realname like'%"+companyName+"%'");
		}
		f.append(" order by bean.goodsMinNnum-bean.goodsNum desc");
		return find(f, pageNo, pageSize);
	}

	@Override
	protected Class<JxcBldGoodsNum> getEntityClass() {
		// TODO Auto-generated method stub
		return JxcBldGoodsNum.class;
	}



	@Override
	public JxcBldGoodsNum findByProductIdAndKetaUserId(Long productId,
			Long ketaUserId) {
		String hql= "from JxcBldGoodsNum bean where  1=1 ";
		if(productId!=null){
		hql=hql+" and bean.product.id="+productId;
		}
		if(ketaUserId!=null){
			hql=hql+" and bean.ketaUser.id="+ketaUserId;
		}
		hql=hql+" order by bean.id";
		JxcBldGoodsNum entity=(JxcBldGoodsNum) findUnique(hql);
		
		return entity;
	}

	
	
}