package com.senpure.base.validator;


import com.senpure.base.struct.PatternDate;
import com.senpure.base.util.DateFormatUtil;
import com.senpure.base.util.StringUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.text.DateFormat;
import java.text.ParseException;
import java.util.Date;

/**
 * Created by 罗中正 on 2017/5/15.
 */
public class PatternDateValidator implements ConstraintValidator<DynamicDate, PatternDate> {
    private static Logger logger = LoggerFactory.getLogger(PatternDateValidator.class);
    @Override
    public void initialize(DynamicDate dynamicDate) {
    }
    @Override
    public boolean isValid(PatternDate patternDate, ConstraintValidatorContext constraintValidatorContext) {
        return valid(patternDate);
    }
    public  static  boolean valid(PatternDate patternDate)
    {
        if (StringUtil.isExistTrim(patternDate.getDateStr())) {
            String pattern=patternDate.getPattern() == null
                    ? DateFormatUtil.DFP_Y2D : patternDate.getPattern();
            DateFormat dateFormat = DateFormatUtil.getDateFormat(pattern);
            if (dateFormat == null) {
                dateFormat = DateFormatUtil.getDateFormat(DateFormatUtil.DFP_Y2D);
            }
            try {
                Date date = dateFormat.parse(patternDate.getDateStr());
                patternDate.setDate(date);
            } catch (ParseException e) {
                logger.error("时间转换出错 {} >  {}" , pattern , patternDate.getDateStr());
                return false;
            }
        }
        return true;
    }
}


