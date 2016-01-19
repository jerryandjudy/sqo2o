package com.jspgou.cms;
import java.io.File;

import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.document.Document;
import org.apache.lucene.document.Field;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.index.Term;
import org.apache.lucene.index.IndexWriter.MaxFieldLength;
import org.apache.lucene.search.IndexSearcher;
import org.apache.lucene.search.ScoreDoc;
import org.apache.lucene.search.Sort;
import org.apache.lucene.search.TermQuery;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.FSDirectory;
import org.apache.lucene.util.Version;

public class SortTest {
public static void makeItem(IndexWriter writer, String bookNumber,
String bookName, String publishDate) throws Exception {
writer.setUseCompoundFile(false);
Document doc = new Document();
Field f1 = new Field("bookNumber", bookNumber, Field.Store.YES,
Field.Index.NOT_ANALYZED);
Field f2 = new Field("bookName", bookName, Field.Store.YES,
Field.Index.ANALYZED);
Field f3 = new Field("publishDate", publishDate, Field.Store.YES,
Field.Index.NOT_ANALYZED);
doc.add(f1);
doc.add(f2);
doc.add(f3);
writer.addDocument(doc);
}

public static void main(String[] args) {
String Index_Store_Path = "D:/index/1";
File file = new File(Index_Store_Path);

try {
Directory Index = FSDirectory.open(file);
IndexWriter writer = new IndexWriter(Index, new StandardAnalyzer(Version.LUCENE_CURRENT), true,
MaxFieldLength.LIMITED);
writer.setUseCompoundFile(false);

Document doc1 = new Document();
Field f11 = new Field("bookNumber", "0000001", Field.Store.YES, Field.Index.NOT_ANALYZED);
Field f12 = new Field("bookName", "钢铁是怎样炼成的", Field.Store.YES, Field.Index.ANALYZED);
Field f13 = new Field("publishDate", "1970-01-01", Field.Store.YES, Field.Index.NOT_ANALYZED);
doc1.add(f11);
doc1.add(f12);
doc1.add(f13);

Document doc2 = new Document();
Field f21 = new Field("bookNumber", "0000002", Field.Store.YES, Field.Index.NOT_ANALYZED);
Field f22 = new Field("bookName", "钢铁战士", Field.Store.YES, Field.Index.ANALYZED);
Field f23 = new Field("publishDate", "1970-01-01", Field.Store.YES, Field.Index.NOT_ANALYZED);
doc2.add(f21);
doc2.add(f22);
doc2.add(f23);

Document doc3 = new Document();
Field f31 = new Field("bookNumber", "0000003", Field.Store.YES, Field.Index.NOT_ANALYZED);
Field f32 = new Field("bookName", "篱笆女人和狗", Field.Store.YES, Field.Index.ANALYZED);
Field f33 = new Field("publishDate", "1970-01-01", Field.Store.YES, Field.Index.NOT_ANALYZED);
doc3.add(f31);
doc3.add(f32);
doc3.add(f33);

Document doc4 = new Document();
Field f41 = new Field("bookNumber", "0000004", Field.Store.YES, Field.Index.NOT_ANALYZED);
Field f42 = new Field("bookName", "女人是水做的", Field.Store.YES, Field.Index.ANALYZED);
Field f43 = new Field("publishDate", "1970-01-01", Field.Store.YES, Field.Index.NOT_ANALYZED);
doc4.add(f41);
doc4.add(f42);
doc4.add(f43);

Document doc5 = new Document();
Field f51 = new Field("bookNumber", "0000005", Field.Store.YES, Field.Index.NOT_ANALYZED);
Field f52 = new Field("bookName", "英雄儿女", Field.Store.YES, Field.Index.ANALYZED);
Field f53 = new Field("publishDate", "1970-01-01", Field.Store.YES, Field.Index.NOT_ANALYZED);
doc5.add(f51);
doc5.add(f52);
doc5.add(f53);

Document doc6 = new Document();
Field f61 = new Field("bookNumber", "0000006", Field.Store.YES, Field.Index.NOT_ANALYZED);
Field f62 = new Field("bookName", "白毛女", Field.Store.YES, Field.Index.ANALYZED);
Field f63 = new Field("publishDate", "1970-01-01", Field.Store.YES, Field.Index.NOT_ANALYZED);
doc6.add(f61);
doc6.add(f62);
doc6.add(f63);

Document doc7 = new Document();
Field f71 = new Field("bookNumber", "0000007", Field.Store.YES, Field.Index.NOT_ANALYZED);
Field f72 = new Field("bookName", "我的兄弟和女儿", Field.Store.YES, Field.Index.ANALYZED);
Field f73 = new Field("publishDate", "1970-01-01", Field.Store.YES, Field.Index.NOT_ANALYZED);
doc7.add(f71);
doc7.add(f72);
doc7.add(f73);

writer.addDocument(doc1);
writer.addDocument(doc2);
writer.addDocument(doc3);
writer.addDocument(doc4);
writer.addDocument(doc5);
writer.addDocument(doc6);
writer.addDocument(doc7);
writer.optimize();
writer.close();
IndexSearcher searcher = new IndexSearcher(Index);
TermQuery q = new TermQuery(new Term("bookName", "女"));
ScoreDoc[] hits = searcher.search(q, null, 1000, Sort.INDEXORDER).scoreDocs;
for (int i = 0; i < hits.length; i++) {
Document hitDoc = searcher.doc(hits[i].doc);
System.out.print("书名 ：");
System.out.println(hitDoc.get("bookName"));
System.out.print("得分 ：");
System.out.println(hits[i].score);
System.out.print("内部ID ：");
System.out.println(hits[i].doc);
System.out.print("书号 ：");
System.out.println(hitDoc.get("bookNumber"));
System.out.print("发行日期 ：");
System.out.println(hitDoc.get("publishDate"));
}
} catch (Exception e) {
e.printStackTrace();
}
}
}

