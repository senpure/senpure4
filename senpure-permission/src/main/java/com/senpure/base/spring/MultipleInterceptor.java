package com.senpure.base.spring;

import com.alibaba.fastjson.JSON;
import com.senpure.base.menu.Menu;
import com.senpure.base.result.ResultMap;
import com.senpure.base.struct.KeyValue;
import com.senpure.base.struct.LoginedAccount;
import com.senpure.base.util.Download;
import com.senpure.base.util.Http;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Map;


public class MultipleInterceptor extends InterceptorSupport {


    // private PropertyFilter filter= new HibernateFastJsonFilter();
    @Autowired
    private LocaleResolver localeResolver;

    private Menu home = new Menu();
    private List<Menu> root = new ArrayList<>();
    private List<KeyValue<String, String>> accountValues;
    private List<Menu> menus=new ArrayList<>(16);

    public void setAccountValues(List<KeyValue<String, String>> accountValues) {
        this.accountValues = accountValues;
    }

    public List<KeyValue<String, String>> getAccountValues() {
        return accountValues;
    }

    public List<Menu> getMenus() {
        return menus;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
                           ModelAndView modelAndView) throws Exception {

        // modelAndView.addAllObjects(JSON.toJSONString(MenuUtil.getMenuContext(request)));

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
            //logger.debug("http state"+response.getStatus());
            //加入菜单......
            String view = modelAndView.getViewName();

            if (view.contains("redirect")) {
                logger.debug("重定向请求，不必加入菜单项");
                return;
            }

            LoginedAccount account = Http.getSubject(request, LoginedAccount.class);
            String menuJosn;
            if (account != null) {
                account.getNormalValueMap().forEach( (key,normalValue)-> modelAndView.addObject(normalValue.getKey(),normalValue.getValue()));
                   menuJosn = JSON.toJSONString(account.getViewMenus());

            } else {
                accountValues.forEach(KeyValue -> modelAndView.addObject(KeyValue.getKey(), KeyValue.getValue()));
                menuJosn = JSON.toJSONString(menus);
            }
            logger.debug("accountValues {}",accountValues.toString());

            logger.debug("menuJson {}", menuJosn);
            logger.debug("view:" + modelAndView.getViewName());
            modelAndView.addObject("menuJson", menuJosn);
          //  modelAndView.addObject("top.key", "top.key");
            //Map<String, Object> context = new HashMap<>();
            //context.put("menuRoot", root);
            //context.put("menuHome", home);
            // modelAndView.addAllObjects(context);
            Locale locale = localeResolver.resolveLocale(request);
            String viewLocale;
            if (locale.getCountry().length() > 0) {
                viewLocale = locale.getLanguage() + "-" + locale.getCountry();
            } else {
                viewLocale = locale.getLanguage();
            }

            modelAndView.addObject("viewLocale", viewLocale);

        } else {
            logger.debug("modelAndView is null");
        }

        super.postHandle(request, response, handler, modelAndView);
    }


    public static void main(String[] args) {
        Locale locale = Locale.CHINA;
        System.out.println(locale.getCountry());
    }
}
