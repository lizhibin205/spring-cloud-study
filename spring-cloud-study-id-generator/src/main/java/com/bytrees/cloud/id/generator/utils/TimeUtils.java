package com.bytrees.cloud.id.generator.utils;

public class TimeUtils {
    private TimeUtils() {}

    /**
     * 返回当前秒数
     * @return
     */
    public static long getNowSecond() {
        return System.currentTimeMillis() / 1000;
    }

    /**
     * 等待并返回下一个Unix时间
     * @param nowSecond
     */
    public static long waitForNextSecond(long nowSecond) {
        while (true) {
            long nextSecond = getNowSecond();
            if (nextSecond > nowSecond) {
                return nextSecond;
            }
        }
    }
}
