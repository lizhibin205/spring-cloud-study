package com.bytrees.cloud.controller;

import com.bytrees.cloud.response.BaseResponse;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@RequestMapping("/idGenerator")
public class IdGeneratorController {
    @GetMapping("/getUUID")
    public BaseResponse<String> getUUID() {
        return BaseResponse.success(UUID.randomUUID().toString());
    }

    @GetMapping("/getSystemTime")
    public BaseResponse<Long> getSystemTime() {
        return BaseResponse.success(System.currentTimeMillis());
    }
}
