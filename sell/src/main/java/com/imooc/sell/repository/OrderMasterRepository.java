package com.imooc.sell.repository;

import com.imooc.sell.pojo.OrderMaster;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

/**
 * @author heiye
 * @version 1.0.0
 * @ClassName OrderMasterRepository.java
 * @createTime 2019年11月04日 20:29:00
 */
public interface OrderMasterRepository extends JpaRepository<OrderMaster,String>, JpaSpecificationExecutor<OrderMaster> {

    /**
     * 根据下单人查询对应订单并分页
     * @param openId
     * @return
     */
    Page<OrderMaster> findByBuyerOpenid(String openId, Pageable pageable);

    /**
     * 修改订单支付状态
     */
    @Query("update OrderMaster set payStatus = ?1 where orderId = ?2")
    @Modifying
    void updatePayStatus(Integer status,String orderId);

    /**
     * 修改订单状态
     * @param status
     * @param orderId
     */
    @Modifying
    @Query("update OrderMaster set orderStatus = ?1 where orderId = ?2")
    void updateOrderStatus(Integer status,String orderId);

    /**
     * 查询订单详情
     * @param openId
     * @param orderId
     * @return
     */
    OrderMaster findByBuyerOpenidAndOrderId(String openId,String orderId);
}
