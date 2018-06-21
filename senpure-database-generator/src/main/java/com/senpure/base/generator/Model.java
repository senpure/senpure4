package com.senpure.base.generator;

import java.util.*;

/**
 * Created by 罗中正 on 2017/9/21.
 */
public class Model {
    private boolean hasExplain;
    private String explain;
    private boolean hasDate;
    private String tableName;
    private String entityPackage;
    private String modelPackage;
    private String mapperPackage;
    private String criteriaPackage;
    private String servicePackage;
    private String controllerPackage;

    private String module;
    private String name;
    private String tableType;
    private boolean localCache;
    private boolean springCache;
    private boolean springLocal;
    private boolean cache;
    private int dateFieldNum = 0;
    //class 和super class
    private List<String> clazzs = new ArrayList<>();
    private Map<String, ModelField> modelFieldMap = new LinkedHashMap<>();
    //外键放在前面
    private Map<String, ModelField> criteriaFieldMap = new LinkedHashMap<>();

    AfterReadColum afterReadColum = new AfterReadColum();
    private ModelField id;

    private ModelField version;
    private ModelField dateField;
    private ModelField table;
    private List<ModelField> findModeFields = new ArrayList<>();

    private Model child;
    private ModelField childField;

    private boolean generatePermission=true;

    private boolean generateMenu=true;

    private int menuId=0;
    private boolean useCriteriaStr=true;

    private List<ModelField> allFields = new ArrayList<>();


    public void dateFieldIncr() {
        dateFieldNum++;
    }

    public boolean isHasDate() {
        return hasDate;
    }

    public void setHasDate(boolean hasDate) {
        this.hasDate = hasDate;
    }

    public String getTableName() {
        return tableName;
    }

    public void setTableName(String tableName) {
        this.tableName = tableName;
    }

    public Map<String, ModelField> getModelFieldMap() {
        return modelFieldMap;
    }

    public void setModelFieldMap(Map<String, ModelField> modelFieldMap) {
        this.modelFieldMap = modelFieldMap;
    }

    public ModelField getId() {
        return id;
    }

    public void setId(ModelField id) {
        this.id = id;
    }

    public AfterReadColum getAfterReadColum() {
        return afterReadColum;
    }

    public void setAfterReadColum(AfterReadColum afterReadColum) {
        this.afterReadColum = afterReadColum;
    }

    public ModelField getVersion() {
        return version;
    }

    public void setVersion(ModelField version) {
        this.version = version;
    }

    public String getModelPackage() {
        return modelPackage;
    }

    public void setModelPackage(String modelPackage) {
        this.modelPackage = modelPackage;
    }

    public String getMapperPackage() {
        return mapperPackage;
    }

    public void setMapperPackage(String mapperPackage) {
        this.mapperPackage = mapperPackage;
    }

    public List<String> getClazzs() {
        return clazzs;
    }

    public void setClazzs(List<String> clazzs) {
        this.clazzs = clazzs;
    }

    public boolean isHasExplain() {
        return hasExplain;
    }

    public ModelField getDateField() {
        return dateField;
    }

    public int getDateFieldNum() {
        return dateFieldNum;
    }

    public String getExplain() {
        return explain;
    }

    public void setExplain(String explain) {
        this.explain = explain;
        hasExplain = true;
    }

    public String getEntityPackage() {
        return entityPackage;
    }

