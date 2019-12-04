package com.imooc.sell.enumCode;

import lombok.Getter;
import org.springframework.data.domain.PageRequest;

/**
 * @author heiye
 * @version 1.0.0
 * @ClassName PayStatusEnum.java
 * @createTime 2019年11月04日 20:19:00
 */
@Getter
public enum  PayStatusEnum implements CodeEnum{

    WAIT(0,"等待支付"),
    SUCCESS(1,"已支付");

    private Integer code;

    private String message;

    PayStatusEnum(Integer code, String message) {
        this.code = code;
        this.message = message;
    }
}
