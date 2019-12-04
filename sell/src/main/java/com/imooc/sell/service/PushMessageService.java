package com.imooc.sell.service;

import me.chanjar.weixin.common.exception.WxErrorException;
import me.chanjar.weixin.mp.api.WxMpService;
import me.chanjar.weixin.mp.api.WxMpServiceImpl;
import me.chanjar.weixin.mp.bean.WxMpTemplateData;
import me.chanjar.weixin.mp.bean.WxMpTemplateMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

@Service
public class PushMessageService {

    public void orderMessage(){
        WxMpTemplateMessage templateMessage = new WxMpTemplateMessage();
        templateMessage.setTemplateId("4oYJ_ZHIR1ps9jzgnx3htexjMiZ6axmqtc0A71jFqW4");
        templateMessage.setTopColor("o_4rV1LZnGStGNoaIdDRPuMD-o2s");

        List<WxMpTemplateData> list = Arrays.asList(
                new WxMpTemplateData("keyword1","阿里巴巴"),
                new WxMpTemplateData("keyword2","2019-11-1"),
                new WxMpTemplateData("keyword3","2019-11-1"),
                new WxMpTemplateData("keyword4","火箭"),
                new WxMpTemplateData("keyword5","3453245675467fd343"),
                new WxMpTemplateData("keyword6","99999"),
                new WxMpTemplateData("keyword7","完结")
        );
        templateMessage.setDatas(list);

        try {
            WxMpServiceImpl wxMpService = new WxMpServiceImpl();
            wxMpService.templateSend(templateMessage);
        } catch (WxErrorException e) {
            e.printStackTrace();
        }
    }
}
