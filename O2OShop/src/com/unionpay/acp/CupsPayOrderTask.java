package com.unionpay.acp;

import java.util.Set;

import com.jspgou.cms.entity.EcOrderJnl;
import com.jspgou.cms.manager.OrderMng;
import com.jspgou.core.entity.User;
import com.jspgou.core.web.ApplicationContextHelper;

public class CupsPayOrderTask extends OwnerTask {

	private EcOrderJnl ecorderjnl;
	private User token;
	 OrderMng orderMng = (OrderMng)ApplicationContextHelper.getBean("orderMng"); 
	//EnumsOrderStatusService  enumsOrderStatusService = (EnumsOrderStatusService)ApplicationContextHelper.getBean("enumsOrderStatusServiceImpl"); 
	public CupsPayOrderTask(int maxTimes, int period,
			EcOrderJnl ecorderjnl, User token) {
		super(maxTimes, period);
		this.ecorderjnl = ecorderjnl;
		this.token = token;
	}

	/**
	 * 定时任务内容,调用支付平台订单查询接口,查询订单信息 查询返回后,返回结果成功,查询订单状态,正常状态,进入支付后流程处理.
	 * 查询返回结果是失败情况下,重新启动该定时任务.
	 */
	@Override
	public void taskrun() {
		this.stop();
		System.out.println("---------------" + currentTimes);
//		QuickPayQuery query = new QuickPayQuery();
//		String res = query.query(ecorderjnl.getTransType(),
//				ecorderjnl.getPaymentNo(), ecorderjnl.getOrderDate()
//						+ ecorderjnl.getOrderTime());
//		System.out.println("res = " + res);
//		//EnumsOrderStatus status = enumsOrderStatusService.getById(new BigInteger("1011"));
////		PayWay payway = new CupsPayWay(token);
//		if (res != null && !"".equals(res)) {
//
//			String[] arr = QuickPayUtils.getResArr(res);
//
//			if (query.checkSecurity(arr)) {// 验证签名
//				// 以下是商户业务处理
//				String queryResult = "";
//				for (int i = 0; i < arr.length; i++) {
//					String[] queryResultArr = arr[i].split("=");
//					// 处理商户业务逻辑
//					if (queryResultArr.length >= 2
//							&& "queryResult".equals(queryResultArr[0])) {
//						queryResult = arr[i].substring(queryResultArr[0]
//								.length() + 1);
//						break;
//					}
//				}
//				CupsMsg msg = new CupsMsg();
//				for (int i = 0; i < arr.length; i++) {
//					String[] queryResultArr = arr[i].split("=");
//
//					// 处理商户业务逻辑
//					if (queryResultArr.length >= 2
//							&& "version".equals(queryResultArr[0])) {
//						msg.setVersion(arr[i].substring(queryResultArr[0]
//								.length() + 1));
//					}
//					if (queryResultArr.length >= 2
//							&& "charset".equals(queryResultArr[0])) {
//						msg.setCharset(arr[i].substring(queryResultArr[0]
//								.length() + 1));
//					}
//					if (queryResultArr.length >= 2
//							&& "signMethod".equals(queryResultArr[0])) {
//						msg.setSignMethod(arr[i].substring(queryResultArr[0]
//								.length() + 1));
//					}
//					if (queryResultArr.length >= 2
//							&& "signature".equals(queryResultArr[0])) {
//						msg.setSignature(arr[i].substring(queryResultArr[0]
//								.length() + 1));
//					}
//					if (queryResultArr.length >= 2
//							&& "respCode".equals(queryResultArr[0])) {
//						msg.setRespCode(arr[i].substring(queryResultArr[0]
//								.length() + 1));
//					}
//					if (queryResultArr.length >= 2
//							&& "respTime".equals(queryResultArr[0])) {
//						msg.setRespTime(arr[i].substring(queryResultArr[0]
//								.length() + 1));
//					}
//					if (queryResultArr.length >= 2
//							&& "queryResult".equals(queryResultArr[0])) {
//						msg.setQueryResult(arr[i].substring(queryResultArr[0]
//								.length() + 1));
//					}
//					if (queryResultArr.length >= 2
//							&& "qid".equals(queryResultArr[0])) {
//						msg.setQid(arr[i].substring(queryResultArr[0].length() + 1));
//					}
//					if (queryResultArr.length >= 2
//							&& "traceNumber".equals(queryResultArr[0])) {
//						msg.setTraceNumber(arr[i].substring(queryResultArr[0]
//								.length() + 1));
//					}
//					if (queryResultArr.length >= 2
//							&& "traceTime".equals(queryResultArr[0])) {
//						msg.setTraceTime(arr[i].substring(queryResultArr[0]
//								.length() + 1));
//					}
//					if (queryResultArr.length >= 2
//							&& "settleAmount".equals(queryResultArr[0])) {
//						msg.setSettleAmount(arr[i].substring(queryResultArr[0]
//								.length() + 1));
//					}
//					if (queryResultArr.length >= 2
//							&& "settleCurrency".equals(queryResultArr[0])) {
//						msg.setSettleCurrency(arr[i]
//								.substring(queryResultArr[0].length() + 1));
//					}
//					if (queryResultArr.length >= 2
//							&& "settleDate".equals(queryResultArr[0])) {
//						msg.setSettleDate(arr[i].substring(queryResultArr[0]
//								.length() + 1));
//					}
//					if (queryResultArr.length >= 2
//							&& "exchangeRate".equals(queryResultArr[0])) {
//						msg.setExchangeRate(arr[i].substring(queryResultArr[0]
//								.length() + 1));
//					}
//					if (queryResultArr.length >= 2
//							&& "exchangeDate".equals(queryResultArr[0])) {
//						msg.setExchangeDate(arr[i].substring(queryResultArr[0]
//								.length() + 1));
//					}
//					if (queryResultArr.length >= 2
//							&& "cupReserved".equals(queryResultArr[0])) {
//						msg.setCupReserved(arr[i].substring(queryResultArr[0]
//								.length() + 1));
//						
//						String cupReserved = msg.getCupReserved();
//						cupReserved = cupReserved.replaceFirst("(\\{)([^}]+)(\\})", "$2");
//						String strs[] = cupReserved.split("&");
//						for(String str : strs){
//							String[] cupReservedArr = str.split("=");
//							if(cupReservedArr.length>=2 && "cardType".equals(cupReservedArr[0])){
//								msg.setCardType(str.substring(cupReservedArr[0].length() + 1));
//							}
//							if(cupReservedArr.length>=2 && "cardNumber".equals(cupReservedArr[0])){
//								msg.setCardNumber(str.substring(cupReservedArr[0].length() + 1));
//							}
//							//if(cupReservedArr.length>=2 && "payMode".equals(cupReservedArr[0])){
//							//	msg.setPayMode(str.substring(cupReservedArr[0].length() + 1));
//							//}
//						}
//						
//					}
//					
//				}
//				
//				System.out.println(msg);
//				if (queryResult.equals("0")) {// 支付成功
//					// ecorderjnl = (EcOrderJnl)
//					// ecorderdao.getAvailableById(ecorderjnl.getId());
//					//if (ecorderjnl.getDiscardOrder().equals("1")) {// 废弃订单
//					//	return;
//					//}
//					System.out.println("支付成功");
//					
//					try {
////						payway.afterPayOrder(msg, ecorderjnl);
//					} catch (Exception e) {
//						System.out.println(e.getMessage());
//						e.printStackTrace();
//					}
//					return;
//				} else {
//					if (currentTimes == maxTimes) {// 返回失败且定时任务执行完毕 删除任务.
//						TaskPackage.task.remove(ecorderjnl.getPaymentNo()
//								+ ecorderjnl.getOrderDate());
//						// 支付超时修改商品订单状态为"下单成功,等待卖家付款" 新建一条流程记录
//						/*Set<ShopOrder> orders = ecorderjnl.getEcorder();
//						for (ShopOrder ecorder : orders) {
//							
//							ecorder.setStatus(status);
//							
//							ShopOrderProcess process = new ShopOrderProcess();
//							process.setCreateDate(new Date());
//							process.setOrder(ecorder);
//							process.setDeleted(false);
//							process.setToken(token);
//							process.setStatus(status);
//							ecOrderService.payTimeoutOrder(ecorder, process);
//						}*/
//					}
//					this.reStart();
//				}
//			}else{
//				if (currentTimes == maxTimes) {// 返回失败且定时任务执行完毕 删除任务.
//					TaskPackage.task.remove(ecorderjnl.getPaymentNo()
//							+ ecorderjnl.getOrderDate());
//					// 支付超时修改商品订单状态为"下单成功,等待卖家付款" 新建一条流程记录
//					Set<ShopOrder> orders = ecorderjnl.getEcorder();
//					/*for (ShopOrder ecorder : orders) {
//						ecorder.setStatus(status);
//						
//						EcOrderProcess process = new EcOrderProcess();
//						process.setCreateDate(new Date());
//						process.setOrder(ecorder);
//						process.setDeleted(false);
//						process.setToken(token);
//						process.setStatus(status);
//						ecOrderService.payTimeoutOrder(ecorder, process);
//					}*/
//				}
//				this.reStart();
//			}
//		} else {
//			this.reStart();
//			System.out.println("报文格式为空");
//		}
	}

}
