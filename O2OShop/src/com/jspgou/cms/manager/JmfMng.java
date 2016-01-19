package com.jspgou.cms.manager;

import java.util.List;

import com.jspgou.cms.entity.Jmf;
import com.jspgou.common.page.Pagination;
/**
* This class should preserve.
* @preserve
*/
public interface JmfMng {
		
	public List<Jmf> getAllList();

	public Jmf findById(Long id);

	public Jmf save(Jmf bean);

	public Jmf update(Jmf bean);

	public Jmf deleteById(Long id);

	public Jmf[] deleteByIds(Long[] ids);
	
	public Jmf findByJmftypeId(Long jmftypeId,  Long websiteId);

	/**
	 * 更新排列顺序
	 * 
	 */
	public Jmf[] updatePriority(Long[] ids, Integer[] priority);
	
	public Pagination getPageByIsDisabled(Boolean isDisabled, int pageNo, int pageSize);
	
	public Pagination getPageByIsDisabledAndWebsiteId(Long websiteId, Boolean isDisabled, int pageNo, int pageSize);
	
	
	public Jmf changeIsDisabledById(Long id);
	
	public Jmf[] changeIsDisabledByIds(Long[] ids);
}