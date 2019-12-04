package com.imooc.sell.dto;

import lombok.Data;

/**
 * @author heiye
 * @version 1.0.0
 * @ClassName CartDTO.java
 * @createTime 2019年11月09日 10:28:00
 */
@Data
public class CartDTO {

    private String productId;

    private Integer productQuantity;

    public CartDTO(String productId, Integer productQuantity) {
        this.productId = productId;
        this.productQuantity = productQuantity;
    }
}
