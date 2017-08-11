package cn.e3mall.search.service;

import cn.e3mall.common.pojo.SearchResult;
import org.apache.solr.client.solrj.SolrServerException;

/**
 * solr查询
 * Created by ZX on 2017/8/2 10:25.
 */
public interface SearchService {
    /**
     * 搜索solr索引库
     * @param keyword 关键字
     * @param page 页数
     * @param rows 条数
     * @return 结果集
     */
    SearchResult search(String keyword,int page, int rows) throws SolrServerException;
}
