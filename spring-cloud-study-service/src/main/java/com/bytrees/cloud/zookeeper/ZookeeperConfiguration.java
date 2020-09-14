package com.bytrees.cloud.zookeeper;

import org.apache.zookeeper.ZooKeeper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.io.IOException;

@Configuration
public class ZookeeperConfiguration {
    @Value("${zookeeper.connection}")
    private String connection;
    @Value("${zookeeper.timeout}")
    private int timeout;

    @Bean
    public ZooKeeper geZookeeper() throws IOException {
        ZookeeperWatch watcher = new ZookeeperWatch();
        return new ZooKeeper(connection, timeout, watcher);
    }
}
