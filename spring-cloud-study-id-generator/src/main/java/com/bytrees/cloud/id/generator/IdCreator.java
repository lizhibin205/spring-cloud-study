package com.bytrees.cloud.id.generator;

public interface IdCreator {
    public long createId(long unixTime, long seq, long machineId);
    public long maxSeq();
}
