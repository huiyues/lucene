package com.imooc.sell.Handler;

import com.imooc.sell.VO.ResultVo;
import com.imooc.sell.enumCode.ResultEnum;
import com.imooc.sell.exception.SellException;
import com.imooc.sell.exception.SellerAuthorizeException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.ModelAndView;

import java.util.Map;

@ControllerAdvice
public class SellerAuthorizeExceptionHandler {

    @ExceptionHandler(value = SellerAuthorizeException.class)
    public ModelAndView exceptionHandler(){
        return new ModelAndView("redirect:/login.html");
    }

    @ExceptionHandler(value = SellException.class)
    @ResponseStatus(HttpStatus.FORBIDDEN)
    public ResultVo sellExceptionHandler(SellException e){
        return new ResultVo(e.getCode(),e.getMessage());
    }
}
