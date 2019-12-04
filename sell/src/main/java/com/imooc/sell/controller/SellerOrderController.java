package com.imooc.sell.controller;

import com.imooc.sell.dto.OrderDTO;
import com.imooc.sell.enumCode.ResultEnum;
import com.imooc.sell.exception.SellException;
import com.imooc.sell.pojo.OrderMaster;
import com.imooc.sell.pojo.SellerInfo;
import com.imooc.sell.service.OrderMasterService;
import com.imooc.sell.service.SellerInfoService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.HashMap;
import java.util.Map;

/**
 * @author heiye
 * @version 1.0.0
 * @ClassName SellerOrderController.java
 * @createTime 2019年11月11日 21:24:00
 */
@Controller
@RequestMapping("/seller/order")
@Slf4j
public class SellerOrderController {

    @Autowired
    private OrderMasterService orderMasterService;

    @Autowired
    private SellerInfoService sellerInfoService;

    /**
     * 查询卖家端订单列表
     *
     * @param page
     * @param size
     * @param model
     * @return
     */
    @RequestMapping("/list")
    public ModelAndView findAll(@RequestParam(value = "page", defaultValue = "1") Integer page,
                                @RequestParam(value = "size", defaultValue = "10") Integer size) {
        Page<OrderDTO> orderDTOS = orderMasterService.findAll(page, size);
        Map map = new HashMap();
        map.put("orderPageList", orderDTOS);
        map.put("currentPage", page);
        return new ModelAndView("order/list", map);
    }


    /**
     * 取消订单操作
     *
     * @param orderId
     * @return
     */
    @RequestMapping("/cancel")
    public ModelAndView cancel(@RequestParam(value = "orderId") String orderId) {
        try {
            OrderMaster orderMaster = orderMasterService.findById(orderId);
            orderMasterService.cancl(orderMaster.getBuyerOpenid(), orderMaster.getOrderId());
        } catch (Exception e) {
            Map map = new HashMap();
            map.put("msg", ResultEnum.ORDER_NOT_EXIT.getMessage());
            map.put("url", "/sell/seller/order/list");
            return new ModelAndView("common/error", map);
        }

        Map map = new HashMap();
        map.put("msg", "取消成功");
        map.put("url", "/sell/seller/order/list");
        return new ModelAndView("common/success", map);
    }

    /**
     * 查询订单详情
     *
     * @return
     */
    @RequestMapping("/detail")
    public ModelAndView detail(@RequestParam("orderId") String orderId, Map map) {
        OrderDTO orderDTO = null;
        try {
            OrderMaster orderMaster = orderMasterService.findById(orderId);
            orderDTO = orderMasterService.findByOrderId(orderMaster.getBuyerOpenid(), orderMaster.getOrderId());
        } catch (Exception e) {
            map.put("msg", e.getMessage());
            map.put("url", "common/error");
            return new ModelAndView("order/detail", map);
        }
        map.put("orderDTO", orderDTO);
        return new ModelAndView("order/detail", map);
    }

    /**
     * 订单完结
     * @param orderId
     * @param map
     * @return
     */
    @RequestMapping("/finish")
    public ModelAndView finish(@RequestParam(value = "orderId") String orderId,Map map) {
        try {
            OrderMaster orderMaster = orderMasterService.findById(orderId);
            orderMasterService.finish(orderMaster.getOrderId());
        } catch (Exception e) {
            map.put("msg",e.getMessage());
            map.put("url", "/sell/seller/order/detail?orderId="+orderId);
            return new ModelAndView("common/error", map);
        }

        map.put("msg", "订单完结");
        map.put("url", "/sell/seller/order/detail?orderId="+orderId);
        return new ModelAndView("common/success", map);
    }
}
