package com.imooc.sell.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.imooc.sell.enumCode.OrderStatusEnum;
import com.imooc.sell.enumCode.PayStatusEnum;
import com.imooc.sell.pojo.OrderDetail;
import com.imooc.sell.util.EnumUtils;
import lombok.Data;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.criterion.Order;

import javax.persistence.Id;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/**
 * @author heiye
 * @version 1.0.0
 * @ClassName OrderDTO.java
 * @createTime 2019年11月06日 20:43:00
 */
@Data
@DynamicUpdate
public class OrderDTO {

    /**订单id*/
    private String orderId;

    /**买家名称*/
    private String buyerName;

    /**买家手机号*/
    private String buyerPhone;

    /**买家地址*/
    private String buyerAddress;

    /**买家微信*/
    private String buyerOpenid;

    /**订单总金额*/
    private BigDecimal orderAmount;

    /**订单状态，默认为新下单*/
    private Integer orderStatus = OrderStatusEnum.NEWORDER.getCode();

    /**支付状态*/
    private Integer payStatus = PayStatusEnum.WAIT.getCode();

    /**创建时间*/
    private Date createTime;

    /**更新时间*/
    private Date updateTime;

    /**订单详情*/
    List<OrderDetail> orderDetailList;

    /**
     * 返回订单状态
     */
    @JsonIgnore
    public OrderStatusEnum getOrderStatusEnum(){
        return EnumUtils.getCode(orderStatus, OrderStatusEnum.class);
    }

    /**
     * 返回支付状态
     */
    @JsonIgnore
    public  PayStatusEnum getPayStatusEnum(){
        return EnumUtils.getCode(payStatus,PayStatusEnum.class );
    }
}
