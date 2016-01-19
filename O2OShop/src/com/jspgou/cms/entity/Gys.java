package com.jspgou.cms.entity;

import java.util.ArrayList;
import java.util.List;

import com.jspgou.cms.entity.base.BaseGys;

public class Gys extends BaseGys{
	public void addToPictures(String path, String desc) {
		List<ContentPicture> list = getPictures();
		if (list == null) {
			list = new ArrayList<ContentPicture>();
			setPictures(list);
		}
		ContentPicture cp = new ContentPicture();
		cp.setImgPath(path);
		cp.setDescription(desc);
		list.add(cp);
	}
}
