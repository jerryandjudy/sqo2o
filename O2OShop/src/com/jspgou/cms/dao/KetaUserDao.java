package com.jspgou.cms.dao;

import java.util.List;

import com.jspgou.cms.entity.KetaUser;
import com.jspgou.common.hibernate3.Updater;

/**
* This class should preserve.
* @preserve
*/
public interface KetaUserDao {
	public List<KetaUser> getAllList();

	public KetaUser findById(Long id);
	public KetaUser findByUserName(String username);
	
	public KetaUser save(KetaUser bean);

	public KetaUser updateByUpdater(Updater<KetaUser> updater);

	public KetaUser deleteById(Long id);
	
	
	
}