package com.senpure.base.entity;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import javax.persistence.Version;
import java.io.Serializable;

/**
 * Created by 罗中正 on 2017/8/25.
 */
@MappedSuperclass
public class VersionEntity implements Serializable {

    private static final long serialVersionUID = -3138271455725357169L;
    @Version
    @Column(nullable = false)
    private Integer version;

    public Integer getVersion() {
        return version;
    }

    public void setVersion(Integer version) {
        this.version = version;
    }
}
