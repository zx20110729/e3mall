package cn.e3mall.manager.controller;

import cn.e3mall.common.pojo.E3Result;
import cn.e3mall.common.pojo.EasyUIDataGridResult;
import cn.e3mall.common.pojo.EasyUITreeNode;
import cn.e3mall.content.service.ContentCategoryService;
import cn.e3mall.content.service.ContentService;
import cn.e3mall.pojo.Content;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * 网站内容管理控制类
 * Created by ZX on 2017/7/27 16:07.
 */
@Controller
@RequestMapping(value = "/content")
public class ContentController {
    @Autowired
    private ContentCategoryService contentCategoryService;

    @Autowired
    private ContentService contentService;

    /**
     * 内容分类管理
     *
     * @param parentId
     * @return
     */
    @RequestMapping("/category/list")
    @ResponseBody
    public List<EasyUITreeNode> getContentCatList(@RequestParam(name = "id", defaultValue = "0") long parentId) {
        return contentCategoryService.getContentCatList(parentId);
    }

    @RequestMapping(value = "/category/create", method = RequestMethod.POST)
    @ResponseBody
    public E3Result create(Long parentId, String name) {
        return contentCategoryService.addContentCategory(parentId, name);
    }

    @RequestMapping(value = "/query/list", method = RequestMethod.GET)
    @ResponseBody
    public EasyUIDataGridResult getContentByCategoryId(long categoryId, int page, int rows) {
        return contentCategoryService.getContentList(categoryId, page, rows);
    }

    @RequestMapping(value = "/save")
    @ResponseBody
    public E3Result addContent(Content content) {
        return contentService.addContent(content);
    }
}
