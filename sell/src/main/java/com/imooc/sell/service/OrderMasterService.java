package com.imooc.sell.service;

import com.imooc.sell.VO.ResultVo;
import com.imooc.sell.dto.CartDTO;
import com.imooc.sell.dto.OrderDTO;
import com.imooc.sell.enumCode.OrderStatusEnum;
import com.imooc.sell.enumCode.PayStatusEnum;
import com.imooc.sell.enumCode.ResultEnum;
import com.imooc.sell.exception.SellException;
import com.imooc.sell.pojo.OrderDetail;
import com.imooc.sell.pojo.OrderMaster;
import com.imooc.sell.pojo.ProductInfo;
import com.imooc.sell.repository.OrderDetailRepository;
import com.imooc.sell.repository.OrderMasterRepository;
import com.imooc.sell.repository.ProductInfoRepository;
import com.imooc.sell.util.IdWorker;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author heiye
 * @version 1.0.0
 * @ClassName OrderMasterService.java
 * @createTime 2019年11月04日 20:39:00
 */
@Service
@Slf4j
public class OrderMasterService {

    @Autowired
    private OrderMasterRepository orderMasterRepo;

    @Autowired
    private IdWorker idWorker;

    @Autowired
    private ProductInfoRepository productInfoRepo;

    @Autowired
    private OrderDetailRepository orderDetailRepo;

    @Autowired
    private ProductInfoService productInfoService;

    /**
     * 下单操作
     *
     * @param orderMaster
     */
    @Transactional(rollbackFor = Exception.class)
    public OrderDTO createOrder(OrderDTO orderDTO) {
        //生成订单ID
        OrderMaster orderMaster = new OrderMaster();
        String orderId = idWorker.nextId() + "";

        BigDecimal bigDecimal = new BigDecimal(0);

        //查询是否存在对应的商品
        List<OrderDetail> orderDetailList = orderDTO.getOrderDetailList();

        //扣减库存
        List<CartDTO> cartDTOList = orderDetailList.stream().map(e ->
                new CartDTO(e.getProductId(), e.getProductQuantity())).collect(Collectors.toList());
        productInfoService.decreaseStack(cartDTOList);

        for (OrderDetail orderDetail : orderDetailList) {
            ProductInfo productInfo = productInfoRepo.findById(orderDetail.getProductId()).get();
            if (productInfo == null) {
                throw new SellException(ResultEnum.PRODUCT_NOEXIT);
            }
            //计算商品总价
            bigDecimal = productInfo.getProductPrice().multiply(new BigDecimal(orderDetail.getProductQuantity())).add(bigDecimal);

            //保存订单详情
            orderDetail.setOrderId(orderId);
            orderDetail.setDetailId(idWorker.nextId() + "");
            BeanUtils.copyProperties(productInfo, orderDetail);
            orderDetailRepo.save(orderDetail);
        }
        orderDTO.setOrderId(orderId);
        orderDTO.setOrderAmount(bigDecimal);
        orderDTO.setOrderStatus(OrderStatusEnum.NEWORDER.getCode());
        orderDTO.setPayStatus(PayStatusEnum.WAIT.getCode());
        BeanUtils.copyProperties(orderDTO, orderMaster);

        //保存订单
        orderMasterRepo.save(orderMaster);
        return orderDTO;
    }

    /**
     * 订单修改
     *
     * @param orderMaster
     * @param id
     */
    public void update(OrderMaster orderMaster, String id) {
        orderMaster.setOrderId(id);
        orderMasterRepo.save(orderMaster);
    }

    /**
     * 修改订单支付状态
     */
    public void payOrder(Integer status, String orderId) {
        orderMasterRepo.updatePayStatus(status, orderId);
    }

    /**
     * 修改订单状态
     *
     * @param status
     * @param orderId
     */
    public void updateOrderStatus(Integer status, String orderId) {
        orderMasterRepo.updateOrderStatus(status, orderId);
    }

    /**
     * 查询单个订单详情
     * @param orderId
     * @return
     */
    public OrderDTO findByOrderId(String openId,String orderId){
        //查询订单
        OrderMaster orderMaster = orderMasterRepo.findByBuyerOpenidAndOrderId(openId,orderId);
        if (orderMaster == null){
            throw new SellException(ResultEnum.ORDER_NOT_EXIT);
        }
        OrderDTO orderDTO = new OrderDTO();
        BeanUtils.copyProperties(orderMaster,orderDTO );

        //查询订单详情
        List<OrderDetail> orderDetailList = orderDetailRepo.findByOrderId(orderMaster.getOrderId());
        if (CollectionUtils.isEmpty(orderDetailList)){
            throw new SellException(ResultEnum.ORDERDETAIL_NOT_EXIT);
        }
        orderDTO.setOrderDetailList(orderDetailList);
        return orderDTO;
    }

