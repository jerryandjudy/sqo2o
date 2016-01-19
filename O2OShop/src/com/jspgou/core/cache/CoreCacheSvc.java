package com.jspgou.core.cache;

/**
* This class should preserve.
* @preserve
*/
public interface CoreCacheSvc{

    public void putWebsiteCount(int i);

    public Integer getWebsiteCount();

    public void putWebsiteId(Long long1);

    public boolean removeWebsiteId();

    public Long getWebsiteId();
}
