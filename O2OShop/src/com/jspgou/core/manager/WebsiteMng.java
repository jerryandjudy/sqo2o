package com.jspgou.core.manager;

import com.jspgou.common.page.Pagination;
import com.jspgou.core.entity.Website;

import java.util.List;
/**
* This class should preserve.
* @preserve
*/
public interface WebsiteMng
{

    public Website getWebsite(String s);
    public List<Website> getListFromCache();
    public Website findByDomain(String name);
    public Website findByName(String name);
    public Pagination getAllPage(int i, int j);
    public List<Website> getTopList();
    public List<Website> getListByParent(Integer parentId);
    public List<Website> getAllList();
    public List<Website> getAllNotCloseList();

    public Website findById(Long id);

    public Website save(Website bean);

    public Website update(Website bean);

    public Website deleteById(Long id);

    public Website[] deleteByIds(Long[] ids);
}
