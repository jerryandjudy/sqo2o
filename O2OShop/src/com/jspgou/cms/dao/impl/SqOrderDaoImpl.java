/**
* 吉林省艾利特信息技术有限公司
* O2OShop系统
* @date 2015年6月4日
* @author liuwang
* @version 1.0
*/
package com.jspgou.cms.dao.impl;

import java.util.Date;
import java.util.Iterator;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Repository;

import com.jspgou.cms.dao.SqOrderDao;
import com.jspgou.cms.entity.OrderItem;
import com.jspgou.cms.entity.SqOrder;
import com.jspgou.cms.entity.SqService;
import com.jspgou.common.hibernate3.Finder;
import com.jspgou.common.hibernate3.HibernateBaseDao;
import com.jspgou.common.page.Pagination;
@Repository
public class SqOrderDaoImpl extends HibernateBaseDao<SqOrder, Long> implements SqOrderDao {

	@Override
	public Pagination getPage(Long webId, Long memberId, String receiveName,
			String sellerName, Date createTime, Date finishedTime,
			Integer status, Long code, int pageNo, int pageSize) {
		// TODO Auto-generated method stub
		Finder f = Finder.create("from SqOrder bean where 1=1 ");	
		if(webId!=null){
			f.append(" and bean.website.id=:webId");
			f.setParam("webId", webId);
		}
		if(memberId!=null){
			f.append(" and bean.member.id=:memberId");
			f.setParam("memberId", memberId);
		}
		if (!StringUtils.isBlank(receiveName)) {
			f.append(" and bean.receiveName like:receiveName");
			f.setParam("receiveName", "%"+receiveName+"%");
		}
		if (!StringUtils.isBlank(sellerName)) {
			f.append(" and bean.seller.realName like:sellerName");
			f.setParam("sellerName", "%"+sellerName+"%");
		}
		if(createTime!=null){
			f.append(" and str(bean.createTime) like:createTime");
			f.setParam("createTime", "%"+createTime+"%");
		}
		if(finishedTime!=null){
			f.append(" and str(bean.finishedTime) like:finishedTime");
			f.setParam("finishedTime", "%"+finishedTime+"%");
		}
		
		if (status !=null ) {
				f.append(" and bean.status=:status");
				f.setParam("status", status);
		}
		
		if (code !=null) {
			f.append(" and bean.code like:code");
			f.setParam("code",code);
		}
		f.append(" order by bean.id desc");
		return find(f, pageNo, pageSize);
	}
	@Override
	public  SqOrder findByMember(Long memberId,Long sqServiceId){
		String hql="from SqOrder bean where bean.sqService.id=:sqServiceId and bean.member.id=:memberId";
	    Iterator<SqOrder> it=this.getSession().createQuery(hql).setParameter("memberId", memberId).setParameter("sqServiceId", sqServiceId)
	    .iterate();
	    if(it.hasNext()){
	    	return it.next();
	    }else{
	    	return null;
	    }
	}
	@Override
	public SqOrder save(SqOrder bean){
		getSession().save(bean);
		return bean;
	}
	@Override
	protected Class<SqOrder> getEntityClass() {
		// TODO Auto-generated method stub
		return SqOrder.class;
	}

}
