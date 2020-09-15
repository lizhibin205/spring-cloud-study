package com.bytrees.cloud.controller;

import com.bytrees.cloud.response.BaseResponse;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/kafka")
public class KafkaContoller {
    @GetMapping("/status")
    public BaseResponse<String> status() {
        return BaseResponse.success("hello world!!");
    }
}
