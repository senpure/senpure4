package com.senpure.base.generator;

import com.senpure.base.AppEvn;
import com.senpure.base.annotation.Explain;
import com.senpure.base.annotation.LocalCache;
import com.senpure.base.annotation.LongDate;
import com.senpure.base.annotation.SpringCache;
import com.senpure.base.util.Assert;
import com.senpure.base.util.StringUtil;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.cfg.ImprovedNamingStrategy;
import org.hibernate.cfg.NamingStrategy;
import org.hibernate.dialect.MySQL57Dialect;
import org.hibernate.dialect.MySQL5Dialect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.persistence.*;
import java.io.File;
import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.sql.Types;
import java.util.*;

/**
 * Created by 罗中正 on 2017/9/21.
 */
public class EntityReader {

    private Config config;
    private NamingStrategy nameStrategy;
    public EntityReader(Config config) {
        this.config = config;
        nameStrategy = config.getNamingStrategy();
        if (nameStrategy == null) {
            nameStrategy = new ImprovedNamingStrategy();
        }
    }

    private Logger logger = LoggerFactory.getLogger(getClass());
    Map<String, Model> modelMap = new HashMap<>();
    MySQL5Dialect dialect = new MySQL57Dialect();


    DefaultEntity defaultEntity = new DefaultEntity();
    private String packageName;
    private String[] unullable = {"int", "char", "short", "byte", "float", "double", "boolean", "long"};

    Column defaultColumn;
    {
        try {
            defaultColumn = defaultEntity.getClass().getDeclaredField("column").getAnnotation(Column.class);
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        }
    }

    public Map<String, Model> read(String packageName) {

        this.packageName = packageName;
        File file = new File(AppEvn.getClassRootPath(), packageName.replace(".", File.separator));
        return read(file);
    }

    public Map<String, Model> read(File file) {
        String classRootPath = AppEvn.getClassRootPath();
        File[] files = null;
        if (file.isDirectory()) {
            files = file.listFiles();
        } else {
            files = new File[1];
            files[0] = file;
        }
        for (File entity : files) {
            if (entity.getName().endsWith(".class")) {
                String classPath = entity.getAbsolutePath().replace(classRootPath, "");
                classPath = classPath.replace(".class", "");
                classPath = classPath.replace(File.separatorChar, '.');
                if (classPath.startsWith(".")) {
                    classPath = classPath.replaceFirst("\\.", "");
                }
                // classPath =classPath  .replace('/','.');
                logger.debug("classPath {}", classPath);
                Class c = null;
                Object obj = null;
                try {
                    c = Class.forName(classPath);
                    obj = c.newInstance();
                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                } catch (InstantiationException e) {
                    e.printStackTrace();
                }
                if (c != null) {
                    if (c.getAnnotation(Entity.class) != null) {
                        read(obj);
                    } else {
                        logger.debug("跳过 {}", c.getName());
                    }
                }
            }
        }
        readAfter();
        return modelMap;
    }

    private void read(Object obj) {
        logger.debug("reade {}", obj.getClass());
        Model model = new Model();
        model.setEntityPackage(obj.getClass().getPackage().getName());
        logger.debug(model.getEntityPackage());
        modelMap.put(obj.getClass().getName(), model);

        Table table = obj.getClass().getAnnotation(Table.class);
        if (table == null || table.name().length() == 0) {
            model.setTableName(nameStrategy.classToTableName(obj.getClass().getName()));
        } else {
            model.setTableName(nameStrategy.classToTableName(table.name()));
        }
        Explain explain = obj.getClass().getAnnotation(Explain.class);
        if (explain != null && explain.value().length() > 0) {
            model.setExplain(explain.value());

        }
        LocalCache localCache = obj.getClass().getAnnotation(LocalCache.class);
        if (localCache != null) {
            model.setLocalCache(localCache.value());
        }
        SpringCache springCache = obj.getClass().getAnnotation(SpringCache.class);
        if (springCache != null) {
            model.setSpringCache(springCache.value());
            model.setSpringLocal(springCache.local());
        }

        String name = obj.getClass().getSimpleName();
        name = name.replace("Table", "");
        name = name.replace("Entity", "");
        model.setName(name);

        com.senpure.base.annotation.Cache cache = obj.getClass().getAnnotation(com.senpure.base.annotation.Cache.class);
        if (cache != null) {
            model.setCache(cache.value());
            model.setSpringCache(cache.remote());
            model.setSpringLocal(cache.local());
            model.setLocalCache(cache.map());
        } else {
            Config.CacheConfig cacheConfig = config.getCacheConfig(model.getName());
            model.setCache(cacheConfig.cache);
            model.setSpringCache(cacheConfig.remoteCache);
            model.setSpringLocal(cacheConfig.localCache);
            model.setLocalCache(cacheConfig.mapCache);
        }
        Field[] fields = obj.getClass().getDeclaredFields();
        readSuperClass(model, obj.getClass().getSuperclass());
        model.getClazzs().add(obj.getClass().getName());
        readFields(model, fields);
        logger.debug(model.toString());
    }

