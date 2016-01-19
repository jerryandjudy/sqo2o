package com.jspgou.cms.dao;

import java.util.Date;

import com.jspgou.common.page.Pagination;
import com.jspgou.cms.entity.Discuss;

/**
* This class should preserve.
* @preserve
*/
public interface DiscussDao {
	public Discuss findById(Long id);

	public Discuss saveOrUpdate(Discuss bean);

	public Discuss update(Discuss bean);

	public Discuss deleteById(Long id);
	
	public Pagination getPageByProduct(Long productId,String userName,String productName,
			Date startTime,Date endTime,int pageNo,int pageSize,boolean cache);
	public Pagination getPageBySqService(Long sqServiceId,String userName,String productName,
			Date startTime,Date endTime,int pageNo,int pageSize,boolean cache);
	
	public Pagination getPageByMember(Long memberId,int pageNo,int pageSize,boolean cache);
	
}