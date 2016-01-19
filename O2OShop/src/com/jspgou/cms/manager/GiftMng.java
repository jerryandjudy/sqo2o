package com.jspgou.cms.manager;

import com.jspgou.common.page.Pagination;
import com.jspgou.cms.entity.Gift;
/**
* This class should preserve.
* @preserve
*/
public interface GiftMng {
	
	public Pagination getPageGift( int pageNo, int pageSize) ;
	
	public Gift findById(Long id);

	public Gift save(Gift bean);

	public Gift updateByUpdater(Gift updater);

	public Gift deleteById(Long id);
	
	public Gift updateByGiftnumb(Long giftId,Integer giftNumb,Long shopMemberId);
	
	public Gift[] deleteByIds(Long[] ids);
}