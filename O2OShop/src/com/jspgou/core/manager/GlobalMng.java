package com.jspgou.core.manager;

import com.jspgou.core.entity.Global;
/**
* This class should preserve.
* @preserve
*/
public interface GlobalMng{

    public Global findById(Long id);

    public Global update(Global bean);
}
