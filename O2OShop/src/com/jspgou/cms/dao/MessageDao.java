package com.jspgou.cms.dao;

import java.util.List;

import com.jspgou.common.hibernate3.Updater;
import com.jspgou.common.page.Pagination;
import com.jspgou.cms.entity.Address;
import com.jspgou.cms.entity.Message;

/**
* This class should preserve.
* @preserve
*/
public interface MessageDao {
	public Long getCountByTel(String tel,Integer minute,Integer types );
	public Long getTodayCountByTel(String tel,Integer types);
	public Message save(Message bean);
	public Long getYzmByTel(String tel,String yzm,Integer minute,Integer types );

}