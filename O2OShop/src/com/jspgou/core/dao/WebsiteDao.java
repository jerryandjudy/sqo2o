package com.jspgou.core.dao;

import com.jspgou.common.hibernate3.Updater;
import com.jspgou.common.page.Pagination;
import com.jspgou.core.entity.Website;

import java.util.List;
/**
* This class should preserve.
* @preserve
*/
public interface WebsiteDao{
    public Website getUniqueWebsite();
    public List<Website> getList(boolean cacheable);
    public int countWebsite();

    public Website findByDomain(String name);
    public Website findByName(String name);
    public List<Website> getTopList();
    public List<Website> getListByParent(Integer parentId);
    public Pagination getAllPage(int pageNo, int pageSize);
    public List<Website> getListNotHasDls();
    public List<Website> getListNotHasBld();
    public List<Website> getListNotHasGys();
    public List<Website> getAllList();
    public List<Website> getAllNotCloseList();

    public Website findById(Long id);

    public Website save(Website bean);

    public Website updateByUpdater(Updater<Website> updater);

    public Website deleteById(Long id);
}
