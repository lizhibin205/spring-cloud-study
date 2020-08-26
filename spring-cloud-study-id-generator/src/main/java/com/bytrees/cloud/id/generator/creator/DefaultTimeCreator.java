package com.bytrees.cloud.id.generator.creator;

import com.bytrees.cloud.id.generator.IdCreator;

public class DefaultTimeCreator implements IdCreator {
    @Override
    public long createId(long unixTime, long seq, long machineId) {
        long ret = 0;
        ret = ret | machineId;
        ret = ret | seq << 10;
        ret = ret | unixTime << 20;
        return ret;
    }

    @Override
    public long maxSeq() {
        return 1024;
    }
}
