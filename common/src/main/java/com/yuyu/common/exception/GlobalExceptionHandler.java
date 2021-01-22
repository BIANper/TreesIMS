package com.yuyu.common.exception;


import com.yuyu.common.utils.ErrorCodeEnum;
import com.yuyu.common.utils.R;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;
import java.util.HashMap;
import java.util.Map;

@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(SQLException.class)
    public R handlesqlException(SQLException e) {
        if (e instanceof SQLIntegrityConstraintViolationException) {
            return R.error("该数据有关联数据，操作失败!");
        }
        return R.error("数据库异常，操作失败!");
    }

/*    @ExceptionHandler(value = {Throwable.class})
    public R handleException(Throwable throwable) {
        throwable.printStackTrace();
        return R.error(ErrorCodeEnum.UNKNOWN_EXCEPTION.getCode(), ErrorCodeEnum.UNKNOWN_EXCEPTION.getMessage());
    }*/

    @ExceptionHandler(value = {MethodArgumentNotValidException.class})
    public R handleValidException(MethodArgumentNotValidException e) {
        BindingResult result = e.getBindingResult();
        Map<String,String> map = new HashMap<>();
        result.getFieldErrors().forEach((item)->{
            String message = item.getDefaultMessage();
            String field = item.getField();
            map.put(field,message);
        });
        return R.error(ErrorCodeEnum.VALID_EXCEPTION.getCode(), ErrorCodeEnum.VALID_EXCEPTION.getMessage()).put("data",map);
    }
}
