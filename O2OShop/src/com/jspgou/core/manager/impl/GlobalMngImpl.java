package com.jspgou.core.manager.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jspgou.core.dao.GlobalDao;
import com.jspgou.core.entity.Global;
import com.jspgou.core.manager.GlobalMng;
/**
* This class should preserve.
* @preserve
*/
@Service
@Transactional
public class GlobalMngImpl  implements GlobalMng{

    public Global findById(Long id){
        return dao.findById(id);
    }

    public Global update(Global bean){
        return dao.update(bean);
    }

    private GlobalDao dao;
    
	@Autowired
    public void setDao(GlobalDao dao){
        this.dao = dao;
    }
}
