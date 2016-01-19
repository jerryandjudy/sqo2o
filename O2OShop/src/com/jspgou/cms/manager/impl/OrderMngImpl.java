package com.jspgou.cms.manager.impl;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Set;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jspgou.cms.action.member.SendMsg_webchinese;
import com.jspgou.cms.dao.OrderDao;
import com.jspgou.cms.dao.OrderItemDao;
import com.jspgou.cms.entity.Bld;
import com.jspgou.cms.entity.Cart;
import com.jspgou.cms.entity.CartItem;
import com.jspgou.cms.entity.Gys;
import com.jspgou.cms.entity.JxcBldGoodsNum;
import com.jspgou.cms.entity.Jysp;
import com.jspgou.cms.entity.Lsfc;
import com.jspgou.cms.entity.MemberCoupon;
import com.jspgou.cms.entity.Order;
import com.jspgou.cms.entity.OrderItem;
import com.jspgou.cms.entity.Payment;
import com.jspgou.cms.entity.PopularityItem;
import com.jspgou.cms.entity.Product;
import com.jspgou.cms.entity.ProductFashion;
import com.jspgou.cms.entity.ProductSite;
import com.jspgou.cms.entity.Shipping;
import com.jspgou.cms.entity.ShopMember;
import com.jspgou.cms.entity.ShopMemberAddress;
import com.jspgou.cms.entity.ShopScore;
import com.jspgou.cms.entity.ShopScore.ScoreTypes;
import com.jspgou.cms.entity.base.BaseOrder;
import com.jspgou.cms.manager.BldMng;
import com.jspgou.cms.manager.CartItemMng;
import com.jspgou.cms.manager.CartMng;
import com.jspgou.cms.manager.GatheringMng;
import com.jspgou.cms.manager.GysMng;
import com.jspgou.cms.manager.JxcBldGoodsNumMng;
import com.jspgou.cms.manager.JyspMng;
import com.jspgou.cms.manager.LsfcMng;
import com.jspgou.cms.manager.MemberCouponMng;
import com.jspgou.cms.manager.OrderMng;
import com.jspgou.cms.manager.OrderReturnMng;
import com.jspgou.cms.manager.PaymentMng;
import com.jspgou.cms.manager.PopularityItemMng;
import com.jspgou.cms.manager.ProductFashionMng;
import com.jspgou.cms.manager.ProductMng;
import com.jspgou.cms.manager.ProductSiteMng;
import com.jspgou.cms.manager.ShipmentsMng;
import com.jspgou.cms.manager.ShippingMng;
import com.jspgou.cms.manager.ShopMemberAddressMng;
import com.jspgou.cms.manager.ShopMemberMng;
import com.jspgou.cms.manager.ShopScoreMng;
import com.jspgou.common.hibernate3.Updater;
import com.jspgou.common.page.Pagination;
import com.jspgou.common.util.ExcelExportUtil;
import com.jspgou.core.entity.Account;
import com.jspgou.core.entity.AccountItem;
import com.jspgou.core.entity.Website;
import com.jspgou.core.manager.AccountItemMng;
import com.jspgou.core.manager.AccountMng;
import com.jspgou.core.manager.WebsiteMng;

/**
 * This class should preserve.
 * 
 * @preserve
 */
@Service
@Transactional
public class OrderMngImpl implements OrderMng {

	public Pagination getPageForOrderReturn(Long memberId, int pageNo,
			int pageSize) {
		return orderDao.getPageForOrderReturn(memberId, pageNo, pageSize);
	}
	/**
	 * 导出excel
	 */
	@Override
	public InputStream exportExcel(Integer ordeRType,Long webId,Long memberId,String productName,String userName,Long paymentId,Long shippingId, 
			Date startTime,Date endTime,Double startOrderTotal,Double endOrderTotal,Integer status,Integer paymentStatus,Integer shippingStatus,Long code)  throws  Exception{
		//获取数据
		List<Order> listOrder = orderDao.getList(ordeRType, webId, memberId, productName, userName, paymentId, shippingId, startTime, endTime, startOrderTotal, endOrderTotal, status, paymentStatus, shippingStatus, code);
		File file = new File("D://"+new Date().getTime()+".xls");
		OutputStream outputStream = new FileOutputStream(file);
		ExcelExportUtil.exportExcel("订单信息导出", BaseOrder.class, listOrder, outputStream);
		InputStream is = new BufferedInputStream(new FileInputStream(file.getPath()));
		file.delete();		
		return is;
	}	
	
    /** 
     * 导入excel
     *  
     * @param fileName 文件名称
     * @param excelFile 文件
     * @return 
     */  	
	public void importExcel(String fileName,File excelFile){
//		List<Order> listUsers = (List<Order>)ExcelUtil.importExcel(excelFile, Order.class);
//		for(Order user : listUsers){
//			user.setCid(UUID.randomUUID().toString());
//			userDao.saveOrUpdate(user);
//		}
	}	
	

