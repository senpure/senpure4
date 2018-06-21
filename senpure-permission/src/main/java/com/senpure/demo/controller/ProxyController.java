package com.senpure.demo.controller;

import com.senpure.base.result.ResultMap;
import com.senpure.base.spring.BaseController;
import com.senpure.demo.criteria.ProxyCriteriaStr;
import com.senpure.demo.criteria.ProxyCriteria;
import com.senpure.demo.service.ProxyService;
import com.senpure.demo.model.Proxy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import com.senpure.base.annotation.PermissionVerify;
import com.senpure.base.menu.MenuGenerator;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

/**
 * @author senpure-generator
 * @version 2018-6-6 15:27:45
 */
@Controller
@RequestMapping("/demo")
@MenuGenerator(id = 1000, text = "Proxy")
public class ProxyController extends BaseController {

    @Autowired
    private ProxyService proxyService;
    private String view = "/demo/proxy";

    @RequestMapping(value = {"/proxies", "/proxies/{page}"}, method = {RequestMethod.GET, RequestMethod.POST})
    @PermissionVerify(name = "/demo/proxy_read", value = "proxies_read")
    @MenuGenerator(id = 1001, text = "Proxy Detail")

    public ModelAndView readProxies(HttpServletRequest request, @ModelAttribute("criteria") @Valid ProxyCriteriaStr criteriaStr, BindingResult result) {
        if (result.hasErrors()) {
            logger.warn("客户端输入不正确{}", result);
            return incorrect(request, result, view);
        }
        ProxyCriteria criteria = criteriaStr.toProxyCriteria();
        logger.debug("查询条件:{}", criteria);
        ResultMap resultMap = proxyService.findPage(criteria);
        return result(request, view, resultMap);
    }

    @RequestMapping(value = "/proxy/{id}", method = RequestMethod.GET)
    @PermissionVerify(name = "/demo/proxy_read", value = "proxy_read")
    @ResponseBody
    public ResultMap readProxy(HttpServletRequest request, @PathVariable String id) {
        Long numberId;
        try {
            numberId = Long.valueOf(id);
        } catch (NumberFormatException e) {
            logger.error("输入转换失败", e);
            return wrapMessage(request, ResultMap.notExist(), id);
        }
        logger.debug("查询Proxy:{}", id);
        Proxy proxy = proxyService.find(numberId);
        if (proxy != null) {
            return wrapMessage(request, ResultMap.success().putItem(proxy));
        } else {
            return wrapMessage(request, ResultMap.notExist(), id);
        }
    }


    @RequestMapping(value = "/proxy", method = RequestMethod.POST)
    @PermissionVerify(value = "proxy_create")
    @ResponseBody
    public ResultMap createProxy(HttpServletRequest request, @ModelAttribute("criteria") @Valid ProxyCriteriaStr criteriaStr, BindingResult result) {
        if (result.hasErrors()) {
            logger.warn("客户端输入不正确{}", result);
            return incorrect(request, result);
        }
        ProxyCriteria criteria = criteriaStr.toProxyCriteria();
        logger.debug("创建Proxy:{}", criteria);
        if (proxyService.save(criteria)) {
            return wrapMessage(request, ResultMap.success().put("id", criteria.getId()));
        } else {
            return wrapMessage(request, ResultMap.dim());
        }
    }

    @RequestMapping(value = "/proxy/{id}", method = RequestMethod.PUT)
    @PermissionVerify(value = "proxy_update")
    @ResponseBody
    public ResultMap updateProxy(HttpServletRequest request, @PathVariable String id, @ModelAttribute("criteria") @Valid ProxyCriteriaStr criteriaStr, BindingResult result) {
        if (result.hasErrors()) {
            logger.warn("客户端输入不正确{}", result);
            return incorrect(request, result);
        }
        Long numberId;
        try {
            numberId = Long.valueOf(id);
        } catch (NumberFormatException e) {
            logger.error("输入转换失败", e);
            return wrapMessage(request, ResultMap.notExist(), id);
        }
        ProxyCriteria criteria = criteriaStr.toProxyCriteria();
        criteria.setId(numberId);
        logger.debug("修改Proxy:{}", criteria);
        if (criteria.getVersion() == null) {
            Proxy proxy = proxyService.find(criteria.getId());
            if (proxy == null) {
                return wrapMessage(request, ResultMap.notExist(), id);
            }
            criteria.effective(proxy);
            if (proxyService.update(proxy)) {
                return wrapMessage(request, ResultMap.success());
            } else {
                return wrapMessage(request, ResultMap.dim());
            }
        }
        if (proxyService.update(criteria.toProxy())) {
            return wrapMessage(request, ResultMap.success());
        } else {
            return wrapMessage(request, ResultMap.dim());
        }
    }

    @RequestMapping(value = "/proxy/{id}", method = RequestMethod.DELETE)
    @PermissionVerify(value = "proxy_delete")
    @ResponseBody
    public ResultMap deleteProxy(HttpServletRequest request, @PathVariable String id) {
        Long numberId;
        try {
            numberId = Long.valueOf(id);
        } catch (NumberFormatException e) {
            logger.error("输入转换失败", e);
            return wrapMessage(request, ResultMap.notExist(), id);
        }
        logger.debug("删除Proxy:{}", id);
        if (proxyService.delete(numberId)) {
            return wrapMessage(request, ResultMap.success());
        } else {
            return wrapMessage(request, ResultMap.dim());
        }
    }
}
