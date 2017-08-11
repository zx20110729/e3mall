package cn.e3mall.content.service.impl;

import cn.e3mall.common.jedis.JedisClient;
import cn.e3mall.common.pojo.E3Result;
import cn.e3mall.content.service.ContentService;
import cn.e3mall.mapper.ContentMapper;
import cn.e3mall.pojo.Content;
import cn.e3mall.pojo.ContentExample;
import cn.e3mall.util.JsonUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * Created by ZX on 2017/7/28 10:18.
 */
@Service
public class ContentServiceImpl implements ContentService {
    @Autowired
    private ContentMapper contentMapper;

    @Autowired
    private JedisClient jedisClient;

    @Override
    public E3Result addContent(Content content) {
        content.setCreated(new Date());
        content.setUpdated(new Date());
        contentMapper.insert(content);
        //缓存同步,删除缓存中的内容列表
        jedisClient.hdel("CONTENTLIST",content.getCategoryId().toString());
        return new E3Result(null);
    }

    /**
     * 通过内容分类id查询内容列表
     *
     * @param catId
     * @return
     */
    @Override
    public List<Content> getContentListById(long catId) {
        //从缓存取
        try {
            String json = jedisClient.hget("CONTENT_LIST", catId + "");
            if (StringUtils.isNotBlank(json)) {
                List<Content> cacheList = JsonUtils.jsonToList(json, Content.class);
                return  cacheList;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        //缓存没有,查询数据库
        ContentExample example = new ContentExample();
        ContentExample.Criteria criteria = example.createCriteria();
        criteria.andCategoryIdEqualTo(catId);
        List<Content> list = contentMapper.selectByExample(example);

        //放入缓存
        try {
            jedisClient.hset("CONTENT_LIST", catId + "", JsonUtils.objectToJson(list));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }
}