	// 生成订单
	public Order createOrder(Cart cart, Long[] cartItemId,
			Long shippingMethodId, Long deliveryInfo, Long paymentMethodId,
			String comments, String ip, ShopMember member, Long webId,
			String memberCouponId) {
		Website web = websiteMng.findById(webId);
		Long mcId = null;
		if (!StringUtils.isBlank(memberCouponId)) {
			mcId = Long.parseLong(memberCouponId);
		}
		Payment pay = paymentMng.findById(paymentMethodId);

		Shipping shipping = shippingMng.findById(shippingMethodId);
		ShopMemberAddress address = shopMemberAddressMng.findById(deliveryInfo);

		Order order = new Order();
		order.setShipping(shipping);
		order.setWebsite(web);
		Timestamp d = new Timestamp(System.currentTimeMillis()); 
		order.setLastModified(d);
		order.setMember(member);
		order.setPayment(pay);
		order.setIp(ip);
		order.setComments(comments);

		order.setOrdeRType(1);
		order.setShippingStatus(1);// 未发货
		order.setPaymentStatus(1);// 未支付	
		order.setReceiveName(address.getUsername());
		order.setReceiveAddress(getAddress(address));
		order.setReceiveMobile(address.getTel());
		order.setReceivePhone(address.getMobile());
		order.setReceiveZip(address.getPostCode());
		int score = 0;
		int weight = 0;
		double price = 0.00;
		List<CartItem> itemList = new ArrayList<CartItem>();
		for (Long cId : cartItemId) {
			itemList.add(cartItemMng.findById(cId));
		}
		for (CartItem item : itemList) {
			score = score + item.getProduct().getScore() * item.getCount();
			weight = weight + item.getProduct().getWeight() * item.getCount();
			if (item.getProductFash() != null) {
				price += item.getProductFash().getSalePrice() * item.getCount();
			} else {
				price += item.getProductSite().getSalePrice() * item.getCount();
			}
		}
		if (member.getFreezeScore() != null) {
			member.setFreezeScore(score + member.getFreezeScore());// 会员冻结的积分
		} else {
			member.setFreezeScore(score + 0);// 会员冻结的积分
		}
		Bld bld = this.getBldBySiteAndPsqy(price, pay, cart, cartItemId, web
				.getId(), address.getProvince().getId(), address.getCity()
				.getId(), address.getCountry().getId(), address.getStreet()
				.getId());

		order.setBld(bld);
		if (bld == null) {
			order.setStatus(0);// 无符合条件的便利店，进行系统分单
			order.setSysComment("系统首次分单无符合条件便利店");
//			return null;
		} else {
			order.setStatus(1);// 未确认
		}
		Double couponPrice = 0.00;
		Double popularityPrice = 0.0;
		if (cart != null) {
			List<PopularityItem> popularityItems = popularityItemMng.getlist(
					cart.getId(), null);
			for (PopularityItem popularityItem : popularityItems) {
				popularityPrice += popularityItem.getPopularityGroup()
						.getPrivilege() * popularityItem.getCount();
			}
		}
		if (mcId != null) {
			MemberCoupon memberCoupon = memberCouponMng.findById(mcId);
			if (memberCoupon != null) {
				if (memberCoupon.getCoupon().getIsusing()
						&& (!memberCoupon.getIsuse())) {
					couponPrice = memberCoupon.getCoupon().getCouponPrice()
							.doubleValue();
					memberCoupon.setIsuse(true);
					memberCouponMng.update(memberCoupon);
				}
			}
		}

		order.setScore(score);
		order.setWeight((double) weight);
		order.setProductPrice(price);
		double freight = shipping.calPrice((double) weight);
		order.setFreight(freight);
		order.setTotal(freight + price - couponPrice - popularityPrice);
		SimpleDateFormat df = new SimpleDateFormat("yyMMdd");
		String code=df.format(new Date())+(new Date().getTime() + member.getId());
		Long date = Long.parseLong(code);
		order.setCode(date);

		CartItem cartItem = null;
		OrderItem orderItem = null;
		String productName = "";
		for (int j = 0; j < itemList.size(); j++) {
			orderItem = new OrderItem();
			cartItem = (CartItem) itemList.get(j);
			orderItem.setOrdeR(order);
			orderItem.setProductSite(cartItem.getProductSite());
			orderItem.setWebsite(web);
			orderItem.setCount(cartItem.getCount());
			if (cartItem.getProductFash() != null) {
				orderItem.setProductFash(cartItem.getProductFash());
				orderItem
						.setSalePrice(cartItem.getProductFash().getSalePrice());
			} else {
				orderItem
						.setSalePrice(cartItem.getProductSite().getSalePrice());
				orderItem.setCostPrice(cartItem.getProductSite().getMarketPrice());
			}
			productName += orderItem.getProductSite().getProduct().getName();
			order.addToItems(orderItem);
		}
		order.setProductName(productName);
		List<PopularityItem> popularityItemList = popularityItemMng.getlist(
				cart.getId(), null);
		for (PopularityItem popularityItem : popularityItemList) {
			popularityItemMng.deleteById(popularityItem.getId());
		}
		cart.getItems().removeAll(itemList);
		cart.setTotalItems(0);
		cartMng.update(cart);
		Double lscc=0d;
//		if (order.getPayment().getType() == 2) {// 有便利店接单且支付方式为货到付款冻结便利店账户金额
			Lsfc lsfc = lsfcMng.findByFctypeId(36l, order.getWebsite().getId());// 流水分成便利店
			if (lsfc != null && lsfc.getId() > 0) {
				lscc=lsfc.getBl()* order.getTotal();
				lscc=Math.round(lscc*100)/100.0;
				order.setLsccMoney(lscc);
			}      
//		}
		order.setLsccMoney(lscc);
		order = save(order);
		if(order.getPayment().getType()==1){ //测试网上支付时默认已经支付
//			order.setPaymentStatus(2);// 已支付
//			order=this.updateByUpdater(order);
			}else if(order.getBld()!=null && order.getBld().getId().length()>0){
				SendMsg_webchinese.sendMsg(order.getBld().getPhone(),"您有新订单，订单号："+order.getCode()+",请尽快处理。否则超时系统将自动收回订单");
			}
//		if(order.getPayment().getType()==1){ //测试网上支付时默认已经支付
//			order.setPaymentStatus(2);// 已支付
//			Account account = accountMng.findByStatus(1);
//			AccountItem accountItem=new AccountItem();
//			accountItem.setAccount(account);
//			accountItem.setMoney(order.getTotal());
//			accountItem.setMoneyTime(new Date());
//			accountItem.setMoneyType(1);
//			accountItem.setName("平台收取在线支付购买便利店商品");
//			accountItem.setOrder(order);
//			accountItem.setRemark("平台收取在线支付购买便利店商品");
//			accountItem.setStatus(true);
//			accountItem.setUseStatus(false);
//			accountItemMng.save(accountItem);
//		}
//		Account account = djMoney(order);// 冻结便利店流水分成金额,并生成收支明细记录
//		List<AccountItem> accountItems=createSelf(order) ;  //生成收支明细记录
		ShopScore shopScore = null;
		Product product = null;
		ProductFashion fashion = null;
		if (bld != null) {
			for (OrderItem item : order.getItems()) {
				// 处理库存
//				JxcBldGoodsNum jxcBldGoodsNum = jxcBldGoodsNumMng
//						.findByProductIdAndKetaUserId(item.getProductSite()
//								.getProduct().getId(), bld.getKetaUser()
//								.getId());
//				jxcBldGoodsNum.setGoodsNum(jxcBldGoodsNum.getGoodsNum()
//						- item.getCount());
//				jxcBldGoodsNumMng.update(jxcBldGoodsNum);
				// product=item.getProduct();
				// if(item.getProductFash()!=null){
				// fashion=item.getProductFash();
				// fashion.setStockCount(fashion.getStockCount()-item.getCount());
				// product.setStockCount(product.getStockCount()-item.getCount());
				// productFashionMng.update(fashion);
				// }else{
				// product.setStockCount(product.getStockCount()-item.getCount());
				// }
				// productMng.updateByUpdater(product);
				shopScore = new ShopScore();
				shopScore.setMember(member);
				shopScore.setName(cartItem.getProduct().getName());
				shopScore.setScoreTime(new Date());
				shopScore.setStatus(false);
				shopScore.setUseStatus(false);
				shopScore.setScoreType(ScoreTypes.ORDER_SCORE.getValue());
				shopScore.setScore(item.getProductSite().getProduct()
						.getScore());
				shopScore.setCode(Long.toString(order.getCode()));
				shopScoreMng.save(shopScore);
			}
		}
		return order;
	}
	/* 
     *拆分订单
	 * @see com.jspgou.cms.manager.OrderMng#cfdd(com.jspgou.cms.entity.Order)
	 */
	public List<OrderItem> cfdd(com.jspgou.cms.entity.Order order) {
		List<OrderItem> list=new ArrayList();
		//原订单取消
		order.setStatus(3);
		order.setSysComment("该订单因有商品下架已被拆分！！");
		Timestamp timestamp = new Timestamp(System.currentTimeMillis());
		order.setLastModified(timestamp);
		order.setFinishedTime(timestamp);
		Updater updater=new Updater<Order>(order);
		orderDao.updateByUpdater(updater);
		
	
		
		
		if(order.getOrdeRType()==1  ){//商城订单
			
			List<OrderItem> lists=orderItemDao.getOrderItemByOrderId(order.getId());
			for(OrderItem orderItem:lists){
				Long mcId = null;
                Bld bld=bldMng.getBldByOrderAndOrderItem(order, orderItem);
				Order ordernew = new Order();
				if(bld==null){
					bld=bldMng.findByIsDef(true, order.getWebsite().getId());
					if(bld==null){
					ordernew.setStatus(3);
					
					list.add(orderItem);
					ordernew.setSysComment("该商品已下架，无法出货，已取消订单！！");
					}else{
						ordernew.setStatus(1);
						ordernew.setSysComment("系统自动拆分订单,原订单号："+order.getCode());
						ordernew.setBld(bld);
						ordernew.setParent(order);
					}
				}else{
					ordernew.setStatus(1);
					ordernew.setSysComment("系统自动拆分订单,原订单号："+order.getCode());
					ordernew.setBld(bld);
					ordernew.setParent(order);
				}
				ordernew.setCreateTime(timestamp);
				ordernew.setShipping(order.getShipping());
				ordernew.setWebsite(order.getWebsite());
				ordernew.setMember(order.getMember());
				ordernew.setPayment(order.getPayment());
				ordernew.setIp(order.getIp());
				ordernew.setComments(order.getComments());
				ordernew.setOrdeRType(order.getOrdeRType());
				ordernew.setShippingStatus(order.getShippingStatus());//发货状态
				ordernew.setPaymentStatus(order.getPaymentStatus());// 支付状态
				ordernew.setReceiveName(order.getReceiveName());
				ordernew.setReceiveAddress(order.getReceiveAddress());
				ordernew.setReceiveMobile(order.getReceiveMobile());
				ordernew.setReceivePhone(order.getReceivePhone());
				ordernew.setReceiveZip(order.getReceiveZip());
				int score = 0;
				int weight = 0;
				double price = 0.00;
				score = score + orderItem.getProductSite().getProduct().getScore() * orderItem.getCount();
				weight = weight + orderItem.getProductSite().getProduct().getWeight() * orderItem.getCount();
				if (orderItem.getProductFash() != null) {
					price += orderItem.getProductFash().getSalePrice() * orderItem.getCount();
				} else {
					price += orderItem.getProductSite().getSalePrice() * orderItem.getCount();
				}
				ShopMember member = order.getMember();
				if (member.getFreezeScore() != null) {
					member.setFreezeScore(score + member.getFreezeScore());// 会员冻结的积分
				} else {
					member.setFreezeScore(score + 0);// 会员冻结的积分
				}
				
				ordernew.setScore(score);
				ordernew.setWeight((double) weight);
				ordernew.setProductPrice(price);
				double freight = order.getShipping().calPrice((double) weight);
				ordernew.setFreight(freight);
				Double couponPrice = 0.00;
				Double popularityPrice = 0.0;
				ordernew.setTotal(freight + price - couponPrice - popularityPrice);
				SimpleDateFormat df = new SimpleDateFormat("yyMMdd");
				String code=df.format(new Date())+(new Date().getTime() + member.getId());
				Long date = Long.parseLong(code);
				ordernew.setCode(date);
				OrderItem orderItemNew= new OrderItem();
				BeanUtils.copyProperties(orderItem, orderItemNew);
				orderItemNew.setId(null);
				orderItemNew.setOrdeR(ordernew);
				ordernew.addToItems(orderItemNew);
				ordernew.setProductName(orderItemNew.getProductSite().getProduct().getName());
				Double lscc=0d;
//				if (ordernew.getPayment().getType() == 2) {// 有便利店接单且支付方式为货到付款冻结便利店账户金额
					Lsfc lsfc = lsfcMng.findByFctypeId(36l, ordernew.getWebsite().getId());// 流水分成便利店
					if (lsfc != null && lsfc.getId() > 0) {
						lscc=lsfc.getBl()* ordernew.getProductPrice();
						lscc=Math.round(lscc*100)/100.0;
						ordernew.setLsccMoney(lscc);
					}
//				}
				ordernew.setLsccMoney(lscc);
				ordernew = save(ordernew);
				if(ordernew.getBld()!=null && ordernew.getBld().getId().length()>0){
					if(ordernew.getPayment().getId()==3||(ordernew.getPayment().getId()==1&&ordernew.getPaymentStatus()==2)){
					SendMsg_webchinese.sendMsg(ordernew.getBld().getPhone(),"您有新订单，订单号："+ordernew.getCode()+",请尽快处理。否则超时系统将自动收回订单");
					}
					}
//				Account account = djMoney(ordernew);// 冻结便利店流水分成金额,并生成收支明细记录
				ShopScore shopScore = null;
				Product product = null;
				ProductFashion fashion = null;
//				if (ordernew.getBld() != null) {
//					for (OrderItem item : ordernew.getItems()) {
//						// 处理库存
//						JxcBldGoodsNum jxcBldGoodsNum = jxcBldGoodsNumMng
//								.findByProductIdAndKetaUserId(item.getProductSite()
//										.getProduct().getId(), ordernew.getBld().getKetaUser()
//										.getId());
//						jxcBldGoodsNum.setGoodsNum(jxcBldGoodsNum.getGoodsNum()
//								- item.getCount());
//						jxcBldGoodsNumMng.update(jxcBldGoodsNum);
//						shopScore = new ShopScore();
//						shopScore.setMember(member);
//						shopScore.setName(ordernew.getProductName());
//						shopScore.setScoreTime(new Date());
//						shopScore.setStatus(false);
//						shopScore.setUseStatus(false);
//						shopScore.setScoreType(ScoreTypes.ORDER_SCORE.getValue());
//						shopScore.setScore(item.getProductSite().getProduct()
//								.getScore());
//						shopScore.setCode(Long.toString(ordernew.getCode()));
//						shopScoreMng.save(shopScore);
//					}
//				}
			}
		}
		if(order.getOrdeRType()==2  ){//进销存订单
			
			if (order.getGys() != null) {
				for (OrderItem item : order.getItems()) {
							//恢复供应商库存
							Jysp jysp = jyspMng
									.findByProductIdAndKetaUserId(item.getProductSite()
											.getProduct().getId(), order.getGys().getKetaUser()
											.getId());
							jysp.setGoodsNum(jysp.getGoodsNum()
									+ item.getCount());
							jyspMng.update(jysp);
				}
				//恢复供应商冻结流水抽成
				Account account = null;
				if ( order.getPayment().getType() == 2) {// 有便利店接单且支付方式为货到付款冻结便利店账户金额
					account = order.getGys().getAccount();
						account.setFrozenMoney(account.getFrozenMoney() -order.getLsccMoney());// 获取便利店流水分成比例金额
						account.setMoney(account.getMoney()+order.getLsccMoney());
						Updater<Account> updaters = new Updater<Account>(account);
						accountMng.updateByUpdater(updaters); // 更新冻结金额字段
						//this.createSelf(order,order.getLsccMoney());//生成收支明细记录
				}
			}
			
			List<OrderItem> lists=orderItemDao.getOrderItemByOrderId(order.getId());
			for(OrderItem orderItem:lists){
				Long mcId = null;
				Gys gys=gysMng.getGysByOrderAndOrderItem(order, orderItem);
				Order ordernew = new Order();
				if(gys==null){
					ordernew.setStatus(3);
					list.add(orderItem);
					ordernew.setSysComment("该商品已下架，无法出货，已取消订单！！");
				}else{
					ordernew.setStatus(1);
					ordernew.setSysComment("系统自动拆分订单,原订单号："+order.getCode());
					ordernew.setGys(gys);
				}
				ordernew.setCreateTime(timestamp);
				ordernew.setShipping(order.getShipping());
				ordernew.setWebsite(order.getWebsite());
				ordernew.setMember(order.getMember());
				ordernew.setPayment(order.getPayment());
				ordernew.setIp(order.getIp());
				ordernew.setComments(order.getComments());
				ordernew.setOrdeRType(order.getOrdeRType());
				ordernew.setShippingStatus(order.getShippingStatus());// 未发货
				ordernew.setPaymentStatus(order.getPaymentStatus());// 未支付
				ordernew.setReceiveName(order.getReceiveName());
				ordernew.setReceiveAddress(order.getReceiveAddress());
				ordernew.setReceiveMobile(order.getReceiveMobile());
				ordernew.setReceivePhone(order.getReceivePhone());
				ordernew.setReceiveZip(order.getReceiveZip());
				int score = 0;
				int weight = 0;
				double price = 0.00;
				score = score + orderItem.getProductSite().getProduct().getScore() * orderItem.getCount();
				weight = weight + orderItem.getProductSite().getProduct().getWeight() * orderItem.getCount();
				if (orderItem.getProductFash() != null) {
					price += orderItem.getProductFash().getSalePrice() * orderItem.getCount();
				} else {
					price += orderItem.getProductSite().getSalePrice() * orderItem.getCount();
				}
				
				ordernew.setScore(score);
				ordernew.setWeight((double) weight);
				ordernew.setProductPrice(price);
				double freight = order.getShipping().calPrice((double) weight);
				ordernew.setFreight(freight);
				Double couponPrice = 0.00;
				Double popularityPrice = 0.0;
				order.setTotal(freight + price - couponPrice - popularityPrice);
				Long date = new Date().getTime() + ordernew.getBld().getKetaUser().getId();
				ordernew.setCode(date);
				OrderItem orderItemNew= new OrderItem();
				BeanUtils.copyProperties(orderItem, orderItemNew);
				orderItemNew.setId(null);
				orderItemNew.setOrdeR(ordernew);
				ordernew.addToItems(orderItemNew);
				ordernew.setProductName(orderItemNew.getProductSite().getProduct().getName());
				Double lscc=0d;
				if (ordernew.getPayment().getType() == 2) {// 有供应商接单且支付方式为货到付款冻结供应商账户金额
					Lsfc lsfc = lsfcMng.findByFctypeId(37l, order.getWebsite().getId());// 流水分成供应商
					if (lsfc != null && lsfc.getId() > 0) {
						lscc=lsfc.getBl()* order.getProductPrice();
						lscc=Math.round(lscc*100)/100.0;
						ordernew.setLsccMoney(lscc);
					}
				}
				ordernew.setLsccMoney(lscc);
				ordernew = save(ordernew);
				Account account = djMoney(ordernew);// 冻结供应商流水分成金额,并生成收支明细记录
				ShopScore shopScore = null;
				Product product = null;
				ProductFashion fashion = null;
				if (ordernew.getGys() != null) {
					for (OrderItem item : ordernew.getItems()) {
						// 处理库存
						Jysp jysp = jyspMng
								.findByProductIdAndKetaUserId(item.getProductSite()
										.getProduct().getId(), ordernew.getGys().getKetaUser()
										.getId());
						jysp.setGoodsNum(jysp.getGoodsNum()
								- item.getCount());
						jyspMng.update(jysp);
//						shopScore = new ShopScore();
//						shopScore.setMember(member);
//						shopScore.setName(order.getProductName());
//						shopScore.setScoreTime(new Date());
//						shopScore.setStatus(false);
//						shopScore.setUseStatus(false);
//						shopScore.setScoreType(ScoreTypes.ORDER_SCORE.getValue());
//						shopScore.setScore(item.getProductSite().getProduct()
//								.getScore());
//						shopScore.setCode(Long.toString(order.getCode()));
//						shopScoreMng.save(shopScore);
					}
				}
			}
		}
		return list;
	}
    /**
     * 创建订单收支明细
     * @param order
     * @return
     */
    public List<AccountItem> createSelf(Order order,Double lscc){
    	List  accountItems=new ArrayList();
    	if (order.getStatus() == 1 && order.getPayment().getType() == 2) {
    		Account account = order.getBld().getAccount();
			Lsfc lsfc = null;
			String name="";
			String remark="";
			if(order.getOrdeRType()==1){
				lsfc=lsfcMng.findByFctypeId(36l, order.getWebsite().getId());// 流水分成便利店
				name="货到付款购买便利店商品";
				remark="货到付款购买”"+order.getBld().getCompanyName()+"“商品，便利店预付流水分成";
			}else if(order.getOrdeRType()==2){
				lsfc=lsfcMng.findByFctypeId(37l, order.getWebsite().getId());// 流水分成供应商
				name="货到付款购买供应商商品";
				remark="货到付款购买”"+order.getGys().getCompanyName()+"“商品，供应商预付流水分成";
			}
			if (lsfc != null && lsfc.getId() > 0) {
				AccountItem accountItem=new AccountItem();
				accountItem.setOrder(order);
				accountItem.setMoney(lscc);
				accountItem.setMoneyTime(new Date());
				accountItem.setMoneyType(1);
				accountItem.setName(name);
				accountItem.setRemark(remark);
				accountItem.setStatus(false);
				accountItem.setUseStatus(true);
				accountItem.setAccount(account);
				accountItemMng.save(accountItem); // 保存记录
				accountItems.add(accountItem);
			}
    	}
    	return accountItems;
    }
	/**
	 * 更新冻结金额
	 * 
	 * @param order
	 * @return
	 */
	public Account djMoney(Order order) {
		Account account = null;
		if (order.getStatus() == 1 && order.getPayment().getType() == 2) {// 有接单且支付方式为货到付款冻结卖家店账户金额
			    if(order.getOrdeRType()==1){
			    account = order.getBld().getAccount();
			    }else if(order.getOrdeRType()==2){
			    account = order.getGys().getAccount();
			    }
				account.setFrozenMoney(account.getFrozenMoney() + order.getLsccMoney());// 获取便利店流水分成比例金额
				account.setMoney(account.getMoney()-order.getLsccMoney());
				Updater<Account> updater = new Updater<Account>(account);
				accountMng.updateByUpdater(updater); // 更新冻结金额字段
				this.createSelf(order,order.getLsccMoney());//生成收支明细记录
		}
		return account;
	}

