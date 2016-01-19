package com.jspgou.core.dao;

import com.jspgou.common.hibernate3.Updater;
import com.jspgou.core.entity.Admin;
/**
* This class should preserve.
* @preserve
*/
public interface AdminDao{
    public Admin getByUserId(Long userId, Long webId);
    public Admin getByUserId(Long userId);

    public Admin findById(Long id);

    public Admin save(Admin bean);

    public Admin updateByUpdater(Updater<Admin> updater);

    public Admin deleteById(Long id);
}
