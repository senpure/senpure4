package com.senpure.base.controller;

import com.senpure.base.result.Result;
import com.senpure.base.result.ResultMap;
import com.senpure.base.spring.BaseController;
import com.senpure.base.util.Assert;
import com.senpure.base.util.Http;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * Created by 罗中正 on 2018/1/13 0013.
 */
@Controller
@RequestMapping("/authorize")
public class AuthorizeController extends BaseController {

    @RequestMapping(value = "/forbidden", method = RequestMethod.GET)
    @ResponseBody
    public ResultMap notPermission(HttpServletRequest request) {
        List<Object> args = (List<Object>) request.getAttribute("lackArgs");
        ResultMap resultMap;
        if (args.size() == 3) {
            resultMap =wrapMessage(request, ResultMap.result(Result.LACK_OF_PERMISSION_RESOURCE_INCORRECT).addMessageArgs(args));
        }
        else {
            resultMap =wrapMessage(request, ResultMap.result(Result.LACK_OF_PERMISSION).addMessageArgs(args));
        }
        if (!Http.isAjaxRequest(request)) {
            Assert.error(resultMap.getMessage());
        }
        return  resultMap;
    }

}
