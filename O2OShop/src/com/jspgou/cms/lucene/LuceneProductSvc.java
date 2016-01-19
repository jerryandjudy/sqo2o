package com.jspgou.cms.lucene;

import java.io.IOException;
import java.util.Date;

import org.apache.lucene.index.CorruptIndexException;
import org.apache.lucene.queryParser.ParseException;
import org.apache.lucene.store.LockObtainFailedException;

import com.jspgou.common.page.Pagination;

/**
* This class should preserve.
* @preserve
*/
public interface LuceneProductSvc {
	/**
	 * 索引产品
	 * 
	 * @param webId
	 *            站点ID
	 * @param path
	 *            索引绝对路径
	 * @param start
	 *            产品录入开始时间
	 * @param end
	 *            产品录入结束时间
	 */
	public int index(String path, Long webId, Date start, Date end)
			throws CorruptIndexException, LockObtainFailedException,
			IOException;

	/**
	 * 
	 * 搜索产品
	 * 
	 * @param path
	 *            索引地址
	 * @param queryString
	 *            搜索关键字
	 * @param webId
	 *            站点ID。可以为null。
	 * @param ctgId
	 *            产品类别ID。可以为null。
	 * @param start
	 *            开始时间。可以为null。
	 * @param end
	 *            结束时间。可以为null。
	 * @param pageNo
	 *            页码
	 * @param pageSize
	 *            每页条数
	 * @return
	 * @throws CorruptIndexException
	 * @throws IOException
	 * @throws ParseException
	 */
	public Pagination search(String path, String queryString, Long webId,
			Long ctgId,Long brandId, Date start, Date end,int orderBy, int pageNo, int pageSize)
			throws CorruptIndexException, IOException, ParseException;
}