	/**
	 * 根据站点和配送区域以，商品库存量足够，如果是货到付款可用余额足够， 以随机的方式获得Bld
	 * 
	 * @param siteId
	 * @param province
	 * @param city
	 * @param country
	 * @param street
	 * @return
	 */
	public Bld getBldBySiteAndPsqy(Double price, Payment pay, Cart cart,
			Long[] cartItemIds, Long siteId, Long province, Long city,
			Long country, Long street) {
		Bld bld = null;
		List<Bld> list = bldMng.getBldBySiteAndPsqy(siteId, province, city,
				country, street);
		if (list != null && list.size() > 0) {
			
			//bld = this.getOneVidationBld(price, pay, cart, list, cartItemIds);
			int counts = list.size();
			int resIndex = (int) (Math.random() * counts);
			bld = list.get(resIndex);
		}
		return bld;
	}

	public Bld getOneVidationBld(Double price, Payment pay, Cart cart,
			List<Bld> list, Long[] cartItemIds) {
		Bld bld = null;
		Boolean flag = true;
		while (list != null && list.size() > 0 && flag) {
			int counts = list.size();
			if (counts == 1) {
				bld = list.get(0);
				Double money = bld.getAccount().getMoney();

				Long ketaUserId = bld.getKetaUser().getId();
				for (Long cartItemId : cartItemIds) {
					CartItem cartItem = cartItemMng.findById(cartItemId);
					Long productId=cartItem.getProduct()
							.getId();
					Integer proCount=cartItem.getCount();
					Boolean isdp=true;
					if(cartItem.getProduct().getParent()!=null && cartItem.getProduct().getParent().getId()!=null && cartItem.getProduct().getParent().getId()>0){
						productId=cartItem.getProduct().getParent().getId();
						isdp=false;
						proCount=cartItem.getCount()*cartItem.getProduct().getParentCount();
					}
					
					JxcBldGoodsNum jxcBldGoodsNum = jxcBldGoodsNumMng
							.findByProductIdAndKetaUserId(productId, ketaUserId);
					if (pay.getType() == 2) {// 货到付款判断便利店账户余额该订单足不足够平台流水分成
						Lsfc lsfc = lsfcMng.findByFctypeId(36l, cart
								.getWebsite().getId());// 流水分成便利店
						if (lsfc != null && lsfc.getId() > 0) {
							if (money < price * lsfc.getBl()) {
								flag = false;
								bld = null;
								break;
							}
						}
					}
					if (jxcBldGoodsNum == null
							||  proCount> jxcBldGoodsNum
									.getGoodsNum()) {
						flag = false;
						bld = null;
						break;
					}

				}
				list.clear();
				

			} else {
				counts = counts - 1;
				int resIndex = (int) (Math.random() * counts);
				bld = list.get(resIndex);
				bld = bldMng.findById(bld.getId());
				Double money = bld.getAccount().getMoney();
				Long ketaUserId = bld.getKetaUser().getId();
				Boolean flag1 = true;
				for (Long cartItemId : cartItemIds) {
					CartItem cartItem = cartItemMng.findById(cartItemId);
					JxcBldGoodsNum jxcBldGoodsNum = jxcBldGoodsNumMng
							.findByProductIdAndKetaUserId(cartItem.getProduct()
									.getId(), ketaUserId);
					if (pay.getType() == 2) {// 货到付款判断便利店账户余额该订单足不足够平台流水分成
						Lsfc lsfc = lsfcMng.findByFctypeId(36l, cart
								.getWebsite().getId());// 流水分成便利店
						if (lsfc != null && lsfc.getId() > 0) {
							if (money < cart.getTotalPrice() * lsfc.getBl()) {
								bld = null;
								list.remove(resIndex);
								flag1 = false;
								break;
							}
						}
					}
					if (jxcBldGoodsNum == null
							|| cartItem.getCount() > jxcBldGoodsNum
									.getGoodsNum()) {
						bld = null;
						list.remove(resIndex);
						flag1 = false;
						break;
					}
				}
				if (!flag1) {
					bld=this.getOneVidationBld(price, pay, cart, list, cartItemIds);
				}

			}
			flag = false;
		}
		return bld;
	}

