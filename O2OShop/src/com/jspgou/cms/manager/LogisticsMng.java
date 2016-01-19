package com.jspgou.cms.manager;

import java.util.List;

import com.jspgou.cms.entity.Logistics;


/**
* This class should preserve.
* @preserve
*/
public interface LogisticsMng {
	
	public List<Logistics> getAllList();

	public Logistics findById(Long id);

	public Logistics save(Logistics bean, String text);

	public Logistics update(Logistics bean, String text);

	public Logistics deleteById(Long id);

	public Logistics[] deleteByIds(Long[] ids);

	/**
	 * 更新排列顺序
	 * 
	 */
	public Logistics[] updatePriority(Long[] ids, Integer[] priority);
	
}