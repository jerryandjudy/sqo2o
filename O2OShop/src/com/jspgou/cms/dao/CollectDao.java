package com.jspgou.cms.dao;

import java.util.List;

import com.jspgou.cms.entity.Collect;
import com.jspgou.common.hibernate3.Updater;
import com.jspgou.common.page.Pagination;

/**
* This class should preserve.
* @preserve
*/
public interface CollectDao {
	
	public Pagination getList(Integer pageSize,Integer pageNo,Long memberId);
	public Pagination getList(Long siteId,Integer pageSize,Integer pageNo,Long memberId);
	
	public Collect findById(Integer id);

	public Collect save(Collect bean);

	public Collect updateByUpdater(Updater<Collect> updater);

	public Collect deleteById(Integer id);
	
	public List<Collect> findByProductId(Long productId) ;
	
	public Collect findByProductFashionId(Long id) ;
}