	public String getAddress(ShopMemberAddress address) {
		String str = "";
		if (address.getProvince() != null) {
			str = str + address.getProvince().getName() + " ";
		}
		if (address.getCity() != null) {
			str = str + address.getCity().getName() + " ";
		}
		if (address.getCountry() != null) {
			str = str + address.getCountry().getName() + " ";
		}
		if(address.getStreet()!=null){
			str = str + address.getStreet().getName() + " ";
		}
		str = str + address.getDetailaddress();
		return str;
	}

	public Order updateByUpdater(Order bean) {
		Updater<Order> updater = new Updater<Order>(bean);
		return orderDao.updateByUpdater(updater);
	}

	public Pagination getOrderByReturn(Long memberId, Integer pageNo,
			Integer pageSize) {
		return orderDao.getOrderByReturn(memberId, pageNo, pageSize);
	}

	@Transactional(readOnly = true)
	public Pagination getPage(Long webId, Long memberId, String productName,
			String userName, Long paymentId, Long shippingId, Date startTime,
			Date endTime, Double startOrderTotal, Double endOrderTotal,
			Integer status, Integer paymentStatus, Integer shippingStatus,
			Long code, int pageNo, int pageSize) {
		Pagination page = orderDao.getPage(webId, memberId, productName,
				userName, paymentId, shippingId, startTime, endTime,
				startOrderTotal, endOrderTotal, status, paymentStatus,
				shippingStatus, code, pageNo, pageSize);
		return page;
	}
	@Transactional(readOnly = true)
	public Pagination getPage(Integer ordeRType,Long webId, Long memberId, String productName,
			String userName, Long paymentId, Long shippingId, Date startTime,
			Date endTime, Double startOrderTotal, Double endOrderTotal,
			Integer status, Integer paymentStatus, Integer shippingStatus,
			Long code, int pageNo, int pageSize) {
		Pagination page = orderDao.getPage( ordeRType,webId, memberId, productName,
				userName, paymentId, shippingId, startTime, endTime,
				startOrderTotal, endOrderTotal, status, paymentStatus,
				shippingStatus, code, pageNo, pageSize);
		return page;
	}

