package com.jspgou.cms.dao.impl;

import java.util.Date;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Repository;

import com.jspgou.cms.dao.ShopScoreDao;
import com.jspgou.cms.entity.ShopScore;
import com.jspgou.common.hibernate3.Finder;
import com.jspgou.common.hibernate3.HibernateBaseDao;
import com.jspgou.common.page.Pagination;

/**
* This class should preserve.
* @preserve
*/
@Repository
public class ShopScoreDaoImpl extends HibernateBaseDao<ShopScore, Long> implements ShopScoreDao {
	public Pagination getPage(Long memberId,Boolean status,Boolean useStatus,
			Date startTime,Date endTime,Integer pageSize,Integer pageNo){
		Finder f = Finder.create("select bean from ShopScore bean where 1=1 ");
		if(memberId!=null){
			f.append(" and bean.member.id=:memberId").setParam("memberId", memberId);
		}
		if(status!=null){
			f.append(" and bean.status=:status").setParam("status", status);
		}
		if(useStatus!=null){
			f.append(" and bean.useStatus=:useStatus").setParam("useStatus", useStatus);
		}
		if(startTime!=null){
			f.append(" and bean.scoreTime>:startTime");
			f.setParam("startTime", startTime);
		}
		if(endTime!=null){
			f.append(" and bean.scoreTime<:endTime");
			f.setParam("endTime", endTime);
		}
		f.append(" order by bean.id desc");
		return find(f, pageNo, pageSize);
	}
	
	public List<ShopScore> getlist(String code){
		Finder f = Finder.create("select bean from ShopScore bean where 1=1 ");
		if (!StringUtils.isBlank(code)){
			f.append(" and bean.code=:code").setParam("code", code);
		}
		return find(f);
	}

	public ShopScore findById(Long id) {
		ShopScore entity = get(id);
		return entity;
	}

	public ShopScore save(ShopScore bean) {
		getSession().save(bean);
		return bean;
	}

	public ShopScore deleteById(Long id) {
		ShopScore entity = super.get(id);
		if (entity != null) {
			getSession().delete(entity);
		}
		return entity;
	}
	
	@Override
	protected Class<ShopScore> getEntityClass() {
		return ShopScore.class;
	}
}