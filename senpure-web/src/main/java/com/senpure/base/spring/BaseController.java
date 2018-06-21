package com.senpure.base.spring;


import com.senpure.base.WebConstant;
import com.senpure.base.result.Result;
import com.senpure.base.result.ResultHelper;
import com.senpure.base.result.ResultMap;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.MessageSourceResolvable;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.*;

public class BaseController {

    protected Logger logger;

    public BaseController() {
        logger = LoggerFactory.getLogger(getClass());
    }

    protected LocaleResolver localeResolver;

    public LocaleResolver getLocaleResolver() {
        return localeResolver;
    }

    @Autowired
    @Qualifier("localeResolver")
    public void setLocaleResolver(LocaleResolver localeResolver) {
        this.localeResolver = localeResolver;
    }

    protected ModelAndView addActionResult(HttpServletRequest request, ModelAndView modelAndView, ResultMap result) {
        return addActionResult(request, modelAndView, result, true);
    }

    protected ModelAndView addActionResult(HttpServletRequest request, ModelAndView modelAndView, ResultMap result,
                                           boolean i18n) {
        if (i18n && result.getMessage() == null) {
            logger.debug("localeResolver" + localeResolver);
            ResultHelper.wrapMessage(result, localeResolver.resolveLocale(request));
        }

        return modelAndView.addObject(WebConstant.ACTION_RESULT_MODEL_VIEW_KEY, result);
    }

    protected ModelAndView view(HttpServletRequest request, String view) {

        return addActionResult(request, new ModelAndView(view), ResultMap.success(), false);
    }

    protected ModelAndView view(HttpServletRequest request, String view, ResultMap result) {

        return addActionResult(request, new ModelAndView(view), result, false);
    }

    protected ModelAndView result(HttpServletRequest request, String view, ResultMap result) {

        return addActionResult(request, new ModelAndView(view), result, true);
    }

    protected ModelAndView success(HttpServletRequest request, String view) {

        return addActionResult(request, new ModelAndView(view), ResultMap.success(), true);
    }

    protected ResultMap success(HttpServletRequest request) {

        return ResultHelper.wrapMessage(ResultMap.success(), localeResolver.resolveLocale(request));

    }

    protected ModelAndView dim(HttpServletRequest request, String view) {

        return addActionResult(request, new ModelAndView(view), ResultMap.dim(), true);
    }

    protected ModelAndView dim(HttpServletRequest request) {

        return addActionResult(request, new ModelAndView("dim"), ResultMap.dim(), true);
    }

    protected ModelAndView incorrect(HttpServletRequest request, BindingResult result,
                                     String view) {
        // LocaleContextHolder.getLocale();
        return addFormatIncorrectResult(request, result, new ModelAndView(view));
    }

    protected ModelAndView addFormatIncorrectResult(HttpServletRequest request, BindingResult result,
                                                    ModelAndView modelAndView) {
        // LocaleContextHolder.getLocale();
        return modelAndView.addObject(WebConstant.ACTION_RESULT_MODEL_VIEW_KEY, incorrect(request, result));
    }

    protected ResultMap incorrect(HttpServletRequest request, BindingResult result) {
        Locale locale=localeResolver.resolveLocale(request);
        List<ObjectError> es = result.getAllErrors();
        Map<String, String> validators = new HashMap<>();
        StringBuilder sb = new StringBuilder();
        //FieldError
        for (ObjectError e : es) {
            Object[] args = e.getArguments();
            MessageSourceResolvable sr = (MessageSourceResolvable) args[0];
            String[] codes = sr.getCodes();
            String key = codes[codes.length - 1];
            if (key.endsWith("Valid")) {
                key = key.replace("Valid", "");
            }
            if (e.getDefaultMessage().contains("NumberFormatException")) {
                validators.put(key, ResultHelper.getMessage(Result.INPUT_NUMBER,locale));
            } else {
                validators.put(key, e.getDefaultMessage());
            }
            sb.append(e.getDefaultMessage());
            sb.append("\n");
        }
        logger.debug(sb.toString());
        ResultMap rm = ResultMap.result(Result.FORMAT_INCORRECT);
        rm.put(ResultMap.VALIDATOR_KEY, validators);
        logger.warn("validators {} ", validators);
        ResultHelper.wrapMessage(rm, locale);
        return rm;
    }

    protected ModelAndView formatIncorrect(HttpServletRequest request, BindingResult validResult, String view) {
        logger.warn("验证不通过");
        return addFormatIncorrectResult(request, validResult, new ModelAndView(view));
    }


    protected ResultMap wrapMessage(HttpServletRequest request, ResultMap resultMap) {
        ResultHelper.wrapMessage(resultMap, localeResolver.resolveLocale(request));
        return resultMap;
    }

    protected ResultMap wrapMessage(HttpServletRequest request, ResultMap resultMap, Object... args) {
        ResultHelper.wrapMessage(resultMap, localeResolver.resolveLocale(request), args);
        return resultMap;
    }
}
