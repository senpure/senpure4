package com.senpure.base.generator;

import org.hibernate.cfg.NamingStrategy;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by 罗中正 on 2017/10/9.
 */
public class Config {
    
    public static final String TABLE_TYPE_MIX = "MIX";
    public static final String TABLE_TYPE_SINGLE = "SINGLE";
    //覆盖
    private Map<String, Boolean> config = new HashMap<>(16);
    //缓存
    private Map<String, CacheConfig> cacheConfig = new HashMap<>(16);
    //数据库表
    private Map<String, String> tableConfig = new HashMap<>(16);
    public static final String SERVICE = "Service";
    public static final String MODEL_COVER = "ModelCover";
    public static final String MAPPER_JAVA_COVER = "MapperJavaCover";
    public static final String MAPPER_XML_COVER = "MapperXmlCover";
    public static final String CRITERIA_COVER = "CriteriaCover";
    public static final String CONTROLLER_COVER = "ControllerCover";
    public static final String SERVICE_COVER = "ServiceCover";
    public static final String VIEW_COVER = "ViewCover";
    public static final String CACHE_DEFAULT_NAME = "CacheDefaultName";
    private boolean allCover = false;
    private boolean singleCheck = false;
    private boolean generateService = true;
    private boolean generateController = true;

    private boolean generatePermission = true;

    private boolean generateMenu = true;
    //生成页面视图
    private boolean generateView = true;
    private boolean generateServiceSingleCheck = false;

    private String javaCodePart = "src/main/java/";
    private String viewPart = "src/main/web/templates/";
    private int classLevel = 2;
    private int menuStartId = 0;
    private boolean userCriteriaStr = true;
    private String defaultTableType = TABLE_TYPE_SINGLE;

    private List<String> specificSpringLocal = new ArrayList<>();

    private NamingStrategy namingStrategy;
    public void closeDefaultCacheCache() {
        setDefaultCache(false, false, false, false);
    }

    public void setDefaultCache(boolean cache, boolean remoteCache, boolean localCache, boolean mapCache) {

        setCacheConfig(CACHE_DEFAULT_NAME, cache, remoteCache, localCache, mapCache);

    }

    public CacheConfig getCacheConfig(String modelJavaName) {
        CacheConfig cacheConfig = this.cacheConfig.get(modelJavaName);
        if (cacheConfig == null) {

            return getDefaultCache();
        }
        return cacheConfig;
    }

    public CacheConfig getDefaultCache() {

        CacheConfig cacheConfig = this.cacheConfig.get(CACHE_DEFAULT_NAME);
        if (cacheConfig == null) {
            setDefaultCache(true, false, true, true);
        }
        return this.cacheConfig.get(CACHE_DEFAULT_NAME);
    }

    public void setCacheConfig(String modelJavaName, boolean cache, boolean remoteCache, boolean localCache, boolean mapCache) {

        CacheConfig cacheConfig = new CacheConfig();
        cacheConfig.name = modelJavaName;
        cacheConfig.cache = cache;
        cacheConfig.remoteCache = remoteCache;
        cacheConfig.localCache = localCache;
        cacheConfig.mapCache = mapCache;
        this.cacheConfig.put(modelJavaName, cacheConfig);
    }


    public String getTableConfig(String modelJavaName) {

        String type = tableConfig.get(modelJavaName);
        if (type != null) {
            return type;
        }
        return getDefaultTable();
    }

    public String getDefaultTable() {


        return defaultTableType;
    }

    public void setDefaultTable(String type) {


        defaultTableType = type;
    }

    public void setTableTypeConfig(String modelJavaName, String type) {
        tableConfig.put(modelJavaName, type);
    }

    public boolean isGenerateServiceSingleCheck() {
        return generateServiceSingleCheck;
    }

    public void setGenerateServiceSingleCheck(boolean generateServiceSingleCheck) {
        this.generateServiceSingleCheck = generateServiceSingleCheck;
    }

    public boolean isGenerateService() {
        return generateService;
    }

    public void setGenerateService(boolean generateService) {
        this.generateService = generateService;
    }

    public boolean isSingleCheck() {
        return singleCheck;
    }

    public void setSingleCheck(boolean singleCheck) {
        this.singleCheck = singleCheck;
    }

    public void setAllCover(boolean cover) {
        allCover = cover;
    }


    public boolean isAllCover() {
        return allCover;
    }

    public void addCoverConfig(String modelJavaName, boolean cover) {
        addModelCoverConfig(modelJavaName, cover);
        addMapperXmlCoverConfig(modelJavaName, cover);
        addMapperJavaCoverConfig(modelJavaName, cover);
        addCriteriaCoverConfig(modelJavaName, cover);
        addServiceCoverConfig(modelJavaName, cover);
        addControllerCoverConfig(modelJavaName, cover);
    }


    public void addGenerateServerConfig(String modelJavaName, boolean generate) {
        config.put(modelJavaName + SERVICE, generate);
    }


