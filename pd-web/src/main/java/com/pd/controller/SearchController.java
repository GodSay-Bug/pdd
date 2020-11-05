package com.pd.controller;

import com.pd.pojo.Item;
import com.pd.service.SearchService;
import lombok.extern.slf4j.Slf4j;
import org.apache.solr.client.solrj.SolrServerException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.io.IOException;
import java.util.List;

/**
 * @Author WL
 * @Date 2020-11-5 14:53
 * @Version 1.0
 */
@Controller
@Slf4j
public class SearchController {

    @Autowired
    private SearchService searchService;

    //http://localhost/search/toSearch.html?key=dsad
    @GetMapping("/search/toSearch.html")
    public String search(String key, Model model) throws IOException, SolrServerException {
        List<Item> search = null;
            search = searchService.search(key);
            model.addAttribute("list", search);
        return "/search.jsp";
    }
}
