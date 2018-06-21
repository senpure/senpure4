package com.senpure.base.init;

import com.senpure.base.PermissionConstant;
import com.senpure.base.criteria.AccountCriteria;
import com.senpure.base.criteria.SequenceCriteria;
import com.senpure.base.model.*;
import com.senpure.base.service.*;
import com.senpure.base.spring.SpringContextRefreshEvent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by 罗中正 on 2017/10/11.
 */
@Component
@Order(2)
public class BaseDataGenerator extends SpringContextRefreshEvent {

    @Autowired
    private AccountService accountService;
    @Autowired
    private SequenceService sequenceService;
    @Autowired
    private ContainerService containerService;
    @Autowired
    private RoleService roleService;
    @Autowired
    private AccountRoleService accountRoleService;
    @Autowired
    private PermissionService permissionService;
    @Autowired
    private ContainerPermissionService containerPermissionService;
    @Autowired
    private RolePermissionService rolePermissionService;
    @Autowired
    private SystemValueService systemValueService;

    @Value("${permission.account:senpure}")
    private String account;
    @Value("${permission.password:senpure}")
    private String password;
    @Override
    @Transactional(rollbackFor = Exception.class)
    public void onApplicationEvent(ContextRefreshedEvent event) {
        containerIdCheck();
        AccountCriteria accountCriteria = new AccountCriteria();
        accountCriteria.setAccount(account);
        Account account = accountService.findOne(accountCriteria);
        if (account == null) {
            Date now = new Date();
            logger.debug("准备生产系统账号");
            //创建顶级容器
            Container container = new Container();
            container.setLevel(PermissionConstant.CONTAINER_LEVEL_TOP);
            container.setName(PermissionConstant.NAME);
            container.setContainerStructure("");
            container.setRelation(0L);
            container.setDescription("系统最高容器");
            container.setCreateDate(now);
            container.setCreateTime(now.getTime());
            containerService.save(container);
            container = containerService.find(container.getId());

            //创建顶级账号
            account = accountService.defaultAccount(now.getTime());
            account.setAccount(this.account);
            account.setPassword(password);
            account.setContainerId(container.getId());
            account.setName(PermissionConstant.NAME);
            accountService.save(account);
            container.setRelation(account.getId());
            containerService.update(container);
           //创建顶级角色
            Role role = new Role();
            role.setName(PermissionConstant.NAME);
            role.setCreateDate(now);
            role.setCreateTime(now.getTime());
            role.setContainerId(container.getId());
            role.setDescription("系统最高角色");
            roleService.save(role);

            //管理账号和角色
            AccountRole accountRole = new AccountRole();
            accountRole.setAccountId(account.getId());
            accountRole.setRoleId(role.getId());
            accountRole.setExpiryDate(PermissionConstant.FORVER_DATE);
            accountRole.setExpiryTime(PermissionConstant.FORVER_TIME);
            accountRoleService.save(accountRole);

            //给角色，和容器授予权限
            List<Permission> permissions = permissionService.findAll();
            List<RolePermission> rolePermissions = new ArrayList<>();
            List<ContainerPermission> containerPermissions = new ArrayList<>();
            Container finalContainer = container;
            permissions.forEach((Permission permission) -> {
                RolePermission rolePermission=new RolePermission();
                rolePermission.setPermissionId(permission.getId());
                rolePermission.setRoleId(role.getId());
                rolePermission.setExpiryDate(PermissionConstant.FORVER_DATE);
                rolePermission.setExpiryTime(PermissionConstant.FORVER_TIME);
                rolePermissions.add(rolePermission);
                ContainerPermission containerPermission = new ContainerPermission();
                containerPermission.setContainerId(finalContainer.getId());
                containerPermission.setPermissionId(permission.getId());
                containerPermission.setExpiryDate(PermissionConstant.FORVER_DATE);
                containerPermission.setExpiryTime(PermissionConstant.FORVER_TIME);
                containerPermissions.add(containerPermission);
            });
            rolePermissionService.save(rolePermissions);
            containerPermissionService.save(containerPermissions);

            //补充系统一些必要的值
            List<SystemValue> systemValues=new ArrayList<>();
            SystemValue systemValue=new SystemValue();
            systemValue.setType(PermissionConstant.VALUE_TYPE_SYSTEM);
            systemValue.setKey(PermissionConstant.TOP_ROLE_ID);
            systemValue.setValue(role.getId()+"");
            systemValue.setDescription("系统顶级角色ID");
            systemValues.add(systemValue);
            systemValue=new SystemValue();
            systemValue.setType(PermissionConstant.VALUE_TYPE_SYSTEM);
            systemValue.setKey(PermissionConstant.TOP_CONTAINER_ID);
            systemValue.setValue(container.getId()+"");
            systemValue.setDescription("系统顶级容器ID");
            systemValues.add(systemValue);

            SystemValue dateFormat=new SystemValue();
            dateFormat.setType(PermissionConstant.VALUE_TYPE_ACCOUNT_DEFAULT);
            dateFormat.setKey(PermissionConstant.DATE_FORMAT_KEY);
            dateFormat.setValue("yyyy-MM-dd");
            dateFormat.setDescription("例如:2017-05-06");
            systemValues.add(dateFormat);

            systemValue=new SystemValue();
            systemValue.setType(PermissionConstant.VALUE_TYPE_ACCOUNT_DEFAULT);
            systemValue.setKey(PermissionConstant.DATETIME_FORMAT_KEY);
            systemValue.setValue("yyyy-MM-dd HH:mm:ss");
            systemValue.setDescription("例如:2017-05-06 17:55:99");
            systemValues.add(systemValue);

            systemValue=new SystemValue();
            systemValue.setType(PermissionConstant.VALUE_TYPE_ACCOUNT_DEFAULT);
            systemValue.setKey(PermissionConstant.TIME_FORMAT_KEY);
            systemValue.setValue("HH:mm:ss");
            systemValue.setDescription("例如:17:55:99");
            systemValues.add(systemValue);
            systemValueService.save(systemValues);

        }





    }

    private void containerIdCheck() {
        SequenceCriteria criteria = new SequenceCriteria();
        criteria.setType(PermissionConstant.SEQUENCE_CONTAINER_ID);

        Sequence sequence = sequenceService.findOne(criteria);
        if (sequence == null) {
            sequence = new Sequence();
            sequence.setType(PermissionConstant.SEQUENCE_CONTAINER_ID);
            sequence.setSequence(1);
            sequence.setDigit(6);
            sequence.setPrefix("");
            sequence.setSuffix("");
            sequence.setSpan(1);
            sequenceService.save(sequence);
        }

    }
}
