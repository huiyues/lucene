package com.imooc.sell.form;

import lombok.Data;

import javax.validation.constraints.NotEmpty;

/**
 * @author heiye
 * @version 1.0.0
 * @ClassName OrderForm.java
 * @createTime 2019年11月09日 16:39:00
 */
@Data
public class OrderForm {

    /**
     * 买家姓名
     */
    @NotEmpty(message = "买家必填")
    private String buyerName;

    /**
     * 买家电话号码
     */
    @NotEmpty(message = "买家电话")
    private String buyerPhone;

    /**
     * 买家地址
     */
    @NotEmpty(message = "买家地址")
    private String buyerAddress;

    /**
     * 买家微信
     */
    @NotEmpty(message = "买家微信Id")
    private String buyerOpenid;

    /**
     * 购物车
     */
    @NotEmpty(message = "购物车不能为空")
    private String items;
}
