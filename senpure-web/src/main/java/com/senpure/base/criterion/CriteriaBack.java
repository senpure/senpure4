package com.senpure.base.criterion;


import com.senpure.base.struct.PatternDate;
import com.senpure.base.util.DateFormatUtil;
import com.senpure.base.validator.DynamicDate;

import javax.validation.constraints.AssertTrue;
import java.io.Serializable;
import java.util.Date;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Created by 罗中正 on 2017/5/15.
 */
public class CriteriaBack implements Serializable {

    public static final String ORDER_DESC = "DESC";
    public static final String ORDER_ASC = "ASC";

    private static final long serialVersionUID = 1L;

    private String startDate, endDate;

    private int page = 1;
    private int pageSize = 15;
    //前端必须分页
    @AssertTrue(message = "{input.error}")
    private boolean usePage = true;

    protected Map<String, String> order = new LinkedHashMap<>();
    @DynamicDate
    private PatternDate startTime = new PatternDate(), endTime = new PatternDate();
    private String datePattern = "yyyy-MM-dd HH:mm:ss";

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        if (startDate != null && startDate.trim().length() == 0) {
            return;
        }
        this.startDate = startDate;
        startTime.setDateStr(startDate);
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        if (endDate != null && endDate.trim().length() == 0) {
            return;
        }
        this.endDate = endDate;
        endTime.setDateStr(endDate);
    }


    public Date getSdate() {
        return startTime.getDate();
    }

    public Date getEdate() {
        return endTime.getDate();
    }


    public String getDatePattern() {
        return datePattern;
    }

    public void setDatePattern(String datePattern) {
        this.datePattern = datePattern;
        startTime.setPattern(datePattern);
        endTime.setPattern(datePattern);
    }

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        if (page < 1) {
            page = 1;
        }
        this.page = page;
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

    public Integer getFirstResult() {
        return page * pageSize - pageSize;
    }

    public int getMaxResults() {
        return pageSize;
    }


    public boolean isHasOrder() {
        return order.size() > 0;
    }

    /**
     * copy 保证前端无法注入
     *
     * @return copy order
     */
    public final Map<String, String> getOrder() {
        return new LinkedHashMap<>(order);
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
        Date date = getEdate();
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
        dateCache(sb);
        afterStr(sb);
        return sb.toString();
    }

    protected void dateStr(StringBuilder sb) {
        if (getStartDate() != null) {
            sb.append("startDate=").append(getStartDate()).append(",");
        }
        if (getEndDate() != null) {
            sb.append("endDate=").append(getEndDate()).append(",");
        }
    }

    protected void dateCache(StringBuilder sb) {
        if (getSdate() != null) {
            sb.append("startDate=").append(DateFormatUtil.
                    getDateFormat(DateFormatUtil.DFP_Y2MS).
                    format(getSdate())).append(",");
        }
        if (getEdate() != null) {
            sb.append("endDate=").append(DateFormatUtil.
                    getDateFormat(DateFormatUtil.DFP_Y2MS).
                    format(getEdate())).append(",");
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
