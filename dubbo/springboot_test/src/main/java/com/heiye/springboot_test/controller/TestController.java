package com.heiye.springboot_test.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.HashMap;
import java.util.Map;

/**
 * @author heiye
 * @version 1.0.0
 * @ClassName TestController.java
 * @createTime 2019年11月11日 21:16:00
 */
@Controller
public class TestController {

    @RequestMapping("/test")
    public String test(Model model){
        Map map = new HashMap();
        map.put("name", "love");
        model.addAllAttributes(map);
        return "order/index";
    }
}
