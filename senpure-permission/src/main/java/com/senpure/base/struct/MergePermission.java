package com.senpure.base.struct;

import java.io.Serializable;

/**
 * Created by 罗中正 on 2017/5/10.
 */
public class MergePermission implements Serializable {


    private static final long serialVersionUID = -2158685938806433229L;
    private	long id;
    private	String name;
    private long expiry;
    private String type;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public long getExpiry() {
        return expiry;
    }

    public void setExpiry(long expiry) {
        this.expiry = expiry;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return "MergePermission{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", expiry=" + expiry +
                ", type='" + type + '\'' +
                '}';
    }
}
