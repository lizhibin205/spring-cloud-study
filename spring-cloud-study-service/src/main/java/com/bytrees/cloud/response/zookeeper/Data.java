package com.bytrees.cloud.response.zookeeper;

public class Data {
    private Integer version;
    private String data;
    public Integer getVersion() {
        return version;
    }
    public void setVersion(Integer version) {
        this.version = version;
    }
    public String getData() {
        return data;
    }
    public void setData(String data) {
        this.data = data;
    }
}
