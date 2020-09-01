package com.bytrees.cloud.id.generator;

public interface IdCreator {
    public long createId(long type, long unixTime, long sequence, long machineId);
    public long beginUnixTimePadding();
    public long maxSequence();
    public IdMeta getIdMetaInfo(long id);
}
