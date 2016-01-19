package com.jspgou.core.cache.impl;

import net.sf.ehcache.Ehcache;
import net.sf.ehcache.Element;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.jspgou.core.cache.DomainCacheSvc;
/**
* This class should preserve.
* @preserve
*/
@Service
public class DomainCacheSvcImpl implements DomainCacheSvc{
	
    private Ehcache domainCache;
    
    @Autowired
    public void setDomainCache(@Qualifier("domain")Ehcache domainCache){
        this.domainCache = domainCache;
    }

    public void put(String paramString, String[] paramArrayOfString, Long paramLong){
        domainCache.put(new Element(paramString, paramLong));
        if(paramArrayOfString != null){
        	for(String s1:paramArrayOfString){
        		domainCache.put(new Element(s1, paramLong));
        	}
        }
    }

    public boolean remove(String paramString, String as[]){
        if(as != null){
        	for(String s1:as){
        		domainCache.remove(s1);
        	}
        }
        return domainCache.remove(paramString);
    }

    public Long get(String paramString){
        Element element = domainCache.get(paramString);
        if(element != null){
            return (Long)element.getValue();
        }else{
            return null;
        }
    }

    public void removeAll(){
        domainCache.removeAll();
    }
}
