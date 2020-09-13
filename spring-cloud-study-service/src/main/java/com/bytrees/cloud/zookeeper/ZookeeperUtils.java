package com.bytrees.cloud.zookeeper;

import org.apache.zookeeper.CreateMode;

import java.nio.charset.StandardCharsets;

public class ZookeeperUtils {
    private ZookeeperUtils() {}

    /**
     * 返回zookeeper节点path
     * @param path
     * @return
     */
    public static String fillPath(String path) {
        if (path == null || path.length() == 0) {
            throw new IllegalArgumentException("path不能为空");
        }
        return path.startsWith("/") ? path : "/" + path;
    }

    /**
     * 返回存储在node的节点数据
     * @param data
     * @return
     */
    public static byte[] fillData(String data) {
        if (data == null || data.length() == 0) {
            throw new IllegalArgumentException("data不能为空");
        }
        return data.getBytes(StandardCharsets.UTF_8);
    }

    /**
     * 返回创建节点的有效性
     * @param mode
     * @return
     */
    public static CreateMode fillCreateMode(String mode) {
        return "1".equals(mode) ? CreateMode.PERSISTENT : CreateMode.EPHEMERAL;
    }
}
