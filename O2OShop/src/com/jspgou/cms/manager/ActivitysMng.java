/**
* 吉林省艾利特信息技术有限公司
* O2OShop系统
* @date 2015年8月4日
* @author liuwang
* @version 1.0
*/
package com.jspgou.cms.manager;

import com.jspgou.cms.entity.Activitys;
import com.jspgou.common.page.Pagination;

public interface ActivitysMng {
	public Pagination getPage(Long siteId,Boolean isusing,String name,int pageNo, int pageSize);
	public Activitys save(Activitys bean);
	public Activitys update(Activitys bean);
	public Activitys delete(Activitys bean);
	public Activitys findById(Long id);
}
