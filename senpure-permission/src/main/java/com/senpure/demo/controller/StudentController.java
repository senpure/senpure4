package com.senpure.demo.controller;

import com.senpure.base.result.ResultMap;
import com.senpure.base.spring.BaseController;
import com.senpure.demo.criteria.StudentCriteriaStr;
import com.senpure.demo.criteria.StudentCriteria;
import com.senpure.demo.service.StudentService;
import com.senpure.demo.model.Student;
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
@MenuGenerator(id = 1100, text = "Student")
public class StudentController extends BaseController {

    @Autowired
    private StudentService studentService;
    private String view = "/demo/student";

    @RequestMapping(value = {"/students", "/students/{page}"}, method = {RequestMethod.GET, RequestMethod.POST})
    @PermissionVerify(name = "/demo/student_read", value = "students_read")
    @MenuGenerator(id = 1101, text = "Student Detail")

    public ModelAndView readStudents(HttpServletRequest request, @ModelAttribute("criteria") @Valid StudentCriteriaStr criteriaStr, BindingResult result) {
        if (result.hasErrors()) {
            logger.warn("客户端输入不正确{}", result);
            return incorrect(request, result, view);
        }
        StudentCriteria criteria = criteriaStr.toStudentCriteria();
        logger.debug("查询条件:{}", criteria);
        ResultMap resultMap = studentService.findPage(criteria);
        return result(request, view, resultMap);
    }

    @RequestMapping(value = "/student/{id}", method = RequestMethod.GET)
    @PermissionVerify(name = "/demo/student_read", value = "student_read")
    @ResponseBody
    public ResultMap readStudent(HttpServletRequest request, @PathVariable String id) {
        Long numberId;
        try {
            numberId = Long.valueOf(id);
        } catch (NumberFormatException e) {
            logger.error("输入转换失败", e);
            return wrapMessage(request, ResultMap.notExist(), id);
        }
        logger.debug("查询Student:{}", id);
        Student student = studentService.find(numberId);
        if (student != null) {
            return wrapMessage(request, ResultMap.success().putItem(student));
        } else {
            return wrapMessage(request, ResultMap.notExist(), id);
        }
    }


    @RequestMapping(value = "/student", method = RequestMethod.POST)
    @PermissionVerify(value = "student_create")
    @ResponseBody
    public ResultMap createStudent(HttpServletRequest request, @ModelAttribute("criteria") @Valid StudentCriteriaStr criteriaStr, BindingResult result) {
        if (result.hasErrors()) {
            logger.warn("客户端输入不正确{}", result);
            return incorrect(request, result);
        }
        StudentCriteria criteria = criteriaStr.toStudentCriteria();
        logger.debug("创建Student:{}", criteria);
        if (studentService.save(criteria)) {
            return wrapMessage(request, ResultMap.success().put("id", criteria.getId()));
        } else {
            return wrapMessage(request, ResultMap.dim());
        }
    }

    @RequestMapping(value = "/student/{id}", method = RequestMethod.PUT)
    @PermissionVerify(value = "student_update")
    @ResponseBody
    public ResultMap updateStudent(HttpServletRequest request, @PathVariable String id, @ModelAttribute("criteria") @Valid StudentCriteriaStr criteriaStr, BindingResult result) {
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
        StudentCriteria criteria = criteriaStr.toStudentCriteria();
        criteria.setId(numberId);
        logger.debug("修改Student:{}", criteria);
        if (criteria.getVersion() == null) {
            Student student = studentService.find(criteria.getId());
            if (student == null) {
                return wrapMessage(request, ResultMap.notExist(), id);
            }
            criteria.effective(student);
            if (studentService.update(student)) {
                return wrapMessage(request, ResultMap.success());
            } else {
                return wrapMessage(request, ResultMap.dim());
            }
        }
        if (studentService.update(criteria.toStudent())) {
            return wrapMessage(request, ResultMap.success());
        } else {
            return wrapMessage(request, ResultMap.dim());
        }
    }

    @RequestMapping(value = "/student/{id}", method = RequestMethod.DELETE)
    @PermissionVerify(value = "student_delete")
    @ResponseBody
    public ResultMap deleteStudent(HttpServletRequest request, @PathVariable String id) {
        Long numberId;
        try {
            numberId = Long.valueOf(id);
        } catch (NumberFormatException e) {
            logger.error("输入转换失败", e);
            return wrapMessage(request, ResultMap.notExist(), id);
        }
        logger.debug("删除Student:{}", id);
        if (studentService.delete(numberId)) {
            return wrapMessage(request, ResultMap.success());
        } else {
            return wrapMessage(request, ResultMap.dim());
        }
    }
}
