package com.senpure.base.entity;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Created by 罗中正 on 2017/10/11.
 */
@MappedSuperclass
public class LongAndVersionEntity implements Serializable {
    private static final long serialVersionUID = -4479206002542553414L;
    @Id
    @GenericGenerator(name = "idGenerator", strategy = "com.senpure.base.util.IDGeneratorHibernate")
    @GeneratedValue(generator = "idGenerator")
    private Long id;
    @Version
    @Column(nullable = false)
    private Integer version;

    public Integer getVersion() {
        return version;
    }

    public void setVersion(Integer version) {
        this.version = version;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
