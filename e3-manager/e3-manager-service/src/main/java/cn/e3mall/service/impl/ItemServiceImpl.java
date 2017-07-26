package cn.e3mall.service.impl;

import cn.e3mall.common.pojo.EasyUIDataGridResult;
import cn.e3mall.mapper.ItemMapper;
import cn.e3mall.pojo.Item;
import cn.e3mall.pojo.ItemExample;
import cn.e3mall.service.ItemService;
import com.alibaba.dubbo.container.page.PageHandler;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by ZX on 2017/7/24 11:32.
 */
@Service
public class ItemServiceImpl implements ItemService{
    @Autowired
    private ItemMapper itemMapper;
    @Override
    public Item getItemById(long itemId) {
        Item item = itemMapper.selectByPrimaryKey(itemId);
        return item;
    }

    @Override
    public EasyUIDataGridResult getItemList(int page, int rows) {
        //设置分页信息
        PageHelper.startPage(page,rows);
        //查询
        ItemExample example = new ItemExample();
        List<Item> list = itemMapper.selectByExample(example);
        //创建一个返回值对象
        EasyUIDataGridResult result = new EasyUIDataGridResult();
        result.setRows(list);
        //取分页结果
        PageInfo<Item> pageInfo = new PageInfo<Item>(list);
        result.setTotal(pageInfo.getTotal());
        return result;
    }
}
