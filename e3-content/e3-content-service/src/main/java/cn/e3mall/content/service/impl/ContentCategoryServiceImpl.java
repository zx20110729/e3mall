package cn.e3mall.content.service.impl;

import cn.e3mall.common.pojo.E3Result;
import cn.e3mall.common.pojo.EasyUIDataGridResult;
import cn.e3mall.common.pojo.EasyUITreeNode;
import cn.e3mall.content.service.ContentCategoryService;
import cn.e3mall.mapper.ContentCategoryMapper;
import cn.e3mall.mapper.ContentMapper;
import cn.e3mall.pojo.Content;
import cn.e3mall.pojo.ContentCategory;
import cn.e3mall.pojo.ContentCategoryExample;
import cn.e3mall.pojo.ContentCategoryExample.Criteria;
import cn.e3mall.pojo.ContentExample;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by ZX on 2017/7/27 15:33.
 */
@Service
public class ContentCategoryServiceImpl implements ContentCategoryService {
    @Autowired
    private ContentCategoryMapper contentCategoryMapper;
    @Autowired
    private ContentMapper contentMapper;

    @Override
    public List<EasyUITreeNode> getContentCatList(long parentId) {
        //根据parentId查询子节点列表
        ContentCategoryExample example = new ContentCategoryExample();
        Criteria criteria = example.createCriteria();
        criteria.andParentIdEqualTo(parentId);
        List<ContentCategory> list = contentCategoryMapper.selectByExample(example);
        List<EasyUITreeNode> result = new ArrayList<>();
        for (ContentCategory c : list) {
            EasyUITreeNode node = new EasyUITreeNode();
            node.setId(c.getId());
            node.setText(c.getName());
            node.setState(c.getIsParent() ? "closed" : "open");
            result.add(node);
        }
        return result;
    }

    @Override
    public E3Result addContentCategory(Long parentId, String name) {
        ContentCategory category = new ContentCategory();
        category.setName(name);
        category.setParentId(parentId);
        category.setCreated(new Date());
        category.setUpdated(new Date());
        //1-正常,2-删除
        category.setStatus(1);
        //暂时没用,默认为1
        category.setSortOrder(1);
        //新增的节点一定为叶子节点
        category.setIsParent(false);
        contentCategoryMapper.insert(category);
        //将父节点isParent改为true
        ContentCategory parent = contentCategoryMapper.selectByPrimaryKey(parentId);
        if (parent!=null && !parent.getIsParent()){
            parent.setIsParent(true);
            parent.setUpdated(new Date());
        }
        contentCategoryMapper.updateByPrimaryKey(parent);
        return new E3Result(category);
    }

    @Override
    public EasyUIDataGridResult getContentList(long categoryId, int page, int rows) {
        PageHelper.startPage(page,rows);
        ContentExample example = new ContentExample();
        ContentExample.Criteria criteria = example.createCriteria();
        criteria.andCategoryIdEqualTo(categoryId);
        List<Content> list = contentMapper.selectByExample(example);

        EasyUIDataGridResult result = new EasyUIDataGridResult();
        result.setRows(list);
        //分页结果
        PageInfo<Content> pageInfo = new PageInfo<Content>(list);
        result.setTotal(pageInfo.getTotal());
        return result;
    }
}
