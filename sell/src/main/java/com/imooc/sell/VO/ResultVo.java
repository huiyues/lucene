package com.imooc.sell.VO;

import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author heiye
 * @version 1.0.0
 * @ClassName ResultVo.java
 * @createTime 2019年11月02日 17:40:00
 */
@Data
@NoArgsConstructor
public class ResultVo<T> {

    /** 状态码*/
    private Integer code;

    /**提示信息*/
    private String message;

    /**数据*/
    private T data;

    public ResultVo(Integer code, String message, T data) {
        this.code = code;
        this.message = message;
        this.data = data;
    }

    public ResultVo(Integer code, String message) {
        this.code = code;
        this.message = message;
    }
}
