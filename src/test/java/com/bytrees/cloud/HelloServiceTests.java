package com.bytrees.cloud;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

@SpringBootTest
public class HelloServiceTests {
    @Test
    public void sayHelloTest() {
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<String> result = restTemplate.getForEntity("http://127.0.0.1:8080/hello", String.class);
        Assertions.assertEquals("hello world!", result.getBody());
    }
}
