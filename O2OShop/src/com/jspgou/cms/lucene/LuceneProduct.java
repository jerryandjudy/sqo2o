package com.jspgou.cms.lucene;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.document.DateTools;
import org.apache.lucene.document.Document;
import org.apache.lucene.document.Field;
import org.apache.lucene.document.Fieldable;
import org.apache.lucene.document.DateTools.Resolution;
import org.apache.lucene.document.Field.Index;
import org.apache.lucene.document.Field.Store;
import org.apache.lucene.index.CorruptIndexException;
import org.apache.lucene.index.Term;
import org.apache.lucene.queryParser.MultiFieldQueryParser;
import org.apache.lucene.queryParser.ParseException;
import org.apache.lucene.search.BooleanClause;
import org.apache.lucene.search.BooleanQuery;
import org.apache.lucene.search.Query;
import org.apache.lucene.search.ScoreDoc;
import org.apache.lucene.search.Searcher;
import org.apache.lucene.search.TermQuery;
import org.apache.lucene.search.TermRangeQuery;
import org.apache.lucene.search.TopDocs;
import org.apache.lucene.util.Version;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.jspgou.common.file.lucene.MoneyTools;
import com.jspgou.common.page.Pagination;
import com.jspgou.cms.entity.Product;
import com.jspgou.cms.entity.ProductExended;
import com.jspgou.cms.entity.ProductInfo;
import com.jspgou.cms.entity.ProductSite;

/**
* This class should preserve.
* @preserve
*/
public class LuceneProduct implements ProductInfo {
	private static final Logger log = LoggerFactory
			.getLogger(LuceneProduct.class);

	public static Query createQuery(String queryString, Long webId, Long ctgId,Long brandId,
			Date start, Date end, Analyzer analyzer) throws ParseException {
		BooleanQuery bq = new BooleanQuery();
		Query q;
		if (!StringUtils.isBlank(queryString)) {
			q = MultiFieldQueryParser.parse(Version.LUCENE_30, queryString,
					QUERY_FIELD, QUERY_FLAGS, analyzer);
			bq.add(q, BooleanClause.Occur.MUST);
		}
		
		if (webId != null) {
			q = new TermQuery(new Term(WEBSITE_ID, webId.toString()));
			bq.add(q, BooleanClause.Occur.MUST);
		}
		if (ctgId != null) {
			q = new TermQuery(new Term(CATEGORY_ID_ARRAY, ctgId.toString()));
			bq.add(q, BooleanClause.Occur.MUST);
		}
		if (brandId != null) {
			q = new TermQuery(new Term(BRAND_ID, brandId.toString()));
			bq.add(q, BooleanClause.Occur.MUST);
		}
		if (start != null || end != null) {
			String startDate = null;
			String endDate = null;
			if (start != null) {
				startDate = DateTools.dateToString(start, Resolution.DAY);
			}
			if (end != null) {
				endDate = DateTools.dateToString(end, Resolution.DAY);
			}
			q = new TermRangeQuery(CREATE_TIME, startDate, endDate, true, true);
			bq.add(q, BooleanClause.Occur.MUST);
		}
		return bq;
	}

	public static Pagination getResult(Searcher searcher,
			TopDocs docs, int pageNo, int pageSize)
			throws CorruptIndexException, IOException {
		List<LuceneProduct> list = new ArrayList<LuceneProduct>(pageSize);
		ScoreDoc[] hits = docs.scoreDocs;
		int endIndex = pageNo * pageSize;
		int len = hits.length;
		if (endIndex > len) {
			endIndex = len;
		}
		for (int i = (pageNo - 1) * pageSize; i < endIndex; i++) {
			Document d = searcher.doc(hits[i].doc);
			list.add(createProduct(d));
		}
		return new Pagination(pageNo, pageSize,docs.totalHits, list);
	}

