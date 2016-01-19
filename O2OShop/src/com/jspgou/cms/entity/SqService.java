/**
* 吉林省艾利特信息技术有限公司
* 进销存管理系统
* @date 2015年6月3日
* @author liuwang
* @version 1.0
*/
package com.jspgou.cms.entity;

import static com.jspgou.common.web.Constants.SPT;

import org.springframework.beans.factory.annotation.Autowired;

import com.jspgou.cms.entity.base.BaseSqService;
import com.jspgou.cms.manager.FwsMng;

public class SqService extends BaseSqService{
	/**
	 * 获得URL访问地址
	 * 
	 * @return
	 */
	public String getUrl() {
		return getWebsite().getUrlBuff(false).append(SPT).append(
				getCategory().getPath()).append(SPT).append(getId()).append(
				getWebsite().getSuffix()).toString();
	}
}
