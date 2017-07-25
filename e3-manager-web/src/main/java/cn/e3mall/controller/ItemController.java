package cn.e3mall.controller;

import cn.e3mall.pojo.Item;
import cn.e3mall.service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * 商品管理控制器
 * Created by ZX on 2017/7/24 11:37.
 */
@RestController
public class ItemController {
    @Autowired
    private ItemService itemService;

    @RequestMapping(value = "/items/{itemId}",method = RequestMethod.GET)
    @ResponseBody
    public Item getItemById(@PathVariable long itemId){
        Item item = itemService.getItemById(itemId);
        return item;
    }
}
