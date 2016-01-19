package com.jspgou.cms.manager;

import java.util.List;
import com.jspgou.cms.entity.Adspace;
/**
* This class should preserve.
* @preserve
*/
public interface AdspaceMng {
	public Adspace findById(Integer id);

	public Adspace save(Adspace bean);

	public Adspace updateByUpdater(Adspace updater);

	public Adspace deleteById(Integer id);
	
	public Adspace updateByAdspacenumb(Integer AdspaceId,Integer AdspaceNumb,Integer shopMemberId);
	
	public Adspace[] deleteByIds(Integer[] ids);
	
	public List<Adspace> getList();
}