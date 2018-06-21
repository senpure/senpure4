package com.senpure.base.criterion;

import com.senpure.base.util.DateFormatUtil;
import com.senpure.base.util.TimeCalculator;

import java.util.Date;

/**
 * CriteriaHelper
 *
 * @author 罗中正
 * @email senpure@senpure.com
 * @github https://github.com/senpure
 * @time 2018-06-06 18:20:51
 */
public class CriteriaHelper {


    public static void dayBegin(CriteriaStr criteriaStr,String pattern) {
        Date date =
                TimeCalculator.getDayBegin().getTime();
        TimeCalculator.toDayBegin(date);
        criteriaStr.setDatePattern(pattern);
        criteriaStr.setStartDate(DateFormatUtil.getDateFormat(pattern).format(date));

    }
}