	public List<Order> getlist() {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date newDate = new Date();
		Date endDate = null;
		Calendar date = Calendar.getInstance();
		date.setTime(newDate);
		date.set(Calendar.DATE, date.get(Calendar.DATE) - 1);
		try {
			endDate = sdf.parse(sdf.format(date.getTime()));
		} catch (ParseException e) {
			e.printStackTrace();
		}
		List<Order> list = orderDao.getlist(endDate);
		return list;
	}

	public void abolishOrder() {
		List<Order> orderList = getlist();
		for (Order order : orderList) {
			order.setStatus(3);
			Timestamp d = new Timestamp(System.currentTimeMillis()); 
			order.setFinishedTime(new Date());
			// 处理库存
			// for(OrderItem item:order.getItems()){
			// Product product=item.getProduct();
			// if(item.getProductFash()!=null){
			// ProductFashion fashion=item.getProductFash();
			// fashion.setStockCount(fashion.getStockCount()+item.getCount());
			// product.setStockCount(product.getStockCount()+item.getCount());
			// productFashionMng.update(fashion);
			// }else{
			// product.setStockCount(product.getStockCount()+item.getCount());
			// }
			// productMng.updateByUpdater(product);
			// }
			// 会员冻结的积分
			ShopMember member = order.getMember();
			member.setFreezeScore(member.getFreezeScore() - order.getScore());
			shopMemberMng.update(member);
			List<ShopScore> list = shopScoreMng.getlist(Long.toString(order
					.getCode()));
			for (ShopScore s : list) {
				shopScoreMng.deleteById(s.getId());
			}
			updateByUpdater(order);
		}
	}

