package com.lcp.blog.exception;

import com.lcp.blog.common.utils.AppException;
import com.lcp.blog.common.utils.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.BindException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@ControllerAdvice
@ResponseBody
@Slf4j
public class AllExceptionHandler {

    /**
     * 处理自定义的业务异常
     * @param e
     * @return
     */
    @ExceptionHandler(value = AppException.class)
    public Result AppExceptionHandler(AppException e){
        return new Result(e.getMessage(), e.getCode());
    }

    /**
     * 处理空指针的异常
     * @param e
     * @return
     */
    @ExceptionHandler(value = NullPointerException.class)
    public Result exceptionHandler(NullPointerException e){
        return new Result(e.getMessage(), AppException.SYSTEM_FAIL);
    }

    /** 数据表单验证失败处理 */
    @ExceptionHandler({BindException.class})
    public Result validExceptionHandler(BindException exception) {
        List<FieldError> fes = exception.getBindingResult().getFieldErrors();
        return new Result(fes.get(0).getDefaultMessage(), AppException.FORM_INVALID);
    }


    /**
     * 处理其他异常
     * @param e
     * @return
     */
    @ExceptionHandler(value =Exception.class)
    public Result exceptionHandler(Exception e){
        return new Result(e.getMessage(), AppException.SYSTEM_FAIL);
    }
}
