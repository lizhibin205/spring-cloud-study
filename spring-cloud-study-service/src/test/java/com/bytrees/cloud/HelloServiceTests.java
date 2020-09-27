package com.bytrees.cloud;

import com.bytrees.cloud.response.BaseResponse;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import org.springframework.web.client.RestTemplate;

@SpringBootTest
public class HelloServiceTests {
    @Autowired
    private RestTemplate restTemplate;

    @Test
    public void sayHelloTest() {
        @SuppressWarnings("unchecked")
        BaseResponse<String> result = restTemplate.getForObject("http://cloud-study/hello", 
            BaseResponse.class);
        Assertions.assertEquals("hello world!", result.getData());
    }
}
