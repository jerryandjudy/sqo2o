package com.jspgou.core.manager;

import java.util.List;
import java.util.Set;

import com.jspgou.core.entity.Role;
/**
* This class should preserve.
* @preserve
*/
public interface RoleMng {
	public List<Role> getList();
	public List<Role> getListByType(Integer roleType);

	public Role findById(Integer id);

	public Role save(Role bean, Set<String> perms);

	public Role update(Role bean, Set<String> perms);

	public Role deleteById(Integer id);

	public Role[] deleteByIds(Integer[] ids);
}