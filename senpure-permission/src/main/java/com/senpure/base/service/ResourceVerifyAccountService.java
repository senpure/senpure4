package com.senpure.base.service;


import com.senpure.base.PermissionConstant;
import com.senpure.base.model.Account;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by Administrator on 2017/2/6.
 */
@Service
public class ResourceVerifyAccountService extends ResourceVerifySupportService<Long> {

    public static final String VERIFY_NAME = "accountResource";


    @Autowired
    private AccountService accountService;
    @Autowired
    private ContainerService containerService;
    @Override
    public String getName() {
        return VERIFY_NAME;
    }


    @Override
    public boolean verify(long accountId, String resourceId) {

       Long checkId = Long.valueOf(resourceId);
        if (checkId.longValue() == accountId) {
            return true;
        }
        Account account = accountService.find(accountId);
        Account checkAccount = accountService.find(checkId);
        if (checkAccount == null) {
            return false;
        }
        return containerService.find(checkAccount.getContainerId()).getContainerStructure().contains(PermissionConstant.CONTAINER_SEPARTOR+account.getContainerId());
    }
}
