package com.jspgou.cms.manager;

import java.util.List;

import com.jspgou.cms.entity.Gys;
import com.jspgou.cms.entity.JxcBldGoodsNum;
import com.jspgou.cms.entity.Jysp;
import com.jspgou.common.hibernate3.Updater;
import com.jspgou.common.page.Pagination;
import com.jspgou.core.entity.Website;

/**
* This class should preserve.
* @preserve
*/
public interface JyspMng {
	public Pagination getPageByGysId(Gys gys, int pageNo, int pageSize);
	public Jysp save(Jysp jysp);
	public Jysp delete(Long id);
	public Jysp findById(Long id);
	public Pagination getJyspPage(Long siteId, String username,
			String companyName,String productName,Long typeId, int pageNo, int pageSize);
	public Jysp findByProductIdAndKetaUserId(Long productId,Long ketaUserId);
	public List<Gys> findGysByProductId(Long productId);
	public List<Jysp> findByProductId(Long parentId,Long productId);
	public Jysp update(Jysp bean);
}