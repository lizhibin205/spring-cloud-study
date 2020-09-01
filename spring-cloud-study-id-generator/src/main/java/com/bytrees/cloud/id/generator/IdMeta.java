package com.bytrees.cloud.id.generator;

public class IdMeta {
    private long type;
    private long unixTime;
    private long sequence;
    private long machineId;

    public IdMeta(long type, long unixTime, long sequence, long machineId) {
        this.type = type;
        this.unixTime = unixTime;
        this.sequence = sequence;
        this.machineId = machineId;
    }

    public long getType() {
        return type;
    }

    public long getUnixTime() {
        return unixTime;
    }

    public long getSequence() {
        return sequence;
    }

    public long getMachineId() {
        return machineId;
    }

    @Override
    public String toString() {
        return new StringBuilder()
                .append("[type=").append(type)
                .append(",unixTime=").append(unixTime)
                .append(",sequence").append(sequence)
                .append(",machineId=").append(machineId)
                .append("]").toString();
    }

}
