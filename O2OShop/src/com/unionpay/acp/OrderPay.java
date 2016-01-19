/**
* 吉林省艾利特信息技术有限公司
* 进销存管理系统-订单支付接口
* @date 2015年5月9日
* @author liyq
* @version 1.0
*/
package com.unionpay.acp;

import java.util.Set;

import com.jspgou.cms.entity.EcOrderJnl;
import com.jspgou.cms.entity.Order;

public interface OrderPay {
	/**
	 * 支付前处理
	 * @param ecorders 本次提交支付的所有订单
	 * @return true 支付前处理成功
	 * 		   false: 支付前处理失败,返回订单状态页面
	 * @throws Exception
	 */
	public boolean beforePayOrder(Set<Order> ecorders) throws Exception;
	/**
	 * 支付中处理
	 */
	public void inPayOrder();
	/**
	 * 支付后处理
	 * @param msg 支付平台返回报文
	 * @param ecOrderJnl 支付平台返回报文对应的支付订单
	 */
	public void afterPayOrder(Msg msg,EcOrderJnl ecOrderJnl) throws Exception;
}

