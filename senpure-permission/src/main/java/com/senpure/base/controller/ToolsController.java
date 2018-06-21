package com.senpure.base.controller;

import com.senpure.base.spring.BaseController;
import com.senpure.base.util.Pinyin;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by 罗中正 on 2017/6/1.
 */
@Controller
@RequestMapping("/tools")
public class ToolsController extends BaseController {


    @RequestMapping(value = "/pinyin/{word}",method = RequestMethod.GET)
    @ResponseBody
    public String pinyin(@PathVariable String word) {

        return Pinyin.toAccount(word)[0];
    }
}
