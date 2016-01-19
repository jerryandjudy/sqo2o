package com.jspgou.core.manager.impl;

import com.jspgou.cms.dao.ShopConfigDao;
import com.jspgou.common.hibernate3.Updater;
import com.jspgou.common.page.Pagination;
import com.jspgou.core.cache.CoreCacheSvc;
import com.jspgou.core.cache.DomainCacheSvc;
import com.jspgou.core.dao.AccountDao;
import com.jspgou.core.dao.WebsiteDao;
import com.jspgou.core.entity.Account;
import com.jspgou.core.entity.Website;
import com.jspgou.core.manager.WebsiteMng;

import java.util.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
/**
* This class should preserve.
* @preserve
*/
@Service
@Transactional
public class WebsiteMngImpl implements WebsiteMng{

	@Transactional(readOnly=true)
    public Website getWebsite(String s){
        Integer count = coreCacheSvc.getWebsiteCount();
        int i;
        if(count == null){
            i = dao.countWebsite();
            coreCacheSvc.putWebsiteCount((new Integer(i)).intValue());
        } else{
            i = count.intValue();
        }
        Long id;
        Website website;
        if(i == 1){
            id = coreCacheSvc.getWebsiteId();
            if(id != null){
                website = findById(id);
            } else{
                website = dao.getUniqueWebsite();
                coreCacheSvc.putWebsiteId(website.getId());
            }
        } else if(i > 1){
            id = domainCacheSvc.get(s);
            if(id != null){
                website = findById(id);
            } else{
                website = dao.findByDomain(s);
                if(website != null){
                    domainCacheSvc.put(website.getDomain(), website.getDomainAliasArray(), website.getId());
                }
            }
        } else{
            throw new IllegalStateException("no website data in database, please init database!");
        }
        return website;
    }
	@Transactional(readOnly = true)
	public List<Website> getListFromCache() {
		return dao.getList(true);
	}
    public Pagination getAllPage(int pageNo, int pageSize){
        return dao.getAllPage(pageNo, pageSize);
    }

    public List<Website> getAllList(){
        return dao.getAllList();
    }
    public List<Website> getAllNotCloseList(){
    	return dao.getAllNotCloseList();
    }

    public Website findById(Long id){
        return dao.findById(id);
    }
    @Transactional
    public Website save(Website bean){
    	Account account=accountDao.save(bean.getAccount());
    	bean.setAccount(account);
        Website entity = dao.save(bean);
        fireOnSave(entity);
        return entity;
    }

    public Website update(Website bean){
        Website entity = findById(bean.getId());
        String domain = entity.getDomain();
        String[] as = entity.getDomainAliasArray();
        entity = dao.updateByUpdater(new Updater<Website>(bean));
        fireOnUpdate(domain, as, entity);
        return entity;
    }

    public Website deleteById(Long id) {
        Website entity = dao.deleteById(id);
        shopConfigDao.deleteById(id);
        fireOnDelete(entity);
        return entity;
    }

    public Website[] deleteByIds(Long[] ids){
        Website[] beans= new Website[ids.length];
        for(int i = 0; i < ids.length; i++){
        	shopConfigDao.deleteById(ids[i]);
        	beans[i] = dao.deleteById(ids[i]);
        }
        fireOnDeleteBatch(beans);
        return beans;
    }
    public Website findByDomain(String name){
    	return dao.findByDomain(name);
    }
    public Website findByName(String name){
    	return dao.findByName(name);
    }
    @SuppressWarnings("unchecked")
	private void resetWebsiteCache(){
        List<Website> list = dao.getAllList();
        int i = list.size();
        if(i == 0){
            throw new IllegalStateException("no website data in database, please init database!");
        }
        coreCacheSvc.putWebsiteCount(i);
        Object object;
        if(i == 1){
            Website entity = (Website)list.get(0);
            coreCacheSvc.putWebsiteId(entity.getId());
            domainCacheSvc.removeAll();
            domainCacheSvc.put(entity.getDomain(), entity.getDomainAliasArray(), entity.getId());
        } else{
            coreCacheSvc.removeWebsiteId();
            domainCacheSvc.removeAll();
            object = list.iterator();
            while (((Iterator)object).hasNext()){
              Website localWebsite = (Website)((Iterator)object).next();
              this.domainCacheSvc.put(localWebsite.getDomain(), localWebsite.getDomainAliasArray(), localWebsite.getId());
            }
        }
    }

    private void onDomainUpdated(String s, String[] as, Website website){
        String domain = website.getDomain();
        String[] as1= website.getDomainAliasArray();
        if(!domain.equals(s) || !Arrays.equals(as1, as)){
            domainCacheSvc.remove(s, as);
            domainCacheSvc.put(domain, as1, website.getId());
        }
    }

    private void onDomainDelete(Website bean){
        resetWebsiteCache();
    }

    private void onDomainDeleteBatch(Website[] beans){
        resetWebsiteCache();
    }

    private void onDomainSave(Website bean){
        resetWebsiteCache();
    }

    private void fireOnUpdate(String s, String as[], Website bean){
        onDomainUpdated(s, as, bean);
    }

    private void fireOnDelete(Website bean){
        onDomainDelete(bean);
    }

    private void fireOnDeleteBatch(Website[] beans){
        onDomainDeleteBatch(beans);
    }

    private void fireOnSave(Website bean){
        onDomainSave(bean);
    }
    
    private CoreCacheSvc coreCacheSvc;
    @Autowired
    private ShopConfigDao shopConfigDao;
    private DomainCacheSvc domainCacheSvc;
    private WebsiteDao dao;
    private AccountDao accountDao;
    
	@Autowired
    public void setCoreCacheSvc(CoreCacheSvc coreCacheSvc){
        this.coreCacheSvc = coreCacheSvc;
    }
	@Autowired
    public void setDomainCacheSvc(DomainCacheSvc domainCacheSvc){
        this.domainCacheSvc = domainCacheSvc;
    }
	@Autowired
    public void setDao(WebsiteDao dao){
        this.dao = dao;
    }
	@Override
	@Transactional(readOnly = true)
	public List<Website> getTopList() {
		// TODO Auto-generated method stub
		return dao.getTopList();
	}
	@Override
	@Transactional(readOnly=true)
	public List<Website> getListByParent(Integer parentId) {
		// TODO Auto-generated method stub
		return dao.getListByParent(parentId);
	}
	@Autowired
	public void setAccountDao(AccountDao accountDao) {
		this.accountDao = accountDao;
	}
	
}
