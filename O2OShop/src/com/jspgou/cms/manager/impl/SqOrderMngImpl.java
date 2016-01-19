/**
* 吉林省艾利特信息技术有限公司
* O2OShop系统
* @date 2015年6月8日
* @author liuwang
* @version 1.0
*/
package com.jspgou.cms.manager.impl;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jspgou.cms.dao.SqOrderDao;
import com.jspgou.cms.entity.SqOrder;
import com.jspgou.cms.manager.SqOrderMng;
import com.jspgou.common.page.Pagination;
@Service
@Transactional
public class SqOrderMngImpl implements SqOrderMng {

	@Override
	public Pagination getPage(Long webId, Long memberId, String receiveName,
			String sellerName, Date createTime, Date finishedTime,
			Integer status, Long code, int pageNo, int pageSize) {
		// TODO Auto-generated method stub
		return sqOrderDao.getPage(webId, memberId, receiveName, sellerName, createTime, finishedTime, status, code, pageNo, pageSize);
	}
	@Override
	public SqOrder findByMember(Long memberId,Long sqServiceId){
		return sqOrderDao.findByMember(memberId, sqServiceId);
	}
	@Override
	public SqOrder save(SqOrder bean){
		return sqOrderDao.save(bean);
	}
	@Autowired
	private SqOrderDao sqOrderDao;
}
