package com.jspgou.cms.manager;

import java.util.List;

import com.jspgou.cms.entity.Bld;
import com.jspgou.cms.entity.Gys;
import com.jspgou.cms.entity.JxcBldGoodsNum;
import com.jspgou.common.hibernate3.Updater;
import com.jspgou.common.page.Pagination;
import com.jspgou.core.entity.Website;

/**
* This class should preserve.
* @preserve
*/
public interface JxcBldGoodsNumMng {
	public JxcBldGoodsNum findByProductIdAndKetaUserId(Long productId,Long ketaUserId);
	public JxcBldGoodsNum update(JxcBldGoodsNum bean);
	public Pagination getPage(Long siteId,String username,String companyName, int pageNo, int pageSize);	
}