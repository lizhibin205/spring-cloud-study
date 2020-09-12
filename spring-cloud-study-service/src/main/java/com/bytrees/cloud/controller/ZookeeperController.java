package com.bytrees.cloud.controller;

import com.bytrees.cloud.response.BaseResponse;
import org.apache.zookeeper.CreateMode;
import org.apache.zookeeper.KeeperException;
import org.apache.zookeeper.ZooDefs;
import org.apache.zookeeper.ZooKeeper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/zookeeper")
public class ZookeeperController {
    private static final Logger logger = LoggerFactory.getLogger(ZookeeperController.class);

    @Autowired
    private ZooKeeper zk;

    @GetMapping("/getStatus")
    public BaseResponse<ZooKeeper.States> getStatus() {
        return BaseResponse.success(zk.getState());
    }

    @GetMapping("/create")
    public  BaseResponse<String> create(HttpServletRequest request) {
        String znode = request.getParameter("znode");
        String data  = request.getParameter("data");
        try {
            String createRessult = zk.create(znode, data.getBytes(), ZooDefs.Ids.CREATOR_ALL_ACL, CreateMode.EPHEMERAL);
            return BaseResponse.success(createRessult);
        } catch (KeeperException | InterruptedException e) {
            logger.error("zookeeper create error.", e);
            return BaseResponse.fail(e.getMessage());
        }
    }
}
