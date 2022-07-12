package com.example.gulimall.search.controller;

import com.example.gulimall.common.utils.Result;
import com.example.gulimall.search.service.IMallSearchService;
import com.example.gulimall.search.vo.SearchParam;
import com.example.gulimall.search.vo.SearchResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SearchController {

    @Autowired
    IMallSearchService mallSearchService;

    @GetMapping("/search")
    public Result<SearchResult> search(SearchParam searchParam) {
        SearchResult search = mallSearchService.search(searchParam);
        return new Result<SearchResult>().ok(search);
    }
}
