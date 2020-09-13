package com.bytrees.cloud.controller;

import com.bytrees.cloud.response.BaseResponse;
import com.bytrees.cloud.zookeeper.ZookeeperUtils;
import org.apache.commons.lang.math.NumberUtils;
import org.apache.zookeeper.CreateMode;
import org.apache.zookeeper.KeeperException;
import org.apache.zookeeper.ZooDefs;
import org.apache.zookeeper.ZooKeeper;
import org.apache.zookeeper.data.Stat;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.nio.charset.StandardCharsets;
import java.util.List;

@RestController
@RequestMapping("/zookeeper")
public class ZookeeperController {
    private static final Logger logger = LoggerFactory.getLogger(ZookeeperController.class);
    private static final String REQUEST_PATH = "path";

    @Autowired
    private ZooKeeper zk;
    @Autowired
    private Stat stat;

    @GetMapping("/getState")
    public BaseResponse<ZooKeeper.States> getState() {
        return BaseResponse.success(zk.getState());
    }

    @PostMapping("/create")
    public  BaseResponse<String> create(HttpServletRequest request) {
        String path  = ZookeeperUtils.fillPath(request.getParameter(REQUEST_PATH));
        String data  = request.getParameter("data");
        byte[] dataBytes  = ZookeeperUtils.fillData(data);
        CreateMode createMode = ZookeeperUtils.fillCreateMode(request.getParameter("mode"));
        try {
            logger.info("zookeeper create path:{}, data:{}", path, data);
            String createResult = zk.create(path, dataBytes,
                    ZooDefs.Ids.OPEN_ACL_UNSAFE, createMode);
            return BaseResponse.success(createResult);
        } catch (KeeperException | InterruptedException e) {
            logger.error("zookeeper create error.", e);
            return BaseResponse.fail(e.getMessage());
        }
    }

    @GetMapping("/getChildren")
    public BaseResponse<List<String>> getChildren(HttpServletRequest request) {
        String path  = ZookeeperUtils.fillPath(request.getParameter(REQUEST_PATH));
        try {
            List<String> children = zk.getChildren(path, false);
            return BaseResponse.success(children);
        } catch (KeeperException | InterruptedException e) {
            logger.error("zookeeper getChildren error.", e);
            return BaseResponse.fail(e.getMessage());
        }
    }

    @PostMapping("/delete")
    public BaseResponse<String> delete(HttpServletRequest request) {
        String path  = ZookeeperUtils.fillPath(request.getParameter(REQUEST_PATH));
        String version = request.getParameter("version");
        try {
            zk.delete(path, NumberUtils.toInt(version, 0));
            return BaseResponse.success(null);
        } catch (KeeperException | InterruptedException e) {
            logger.error("zookeeper getChildren error.", e);
            return BaseResponse.fail(e.getMessage());
        }
    }

    @GetMapping("/getData")
    public BaseResponse<String> getData(HttpServletRequest request) {
        String path  = ZookeeperUtils.fillPath(request.getParameter(REQUEST_PATH));
        try {
            byte[] data = zk.getData(path, false, stat);
            return BaseResponse.success(new String(data, StandardCharsets.UTF_8));
        } catch (KeeperException | InterruptedException e) {
            logger.error("zookeeper getData error.", e);
            return BaseResponse.fail(e.getMessage());
        }
    }

    @PostMapping("/setData")
    public BaseResponse<String> setData(HttpServletRequest request) {
        String path  = ZookeeperUtils.fillPath(request.getParameter(REQUEST_PATH));
        String data  = request.getParameter("data");
        byte[] dataBytes  = ZookeeperUtils.fillData(data);
        int version = NumberUtils.toInt(request.getParameter("version"), 0);
        try {
            zk.setData(path, dataBytes, version);
            return BaseResponse.success(null);
        } catch (KeeperException | InterruptedException e) {
            logger.error("zookeeper setData error.", e);
            return BaseResponse.fail(e.getMessage());
        }
    }
}
