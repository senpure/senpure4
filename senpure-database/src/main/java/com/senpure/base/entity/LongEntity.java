package com.senpure.base.entity;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import java.io.Serializable;

/**
 * Created by 罗中正 on 2017/8/25.
 */
@MappedSuperclass
public class LongEntity  implements Serializable{
    private static final long serialVersionUID = -5114875919596845607L;
    @Id
    @GenericGenerator(name="idGenerator", strategy="com.senpure.base.util.IDGeneratorHibernate")
    @GeneratedValue(generator="idGenerator")
    private Long id;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
