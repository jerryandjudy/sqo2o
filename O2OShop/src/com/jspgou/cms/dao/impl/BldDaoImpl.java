package com.jspgou.cms.dao.impl;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.jspgou.cms.dao.BldDao;
import com.jspgou.cms.dao.OrderItemDao;
import com.jspgou.cms.dao.ShopOrderTypeDao;
import com.jspgou.cms.entity.Bld;
import com.jspgou.cms.entity.Gys;
import com.jspgou.cms.entity.Logistics;
import com.jspgou.cms.entity.OrderItem;
import com.jspgou.cms.entity.ShopOrderType;
import com.jspgou.cms.manager.AddressMng;
import com.jspgou.common.hibernate3.Finder;
import com.jspgou.common.hibernate3.HibernateBaseDao;
import com.jspgou.common.hibernate3.Updater;
import com.jspgou.common.page.Pagination;
import com.jspgou.core.entity.Website;

/**
 * This class should preserve.
 * 
 * @preserve
 */
@Repository
public class BldDaoImpl extends HibernateBaseDao<Bld, String> implements BldDao {

	@Override
	public List<Bld> getAllList() {
		// TODO Auto-generated method stub
		Criteria crit = createCriteria();
		crit.addOrder(Order.desc(Bld.PROP_CREATETIME));
		List<Bld> list = crit.list();
		return list;
	}

	@Override
	public List<Bld> getBldBySiteAndPsqy(Long siteId, Long province, Long city,
			Long country, Long street) {
		// TODO Auto-generated method stub
		Finder f = Finder
				.create("select bean.bld from Psqy bean where  bean.bld is not null and bean.bld.isDisabled=false  ");
		if (siteId != null) {
			f.append(" and bean.bld.website.id=" + siteId);
		}
		if (province != null) {
			f.append(" and bean.province.id=" + province);
		}
		if (city != null) {
			f.append(" and bean.city.id=" + city);
		}
		if (country != null) {
			f.append(" and bean.country.id=" + country);
		}
		if (street != null) {
			f.append(" and bean.street.id=" + street);
		}
		f.append(" order by bean.id desc");
		return find(f);
	}