	@Transactional(readOnly = true)
	public Pagination getPage(Long webId, Long memberId, String productName,
			String userName, Long paymentId, Long shippingId, Date startTime,
			Date endTime, Double startOrderTotal, Double endOrderTotal,
			Integer status, Long code, int pageNo, int pageSize) {
		Pagination page = orderDao.getPage(webId, memberId, productName,
				userName, paymentId, shippingId, startTime, endTime,
				startOrderTotal, endOrderTotal, status, code, pageNo, pageSize);
		return page;
	}

	@Transactional(readOnly = true)
	public Pagination getPageForMember(Long memberId, int pageNo, int pageSize) {
		return orderDao.getPageForMember(memberId, pageNo, pageSize);
	}

	public Pagination getPageForMember1(Long memberId, int pageNo, int pageSize) {
		return orderDao.getPageForMember1(memberId, pageNo, pageSize);
	}

	public Pagination getPageForMember2(Long memberId, int pageNo, int pageSize) {
		return orderDao.getPageForMember2(memberId, pageNo, pageSize);
	}

	public Pagination getPageForMember3(Long memberId, int pageNo, int pageSize) {
		return orderDao.getPageForMember3(memberId, pageNo, pageSize);
	}

	@Transactional(readOnly = true)
	public Order findById(Long id) {
		Order entity = orderDao.findById(id);
		return entity;
	}
	@Transactional(readOnly = true)
	public Order findByCode(Long code) {
		Order entity = orderDao.findByCode(code);
		return entity;
	}
	@Transactional(readOnly = true)
	public Order findByTradeNo(String tradeNo) {
		Order entity = orderDao.findByTradeNo(tradeNo);
		return entity;
	}

	
	// 销量
	public void updateSaleCount(Long id) {
		Order entity = orderDao.findById(id);
		for (OrderItem item : entity.getItems()) {
			// Product product = item.getProduct();
			ProductSite productSite = item.getProductSite();
			productSite.setSaleCount(productSite.getSaleCount()
					+ item.getCount());
			productSiteMng.update(productSite);
		}
	}

