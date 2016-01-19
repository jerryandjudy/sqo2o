package com.jspgou.cms.manager;

import java.util.List;
import com.jspgou.cms.entity.Poster;
/**
* This class should preserve.
* @preserve
*/
public interface PosterMng {
	
	public Poster findById(Integer id);

	public Poster update(Poster Poster);

	public Poster deleteById(Integer id);
	
	public Poster saveOrUpdate(Poster bean);
	
	public List<Poster> getPage();
	
	public void deleteByIds(Integer[] ids);
	
}