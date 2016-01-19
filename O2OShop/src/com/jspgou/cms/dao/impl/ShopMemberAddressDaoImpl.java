package com.jspgou.cms.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.jspgou.cms.dao.ShopMemberAddressDao;
import com.jspgou.cms.entity.ShopMemberAddress;
import com.jspgou.common.hibernate3.Finder;
import com.jspgou.common.hibernate3.HibernateBaseDao;

/**
* This class should preserve.
* @preserve
*/
@Repository
public class ShopMemberAddressDaoImpl extends HibernateBaseDao<ShopMemberAddress, Long>
		implements ShopMemberAddressDao {
	@SuppressWarnings("unchecked")
	public List<ShopMemberAddress> getList(Long memberId) {
		String hql = "from ShopMemberAddress bean where bean.member.id=? order by bean.isDefault desc";
		return find(hql, memberId);
	}
	@SuppressWarnings("unchecked")
	public List<ShopMemberAddress> getList(Long memberId,Long siteId) {
		String hql = "from ShopMemberAddress bean where bean.member.id=? and bean.website.id=? order by bean.isDefault desc";
		return find(hql, memberId,siteId);
	}
	
	@SuppressWarnings("unchecked")
	public List<ShopMemberAddress> findByMemberDefault(Long memberId,Boolean isDefault){
		Finder f=Finder.create("from ShopMemberAddress bean where 1=1");
		if(memberId!=null){
		  f.append(" and bean.member.id=:memberId");
		  f.setParam("memberId", memberId);
		}
		if(isDefault!=null){
			  f.append(" and bean.isDefault=:isDefault ");
			  f.setParam("isDefault", isDefault);
		}
		f.append(" order by bean.isDefault desc");
		return find(f);
	}

	public ShopMemberAddress findById(Long id) {
		ShopMemberAddress entity = get(id);
		return entity;
	}

	public ShopMemberAddress save(ShopMemberAddress bean) {
		getSession().save(bean);
		return bean;
	}

	public ShopMemberAddress deleteById(Long id) {
		ShopMemberAddress entity = super.get(id);
		if (entity != null) {
			getSession().delete(entity);
		}
		return entity;
	}

	@Override
	protected Class<ShopMemberAddress> getEntityClass() {
		return ShopMemberAddress.class;
	}
}