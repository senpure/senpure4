package com.senpure.base.controller;

import com.senpure.base.annotation.PermissionVerify;
import com.senpure.base.annotation.ResourceVerify;
import com.senpure.base.criteria.ContainerCriteria;
import com.senpure.base.menu.MenuGenerator;
import com.senpure.base.result.ResultMap;
import com.senpure.base.service.AuthorizeService;
import com.senpure.base.service.ContainerService;
import com.senpure.base.service.ResourceVerifyContainerService;
import com.senpure.base.service.ResourceVerifyPermissionService;
import com.senpure.base.spring.BaseController;
import com.senpure.base.struct.LoginedAccount;
import com.senpure.base.util.Http;
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
 * Created by 罗中正 on 2017/12/25 0025.
 */

@Controller
@RequestMapping("/authorize")
public class ContainerController extends BaseController {


    @Autowired
    private ContainerService containerService;
    @Autowired
    private AuthorizeService authorizeService;
    private String view = "/authorize/container";

    @MenuGenerator(text = "组织管理", id = 10, parentId = 1)
    @PermissionVerify(value = "查看组织", name = "/authorize/container_read_owner")
    @RequestMapping(value = {"/containers", "/containers/{page}"},method = {RequestMethod.GET,RequestMethod.POST})
    public ModelAndView readContainers(HttpServletRequest request,
                                  @Valid @ModelAttribute("criteria") ContainerCriteria criteria, BindingResult validResult) {
        if (validResult.hasErrors()) {
            return incorrect(request, validResult, view);
        }
        LoginedAccount account = Http.getSubject(request, LoginedAccount.class);
        criteria.setParentId(account.getContainerId());
        ResultMap resultMap = containerService.findPage(criteria);
        return view(request, view, resultMap);
    }


    @PermissionVerify(name = "/authorize/container_create_owner", value = "创建组织")
    @RequestMapping(value = "/container", method = RequestMethod.POST)
    public ModelAndView createContainer(HttpServletRequest request, @ModelAttribute("criteria") ContainerCriteria criteria, BindingResult validResult) throws Exception {
        if (validResult.hasErrors()) {
            return formatIncorrect(request, validResult, view);
        }
        LoginedAccount account = Http.getSubject(request, LoginedAccount.class);
        int containerId = account.getContainerId();
        //  authorizeService.createContaier(criteria, account.getId(), containerId);

        boolean success = authorizeService.createContainer(criteria, account.getId(), containerId);
        if (success) {
            ContainerCriteria containerCriteria = new ContainerCriteria();
            containerCriteria.setParentId(containerId);
            ResultMap resultMap = containerService.findPage(containerCriteria);
            return result(request, view, resultMap);
        }
        return result(request, view, ResultMap.dim());
    }

    @PermissionVerify(name = "/container/{containerId}/permissions_read_owner",value = "查看组织权限")
    @RequestMapping(value = "/container/{containerId}/permissions", method = RequestMethod.GET)
    @ResourceVerify(value = ResourceVerifyContainerService.VERIFY_NAME)
    public ModelAndView readContainerHasPermission(HttpServletRequest request, @PathVariable int containerId) {
        ResultMap resultMap = authorizeService.containerHasPermissions(containerId);

        return view(request, "authorize/containerPermission", resultMap);
    }


    @PermissionVerify(name = "/container/{containerId}/permission/{permissionId}_update_owner",value = "修改组织权限")
    @RequestMapping(value = "/container/{containerId}/permission/{permissionId}", method = RequestMethod.PUT)
    @ResourceVerify(value = ResourceVerifyContainerService.VERIFY_NAME,permissionName = "修改组织权限")
    @ResourceVerify(value = ResourceVerifyPermissionService.VERIFY_NAME,offset = 2)
    public ModelAndView updateContainerPermission(HttpServletRequest request, @PathVariable int containerId,  @PathVariable long permissionId, boolean award) {
       // LoginedAccount account = Http.getSubject(request,LoginedAccount.class);
        authorizeService.containerPermission(containerId,permissionId,award);
        return success(request,"authorize/containerPermission");
    }
}