    public void setEntityPackage(String entityPackage) {
        this.entityPackage = entityPackage;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCriteriaPackage() {
        return criteriaPackage;
    }

    public void setCriteriaPackage(String criteriaPackage) {
        this.criteriaPackage = criteriaPackage;
    }

    public String getServicePackage() {
        return servicePackage;
    }

    public void setServicePackage(String servicePackage) {
        this.servicePackage = servicePackage;
    }

    public boolean isLocalCache() {
        return localCache;
    }

    public void setLocalCache(boolean localCache) {
        this.localCache = localCache;
    }

    public boolean isSpringCache() {
        return springCache;
    }

    public void setSpringCache(boolean springCache) {
        this.springCache = springCache;
    }

    public boolean isSpringLocal() {
        return springLocal;
    }

    public void setSpringLocal(boolean springLocal) {
        this.springLocal = springLocal;
    }

    public boolean isCache() {
        return cache;
    }

    public void setCache(boolean cache) {
        this.cache = cache;
    }

    public void setDateField(ModelField dateField) {
        this.dateField = dateField;
    }


    public ModelField getTable() {
        return table;
    }

    public void setTable(ModelField table) {
        this.table = table;
    }

    public String getTableType() {
        return tableType;
    }

    public void setTableType(String tableType) {
        this.tableType = tableType;
    }

    private void checkAllFields()
    {
        if (allFields.size() ==0) {
            allFields.clear();
            if (id != null) {
                allFields.add(id);
            }
            if (version != null) {
                allFields.add(version);
            }
            allFields.addAll(modelFieldMap.values());
        }
    }

    public int getXmlMaxLen() {
        checkAllFields();
        int maxLen = 0;
        for (ModelField modelField : allFields) {
            int len = modelField.getXmlLen();
            maxLen = len > maxLen ? len : maxLen;
        }
        return maxLen + 1;
    }
    public int getColumnMaxLen()
    {
        checkAllFields();
        int maxLen = 0;
        for (ModelField modelField : allFields) {
            int len = modelField.getColumnLen();
            maxLen = len > maxLen ? len : maxLen;
        }
        return maxLen+1;
    }

    public int getNameMaxLen()
    {
        checkAllFields();
        int maxLen = 0;
        for (ModelField modelField : allFields) {
            int len = modelField.getNameLen();
            maxLen = len > maxLen ? len : maxLen;
        }
        return maxLen+1;
    }

    public int getJdbcMaxLen()
    {
        checkAllFields();
        int maxLen = 0;
        for (ModelField modelField : allFields) {
            int len = modelField.getJdbcLen();
            maxLen = len > maxLen ? len : maxLen;
        }
        return maxLen+1;
    }
    public Map<String, ModelField> getCriteriaFieldMap() {
        return criteriaFieldMap;
    }

    public void setCriteriaFieldMap(Map<String, ModelField> criteriaFieldMap) {
        this.criteriaFieldMap = criteriaFieldMap;
    }

    public List<ModelField> getFindModeFields() {
        return findModeFields;
    }

    public void setFindModeFields(List<ModelField> findModeFields) {
        this.findModeFields = findModeFields;
    }

    public String getControllerPackage() {
        return controllerPackage;
    }

    public void setControllerPackage(String controllerPackage) {
        this.controllerPackage = controllerPackage;
    }

    public String getModule() {
        return module;
    }

    public void setModule(String module) {
        this.module = module;
    }

    public boolean isGeneratePermission() {
        return generatePermission;
    }

    public void setGeneratePermission(boolean generatePermission) {
        this.generatePermission = generatePermission;
    }

    public boolean isGenerateMenu() {
        return generateMenu;
    }

    public void setGenerateMenu(boolean generateMenu) {
        this.generateMenu = generateMenu;
    }

    public int getMenuId() {
        return menuId;
    }

    public void setMenuId(int menuId) {
        this.menuId = menuId;
    }

    public Model getChild() {
        return child;
    }

    public void setChild(Model child) {
        this.child = child;
    }

    public ModelField getChildField() {
        return childField;
    }

    public void setChildField(ModelField childField) {
        this.childField = childField;
    }

    public boolean isUseCriteriaStr() {
        return useCriteriaStr;
    }

    public void setUseCriteriaStr(boolean useCriteriaStr) {
        this.useCriteriaStr = useCriteriaStr;
    }

    @Override
    public String toString() {
        return "Model{" +
                "hasDate=" + hasDate +
                ", tableName='" + tableName + '\'' +
                '}';
    }

}
