package cn.e3mall.search.message;

import cn.e3mall.common.pojo.SearchItem;
import cn.e3mall.search.dao.SearchDAO;
import cn.e3mall.search.mapper.SearchItemMapper;
import org.apache.solr.client.solrj.SolrServer;
import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.common.SolrInputDocument;
import org.springframework.beans.factory.annotation.Autowired;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.TextMessage;
import java.io.IOException;

/**
 * Created by ZX on 2017/8/7 16:18.
 */
public class ItemAddMessageListener implements MessageListener {
    @Autowired
    private SearchItemMapper mapper;
    @Autowired
    private SolrServer solrServer;

    @Override
    public void onMessage(Message message) {
        try {
            //从消息中读取新增商品id
            TextMessage textMessage = (TextMessage) message;
            String text = textMessage.getText();
            Long itemId = new Long(text);
            //等待事物提交
            Thread.sleep(100);
            //查询新增的信息
            SearchItem item = mapper.getItemById(itemId);
            //创建solr文档对象
            SolrInputDocument doc = new SolrInputDocument();
            doc.setField("id", item.getId());
            doc.setField("item_title", item.getTitle());
            doc.setField("item_image", item.getImage());
            doc.setField("item_price", item.getPrice());
            doc.setField("item_sell_point", item.getSell_point());
            doc.setField("item_category_name", item.getCategory_name());
            //把文档写入索引库
            solrServer.add(doc);
            solrServer.commit();
        } catch (JMSException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (SolrServerException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
