package com.jspgou.core.dao;

import java.util.List;

import com.jspgou.core.entity.Role;
import com.jspgou.common.hibernate3.Updater;
/**
* This class should preserve.
* @preserve
*/
public interface RoleDao {
	public List<Role> getList();
	public List<Role> getListByType(Integer roleType);

	public Role findById(Integer id);

	public Role save(Role bean);

	public Role updateByUpdater(Updater<Role> updater);

	public Role deleteById(Integer id);
}