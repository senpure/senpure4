package com.senpure.base.struct;

import java.io.Serializable;

/**
 * Created by 罗中正 on 2017/12/25 0025.
 */
public class NormalValue implements Serializable {


    private static final long serialVersionUID = -2247476317068634168L;

    private long id;

    private String key;
    private String value;

    private boolean normal;

    private int version;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public boolean isNormal() {
        return normal;
    }

    public void setNormal(boolean normal) {
        this.normal = normal;
    }

    public int getVersion() {
        return version;
    }

    @Override
    public String toString() {
        return "NormalValue{" +
                "key='" + key + '\'' +
                ", value='" + value + '\'' +
                '}';
    }

    public void setVersion(int version) {
        this.version = version;
    }


}
