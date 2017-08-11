package cn.e3mall.manager.controller;

import cn.e3mall.common.pojo.E3Result;
import cn.e3mall.common.pojo.EasyUIDataGridResult;
import cn.e3mall.common.pojo.EasyUITreeNode;
import cn.e3mall.pojo.Item;
import cn.e3mall.service.ItemCatService;
import cn.e3mall.service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 商品管理控制器
 * Created by ZX on 2017/7/24 11:37.
 */
@Controller
@RequestMapping("/item")
public class ItemController {
    @Autowired
    private ItemService itemService;
    @Autowired
    private ItemCatService itemCatService;

    /**
     * 商品详情
     *
     * @param itemId
     * @return
     */
    @RequestMapping("/{itemId}")
    @ResponseBody
    public Item getItemById(@PathVariable long itemId) {
        Item item = itemService.getItemById(itemId);
        return item;
    }

    /**
     * 商品列表
     *
     * @param page
     * @param rows
     * @return
     */
    @RequestMapping(value = "/list", method = RequestMethod.GET)
    @ResponseBody
    public EasyUIDataGridResult getItemList(Integer page, Integer rows) {
        return itemService.getItemList(page, rows);
    }

    /**
     * 商品分类列表
     *
     * @param parentId
     * @return
     */
    @RequestMapping("/cat/list")
    @ResponseBody
    public List<EasyUITreeNode> getCatList(@RequestParam(name = "id", defaultValue = "0") long parentId) {
        List<EasyUITreeNode> itemCatList = itemCatService.getItemCatList(parentId);
        return itemCatList;
    }

    @RequestMapping(value = "/save", method = RequestMethod.POST)
    @ResponseBody
    public E3Result addItem(Item item, String desc) {
        return itemService.addItem(item, desc);
    }
}
