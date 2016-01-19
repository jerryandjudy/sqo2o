package com.jspgou.cms.manager;

import java.util.List;

import com.jspgou.cms.entity.Logistics;
import com.jspgou.cms.entity.ShopOrderType;
import com.jspgou.common.page.Pagination;
/**
* This class should preserve.
* @preserve
*/
public interface ShopOrderTypeMng {
		
	public List<ShopOrderType> getAllList();

	public ShopOrderType findById(Long id);

	public ShopOrderType save(ShopOrderType bean);

	public ShopOrderType update(ShopOrderType bean);

	public ShopOrderType deleteById(Long id);

	public ShopOrderType[] deleteByIds(Long[] ids);
	
	public ShopOrderType findByTypeCode(String typeCode);

	/**
	 * 更新排列顺序
	 * 
	 */
	public ShopOrderType[] updatePriority(Long[] ids, Integer[] priority);
}