package com.jspgou.cms.manager;

import java.util.List;

import com.jspgou.cms.entity.Lsfc;
import com.jspgou.common.page.Pagination;
/**
* This class should preserve.
* @preserve
*/
public interface LsfcMng {
		
	public List<Lsfc> getAllList();

	public Lsfc findById(Long id);

	public Lsfc save(Lsfc bean);

	public Lsfc update(Lsfc bean);

	public Lsfc deleteById(Long id);

	public Lsfc[] deleteByIds(Long[] ids);
	
	public Lsfc findByFctypeId(Long fctypeId,  Long websiteId);

	/**
	 * 更新排列顺序
	 * 
	 */
	public Lsfc[] updatePriority(Long[] ids, Integer[] priority);
	
	public Pagination getPageByIsDisabled(Boolean isDisabled, int pageNo, int pageSize);
	
	public Pagination getPageByIsDisabledAndWebsiteId(Long websiteId, Boolean isDisabled, int pageNo, int pageSize);
	
	
	public Lsfc changeIsDisabledById(Long id);
	
	public Lsfc[] changeIsDisabledByIds(Long[] ids);
}