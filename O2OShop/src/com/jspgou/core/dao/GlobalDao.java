package com.jspgou.core.dao;

import com.jspgou.core.entity.Global;
/**
* This class should preserve.
* @preserve
*/
public interface GlobalDao{
    public Global findById(Long id);

    public Global update(Global bean);
}
