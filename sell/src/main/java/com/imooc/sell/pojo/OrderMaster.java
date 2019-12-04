package com.imooc.sell.pojo;

import com.imooc.sell.enumCode.OrderStatusEnum;
import com.imooc.sell.enumCode.PayStatusEnum;
import lombok.Data;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.math.BigDecimal;
import java.util.Date;

/**
 * @author heiye
 * @version 1.0.0
 * @ClassName OrderMasterService.java
 * @createTime 2019年11月03日 11:59:00
 */
@Entity
@Table(name = "order_master")
@Data
@DynamicUpdate
public class OrderMaster {

    /**订单id*/
    @Id
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

    /**支付状态 0 未支付， 1 已支付*/
    private Integer payStatus = PayStatusEnum.WAIT.getCode();

    /**创建时间*/
    private Date createTime;

    /**更新时间*/
    private Date updateTime;

}
