package com.imooc.sell.repository;

import com.imooc.sell.pojo.OrderDetail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.List;

/**
 * @author heiye
 * @version 1.0.0
 * @ClassName OrderDetailRepository.java
 * @createTime 2019年11月04日 20:30:00
 */
public interface OrderDetailRepository extends JpaRepository<OrderDetail,String>, JpaSpecificationExecutor<OrderDetail> {

    /**
     * 根据订单id查询所有的订单详情
     * @param orderId
     * @return
     */
    List<OrderDetail> findByOrderId(String orderId);
}
