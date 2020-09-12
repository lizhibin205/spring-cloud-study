package com.bytrees.cloud.zookeeper;

import org.apache.zookeeper.WatchedEvent;
import org.apache.zookeeper.Watcher;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ZookeeperWatch implements Watcher {
    private static final Logger logger = LoggerFactory.getLogger(ZookeeperWatch.class);

    @Override
    public void process(WatchedEvent event) {
        logger.info("ZookeeperWatch process: {}", event);
    }
}
