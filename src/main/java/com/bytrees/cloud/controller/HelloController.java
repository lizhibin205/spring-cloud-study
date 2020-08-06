package com.bytrees.cloud.controller;

import com.bytrees.cloud.response.BaseResponse;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {
    @GetMapping("/hello")
    public BaseResponse<String> sayHello() {
        return BaseResponse.success("hello world!!");
    }
}
