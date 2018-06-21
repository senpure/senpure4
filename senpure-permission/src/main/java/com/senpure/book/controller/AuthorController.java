package com.senpure.book.controller;

import com.senpure.base.result.ResultMap;
import com.senpure.base.spring.BaseController;
import com.senpure.book.criteria.AuthorCriteriaStr;
import com.senpure.book.criteria.AuthorCriteria;
import com.senpure.book.service.AuthorService;
import com.senpure.book.model.Author;
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
 * @version 2018-1-25 15:15:45
 */
@Controller
@RequestMapping("/book")
@MenuGenerator(id = 3029800, text = "Author")
public class AuthorController extends BaseController {

    @Autowired
    private AuthorService authorService;
    private String view = "/book/author";

    @RequestMapping(value = {"/authors", "/authors/{page}"}, method = {RequestMethod.GET, RequestMethod.POST})
   // @GetMapping(value = {"/authors", "/authors/{page}"})
   // @PostMapping(value = {"/authors", "/authors/{page}"})
    @PermissionVerify(name = "/book/author_read", value = "authors_read")
    @MenuGenerator(id = 3029801, text = "Author Detail")

    public ModelAndView readAuthors(HttpServletRequest request, @ModelAttribute("criteria") @Valid AuthorCriteriaStr criteriaStr, BindingResult result) {
        if (result.hasErrors()) {
            logger.warn("客户端输入不正确{}", result);
            return incorrect(request, result, view);
        }
        AuthorCriteria criteria = criteriaStr.toAuthorCriteria();
        logger.debug("查询条件:{}", criteria);
        ResultMap resultMap = authorService.findPage(criteria);
        return result(request, view, resultMap);
    }

    @GetMapping(value = "/author/{id}")
    @PermissionVerify(name = "/book/author_read", value = "author_read")
    @ResponseBody
    public ResultMap readAuthor(HttpServletRequest request, @PathVariable String id) {
        Long numberId;
        try {
            numberId = Long.valueOf(id);
        } catch (NumberFormatException e) {
            logger.error("输入转换失败", e);
            return wrapMessage(request, ResultMap.notExist(), id);
        }
        logger.debug("查询Author:{}", id);
        Author author = authorService.find(numberId);
        if (author != null) {
            return wrapMessage(request, ResultMap.success().putItem(author));
        } else {
            return wrapMessage(request, ResultMap.notExist(), id);
        }
    }


    @RequestMapping(value = "/author", method = RequestMethod.POST)
    @PermissionVerify(value = "author_create")
    @ResponseBody
    public ResultMap createAuthor(HttpServletRequest request, @ModelAttribute("criteria") @Valid AuthorCriteriaStr criteriaStr, BindingResult result) {
        if (result.hasErrors()) {
            logger.warn("客户端输入不正确{}", result);
            return incorrect(request, result);
        }
        AuthorCriteria criteria = criteriaStr.toAuthorCriteria();
        logger.debug("创建Author:{}", criteria);
        if (authorService.save(criteria)) {
            return wrapMessage(request, ResultMap.success().put("id", criteria.getId()));
        } else {
            return wrapMessage(request, ResultMap.dim());
        }
    }

    @RequestMapping(value = "/author/{id}", method = RequestMethod.PUT)
    @PermissionVerify(value = "author_update")
    @ResponseBody
    public ResultMap updateAuthor(HttpServletRequest request, @PathVariable String id, @ModelAttribute("criteria") @Valid AuthorCriteriaStr criteriaStr, BindingResult result) {
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
        AuthorCriteria criteria = criteriaStr.toAuthorCriteria();
        criteria.setId(numberId);
        logger.debug("修改Author:{}", criteria);
        if (criteria.getVersion() == null) {
            Author author = authorService.find(criteria.getId());
            if (author == null) {
                return wrapMessage(request, ResultMap.notExist(), id);
            }
            criteria.effective(author);
            if (authorService.update(author)) {
                return wrapMessage(request, ResultMap.success());
            } else {
                return wrapMessage(request, ResultMap.dim());
            }
        }
        if (authorService.update(criteria.toAuthor())) {
            return wrapMessage(request, ResultMap.success());
        } else {
            return wrapMessage(request, ResultMap.dim());
        }
    }

    @RequestMapping(value = "/author/{id}", method = RequestMethod.DELETE)
    @PermissionVerify(value = "author_delete")
    @ResponseBody
    public ResultMap deleteAuthor(HttpServletRequest request, @PathVariable String id) {
        Long numberId;
        try {
            numberId = Long.valueOf(id);
        } catch (NumberFormatException e) {
            logger.error("输入转换失败", e);
            return wrapMessage(request, ResultMap.notExist(), id);
        }
        logger.debug("删除Author:{}", id);
        if (authorService.delete(numberId)) {
            return wrapMessage(request, ResultMap.success());
        } else {
            return wrapMessage(request, ResultMap.dim());
        }
    }
}
