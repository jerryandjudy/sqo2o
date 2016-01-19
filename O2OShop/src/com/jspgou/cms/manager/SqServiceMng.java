/**
* 吉林省艾利特信息技术有限公司
* O2OShop系统
* @date 2015年6月5日
* @author liuwang
* @version 1.0
*/
package com.jspgou.cms.manager;

import java.util.List;

import com.jspgou.cms.entity.SqService;
import com.jspgou.common.page.Pagination;

public interface SqServiceMng {
	/**
	 * 获取服务列表
	 * @param ctId
	 * @param groom
	 * @param status
	 * @param area
	 * @param name
	 * @param count
	 * @return
	 */
	public List<SqService> getList(Long ketaUserId,Long siteId,Long ctId,Boolean groom,Boolean status,Long provinceId,Long cityId,Long countryId,Long streetId,String name,Integer count);
	/**
	 * 服务分页数据
	 * @param ketaUserId
	 * @param siteId
	 * @param ctId
	 * @param groom
	 * @param status
	 * @param provinceId
	 * @param cityId
	 * @param countryId
	 * @param streetId
	 * @param name
	 * @param pageNo
	 * @param pageSize
	 * @return
	 */
	public Pagination getPage(Long ketaUserId,Long siteId,Long ctId,Boolean groom,Boolean status,Long provinceId,Long cityId,Long countryId,Long streetId,String name,int pageNo, int pageSize);
	/**
	 * 根据id查询对象
	 * @param id
	 * @return
	 */
	public SqService  getById(Long id);
}
