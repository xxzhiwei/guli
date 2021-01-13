package com.atshixin.base.exceptionHandler;

import com.atshixin.util.R;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
@Slf4j
public class GlobalExceptionHandler {

    @ExceptionHandler(Exception.class) // 指定哪些异常使用该方法处理
    @ResponseBody
    @ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
    public R exceptionHandler(Exception e) {
        log.error(e.getMessage());
        e.printStackTrace();
        return R.error().message("服务器错误。。");
    }

    @ExceptionHandler(GuliException.class)
    @ResponseBody
    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    public R exceptionHandler(GuliException e) {
        log.error(e.getMessage());
        e.printStackTrace();
        return R.error().message(e.getMessage());
    }
}
