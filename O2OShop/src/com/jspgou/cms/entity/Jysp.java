package com.jspgou.cms.entity;

import com.jspgou.cms.entity.base.BaseJysp;

public class Jysp extends BaseJysp{
	public String getProductName(){
		return getProductSite().getProduct().getName();
	}
	public String getProductTypeName(){
		return getProductSite().getProduct().getType().getName();
	}
	public String getRepertoryName(){
		Repertory repertory = getRepertory();
		if(repertory==null || repertory.getId()==null){
			return "";
		}
		return repertory.getName();
	}
	public String getGysCompanyName(){
		return getGys().getCompanyName();
	}

}
