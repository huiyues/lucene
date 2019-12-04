package com.imooc.sell.Handler;

import com.imooc.sell.exception.SellerAuthorizeException;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

@Component
@Aspect
public class HandlerAuthorizeAspect {

    @Autowired
    private RedisTemplate redisTemplate;

    @Pointcut("execution(public * com.imooc.sell.controller.Seller*.*(..))"+
    "&& !execution(public * com.imooc.sell.controller.SellerUserController.*(..))")
    public void verify(){}

    @Before("verify()")
    public void before() {
        //获取所有请求信息
        ServletRequestAttributes requestAttributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = requestAttributes.getRequest();

        //获取cookie
        Cookie[] cookies = request.getCookies();
        if (cookies == null){
            throw new SellerAuthorizeException();
        }

        //验证redis
        String token = (String) redisTemplate.opsForValue().get("token_");
        if (StringUtils.isEmpty(token)){
            throw new SellerAuthorizeException();
        }
    }
}
