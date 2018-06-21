package com.senpure.base.struct;

import org.springframework.web.servlet.mvc.condition.PatternsRequestCondition;
import org.springframework.web.servlet.mvc.method.RequestMappingInfo;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by 罗中正 on 2018/1/23 0023.
 */
public class RestFullPattern {

    private PatternsRequestCondition patternsRequestCondition;

    private String permissionName;

    public RestFullPattern(RequestMappingInfo mappingInfo) {
        patternsRequestCondition = mappingInfo.getPatternsCondition();
        this.permissionName = patternsRequestCondition.getPatterns().iterator().next();

    }

    public RestFullPattern(RequestMappingInfo mappingInfo, String permissionName) {
        patternsRequestCondition = mappingInfo.getPatternsCondition();
        this.permissionName = permissionName;

    }

    public RestFullPattern(PatternsRequestCondition patternsRequestCondition) {
        this.patternsRequestCondition = patternsRequestCondition;
        this.permissionName = patternsRequestCondition.getPatterns().iterator().next();
    }

    public RestFullPattern(PatternsRequestCondition patternsRequestCondition, String permissionName) {
        this.patternsRequestCondition = patternsRequestCondition;
        this.permissionName = permissionName;
    }



    public RestFullPattern getMatchingCondition(HttpServletRequest request) {
        PatternsRequestCondition condition = patternsRequestCondition.getMatchingCondition(request);
        return condition == null ? null : new RestFullPattern(condition, permissionName);
    }

    public int compareTo(RestFullPattern other, HttpServletRequest request) {
        return patternsRequestCondition.compareTo(other.patternsRequestCondition, request);
    }
}
