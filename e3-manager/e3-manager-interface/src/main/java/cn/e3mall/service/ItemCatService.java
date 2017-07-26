package cn.e3mall.service;

import cn.e3mall.common.pojo.EasyUITreeNode;

import java.util.List;

/**
 *商品分类服务接口
 * Created by ZX on 2017/7/25 15:35.
 */
public interface ItemCatService {
    List<EasyUITreeNode> getItemCatList(long parentId);
}
