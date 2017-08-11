package cn.e3mall.search.mapper;

import cn.e3mall.common.pojo.SearchItem;

import java.util.List;

/**
 * Created by ZX on 2017/8/1 13:11.
 */
public interface SearchItemMapper {
    /**
     * 查询结果集合
     * @return
     */
    List<SearchItem> getSearchItemList();

    /**
     * 提供id获取结果
     * @param id
     * @return
     */
    SearchItem getItemById(long id);
}