    private void readAfter() {
        logger.info("readAfter------------------");
        Iterator<Map.Entry<String, Model>> iterator = modelMap.entrySet().iterator();
        while (iterator.hasNext()) {
            Map.Entry<String, Model> entry = iterator.next();
            Model model = entry.getValue();
            List<String> targets = model.getAfterReadColum().getTargetClass();
            if (targets.size() > 0) {
                for (String name : targets) {
                    Model target = modelMap.get(name);
                    if (target == null) {
                        Assert.error(name + "不存在 ");
                    }
                    // ModelField modelField = new ModelField();
                }
            }
            List<ModelField> modelFields = model.getAfterReadColum().getModelFields();

            Map<String, ModelField> tempModelFieldMap = new LinkedHashMap<>(model.getModelFieldMap());
            for (ModelField modelField : modelFields) {

                Model target = modelMap.get(modelField.getName());
                if (target == null) {
                    Assert.error(modelField.getName() + "不存在 ");
                }
                logger.debug(target.toString());
                modelField.setClazzType(target.getId().getClazzType());
                modelField.setJdbcType(target.getId().getJdbcType());
                target.setChild(model);
                target.setChildField(modelField);
                find(model, modelField, false);
                if (modelField.getColumn() == null) {
                    modelField.setName(StringUtil.toLowerFirstLetter(target.getName()
                            + StringUtil.toUpperFirstLetter(target.getId().getName())));
                    modelField.setColumn(nameStrategy.columnName(target.getName() + target.getId().getName()));
                } else {
                    modelField.setName(modelField.getColumn());
                    modelField.setColumn(nameStrategy.columnName(modelField.getColumn()));
                }
                String ex = "外键," + "modelName:" + target.getName() + ",tableName:" + target.getTableName();
                if (modelField.getExplain() == null) {
                    modelField.setExplain(ex);
                } else if (!modelField.getExplain().contains("外键")) {

                    modelField.setExplain(modelField.getExplain() + "(" + ex + ")");
                }
                modelField.setJavaNullable(javaNullable(target.getId().getClazzType()));
                model.getModelFieldMap().put(modelField.getName(), modelField);
                model.getCriteriaFieldMap().put(modelField.getName(), modelField);

            }
            model.getCriteriaFieldMap().putAll(tempModelFieldMap);
            //mybatis 时间范围 >= <= 处理，只处理只有一个时间字段的情况
            logger.debug("start {} model.getDateFieldNum() {}", model.getName(), model.getDateFieldNum());
            if (model.getDateFieldNum() == 1) {
                ModelField field = model.getDateField();
                field.setCriteriaEquals(false);
                String longName = field.getName();
                logger.debug("time field {}", field);
                if (longName.endsWith("Date")) {
                    longName = longName.substring(0, longName.length() - 4) + "Time";
                }
                logger.debug("time long name {}", longName);
                ModelField temp = model.getModelFieldMap().get(longName);

                logger.debug("time long {}", temp);
                if (temp != null) {
                    temp.setCriteriaEquals(false);
                    model.setDateField(temp);
                }
            }
            Collection<ModelField> modelFieldCollection = model.getModelFieldMap().values();
            for (ModelField modelField : modelFieldCollection) {
                if (model.getName().equalsIgnoreCase("account")) {

                    logger.debug(modelField.toString());
                }
                String name = modelField.getName();
                if (name.endsWith("Time") && modelField.getClazzType().equalsIgnoreCase("long")) {
                    String dateName = name.substring(0, name.length() - 4) + "Date";
                    ModelField temp = model.getModelFieldMap().get(dateName);
                    if (temp != null) {
                        modelField.setDate(true);
                        modelField.setStrShow(false);
                        temp.setHtmlShow(false);
                        temp.setCriteriaEquals(false);
                        temp.setLongDate(modelField);
                        modelField.setOrder(true);

                    }
                }
            }
            if (model.getDateField() == null) {
                ModelField modelField = new ModelField();
                modelField.setName("123456789");
                model.setDateField(modelField);

            }
            logger.debug("end {} model.getDateFieldNum() {}", model.getName(), model.getDateFieldNum());

            //解读出findBy的字段
            for (ModelField modelField : modelFieldCollection) {
                if (modelField.getName().equals("account")) {
                    find(model, modelField, true);
                } else if (modelField.getName().equals("name")) {
                    find(model, modelField, true);
                } else if (modelField.getName().endsWith("Name") && !modelField.getName().startsWith("readable")) {
                    find(model, modelField, false);
                } else if (modelField.getName().equals("nick")) {
                    find(model, modelField, false);
                } else if (modelField.getName().endsWith("Nick")) {
                    find(model, modelField, false);
                } else if (modelField.getName().endsWith("Id")) {
                    if (model.getName().endsWith("Info")
                            || model.getName().endsWith("info")
                            || model.getName().endsWith("Ext")
                            || model.getName().endsWith("ext")) {
                        find(model, modelField, true);
                    } else {
                        find(model, modelField, false);
                    }
                } else if (modelField.getName().equals("type")) {
                    find(model, modelField, false);
                } else if (modelField.getName().endsWith("Type")) {
                    find(model, modelField, false);
                } else if (modelField.getName().equals("key")) {
                    find(model, modelField, true);
                } else if (modelField.getName().endsWith("Key")) {
                    find(model, modelField, true);
                } else if (modelField.getName().equals("uriAndMethod")) {
                    find(model, modelField, false);
                }
                else if (modelField.getName().equalsIgnoreCase("gold")
                        ||modelField.getName().equalsIgnoreCase("golds")
                        ||modelField.getName().equalsIgnoreCase("diamond")
                        ) {
                    find(model, modelField, false);
                }
            }
            if (model.getFindModeFields().size() == 0) {
                for (ModelField modelField : modelFieldCollection) {
                    if (modelField.getClazzType().equals("String")) {
                        if (modelField.getName().equals("text")) {
                            // model.getFindModeFields().add(modelField);
                            break;
                        }
                    }
                }
            }
        }


    }

