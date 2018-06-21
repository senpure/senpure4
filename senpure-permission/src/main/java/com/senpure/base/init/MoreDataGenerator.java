package com.senpure.base.init;


import com.senpure.base.PermissionConstant;
import com.senpure.base.model.Account;
import com.senpure.base.model.Container;
import com.senpure.base.model.SystemValue;
import com.senpure.base.service.AccountService;
import com.senpure.base.service.ContainerService;
import com.senpure.base.service.SystemValueService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by 罗中正 on 2017/5/22.
 */
@Component
@Order(value = 2)
public class MoreDataGenerator implements ApplicationListener<ContextRefreshedEvent> {



    @Autowired
    AccountService accountService;
    @Autowired
    SystemValueService systemValueService;
    @Autowired
    ContainerService containerService;

    @Override
    @Transactional
    public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent) {

        SystemValue systemValue =   systemValueService.findByKey("create.account.test");
        if (systemValue != null) {
            return;
        }
        systemValue = systemValueService.findByKey("top.container.id");
        Integer containerId=Integer.valueOf(systemValue.getValue());
        Container p = containerService.find(containerId);
        long now =System.currentTimeMillis();
        List<Account> accounts = new ArrayList<>();
        for (int j = 0; j <5 ; j++) {
            Container container = new Container();

            container.setLevel(PermissionConstant.CONTAINER_LEVEL_GROUP);
            container.setName("团队["+j+1+"]");
            container.setContainerStructure(p.getContainerStructure()
                    + PermissionConstant.CONTAINER_SEPARTOR + p.getId() + PermissionConstant.CONTAINER_SEPARTOR);
            container.setDescription("用来容纳我的下级成员的一个团队");
            container.setParentId(containerId);
            container.setCreateDate(new Date());
            container.setCreateTime(container.getCreateDate().getTime());
            container.setRelation(p.getRelation());
            containerService.save(container);

            for (int i = 10; i < 80; i++) {
                Account account = accountService.defaultAccount(now);
                account.setAccount("accTest_[" +(j*100+ i)+"]");
                account.setPassword("accountTest");
                account.setName("accTest_name" +(j*100+ i)+"]");
                account.setContainerId(container.getId());
                accounts.add(account);
            }
        }

       accountService.save(accounts);
        systemValue = new SystemValue();
        systemValue.setKey("create.account.test");
        systemValue.setValue("true");
        systemValue.setDescription("标记是否创建了account Test 数据");
        systemValue.setType(PermissionConstant.VALUE_TYPE_SYSTEM);
        systemValueService.save(systemValue);

    }
}
