package com.bytrees.cloud.id.generator;

import com.bytrees.cloud.id.generator.utils.TimeUtils;

public class Generator {
    /**
     * 创建全局单例ID生成器对象
     * @return
     */
    public static synchronized Generator getInstance(IdCreator idCreator, long machineId) {
        if (generator == null) {
            generator = new Generator(idCreator, machineId);
        }
        return generator;
    }

    /**
     * 全局ID生成器对象
     */
    private static Generator generator = null;

    /**
     * 构造函数
     * @param idCreator
     */
    public Generator(IdCreator idCreator, long machineId) {
        super();
        this.idCreator = idCreator;
        this.machineId = machineId;
        this.lastSecond = TimeUtils.getNowSecond();
        this.sequence = -1;
    }

    private final IdCreator idCreator;
    private final long machineId;
    private volatile long lastSecond;
    private volatile long sequence;

    /**
     * 生成ID序列
     * @return
     */
    public synchronized long genId(long type) {
        long nowSecond = TimeUtils.getNowSecond();
        //出现时钟回拨
        if (nowSecond < lastSecond) {
            throw new IllegalStateException("状态异常：当前时间少于上一次时间");
        }
        if (nowSecond > lastSecond) {
            //新的一秒，重置队列为0
            lastSecond = nowSecond;
            sequence = 0;
        } else {
            //当前秒，队列自增1，且要判断是否满足最大队列数
            sequence += 1;
            if (sequence == idCreator.maxSequence()) {
                //必须等待下一秒
                lastSecond = TimeUtils.waitForNextSecond(nowSecond);
                sequence = 0;
            }
        }
        return idCreator.createId(type, lastSecond, sequence, machineId);
    }
}
