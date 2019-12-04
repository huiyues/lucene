package com.imooc.sell.service;

import com.imooc.sell.pojo.OrderDetail;
import com.imooc.sell.repository.OrderDetailRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author heiye
 * @version 1.0.0
 * @ClassName OrderDetailService.java
 * @createTime 2019年11月04日 20:39:00
 */
@Service
public class OrderDetailService {

    @Autowired
    private OrderDetailRepository orderDetailRepo;

    /**
     * 根据订单id查询订单详情
     * @param orderId
     * @return
     */
    public List<OrderDetail> findByOrderId(String orderId){
        return orderDetailRepo.findByOrderId(orderId);
    }
}
