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
     * 返回当前微秒数
     * @return
     */
    public static long getNowMillis() {
        return System.currentTimeMillis();
    }

    /**
     * 等待并返回下一个Unix时间
     * @param nowSecond
     */
    public static long waitForNextSecond(long nowSecond) {
        while (true) {
            long nextSecond = getNowSecond();
            // 线程休眠，避免CPU占用率升高
            if (nextSecond <= nowSecond) {
                long sleepTime = (nowSecond + 1) * 1000 - getNowMillis();
                if (sleepTime > 0) {
                    try {
                        Thread.sleep(sleepTime);
                    } catch (InterruptedException e) {
                        
                        Thread.currentThread().interrupt();
                    }
                }
                continue;
            }
            return nextSecond;
        }
    }
}