    private void find(Model model, ModelField modelField, boolean findOne) {
        if (model.getFindModeFields().contains(modelField)) {
            logger.info("change {} to {},{}  {}", modelField.isFindOne(), findOne, model, modelField);
            modelField.setFindOne(findOne);
        } else {
            logger.info("find  {}  {}", model, modelField);
            modelField.setFindOne(findOne);
            model.getFindModeFields().add(modelField);
        }
    }

    private void readComment() {
        Iterator<Map.Entry<String, Model>> iterator = modelMap.entrySet().iterator();
        while (iterator.hasNext()) {
            Map.Entry<String, Model> entry = iterator.next();
            Model model = entry.getValue();


        }

        File file = new File(AppEvn.getClassRootPath());
        File javaFile = new File(file.getParentFile().getParentFile(), "src/main/java");
        javaFile = new File(javaFile, packageName.replace(".", File.separator));
    }

    private void readFields(Model model, Field[] fields) {


        for (Field field : fields) {
            //排除static
            if ((field.getModifiers() & Modifier.STATIC) == Modifier.STATIC) {
                logger.info("跳过{}", field.getName());
                continue;
            }
            String simpleName = field.getType().getSimpleName();
            if ("List".equalsIgnoreCase(simpleName) ||
                    "Set".equalsIgnoreCase(simpleName)) {
                logger.info("跳过{}", field.getName());
                continue;
            }
            Transient tr = field.getAnnotation(Transient.class);
            if (tr == null) {
                ModelField modelField = new ModelField();
                Explain explain = field.getAnnotation(Explain.class);
                if (explain != null && explain.value().length() > 0) {
                    modelField.setExplain(explain.value());
                }
                ManyToOne manyToOne = field.getAnnotation(ManyToOne.class);
                if (manyToOne != null) {

                    JoinColumn joinColumn = field.getAnnotation(JoinColumn.class);
                    if (joinColumn != null && joinColumn.name().length() > 0) {
                        // modelField.setColumn(nameStrategy.columnName(joinColumn.name()));

                        modelField.setColumn(joinColumn.name());
                        // logger.debug(modelField.getColumn());
                    }
                    modelField.setNullable(joinColumn.nullable());
                    modelField.setAccessType(Modifier.toString(field.getModifiers()));
                    modelField.setClazzType(field.getType().getName());
                    modelField.setName(field.getType().getName());

                    logger.debug("暂时不处理字段{} {}", field.getType().getName(), field.getName());
                    model.getAfterReadColum().getTargetClass().add(field.getType().getName());

                    model.getAfterReadColum().getModelFields().add(modelField);

                    continue;
                }


                modelField.setClazzType(field.getType().getSimpleName());

                modelField.setAccessType(Modifier.toString(field.getModifiers()));
                modelField.setName(field.getName());
                Column column = field.getAnnotation(Column.class);
                if (column == null) {
                    column = defaultColumn;
                }
                modelField.setNullable(column.nullable());
                String jdbcType = dialect.getTypeName(jdbcTypeCode(modelField.getClazzType()),
                        column.length(), column.precision(), column.scale()).toUpperCase();
                int index = jdbcType.indexOf("(");
                if (index > 0) {
                    jdbcType = jdbcType.substring(0, index);
                }
                modelField.setJdbcType(jdbcType);
                if ("Date".equals(modelField.getClazzType())) {
                    model.setHasDate(true);
                    model.dateFieldIncr();
                    model.setDateField(modelField);
                    modelField.setJdbcType("TIMESTAMP");
                    modelField.setDate(true);
                    modelField.setOrder(true);
                }
                if ("DOUBLE PRECISION".equalsIgnoreCase(jdbcType)) {
                    modelField.setJdbcType("DOUBLE");

                }
                modelField.setJavaNullable(javaNullable(modelField.getClazzType()));
                if (column.name().length() == 0) {
                    modelField.setColumn(nameStrategy.columnName(field.getName()));
                } else {
                    modelField.setColumn(nameStrategy.columnName(column.name()));
                }
                if (field.getAnnotation(Id.class) == null) {
                    model.getModelFieldMap().put(modelField.getName(), modelField);
                } else {
                    modelField.setNullable(false);
                    //  modelField.setJavaNullable(false);
                    if (modelField.getExplain() == null) {
                        modelField.setExplain("主键");
                    } else if (!modelField.getExplain().contains("主键")) {
                        modelField.setExplain(modelField.getExplain() + "(主键)");
                    }
                    modelField.setId(true);
                    model.getModelFieldMap().remove(modelField.getName());
                    model.setId(modelField);
                    GenericGenerator generatedValue = field.getAnnotation(GenericGenerator.class);
                    if (generatedValue == null) {
                        modelField.setDatabaseId(true);
                    } else {
                        modelField.setDatabaseId(false);
                    }

                }
                if (field.getAnnotation(Version.class) != null) {
                    modelField.setNullable(false);
                    //  modelField.setJavaNullable(false);
                    modelField.setVersion(true);
                    modelField.setExplain("乐观锁，版本控制");
                    model.getModelFieldMap().remove(modelField.getName());
                    model.setVersion(modelField);

                }
                if (field.getAnnotation(LongDate.class) != null) {
                    if (modelField.getClazzType().equalsIgnoreCase("long")) {
                        model.dateFieldIncr();
                        model.setDateField(modelField);
                    }

                }

                logger.debug(modelField.toString());

                // System.out.println(field.getType());
            }
        }
    }

