package com.jspgou.cms.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.jspgou.common.hibernate3.Finder;
import com.jspgou.common.hibernate3.HibernateBaseDao;
import com.jspgou.common.page.Pagination;
import com.jspgou.cms.dao.ShopArticleDao;
import com.jspgou.cms.entity.ShopArticle;

/**
* This class should preserve.
* @preserve
*/
@Repository
public class ShopArticleDaoImpl extends HibernateBaseDao<ShopArticle, Long>
		implements ShopArticleDao {
	public Pagination getPage(Long channelId,Long webId, int pageNo, int pageSize) {
		Finder f = Finder.create("select bean from ShopArticle bean ");
		if (channelId != null) {
			
			f.append(" where channel.id=:channelId");
			f.setParam("channelId", channelId);
			f.append(" and bean.website.id=:webId");
			f.setParam("webId", webId);
		}else{
			f.append(" where bean.website.id=:webId");
			f.setParam("webId", webId);
		}
		f.append(" order by bean.publishTime desc");
		return find(f, pageNo, pageSize);
	}
	public Pagination getPage(Boolean hasTitleImg,Long channelId,Long webId, int pageNo, int pageSize) {
		Finder f = Finder.create("select bean from ShopArticle bean ");
		if (channelId != null) {
			
			f.append(" where channel.id=:channelId");
			f.setParam("channelId", channelId);
			f.append(" and bean.website.id=:webId");
			f.setParam("webId", webId);
		}else{
			f.append(" where bean.website.id=:webId");
			f.setParam("webId", webId);
		}
       if(hasTitleImg!=null){
			
			if(hasTitleImg){
				f.append("  and bean.titleImg is not null and titleImg<>''");
			}else{
				f.append("  and bean.titleImg is  null or titleImg=''");
			}
		}
		f.append(" order by bean.publishTime desc");
		return find(f, pageNo, pageSize);
	}

	public Pagination getPageByChannel(Long channelId, int pageNo,
			int pageSize, boolean cacheable) {
		Finder f = Finder.create("from ShopArticle bean");
		f.append(" where bean.channel.id=:channelId");
		f.append(" order by bean.publishTime desc");
		f.setParam("channelId", channelId);
		f.setCacheable(cacheable);
		return find(f, pageNo, pageSize);
	}

	public Pagination getPageByWebsite(Long webId, int pageNo, int pageSize,
			boolean cacheable) {
		Finder f = Finder.create("from ShopArticle bean");
		f.append(" where bean.website.id=:webId");
		f.append(" order by bean.publishTime desc");
		f.setParam("webId", webId);
		f.setCacheable(cacheable);
		return find(f, pageNo, pageSize);
	}

	@SuppressWarnings("unchecked")
	public List<ShopArticle> getListByChannel(Long channelId,Long webId, int firstResult,
			int maxResults, boolean cacheable) {
		Finder f = Finder.create("from ShopArticle bean");
		f.append(" where bean.channel.id=:channelId");
		f.setParam("channelId", channelId);
		if(webId!=null){
			f.append("  and bean.website.id=:webId");
			f.setParam("webId", webId);
		}
		f.setCacheable(cacheable);
		f.setFirstResult(firstResult);
		f.setMaxResults(maxResults);
		return find(f);
	}
	@SuppressWarnings("unchecked")
	public List<ShopArticle> getListByChannel(Boolean hasTitleImg,Long channelId,Long webId, int firstResult,
			int maxResults, boolean cacheable) {
		Finder f = Finder.create("from ShopArticle bean");
		f.append(" where bean.channel.id=:channelId");
		f.setParam("channelId", channelId);
		if(webId!=null){
			f.append("  and bean.website.id=:webId");
			f.setParam("webId", webId);
		}
		if(hasTitleImg!=null){
			
			if(hasTitleImg){
				f.append("  and bean.titleImg is not null and titleImg<>''");
			}else{
				f.append("  and bean.titleImg is  null or titleImg=''");
			}
		}
		f.setCacheable(cacheable);
		f.setFirstResult(firstResult);
		f.setMaxResults(maxResults);
		return find(f);
	}
	@SuppressWarnings("unchecked")
	public List<ShopArticle> getListByChannel(Long channelId, int firstResult,
			int maxResults, boolean cacheable) {
		Finder f = Finder.create("from ShopArticle bean");
		f.append(" where bean.channel.id=:channelId");
		f.setParam("channelId", channelId);
		f.setCacheable(cacheable);
		f.setFirstResult(firstResult);
		f.setMaxResults(maxResults);
		return find(f);
	}

	@SuppressWarnings("unchecked")
	public List<ShopArticle> getListByWebsite(Long webId, int firstResult,
			int maxResults, boolean cacheable) {
		Finder f = Finder.create("from ShopArticle bean");
		f.append(" where bean.website.id=:webId");
		f.setParam("webId", webId);
		f.setCacheable(cacheable);
		f.setFirstResult(firstResult);
		f.setMaxResults(maxResults);
		return find(f);
	}
	@SuppressWarnings("unchecked")
	public List<ShopArticle> getListByWebsite(Boolean hasTitleImg,Long webId, int firstResult,
			int maxResults, boolean cacheable) {
		Finder f = Finder.create("from ShopArticle bean");
		f.append(" where bean.website.id=:webId");
		f.setParam("webId", webId);
           if(hasTitleImg!=null){
			
			if(hasTitleImg){
				f.append("  and bean.titleImg is not null and titleImg<>''");
			}else{
				f.append("  and bean.titleImg is  null or titleImg=''");
			}
		}
		f.setCacheable(cacheable);
		f.setFirstResult(firstResult);
		f.setMaxResults(maxResults);
		return find(f);
	}

	public ShopArticle findById(Long id) {
		ShopArticle entity = get(id);
		return entity;
	}

	public ShopArticle save(ShopArticle bean) {
		getSession().save(bean);
		return bean;
	}

	public ShopArticle deleteById(Long id) {
		ShopArticle entity = super.get(id);
		if (entity != null) {
			getSession().delete(entity);
		}
		return entity;
	}

	@Override
	protected Class<ShopArticle> getEntityClass() {
		return ShopArticle.class;
	}
}