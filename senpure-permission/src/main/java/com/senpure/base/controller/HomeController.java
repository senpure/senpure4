package com.senpure.base.controller;

import com.senpure.base.annotation.PermissionVerify;
import com.senpure.base.spring.BaseController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Created by Administrator on 2017/3/1.
 */
@Controller
public class HomeController extends BaseController {

    @RequestMapping(value = {"/home","/index"},method = RequestMethod.GET)
    @PermissionVerify("进入主页 ")
    public Object home()
    {

        return "home";
    }
}