    public boolean getGenerateService(String modelJavaName) {
        if (generateService) {
            if (generateServiceSingleCheck) {
                return config.getOrDefault(modelJavaName + SERVICE, false);
            }
            return generateService;
        }
        return config.getOrDefault(modelJavaName + SERVICE, true);
    }

    public boolean getModelCover(String modelJavaName) {
        if (allCover) {
            if (singleCheck) {
                return config.getOrDefault(modelJavaName + MODEL_COVER, true);
            }
            return allCover;
        }
        return config.getOrDefault(modelJavaName + MODEL_COVER, false);
    }

    public void addModelCoverConfig(String modelJavaName, boolean cover) {

        config.put(modelJavaName + MODEL_COVER, cover);
    }

    public boolean getMapperJavaCover(String modelJavaName) {
        if (allCover) {
            if (singleCheck) {
                return config.getOrDefault(modelJavaName + MAPPER_JAVA_COVER, true);
            }
            return allCover;
        }
        return config.getOrDefault(modelJavaName + MAPPER_JAVA_COVER, false);
    }

    public void addMapperJavaCoverConfig(String modelJavaName, boolean cover) {

        config.put(modelJavaName + MAPPER_JAVA_COVER, cover);
    }

    public boolean getMapperXmlCover(String modelJavaName) {
        if (allCover) {
            if (singleCheck) {
                return config.getOrDefault(modelJavaName + MAPPER_XML_COVER, true);
            }
            return allCover;
        }
        return config.getOrDefault(modelJavaName + MAPPER_XML_COVER, false);
    }

    public void addMapperXmlCoverConfig(String modelJavaName, boolean cover) {

        config.put(modelJavaName + MAPPER_XML_COVER, cover);
    }

    public boolean getCriteriaCover(String modelJavaName) {
        if (allCover) {
            if (singleCheck) {
                return config.getOrDefault(modelJavaName + CRITERIA_COVER, true);
            }
            return allCover;
        }
        return config.getOrDefault(modelJavaName + CRITERIA_COVER, false);
    }

    public void addCriteriaCoverConfig(String modelJavaName, boolean cover) {

        config.put(modelJavaName + CRITERIA_COVER, cover);
    }


    public boolean getControllerCover(String modelJavaName) {
        if (allCover) {
            if (singleCheck) {
                return config.getOrDefault(modelJavaName + CONTROLLER_COVER, true);
            }
            return allCover;
        }
        return config.getOrDefault(modelJavaName + CONTROLLER_COVER, false);
    }

    public void addControllerCoverConfig(String modelJavaName, boolean cover) {

        config.put(modelJavaName + CONTROLLER_COVER, cover);
    }

    public boolean getServiceCover(String modelJavaName) {
        if (allCover) {
            if (singleCheck) {
                return config.getOrDefault(modelJavaName + SERVICE_COVER, true);
            }
            return allCover;
        }
        return config.getOrDefault(modelJavaName + SERVICE_COVER, false);
    }

    public void addServiceCoverConfig(String modelJavaName, boolean cover) {

        config.put(modelJavaName + SERVICE_COVER, cover);
    }

    public boolean getViewCover(String modelJavaName) {
        if (allCover) {
            if (singleCheck) {
                return config.getOrDefault(modelJavaName + VIEW_COVER, true);
            }
            return allCover;
        }
        return config.getOrDefault(modelJavaName + SERVICE_COVER, false);
    }

    public void addViewCoverConfig(String modelJavaName, boolean cover) {

        config.put(modelJavaName + SERVICE_COVER, cover);
    }

    public boolean isGenerateController() {
        return generateController;
    }

    public void setGenerateController(boolean generateController) {
        this.generateController = generateController;
    }

    public boolean isGenerateView() {
        return generateView;
    }

    public void setGenerateView(boolean generateView) {
        this.generateView = generateView;
    }

    public String getJavaCodePart() {
        return javaCodePart;
    }

    public void setJavaCodePart(String javaCodePart) {
        this.javaCodePart = javaCodePart;
    }

    public String getViewPart() {
        return viewPart;
    }

    public void setViewPart(String viewPart) {
        this.viewPart = viewPart;
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

    public List<String> getSpecificSpringLocal() {
        return specificSpringLocal;
    }

    public int getMenuStartId() {
        return menuStartId;
    }

    public void setMenuStartId(int menuStartId) {
        this.menuStartId = menuStartId;
    }

    public boolean isUserCriteriaStr() {
        return userCriteriaStr;
    }

    public void setUserCriteriaStr(boolean userCriteriaStr) {
        this.userCriteriaStr = userCriteriaStr;
    }

    public int getClassLevel() {
        return classLevel;
    }

    public void setClassLevel(int classLevel) {
        this.classLevel = classLevel;
    }

    public NamingStrategy getNamingStrategy() {
        return namingStrategy;
    }

    public void setNamingStrategy(NamingStrategy namingStrategy) {
        this.namingStrategy = namingStrategy;
    }

    protected static class CacheConfig {
        protected String name;
        protected boolean cache;
        protected boolean remoteCache;
        protected boolean localCache;
        protected boolean mapCache;
    }
}