	/**
	 * 获得Lucene格式的Document
	 * 
	 * @param p
	 *            产品持久化对象
	 * @return
	 */
	public static Document createDocument(ProductSite psite) {
		Product p=psite.getProduct();
		Document doc = new Document();
		doc.add(new Field(ID, p.getId().toString(), Field.Store.YES,
				Field.Index.NO));
		doc.add(new Field(WEBSITE_ID, p.getWebsite().getId().toString(),
				Field.Store.YES, Field.Index.NOT_ANALYZED));
		if(p.getBrandId()!=null){
			doc.add(new Field(BRAND_ID, p.getBrandId(),
					Field.Store.YES, Field.Index.NOT_ANALYZED));
		}
		for (Long id : p.getCategoryIdArray()) {
			doc.add(new Field(CATEGORY_ID_ARRAY, id.toString(),
					Field.Store.YES, Field.Index.NOT_ANALYZED));
		}
	
		doc.add(new Field(NAME, p.getName(), Field.Store.YES,
				Field.Index.ANALYZED));
		for (String name : p.getCategoryNameArray()) {
			doc.add(new Field(CATEGORY_NAME_ATTRY, name, Field.Store.YES,
					Field.Index.ANALYZED));
		}
		if (!StringUtils.isBlank(p.getMdescription())) {
			doc.add(new Field(DESCRIPTION, p.getMdescription(), Field.Store.YES,
					Field.Index.ANALYZED));
		}
		if (!StringUtils.isBlank(p.getBrandName())) {
			doc.add(new Field(BRAND_NAME, p.getBrandName(), Field.Store.YES,
					Field.Index.ANALYZED));
		}
		if (p.getExendeds()!=null&& p.getExendeds().size()>0) {
			for(ProductExended exended:p.getExendeds()){
				if("price".equals(exended.getName())){
				doc.add(new Field(PRICE_QJ,exended.getName(), Field.Store.YES,
						Field.Index.ANALYZED));
				}
			}
		}

		doc.add(new Field(URL, psite.getUrl(), Field.Store.YES, Field.Index.NO));

		if (!StringUtils.isBlank(p.getDetailImgUrl())) {
			doc.add(new Field(DETAIL_IMG_URL, p.getDetailImgUrl(),
					Field.Store.YES, Field.Index.NO));
		}
		if (!StringUtils.isBlank(p.getListImgUrl())) {
			doc.add(new Field(LIST_IMG_URL, p.getListImgUrl(), Field.Store.YES,
					Field.Index.NO));
		}
		if (!StringUtils.isBlank(p.getCoverImgUrl())) {
			doc.add(new Field(COVER_IMG_URL, p.getCoverImgUrl(), Field.Store.YES,
					Field.Index.NO));
		}
		if (!StringUtils.isBlank(p.getMinImgUrl())) {
			doc.add(new Field(MIN_IMG_URL, p.getMinImgUrl(), Field.Store.YES,
					Field.Index.NO));
		}

		doc.add(new Field(MARKET_PRICE, MoneyTools.moneyToString(psite
				.getMarketPrice()), Field.Store.YES, Field.Index.NOT_ANALYZED));
		doc.add(new Field(SALE_PRICE, String.valueOf(psite.getSalePrice()), Store.YES,Index.NOT_ANALYZED));
	    doc.add(new Field(SALE_COUNT,String.valueOf(p.getSaleCount()),Store.YES,Field.Index.NOT_ANALYZED));
		doc.add(new Field(VIEW_COUNT,String.valueOf(p.getViewCount()), Store.YES,Field.Index.NOT_ANALYZED));
		doc.add(new Field(CREATE_TIME, DateTools.dateToString(
				p.getCreateTime(), DateTools.Resolution.MILLISECOND),
				Field.Store.YES, Field.Index.NOT_ANALYZED));

		Collection<String> keywords = p.getKeywordArray();
		for (String keyword : keywords) {
			doc.add(new Field(KEYWORD_ARRAY, keyword, Field.Store.YES,
					Field.Index.ANALYZED));
		}
		Collection<String> tags = p.getTagArray();
		for (String tag : tags) {
			doc.add(new Field(TAG_ARRAY, tag, Field.Store.YES,
					Field.Index.ANALYZED));
		}
		return doc;
	}

