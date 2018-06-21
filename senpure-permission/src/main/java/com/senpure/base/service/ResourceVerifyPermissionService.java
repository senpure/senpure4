package com.senpure.base.service;


import com.senpure.base.model.Account;
import com.senpure.base.model.ContainerPermission;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by Administrator on 2017/2/6.
 */
@Service
public class ResourceVerifyPermissionService extends ResourceVerifySupportService<Long> {

    public static final String VERIFY_NAME = "permissionResource";

    @Autowired
    private AccountService accountService;
    @Autowired
    private ContainerPermissionService containerPermissionService;

    @Override
    public String getName() {
        return VERIFY_NAME;
    }

    @Override
    public boolean verify(long accountId, String resourceId) {
        Account account = accountService.find(accountId);
        Long checkId = Long.valueOf(resourceId);
        List<ContainerPermission> containerPermissions = containerPermissionService.findByContainerId(account.getContainerId());
        for (ContainerPermission containerPermission : containerPermissions) {
            if (containerPermission.getExpiryTime() == null || containerPermission.getExpiryTime() == 0 ||
                    containerPermission.getExpiryTime() > System.currentTimeMillis()) {
                if (containerPermission.getPermissionId().longValue() == checkId.longValue()) {
                    return  true;
                }
            }
        }
        return false;
    }

}
