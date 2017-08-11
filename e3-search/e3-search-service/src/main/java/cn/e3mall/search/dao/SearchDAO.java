package cn.e3mall.search.dao;

import cn.e3mall.common.pojo.SearchItem;
import cn.e3mall.common.pojo.SearchResult;
import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.SolrServer;
import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.client.solrj.response.QueryResponse;
import org.apache.solr.common.SolrDocument;
import org.apache.solr.common.SolrDocumentList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * 查询solr索引库
 * Created by ZX on 2017/8/2 10:10.
 */
@Repository
public class SearchDAO {
    @Autowired
    private SolrServer solrServer;
    /**
     * 根据查询条件查询solr索引库
     *
     * @param query
     * @return
     */
    public SearchResult search(SolrQuery query) throws SolrServerException {
        SearchResult searchResult = new SearchResult();
        //获取查询结果
        QueryResponse response = solrServer.query(query);
        SolrDocumentList results = response.getResults();
        //获取总条数
        long total = results.getNumFound();
        searchResult.setRecordCount(total);
        List<SearchItem> list = new ArrayList<>();
        //获取高亮文本
        Map<String, Map<String, List<String>>> highlighting = response.getHighlighting();
        for (SolrDocument doc: results){
            SearchItem item = new SearchItem();
            item.setId(Long.parseLong(doc.get("id").toString()));
            item.setCategory_name(doc.get("item_category_name").toString());
            item.setImage((String) doc.get("item_image"));
            item.setPrice((Long) doc.get("item_price"));
            item.setSell_point((String) doc.get("item_sell_point"));
            //取高亮标题显示
            List<String> itemTitle = highlighting.get(doc.get("id")).get("item_title");
            if (itemTitle!=null&&itemTitle.size()!=0){
                item.setTitle(itemTitle.get(0));
            }else{
                item.setTitle((String) doc.get("item_title"));
            }
            list.add(item);
        }
        searchResult.setItemList(list);
        //返回结果

        return searchResult;
    }
}
