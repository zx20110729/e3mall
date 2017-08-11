package cn.e3mall.search.service.impl;

import cn.e3mall.common.pojo.E3Result;
import cn.e3mall.common.pojo.SearchItem;
import cn.e3mall.search.mapper.SearchItemMapper;
import cn.e3mall.search.service.SearchItemService;
import org.apache.solr.client.solrj.SolrServer;
import org.apache.solr.common.SolrInputDocument;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by ZX on 2017/8/1 13:51.
 */
@Service
public class SearchItemServiceImpl implements SearchItemService {
    @Autowired
    private SearchItemMapper searchItemMapper;

    @Autowired
    private SolrServer solrServer;
    @Override
    public E3Result importAllItems() {
        try {
            //查询商品列表
            List<SearchItem> itemList = searchItemMapper.getSearchItemList();
            //建立商品列表
            for (SearchItem item : itemList) {
                //创建文档对象
                SolrInputDocument doc = new SolrInputDocument();
                doc.setField("id", item.getId());
                doc.setField("item_title", item.getTitle());
                doc.setField("item_image", item.getImage());
                doc.setField("item_price", item.getPrice());
                doc.setField("item_sell_point", item.getSell_point());
                doc.setField("item_category_name", item.getCategory_name());
                solrServer.add(doc);
            }
            //向文档添加域
            solrServer.commit();
            return E3Result.ok();
        } catch (Exception e) {
            e.printStackTrace();
            return E3Result.build(500, "数据导入时出现异常!");
        }

    }
}
