package com.bytrees.cloud.controller;

import com.bytrees.cloud.response.BaseResponse;
import org.apache.commons.lang.StringUtils;
import org.apache.zookeeper.CreateMode;
import org.apache.zookeeper.KeeperException;
import org.apache.zookeeper.ZooDefs;
import org.apache.zookeeper.ZooKeeper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.List;

@RestController
@RequestMapping("/zookeeper")
public class ZookeeperController {
    private static final Logger logger = LoggerFactory.getLogger(ZookeeperController.class);

    @Autowired
    private ZooKeeper zk;

    @GetMapping("/getState")
    public BaseResponse<ZooKeeper.States> getState() {
        return BaseResponse.success(zk.getState());
    }

    @PostMapping("/create")
    public  BaseResponse<String> create(HttpServletRequest request) {
        String path  = request.getParameter("path");
        String data  = request.getParameter("data");
        String mode  = request.getParameter("mode");
        if (StringUtils.isBlank(path) || StringUtils.isBlank(data)) {
            return BaseResponse.fail("path或data不能为空");
        }
        path = path.startsWith("/") ? path : "/" + path;
        CreateMode createMode = CreateMode.EPHEMERAL;
        if ("1".equals(mode)) {
            createMode = CreateMode.PERSISTENT;
        }
        try {
            logger.info("zookeeper create path:{}, data:{}", path, data);
            String createResult = zk.create(path, data.getBytes(StandardCharsets.UTF_8),
                    ZooDefs.Ids.OPEN_ACL_UNSAFE, createMode);
            return BaseResponse.success(createResult);
        } catch (KeeperException | InterruptedException e) {
            logger.error("zookeeper create error.", e);
            return BaseResponse.fail(e.getMessage());
        }
    }

    @GetMapping("/getChildren")
    public BaseResponse<List<String>> getChildren(HttpServletRequest request) {
        String path = request.getParameter("path");
        if (StringUtils.isBlank(path)) {
            return BaseResponse.fail("path不能为空");
        }
        path = path.startsWith("/") ? path : "/" + path;
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
        String path = request.getParameter("path");
        if (StringUtils.isBlank(path)) {
            return BaseResponse.fail("path不能为空");
        }
        path = path.startsWith("/") ? path : "/" + path;
        try {
            zk.delete(path, 1);
            return BaseResponse.success(null);
        } catch (KeeperException | InterruptedException e) {
            logger.error("zookeeper getChildren error.", e);
            return BaseResponse.fail(e.getMessage());
        }
    }
}
