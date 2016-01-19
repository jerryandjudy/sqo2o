package com.jspgou.cms.lucene;

import java.io.File;
import java.io.IOException;
import java.util.Date;

import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.index.CorruptIndexException;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.queryParser.ParseException;
import org.apache.lucene.search.IndexSearcher;
import org.apache.lucene.search.Query;
import org.apache.lucene.search.Searcher;
import org.apache.lucene.search.Sort;
import org.apache.lucene.search.SortField;
import org.apache.lucene.search.TopDocs;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.SimpleFSDirectory;
import org.apache.lucene.util.Version;
import org.apache.lucene.store.LockObtainFailedException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jspgou.common.page.Pagination;
import com.jspgou.cms.dao.ProductDao;

/**
* This class should preserve.
* @preserve
*/
@Service
public class LuceneProductSvcImpl implements LuceneProductSvc {
	public int index(String path, Long webId, Date start, Date end)
			throws CorruptIndexException, LockObtainFailedException,
			IOException {
		Directory dir = new SimpleFSDirectory(new File(path));
		IndexWriter writer = new IndexWriter(dir, new StandardAnalyzer(Version.LUCENE_30),
				true, IndexWriter.MaxFieldLength.LIMITED);
		writer.commit();
		try {
			int count = productDao.luceneWriteIndex(writer, webId, start, end);
			writer.optimize();
			return count;
		} finally {
			writer.close();
		}
	}

	public Pagination search(String path, String queryString, Long webId,
			Long ctgId,Long brandId, Date start, Date end,int orderBy, int pageNo, int pageSize)
			throws CorruptIndexException, IOException, ParseException {
		Directory dir = new SimpleFSDirectory(new File(path));
		Searcher searcher = new IndexSearcher(dir);
		try {
			Analyzer analyzer = new StandardAnalyzer(Version.LUCENE_30);
			Query query =LuceneProduct.createQuery(queryString, webId,ctgId,brandId, start, end, analyzer);
			Sort sort = getSort(orderBy);;
			TopDocs docs;
		    if(sort!=null){
				 docs = searcher.search(query, null, pageNo * pageSize,sort);
			}else{
				 docs = searcher.search(query, pageNo * pageSize);
			}
			Pagination p = LuceneProduct.getResult(searcher, docs, pageNo,pageSize);
			return p;
		} finally {
			searcher.close();
		}
	}
	
	
	private Sort getSort(int orderBy){
		Sort sort = null;
		switch (orderBy) {
			case 1:
				sort= new Sort(new SortField("saleCount", SortField.INT,true));
				return sort;
			case 2:
				sort= new Sort(new SortField("createTime", SortField.STRING,false));
				return sort;
			case 3:
				sort= new Sort(new SortField("salePrice", SortField.DOUBLE,true));
				return sort;
			case 4:
				sort= new Sort(new SortField("salePrice", SortField.DOUBLE,false));
				return sort;
			case 5:
				sort= new Sort(new SortField("viewCount", SortField.LONG,true));
				return sort;
			default:
				sort = null;
			}
		return sort;	
	}
	
	

	private ProductDao productDao;

	@Autowired
	public void setProductDao(ProductDao productDao) {
		this.productDao = productDao;
	}

}
