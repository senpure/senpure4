package com.senpure.base.service;

import com.senpure.base.criterion.Criteria;
import com.senpure.base.util.IDGenerator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Created by 罗中正 on 2017/10/11.
 */
public class BaseService {
    protected Logger logger;

    @Autowired
    protected IDGenerator idGenerator;

    public BaseService() {

        logger = LoggerFactory.getLogger(getClass());
    }

    public void checkPage(Criteria criteria, int total) {
        if (criteria.getPage() * criteria.getPageSize() > total) {
            int page = total / criteria.getPageSize();
            if (total % criteria.getPageSize() > 0) {
                page++;
            }
            criteria.setPage(page);
        }
    }


}
