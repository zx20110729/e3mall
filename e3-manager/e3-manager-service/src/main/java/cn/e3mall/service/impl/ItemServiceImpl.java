package cn.e3mall.service.impl;

import cn.e3mall.mapper.ItemMapper;
import cn.e3mall.pojo.Item;
import cn.e3mall.service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by ZX on 2017/7/24 11:32.
 */
@Service
public class ItemServiceImpl implements ItemService{
    @Autowired
    private ItemMapper itemMapper;
    @Override
    public Item getItemById(long itemId) {
        return itemMapper.selectByPrimaryKey(itemId);
    }
}
