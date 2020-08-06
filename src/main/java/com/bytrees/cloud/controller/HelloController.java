package com.bytrees.cloud.controller;

import com.bytrees.cloud.response.BaseResponse;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/hello")
public class HelloController {
    @GetMapping("/say")
    public BaseResponse<String> sayHello() {
        return BaseResponse.success("hello world!!");
    }
}
