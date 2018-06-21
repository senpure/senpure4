package com.senpure.base.util;

import org.hibernate.HibernateException;
import org.hibernate.engine.spi.SharedSessionContractImplementor;
import org.hibernate.id.IdentifierGenerator;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.Serializable;


public class IDGeneratorHibernate implements IdentifierGenerator {

    private IDGenerator tempIdGenerator;
    @Autowired
    public void setTempIdGenerator(IDGenerator tempIdGenerator) {
        this.tempIdGenerator = tempIdGenerator;
        idGenerator = tempIdGenerator;
    }

    private static IDGenerator idGenerator;


    @Override
    public Serializable generate(SharedSessionContractImplementor session, Object object) throws HibernateException {
        return idGenerator.nextId();
    }
}
