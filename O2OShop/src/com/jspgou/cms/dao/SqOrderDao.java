/**
* 吉林省艾利特信息技术有限公司
* O2OShop系统
* @date 2015年6月4日
* @author liuwang
* @version 1.0
*/
package com.jspgou.cms.dao;

import java.util.Date;

import com.jspgou.cms.entity.SqOrder;
import com.jspgou.common.page.Pagination;

public interface SqOrderDao {
	public Pagination getPage(Long webId, Long memberId, String receiveName,
			String sellerName, Date createTime, Date finishedTime,
			Integer status, Long code, int pageNo, int pageSize);
	public SqOrder findByMember(Long memberId,Long sqServiceId);
	public SqOrder save(SqOrder bean);
}
