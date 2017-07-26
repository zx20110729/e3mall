package cn.e3mall.service;

import cn.e3mall.mapper.ItemMapper;
import cn.e3mall.pojo.Item;
import cn.e3mall.pojo.ItemExample;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.List;

/**
 * Created by ZX on 2017/7/25 13:39.
 */
public class PageHelperTest {
    @Test
    public void test_pageHelper() {
        ApplicationContext context = new ClassPathXmlApplicationContext("classpath:spring/applicationContext-dao.xml");
        ItemMapper mapper = context.getBean(ItemMapper.class);
        //设置分页信息
        PageHelper.startPage(1, 10);
        ItemExample example = new ItemExample();
        List<Item> itemList = mapper.selectByExample(example);

        PageInfo<Item> pageInfo = new PageInfo<Item>(itemList);
        System.out.println(pageInfo.getTotal());
        System.out.println(pageInfo.getPages());
        System.out.println(itemList.size());
    }
}
