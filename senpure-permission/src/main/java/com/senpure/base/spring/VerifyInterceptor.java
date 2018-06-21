package com.senpure.base.spring;


import com.senpure.base.PermissionConstant;
import com.senpure.base.annotation.ResourceVerify;
import com.senpure.base.controller.LoginController;
import com.senpure.base.model.Account;
import com.senpure.base.model.Permission;
import com.senpure.base.model.URIPermission;
import com.senpure.base.result.Result;
import com.senpure.base.result.ResultHelper;
import com.senpure.base.result.ResultMap;
import com.senpure.base.service.AuthorizeService;
import com.senpure.base.service.PermissionService;
import com.senpure.base.service.ResourcesVerifyService;
import com.senpure.base.service.URIPermissionService;
import com.senpure.base.struct.LoginedAccount;
import com.senpure.base.struct.MergePermission;
import com.senpure.base.util.Http;
import com.senpure.base.util.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerMapping;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.mvc.method.RequestMappingInfo;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerMapping;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2017/2/7.
 */

public class VerifyInterceptor extends InterceptorSupport {
    private String loginURI = "/authorize/loginView";
    @Autowired
    private URIPermissionService uriPermissionService;
    @Autowired
    private PermissionService permissionService;
    @Autowired
    private AuthorizeService authorizeService;
    @Autowired
    private ResourcesVerifyService resourcesVerifyService;
    @Autowired
    @Qualifier("localeResolver")
    protected LocaleResolver localeResolver;


    @Autowired
    private LoginController loginController;
    private RequestMappingHandlerMapping handlerMapping;


