package com.imooc.sell.controller;

import com.imooc.sell.enumCode.ResultEnum;
import com.imooc.sell.exception.SellException;
import com.imooc.sell.pojo.SellerInfo;
import com.imooc.sell.pojo.Token;
import com.imooc.sell.service.SellerInfoService;
import com.imooc.sell.util.CookieUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

@Controller
@RequestMapping("/seller/user")
public class SellerUserController {

    @Autowired
    private SellerInfoService sellerInfoService;

    @Autowired
    private RedisTemplate redisTemplate;

    //创建token
    private static final String TOKEN = UUID.randomUUID().toString();
    //创建过期时间
    private static final long EXPIRE = 7200L;

    @GetMapping("/login")
    public ModelAndView login(@RequestParam("openId")String openId, HttpServletResponse response){
        //获取openID判断是否存在该用户
        SellerInfo sellerInfo = sellerInfoService.findByOpenId(openId);
        if (sellerInfo == null){
            throw new RuntimeException(ResultEnum.LOGIN_ERROR.getMessage());
        }

        //将token存入到redis
        redisTemplate.opsForValue().set("token_", TOKEN, EXPIRE, TimeUnit.SECONDS);

        //设置cookie的值
        CookieUtils.set(response, TOKEN, EXPIRE);

        //重定向到首页
        return new ModelAndView("redirect:/seller/order/list");
    }

    /**
     * 登出操作
     * @param request
     * @param response
     * @param map
     * @return
     */
    @GetMapping("/logout")
    public ModelAndView logout(HttpServletRequest request, HttpServletResponse response, Map map){
        //清除cookie
        CookieUtils.del(request, response,"token", 0);

        //清除redis
        redisTemplate.opsForValue().getOperations().delete("token_");

        //跳转页面
        map.put("msg", ResultEnum.LOGOUT_SUCCESS.getMessage());
        map.put("url", "/sell/login.html");
        return new ModelAndView("common/success",map);
    }
}
