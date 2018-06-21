package com.senpure.base.criterion;


import com.senpure.base.struct.PatternDate;
import com.senpure.base.validator.DynamicDate;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.Pattern;
import java.io.Serializable;

/**
 * Created by 罗中正 on 2017/5/15.
 */
public class CriteriaStr implements Serializable {

    public static final String ORDER_DESC = "DESC";
    public static final String ORDER_ASC = "ASC";

    private static final long serialVersionUID = 1L;

    private String startDate, endDate;

    @DynamicDate
    private PatternDate startDateValid = new PatternDate();
    @DynamicDate
    private PatternDate endDateValid = new PatternDate();

    @Min(value = 1, message = "{input.error}")
    private String page = "1";
    @Max(value = 200, message = "{input.error}")
    private String pageSize = "15";
    //前端必须分页
    @Pattern(regexp = "true", message = "{input.error}")
    private String usePage = "true";

    private String datePattern = "yyyy-MM-dd HH:mm:ss";

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        if (startDate != null && startDate.trim().length() == 0) {
            startDate = null;
        }
        this.startDate = startDate;
        startDateValid.setDateStr(startDate);
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        if (endDate != null && endDate.trim().length() == 0) {
            endDate = null;
        }
        this.endDate = endDate;
        endDateValid.setDateStr(endDate);
    }


    protected PatternDate getStartDateValid() {
        return startDateValid;
    }


    protected PatternDate getEndDateValid() {
        return endDateValid;
    }

    public String getDatePattern() {
        return datePattern;
    }

    public void setDatePattern(String datePattern) {
        if (datePattern != null && datePattern.trim().length() == 0) {
            this.datePattern = null;
            return;
        }
        this.datePattern = datePattern;
        startDateValid.setPattern(datePattern);
        endDateValid.setPattern(datePattern);
    }

    public void setPageSize(String pageSize) {
        this.pageSize = pageSize;
    }

    public String getPage() {
        return page;
    }

    public void setPage(String page) {
        this.page = page;
    }

    public String getPageSize() {
        return pageSize;
    }

    public String getUsePage() {
        return usePage;
    }

    public void setUsePage(String usePage) {
        this.usePage = usePage;
    }

    public Criteria toCriteria() {
        Criteria criteria = new Criteria();
        criteria.setUsePage(Boolean.valueOf(getUsePage()));
        criteria.setPage(Integer.valueOf(getPage()));
        criteria.setPageSize(Integer.valueOf(getPageSize()));
        criteria.setStartDate(startDateValid.getDate());
        criteria.setEndDate(endDateValid.getDate());
        return criteria;
    }

    protected void beforeStr(StringBuilder sb) {
        sb.append("Criteria{");
    }

    protected void dateStr(StringBuilder sb) {
        if (getStartDate() != null) {
            sb.append("startDate=").append(getStartDate()).append(",");
        }
        if (getEndDate() != null) {
            sb.append("endDate=").append(getEndDate()).append(",");
        }
    }

    protected void afterStr(StringBuilder sb) {
        sb.append("page=").append(getPage()).append(",");
        sb.append("pageSize=").append(getPageSize()).append(",");
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
