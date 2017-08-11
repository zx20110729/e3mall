package cn.e3mall.search.service.impl;

import cn.e3mall.common.pojo.SearchResult;
import cn.e3mall.search.dao.SearchDAO;
import cn.e3mall.search.service.SearchService;
import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.SolrServerException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 商品检索service
 * Created by ZX on 2017/8/2 10:28.
 */
@Service
public class SearchServiceImpl implements SearchService {
    @Autowired
    private SearchDAO searchDAO;

    @Override
    public SearchResult search(String keyword, int page, int rows) throws SolrServerException {
        SolrQuery query = new SolrQuery();
        query.set("q", keyword);//检索关键字
        query.set("df", "item_title");//检索域
        query.setHighlight(true);
        query.addHighlightField("item_title");
        query.setHighlightSimplePre("<em style=\"color:red\">");
        query.setHighlightSimplePost("</em>");
        query.setStart((page <= 0 ? 1 : (page - 1)) * rows);
        query.setRows(rows);
        SearchResult result = searchDAO.search(query);
        long recordCount = result.getRecordCount();
        int totalPages = (int) (recordCount/rows);
        result.setTotalPages(totalPages);
        return result;
    }
}
