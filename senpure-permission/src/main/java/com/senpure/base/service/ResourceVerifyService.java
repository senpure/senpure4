package com.senpure.base.service;

import java.util.List;

/**
 * Created by Administrator on 2017/2/6.
 */

public interface ResourceVerifyService<T> {


    public String getName();

    public boolean verify(long accountId, String resourceId);

    public List<T> check(long accountId, List<T> resourceId);
}
