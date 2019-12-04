package com.imooc.sell.controller;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.imooc.sell.VO.ResultVo;
import com.imooc.sell.dto.OrderDTO;
import com.imooc.sell.enumCode.ResultEnum;
import com.imooc.sell.exception.SellException;
import com.imooc.sell.form.OrderForm;
import com.imooc.sell.pojo.OrderDetail;
import com.imooc.sell.pojo.OrderMaster;
import com.imooc.sell.service.OrderMasterService;
import com.imooc.sell.service.WebSocket;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author heiye
 * @version 1.0.0
 * @ClassName BuyerOrderController.java
 * @createTime 2019年11月09日 16:50:00
 */
@RestController
@RequestMapping("/buyer/order")
@Slf4j
public class BuyerOrderController {

    @Autowired
    private OrderMasterService orderMasterService;

    /**
     * 创建订单
     *
     * @param orderForm
     * @return
     */
    @RequestMapping("/create")
    public ResultVo<Map> createOrder(@Valid OrderForm orderForm, BindingResult bindingResult) {

        //效验数据
        if (bindingResult.hasErrors()) {
            log.error("【创建订单】参数不合法,orderForm={}", orderForm);
            throw new SellException(ResultEnum.PARAMETER_INCORRECT.getCode(), bindingResult.getFieldError().getDefaultMessage());
        }

        OrderDTO orderDTO = new OrderDTO();
        BeanUtils.copyProperties(orderForm, orderDTO);
        //参数转换
        List<OrderDetail> orderDetailList = new ArrayList<>();
        Gson gson = new Gson();
        try {
            orderDetailList = gson.fromJson(orderForm.getItems(), new TypeToken<List<OrderDetail>>() {
            }.getType());
        } catch (Exception e) {
            e.printStackTrace();
            log.error("【对象转换】错误，orderForm={}", orderForm.getItems());
            throw new SellException(ResultEnum.PARAMETER_INCORRECT);
        }
        orderDTO.setOrderDetailList(orderDetailList);

        OrderDTO order = orderMasterService.createOrder(orderDTO);
        Map<String,String> map = new HashMap<>();
        map.put("orderId", order.getOrderId());

        //发送消息
        WebSocket.senMessage("你有新的订单");

        return new ResultVo<>(0,"创建成功" ,map );
    }

    /**
     * 查询订单列表
     * @param openId
     * @param page
     * @param size
     * @return
     */
    @GetMapping("/list")
    @Cacheable(cacheNames = "order",key = "orderList")
    public ResultVo<List<OrderDTO>> findOrderList(@RequestParam("openId")String openId,
                                                  @RequestParam("page")Integer page,@RequestParam("size")Integer size){
        if (StringUtils.isEmpty(openId)){
            log.error("【订单列表】参数错误,openId={}",openId );
            throw new SellException(ResultEnum.PARAMETER_INCORRECT);
        }
        Page<OrderDTO> orderList = orderMasterService.findOrderList(openId, page, size);
        return new ResultVo<>(0,"查询成功" ,orderList.getContent() );
    }

    /**
     * 查询订单详情
     * @param openId
     * @param orderId
     * @return
     */
    @GetMapping("/detail")
    public ResultVo<OrderDTO> findOrderDetail(@RequestParam("openId")String openId,@RequestParam("orderId")String orderId){
        if (StringUtils.isEmpty(openId) && StringUtils.isEmpty(orderId)){
            log.error("【订单详情】参数错误,openId={},orderId={}",openId,orderId );
            throw new SellException(ResultEnum.PARAMETER_INCORRECT);
        }

        OrderDTO orderDTO = orderMasterService.findByOrderId(openId, orderId);
        return new ResultVo<>(0,"查询成功" ,orderDTO );
    }

    /**
     * 订单取消
     * @param openId
     * @param orderId
     * @return
     */
    @GetMapping("cancel")
    @CacheEvict(cacheNames = "order",key = "orderList")
    public ResultVo cancel(@RequestParam("openId")String openId,@RequestParam("orderId")String orderId){
        if (StringUtils.isEmpty(openId) && StringUtils.isEmpty(orderId)){
            log.error("【订单取消】参数错误,openId={},orderId={}",openId,orderId );
            throw new SellException(ResultEnum.PARAMETER_INCORRECT);
        }
        orderMasterService.cancl(openId, orderId);

        return new ResultVo(0,"取消成功" ,null );
    }
}
