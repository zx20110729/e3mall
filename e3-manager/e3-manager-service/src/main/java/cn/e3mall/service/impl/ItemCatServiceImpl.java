package cn.e3mall.service.impl;

import cn.e3mall.common.pojo.EasyUITreeNode;
import cn.e3mall.mapper.ItemCategoryMapper;
import cn.e3mall.pojo.ItemCategory;
import cn.e3mall.pojo.ItemCategoryExample;
import cn.e3mall.service.ItemCatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * 商品条目服务类
 * Created by ZX on 2017/7/25 15:37.
 */
@Service
public class ItemCatServiceImpl implements ItemCatService {
    @Autowired
    private ItemCategoryMapper itemCategoryMapper;

    @Override
    public List<EasyUITreeNode> getItemCatList(long parentId) {
        //根据parentId查询子节点
        ItemCategoryExample example = new ItemCategoryExample();
        ItemCategoryExample.Criteria criteria = example.createCriteria();
        criteria.andParentIdEqualTo(parentId);
        //把列表转换为目标数据
        List<ItemCategory> list = itemCategoryMapper.selectByExample(example);
        List<EasyUITreeNode> result = new ArrayList<>();
        for (ItemCategory category : list) {
            EasyUITreeNode node = new EasyUITreeNode();
            node.setId(category.getId());
            node.setText(category.getName());
            node.setState(category.getIsParent() ? "closed" : "open");
            result.add(node);
        }
        //返回结果
        return result;
    }
}
