package com.jspgou.cms.dao.impl;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.SQLQuery;
import org.hibernate.criterion.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.jspgou.cms.dao.GysDao;
import com.jspgou.cms.dao.OrderItemDao;
import com.jspgou.cms.dao.ShopOrderTypeDao;
import com.jspgou.cms.entity.Bld;
import com.jspgou.cms.entity.Gys;
import com.jspgou.cms.entity.Logistics;
import com.jspgou.cms.entity.OrderItem;
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
public class GysDaoImpl extends HibernateBaseDao<Gys, String> implements GysDao {

	@Override
	public List<Gys> getAllList() {
		// TODO Auto-generated method stub
		Criteria crit = createCriteria();
		crit.addOrder(Order.desc(Gys.PROP_CREATETIME));
		List<Gys> list = crit.list();
		return list;
	}

	@Override
	public Gys findById(String id) {
		// TODO Auto-generated method stub
		Gys entity = get(id);
		return entity;
	}
	@Override
	public List<Gys> getGysByOrder(com.jspgou.cms.entity.Order order) {
		// TODO Auto-generated method stub
		List<OrderItem>list=orderItemDao.getOrderItemByOrderId(order.getId());
		String[] psqys = order.getReceiveAddress().split(" ");
		String queryBasic = "select * from ";
		StringBuilder queryString = new StringBuilder();
		queryString.append(queryBasic);
		for (int i = 0; i < list.size(); i++) {
			queryString
					.append("(select gys.* from jc_core_gys gys,jxc_gys_goods goodnum,jc_core_psqy psqy  where gys.id=goodnum.gysId and goodnum.goodsId="+list.get(i).getProductSite().getProduct().getId()+" and goodnum.goods_num>="+list.get(i).getCount()
							+" and psqy.gys_id=gys.id  and psqy.province_id in(select ad.id from jc_address ad where ad.name='"+psqys[0]+"') and psqy.city_id in(select ad.id from jc_address ad where ad.name='"+psqys[1]+"') and psqy.country_id in(select ad.id from jc_address ad where ad.name='"+psqys[2]+"') ");
							if(order.getGys()!=null){
								queryString.append("  and gys.id not in('"+order.getGys().getId()+")");	
							}	
							if(order.getPayment().getType()==2){
								queryString.append("  and (SELECT money FROM `jc_core_account` where account_id=gys.account_id )>"+order.getLsccMoney()+" ");
							}
							queryString.append(" ) as a");
			queryString.append(i + " ");
			if (i < list.size() - 1) {
				queryString.append(" , ");
			}
		}
		if (list.size() > 1) {
			queryString.append(" where ");
			for (int i = 0; i < list.size(); i++) {
				queryString.append("a" + i);
				queryString.append(".id");
				if (i < list.size() - 1) {
					queryString.append("=");
				}
			}
		}
		return getSession().createSQLQuery(queryString.toString()).addEntity(Gys.class).list();
	}
	@Override
	@Transactional(readOnly = true)
	public Gys findByKetaUserId(Long id) {
		// TODO Auto-generated method stub
		String hql="from Gys bean where bean.ketaUser.id=?";
		return (Gys) this.findUnique(hql, id);
	}
	
	@Override
	public Gys getGysByOrderAndOrderItem(com.jspgou.cms.entity.Order order ,OrderItem orderItem) {
		List<OrderItem>list=new ArrayList();
		list.add(orderItem);
		String[] psqys = order.getReceiveAddress().split(" ");
		String queryBasic = "select * from ";
		StringBuilder queryString = new StringBuilder();
		queryString.append(queryBasic);
		for (int i = 0; i < list.size(); i++) {
			queryString
					.append("(select gys.* from jc_core_gys gys,jxc_gys_goods goodnum,jc_core_psqy psqy  where gys.id=goodnum.gysId and goodnum.goodsId="+list.get(i).getProductSite().getProduct().getId()+" and goodnum.goods_num>="+list.get(i).getCount()
							+" and psqy.gys_id=gys.id  and psqy.province_id in(select ad.id from jc_address ad where ad.name='"+psqys[0]+"') and psqy.city_id in(select ad.id from jc_address ad where ad.name='"+psqys[1]+"') and psqy.country_id in(select ad.id from jc_address ad where ad.name='"+psqys[2]+"') ");
							if(order.getPayment().getType()==2){
								queryString.append("  and (SELECT money FROM `jc_core_account` where account_id=gys.account_id )>"+order.getLsccMoney()+" ");
							}
							queryString.append(" ) as a");
			queryString.append(i + " ");
			if (i < list.size() - 1) {
				queryString.append(" , ");
			}
		}
		if (list.size() > 1) {
			queryString.append(" where ");
			for (int i = 0; i < list.size(); i++) {
				queryString.append("a" + i);
				queryString.append(".id");
				if (i < list.size() - 1) {
					queryString.append("=");
				}
			}
		}
		 
		List<Gys> lists=getSession().createSQLQuery(queryString.toString()).addEntity(Gys.class).list();
		Gys gys=null;
		if(lists.size()>0){
			gys=lists.get(0);
		}
		return gys;
	}
	@Override
	public Gys getGysByOrder(com.jspgou.cms.entity.Order order, String gysId) {
		List<OrderItem>list=orderItemDao.getOrderItemByOrderId(order.getId());
		String[] psqys = order.getReceiveAddress().split(" ");
		String queryBasic = "select * from ";
		StringBuilder queryString = new StringBuilder();
		queryString.append(queryBasic);
		for (int i = 0; i < list.size(); i++) {
			queryString
					.append("(select gys.* from jc_core_gys gys,jxc_gys_goods goodnum,jc_core_psqy psqy  where gys.id=goodnum.gysId and goodnum.goodsId="+list.get(i).getProductSite().getProduct().getId()+" and goodnum.goods_num>="+list.get(i).getCount()
							+" and psqy.gys_id=gys.id  and psqy.province_id in(select ad.id from jc_address ad where ad.name='"+psqys[0]+"') and psqy.city_id in(select ad.id from jc_address ad where ad.name='"+psqys[1]+"') and psqy.country_id in(select ad.id from jc_address ad where ad.name='"+psqys[2]+"') ");
							if(order.getPayment().getType()==2){
								queryString.append("  and (SELECT money FROM `jc_core_account` where account_id=gys.account_id )>"+order.getLsccMoney()+" ");
							}
							queryString.append("  and gys.id ='"+gysId+"'");	
							queryString.append(" ) as a");
			queryString.append(i + " ");
			if (i < list.size() - 1) {
				queryString.append(" , ");
			}
		}
		if (list.size() > 1) {
			queryString.append(" where ");
			for (int i = 0; i < list.size(); i++) {
				queryString.append("a" + i);
				queryString.append(".id");
				if (i < list.size() - 1) {
					queryString.append("=");
				}
			}
		}
		List<Gys> lists=getSession().createSQLQuery(queryString.toString()).addEntity(Gys.class).list();
		Gys gys=null;
		if(lists.size()>0){
			gys=lists.get(0);
		}
		return gys;
	}
	@Override
	public Gys save(Gys bean) {
		// TODO Auto-generated method stub
		getSession().save(bean);
		return bean;
	}


