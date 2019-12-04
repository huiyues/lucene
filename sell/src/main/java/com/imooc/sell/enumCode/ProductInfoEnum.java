package com.imooc.sell.enumCode;

import lombok.Getter;

/**
  * @author:     heiye
  * @UpdateRemark:   工具枚举类
  */
 @Getter
public enum ProductInfoEnum implements CodeEnum{

    UP(0,"上架"),
     DOWN(1,"下架");

    private Integer code;
    private String  message;

     ProductInfoEnum(Integer code, String message) {
         this.code = code;
         this.message = message;
     }
 }
