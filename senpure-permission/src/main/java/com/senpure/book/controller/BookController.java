package com.senpure.book.controller;

import com.senpure.base.result.ResultMap;
import com.senpure.base.spring.BaseController;
import com.senpure.book.criteria.BookCriteriaStr;
import com.senpure.book.criteria.BookCriteria;
import com.senpure.book.service.BookService;
import com.senpure.book.model.Book;
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
@MenuGenerator(id = 3029700, text = "Book")
public class BookController extends BaseController {

    @Autowired
    private BookService bookService;
    private String view = "/book/book";

    @RequestMapping(value = {"/books", "/books/{page}"}, method = {RequestMethod.GET, RequestMethod.POST})
    @PermissionVerify(name = "/book/book_read", value = "books_read")
    @MenuGenerator(id = 3029701, text = "Book Detail")

    public ModelAndView readBooks(HttpServletRequest request, @ModelAttribute("criteria") @Valid BookCriteriaStr criteriaStr, BindingResult result) {
        if (result.hasErrors()) {
            logger.warn("客户端输入不正确{}", result);
            return incorrect(request, result, view);
        }
        BookCriteria criteria = criteriaStr.toBookCriteria();
        logger.debug("查询条件:{}", criteria);
        ResultMap resultMap = bookService.findPage(criteria);
        return result(request, view, resultMap);
    }

    @RequestMapping(value = "/book/{id}", method = RequestMethod.GET)
    @PermissionVerify(name = "/book/book_read", value = "book_read")
    @ResponseBody
    public ResultMap readBook(HttpServletRequest request, @PathVariable String id) {
        Long numberId;
        try {
            numberId = Long.valueOf(id);
        } catch (NumberFormatException e) {
            logger.error("输入转换失败", e);
            return wrapMessage(request, ResultMap.notExist(), id);
        }
        logger.debug("查询Book:{}", id);
        Book book = bookService.find(numberId);
        if (book != null) {
            return wrapMessage(request, ResultMap.success().putItem(book));
        } else {
            return wrapMessage(request, ResultMap.notExist(), id);
        }
    }


    @RequestMapping(value = "/book", method = RequestMethod.POST)
    @PermissionVerify(value = "book_create")
    @ResponseBody
    public ResultMap createBook(HttpServletRequest request, @ModelAttribute("criteria") @Valid BookCriteriaStr criteriaStr, BindingResult result) {
        if (result.hasErrors()) {
            logger.warn("客户端输入不正确{}", result);
            return incorrect(request, result);
        }
        BookCriteria criteria = criteriaStr.toBookCriteria();
        logger.debug("创建Book:{}", criteria);
        if (bookService.save(criteria)) {
            return wrapMessage(request, ResultMap.success().put("id", criteria.getId()));
        } else {
            return wrapMessage(request, ResultMap.dim());
        }
    }

    @RequestMapping(value = "/book/{id}", method = RequestMethod.PUT)
    @PermissionVerify(value = "book_update")
    @ResponseBody
    public ResultMap updateBook(HttpServletRequest request, @PathVariable String id, @ModelAttribute("criteria") @Valid BookCriteriaStr criteriaStr, BindingResult result) {
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
        BookCriteria criteria = criteriaStr.toBookCriteria();
        criteria.setId(numberId);
        logger.debug("修改Book:{}", criteria);
        if (criteria.getVersion() == null) {
            Book book = bookService.find(criteria.getId());
            if (book == null) {
                return wrapMessage(request, ResultMap.notExist(), id);
            }
            criteria.effective(book);
            if (bookService.update(book)) {
                return wrapMessage(request, ResultMap.success());
            } else {
                return wrapMessage(request, ResultMap.dim());
            }
        }
        if (bookService.update(criteria.toBook())) {
            return wrapMessage(request, ResultMap.success());
        } else {
            return wrapMessage(request, ResultMap.dim());
        }
    }

    @RequestMapping(value = "/book/{id}", method = RequestMethod.DELETE)
    @PermissionVerify(value = "book_delete")
    @ResponseBody
    public ResultMap deleteBook(HttpServletRequest request, @PathVariable String id) {
        Long numberId;
        try {
            numberId = Long.valueOf(id);
        } catch (NumberFormatException e) {
            logger.error("输入转换失败", e);
            return wrapMessage(request, ResultMap.notExist(), id);
        }
        logger.debug("删除Book:{}", id);
        if (bookService.delete(numberId)) {
            return wrapMessage(request, ResultMap.success());
        } else {
            return wrapMessage(request, ResultMap.dim());
        }
    }
}
