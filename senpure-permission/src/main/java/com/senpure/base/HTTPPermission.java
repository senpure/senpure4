package com.senpure.base;

import javax.servlet.http.HttpServletRequest;
import java.util.*;

/**
 * Created by 罗中正 on 2018/1/22 0022.
 */
public class HTTPPermission {
    public static final int HTTP_GET = 1;
    public static final int HTTP_POST = 2;
    public static final int HTTP_PUT = 4;
    public static final int HTTP_DELETE = 8;
    public static final int HTTP_UNKNOWN = 0;

    public static final String HTTP_GET_STR = "GET";
    public static final String HTTP_POST_STR = "POST";
    public static final String HTTP_PUT_STR = "PUT";
    public static final String HTTP_DELETE_STR = "DELETE";
    public static final String HTTP_UNKNOWN_STR = "UNKNOWN";
    private static Map<String, Integer> methodMap = new HashMap<>();

    static {
        methodMap.put(HTTP_GET_STR, HTTP_GET);
        methodMap.put(HTTP_POST_STR, HTTP_POST);
        methodMap.put(HTTP_PUT_STR, HTTP_PUT);
        methodMap.put(HTTP_DELETE_STR, HTTP_DELETE);

    }


    public static List<String> allMethod() {
        return new ArrayList<>(methodMap.keySet());
    }


    public static int getMethodValue(String method) {
        Integer value = methodMap.get(method);

        return value == null ? 0 : value;
    }
    public static int getMethodValue(List<String> methods) {
        int  methodValue=0;
        for (int i = 0; i <methods.size() ; i++) {
            methodValue|= getMethodValue(methods.get(i));
        }
        return methodValue;
    }
    public static String getMethod(int methodValue) {
        String method = "";
        int methodCount = 0;
        Iterator<Map.Entry<String, Integer>> iterator = methodMap.entrySet().iterator();
        while (iterator.hasNext()) {
            Map.Entry<String, Integer> entry = iterator.next();

            if (entry.getValue() == (entry.getValue() & methodValue)) {
                if (methodCount > 0) {
                    method += " || ";
                }
                method += entry.getKey();
                methodCount++;
            }
        }
        return method;
    }

    public static boolean check(HttpServletRequest request, int value) {
        Integer method = methodMap.get(request.getMethod());
        if (method == null) {
            method = methodMap.get(HTTP_UNKNOWN_STR);
        }
        return method == (method & value);
    }


}
