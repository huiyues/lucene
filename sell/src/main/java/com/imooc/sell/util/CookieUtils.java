package com.imooc.sell.util;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class CookieUtils {

    public static void set(HttpServletResponse response,String token,long expire){
        Cookie cookie = new Cookie("token",token);
        cookie.setPath("/");
        cookie.setMaxAge((int)expire);
        response.addCookie(cookie);
    }

    public static void del(HttpServletRequest request,HttpServletResponse response ,String name, long expire){
        Cookie[] cookies = request.getCookies();
        if (cookies != null){
            for (Cookie cookie : cookies) {
                if (name.equals(cookie.getName())){
                    cookie.setValue(null);
                    cookie.setMaxAge((int) expire);
                    cookie.setPath("/");
                    response.addCookie(cookie);
                }
            }
        }
    }
}
