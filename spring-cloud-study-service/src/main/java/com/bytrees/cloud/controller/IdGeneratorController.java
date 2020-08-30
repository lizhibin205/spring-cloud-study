package com.bytrees.cloud.controller;

import com.bytrees.cloud.id.generator.Generator;
import com.bytrees.cloud.id.generator.creator.DefaultTimeCreator;
import com.bytrees.cloud.response.BaseResponse;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@RequestMapping("/idGenerator")
public class IdGeneratorController {
    private static final Generator generator = Generator.getInstance(new DefaultTimeCreator(), 1);

    @GetMapping("/getId")
    public BaseResponse<Long> getId() {
        return BaseResponse.success(generator.genId(1));
    }

    @GetMapping("/getUUID")
    public BaseResponse<String> getUUID() {
        return BaseResponse.success(UUID.randomUUID().toString());
    }

    @GetMapping("/getSystemTime")
    public BaseResponse<Long> getSystemTime() {
        return BaseResponse.success(System.currentTimeMillis());
    }
}
