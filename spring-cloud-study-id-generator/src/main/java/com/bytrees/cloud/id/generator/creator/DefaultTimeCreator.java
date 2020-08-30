package com.bytrees.cloud.id.generator.creator;

import com.bytrees.cloud.id.generator.IdCreator;

/**
 * 默认的ID规则生成器
 * 类型[0-15] + unix时间毫秒[0-4294967295] + 自增序列号[0-1023] + 机器ID[0-1023]
 * 位长：[4] + [32] + [10] + [10] = [56]
 */
public class DefaultTimeCreator implements IdCreator {
    @Override
    public long createId(long type, long unixTime, long sequence, long machineId) {
        long ret = 0;
        ret = ret | machineId;
        ret = ret | sequence << 10;
        ret = ret | unixTime << 20;
        ret = ret | type << 52;
        return ret;
    }

    @Override
    public long maxSequence() {
        return 1024;
    }
}
