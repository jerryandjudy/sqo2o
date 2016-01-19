package com.jspgou.cms.manager;

import java.util.Date;
import java.util.List;

import com.jspgou.common.page.Pagination;
import com.jspgou.cms.entity.Consult;
/**
* This class should preserve.
* @preserve
*/
public interface ConsultMng {
	public Consult findById(Long id);

	public Consult saveOrUpdate(Long productId,String content,Long memberId);

	public Consult update(Consult Consult);

	public Consult deleteById(Long id);
	
	public List<Consult> findByProductId(Long productId) ;
	
	public Pagination getPage(Long productId,String userName,String productName,
			Date startTime,Date endTime,int pageNo,int pageSize,Boolean cache);
	
	public Consult[] deleteByIds(Long[] ids);
	
	public Pagination getPageByMember(Long memberId,int pageNo,int pageSize,boolean cache);
}