	// 利润
	public void updateliRun(Long id) {
		Order entity = orderDao.findById(id);
		for (OrderItem item : entity.getItems()) {
			ProductSite productSite = item.getProductSite();
			ProductFashion productFashion = item.getProductFash();
			if (productFashion != null) {
				productSite.setLiRun(productSite.getLiRun()
						+ item.getCount()
						* (productFashion.getSalePrice() - productFashion
								.getCostPrice()));
			} else {
				productSite.setLiRun(productSite.getLiRun()
						+ item.getCount()
						* (productSite.getSalePrice() - productSite
								.getMarketPrice()));
			}

			productSiteMng.update(productSite);
		}
	}

	public Order save(Order bean) {
		bean.init();
		orderDao.save(bean);
		return bean;
	}

	public List<Object> getTotlaOrder(Long webId) {
		return orderDao.getTotlaOrder(webId);
	}

	public BigDecimal getMemberMoneyByYear(Long memberId) {
		return orderDao.getMemberMoneyByYear(memberId);
	}

	public Order deleteById(Long id) {
		Order bean = orderDao.findById(id);
		gatheringMng.deleteByorderId(id);
		shipmentsMng.deleteByorderId(id);
		if (bean.getReturnOrder() != null) {
			orderReturnMng.deleteById(bean.getReturnOrder().getId());
		}
		if ((bean.getShippingStatus() == 1 && bean.getStatus() == 1)
				|| (bean.getShippingStatus() == 1 && bean.getStatus() == 2)) {
			Set<OrderItem> set = bean.getItems();
			// 处理库存
			// for(OrderItem item:set){
			// Product product=item.getProduct();
			// if(item.getProductFash()!=null){
			// ProductFashion fashion=item.getProductFash();
			// fashion.setStockCount(fashion.getStockCount()+item.getCount());
			// product.setStockCount(product.getStockCount()+item.getCount());
			// productFashionMng.update(fashion);
			// }else{
			// product.setStockCount(product.getStockCount()+item.getCount());
			// }
			// productMng.updateByUpdater(product);
			// }
			// 会员冻结的积分
			ShopMember member = bean.getMember();
			member.setFreezeScore(member.getFreezeScore() - bean.getScore());
			shopMemberMng.update(member);
			List<ShopScore> list = shopScoreMng.getlist(Long.toString(bean
					.getCode()));
			for (ShopScore s : list) {
				shopScoreMng.deleteById(s.getId());
			}
		}

		bean = orderDao.deleteById(id);
		return bean;
	}

