package com.bytrees.cloud;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

@SpringBootTest
public class HelloServiceTests {
    @Autowired
    private RestTemplate restTemplate;

    @Test
    public void sayHelloTest() {
        ResponseEntity<String> result = restTemplate.getForEntity("http://cloud-study/hello", String.class);
        Assertions.assertEquals("hello world!", result.getBody());
    }
}
