package com.senpure.base.init;

import com.senpure.base.PermissionConstant;
import com.senpure.base.criteria.RolePermissionCriteria;
import com.senpure.base.criteria.SystemValueCriteria;
import com.senpure.base.model.ContainerPermission;
import com.senpure.base.model.Permission;
import com.senpure.base.model.RolePermission;
import com.senpure.base.model.SystemValue;
import com.senpure.base.service.*;
import com.senpure.base.spring.SpringContextRefreshEvent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by 罗中正 on 2017/10/20.
 */
@Component
@Order(value = 3)
public class PermissionCompleteGenerator extends SpringContextRefreshEvent {

    @Autowired
    private PermissionService permissionService;
    @Autowired
    private SystemValueService systemValueService;
    @Autowired
    private RoleService roleService;
    @Autowired
    private ContainerService containerService;
    @Autowired
    private RolePermissionService rolePermissionService;
    @Autowired
    private ContainerPermissionService containerPermissionService;

    @Override
    @Transactional
    public void onApplicationEvent(ContextRefreshedEvent event) {


        List<Permission> allPermissions = permissionService.findAll();
        SystemValueCriteria systemValueCriteria = new SystemValueCriteria();
        systemValueCriteria.setType(PermissionConstant.VALUE_TYPE_SYSTEM);
        systemValueCriteria.setKey(PermissionConstant.TOP_ROLE_ID);
        systemValueCriteria.setUsePage(false);
        SystemValue topRole = systemValueService.findOne(systemValueCriteria);
        systemValueCriteria.setKey(PermissionConstant.TOP_CONTAINER_ID);
        SystemValue topContainer = systemValueService.findOne(systemValueCriteria);

        RolePermissionCriteria rolePermissionCriteria = new RolePermissionCriteria();
        rolePermissionCriteria.setUsePage(false);
        Long roleId = Long.valueOf(topRole.getValue());
        rolePermissionCriteria.setRoleId(roleId);
        List<RolePermission> rolePermissions = rolePermissionService.find(rolePermissionCriteria);
        List<RolePermission> saveRolePermissions = new ArrayList<>();
        List<ContainerPermission> saveContainerPermissions = new ArrayList<>();
        for (Permission permission : allPermissions) {
            boolean save = true;
            for (RolePermission rolePermission : rolePermissions) {
                if (rolePermission.getPermissionId().longValue() == permission.getId()) {
                    save = false;
                    break;
                }
            }
            if (save) {
                logger.debug("给系统补上权限{}", permission);
                RolePermission rolePermission = new RolePermission();
                rolePermission.setRoleId(roleId);
                rolePermission.setPermissionId(permission.getId());
                rolePermission.setExpiryDate(PermissionConstant.FORVER_DATE);
                rolePermission.setExpiryTime(PermissionConstant.FORVER_TIME);
                saveRolePermissions.add(rolePermission);
                ContainerPermission containerPermission = new ContainerPermission();
                containerPermission.setPermissionId(permission.getId());
                containerPermission.setContainerId(Integer.valueOf(topContainer.getValue()));
                containerPermission.setExpiryDate(PermissionConstant.FORVER_DATE);
                containerPermission.setExpiryTime(PermissionConstant.FORVER_TIME);
                saveContainerPermissions.add(containerPermission);
            }
        }
        if (saveRolePermissions.size() > 0) {
            rolePermissionService.save(saveRolePermissions);
            containerPermissionService.save(saveContainerPermissions);
        }
    }
}
