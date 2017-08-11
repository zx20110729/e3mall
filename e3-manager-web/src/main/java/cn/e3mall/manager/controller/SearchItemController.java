package cn.e3mall.manager.controller;

import cn.e3mall.common.pojo.E3Result;
import cn.e3mall.search.service.SearchItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 导入商品到索引库
 * Created by ZX on 2017/8/1 16:03.
 */
@Controller
public class SearchItemController {

    @Autowired
    private SearchItemService searchItemService;

    @RequestMapping(value = "/index/item/import")
    @ResponseBody
    public E3Result importAllItem() {
        return searchItemService.importAllItems();
    }
}
