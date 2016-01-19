package com.jspgou.cms.entity;

import static com.jspgou.common.web.Constants.SPT;

import java.sql.Timestamp;
import java.util.Date;

import org.apache.commons.lang.StringUtils;

import com.jspgou.cms.entity.base.BaseShopArticle;
/**
* This class should preserve.
* @preserve
*/
public class ShopArticle extends BaseShopArticle {
	private static final long serialVersionUID = 1L;

	/**
	 * 获得URL访问地址
	 * 
	 * @return
	 */
	public String getUrl() {
		if (!StringUtils.isBlank(getLink())) {
			return getLink();
		}
		return getWebsite().getUrlBuff(false).append(SPT).append(
				getChannel().getPath()).append(SPT).append(getId()).append(
				getWebsite().getSuffix()).toString();
	}

	/**
	 * 获得内容
	 * 
	 * @return
	 */
	public String getContent() {
		ShopArticleContent content = getArticleContent();
		if (content != null) {
			return content.getContent();
		} else {
			return null;
		}
	}

	public void init() {
		Date d = getPublishTime();
		if (d == null) {
			setPublishTime(new Timestamp(System.currentTimeMillis()));
		}
	}

	/* [CONSTRUCTOR MARKER BEGIN] */
	public ShopArticle() {
		super();
	}

	/**
	 * Constructor for primary key
	 */
	public ShopArticle(java.lang.Long id) {
		super(id);
	}

	/**
	 * Constructor for required fields
	 */
	public ShopArticle(java.lang.Long id,
			com.jspgou.core.entity.Website website,
			com.jspgou.cms.entity.ShopChannel channel,
			java.lang.String title, java.util.Date publishTime,
			java.lang.Boolean disabled) {

		super(id, website, channel, title, publishTime, disabled);
	}

	/* [CONSTRUCTOR MARKER END] */
}