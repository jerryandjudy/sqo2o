package com.jspgou.cms.dao;

import java.util.Date;
import java.util.List;

import com.jspgou.common.page.Pagination;
import com.jspgou.cms.entity.Consult;

/**
* This class should preserve.
* @preserve
*/
public interface ConsultDao {
	public Consult findById(Long id);

	public Consult saveOrUpdate(Consult bean);

	public Consult update(Consult bean);

	public Consult deleteById(Long id);
	
	public List<Consult> findByProductId(Long productId);
	
	public Pagination getPage(Long productId,String userName,String productName,
			Date startTime,Date endTime,int pageNo,int pageSize,boolean cache);
	
	public Consult getSameConsult(Long memberId);
	
	public Pagination getPageByMember(Long memberId,int pageNo,int pageSize,boolean cache);
}