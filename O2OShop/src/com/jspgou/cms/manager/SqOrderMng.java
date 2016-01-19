/**
* 吉林省艾利特信息技术有限公司
* O2OShop系统
* @date 2015年6月8日
* @author liuwang
* @version 1.0
*/
package com.jspgou.cms.manager;

import java.util.Date;

import com.jspgou.cms.entity.Bld;
import com.jspgou.cms.entity.OrderItem;
import com.jspgou.cms.entity.SqOrder;
import com.jspgou.common.page.Pagination;

public interface SqOrderMng {
	/**
	 * 查询服务订单列表
	 * @param webId
	 * @param memberId
	 * @param receiveName
	 * @param sellerName
	 * @param createTime
	 * @param finishedTime
	 * @param status
	 * @param code
	 * @param pageNo
	 * @param pageSize
	 * @return
	 */
	public Pagination getPage(Long webId,Long memberId,String receiveName,String sellerName, 
			Date createTime,Date finishedTime,Integer status,Long code,int pageNo, int pageSize);
	/**
	 * 会员购买
	 * @param memberId
	 * @param sqServiceId
	 * @return
	 */
	public SqOrder findByMember(Long memberId,Long sqServiceId);
	/**
	 * 保存
	 * @param bean
	 * @return
	 */
	public SqOrder save(SqOrder bean);
}
