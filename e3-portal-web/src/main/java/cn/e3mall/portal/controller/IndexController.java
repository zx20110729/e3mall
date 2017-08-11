package cn.e3mall.portal.controller;

import cn.e3mall.content.service.ContentService;
import cn.e3mall.pojo.Content;
import org.springframework.beans.factory.ListableBeanFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

/**
 * 首页控制器
 * Created by ZX on 2017/7/27 11:47.
 */
@Controller
public class IndexController {
    @Value("${CONTENT_LUNBO_ID}")
    private Long lunboId;
    @Autowired
    private ContentService contentService;

    @RequestMapping("/index")
    public String index(Model model) {
        List<Content> list = contentService.getContentListById(lunboId);
        model.addAttribute("ad1List",list);
        return "index";
    }
}
