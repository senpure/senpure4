package com.senpure.base.controller;

import com.alibaba.fastjson.JSON;
import com.senpure.base.PermissionConstant;
import com.senpure.base.result.ResultHelper;
import com.senpure.base.result.ResultMap;
import com.senpure.base.spring.BaseController;
import com.senpure.base.spring.MultipleInterceptor;
import com.senpure.base.struct.LoginedAccount;
import com.senpure.base.util.DateFormatUtil;
import com.senpure.base.util.Http;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Date;
import java.util.Locale;

/**
 * NotFindController
 *
 * @author 罗中正
 * @email senpure@senpure.com
 * @github https://github.com/senpure
 * @time 2018-06-03 11:26:59
 */
@Controller
public class NotFindController extends BaseController implements org.springframework.boot.web.servlet.error.ErrorController {

    @Autowired
    private MultipleInterceptor multipleInterceptor;

    @RequestMapping(value = "/error", method = {RequestMethod.GET, RequestMethod.POST})
    public ModelAndView notFind(HttpServletRequest request, HttpServletResponse response) {
//        Enumeration<String> enumeration = request.getAttributeNames();
//        while (enumeration.hasMoreElements()) {
//            String key = enumeration.nextElement();
//            logger.debug("{} = {}",key,request.getAttribute(key));
//        }
        // javax.servlet.forward.servlet_path
        //javax.servlet.error.request_uri

        String path = (String) request.getAttribute("javax.servlet.error.request_uri");
        logger.warn("{}:{} 服务器服务器未找到", request.getMethod(), path);
        ResultMap result = ResultMap.notExist();
        ResultHelper.wrapMessage(result, getLocaleResolver().resolveLocale(request), path);
        if (Http.isAjaxRequest(request)) {
            Http.returnJson(response, JSON.toJSONString(result));
            return null;
        }
        ModelAndView modelAndView = new ModelAndView("/error");
        LoginedAccount account = Http.getSubject(request, LoginedAccount.class);
        String menuJosn;
        if (account != null) {
            account.getNormalValueMap().forEach((key, normalValue) -> modelAndView.addObject(normalValue.getKey(), normalValue.getValue()));
            menuJosn = JSON.toJSONString(account.getViewMenus());
        } else {
            multipleInterceptor.getAccountValues().forEach(KeyValue -> modelAndView.addObject(KeyValue.getKey(), KeyValue.getValue()));
            menuJosn = JSON.toJSONString(multipleInterceptor.getMenus());
        }
        modelAndView.addObject("menuJson", menuJosn);
        Locale locale = localeResolver.resolveLocale(request);
        String viewLocale;
        if (locale.getCountry().length() > 0) {
            viewLocale = locale.getLanguage() + "-" + locale.getCountry();
        } else {
            viewLocale = locale.getLanguage();
        }
        modelAndView.addObject("viewLocale", viewLocale);
        StringBuilder error = new StringBuilder();
        String format = (String) modelAndView.getModelMap().get(PermissionConstant.DATETIME_FORMAT_KEY);
        error.append("<h4>TIME:").append(DateFormatUtil.getDateFormat(format).format(new Date())).append("</h4>");
        error.append("<h4>");
        String args = "<span style =\"color:red\">" + path + "</span>";
        ResultHelper.wrapMessage(result, locale, args);
        error.append(result.getMessage());
        error.append("</h4>");
        logger.debug("accountValues {}", multipleInterceptor.getAccountValues().toString());
        logger.debug("menuJson {}", menuJosn);
        logger.debug("view:" + modelAndView.getViewName());
        logger.debug("{} {} > {}", request.getMethod(), request.getRequestURI(), modelAndView.getViewName());
        result.putMessage(error.toString());
        modelAndView.addAllObjects(result);

        return modelAndView;
    }

    @Override
    public String getErrorPath() {
        return "/error";
    }
}
