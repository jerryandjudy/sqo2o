package com.jspgou.cms.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;
import com.jspgou.common.hibernate3.HibernateBaseDao;
import com.jspgou.cms.dao.KeyWordDao;
import com.jspgou.cms.entity.KeyWord;

/**
* This class should preserve.
* @preserve
*/
@Repository
public class KeyWordDaoImpl extends HibernateBaseDao<KeyWord, Integer> implements
		KeyWordDao {
	
	
	public KeyWord findById(Integer id) {
		KeyWord entity = get(id);
		return entity;
	}
	
	@SuppressWarnings("unchecked")
	public List<KeyWord> findKeyWord(Integer count) {
		List<KeyWord> list= this.getSession().createQuery("from KeyWord bean order by bean.times desc")
		.setMaxResults(count).list();
		return list;
	}
	
	@SuppressWarnings("unchecked")
	public List<KeyWord> getKeyWordContent(String keyWord){
		List<KeyWord> keyContent=this.getSession().createQuery("from KeyWord bean where bean.keyword=:keyword ")
		.setParameter("keyword", keyWord).list();
		return  keyContent;
	}
	
	public KeyWord save(KeyWord bean) {
		getSession().save(bean);
		return bean;
	}

	public KeyWord deleteById(Integer id) {
		KeyWord entity = super.get(id);
		if (entity != null) {
			getSession().delete(entity);
		}
		return entity;
	}

	@Override
	protected Class<KeyWord> getEntityClass() {
		return KeyWord.class;
	}


	public List<KeyWord> getAllList() {
		// TODO Auto-generated method stub
		return null;
	}
}