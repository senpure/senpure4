package com.senpure.base.service;


import com.senpure.base.PermissionConstant;
import com.senpure.base.model.Account;
import com.senpure.base.model.Container;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by Administrator on 2017/2/6.
 */
@Service
public class ResourceVerifyContainerService extends ResourceVerifySupportService<Integer> {

    public static final String VERIFY_NAME = "containerResource";

    @Autowired
    private ContainerService containerService;

    @Autowired
    private AccountService accountService;

    @Override
    public String getName() {
        return VERIFY_NAME;
    }

    @Override
    public boolean verify(long accountId, String resourceId) {
        Integer checkId = Integer.valueOf(resourceId);
        return verify(accountId, checkId);
    }
    public boolean verify(long accountId, int resourceId) {
        Container container = containerService.find(resourceId);
        if (container == null) {
            logger.warn("容器Id[{}] 不存在", resourceId);
            return false;
        }
        Account account = accountService.find(accountId);
        logger.debug("账号所在容器id " + account.getContainerId());
        if (container.getParentId().intValue() == account.getContainerId()) {
            return true;
        }
        logger.debug("待检测的容器结构" + container.getContainerStructure());
        return container.getContainerStructure().contains(PermissionConstant.CONTAINER_SEPARTOR + account.getContainerId());

    }
}
