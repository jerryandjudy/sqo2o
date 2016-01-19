package com.jspgou.cms.manager;

import java.util.List;

import com.jspgou.cms.entity.KeyWord;

/**
* This class should preserve.
* @preserve
*/
public interface KeyWordMng {
	
	public List<KeyWord> getAllList();

	public KeyWord findById(Integer id);

	public List<KeyWord> getKeyWordContent(String keyWord);
	public KeyWord save(String keyword);
	public List<KeyWord> findKeyWord(Integer count) ;
	
}