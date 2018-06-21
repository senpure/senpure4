package com.senpure.template.sovereignty;

/**
 * TemplateBean
 *
 * @author 罗中正
 * @email senpure@senpure.com
 * @github https://github.com/senpure
 * @date 2018-05-08
 */
public class TemplateBean {

    private String sovereignty;

    private int beforeSovereigntyCount = 0;
    private int afterSovereigntyCount = 0;

    public final String getSovereignty() {
        StackTraceElement[] stackTraceElements = Thread.currentThread().getStackTrace();
        if (stackTraceElements[6].getClassName().
                equals("freemarker.ext.beans.BeansWrapper")||
                stackTraceElements[6].getClassName().
                        equals("freemarker.ext.beans.BeanModel")) {
            afterSovereigntyCount++;
        }
        return sovereignty;
    }

    public void setSovereignty(String sovereignty) {
        this.sovereignty = sovereignty;
    }

    public final boolean checkSovereignty() {
        boolean result = beforeSovereigntyCount < afterSovereigntyCount;
        afterSovereigntyCount = 0;
        beforeSovereigntyCount = 0;
        return result;
    }
}
