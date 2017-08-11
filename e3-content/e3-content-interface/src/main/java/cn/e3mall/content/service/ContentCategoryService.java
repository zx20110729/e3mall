package cn.e3mall.content.service;

import cn.e3mall.common.pojo.E3Result;
import cn.e3mall.common.pojo.EasyUIDataGridResult;
import cn.e3mall.common.pojo.EasyUITreeNode;

import java.util.List;

/**
 * 首页内容分类服务接口
 * Created by Administrator on 2017/7/27.
 */
public interface ContentCategoryService {
    List<EasyUITreeNode> getContentCatList(long parentId);

    E3Result addContentCategory(Long parentId,String name);

    EasyUIDataGridResult getContentList(long categoryId, int page, int rows);
}
