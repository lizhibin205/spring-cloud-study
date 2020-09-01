package com.bytrees.cloud.id.generator.creator;

import com.bytrees.cloud.id.generator.IdCreator;
import com.bytrees.cloud.id.generator.IdMeta;

/**
 * 默认的ID规则生成器
 * 类型[0-15] + unix时间毫秒[0-4294967295] + 自增序列号[0-1023] + 机器ID[0-1023]
 * 位长：[4] + [32] + [10] + [10] = [56]
 */
public class DefaultTimeCreator implements IdCreator {
    @Override
    public long createId(long type, long unixTime, long sequence, long machineId) {
        long paddingUnixTime = unixTime - beginUnixTimePadding();
        long ret = 0;
        ret = ret | machineId;
        ret = ret | sequence << 10;
        ret = ret | paddingUnixTime << 20;
        ret = ret | type << 52;
        return ret;
    }

    @Override
    public long beginUnixTimePadding() {
        //时间偏移开始日期：2020/1/1 0:0:0
        return 1577808000;
    }

    @Override
    public long maxSequence() {
        return 1024;
    }

    @Override
    public IdMeta getIdMetaInfo(long id) {
        long machineId = 1023L & id;
        long sequence = 1023L & (id >> 10);
        long unixTime = 4294967295L & (id >> 20);
        long type = 15L & (id >> 52);
        return new IdMeta(type, unixTime + beginUnixTimePadding(), sequence, machineId);
    }
}
