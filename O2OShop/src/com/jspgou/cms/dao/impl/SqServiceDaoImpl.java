/**
* 吉林省艾利特信息技术有限公司
* O2OShop系统
* @date 2015年6月4日
* @author liuwang
* @version 1.0
*/
package com.jspgou.cms.dao.impl;

import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Repository;

import com.jspgou.cms.dao.SqServiceDao;
import com.jspgou.cms.entity.SqService;
import com.jspgou.common.hibernate3.Finder;
import com.jspgou.common.hibernate3.HibernateBaseDao;
import com.jspgou.common.page.Pagination;
@Repository
public class SqServiceDaoImpl extends HibernateBaseDao<SqService, Long> implements SqServiceDao {

	@Override
	public List<SqService> getList(Long ketaUserId,Long siteId,Long ctId, Boolean groom, Boolean status,
			Long provinceId,Long cityId,Long countryId,Long streetId, String name, Integer count) {
		// TODO Auto-generated method stub
		Finder f=Finder.create("from SqService bean where bean.ketaUser.status='enabled'");
		if(ctId!=null && ctId>0){
			f.append(" and bean.category.id=:ctId").setParam("ctId", ctId);
		}
		if(status==null){
			status=true;
		}
		f.append(" and bean.status=:status").setParam("status", status);
		if(ketaUserId!=null){
			f.append(" and bean.ketaUser.id=:ketaUserId").setParam("ketaUserId", ketaUserId);
		}
		if(siteId!=null){
			f.append(" and bean.website.id=:siteId").setParam("siteId", siteId);
		}
		if(provinceId!=null){
			f.append(" and bean.province.id=:provinceId").setParam("provinceId", provinceId);
		}
		if(cityId!=null){
			f.append(" and bean.city.id=:cityId").setParam("cityId", cityId);
		}
		if(countryId!=null){
			f.append(" and bean.country.id=:countryId").setParam("countryId", countryId);
		}
		if(streetId!=null){
			f.append(" and bean.street.id=:streetId").setParam("streetId", streetId);
		}
		if(groom!=null){
			f.append(" and bean.groom=:groom").setParam("groom", groom);
		}
		if(name!=null  && name.trim().length()>0){
			f.append(" and bean.name like '%"+groom+"%'");
		}
		if(count!=null){
		f.setMaxResults(count);
		}
		return this.find(f);
	}

	@Override
	protected Class<SqService> getEntityClass() {
		// TODO Auto-generated method stub
		return SqService.class;
	}

	@Override
	public SqService getById(Long id) {
		// TODO Auto-generated method stub
		return this.get(id);
	}

	@Override
	public Pagination getPage(Long ketaUserId, Long siteId, Long ctId,
			Boolean groom, Boolean status, Long provinceId, Long cityId,
			Long countryId, Long streetId, String name, int pageNo, int pageSize) {
		// TODO Auto-generated method stub
		Finder f=Finder.create("from SqService bean where  bean.ketaUser.status='enabled'");
		if(ctId!=null && ctId>0){
			f.append(" and bean.category.id=:ctId").setParam("ctId", ctId);
		}
		if(status==null){
			status=true;
		}
		f.append(" and bean.status=:status").setParam("status", status);
		if(ketaUserId!=null){
			f.append(" and bean.ketaUser.id=:ketaUserId").setParam("ketaUserId", ketaUserId);
		}
		if(siteId!=null){
			f.append(" and bean.website.id=:siteId").setParam("siteId", siteId);
		}
		if(provinceId!=null){
			f.append(" and bean.province.id=:provinceId").setParam("provinceId", provinceId);
		}
		if(cityId!=null){
			f.append(" and bean.city.id=:cityId").setParam("cityId", cityId);
		}
		if(countryId!=null){
			f.append(" and bean.country.id=:countryId").setParam("countryId", countryId);
		}
		if(streetId!=null){
			f.append(" and bean.street.id=:streetId").setParam("streetId", streetId);
		}
		if(groom!=null){
			f.append(" and bean.groom=:groom").setParam("groom", groom);
		}
		if(name!=null  && name.trim().length()>0){
			f.append(" and bean.name like '%"+groom+"%'");
		}
		return find(f, pageNo, pageSize);
	}

}
