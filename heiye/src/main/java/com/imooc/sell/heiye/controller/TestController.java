package com.imooc.sell.heiye.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.HashMap;
import java.util.Map;

/**
 * @author heiye
 * @version 1.0.0
 * @ClassName TestController.java
 * @createTime 2019年11月12日 21:14:00
 */
@Controller
public class TestController {

    @GetMapping("/test")
    public ModelAndView test(){
        Map map = new HashMap();
        map.put("name", "heiye");
        return new ModelAndView("order/list");
    }
}
