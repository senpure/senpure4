package com.senpure.base.generator;

/**
 * Created by 罗中正 on 2017/9/21.
 */
public class ModelField {

    private boolean hasExplain;
    private String explain;
    //private public
    private String accessType = "";

    private String clazzType;
    private String name;

    private String column;

    private boolean nullable;

    private boolean javaNullable;
    private String jdbcType;
    private boolean id;
    private boolean version;
    private boolean databaseId;
    private boolean criteriaEquals = true;
    private boolean findOne=false;
    private boolean date;
    private boolean htmlShow=true;

    private boolean order=false;
    private boolean strShow=true;
    private ModelField longDate;



    public int getXmlLen() {
        String str = name + column + jdbcType;
        return str.length();

    }
    public int getColumnLen()
    {
        return column.length();
    }
    public int getNameLen()
    {
        return name.length();
    }
    public int getJdbcLen()
    {
        return jdbcType.length();
    }

    public String getAccessType() {
        return accessType;
    }

    public void setAccessType(String accessType) {
        this.accessType = accessType;
    }

    public String getClazzType() {
        return clazzType;
    }

    public void setClazzType(String clazzType) {
        this.clazzType = clazzType;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getColumn() {
        return column;
    }

    public void setColumn(String column) {
        this.column = column;
    }

    public String getJdbcType() {
        return jdbcType;
    }

    public void setJdbcType(String jdbcType) {
        this.jdbcType = jdbcType;
    }

    public boolean isId() {
        return id;
    }

    public void setId(boolean id) {
        this.id = id;
    }


    public boolean isVersion() {
        return version;
    }

    public void setVersion(boolean version) {
        this.version = version;
    }

    public boolean isHasExplain() {
        return hasExplain;
    }

    public boolean isCriteriaEquals() {
        return criteriaEquals;
    }

    public void setCriteriaEquals(boolean criteriaEquals) {
        this.criteriaEquals = criteriaEquals;
    }

    public String getExplain() {
        return explain;
    }

    public void setExplain(String explain) {
        this.explain = explain;
        hasExplain = true;
    }

    public boolean isNullable() {
        return nullable;
    }

    public void setNullable(boolean nullable) {
        this.nullable = nullable;
    }

    public boolean isJavaNullable() {
        return javaNullable;
    }

    public void setJavaNullable(boolean javaNullable) {
        this.javaNullable = javaNullable;
    }

    public boolean isDatabaseId() {
        return databaseId;
    }

    public void setDatabaseId(boolean databaseId) {
        this.databaseId = databaseId;
    }

    public boolean isFindOne() {
        return findOne;
    }

    public void setFindOne(boolean findOne) {

        order=true;
        this.findOne = findOne;
    }

    public boolean isDate() {
        return date;
    }

    public void setDate(boolean date) {
        this.date = date;
    }

    public boolean isHtmlShow() {
        return htmlShow;
    }

    public void setHtmlShow(boolean htmlShow) {
        this.htmlShow = htmlShow;
    }

    public boolean isOrder() {
        return order;
    }

    public void setOrder(boolean order) {
        this.order = order;
    }

    public boolean isStrShow() {

        return strShow;
    }

    public void setStrShow(boolean strShow) {
        this.strShow = strShow;
    }

    public ModelField getLongDate() {
        return longDate;
    }

    public void setLongDate(ModelField longDate) {
        this.longDate = longDate;
    }

    @Override
    public String toString() {
        return "ModelField{" +
                "accessType='" + accessType + '\'' +
                ", clazzType='" + clazzType + '\'' +
                ", name='" + name + '\'' +
                ", column='" + column + '\'' +
                ", jdbcType='" + jdbcType + '\'' + (id ?
                ", id='" + id : "") + '\'' +
                (version ?
                        ", version='" + version : "") + '\'' +
                '}';
    }
}
