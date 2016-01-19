package com.unionpay.acp;

import java.util.Set;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;




import com.jspgou.cms.entity.EcOrderJnl;
import com.jspgou.cms.entity.Order;
import com.jspgou.cms.entity.base.BaseEcOrderJnl;
import com.jspgou.cms.manager.OrderMng;
import com.jspgou.core.web.ApplicationContextHelper;


/**
 * 支付方式 包含退货  
 * @author Administrator
 *
 */
public abstract class PayWay  implements OrderPay{
	
	protected OwnerTask ownertask;
	protected com.jspgou.cms.entity.EcOrderJnl ecOrderJnl;
	protected OrderMng orderMng = (OrderMng)ApplicationContextHelper.getBean("orderMng");  
	

	/**
	 * 支付前处理 功能:确认订单状态、生成支付订单、启动定时任务
	 */
	@Override
	public boolean beforePayOrder(Set<Order> ecorders) throws Exception {

		boolean flag = false;
		if (ecorders == null || ecorders.size() == 0) {
			throw new Exception("提交订单为空,无法支付");
		}
		for(Order orders : ecorders) {
			// 检查订单状态  当订单支付状态为未支付，订单状态为未确认
			if((orders.getStatus()==3 || orders.getStatus()==4) || orders.getPaymentStatus()!=1) {
				return false;
			}
			// 检查原支付订单状态
			if(orders.getEcOrderJnl() != null){
				OwnerTask ownertask = TaskPackage.task.remove("");
				if(ownertask != null)
					ownertask.stop();
			}
		}

		flag = crtPayOrder();// 生成新支付订单
		if (!flag)
			throw new Exception("生成支付订单失败");

		inPayOrder();// 启动定时任务

		return flag;
	}

	/**
	 * 支付中处理,不同支付方式执行不同的定时任务.
	 */
	@Override
	public void inPayOrder() {
		try {
			 Subject subject = SecurityUtils.getSubject();
			 if(subject==null){
				 return;
			 }
			 String username = (String) subject.getPrincipal();
			// 创建定时任务
			crtTask(ecOrderJnl,username);
			// 启动定时任务
			if(TaskPackage.task.get(ecOrderJnl.getPaymentNo() + ecOrderJnl.getOrderDate()) != null){
				OwnerTask ownertask = TaskPackage.task.remove(ecOrderJnl.getPaymentNo()
						+ ecOrderJnl.getOrderDate());
				ownertask.stop();
			}
			// 保存定时任务
			ownertask.start();
			TaskPackage.task.put(
					ecOrderJnl.getPaymentNo() + ecOrderJnl.getOrderDate(),
					ownertask);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 支付后处理 在支付结果有返回切验证签名成功时选择调用此方法
	 * 注:银联在线应答,当是页面返回的应答时,不调用此方法.页面返回后经查询,返回的查询应答调用该方法.
	 */
	@Override
	public void afterPayOrder(Msg msg, EcOrderJnl ecorderjnl) throws Exception {
//		this.paymentConfig = new PaymentConfig();//需修改
		// 首先检查应答结果是否成功
		if(!checkRspMsg(msg))
			return;
		
		// 判断返回的支付订单在数据库中状态
		ecOrderJnl = new EcOrderJnl();//需修改
		if(ecOrderJnl.getRspcode2() != null && (ecOrderJnl.getRspcode2().equals("00") || ecOrderJnl.getRspcode2().equals("20") )){//原订单已经交易成功
			return;
		}else if(ecOrderJnl.getRspcode3() != null && (ecOrderJnl.getRspcode3().equals("00") || ecOrderJnl.getRspcode3().equals("20") )){
			return;
		}
		
		//判断订单状态
			if((ecOrderJnl.getoRder().getStatus()==3 || ecOrderJnl.getoRder().getStatus()==3) || ecOrderJnl.getoRder().getPaymentStatus()!=1){
				return;
			}
		
		// 修改订单状态
		updPayOrder(msg);
		System.out.println("into ownertask");
		// 移除支付中生成对应订单的定时任务并关闭
		OwnerTask ownertask = TaskPackage.task.get(ecOrderJnl.getPaymentNo() + ecOrderJnl.getOrderDate());
		if (ownertask != null) {
			TaskPackage.task.remove(ecOrderJnl.getPaymentNo()
					+ ecOrderJnl.getOrderDate());
			ownertask.stop();
		}
		
		
	}
	/**
	 * 创建定时任务
	 */
	protected void crtTask(EcOrderJnl ecOrderJnl,String token) throws Exception {
		if (ecOrderJnl == null || ecOrderJnl.getPaymentNo()==null) {
			throw new NullPointerException("Task ecOrderJnl is null");
		} else
			this.ecOrderJnl = ecOrderJnl;
		crtTask(token);
	}

	/**
	 * 获取支付请求信息
	 * 
	 * @return
	 * @throws Exception
	 */
	public abstract Msg getPayMsg() throws Exception;

	/**
	 * 创建并保存支付订单,修改订单状态
	 * 
	 * @return
	 */
	protected abstract boolean crtPayOrder();

	/**
	 * 支付成功后修改支付订单及订单状态
	 * 
	 * @param msg
	 *            支付返回响应报文
	 * @return
	 */
	protected abstract boolean updPayOrder(Msg msg);

	/**
	 * 创建支付定时任务
	 */
	protected abstract void crtTask(String token);
	
	/**
	 * 验证返回报文结果是否成功
	 * @param msg
	 * @return
	 */
	protected abstract boolean checkRspMsg(Msg msg);
	
	
}
