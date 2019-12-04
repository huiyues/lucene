package com.imooc.sell.repository;

import com.imooc.sell.pojo.SellerInfo;
import net.bytebuddy.implementation.bytecode.assign.TypeCasting;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author heiye
 * @version 1.0.0
 * @ClassName SellerInfoRepository.java
 * @createTime 2019年11月19日 20:53:00
 */
public interface SellerInfoRepository extends JpaRepository<SellerInfo, String> {

    /**
     * 根据openID查询用户信息
     * @return
     */
    SellerInfo findByOpenId(String openId);
}