    /**
     * 查询订单列表
     * @return
     */
    public Page<OrderDTO> findOrderList(String buyerOpenId,Integer page,Integer size){
        if (page <= 0){
            page = 1;
        }
        if (size < 0){
            size = 10;
        }
        Pageable pageable = PageRequest.of(page -1 , size);
        Page<OrderMaster> orderMasterPage =  orderMasterRepo.findByBuyerOpenid(buyerOpenId,pageable);

        //将数据封装到orderDTO
        List<OrderDTO> orderDTOList = new ArrayList<>();
        List<OrderMaster> content = orderMasterPage.getContent();
        for (OrderMaster orderMaster : content) {
            OrderDTO orderDTO = new OrderDTO();
            BeanUtils.copyProperties(orderMaster, orderDTO);
            orderDTOList.add(orderDTO);
        }
        Page<OrderDTO> orderDTOPage = new PageImpl<OrderDTO>(orderDTOList, pageable,orderMasterPage.getTotalElements());
        return orderDTOPage;
    }

    /**
     * 查询订单列表（卖家端）
     * @return
     */
    public Page<OrderDTO> findAll(Integer page,Integer size){
        if (page <= 0){
            page = 1;
        }
        if (size < 0){
            size = 10;
        }
        Pageable pageable = PageRequest.of(page -1 , size);
        Page<OrderMaster> orderMasterPage =  orderMasterRepo.findAll(pageable);

        //将数据封装到orderDTO
        List<OrderDTO> orderDTOList = new ArrayList<>();
        List<OrderMaster> content = orderMasterPage.getContent();
        for (OrderMaster orderMaster : content) {
            OrderDTO orderDTO = new OrderDTO();
            BeanUtils.copyProperties(orderMaster, orderDTO);
            orderDTOList.add(orderDTO);
        }
        Page<OrderDTO> orderDTOPage = new PageImpl<OrderDTO>(orderDTOList, pageable,orderMasterPage.getTotalElements());
        return orderDTOPage;
    }

    /**
     * 取消订单
     * @param orderDTO
     * @return
     */
    @Transactional(rollbackFor = Exception.class)
    public void cancl(String openId,String orderId){
        //判断当前订单的状态
        OrderDTO orderDTO = findByOrderId(openId, orderId);
        if (!orderDTO.getOrderStatus().equals(OrderStatusEnum.NEWORDER.getCode())){
            throw new SellException(ResultEnum.ORDER_STATUS_INCORRECT);
        }

        //修改订单的状态
        OrderMaster orderMaster = new OrderMaster();
        orderDTO.setOrderStatus(OrderStatusEnum.CANCL.getCode());
        BeanUtils.copyProperties(orderDTO,orderMaster );
        OrderMaster saveOrderMaster = orderMasterRepo.save(orderMaster);
        if (saveOrderMaster == null){
            throw new SellException(ResultEnum.ORDER_CANCL_FAIL);
        }

        //回滚库存
        List<OrderDetail> orderDetailList = orderDTO.getOrderDetailList();
        if (CollectionUtils.isEmpty(orderDetailList)){
            throw new SellException(ResultEnum.ORDERDETAIL_NOT_EXIT);
        }
        List<CartDTO> cartDTOList = orderDetailList.stream().map(e->
                new CartDTO(e.getProductId(), e.getProductQuantity())
                ).collect( Collectors.toList() );
        productInfoService.increaseStack(cartDTOList);

        //判断是否支付，退款
        if (orderDTO.getPayStatus().equals(PayStatusEnum.SUCCESS)){
            //TODO
            log.error("退款操作尚未实现，后续支付功能开发实现" );
        }
    }

    /**
     * 订单完结
     * @param orderDTO
     * @return
     */
    @Transactional(rollbackFor = Exception.class)
    public void finish(String orderId){
        //判断订单的状态
        OrderMaster orderDTO = findById(orderId);
        if (!orderDTO.getOrderStatus().equals(OrderStatusEnum.NEWORDER.getCode())){
            throw new SellException(ResultEnum.ORDER_STATUS_INCORRECT);
        }

        //修改订单的状态
        orderDTO.setOrderStatus(OrderStatusEnum.FINISH.getCode());
        OrderMaster save = orderMasterRepo.save(orderDTO);
        if (save == null){
            throw new SellException(ResultEnum.ORDER_CANCL_FAIL);
        }
    }

    /**
     * 支付订单
     * @param orderDTO
     * @return
     */
    @Transactional(rollbackFor = Exception.class)
    public OrderDTO paid(OrderDTO orderDTO){
        //判断订单的状态
        if (!orderDTO.getOrderStatus().equals(OrderStatusEnum.NEWORDER)){
            throw new SellException(ResultEnum.ORDER_STATUS_INCORRECT);
        }

        //判断订单的支付状态
        if (!orderDTO.getPayStatus().equals(PayStatusEnum.WAIT)){
            throw new SellException(ResultEnum.ORDER_PAY_SUCCESS);
        }

        //修改订单的支付状态
        OrderMaster orderMaster = new OrderMaster();
        orderDTO.setPayStatus(PayStatusEnum.SUCCESS.getCode());
        BeanUtils.copyProperties(orderDTO,orderMaster );
        OrderMaster save = orderMasterRepo.save(orderMaster);
        if (save == null){
            throw new SellException(ResultEnum.ORDER_CANCL_FAIL);
        }

        return orderDTO;
    }


    /**
     * 根据id查询订单
     */
    public OrderMaster findById(String orderId){
        OrderMaster orderMaster = orderMasterRepo.findById(orderId).get();
        return orderMaster;
    }
}
