package com.senpure.demo.controller;

import com.senpure.base.result.ResultMap;
import com.senpure.base.spring.BaseController;
import com.senpure.demo.criteria.ClazzCriteriaStr;
import com.senpure.demo.criteria.ClazzCriteria;
import com.senpure.demo.service.ClazzService;
import com.senpure.demo.model.Clazz;
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
@MenuGenerator(id = 1300, text = "Clazz")
public class ClazzController extends BaseController {

    @Autowired
    private ClazzService clazzService;
    private String view = "/demo/clazz";

    @RequestMapping(value = {"/clazzs", "/clazzs/{page}"}, method = {RequestMethod.GET, RequestMethod.POST})
    @PermissionVerify(name = "/demo/clazz_read", value = "clazzs_read")
    @MenuGenerator(id = 1301, text = "Clazz Detail")

    public ModelAndView readClazzs(HttpServletRequest request, @ModelAttribute("criteria") @Valid ClazzCriteriaStr criteriaStr, BindingResult result) {
        if (result.hasErrors()) {
            logger.warn("客户端输入不正确{}", result);
            return incorrect(request, result, view);
        }
        ClazzCriteria criteria = criteriaStr.toClazzCriteria();
        logger.debug("查询条件:{}", criteria);
        ResultMap resultMap = clazzService.findPage(criteria);
        return result(request, view, resultMap);
    }

    @RequestMapping(value = "/clazz/{id}", method = RequestMethod.GET)
    @PermissionVerify(name = "/demo/clazz_read", value = "clazz_read")
    @ResponseBody
    public ResultMap readClazz(HttpServletRequest request, @PathVariable String id) {
        Long numberId;
        try {
            numberId = Long.valueOf(id);
        } catch (NumberFormatException e) {
            logger.error("输入转换失败", e);
            return wrapMessage(request, ResultMap.notExist(), id);
        }
        logger.debug("查询Clazz:{}", id);
        Clazz clazz = clazzService.find(numberId);
        if (clazz != null) {
            return wrapMessage(request, ResultMap.success().putItem(clazz));
        } else {
            return wrapMessage(request, ResultMap.notExist(), id);
        }
    }


    @RequestMapping(value = "/clazz", method = RequestMethod.POST)
    @PermissionVerify(value = "clazz_create")
    @ResponseBody
    public ResultMap createClazz(HttpServletRequest request, @ModelAttribute("criteria") @Valid ClazzCriteriaStr criteriaStr, BindingResult result) {
        if (result.hasErrors()) {
            logger.warn("客户端输入不正确{}", result);
            return incorrect(request, result);
        }
        ClazzCriteria criteria = criteriaStr.toClazzCriteria();
        logger.debug("创建Clazz:{}", criteria);
        if (clazzService.save(criteria)) {
            return wrapMessage(request, ResultMap.success().put("id", criteria.getId()));
        } else {
            return wrapMessage(request, ResultMap.dim());
        }
    }

    @RequestMapping(value = "/clazz/{id}", method = RequestMethod.PUT)
    @PermissionVerify(value = "clazz_update")
    @ResponseBody
    public ResultMap updateClazz(HttpServletRequest request, @PathVariable String id, @ModelAttribute("criteria") @Valid ClazzCriteriaStr criteriaStr, BindingResult result) {
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
        ClazzCriteria criteria = criteriaStr.toClazzCriteria();
        criteria.setId(numberId);
        logger.debug("修改Clazz:{}", criteria);
        if (criteria.getVersion() == null) {
            Clazz clazz = clazzService.find(criteria.getId());
            if (clazz == null) {
                return wrapMessage(request, ResultMap.notExist(), id);
            }
            criteria.effective(clazz);
            if (clazzService.update(clazz)) {
                return wrapMessage(request, ResultMap.success());
            } else {
                return wrapMessage(request, ResultMap.dim());
            }
        }
        if (clazzService.update(criteria.toClazz())) {
            return wrapMessage(request, ResultMap.success());
        } else {
            return wrapMessage(request, ResultMap.dim());
        }
    }

    @RequestMapping(value = "/clazz/{id}", method = RequestMethod.DELETE)
    @PermissionVerify(value = "clazz_delete")
    @ResponseBody
    public ResultMap deleteClazz(HttpServletRequest request, @PathVariable String id) {
        Long numberId;
        try {
            numberId = Long.valueOf(id);
        } catch (NumberFormatException e) {
            logger.error("输入转换失败", e);
            return wrapMessage(request, ResultMap.notExist(), id);
        }
        logger.debug("删除Clazz:{}", id);
        if (clazzService.delete(numberId)) {
            return wrapMessage(request, ResultMap.success());
        } else {
            return wrapMessage(request, ResultMap.dim());
        }
    }
}
