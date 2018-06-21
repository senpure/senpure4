package com.senpure.base.controller;

import com.senpure.base.annotation.PermissionVerify;
import com.senpure.base.annotation.ResourceVerify;
import com.senpure.base.menu.MenuGenerator;
import com.senpure.base.service.AuthorizeService;
import com.senpure.base.service.ResourceVerifyAccountService;
import com.senpure.base.spring.BaseController;
import com.senpure.base.struct.LoginedAccount;
import com.senpure.base.util.Http;
import org.hibernate.validator.constraints.Length;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

/**
 * Created by 罗中正 on 2017/6/19.
 */
@Controller
@RequestMapping("/authorize")
@MenuGenerator(id = 2000000000, text = "系统设置",icon = "glyphicon glyphicon-cog faa-spin",sort = Integer.MAX_VALUE)
public class SystemController extends BaseController {


    @Autowired
    private AuthorizeService authorizeService;

    @RequestMapping(value = {"/account/password", "/account/{accountId}/password"}, method = RequestMethod.GET)
    @PermissionVerify(value = "进入修改密码界面", name = "authorize/account/password_read")
    @MenuGenerator(id = 2000000001, text = "修改密码",icon = "glyphicon glyphicon-lock faa-float",sort = Integer.MAX_VALUE)
    public ModelAndView updatePasswordPage(HttpServletRequest request, Model model, @PathVariable(required = false) Long accountId) {

        if (accountId == null) {
            LoginedAccount account = Http.getSubject(request,LoginedAccount.class);
            accountId = account.getId();
            model.addAttribute("accountId",accountId);
        }

        return view(request, "authorize/updatePassword");
    }

    @RequestMapping(value = "/account/{accountId}/password", method = RequestMethod.PUT)
    @PermissionVerify("修改密码")
    @ResourceVerify(ResourceVerifyAccountService.VERIFY_NAME)
    public ModelAndView updatePassword(HttpServletRequest request, @PathVariable Long accountId, @Valid @Length(min = 5, max = 24, message = "{password.length.error}") String password) {


        authorizeService.updatePassword(accountId, password);

        return success(request, "authorize/updatePassword");

    }
    @RequestMapping(value = {"/help"},method = RequestMethod.GET)
    @PermissionVerify(value = "使用帮助", verify = false)
    @MenuGenerator(id = 2000000002, text = "使用帮助",icon = "glyphicon glyphicon-thumbs-up faa-float")
    public ModelAndView help(HttpServletRequest request, Model model, @PathVariable(required = false) Long accountId) {

        if (accountId == null) {
            LoginedAccount account = Http.getSubject(request,LoginedAccount.class);
            accountId = account.getId();
            model.addAttribute("accountId",accountId);
        }

        return view(request, "help");
    }
}
