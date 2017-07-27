package cn.e3mall.service.impl;

import cn.e3mall.common.pojo.E3Result;
import cn.e3mall.common.pojo.EasyUIDataGridResult;
import cn.e3mall.mapper.ItemDescMapper;
import cn.e3mall.mapper.ItemMapper;
import cn.e3mall.pojo.Item;
import cn.e3mall.pojo.ItemDesc;
import cn.e3mall.pojo.ItemExample;
import cn.e3mall.service.ItemService;
import cn.e3mall.util.IDUtils;
import com.alibaba.dubbo.container.page.PageHandler;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * Created by ZX on 2017/7/24 11:32.
 */
@Service
public class ItemServiceImpl implements ItemService {
    @Autowired
    private ItemMapper itemMapper;
    @Autowired
    private ItemDescMapper itemDescMapper;

    @Override
    public Item getItemById(long itemId) {
        Item item = itemMapper.selectByPrimaryKey(itemId);
        return item;
    }

    @Override
    public EasyUIDataGridResult getItemList(int page, int rows) {
        //设置分页信息
        PageHelper.startPage(page, rows);
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

    @Override
    public E3Result addItem(Item item, String desc) {
        long itemId = IDUtils.genItemId();
        Date created = new Date();
        //将item保存到数据库
        item.setId(itemId);
        //1-正常,2-下架,3-删除
        item.setStatus((byte) 1);
        item.setCreated(created);
        item.setUpdated(created);
        itemMapper.insert(item);
        //将itemDesc保存到数据库
        ItemDesc itemDesc = new ItemDesc();
        itemDesc.setItemId(itemId);
        itemDesc.setItemDesc(desc);
        itemDesc.setCreated(created);
        itemDesc.setUpdated(created);
        itemDescMapper.insert(itemDesc);
        return new E3Result(null);
    }
}
