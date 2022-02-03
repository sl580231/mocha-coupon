package com.mocha.coupon.advice;

import com.mocha.coupon.exception.CouponException;
import com.mocha.coupon.vo.CommonResponse;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.servlet.http.HttpServletRequest;

/**
 * <h1>全局异常处理</h1>
 */
@RestControllerAdvice
public class GlobalExceptionAdvice {

    @ExceptionHandler(value = CouponException.class)
    public CommonResponse<String> handlerCouponException(
            HttpServletRequest req, CouponException ex
            ){
        CommonResponse<String> response = new CommonResponse<>(
          -1,"business error"
        );
        response.setData(ex.getMessage());
        return response;
    }
}
