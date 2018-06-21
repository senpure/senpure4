package com.senpure.base.menu;

import com.alibaba.fastjson.JSON;
import com.senpure.base.struct.LoginedAccount;
import com.senpure.base.util.Http;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

public class MenuUtil {


    public static String getMenu(HttpServletRequest request) {
        LoginedAccount account = Http.getSubject(request, LoginedAccount.class);
        if (account != null) {
            List<Menu> menus = account.getViewMenus();
            return JSON.toJSONString(menus);
        }
        return null;

    }
    public static String getMenu(LoginedAccount  account) {
        if (account != null) {
            List<Menu> menus = account.getViewMenus();
            return JSON.toJSONString(menus);
        }
        return null;

    }
}
