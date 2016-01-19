package com.jspgou.cms.manager;

import java.util.List;

import com.jspgou.cms.entity.Collect;
import com.jspgou.cms.entity.ShopMember;
import com.jspgou.common.page.Pagination;

/**
* This class should preserve.
* @preserve
*/
public interface CollectMng {
	
	public Pagination getList(Integer pageSize,Integer pageNo,Long memberId) ;
	public Pagination getList(Long siteId,Integer pageSize,Integer pageNo,Long memberId) ;
	
	public Collect findById(Integer id);
	
	public Collect save(ShopMember bean,Long productId,Long fashionId);
	
	public Collect update(Collect bean,Long pTypeid);
	
	public Collect deleteById(Integer id);
	
	public Collect[] deleteByIds(Integer[] ids);
	
	public List<Collect> findByProductId(Long productId) ;
	
	public Collect findByProductFashionId(Long id) ;
	
}