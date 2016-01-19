package com.jspgou.cms.manager;

import java.util.List;

import com.jspgou.cms.entity.Gys;
import com.jspgou.cms.entity.Khgx;
import com.jspgou.common.page.Pagination;

/**
 * This class should preserve.
 * 
 * @preserve
 */
public interface KhgxMng {
	public Khgx findById(Long id);

	public Khgx save(Khgx bean);
	public List getListByGysAndBldId(Gys gys,String[] ids);

	public Khgx updateByUpdater(Khgx bean);

	public Khgx deleteById(Long id);

	public Pagination getPage(Khgx khgx, int pageNo, int pageSize);
}