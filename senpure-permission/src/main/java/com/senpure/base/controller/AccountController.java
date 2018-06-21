package com.senpure.base.controller;

import com.senpure.base.annotation.PermissionVerify;
import com.senpure.base.annotation.ResourceVerify;
import com.senpure.base.criteria.AccountCriteria;
import com.senpure.base.criteria.ContainerCriteria;
import com.senpure.base.menu.MenuGenerator;
import com.senpure.base.model.AccountValue;
import com.senpure.base.model.Container;
import com.senpure.base.result.ResultMap;
import com.senpure.base.service.*;
import com.senpure.base.spring.BaseController;
import com.senpure.base.struct.LoginedAccount;
import com.senpure.base.struct.NormalValue;
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
 * Created by 罗中正 on 2017/12/22 0022.
 */
@Controller
@RequestMapping("/authorize")
@MenuGenerator(id = 1, text = "组织架构", icon = "glyphicon glyphicon-fire faa-float")
public class AccountController extends BaseController {


    @Autowired
    private AccountService accountService;

    @Autowired
    private ContainerService containerService;
    @Autowired
    private AccountValueService accountValueService;

    @Autowired
    private AuthorizeService authorizeService;

    private String view = "/authorize/account";
    String key = "default.account.container.id";

    @MenuGenerator(id = 2, text = "人员管理", icon = "glyphicon glyphicon-user faa-float ")
    @RequestMapping(value = {"/accounts", "accounts/{page}"},method = {RequestMethod.GET,RequestMethod.POST})
    @PermissionVerify(name = "/authorize/accounts_read_owner", value = "查看组织人员")
    public ModelAndView readAccounts(HttpServletRequest request, @ModelAttribute("criteria") AccountCriteria criteria) {
        // criteria.setContainerId(PermissionConstant.ALL_OPOTION_INT);
        LoginedAccount account = Http.getSubject(request, LoginedAccount.class);
        String containerId = account.getConfig(key);
        if (containerId == null) {
            ContainerCriteria containerCriteria = new ContainerCriteria();
            containerCriteria.setParentId(account.getContainerId());
            Container container = containerService.findOne(containerCriteria);
            if (container == null) {
                logger.error("{}-{}没有账号可以查看", account.getAccount(), account.getName());
                return view(request, view, ResultMap.success());
            }
            criteria.setContainerId(container.getId());
            AccountValue accountValue = new AccountValue();
            accountValue.setAccountId(account.getId());
            accountValue.setKey(key);
            accountValue.setValue(container.getId() + "");
            accountValueService.save(accountValue);
            account.setConfig(key, accountValue.getValue());
        } else {
            criteria.setContainerId(Integer.valueOf(containerId));
        }
        ResultMap resultMap = accountService.findPage(criteria);
        return view(request, view, resultMap);
    }

    @RequestMapping(value = {"/container/{containerId}/accounts", "/container/{containerId}/accounts/{page}"},method = {RequestMethod.GET,RequestMethod.POST})
    @PermissionVerify(name = "/authorize/accounts_read_owner", value = "查看组织人员")
    @ResourceVerify(value = ResourceVerifyContainerService.VERIFY_NAME)
    public ModelAndView readAccounts(HttpServletRequest request, @Valid @ModelAttribute("criteria") AccountCriteria criteria, BindingResult validResult) {
        if (validResult.hasErrors()) {
            return formatIncorrect(request, validResult, view);
        }
        LoginedAccount account = Http.getSubject(request, LoginedAccount.class);
        NormalValue normalValue = account.getNormalValue(key);
        String thisValue = criteria.getContainerId() + "";
        if (normalValue != null && !thisValue.equals(normalValue.getValue())) {
            if (normalValue.isNormal()) {
                AccountValue accountValue = new AccountValue();
                accountValue.setKey(key);
                accountValue.setValue(thisValue);
                accountValue.setId(normalValue.getId());
                accountValue.setAccountId(account.getId());
                accountValue.setVersion(normalValue.getVersion());
                accountValueService.update(accountValue);
                account.setConfig(key,thisValue);

            }

        }
        ResultMap resultMap = accountService.findPage(criteria);
        return view(request, view, resultMap);
    }


    @RequestMapping(value = "container/{containerId}/account", method = RequestMethod.GET)
    public ModelAndView readAccount(HttpServletRequest request, @ModelAttribute("criteria") AccountCriteria criteria) {

        return view(request, "/authorize/accountCreate");
    }


    @PermissionVerify(name = "/container/{containerId}/account_create_owner", value = "创建账号")
    @RequestMapping(value = "/container/{containerId}/account", method = RequestMethod.POST)
    @ResourceVerify(value = ResourceVerifyContainerService.VERIFY_NAME)
    public ModelAndView createDefaultAccount(HttpServletRequest request,
                                             @Valid @ModelAttribute("criteria") AccountCriteria criteria, BindingResult validResult) {
        if (validResult.hasErrors()) {
            return formatIncorrect(request, validResult, "/authorize/accountCreate");
        }

        ResultMap resultMap;
        if (authorizeService.createAccount(criteria)) {
            resultMap = ResultMap.success();
        } else {
            resultMap = ResultMap.dim();
        }

        return result(request, "/authorize/accountCreate", resultMap);
    }


    @PermissionVerify(value = "查看账号角色", name = "/authorize/account/{accountId}/roles_read_owner")
    @RequestMapping(value = {"/account/{accountId}/roles"},method = {RequestMethod.GET})
    @ResourceVerify(ResourceVerifyAccountService.VERIFY_NAME)
    public ModelAndView accountHasRole(HttpServletRequest request,
                                       @PathVariable long accountId) {
        return view(request, "authorize/accountRole", authorizeService.hasRole(accountId));
    }

    @PermissionVerify(value = "修改账号角色", name = "/authorize/account/{accountId}/role/{roleId}_update_owner")
    @RequestMapping(value = "/account/{accountId}/role/{roleId}", method = RequestMethod.PUT)
    @ResourceVerify(ResourceVerifyAccountService.VERIFY_NAME)
    @ResourceVerify(value = ResourceVerifyRoleService.VERIFY_NAME, offset = 2)
    public ModelAndView updateAccountRole(HttpServletRequest request,
                                          @PathVariable long accountId, @PathVariable long roleId, boolean award) {

        authorizeService.accountRole(accountId, roleId, award);
        return success(request, "authorize/accountRole");
    }


}
