package com.jspgou.cms.manager;

import java.util.List;

import com.jspgou.cms.entity.Address;
import com.jspgou.cms.entity.Message;
import com.jspgou.common.page.Pagination;
/**
* This class should preserve.
* @preserve
*/
public interface MessageMng {
	public Long getCountByTel(String tel,Integer minute ,Integer types);
	public Long getYzmByTel(String tel,String yzm,Integer minute,Integer types );
	public Long getTodayCountByTel(String tel ,Integer types);

	public Message save(Message bean);
}