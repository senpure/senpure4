package com.senpure.base.util;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Created by 罗中正 on 2017/5/26.
 */
public class DateFormatUtil {
    /**
     * DATE FORMAT PATTERN YEAR TO MILLISECOND like 2017-12-12 12:45:50:123
     */
    public static final String DFP_Y2MS = "yyyy-MM-dd HH:mm:ss:SSS";
    /**
     * DATE FORMAT PATTERN YEAR TO SECOND like 2017-12-12
     */
    public static final String DFP_Y2S = "yyyy-MM-dd HH:mm:ss";

    /**
     * DATE FORMAT PATTERN YEAR TO Day like 2017-12-12
     */
    public static final String DFP_Y2D = "yyyy-MM-dd";
    private static ConcurrentHashMap<String, DateFormat> dateFormatMap = new ConcurrentHashMap<>();


    public static DateFormat S_Y_S = getDateFormat("yyyyMMddHHmmss");
    public static DateFormat S_Y_D = getDateFormat("yyyyMMdd");
    public static DateFormat S_Y_M = getDateFormat("yyyyMM");
    public static DateFormat getDateFormat(String pattern) {
        DateFormat dateFormat = dateFormatMap.get(pattern);
        if (dateFormat != null) {
            return dateFormat;
        }
        dateFormat = new SimpleDateFormat(pattern);
        try {
            dateFormat.format(new Date());
            dateFormatMap.putIfAbsent(pattern, dateFormat);
        } catch (Exception e) {
            Assert.error("格式化日期参数不对 " + pattern + "\n" + e.toString());
        }
        return dateFormatMap.get(pattern);
    }
}
