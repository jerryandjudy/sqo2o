package com.jspgou.cms.dao;

import java.util.List;
import com.jspgou.cms.entity.Poster;

/**
* This class should preserve.
* @preserve
*/
public interface PosterDao {
	public Poster findById(Integer id);

	public Poster saveOrUpdate(Poster bean);

	public Poster update(Poster bean);

	public Poster deleteById(Integer id);
	
	public List<Poster> getPage();	
}