package com.jspgou.cms.manager.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jspgou.cms.dao.KeyWordDao;
import com.jspgou.cms.entity.KeyWord;
import com.jspgou.cms.manager.KeyWordMng;
/**
* This class should preserve.
* @preserve
*/
@Service
@Transactional
public class KeyWordMngImpl implements KeyWordMng {
	@Transactional(readOnly = true)
	public List<KeyWord> getAllList() {
		List<KeyWord> list = keyWordDao.getAllList();
		return list;
	}

	@Transactional(readOnly = true)
	public KeyWord findById(Integer id) {
		KeyWord entity = keyWordDao.findById(id);
		return entity;
	}
	public List<KeyWord> findKeyWord(Integer count) {
		return keyWordDao.findKeyWord(count);
	}
	public KeyWord save(String keyword){
		List<KeyWord> list=this.getKeyWordContent(keyword);
		KeyWord bean=null;
		if(list.isEmpty()){
			bean=new KeyWord();
			bean.setKeyword(keyword);
			bean.setTimes(1);
			keyWordDao.save(bean);
		}else{
			bean=list.iterator().next();
			bean.setTimes(bean.getTimes()+1);
		}
		return bean;
	}
	
	public List<KeyWord> getKeyWordContent(String keyWord){
		return keyWordDao.getKeyWordContent(keyWord);
	}
	@Autowired
	public KeyWordDao keyWordDao;

}