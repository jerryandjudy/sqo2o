package com.jspgou.core.cache;

/**
* This class should preserve.
* @preserve
*/
public interface DomainCacheSvc{

    public  void put(String s, String as[], Long long1);

    public  boolean remove(String s, String as[]);

    public  Long get(String s);

    public  void removeAll();
}