	@SuppressWarnings("unchecked")
	public static LuceneProduct createProduct(Document d) {
		LuceneProduct p = new LuceneProduct();
		List<Fieldable> list = d.getFields();	
    	String name;
		for (Fieldable f : list) {
				name = f.name();
			if (name.equals(KEYWORD_ARRAY)) {
				p.addToKeyworeds(f.stringValue());
			} else if (name.equals(TAG_ARRAY)) {
				p.addToTags(f.stringValue());
			} else if (name.equals(ID)) {
				p.setId(Long.valueOf(f.stringValue()));
			} else if (name.equals(WEBSITE_ID)) {
				p.setWebsiteId(Long.valueOf(f.stringValue()));
			} else if (name.equals(BRAND_ID)) {
				p.setWebsiteId(Long.valueOf(f.stringValue()));
			}else if (name.equals(CATEGORY_ID_ARRAY)) {
				p.addToCategoryIds(Long.valueOf(f.stringValue()));
			} else if (name.equals(CREATE_TIME)) {
				try {
					p.setCreateTime(DateTools.stringToDate(f.stringValue()));
				} catch (java.text.ParseException e) {
					log.error("lucene date format faild", e);
				}
			} else if (name.equals(NAME)) {
				p.setName(f.stringValue());
			} else if (name.equals(CATEGORY_NAME_ATTRY)) {
				p.addToCategoryNames(f.stringValue());
			} else if (name.equals(BRAND_NAME)) {
				p.setBrandName(f.stringValue());
			} else if (name.equals(DESCRIPTION)) {
				p.setDescription(f.stringValue());
			} else if (name.equals(URL)) {
				p.setUrl(f.stringValue());
			} else if (name.equals(DETAIL_IMG_URL)) {
				p.setDetailImgUrl(f.stringValue());
			} else if (name.equals(LIST_IMG_URL)) {
				p.setListImgUrl(f.stringValue());
			} else if (name.equals(COVER_IMG_URL)) {
				p.setCoverImgUrl(f.stringValue());
			} else if (name.equals(MIN_IMG_URL)) {
				p.setMinImgUrl(f.stringValue());
			} else if (name.equals(MARKET_PRICE)) {
				p.setMarketPrice(MoneyTools.stringToMoney(f.stringValue()));
			} else if (name.equals(SALE_PRICE)) {
				p.setSalePrice(Double.valueOf(f.stringValue()));
			} else if(name.equals(SALE_COUNT)){
				p.setSaleCount(Integer.valueOf(f.stringValue()));
			} else if(name.equals(VIEW_COUNT)){
				p.setViewCount(Long.valueOf(f.stringValue()));
			}
		}

		return p;
	}

	public static final String ID = "id";
	public static final String WEBSITE_ID = "websiteId";
	public static final String CATEGORY_ID_ARRAY = "categoryIdArray";
	public static final String CATEGORY_NAME_ATTRY = "categoryNameArray";
	public static final String BRAND_ID = "brandId";
	public static final String BRAND_NAME = "brandName";
	public static final String NAME = "name";
	public static final String DESCRIPTION = "description";
	public static final String URL = "url";
	public static final String DETAIL_IMG_URL = "detailImgUrl";
	public static final String LIST_IMG_URL = "listImgUrl";
	public static final String COVER_IMG_URL = "coverImgUrl";
	public static final String MIN_IMG_URL = "minImgUrl";
	public static final String MARKET_PRICE = "marketPrice";
	public static final String SALE_PRICE = "salePrice";
	public static final String KEYWORD_ARRAY = "keywordArray";
	public static final String TAG_ARRAY = "tagArray";
	public static final String CREATE_TIME = "createTime";
	public static final String SALE_COUNT = "saleCount";
	public static final String VIEW_COUNT = "viewCount";
	public static final String PRICE_QJ = "priceQj";

	public static final String[] QUERY_FIELD = { NAME };
	/*public static final String[] QUERY_FIELD = { NAME, CATEGORY_NAME_ATTRY,
		BRAND_NAME, DESCRIPTION };*/
/*	public static final BooleanClause.Occur[] QUERY_FLAGS = {
			BooleanClause.Occur.SHOULD, BooleanClause.Occur.SHOULD,
			BooleanClause.Occur.SHOULD, BooleanClause.Occur.SHOULD };*/
	public static final BooleanClause.Occur[] QUERY_FLAGS = {
		BooleanClause.Occur.SHOULD };

