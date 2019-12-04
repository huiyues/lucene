package com.imooc.sell.repository;

import com.imooc.sell.pojo.ProductCategory;
import com.imooc.sell.pojo.ProductInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

/**
 * @author heiye
 * @version 1.0.0
 * @ClassName ProductInfoRepository.java
 * @createTime 2019年11月02日 16:28:00
 */
public interface ProductInfoRepository extends JpaRepository<ProductInfo,String>, JpaSpecificationExecutor<ProductInfo> {

    /**
     * 查询上架商品
     * @param status
     * @return
     */
    List<ProductInfo> findByProductStatus(Integer status);

    /**
     * 扣减库存
     */
    @Modifying
    @Query("update ProductInfo set productStock = ?1 where productId = ?2")
    void updateStack(Integer stack,Integer productId);
}
