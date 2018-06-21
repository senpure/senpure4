package com.senpure.demo.controller;

import com.senpure.base.result.ResultMap;
import com.senpure.base.spring.BaseController;
import com.senpure.demo.criteria.NoticeCriteriaStr;
import com.senpure.demo.criteria.NoticeCriteria;
import com.senpure.demo.service.NoticeService;
import com.senpure.demo.model.Notice;
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
@MenuGenerator(id = 1200, text = "Notice")
public class NoticeController extends BaseController {

    @Autowired
    private NoticeService noticeService;
    private String view = "/demo/notice";

    @RequestMapping(value = {"/notices", "/notices/{page}"}, method = {RequestMethod.GET, RequestMethod.POST})
    @PermissionVerify(name = "/demo/notice_read", value = "notices_read")
    @MenuGenerator(id = 1201, text = "Notice Detail")

    public ModelAndView readNotices(HttpServletRequest request, @ModelAttribute("criteria") @Valid NoticeCriteriaStr criteriaStr, BindingResult result) {
        if (result.hasErrors()) {
            logger.warn("客户端输入不正确{}", result);
            return incorrect(request, result, view);
        }
        NoticeCriteria criteria = criteriaStr.toNoticeCriteria();
        logger.debug("查询条件:{}", criteria);
        ResultMap resultMap = noticeService.findPage(criteria);
        return result(request, view, resultMap);
    }

    @RequestMapping(value = "/notice/{id}", method = RequestMethod.GET)
    @PermissionVerify(name = "/demo/notice_read", value = "notice_read")
    @ResponseBody
    public ResultMap readNotice(HttpServletRequest request, @PathVariable String id) {
        Long numberId;
        try {
            numberId = Long.valueOf(id);
        } catch (NumberFormatException e) {
            logger.error("输入转换失败", e);
            return wrapMessage(request, ResultMap.notExist(), id);
        }
        logger.debug("查询Notice:{}", id);
        Notice notice = noticeService.find(numberId);
        if (notice != null) {
            return wrapMessage(request, ResultMap.success().putItem(notice));
        } else {
            return wrapMessage(request, ResultMap.notExist(), id);
        }
    }


    @RequestMapping(value = "/notice", method = RequestMethod.POST)
    @PermissionVerify(value = "notice_create")
    @ResponseBody
    public ResultMap createNotice(HttpServletRequest request, @ModelAttribute("criteria") @Valid NoticeCriteriaStr criteriaStr, BindingResult result) {
        if (result.hasErrors()) {
            logger.warn("客户端输入不正确{}", result);
            return incorrect(request, result);
        }
        NoticeCriteria criteria = criteriaStr.toNoticeCriteria();
        logger.debug("创建Notice:{}", criteria);
        if (noticeService.save(criteria)) {
            return wrapMessage(request, ResultMap.success().put("id", criteria.getId()));
        } else {
            return wrapMessage(request, ResultMap.dim());
        }
    }

    @RequestMapping(value = "/notice/{id}", method = RequestMethod.PUT)
    @PermissionVerify(value = "notice_update")
    @ResponseBody
    public ResultMap updateNotice(HttpServletRequest request, @PathVariable String id, @ModelAttribute("criteria") @Valid NoticeCriteriaStr criteriaStr, BindingResult result) {
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
        NoticeCriteria criteria = criteriaStr.toNoticeCriteria();
        criteria.setId(numberId);
        logger.debug("修改Notice:{}", criteria);
        if (criteria.getVersion() == null) {
            Notice notice = noticeService.find(criteria.getId());
            if (notice == null) {
                return wrapMessage(request, ResultMap.notExist(), id);
            }
            criteria.effective(notice);
            if (noticeService.update(notice)) {
                return wrapMessage(request, ResultMap.success());
            } else {
                return wrapMessage(request, ResultMap.dim());
            }
        }
        if (noticeService.update(criteria.toNotice())) {
            return wrapMessage(request, ResultMap.success());
        } else {
            return wrapMessage(request, ResultMap.dim());
        }
    }

    @RequestMapping(value = "/notice/{id}", method = RequestMethod.DELETE)
    @PermissionVerify(value = "notice_delete")
    @ResponseBody
    public ResultMap deleteNotice(HttpServletRequest request, @PathVariable String id) {
        Long numberId;
        try {
            numberId = Long.valueOf(id);
        } catch (NumberFormatException e) {
            logger.error("输入转换失败", e);
            return wrapMessage(request, ResultMap.notExist(), id);
        }
        logger.debug("删除Notice:{}", id);
        if (noticeService.delete(numberId)) {
            return wrapMessage(request, ResultMap.success());
        } else {
            return wrapMessage(request, ResultMap.dim());
        }
    }
}
