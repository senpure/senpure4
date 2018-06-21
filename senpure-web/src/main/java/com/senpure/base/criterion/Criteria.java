package com.senpure.base.criterion;


import com.senpure.base.util.DateFormatUtil;

import javax.validation.constraints.AssertTrue;
import java.io.Serializable;
import java.util.Date;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Created by 罗中正 on 2017/5/15.
 */
public class Criteria implements Serializable {

    public static final String ORDER_DESC = "DESC";
    public static final String ORDER_ASC = "ASC";

    private static final long serialVersionUID = 1L;

    private int page = 1;
    private int pageSize = 15;
    //前端必须分页
    @AssertTrue
    private  boolean usePage = true;
    private Date startDate;
    private Date endDate;
    private String datePattern;
    private  Map<String, String> order = new LinkedHashMap<>(8);

    public int getPage() {
        return page;
    }
    public void setPage(int page) {
        if (page < 1) {
            page = 1;
        }
        this.page = page;
    }

    public Date getStartDate() {
        return startDate;
    }
    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }
    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public boolean isUsePage() {
        return usePage;
    }

    public void setUsePage(boolean usePage) {
        this.usePage = usePage;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        if (pageSize < 5) {
            pageSize = 5;
        }
        if (pageSize > 200) {
            pageSize = 200;
        }
        this.pageSize = pageSize;
    }


    public Map<String, String> getOrder() {
        //防止前端注入
        return new LinkedHashMap<>(order);
    }

    public Integer getFirstResult() {
        return page * pageSize - pageSize;
    }

    public int getMaxResults() {
        return pageSize;
    }

    public boolean isHasOrder() {
        return order.size() > 0;
    }

    public void setDatePattern(String datePattern) {
        this.datePattern = datePattern;
    }

    protected void putSort(String name, String sort) {
        if (sort.equals(ORDER_ASC) || sort.equals(ORDER_DESC)) {
            order.put(name, sort);
        } else {
            order.remove(name);
        }
    }

    public boolean isHasDate() {
        return true;
    }
    public boolean isCanCache() {
        if (!isHasDate()) {
            return true;
        }
        //必须保证时间在当前之前，之后会不断的加入新的内容，数据不一致
        Date date = getEndDate();
        if (date == null) {
            return false;
        }
        return date.getTime() < System.currentTimeMillis();
    }
    protected void beforeStr(StringBuilder sb) {
        sb.append("Criteria{");
    }
    public String getCacheKey() {
        StringBuilder sb = new StringBuilder();
        beforeStr(sb);
        dateStr(sb);
        afterStr(sb);
        return sb.toString();
    }

    protected void dateStr(StringBuilder sb) {
        if (getStartDate() != null) {
            sb.append("startDate=").append(DateFormatUtil.
                    getDateFormat(datePattern).
                    format(getStartDate())).append(",");
        }
        if (getEndDate() != null) {
            sb.append("endDate=").append(DateFormatUtil.
                    getDateFormat(datePattern).
                    format(getEndDate())).append(",");
        }
    }
    protected void dateCache(StringBuilder sb) {
        if (getStartDate() != null) {
            sb.append("startDate=").append(DateFormatUtil.
                    getDateFormat(DateFormatUtil.DFP_Y2MS).
                    format(getStartDate())).append(",");
        }
        if (getEndDate() != null) {
            sb.append("endDate=").append(DateFormatUtil.
                    getDateFormat(DateFormatUtil.DFP_Y2MS).
                    format(getEndDate())).append(",");
        }
    }
    protected void afterStr(StringBuilder sb) {
        if (order.size() > 0) {
            Iterator<Map.Entry<String, String>> iterator = order.entrySet().iterator();
            while (iterator.hasNext()) {
                Map.Entry<String, String> entry = iterator.next();
                sb.append("column[");
                sb.append(entry.getKey()).append("]=").append(entry.getValue()).append(",");
            }

        }
        if (isUsePage()) {
            sb.append("firstResult=").append(getFirstResult()).append(",");
            sb.append("maxResults=").append(getMaxResults()).append(",");
        }
        if (sb.length() > 2) {
            sb.delete(sb.length() - 1, sb.length());
        }
        sb.append("}");
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        beforeStr(sb);
        dateStr(sb);
        afterStr(sb);
        return sb.toString();
    }

}
