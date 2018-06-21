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
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Date;
import java.util.Locale;


@ControllerAdvice
public class ErrorController extends BaseController {

    @Autowired
    private MultipleInterceptor multipleInterceptor;

    @ExceptionHandler(Exception.class)
    public ModelAndView error(HttpServletRequest request, HttpServletResponse response, Exception e) {

        logger.error(request.getMethod()+": "+request.getRequestURI()+" 服务器未知错误",e);
        ResultMap result = ResultMap.dim();
        ResultHelper.wrapMessage(result, getLocaleResolver().resolveLocale(request));
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
        error.append("<h5>");
        error.append(request.getMethod()).append(": ").append(request.getRequestURI()).append("<br>");
        error.append("</h5>");
        error.append(e.toString().replace("\n", "<br>"));
        error.append("<br>");
        for (StackTraceElement s : e.getStackTrace()) {
            error.append(s.toString()).append("<br>");


        }

        result.put(ResultMap.MESSAGE_KEY, error.toString());
        logger.debug("accountValues {}", multipleInterceptor.getAccountValues().toString());
        logger.debug("menuJson {}", menuJosn);
        logger.debug("view:" + modelAndView.getViewName());
        logger.debug("{} {} > {}", request.getMethod(), request.getRequestURI(), modelAndView.getViewName());
        modelAndView.addAllObjects(result);
        return modelAndView;
    }
}
