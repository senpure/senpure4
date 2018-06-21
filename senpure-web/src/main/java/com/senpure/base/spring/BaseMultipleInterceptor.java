package com.senpure.base.spring;

import com.alibaba.fastjson.JSON;
import com.senpure.base.result.ResultMap;
import com.senpure.base.util.Download;
import com.senpure.base.util.Http;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.util.Map;


public class BaseMultipleInterceptor extends InterceptorSupport {

    // private PropertyFilter filter= new HibernateFastJsonFilter();
@Autowired
    private LocaleResolver localeResolver;

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
                           ModelAndView modelAndView) throws Exception {

        if (modelAndView != null) {
            logger.debug(modelAndView.toString());
            Map<String, Object> model = modelAndView.getModel();
            ResultMap result = (ResultMap) model.get("action.result");
            if (result != null) {
                if (Http.isAjaxRequest(request)) {
                    Http.returnJson(response, JSON.toJSONString(result));
                    modelAndView.clear();
                    //modelAndView = null;
                    return;

                } else {
                    // 下载文件
                    if (result.isSuccess() && result.getFile() != null) {
                        File file = result.getFile();
                        Download.execute(response, file, result.getFileName(), result.isDelete());
                        //modelAndView.setViewName(null);
                        modelAndView.clear();
                        //modelAndView = null;
                        return;
                    }
                    // 处理页面视图，将result转换成一级字段
                    model.remove("action.result");
                    modelAndView.addAllObjects(result);
                }
            }


        } else {
            logger.debug("modelAndView is null");
        }

        super.postHandle(request, response, handler, modelAndView);
    }




}
