package com.senpure.base.service;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2017/2/6.
 */


public abstract class ResourceVerifySupportService<T> implements ResourceVerifyService<T>, InitializingBean {

    @Autowired
    private ResourcesVerifyService service;
    protected Logger logger;

    public ResourceVerifySupportService() {
        logger = LoggerFactory.getLogger(getClass());
    }

    @Transactional
    @Override
    public List<T> check(long accountId, List<T> resources) {
        if (resources == null) {
            return null;
        }
        List<T> list = new ArrayList<T>();
        for (T t : resources) {
            if (verify(accountId, t.toString())) {
                list.add(t);
            }
        }
        return list.isEmpty() ? null : list;
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        service.regService(this);
    }
}
