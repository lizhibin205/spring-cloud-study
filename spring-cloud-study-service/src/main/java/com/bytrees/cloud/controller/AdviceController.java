package com.bytrees.cloud.controller;

import com.bytrees.cloud.response.BaseResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class AdviceController {
    private static final Logger logger = LoggerFactory.getLogger(AdviceController.class);

    @ExceptionHandler(Exception.class)
    public BaseResponse<String> onException(Exception e) {
        logger.error("AdviceController.onException", e);
        return BaseResponse.fail(e.getMessage());
    }
}
