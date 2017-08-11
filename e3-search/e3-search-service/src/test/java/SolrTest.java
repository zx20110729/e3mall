import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.SolrServer;
import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.client.solrj.impl.HttpSolrServer;
import org.apache.solr.client.solrj.response.QueryResponse;
import org.apache.solr.common.SolrDocument;
import org.apache.solr.common.SolrDocumentList;
import org.apache.solr.common.SolrInputDocument;
import org.junit.Test;

import java.io.IOException;
import java.util.List;
import java.util.Map;

/**
 * Created by ZX on 2017/8/1 13:34.
 */
public class SolrTest {
    @Test
    public void test_addDocument() throws IOException, SolrServerException {
        //创建solrServer对象
        SolrServer solrServer = new HttpSolrServer("http://192.168.25.134:8080/solr/collection1");
        //创建一个文档对象SolrInputDocument
        SolrInputDocument document = new SolrInputDocument();
        //向文档中添加域
        document.addField("id", "doc01");
        document.addField("item_title", "测试商品01");
        document.addField("item_price", 1000);
        //把文档写入索引库
        solrServer.add(document);
        solrServer.commit();
    }

    @Test
    public void test_delDocument() throws IOException, SolrServerException {
        //创建solrServer对象
        SolrServer solrServer = new HttpSolrServer("http://192.168.25.134:8080/solr/collection1");

        //solrServer.deleteById("doc01");
        solrServer.deleteByQuery("id:doc01");

        solrServer.commit();
    }

    @Test
    public void test_query() throws SolrServerException {
        SolrServer solrServer = new HttpSolrServer("http://192.168.25.134:8080/solr/collection1");
        SolrQuery query = new SolrQuery();
        //设置查询条件
        //query.setQuery("*:*");
        query.set("q", "*:*");
        //获取的查询结果
        QueryResponse response = solrServer.query(query);
        SolrDocumentList results = response.getResults();
        System.out.println("总记录数:" + results.getNumFound());
        for (SolrDocument doc : results) {
            System.out.println(doc.get("id") + ":" + doc.get("item_title"));
        }
    }

    @Test
    public void test_queryConplex() throws SolrServerException {
        SolrServer solrServer = new HttpSolrServer("http://192.168.25.134:8080/solr/collection1");
        SolrQuery query = new SolrQuery();
        //设置查询条件
        //query.setQuery("*:*");
        query.set("q", "手机");//查询条件
        query.set("df", "item_title");//设置搜索域
        query.setStart(0);
        query.setRows(20);
        query.setHighlight(true);
        query.addHighlightField("item_title");
        query.setHighlightSimplePre("<em>");
        query.setHighlightSimplePost("</em>");
        //获取的查询结果
        QueryResponse response = solrServer.query(query);
        SolrDocumentList results = response.getResults();
        System.out.println("总记录数:" + results.getNumFound());
        for (SolrDocument doc : results) {
            Map<String, Map<String, List<String>>> highlighting = response.getHighlighting();
            String id = doc.get("id").toString();
            System.out.println("高亮id:" + id + ";结果:" + highlighting.get(id));
            System.out.println(doc.get("id") + ":" + doc.get("item_title"));
        }
        //取高亮显示的结果
    }
}
