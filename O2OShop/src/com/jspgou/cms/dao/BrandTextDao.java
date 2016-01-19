package com.jspgou.cms.dao;

import com.jspgou.common.hibernate3.Updater;
import com.jspgou.cms.entity.BrandText;

/**
* This class should preserve.
* @preserve
*/
public interface BrandTextDao {
	public BrandText save(BrandText bean);

	public BrandText updateByUpdater(Updater<BrandText> updater);
}