	@Override
	public List<Bld> getBldByOrder(com.jspgou.cms.entity.Order order) {
		// TODO Auto-generated method stub
		List<OrderItem>list=orderItemDao.getOrderItemByOrderId(order.getId());
		String[] psqys = order.getReceiveAddress().split(" ");
		String psqy = psqys[0] + psqys[1] + psqys[2] + psqys[3];
		String queryBasic = "select * from ";
		StringBuilder queryString = new StringBuilder();
		queryString.append(queryBasic);
		for (int i = 0; i < list.size(); i++) {
			queryString
					.append("(select bld.* from jc_core_bld bld,jxc_bld_goods_num goodnum,jc_core_psqy psqy  where bld.keta_user_id=goodnum.keta_user_id and goodnum.product_id="+list.get(i).getProductSite().getProduct().getId()+" and goodnum.goods_num>="+list.get(i).getCount()
							+" and psqy.bld_id=bld.id  and psqy.province_id in(select ad.id from jc_address ad where ad.name='"+psqys[0]+"') and psqy.city_id in(select ad.id from jc_address ad where ad.name='"+psqys[1]+"') and psqy.country_id in(select ad.id from jc_address ad where ad.name='"+psqys[2]+"') and psqy.street_id in(select ad.id from jc_address ad where ad.name='"+psqys[3]+"')");
					
					if(order.getBld()!=null){
						queryString.append("  and bld.id not in('"+order.getBld().getId()+"')");	
					}	
					if(order.getPayment().getType()==2){
						queryString.append("  and (SELECT money FROM `jc_core_account` where account_id=bld.account_id )>"+order.getLsccMoney()+" ");
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
		return getSession().createSQLQuery(queryString.toString()).addEntity(Bld.class).list();
	}

	@Override
	public Bld findById(String id) {
		// TODO Auto-generated method stub
		Bld entity = get(id);
		return entity;
	}

	@Override
	public Bld save(Bld bean) {
		// TODO Auto-generated method stub
		getSession().save(bean);
		return bean;
	}

	@Override
	public Bld deleteById(String id) {
		// TODO Auto-generated method stub
		Bld bean = findById(id);
		getSession().delete(bean);
		return bean;
	}

	@Override
	public Pagination getPageByIsDisabled(Boolean isDisabled, int pageNo,
			int pageSize) {
		// TODO Auto-generated method stub
		Finder f = Finder.create("from Bld bean where 1=1");
		if (isDisabled != null) {
			f.append(" and bean.isDisabled=" + isDisabled);
		}
		f.append(" order by bean.id desc");
		return find(f, pageNo, pageSize);
	}
	@Override
	public Pagination getPageByIsDisabled(String organizationName,Long siteId,String username,String companyName,Boolean isDisabled, int pageNo,
			int pageSize) {
		// TODO Auto-generated method stub
		Finder f = Finder.create("from Bld bean where 1=1");
		if (isDisabled != null) {
			f.append(" and bean.isDisabled=" + isDisabled);
		}
		if(username!=null){
			f.append(" and bean.ketaUser.realname like'%"+username+"%'");
		}
		if(organizationName!=null && organizationName.length()>0){
			f.append(" and bean.ketaUser.organization.name='"+organizationName+"'");
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
	public Bld findByIsDef(Boolean isDef,Long siteId){
		// TODO Auto-generated method stub
//		Finder f = Finder.create(");
		StringBuilder f=new StringBuilder("from Bld bean where 1=1");
		if (isDef != null) {
			f.append(" and bean.isDef=" + isDef);
		}
		f.append(" order by bean.id desc");
		return (Bld) findUnique(f.toString());
	}
	@Override
	@Transactional(readOnly = true)
	public Bld findByKetaUserId(Long id) {
		// TODO Auto-generated method stub
		String hql="from Bld bean where bean.ketaUser.id=?";
		return (Bld) this.findUnique(hql, id);
	}
	@Override
	public Pagination getPageBySiteId(Long siteId, int pageNo, int pageSize) {
		// TODO Auto-generated method stub
		Finder f = Finder.create("from Bld bean where 1=1");
		if (siteId != null) {
			f.append(" and bean.website.id=" + siteId);
		}
		f.append(" and bean.isDisabled=" + false);
		f.append(" order by bean.id desc");
		return find(f, pageNo, pageSize);
	}

	@Override
	protected Class<Bld> getEntityClass() {
		// TODO Auto-generated method stub
		return Bld.class;
	}

	@Override
	public Pagination getPageByGysNotHas(Bld bld, Gys gys, int pageNo,
			int pageSize) {
		// TODO Auto-generated method stub
		Finder f = Finder
				.create("from Bld bean where  bean.isDisabled=" + false);
		if (bld != null && bld.getCompanyName() != null) {
			f.append(" and bean.companyName like'%" + bld.getCompanyName()
					+ "'");
		}
		if (gys != null && gys.getId() != null) {
			f.append(" and bean.id not in(select khgx.bld.id from Khgx khgx where khgx.gys.id='"
					+ gys.getId() + "')");
		}
		if (gys != null && gys.getWebsite() != null
				&& gys.getWebsite().getId() != null) {
			f.append(" and bean.website.id=" + gys.getWebsite().getId() + ")");
		}
		f.append(" order by bean.id desc");
		return find(f, pageNo, pageSize);
	}

	@Override
	public Pagination getBldByOrder(com.jspgou.cms.entity.Order order,
			int pageNo, int pageSize) {
		// TODO Auto-generated method stub
		String[] psqys = order.getReceiveAddress().split(" ");
		String psqy = psqys[0] + psqys[1] + psqys[2] + psqys[3];

		Finder f = Finder.create(" ");
		String hql = " select goodnum.ketaUser from  OrderItem item , JxcBldGoodsNum goodnum  "
				+ " where  item.ordeR.id="
				+ order.getId()
				+ "  and  item.productSite.product.id=goodnum.product.id and item.ordeR.ordeRType=1   where goodnum.id is not null and item.count<=goodnum.goodsNum ) as user,Psqy psqy"
				+ " where  bean.ketaUser.id=user.id and psqy.bld.id=bean.id and CONCAT(psqy.province.name,psqy.city.name,psqy.country.name,psqy.street.name)='"
				+ psqy + "' ";
		f.append(hql);
		// if(order.getPayment().getType()==2){
		// f.append(" and beans.account.money>="+order.getLsccMoney());
		// }
		// if(order.getBld()!=null && order.getBld().getId().length()>0){
		// f.append(" and beans.id <> '"+order.getId()+"'");
		// }
		// f.append(" order by beans.id desc");
		return find(f, pageNo, pageSize);
	}

	@Override
	public Bld getBldByOrder(com.jspgou.cms.entity.Order order, String bldId) {
		List<OrderItem>list=orderItemDao.getOrderItemByOrderId(order.getId());
		String[] psqys = order.getReceiveAddress().split(" ");
		String queryBasic = "select * from ";
		StringBuilder queryString = new StringBuilder();
		queryString.append(queryBasic);
		for (int i = 0; i < list.size(); i++) {
			queryString
					.append("(select bld.* from jc_core_bld bld,jxc_bld_goods_num goodnum,jc_core_psqy psqy  where bld.keta_user_id=goodnum.keta_user_id and goodnum.product_id="+list.get(i).getProductSite().getProduct().getId()+" and goodnum.goods_num>="+list.get(i).getCount()
							+" and psqy.bld_id=bld.id  and psqy.province_id in(select ad.id from jc_address ad where ad.name='"+psqys[0]+"') and psqy.city_id in(select ad.id from jc_address ad where ad.name='"+psqys[1]+"') and psqy.country_id in(select ad.id from jc_address ad where ad.name='"+psqys[2]+"') and psqy.street_id in(select ad.id from jc_address ad where ad.name='"+psqys[3]+"')");
					
					if(order.getPayment().getType()==2){
						queryString.append("  and (SELECT money FROM `jc_core_account` where account_id=bld.account_id )>"+order.getLsccMoney()+" ");
					}
					queryString.append("  and bld.id ='"+bldId+"'");	
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
		List<Bld> lists=getSession().createSQLQuery(queryString.toString()).addEntity(Bld.class).list();
		Bld bld=null;
		if(lists.size()>0){
			bld=lists.get(0);
		}
		return bld;
	}
	@Override
	public Bld getBldByOrderAndOrderItem(com.jspgou.cms.entity.Order order ,OrderItem orderItem) {
		List<OrderItem>list=new ArrayList();
		list.add(orderItem);
		String[] psqys = order.getReceiveAddress().split(" ");
		String queryBasic = "select * from ";
		StringBuilder queryString = new StringBuilder();
		queryString.append(queryBasic);
		for (int i = 0; i < list.size(); i++) {
			queryString
			.append("(select bld.* from jc_core_bld bld,jxc_bld_goods_num goodnum,jc_core_psqy psqy  where bld.keta_user_id=goodnum.keta_user_id and goodnum.product_id="+list.get(i).getProductSite().getProduct().getId()+" and goodnum.goods_num>="+list.get(i).getCount()
					+" and psqy.bld_id=bld.id  and psqy.province_id in(select ad.id from jc_address ad where ad.name='"+psqys[0]+"') and psqy.city_id in(select ad.id from jc_address ad where ad.name='"+psqys[1]+"') and psqy.country_id in(select ad.id from jc_address ad where ad.name='"+psqys[2]+"') and psqy.street_id in(select ad.id from jc_address ad where ad.name='"+psqys[3]+"')");
			
			if(order.getPayment().getType()==2){
				queryString.append("  and (SELECT money FROM `jc_core_account` where account_id=bld.account_id )>"+order.getLsccMoney()+" ");
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
		List<Bld> lists=getSession().createSQLQuery(queryString.toString()).addEntity(Bld.class).list();
		Bld bld=null;
		if(lists.size()>0){
			bld=lists.get(0);
		}
		return bld;
	}
	@Autowired
	private OrderItemDao orderItemDao;
}