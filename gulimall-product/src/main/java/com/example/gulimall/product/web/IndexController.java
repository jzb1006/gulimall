package com.example.gulimall.product.web;

import com.example.gulimall.product.service.CategoryService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.ArrayList;
import java.util.List;

/**
 * @author ZhibinJiang on 2022/6/30
 */
@Controller
@Slf4j
public class IndexController {


    @GetMapping({"/","index.html"})
    public String indexPage(){
        return "index";
    }
}
