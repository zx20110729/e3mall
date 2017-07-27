package cn.e3mall.service;

import cn.e3mall.common.pojo.E3Result;
import cn.e3mall.common.pojo.EasyUIDataGridResult;
import cn.e3mall.pojo.Item;

/**
 * Created by ZX on 2017/7/24 11:30.
 */
public interface ItemService {
    /**
     * 通过商品id查询商品详细信息
     *
     * @param itemId 商品id
     * @return
     */
    Item getItemById(long itemId);

    /**
     * 获取商品列表信息
     *
     * @param page 页码
     * @param rows 每页显示的行数
     * @return
     */
    EasyUIDataGridResult getItemList(int page, int rows);

    /**
     * 添加商品条目
     *
     * @param item 商品主体信息
     * @param desc 商品详细描述
     * @return
     */
    E3Result addItem(Item item, String desc);
}
