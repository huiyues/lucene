package com.imooc.sell.controller;

import com.imooc.sell.pojo.SellerInfo;
import com.imooc.sell.pojo.Token;
import com.imooc.sell.service.SellerInfoService;
import com.imooc.sell.util.HttpClient;
import lombok.extern.slf4j.Slf4j;
import org.apache.catalina.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import com.alibaba.fastjson.JSON;
import org.springframework.web.servlet.ModelAndView;


import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Date;
import java.util.Map;

/**
 * @author heiye
 * @version 1.0.0
 * @ClassName WeiXinController.java
 * @createTime 2019年11月10日 15:15:00
 */
@Controller
@Slf4j
public class WeiXinController {

    private static final String TOKEN_URL =
            "https://api.weixin.qq.com/sns/oauth2/access_token?appid=wx7287a60bb700fd21&secret=1ef8755f92bebae8ad7bab432ba29cbf&code=";

    private static final String USER_URL = "https://api.weixin.qq.com/sns/userinfo?access_token=";

    @Autowired
    private SellerInfoService sellerInfoServicel;

    @GetMapping("/loginServlet")
    public void auth(HttpServletRequest request, HttpServletResponse response, Model model) {
        // 获取到code值
        String code = request.getParameter("code");
        // 判断
        if (code == null) {
            throw new RuntimeException("用户禁止授权...");
        }

        try {
            // 获取到了code值，回调没有问题
            // 定义地址，获取到code向微信验证用户是否同意登录
            String token_url = TOKEN_URL + code + "&grant_type=authorization_code";
            // 发送请求
            HttpClient client = new HttpClient(token_url);
            // 发送get请求
            client.get();
            // 获取到请求的结果  json格式的字符串，把json格式的字符串转换成对象或者Map集合
            String token_content = client.getContent();
            // 把json字符串转换成对象
            Token token = JSON.parseObject(token_content, Token.class);

            // 获取到接口调用凭证
            // 获取个人的信息，用户同意登录获取用户的个人信息
            String user_url = USER_URL + token.getAccess_token() + "&openid=" + token.getOpenid();
            HttpClient client1 = new HttpClient(user_url);
            client1.get();
            String user_content = client1.getContent();
            // 解析json字符串
            Map<String, String> map = JSON.parseObject(user_content, Map.class);
            SellerInfo sellerInfoTwo = sellerInfoServicel.findByOpenId(map.get("openid"));
            SellerInfo sellerInfo = new SellerInfo();
            if (sellerInfoTwo == null) {
                sellerInfo.setOpenId(map.get("openid"));
                sellerInfo.setUsername(map.get("nickname") + "1");
                sellerInfo.setPassword("123");
                sellerInfo.setIcon(map.get("headimgurl"));

                //存入数据库
                if (sellerInfo != null) {
                    sellerInfoServicel.save(sellerInfo);
                }
            }

            response.sendRedirect(request.getContextPath() + "/seller/user/login?openId=" + map.get("openid"));

        } catch (Exception e) {
            e.printStackTrace();
            System.out.println(e.getMessage());
        }
    }
}
