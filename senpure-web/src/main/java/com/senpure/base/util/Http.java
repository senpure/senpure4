package com.senpure.base.util;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


public class Http {

    private static Logger logger = LoggerFactory.getLogger(Http.class);
    private static ThreadLocal<HttpServletRequest> currentRequest = new ThreadLocal<>();
    /*
     * 字段名的顺序不要变
     */
    private static int offset = 0;
    public static final int REQUEST_METHOD_GET = 1 << offset++;
    public static final int REQUEST_METHOD_POST = 1 << offset++;
    public static final int REQUEST_METHOD_DELETE = 1 << offset++;
    public static final int REQUEST_METHOD_PUT = 1 << offset++;

    public static final String GET = "get";
    public static final String POST = "post";
    public static final String PUT = "put";
    public static final String DELETE = "delete";

    public static String getIP(HttpServletRequest request) {
        return getIP(request, false);
    }

    public static String getIP(HttpServletRequest request, boolean onlyOne) {

        String ip = request.getHeader("x-forwarded-for");
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("Proxy-Client-IP");
        } else {
            return getIP(ip, onlyOne);
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("WL-Proxy-Client-IP");
        } else {
            return getIP(ip, onlyOne);
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("http_client_ip");
        } else {
            return getIP(ip, onlyOne);
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("HTTP_X_FORWARDED_FOR");
        } else {
            return getIP(ip, onlyOne);
        }

        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getRemoteAddr();
        } else {
            return getIP(ip, onlyOne);
        }

        return ip;
    }

    public  static String getIP(String ip, boolean onlyOne) {

        if (onlyOne && ip != null) {
            String[] temps = ip.split(",");

            ip = temps[temps.length - 1];
        }

        return ip;
    }

    // 单个Ip
    public static long ipToLong(String ip) {

        String[] ipstr = ip.split("\\.");
        return Long.parseLong(ipstr[0]) << 24 | Long.parseLong(ipstr[1]) << 16
                | Long.parseLong(ipstr[2]) << 8 | Long.parseLong(ipstr[3]);

    }

    public static String longToIP(long ip) {

        StringBuilder sb = new StringBuilder();
        sb.append(ip >> 24 & 0xff).append(".").append(ip >> 16 & 0xff)
                .append(".").append(ip >> 8 & 0xff).append(".")
                .append(ip & 0xff);

        return sb.toString();
    }

    public static void returnJson(HttpServletResponse response, String json) {

        response.setContentType("application/json;charset=UTF-8");
        try {
            logger.trace("返回客户端的数据:" + json);
            response.getWriter().write(json);
        } catch (IOException e) {
            logger.debug("向客户端返回数据出错", e);

        }
    }

    public static void returnText(HttpServletResponse response, String text) {

        response.setContentType("text/html;charset=UTF-8");
        try {
            logger.trace("返回客户端的数据:" + text);
            response.getWriter().write(text);
        } catch (IOException e) {
            logger.debug("向客户端返回数据出错", e);

        }
    }

    public static boolean isAjaxRequest(HttpServletRequest request) {

       // logger.debug("request.getHeader(x-requested-with):" + request.getHeader("x-requested-with"));
        return "XMLHttpRequest".equalsIgnoreCase(request.getHeader("x-requested-with"));
    }


    public static int methodToInt(HttpServletRequest request) {

        switch (request.getMethod().toLowerCase()) {

            case GET:

                return REQUEST_METHOD_GET;
            case POST:

                return REQUEST_METHOD_POST;
            case PUT:

                return REQUEST_METHOD_PUT;
            case DELETE:

                return REQUEST_METHOD_DELETE;

            default:
                break;
        }

        return 0;
    }

    /**
     * session级别
     *
     * @param request
     * @param key
     * @return
     */
    public static Object getFromSession(HttpServletRequest request, String key) {
        return request.getSession().getAttribute(key);
    }

    /**
     * session级别
     *
     * @param request
     * @param key
     */
    public static void removeFromSession(HttpServletRequest request, String key) {
        request.getSession().removeAttribute(key);
    }

    /**
     * session级别
     *
     * @param request
     * @param key
     * @param value
     */
    public static void setToSession(HttpServletRequest request, String key, Object value) {
        request.getSession().setAttribute(key, value);
    }

    public static void setSubject(HttpServletRequest request, Object account) {
        setToSession(request, "http_subject", account);
    }

    public static void removeSubject(HttpServletRequest request) {
        request.getSession().removeAttribute(
                "http_subject");
        request.getSession().invalidate();
    }
    public static <T> T getSubject(HttpServletRequest request,Class<T> clazz)
    {
        return (T) getFromSession(request,"http_subject");
    }
    public static HttpServletRequest getCurrentRequest() {

        return currentRequest.get();
    }

    public static void setCurrentRequest(HttpServletRequest request) {

        currentRequest.set(request);

    }
}