	@Override
	public Gys deleteById(String id) {
		// TODO Auto-generated method stub
		Gys bean = findById(id);
		getSession().delete(bean);
		return bean;
	}

	@Override
	public Pagination getPageByIsDisabled(Boolean isDisabled, int pageNo,
			int pageSize) {
		// TODO Auto-generated method stub
		Finder f = Finder
				.create("from Gys bean where 1=1");
		if(isDisabled!=null){
		f.append(" and bean.isDisabled="+isDisabled);
		}
		f.append(" order by bean.id desc");
		return find(f, pageNo, pageSize);
	}
	@Override
	public Pagination getPageByIsDisabled(String organizationName,Long siteId,String username,String companyName,Boolean isDisabled, int pageNo,
			int pageSize) {
		// TODO Auto-generated method stub
		Finder f = Finder
				.create("from Gys bean where 1=1");
		if(isDisabled!=null){
			f.append(" and bean.isDisabled="+isDisabled);
		}
		if(organizationName!=null && organizationName.length()>0){
			f.append(" and bean.ketaUser.organization.name='"+organizationName+"'");
		}
		if(username!=null){
			f.append(" and bean.ketaUser.realname like'%"+username+"%'");
		}
		if(siteId!=null){
			f.append(" and bean.website.id="+siteId);
		}
		if(companyName!=null){
			f.append(" and bean.companyName like'%"+companyName+"%'");
		}
		f.append(" order by bean.createTime desc");
		return find(f, pageNo, pageSize);
	}

	@Override
	protected Class<Gys> getEntityClass() {
		// TODO Auto-generated method stub
		return Gys.class;
	}

	@Autowired
	private OrderItemDao orderItemDao;

	@Override
	public Pagination getPageBySiteAndNotGysId(String username,String companyName,Long siteId, String id,
			int pageNo, int pageSize) {
		// TODO Auto-generated method stub
		Finder f = Finder
				.create("from Gys bean where 1=1 and bean.ketaUser.organization.name='供应商'");
		if(siteId!=null){
		f.append(" and bean.website.id="+siteId);
		}
		if(siteId!=null){
			f.append(" and bean.id !='"+id+"'");
		}
		if(username!=null){
			f.append(" and bean.ketaUser.username like'%"+username+"%'");
		}
		if(companyName!=null){
			f.append(" and bean.companyName like'%"+companyName+"%'");
		}
		f.append(" order by bean.id desc");
		return find(f, pageNo, pageSize);
	}

	@Override
	public List<Gys> getListBySiteIdAndProductSiteId(Long siteId,
			Long buyProductSiteId, Long giveProductSiteId) {
		// TODO Auto-generated method stub
		String sql="select a.* from (select gys.* from jc_core_gys gys,jxc_gys_goods goods where gys.id=goods.gysid and gys.site_id="+siteId+" and goods.goodsid="+buyProductSiteId+") as a ,"
				+ "(select gys.* from jc_core_gys gys,jxc_gys_goods goods where gys.id=goods.gysid and gys.site_id="+siteId+" and goods.goodsid="+giveProductSiteId+") as b where a.id=b.id";
		SQLQuery sqlquery = getSession().createSQLQuery(sql);
		sqlquery.addEntity(Gys.class);
		return sqlquery.list();
	}

	
	
}