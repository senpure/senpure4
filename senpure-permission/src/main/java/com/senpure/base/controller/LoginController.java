package com.senpure.base.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.senpure.base.criteria.LoginCriteria;
import com.senpure.base.result.ResultMap;
import com.senpure.base.service.AuthorizeService;
import com.senpure.base.spring.BaseController;
import com.senpure.base.struct.LoginedAccount;
import com.senpure.base.util.Http;
import com.senpure.base.util.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

/**
 * Created by Administrator on 2017/1/20.
 */
@Controller
@RequestMapping("/")
public class LoginController extends BaseController {
    private static String cookieAesKey = "senpure123456789";
    private static String cookieKey = "senpure";
    @Autowired
    private AuthorizeService authorizeService;
    //String view="helloWorld";
    String view = "/authorize/login";

    @RequestMapping(value = {"authorize/login", ""}, method = {RequestMethod.GET, RequestMethod.POST})
    public ModelAndView login(HttpServletRequest request, HttpServletResponse response, @Valid @ModelAttribute("criteria") LoginCriteria criteria, BindingResult result) {
        logger.debug("login method");
        if (result.hasErrors()) {
            logger.warn("验证不通过");
            //  Exception exception=new Exception();
            // logger.error("",exception);
            return incorrect(request, result, view);
        }
        if (criteria.getAccount() == null) {

            return new ModelAndView(view);
        }
        criteria.setLoginType("ACCOUNT");
        loginInfo(request, criteria);
        ResultMap resultMap = authorizeService.login(criteria);
        if (resultMap.isSuccess()) {
            LoginedAccount loginedAccount = (LoginedAccount) resultMap.get("account");
            Http.setSubject(request, loginedAccount);
            logger.debug("\n{}\n{}", loginedAccount.getNormalValueMap(), loginedAccount.getPermissions());
            if (criteria.isRemember()) {
                JSONObject json = new JSONObject();
                json.put("account", criteria.getAccount());
                json.put("password", criteria.getPassword());
                json.put("time", System.currentTimeMillis());
                String jsonStr = json.toJSONString();
                // logger.debug("json:" + jsonStr);
                jsonStr = StringUtil.aesEncryptHex(jsonStr, cookieAesKey);
                Cookie cookie = new Cookie(cookieKey, jsonStr);
                cookie.setPath("/");
                // 一周
                cookie.setMaxAge(60 * 60 * 24 * 7);
                response.addCookie(cookie);
            } else {
                Cookie cookie = new Cookie(cookieKey, "");
                cookie.setMaxAge(0);
                response.addCookie(cookie);

            }
            String toURI = loginSuccessTo(request);
           // ModelAndView view = new ModelAndView(toURI);
           // return result(request, toURI, resultMap);
            return new ModelAndView(toURI);
        }

        return result(request, view, resultMap);
    }

    private String loginSuccessTo(HttpServletRequest request) {
        String toURI = null;
        Object o = Http.getFromSession(request, "loginToURI");
        if (o != null) {
            Http.removeFromSession(request, "loginToURI");
            String uri = (String) o;
            toURI = "redirect:" + uri;
        }
        toURI = toURI == null ? "redirect:/home" : toURI;
        if (!Http.isAjaxRequest(request)) {
            Boolean c = (Boolean) request.getAttribute("loginReferer");
            if (c != null && c) {
                Http.setToSession(request, "loginReferer", true);

            } else {
                Http.setToSession(request, "loginReferer", false);
            }
        }
        return toURI;
    }

    @RequestMapping(value = {"authorize/loginView"}, method = RequestMethod.GET)
    public ModelAndView login(HttpServletRequest request, @ModelAttribute("criteria") LoginCriteria criteria) {
        criteria.setRemember(true);
        ResultMap r = (ResultMap) request.getAttribute("args");
        if (r != null) {
            return result(request, view, r);
        }
        return view(request, view);
    }

    @RequestMapping(value = {"loginout"}, method = {RequestMethod.GET, RequestMethod.POST})
    public ModelAndView loginOut(HttpServletRequest request, HttpServletResponse response,@ModelAttribute("criteria") LoginCriteria criteria) {
        LoginedAccount account = Http.getSubject(request, LoginedAccount.class);
        if (account != null) {
            authorizeService.loginOut(account);
            Http.removeSubject(request);
        }
        Cookie cookie = new Cookie(cookieKey, "");
        cookie.setMaxAge(0);
        response.addCookie(cookie);
        ModelAndView view = new ModelAndView("redirect:/authorize/login");
       // return view(request, "redirect:/authorize/login");
        return view;
    }


    private void loginInfo(HttpServletRequest request,LoginCriteria criteria )
    {
        criteria.setLoginIP(Http.getIP(request,true));

    }
    public LoginedAccount autoLogin(HttpServletRequest request) {
        CheckCookie checkCookie = readCookie(request.getCookies());
        if (checkCookie.result && checkCookie.account != null && checkCookie.password != null) {
            LoginCriteria criteria = new LoginCriteria();
            criteria.setAccount(checkCookie.account);
            criteria.setPassword(checkCookie.password);
            criteria.setLoginType("COOKIE");

            loginInfo(request, criteria);
            ResultMap resultMap = authorizeService.login(criteria);
            if (resultMap.isSuccess()) {
                LoginedAccount loginedAccount = (LoginedAccount) resultMap.get("account");
                Http.setSubject(request, loginedAccount);
                return loginedAccount;
            }
        }
        return null;
    }

    public class CheckCookie {

        public boolean result = false;
        public String account;
        public String password;
    }

    public CheckCookie readCookie(Cookie[] cookie) {
        CheckCookie checkCookie = new CheckCookie();
        checkCookie.result = true;
        if (cookie != null) {
            for (Cookie c : cookie) {
                if (c.getName().equals(cookieKey)) {
                    try {
                        JSONObject json = JSON.parseObject(StringUtil.aesDecryptHex(c.getValue(), cookieAesKey));
                        checkCookie.account = json.getString("account");
                        checkCookie.password = json.getString("password");
                    } catch (Exception e) {
                        logger.error("解析cookie 失败:" + c.getValue(), e);
                        checkCookie.result = false;
                    }
                    break;
                }
            }
        }
        return checkCookie;
    }
}
