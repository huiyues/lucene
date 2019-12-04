package com.imooc.sell.exception;

import com.imooc.sell.enumCode.ResultEnum;
import lombok.Getter;

/**
 * @author heiye
 * @version 1.0.0
 * @ClassName SellException.java
 * @createTime 2019年11月07日 20:40:00
 */
@Getter
public class SellException extends RuntimeException{

    private Integer code;

    public SellException(ResultEnum resultEnum) {
        super(resultEnum.getMessage());
        this.code = resultEnum.getCode();
    }

    public SellException( Integer code,String message) {
        super(message);
        this.code = code;
    }
}
