package com.imooc.sell.enumCode;

import lombok.Getter;

/**
 * @author heiye
 * @version 1.0.0
 * @ClassName OrderStatusEnum.java
 * @createTime 2019年11月04日 20:16:00
 */
@Getter
public enum OrderStatusEnum implements CodeEnum{

    NEWORDER(0, "新订单"),
    FINISH(1, "订单完结"),
    CANCL(3, "取消订单"),
    ERROR(2, "下单失败"),
    ;

    private Integer code;

    private String message;

    OrderStatusEnum(Integer code, String message) {
        this.code = code;
        this.message = message;
    }
}
