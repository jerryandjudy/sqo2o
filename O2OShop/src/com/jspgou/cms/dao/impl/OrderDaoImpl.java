package com.jspgou.cms.dao.impl;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.hibernate.Query;
import org.springframework.stereotype.Repository;

import com.jspgou.common.hibernate3.Finder;
import com.jspgou.common.hibernate3.HibernateBaseDao;
import com.jspgou.common.page.Pagination;
import com.jspgou.cms.dao.OrderDao;
import com.jspgou.cms.entity.Order;

/**
* This class should preserve.
* @preserve
*/
@Repository
public class OrderDaoImpl extends HibernateBaseDao<Order, Long> implements
		OrderDao {
	public Pagination getPageForMember(Long memberId, int pageNo, int pageSize) {//会员中心(我的王成订单)
		Finder f = Finder
				.create("from Order bean where bean.member.id=:memberId");
		f.setParam("memberId", memberId);
		f.append(" and bean.status=40");
		f.append(" order by bean.id desc");
		return find(f, pageNo, pageSize);
	}
	
	
	public Pagination getPageForOrderReturn(Long memberId, int pageNo, int pageSize) {//会员中心(退货订单)
		Finder f = Finder.create("from Order bean where bean.returnOrder.id is not null");
		if(memberId!=null){
			f.append(" and bean.member.id=:memberId");
			f.setParam("memberId", memberId);
		}
		f.append(" order by bean.id desc");
		return find(f, pageNo, pageSize);
	}
	
	
	public Pagination getPageForMember1(Long memberId, int pageNo, int pageSize) {//会员中心(我的待付款订单)
		Finder f = Finder
				.create("from Order bean where bean.member.id=:memberId");
		f.setParam("memberId", memberId);
		f.append(" and bean.status>=10 and bean.status<=19");
		f.append(" order by bean.id desc");
		return find(f, pageNo, pageSize);
	}
	public Pagination getPageForMember2(Long memberId, int pageNo, int pageSize) {//会员中心(我的处理中订单)
		Finder f = Finder
				.create("from Order bean where bean.member.id=:memberId");
		f.setParam("memberId", memberId);
		f.append(" and bean.status>=20 and bean.status<=29");
		f.append(" order by bean.id desc");
		return find(f, pageNo, pageSize);
	}
	
	public List<Order> getlist(Date endDate) {
		Finder f = Finder.create("from Order bean where bean.payment.type=1");
		f.append(" and bean.paymentStatus=:paymentStatus");
		f.append(" and bean.createTime<:endTime");
		f.append(" and (bean.status=:checking or bean.status=:checked)");
		f.setParam("checking", CHECKING);
		f.setParam("checked", CHECKED);
		f.setParam("endTime", endDate);
		f.setParam("paymentStatus", CHECKING);
		return find(f);
	}
	

	
	public Pagination getPageForMember3(Long memberId, int pageNo, int pageSize) {//会员中心(全部订单)
		Finder f = Finder
				.create("from Order bean where bean.member.id=:memberId");
		f.setParam("memberId", memberId);
		f.append(" order by bean.id desc");
		return find(f, pageNo, pageSize);
	}

	public Pagination getPage(Long webId,Long memberId,String productName, String userName,
			Long paymentId, Long shippingId, Date startTime,Date endTime,
			Double startOrderTotal,Double endOrderTotal,Integer status,Integer paymentStatus,Integer shippingStatus,Long code,
			int pageNo, int pageSize) {
		Finder f = Finder.create("from Order bean where 1=1 ");	
		if(webId!=null){
			f.append(" and bean.website.id=:webId");
			f.setParam("webId", webId);
		}
		if(memberId!=null){
			f.append(" and bean.member.id=:memberId");
			f.setParam("memberId", memberId);
		}
		if (!StringUtils.isBlank(userName)) {
			f.append(" and bean.receiveName like:userName");
			f.setParam("userName", "%"+userName+"%");
		}
		if (!StringUtils.isBlank(productName)) {
			f.append(" and bean.productName like:productName");
			f.setParam("productName", "%"+productName+"%");
		}
		if(paymentId!=null){
			f.append(" and bean.payment.id=:paymentId");
			f.setParam("paymentId", paymentId);
		}
		if(shippingId!=null){
			f.append(" and bean.shipping.id=:shippingId");
			f.setParam("shippingId", shippingId);
		}
		if(startTime!=null){
			f.append(" and bean.createTime>:startTime");
			f.setParam("startTime", startTime);
		}
		if(endTime!=null){
			f.append(" and bean.createTime<:endTime");
			f.setParam("endTime", endTime);
		}
		if(startOrderTotal!=null){
			f.append(" and bean.total>=:startOrderTotal");
			f.setParam("startOrderTotal", startOrderTotal);
		}
		if(endOrderTotal!=null){
			f.append(" and bean.total<=:endOrderTotal");
			f.setParam("endOrderTotal", endOrderTotal);
		}
		if (status !=null ) {
			if(status == 5){
				f.append(" and (bean.status=:checking or bean.status=:checked)");
				f.append(" and bean.paymentStatus=:payment");
				f.setParam("checking", CHECKING);
				f.setParam("checked", CHECKED);
				f.setParam("payment", CHECKING);
			}else if(status==6){
				f.append(" and ((bean.payment.type=1 and bean.paymentStatus=:payment)or(bean.payment.type=2))");
				f.append(" and bean.status=:checked");
				f.append(" and bean.shippingStatus=:shipping");
				f.setParam("checked", CHECKED);
				f.setParam("shipping", CHECKING);
				f.setParam("payment", CHECKING);
			}else{
				f.append(" and bean.status=:status");
				f.setParam("status", status);
			}
		}
		if (paymentStatus !=null) {
			f.append(" and bean.paymentStatus=:paymentStatus");
			f.setParam("paymentStatus", paymentStatus);
		}
		if (shippingStatus !=null) {
			f.append(" and bean.shippingStatus=:shippingStatus");
			f.setParam("shippingStatus", shippingStatus);
		}
		if (code !=null) {
			f.append(" and bean.code=:code");
			f.setParam("code",code);
		}
		f.append(" order by bean.id desc");
		return find(f, pageNo, pageSize);
	}
	
	public Pagination getPage(Integer ordeRType,Long webId,Long memberId,String productName, String userName,
			Long paymentId, Long shippingId, Date startTime,Date endTime,
			Double startOrderTotal,Double endOrderTotal,Integer status,Integer paymentStatus,Integer shippingStatus,Long code,
			int pageNo, int pageSize) {
		Finder f = Finder.create("from Order bean where 1=1 ");	
		if(webId!=null){
			f.append(" and bean.website.id=:webId");
			f.setParam("webId", webId);
		}
		if(memberId!=null){
			f.append(" and bean.member.id=:memberId");
			f.setParam("memberId", memberId);
		}
		if (!StringUtils.isBlank(userName)) {
			f.append(" and bean.receiveName like:userName");
			f.setParam("userName", "%"+userName+"%");
		}
		if (!StringUtils.isBlank(productName)) {
			f.append(" and bean.productName like:productName");
			f.setParam("productName", "%"+productName+"%");
		}
		if(paymentId!=null){
			f.append(" and bean.payment.id=:paymentId");
			f.setParam("paymentId", paymentId);
		}
		if(ordeRType!=null){
			f.append(" and bean.ordeRType=:ordeRType");
			f.setParam("ordeRType", ordeRType);
		}
		if(shippingId!=null){
			f.append(" and bean.shipping.id=:shippingId");
			f.setParam("shippingId", shippingId);
		}
		if(startTime!=null){
			f.append(" and bean.createTime>:startTime");
			f.setParam("startTime", startTime);
		}
		if(endTime!=null){
			f.append(" and bean.createTime<:endTime");
			f.setParam("endTime", endTime);
		}
		if(startOrderTotal!=null){
			f.append(" and bean.total>=:startOrderTotal");
			f.setParam("startOrderTotal", startOrderTotal);
		}
		if(endOrderTotal!=null){
			f.append(" and bean.total<=:endOrderTotal");
			f.setParam("endOrderTotal", endOrderTotal);
		}
		if (status !=null ) {
			if(status == 5){
				f.append(" and (bean.status=:checking or bean.status=:checked)");
				f.append(" and bean.paymentStatus=:payment");
				f.setParam("checking", CHECKING);
				f.setParam("checked", CHECKED);
				f.setParam("payment", CHECKING);
			}else if(status==6){
				f.append(" and ((bean.payment.type=1 and bean.paymentStatus=:payment)or(bean.payment.type=2))");
				f.append(" and bean.status=:checked");
				f.append(" and bean.shippingStatus=:shipping");
				f.setParam("checked", CHECKED);
				f.setParam("shipping", CHECKING);
				f.setParam("payment", CHECKING);
			}else{
				f.append(" and bean.status=:status");
				f.setParam("status", status);
			}
		}
		if (paymentStatus !=null) {
			f.append(" and bean.paymentStatus=:paymentStatus");
			f.setParam("paymentStatus", paymentStatus);
		}
		if (shippingStatus !=null) {
			f.append(" and bean.shippingStatus=:shippingStatus");
			f.setParam("shippingStatus", shippingStatus);
		}
		if (code !=null) {
			f.append(" and bean.code=:code");
			f.setParam("code",code);
		}
		f.append(" order by bean.id desc");
		return find(f, pageNo, pageSize);
	}
	public List getList(Integer ordeRType,Long webId,Long memberId,String productName, String userName,
			Long paymentId, Long shippingId, Date startTime,Date endTime,
			Double startOrderTotal,Double endOrderTotal,Integer status,Integer paymentStatus,Integer shippingStatus,Long code
			) {
		Finder f = Finder.create("from Order bean where 1=1 ");	
		if(webId!=null){
			f.append(" and bean.website.id=:webId");
			f.setParam("webId", webId);
		}
		if(memberId!=null){
			f.append(" and bean.member.id=:memberId");
			f.setParam("memberId", memberId);
		}
		if (!StringUtils.isBlank(userName)) {
			f.append(" and bean.receiveName like:userName");
			f.setParam("userName", "%"+userName+"%");
		}
		if (!StringUtils.isBlank(productName)) {
			f.append(" and bean.productName like:productName");
			f.setParam("productName", "%"+productName+"%");
		}
		if(paymentId!=null){
			f.append(" and bean.payment.id=:paymentId");
			f.setParam("paymentId", paymentId);
		}
		if(ordeRType!=null){
			f.append(" and bean.ordeRType=:ordeRType");
			f.setParam("ordeRType", ordeRType);
		}
		if(shippingId!=null){
			f.append(" and bean.shipping.id=:shippingId");
			f.setParam("shippingId", shippingId);
		}
		if(startTime!=null){
			f.append(" and bean.createTime>:startTime");
			f.setParam("startTime", startTime);
		}
		if(endTime!=null){
			f.append(" and bean.createTime<:endTime");
			f.setParam("endTime", endTime);
		}
		if(startOrderTotal!=null){
			f.append(" and bean.total>=:startOrderTotal");
			f.setParam("startOrderTotal", startOrderTotal);
		}
		if(endOrderTotal!=null){
			f.append(" and bean.total<=:endOrderTotal");
			f.setParam("endOrderTotal", endOrderTotal);
		}
		if (status !=null ) {
			if(status == 5){
				f.append(" and (bean.status=:checking or bean.status=:checked)");
				f.append(" and bean.paymentStatus=:payment");
				f.setParam("checking", CHECKING);
				f.setParam("checked", CHECKED);
				f.setParam("payment", CHECKING);
			}else if(status==6){
				f.append(" and ((bean.payment.type=1 and bean.paymentStatus=:payment)or(bean.payment.type=2))");
				f.append(" and bean.status=:checked");
				f.append(" and bean.shippingStatus=:shipping");
				f.setParam("checked", CHECKED);
				f.setParam("shipping", CHECKING);
				f.setParam("payment", CHECKING);
			}else{
				f.append(" and bean.status=:status");
				f.setParam("status", status);
			}
		}
		if (paymentStatus !=null) {
			f.append(" and bean.paymentStatus=:paymentStatus");
			f.setParam("paymentStatus", paymentStatus);
		}
		if (shippingStatus !=null) {
			f.append(" and bean.shippingStatus=:shippingStatus");
			f.setParam("shippingStatus", shippingStatus);
		}
		if (code !=null) {
			f.append(" and bean.code=:code");
			f.setParam("code",code);
		}
		f.append(" order by bean.id desc");
		return find(f);
	}
	
	public Pagination getPage(Long webId,Long memberId,String productName, String userName,
			Long paymentId, Long shippingId, Date startTime,Date endTime,
			Double startOrderTotal,Double endOrderTotal,Integer status,Long code,
			int pageNo, int pageSize) {
		Finder f = Finder.create("from Order bean where 1=1 ");	
		if(webId!=null){
			f.append(" and bean.website.id=:webId");
			f.setParam("webId", webId);
		}
		if(memberId!=null){
			f.append(" and bean.member.id=:memberId");
			f.setParam("memberId", memberId);
		}
		if (!StringUtils.isBlank(userName)) {
			f.append(" and bean.receiveName like:userName");
			f.setParam("userName", "%"+userName+"%");
		}
		if (!StringUtils.isBlank(productName)) {
			f.append(" and bean.productName like:productName");
			f.setParam("productName", "%"+productName+"%");
		}
		if(paymentId!=null){
			f.append(" and bean.payment.id=:paymentId");
			f.setParam("paymentId", paymentId);
		}
		if(shippingId!=null){
			f.append(" and bean.shipping.id=:shippingId");
			f.setParam("shippingId", shippingId);
		}
		if(startTime!=null){
			f.append(" and bean.createTime>:startTime");
			f.setParam("startTime", startTime);
		}
		if(endTime!=null){
			f.append(" and bean.createTime<:endTime");
			f.setParam("endTime", endTime);
		}
		if(startOrderTotal!=null){
			f.append(" and bean.total>=:startOrderTotal");
			f.setParam("startOrderTotal", startOrderTotal);
		}
		if(endOrderTotal!=null){
			f.append(" and bean.total<=:endOrderTotal");
			f.setParam("endOrderTotal", endOrderTotal);
		}
		if (status !=null ) {
			if(status == 5){
				f.append(" and (bean.status=:checking or bean.status=:checked or bean.status=:check)");
				f.append(" and bean.payment.id<>3");
				f.append(" and bean.paymentStatus=:payment");
				f.setParam("checking", CHECKING);
				f.setParam("checked", CHECKED);
				f.setParam("payment", CHECKING);
				f.setParam("check", CHECK);
			}else if(status==6){
				f.append(" and bean.status<>3 and  bean.status<>4     and ( (bean.payment.id=3 and bean.paymentStatus=1)  or (bean.payment.id=1 and bean.paymentStatus=2 )) ");
//				f.setParam("payment", CHECKED);
//				f.setParam("check", CHECK);
			}else{
				f.append(" and bean.status=:status");
				f.setParam("status", status);
			}
			
		}
		if (code !=null) {
			f.append(" and bean.code=:code");
			f.setParam("code",code);
		}
		f.append(" order by bean.id desc");
		return find(f, pageNo, pageSize);
	}
	

	
	public List<Object> getTotlaOrder(Long webId){//后台首页统计函数（wang ze wu）
		List<Object> o=new ArrayList<Object>();
		String hql1="select count(*) from Order bean where 1=1 ";
		if(webId!=null&&webId>0){
			hql1=hql1+" and bean.website.id="+webId;
		}
		Long totalOrder=(Long)this.getSession().createQuery(hql1).uniqueResult();
		String hql2="select count(*) from Order bean where bean.status between 1 and 2";
		if(webId!=null&&webId>0){
			hql2=hql2+" and bean.website.id="+webId;
		}
		Long noCompleteOrder=(Long)this.getSession().createQuery(hql2).uniqueResult();
		Calendar c=Calendar.getInstance();
	    String month=(c.get(Calendar.MONTH)+1)+"";
	    String year=c.get(Calendar.YEAR)+"";
	    if(month.length()==1){
	    	month="0"+month;
	    }else{
	    	month=month;
	    }
	    String str=year+"-"+month;
	    String hql3="select count(*) from Order bean where bean.createTime like :time";
	    if(webId!=null&&webId>0){
			hql3=hql3+" and bean.website.id="+webId;
		}
		Long thisMontyOrder=(Long)this.getSession().createQuery(hql3).setString("time", "%"+str+"%").uniqueResult();
		SimpleDateFormat sf=new SimpleDateFormat("yyyy-MM-dd");
		String tady=sf.format(new Date());
		 String hql4="select count(*) from Order bean where bean.createTime like :tody";
		    if(webId!=null&&webId>0){
				hql4=hql4+" and bean.website.id="+webId;
			}
		Long todayOrder=(Long)this.getSession().createQuery(hql4).setString("tody", "%"+tady+"%").uniqueResult();
		String hql5="select count(*) from Product bean";
		    if(webId!=null&&webId>0){
		    	hql5="select count(*) from ProductSite bean where 1=1";
				hql5=hql5+" and bean.website.id="+webId;
			}
		Long totalProduct=(Long)this.getSession().createQuery(hql5).uniqueResult();
		String hql6="select count(*) from Product bean where bean.createTime like :time";
	    if(webId!=null&&webId>0){
	    	hql6="select count(*) from ProductSite bean where bean.createTime like :time";
			hql6=hql6+" and bean.website.id="+webId;
		}
		
		Long newProductMonth=(Long)this.getSession().createQuery(hql6).setString("time", "%"+str+"%").uniqueResult();
		String hql7="select count(*) from Product bean where bean.createTime like :time";
	    if(webId!=null&&webId>0){
	    	hql7="select count(*) from ProductSite bean where bean.createTime like :time";
			hql7=hql7+" and bean.website.id="+webId;
		}
		Long dateProduct=(Long)this.getSession().createQuery(hql7).setString("time", "%"+tady+"%").uniqueResult();
		String hql8="select count(*) from Product bean ";
	    if(webId!=null&&webId>0){
	    	hql8="select count(*) from ProductSite bean where 1=1";
			hql8=hql8+" and bean.website.id="+webId;
		}
	    
		
	    Long putawayProduct=(Long)this.getSession().createQuery(hql8).uniqueResult();
//	    Long putawayProduct=(Long)this.getSession().createQuery("select count(*) from Product bean where bean.onSale=true").uniqueResult();
	    String hql9="select count(*) from ShopMember bean";
	    if(webId!=null&&webId>0){
	    	hql9="select count(*) from ShopMember bean where 1=1";
	    	hql9=hql9+" and bean.website.id="+webId;
	    }
	    Long totalMember=(Long)this.getSession().createQuery(hql9).uniqueResult();
	    String hql10="select count(*) from Member bean where bean.createTime like :time ";
	    if(webId!=null&&webId>0){
	    	hql10=hql10+" and bean.website.id="+webId;
	    }
	    
	    Long totalMonthMember=(Long)this.getSession().createQuery(hql10).setString("time", "%"+str+"%").uniqueResult();
	    String hql11="select count(*) from Member bean where bean.createTime like :time";
	    if(webId!=null&&webId>0){
	    	hql11=hql11+" and bean.website.id="+webId;
	    }
	    Long totalDateMember=(Long)this.getSession().createQuery(hql11).setString("time", "%"+tady+"%").uniqueResult();
	    c.add(Calendar.DATE, -7);
	   Date oldDate= c.getTime();
	   
	   String hql12="select count(*) from Member bean where bean.createTime >:time";
	    if(webId!=null&&webId>0){
	    	hql12=hql12+" and bean.website.id="+webId;
	    }
	   Long date7Member=(Long)this.getSession().  createQuery(hql12).setParameter("time", oldDate).uniqueResult();

	   
	    Long[] cc=new Long[12];
	    cc[0]=totalOrder==null?0:totalOrder;
	    cc[1]=noCompleteOrder==null?0:noCompleteOrder;
	    cc[2]=thisMontyOrder==null?0:thisMontyOrder;
	    cc[3]=todayOrder==null?0:todayOrder;
	    
	    cc[4]=totalProduct==null?0:totalProduct;
	    cc[5]=newProductMonth==null?0:newProductMonth;
	    cc[6]=dateProduct==null?0:dateProduct;
	    
	    
	    cc[7]=totalMember==null?0:totalMember;
	    cc[8]=totalMonthMember==null?0:totalMonthMember;
	    cc[9]=totalDateMember==null?0:totalDateMember;
	    cc[10]=date7Member==null?0:date7Member;
	    cc[11]=putawayProduct==null?0:putawayProduct;
	    
	    o.add(cc);
	    return o;
	}
    
	public BigDecimal getMemberMoneyByYear(Long memberId){//会员今年的消费(wang ze wu)
		Calendar c=Calendar.getInstance();
		String year=""+c.get(Calendar.YEAR);
		Query q=this.getSession().createQuery("select sum((bean.salePrice)* bean.count) from OrderItem bean" +
				" where bean.ordeR.member.id=:id and bean.ordeR.createTime like:time and bean.ordeR.status=4");
		q.setParameter("id", memberId).setString("time", "%"+year+"%");
		Double v1 =(Double) q.uniqueResult();
		if(v1==null){
			v1=0.0;
		}
		return new BigDecimal(v1);
	}
	
	public Integer[] getOrderByMember(Long memberId){//会员我的订单（完成，未完成）
		Long succOrder=(Long)this.getSession().createQuery("select count(*) from Order bean where bean.member.id=:id")
		.setParameter("id", memberId).uniqueResult();
		Long failOrder=(Long)this.getSession().createQuery("select count(*) from Order bean where bean.member.id=:id")
		.setParameter("id", memberId).uniqueResult();
		
		Long totalOrder=(Long)this.getSession().createQuery("select count(*) from Order bean where bean.member.id=:id")//全部订单
		.setParameter("id", memberId).uniqueResult();
		
		Long pendIngOrder=(Long)this.getSession().createQuery("select count(*) from Order bean where bean.member.id=:id")//待付款订单
		.setParameter("id", memberId).uniqueResult();
		
		Long proceOrder=(Long)this.getSession().createQuery("select count(*) from Order bean where bean.member.id=:id")//待处理订单
		.setParameter("id", memberId).uniqueResult();
		
		Integer[] orders = new Integer[5];
		orders[0] = succOrder.intValue();
		orders[1] = failOrder.intValue();//退回订单
		orders[2] = totalOrder.intValue();
		orders[3] = pendIngOrder.intValue();
		orders[4] = proceOrder.intValue();
		return orders;
	}
	
	public Integer[] getOrderByMemberAndSiteId(Long memberId,Long siteId){//会员我的订单（完成，未完成）
		Long succOrder=(Long)this.getSession().createQuery("select count(*) from Order bean where bean.member.id=:id and bean.website.id=:siteId")
				.setParameter("id", memberId).setParameter("siteId", siteId).uniqueResult();
		Long failOrder=(Long)this.getSession().createQuery("select count(*) from Order bean where bean.member.id=:id and bean.website.id=:siteId")
				.setParameter("id", memberId).setParameter("siteId", siteId).uniqueResult();
		
		Long totalOrder=(Long)this.getSession().createQuery("select count(*) from Order bean where bean.member.id=:id and bean.website.id=:siteId")//全部订单
				.setParameter("id", memberId).setParameter("siteId", siteId).uniqueResult();
		
		Long pendIngOrder=(Long)this.getSession().createQuery("select count(*) from Order bean where bean.member.id=:id and bean.website.id=:siteId")//待付款订单
				.setParameter("id", memberId).setParameter("siteId", siteId).uniqueResult();
		
		Long proceOrder=(Long)this.getSession().createQuery("select count(*) from Order bean where bean.member.id=:id and bean.website.id=:siteId")//待处理订单
				.setParameter("id", memberId).setParameter("siteId", siteId).uniqueResult();
		
		Integer[] orders = new Integer[5];
		orders[0] = succOrder.intValue();
		orders[1] = failOrder.intValue();//退回订单
		orders[2] = totalOrder.intValue();
		orders[3] = pendIngOrder.intValue();
		orders[4] = proceOrder.intValue();
		return orders;
	}
	
	public Pagination getOrderByReturn(Long memberId,Integer pageNo,Integer pageSize){//会员中心，退回订单
		Finder f = Finder.create("from Order bean where bean.member.id=:id and bean.status=41");
		f.setParam("id", memberId);
		return this.find(f, pageNo, pageSize);
	}
	
	public Order findById(Long id) {
		Order entity = get(id);
		return entity;
	}
	public Order findByCode(Long code){
		return this.findUniqueByProperty("code", code);
	}
	public Order findByTradeNo(String tradeNo){
		return this.findUniqueByProperty("tradeNo", tradeNo);
	}

	public Order save(Order bean) {
		getSession().save(bean);
		return bean;
	}

	public Order deleteById(Long id) {
		Order entity = super.get(id);
		if (entity != null) {
			getSession().delete(entity);
		}
		return entity;
	}

	@Override
	protected Class<Order> getEntityClass() {
		return Order.class;
	}
	
	/**
	 * 未确认
	 */
	public static final  Integer CHECKING = 1;
	/**
	 * 已确认
	 */
	public static final Integer CHECKED = 2;
	/**
	 *未分单
	 */
	public static final Integer CHECK = 0;
	

}