	public void addToKeyworeds(String keyword) {
		if (this.keywordArray == null) {
			this.keywordArray = new ArrayList<String>();
		}
		this.keywordArray.add(keyword);
	}

	public void addToTags(String tag) {
		if (this.tagArray == null) {
			this.tagArray = new ArrayList<String>();
		}
		this.tagArray.add(tag);
	}

	public void addToCategoryNames(String categoryName) {
		if (this.categoryNameArray == null) {
			this.categoryNameArray = new ArrayList<String>();
		}
		this.categoryNameArray.add(categoryName);
	}

	public void addToCategoryIds(Long categoryId) {
		if (this.categoryIdArray == null) {
			this.categoryIdArray = new ArrayList<Long>();
		}
		this.categoryIdArray.add(categoryId);
	}

	private Long id;
	private Long websiteId;
	private Collection<Long> categoryIdArray;
	private Collection<String> categoryNameArray;
	private String brandName;
	private String name;
	private String url;
	private String description;
	private String detailImgUrl;
	private String listImgUrl;
	private String minImgUrl;
	private Double marketPrice;
	private Double salePrice;
	private Collection<String> keywordArray;
	private Collection<String> tagArray;
	private Date createTime;
	private String coverImgUrl;
	private Integer saleCount;
	private Long viewCount;
	private Long brandId;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getDetailImgUrl() {
		return detailImgUrl;
	}

	public void setDetailImgUrl(String detailImgUrl) {
		this.detailImgUrl = detailImgUrl;
	}

	public String getListImgUrl() {
		return listImgUrl;
	}

	public void setListImgUrl(String listImgUrl) {
		this.listImgUrl = listImgUrl;
	}
	public String getCoverImgUrl() {
		return coverImgUrl;
	}

	public void setCoverImgUrl(String coverImgUrl) {
		this.coverImgUrl = coverImgUrl;
	}

	public String getMinImgUrl() {
		return minImgUrl;
	}

	public void setMinImgUrl(String minImgUrl) {
		this.minImgUrl = minImgUrl;
	}

	public Double getMarketPrice() {
		return marketPrice;
	}

	public void setMarketPrice(java.lang.Double marketPrice) {
		this.marketPrice = marketPrice;
	}

	public Double getSalePrice() {
		return salePrice;
	}

	public void setSalePrice(java.lang.Double salePrice) {
		this.salePrice = salePrice;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public Long getWebsiteId() {
		return websiteId;
	}

	public void setWebsiteId(Long websiteId) {
		this.websiteId = websiteId;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public String getBrandName() {
		return brandName;
	}

	public void setBrandName(String brandName) {
		this.brandName = brandName;
	}

	public Collection<String> getKeywordArray() {
		return keywordArray;
	}

	public void setKeywordArray(Collection<String> keywordArray) {
		this.keywordArray = keywordArray;
	}

	public Collection<String> getTagArray() {
		return tagArray;
	}

	public void setTagArray(Collection<String> tagArray) {
		this.tagArray = tagArray;
	}

	public Collection<String> getCategoryNameArray() {
		return categoryNameArray;
	}

	public void setCategoryNameArray(Collection<String> categoryNameArray) {
		this.categoryNameArray = categoryNameArray;
	}

	public Collection<Long> getCategoryIdArray() {
		return categoryIdArray;
	}

	public void setCategoryIdArray(Collection<Long> categoryIdArray) {
		this.categoryIdArray = categoryIdArray;
	}

	public void setSaleCount(Integer saleCount) {
		this.saleCount = saleCount;
	}

	public Integer getSaleCount() {
		return saleCount;
	}

	public void setViewCount(Long viewCount) {
		this.viewCount = viewCount;
	}

	public Long getViewCount() {
		return viewCount;
	}

	public void setBrandId(Long brandId) {
		this.brandId = brandId;
	}

	public Long getBrandId() {
		return brandId;
	}

}
