package cn.e3mall.search.controller;

import cn.e3mall.common.pojo.SearchResult;
import cn.e3mall.search.service.SearchService;
import org.apache.solr.client.solrj.SolrServerException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.UnsupportedEncodingException;

/**
 * 商品检索
 * Created by ZX on 2017/8/2 10:58.
 */
@Controller
public class SearchController {
    @Value("${SEARCH_ROWS}")
    private int rows;
    @Autowired
    private SearchService searchService;

    @RequestMapping(value = "/search")
    public String search(String keyword,@RequestParam(defaultValue = "1") int page ,Model model) throws SolrServerException, UnsupportedEncodingException {
        keyword = new String(keyword.getBytes("iso-8859-1"),"utf-8");
        SearchResult result = searchService.search(keyword, page, rows);
        model.addAttribute("query",keyword);
        model.addAttribute("totalPages",result.getTotalPages());
        model.addAttribute("page",page);
        model.addAttribute("itemList",result.getItemList());
        model.addAttribute("recordCount",result.getRecordCount());
        return "search";

    }
}
