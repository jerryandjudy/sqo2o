package com.jspgou.cms.manager;

import java.util.Date;

import com.jspgou.common.page.Pagination;
import com.jspgou.cms.entity.Discuss;
/**
* This class should preserve.
* @preserve
*/
public interface DiscussMng {
	public Discuss findById(Long id);

	public Discuss update(Discuss Discuss);

	public Discuss deleteById(Long id);
	
	public Discuss saveOrUpdate(Long productId,String content,Long memberId);
	public Discuss saveOrUpdateSer(Long sqServiceId,String content,Long memberId);
	
	public Pagination getPage(Long productId,String userName,String productName,
			Date startTime,Date endTime,int pageNo,int pageSize,boolean cache);
	public Pagination getPageSer(Long sqServiceId,String userName,String productName,
			Date startTime,Date endTime,int pageNo,int pageSize,boolean cache);
	
	public Discuss[] deleteByIds(Long[] ids);
	
	public Pagination getPageByMember(Long memberId,int pageNo,int pageSize,boolean cache);
	
}