/**
* 吉林省艾利特信息技术有限公司
* 进销存管理系统
* @date 2015年4月10日
* @author liuwang
* @version 1.0
*/
package com.jspgou.cms.dao;

import java.util.List;

import com.jspgou.cms.entity.Bld;
import com.jspgou.cms.entity.Gys;
import com.jspgou.cms.entity.Jmf;
import com.jspgou.cms.entity.JxcBldGoodsNum;
import com.jspgou.common.hibernate3.Updater;
import com.jspgou.common.page.Pagination;

/**
* This class should preserve.
* @preserve
*/
public interface JxcBldGoodsNumDao {

	public JxcBldGoodsNum findByProductIdAndKetaUserId(Long productId,Long ketaUserId);
	public JxcBldGoodsNum updateByUpdater(Updater<JxcBldGoodsNum> updater);
	public Pagination getPage(Long siteId,String username,String companyName, int pageNo, int pageSize);
}