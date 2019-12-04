package com.imooc.sell.enumCode;

import lombok.Getter;

/**
 * @author heiye
 * @version 1.0.0
 * @ClassName ResultEnum.java
 * @createTime 2019年11月07日 20:40:00
 */
@Getter
public enum ResultEnum {

    PRODUCT_NOEXIT(10, "商品不存在"),
    PRODUCT_NOT_STACK(11, "库存不足"),

    ORDER_NOT_EXIT(20,"订单不存在"),
    ORDERDETAIL_NOT_EXIT(21,"订单详情不存在"),
    ORDER_STATUS_INCORRECT(22,"订单状态不合法"),
    ORDER_CANCL_FAIL(23,"更新订单状态失败"),
    ORDER_DETAIL_INCORRECT(24,"订单详情不存在"),
    ORDER_PAY_SUCCESS(25,"订单已支付"),
    PARAMETER_INCORRECT(1,"参数不合法"),
    PRODUCT_UPDATE_ERROR(26,"商品修改失败"),
    CATEGORY_UPDATE_ERROR(27,"类目修改失败"),
    LOGIN_ERROR(27,"登录失败"),
    LOGOUT_SUCCESS(27,"退出成功"),
    ;

    private Integer code;
    private String message;

    ResultEnum(Integer code, String message) {
        this.code = code;
        this.message = message;
    }
}
