package com.jspgou.cms.manager.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jspgou.common.hibernate3.Updater;
import com.jspgou.common.page.Pagination;
import com.jspgou.cms.dao.AddressDao;
import com.jspgou.cms.dao.MessageDao;
import com.jspgou.cms.entity.Address;
import com.jspgou.cms.entity.Message;
import com.jspgou.cms.manager.AddressMng;
import com.jspgou.cms.manager.MessageMng;
/**
* This class should preserve.
* @preserve
*/
@Service
@Transactional
public class MessageMngImpl implements MessageMng {
	
	@Autowired
	private MessageDao messageDao;

	@Override
	public Long getCountByTel(String tel, Integer minute,Integer types) {
		// TODO Auto-generated method stub
		return messageDao.getCountByTel(tel, minute, types);
	}
	@Override
	public Long getTodayCountByTel(String tel,Integer types) {
		// TODO Auto-generated method stub
		return messageDao.getTodayCountByTel(tel, types);
	}
	@Override
	public Long getYzmByTel(String tel,String yzm,Integer minute ,Integer types) {
		// TODO Auto-generated method stub
		return messageDao.getYzmByTel( tel, yzm, minute , types);
	}

	@Override
	public Message save(Message bean) {
		// TODO Auto-generated method stub
		return messageDao.save(bean);
	}

	
}