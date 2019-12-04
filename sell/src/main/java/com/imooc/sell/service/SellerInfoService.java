package com.imooc.sell.service;

import com.imooc.sell.pojo.SellerInfo;
import com.imooc.sell.repository.SellerInfoRepository;
import com.imooc.sell.util.IdWorker;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author heiye
 * @version 1.0.0
 * @ClassName SellerInfoService.java
 * @createTime 2019年11月19日 21:01:00
 */
@Service
@Slf4j
public class SellerInfoService {

    @Autowired
    private SellerInfoRepository sellerInfoRepository;

    @Autowired
    private IdWorker idWorker;

    /**
     * 根据id查询用户的信息
     * @param openId
     * @return
     */
    public SellerInfo findByOpenId(String openId){
       return sellerInfoRepository.findByOpenId(openId);
    }

    /**
     * 新增用户
     * @param sellerInfo
     */
    public void save(SellerInfo sellerInfo){
        sellerInfo.setSellerId(idWorker.nextId()+"");
        sellerInfoRepository.save(sellerInfo);
    }
}
