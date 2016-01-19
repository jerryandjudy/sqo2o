package com.jspgou.cms.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.jspgou.common.hibernate3.Finder;
import com.jspgou.common.hibernate3.HibernateBaseDao;
import com.jspgou.common.page.Pagination;
import com.jspgou.core.entity.Website;
import com.jspgou.cms.dao.AdvertiseDao;
import com.jspgou.cms.entity.Advertise;

/**
* This class should preserve.
* @preserve
*/
@Repository
public class AdvertiseDaoImpl extends
		HibernateBaseDao<Advertise, Integer> implements AdvertiseDao {
	public Pagination getPage(Long categoryId, Integer adspaceId,
			Boolean enabled, int pageNo, int pageSize,Integer count) {
		Finder f = Finder.create("from Advertise bean where 1=1");
		if (categoryId != null) {
			f.append(" and bean.categoryId=:categoryId");
			f.setParam("categoryId", categoryId);
		}
		if (adspaceId != null) {
			f.append(" and bean.adspace.id=:adspaceId");
			f.setParam("adspaceId", adspaceId);
		}
		if (enabled != null) {
			f.append(" and bean.enabled=:enabled");
			f.setParam("enabled", enabled);
		}
		f.append(" and ( bean.startTime < now()  or bean.startTime is  null )");
		f.append(" and (bean.endTime > now()  or bean.endTime is  null)");
		f.append(" order by bean.weight asc,bean.id desc");
		if (count != null) {
			pageSize=count;
		}
		
		return find(f, pageNo, pageSize);
	}
	public Pagination getPage(Website website, Integer adspaceId,
			Boolean enabled, int pageNo, int pageSize,Integer count) {
		Finder f = Finder.create("from Advertise bean where 1=1");
		if (website != null) {
			f.append(" and bean.website.id=:website");
			f.setParam("website", website);
		}
		if (adspaceId != null) {
			f.append(" and bean.adspace.id=:adspaceId");
			f.setParam("adspaceId", adspaceId);
		}
		if (enabled != null) {
			f.append(" and bean.enabled=:enabled");
			f.setParam("enabled", enabled);
		}
		f.append(" and ( bean.startTime < now()  or bean.startTime is  null )");
		f.append(" and ( bean.endTime > now()  or bean.endTime is  null)");
		f.append(" order by bean.weight asc,bean.id desc");
		return find(f, pageNo, pageSize);
	}

	@SuppressWarnings("unchecked")
	public List<Advertise> getList(Integer adspaceId, Boolean enabled) {
		Finder f = Finder.create("from Advertise bean where 1=1");
		if (adspaceId != null) {
			f.append(" and bean.adspace.id=:adspaceId");
			f.setParam("adspaceId", adspaceId);
		}
		if (enabled != null) {
			f.append(" and bean.enabled=:enabled");
			f.setParam("enabled", enabled);
		}
		f.append(" and ( bean.startTime < now() )");
		f.append(" and (bean.endTime > now() )");
		f.append(" order by bean.weight asc,bean.id desc");
		return find(f);
	}

	public Advertise findById(Integer id) {
		Advertise entity = get(id);
		return entity;
	}

	public Advertise save(Advertise bean) {
		getSession().save(bean);
		return bean;
	}

	public Advertise deleteById(Integer id) {
		Advertise entity = super.get(id);
		if (entity != null) {
			getSession().delete(entity);
		}
		return entity;
	}

	@Override
	protected Class<Advertise> getEntityClass() {
		return Advertise.class;
	}
}