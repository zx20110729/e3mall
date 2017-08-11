package cn.e3mall.content.service;

import cn.e3mall.common.pojo.E3Result;
import cn.e3mall.pojo.Content;

import java.util.List;

/**
 * 内容服务接口
 * Created by Administrator on 2017/7/28.
 */
public interface ContentService {
    E3Result addContent(Content content);

    /**
     * 通过分类id获取内容列表
     * @param catId
     * @return
     */
    List<Content> getContentListById(long catId);
}