    public void setHandlerMapping(RequestMappingHandlerMapping handlerMapping) {
        this.handlerMapping = handlerMapping;
    }

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        LoginedAccount account = Http.getSubject(request, LoginedAccount.class);
        if (account != null) {
            Account lastAccount = authorizeService.findAccount(account.getId());
            boolean login = account.getLoginTime() < lastAccount.getLoginTime()
                    //  && !account.getLoginIP().equals(accountVo.getIp())
                    ;
            if (login) {
                RequestDispatcher dispatcher = request.getRequestDispatcher(loginURI);
                ResultMap result = ResultMap.result(Result.ACCOUNT_OTHER_LOGIN);

                ResultHelper.wrapMessage(result, localeResolver.resolveLocale(request), lastAccount.getIp() == null ? "UNKNOWN" : lastAccount.getIp());
                logger.info("由于在其他地方登陆，该次请求中断,跳转登陆界面");
                logger.debug(result.toString());
                afterLogin(request, result, false);
                dispatcher.forward(request, response);
                return false;
            }
        }
        HandlerMethod method = null;
        if (handler instanceof HandlerMethod) {
            method = (HandlerMethod) handler;

        }
        if (method != null) {
            List<Permission> needPermissions = new ArrayList<>();
            String mappingInfoUri = getBestMatchingPattern(request);
            //  List<URIPermission> uriPermissions = uriPermissionService.loadURIPermissions(mapingInfoUri + "->" + method.getMethod().getName());
            List<URIPermission> uriPermissions = uriPermissionService.findByUriAndMethodOnlyCache(request.getMethod() + "->" + mappingInfoUri);
            if (uriPermissions.size() == 0) {
                logger.debug("{} > {}", request.getRequestURI(), "不需要任何权限检查");
                return true;
            } else if (account == null) {
                logger.debug("{} > {}", request.getRequestURI(), "没有登陆或者登陆超时");
                account = loginController.autoLogin(request);
                if (account == null) {
                    ResultMap result = ResultMap.result(Result.ACCOUNT_NOT_LOGIN_OR_SESSION_TIMEOUT);
                    RequestDispatcher dispatcher = request.getRequestDispatcher(loginURI);
                    ResultHelper.wrapMessage(result, localeResolver.resolveLocale(request));
                    afterLogin(request, result, false);
                    dispatcher.forward(request, response);
                    return false;
                } else {
                    logger.debug("cookie 登陆成功");
                }

            }
            StringBuilder sb = new StringBuilder();
            sb.append(account.getAccount())
                    .append("[").append(account.getName()).append("]")
                    .append(" 通过 ")
                    .append(request.getRequestURI())
                    .append(" 访问 ").append(method.getMethod().getName())
                    .append(" 方法 ");
            boolean pass = false;
            for (URIPermission uriPermission : uriPermissions) {
                //needPermissions.add(uriPermission.getPermission());
                needPermissions.add(permissionService.find(uriPermission.getPermissionId()));
            }
            for (Permission permission : needPermissions) {
                logger.debug("{} 需要 [ {} ] 权限[{},{}] ", sb.toString(), permission.getName(), permission.getReadableName(), permission.getType());
                if (permission.getType().equals(PermissionConstant.PERMISSION_TYPE_NORMAL)) {
                    pass = hasPermission(account, permission.getName());

                } else if (permission.getType().equals(PermissionConstant.PERMISSION_TYPE_OWNER)) {
                    pass = hasPermission(account, permission.getName());
                    if (pass) {
                        ResourceVerify[] rs = method.getMethod().getAnnotationsByType(ResourceVerify.class);
                        for (ResourceVerify r : rs) {
                            String resourceId = null;
                            String uri = request.getRequestURI();
                            int offset = r.offset();
                            int first = StringUtil.indexOf(mappingInfoUri, "{", offset);
                            if (first < 0) {
                                continue;
                            }
                            int formIndex = -1;
                            int count = 0;
                            while (true) {
                                formIndex = mappingInfoUri.indexOf("/", formIndex + 1);
                                if (formIndex > first || formIndex < 0) {
                                    break;
                                } else {
                                    count++;
                                }
                            }
                            first = StringUtil.indexOf(uri, "/", count);
                            int sencond = uri.indexOf("/", first + 1);
                            if (sencond > 0) {
                                resourceId = uri.substring(first + 1, sencond);
                            } else {
                                resourceId = uri.substring(first + 1);
                            }
                            logger.debug("resourceId = {}", resourceId);
                            if (resourceId.equals(PermissionConstant.ALL_OPOTION_STRING)) {
                                pass = true;
                            } else {
                                pass = resourcesVerifyService.verify(r.value(), account.getId(), resourceId);
                            }
                            if (!pass) {
                                break;
                            }

                        }
                    }
                }
                if (pass) {
                    break;
                }
            }
            if (!pass) {
                logger.warn("{}[{}] 没有权限 {} > {}", account.getAccount(), account.getName(), request.getMethod(), request.getRequestURI());
                RequestDispatcher dispatcher = request.getRequestDispatcher("/authorize/forbidden");
                dispatcher.forward(request, response);
                return false;
            }

        }
        return true;
    }

    private boolean hasPermission(LoginedAccount account, Permission permission) {
        int size = account.getPermissions().size();

        for (int i = 0; i < size; i++) {
            MergePermission mergePermission = account.getPermissions().get(i);
            logger.debug(mergePermission.toString());
            if (mergePermission.getId() == permission.getId()) {
                if (mergePermission.getExpiry() > 0 && mergePermission.getExpiry() > System.currentTimeMillis()) {
                    account.getPermissions().remove(i);
                    return false;
                }
                return true;
            }
        }
        if (account.getAccount().equalsIgnoreCase(PermissionConstant.NAME)) {
            return true;
        }
        return false;
    }

    private boolean hasPermission(LoginedAccount account, String permissionName) {
        int size = account.getPermissions().size();
        for (int i = 0; i < size; i++) {
            MergePermission mergePermission = account.getPermissions().get(i);
            if (mergePermission.getName().equals(permissionName)) {
                if (mergePermission.getExpiry() > 0 && mergePermission.getExpiry() > System.currentTimeMillis()) {
                    account.getPermissions().remove(i);
                    return false;
                }
                return true;
            }
        }
        if (account.getAccount().equalsIgnoreCase(PermissionConstant.NAME)) {
            return true;
        }
        return false;
    }

    private void afterLogin(HttpServletRequest request, ResultMap result, boolean checkLogin) {
        request.setAttribute("checkLogin", checkLogin);
        request.setAttribute("args", result);
        if ("get".equalsIgnoreCase(request.getMethod())) {
            String uri = request.getRequestURI();
            //uri = uri.substring(uri.indexOf("/"), 2);
            logger.debug("get 请求，登陆后直接重定向....,uri=" + uri);
            Http.setToSession(request, "loginToURI", uri);
        } else {
            String referer = request.getHeader("referer");
            if (referer != null) {
                logger.debug("从" + referer + "进入，登陆后，调用浏览器，返回该页面");
                //Http.set(request, "loginReferer", true);
                request.setAttribute("loginReferer", true);
            }
//		String uri = request.getRequestURI();
//		uri = uri.substring(uri.indexOf("/", 2));
//		logger.debug("post请求，登陆后同样直接访问.,会出现参数缺失情况...,uri=" + uri);
//		Http.set(request, "loginToURI", uri);
        }
    }

    private String getBestMatchingPattern(HttpServletRequest request) {
        return request.getParameter(HandlerMapping.BEST_MATCHING_PATTERN_ATTRIBUTE);
    }

    private String getMappingInfoUri(HttpServletRequest request) {
        Map<RequestMappingInfo, HandlerMethod> map = handlerMapping.getHandlerMethods();
        Iterator<Map.Entry<RequestMappingInfo, HandlerMethod>> iterator = map.entrySet().iterator();
        RequestMappingInfo temp = null;
        while (iterator.hasNext()) {
            Map.Entry<RequestMappingInfo, HandlerMethod> entry = iterator.next();
            RequestMappingInfo info = entry.getKey();
            //temp2 只会包含一个当前请求的uri
            RequestMappingInfo temp2 = info.getMatchingCondition(request);
            if (temp2 != null) {
                //logger.debug("temp2" + temp2 + "," + temp2.getPatternsCondition().getPatterns() + " info is" + info);
                if (temp == null) {
                    temp = temp2;
                } else {
                    int result = temp.compareTo(temp2, request);
                    temp = result < 0 ? temp : temp2;
                }
            }
        }
        if (temp != null) {
            return temp.getPatternsCondition().getPatterns().iterator().next();
        }
        return null;
    }
}
