package com.jspgou.cms.dao;

import java.util.List;

import com.jspgou.common.hibernate3.Updater;
import com.jspgou.cms.entity.KeyWord;

/**
* This class should preserve.
* @preserve
*/
public interface KeyWordDao {
	public List<KeyWord> getAllList();


	public KeyWord findById(Integer id);

	public KeyWord save(KeyWord bean);

	public KeyWord updateByUpdater(Updater<KeyWord> updater);

	public KeyWord deleteById(Integer id);
	
	public List<KeyWord> getKeyWordContent(String keyWord);
	public List<KeyWord> findKeyWord(Integer count) ;
}