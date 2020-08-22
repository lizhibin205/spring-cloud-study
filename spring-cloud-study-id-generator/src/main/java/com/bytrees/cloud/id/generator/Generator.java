package com.bytrees.cloud.id.generator;

public class Generator {
    /**
     * 创建全局单例ID生成器对象
     * @return
     */
    public static synchronized Generator getInstance(String machineId) {
        if (generator == null) {
            generator = new Generator(machineId);
        }
        return generator;
    }

    /**
     * 全局ID生成器对象
     */
    private static Generator generator = null;

    /**
     * 构造函数
     * @param machineId
     */
    public Generator(String machineId) {
        super();
        this.machineId = machineId;
    }

    private final String machineId;
}
