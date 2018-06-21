package com.senpure.base.controller;

import com.senpure.base.annotation.PermissionVerify;
import com.senpure.base.annotation.ResourceVerify;
import com.senpure.base.criteria.RoleCriteria;
import com.senpure.base.criteria.RolePermissionCriteria;
import com.senpure.base.service.AuthorizeService;
import com.senpure.base.service.ResourceVerifyContainerService;
import com.senpure.base.service.ResourceVerifyPermissionService;
import com.senpure.base.service.ResourceVerifyRoleService;
import com.senpure.base.spring.BaseController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

/**
 * Created by 罗中正 on 2017/12/29 0029.
 */

@Controller
@RequestMapping("/authorize")
public class RoleController extends BaseController {

    @Autowired
    private AuthorizeService authorizeService;

 
    @RequestMapping(value = "container/{containerId}/role", method = RequestMethod.GET)
    public ModelAndView role(HttpServletRequest request, @ModelAttribute("criteria") RoleCriteria criteria) {

        return view(request, "/authorize/roleCreate");
    }

    @PermissionVerify(value = "创建角色", name = "/authorize/container/{containerId}/role_create_owner")
    @RequestMapping(value = "container/{containerId}/role", method = RequestMethod.POST)
    @ResourceVerify(ResourceVerifyContainerService.VERIFY_NAME)
    public ModelAndView createRole(HttpServletRequest request,
                                   @Valid @ModelAttribute("criteria") RoleCriteria criteria, BindingResult validReslut) {

        if (validReslut.hasErrors()) {
            return view(request, "authorize/roleCreate");
        }
        authorizeService.createRole(criteria);
        return success(request, "authorize/roleCreate");
    }


    @PermissionVerify(value = "查看组织角色", name = "/authorize/container/{containerId}/roles_read_owner")
    @RequestMapping(value = {"container/{containerId}/roles", "container/{containerId}/roles/{page}"},method = RequestMethod.GET)
    @ResourceVerify(ResourceVerifyContainerService.VERIFY_NAME)
    public ModelAndView readRoles(HttpServletRequest request,
                             @Valid @ModelAttribute("criteria") RoleCriteria criteria, BindingResult validReslut) {

        if (validReslut.hasErrors()) {
            return view(request, "authorize/role");

        }
        return view(request, "authorize/role", authorizeService.loadRoles(criteria));
    }


    @PermissionVerify(value = "查看角色权限", name = "/authorize/role/{roleId}/permissions_read_owner")
    @RequestMapping(value = {"/role/{roleId}/permissions"},method = RequestMethod.GET)
    @ResourceVerify(ResourceVerifyRoleService.VERIFY_NAME)
    public ModelAndView readRoleHasPermission(HttpServletRequest request,
                                              @ModelAttribute("criteria") RolePermissionCriteria criteria) {

        return view(request, "authorize/rolePermission", authorizeService.roleHasPermission(criteria.getRoleId()));
    }

    @PermissionVerify(value = "修改角色权限", name = "/authorize/role/{roleId}/permission/{permissionId}_update_owner")
    @RequestMapping(value = "/role/{roleId}/permission/{permissionId}", method = RequestMethod.PUT)
    @ResourceVerify(ResourceVerifyRoleService.VERIFY_NAME)
    @ResourceVerify(value = ResourceVerifyPermissionService.VERIFY_NAME,offset = 2)
    public ModelAndView updateRolePermission(HttpServletRequest request, @PathVariable long roleId, @PathVariable long permissionId, boolean award) {

        authorizeService.rolePermission(roleId, permissionId, award);

        return success(request, "authorize/containerPermission");
    }
}