    private void readSuperClass(Model model, Class superClazz) {
        if (superClazz == null) {
            return;
        }
        readSuperClass(model, superClazz.getSuperclass());
        if (superClazz.getAnnotation(MappedSuperclass.class) != null) {
            model.getClazzs().add(superClazz.getName());
            Field[] fields = superClazz.getDeclaredFields();
            readFields(model, fields);
        }


    }

    private int jdbcTypeCode(String type) {

        switch (type) {
            case "char":
                return Types.CHAR;
            case "byte":
            case "Byte":
                return Types.TINYINT;
            case "String":
                return Types.VARCHAR;

            case "Date":
                return Types.DATE;
            case "boolean":
            case "Boolean":
                return Types.BOOLEAN;
            case "int":
            case "Integer":
                return Types.INTEGER;
            case "short":
            case "Short":
                return Types.SMALLINT;
            case "float":
            case "Float":
                return Types.FLOAT;
            case "double":
            case "Double":
                return Types.DOUBLE;
            case "long":
            case "Long":
                return Types.BIGINT;
        }

        Assert.error("不支持的Java类型" + type);
        return Types.VARCHAR;
    }

    private boolean javaNullable(String type) {
        for (String str : unullable) {
            if (str.equals(type)) {

                return false;
            }
        }
        return true;
    }

    public static void main(String[] args) {

        EntityReader reader = new EntityReader(new Config());

        String packageName = "com.senpure.base.entity";

        File file = new File(AppEvn.getClassRootPath(), packageName.replace(".", File.separator));
        reader.read(file);
    }
}
