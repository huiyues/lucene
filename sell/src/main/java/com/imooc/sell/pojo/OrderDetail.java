package com.imooc.sell.pojo;

import lombok.Data;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.math.BigDecimal;

/**
 * @author heiye
 * @version 1.0.0
 * @ClassName OrderDetail.java
 * @createTime 2019年11月04日 20:23:00
 */
@Entity
@Table(name = "order_detail")
@Data
@DynamicUpdate
public class OrderDetail {

    /**订单详情ID*/
    @Id
    private String detailId;

    /**订单ID*/
    private String orderId;

    /**商品ID*/
    private String productId;

    /**商品名称*/
    private String productName;

    /**商品价格*/
    private BigDecimal productPrice;

    /**商品数量*/
    private Integer productQuantity;

    /**商品小图*/
    private String productIcon;
}
