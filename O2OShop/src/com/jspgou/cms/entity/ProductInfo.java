package com.jspgou.cms.entity;

import java.util.Collection;
import java.util.Date;

/**
* This class should preserve.
* @preserve
*/
public interface ProductInfo {
	public Long getId();

	public String getName();

	public Collection<String> getCategoryNameArray();

	public Collection<Long> getCategoryIdArray();

	public String getBrandName();


	public Collection<String> getKeywordArray();

	public Collection<String> getTagArray();

	public java.lang.String getDetailImgUrl();

	public java.lang.String getListImgUrl();

	public java.lang.String getMinImgUrl();


	public Date getCreateTime();
}
