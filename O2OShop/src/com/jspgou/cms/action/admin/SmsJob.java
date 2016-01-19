package com.jspgou.cms.action.admin;

import org.springframework.beans.factory.annotation.Autowired;

import com.jspgou.cms.manager.OrderMng;
/**
* This class should preserve.
* @preserve
*/
public class SmsJob {
	public void execute() {
		orderMng.abolishOrder();
	}
	
	@Autowired
	private OrderMng orderMng;
}
