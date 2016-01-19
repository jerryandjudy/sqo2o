package com.unionpay.acp;

import java.util.Hashtable;

public class TaskPackage {
	//存放支付中启动的定时任务.key=支付订单号+交易日期  value = 定时任务
	public static Hashtable<String ,OwnerTask> task = new Hashtable<String, OwnerTask>();
}