	public Order[] deleteByIds(Long[] ids) {
		Order[] beans = new Order[ids.length];
		for (int i = 0, len = ids.length; i < len; i++) {
			beans[i] = deleteById(ids[i]);
		}
		return beans;
	}

	@Autowired
	private ProductMng productMng;
	@Autowired
	private ProductSiteMng productSiteMng;
	@Autowired
	private ProductFashionMng productFashionMng;
	@Autowired
	private ShopScoreMng shopScoreMng;
	@Autowired
	private WebsiteMng websiteMng;
	@Autowired
	private ShopMemberMng shopMemberMng;
	@Autowired
	private CartMng cartMng;
	@Autowired
	private AccountMng accountMng;
	@Autowired
	private AccountItemMng accountItemMng;
	@Autowired
	private OrderDao orderDao;
	@Autowired
	private OrderItemDao orderItemDao;
	@Autowired
	private CartItemMng cartItemMng;
	@Autowired
	private BldMng bldMng;
	@Autowired
	private GysMng gysMng;
	@Autowired
	private LsfcMng lsfcMng;
	@Autowired
	private JxcBldGoodsNumMng jxcBldGoodsNumMng;
	@Autowired
	private GatheringMng gatheringMng;
	@Autowired
	private ShipmentsMng shipmentsMng;
	@Autowired
	private OrderReturnMng orderReturnMng;
	@Autowired
	private MemberCouponMng memberCouponMng;
	@Autowired
	private PaymentMng paymentMng;
	@Autowired
	private JyspMng jyspMng;
	@Autowired
	private ShopMemberAddressMng shopMemberAddressMng;
	@Autowired
	private ShippingMng shippingMng;
	@Autowired
	private PopularityItemMng popularityItemMng;
}