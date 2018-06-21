package com.senpure.base.generator;

import com.senpure.base.AppEvn;
import com.senpure.base.util.Assert;
import com.senpure.base.util.StringUtil;
import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import org.fusesource.jansi.AnsiConsole;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.ansi.AnsiColor;
import org.springframework.boot.ansi.AnsiOutput;

import java.io.*;
import java.util.*;

/**
 * Created by 罗中正 on 2017/9/26.
 */
public class DatabaseGenerator {

    private Logger logger = LoggerFactory.getLogger(DatabaseGenerator.class);
    private List<String> exists = new ArrayList<>();

    private List<String> springLocal = new ArrayList<>();


    public DatabaseGenerator() {
        AppEvn.markClassRootPath(AppEvn.getCallerClass());
    }

    public static void generate(Object object, Template template, File file) {
        try {
            FileOutputStream fos = new FileOutputStream(file);
            template.process(object, new OutputStreamWriter(fos, "utf-8"));
            fos.close();

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (TemplateException e) {
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void generate(String part) {
        generate(part, new Config());
    }

    public void generate(String part, Config config) {
        prepLog();
        Configuration cfg = new Configuration(Configuration.DEFAULT_INCOMPATIBLE_IMPROVEMENTS);

        cfg.setSharedVariable("pluralize", new Pluralize());
        cfg.setSharedVariable("nameRule", new NameRule());
        cfg.setSharedVariable("space", new SpaceResidual());
        cfg.setSharedVariable("serial", new HashCode());
        cfg.setSharedVariable("labelFormat", new LabelFormat());
        int point = StringUtil.indexOf(part, ".", 1, true);
        String module = part;
        if (point > 0) {
            module = part.substring(point + 1);
        }
        cfg.setClassForTemplateLoading(getClass(), "/");
        Template modelTemplate = null;
        Template mapperJavaTemplate = null;
        Template mapperXmlTemplate = null;
        Template criteriaTemplate = null;
        Template criteriaStrTemplate = null;
        Template serviceTemplate = null;
        Template serviceLocalCacheTemplate = null;
        Template serviceSpringCacheTemplate = null;
        Template controllerTemplate = null;
        Template viewTemplate = null;
        try {
            modelTemplate = cfg.getTemplate(
                    "com/senpure/base/generator/template/model.ftl",
                    "utf-8");
            mapperJavaTemplate = cfg.getTemplate(
                    "com/senpure/base/generator/template/mapperJava.ftl",
                    "utf-8");
            mapperXmlTemplate = cfg.getTemplate(
                    "com/senpure/base/generator/template/mapperXml.ftl",
                    "utf-8");
            criteriaTemplate = cfg.getTemplate(
                    "com/senpure/base/generator/template/criteria.ftl",
                    "utf-8");
            criteriaStrTemplate = cfg.getTemplate(
                    "com/senpure/base/generator/template/criteriaStr.ftl",
                    "utf-8");
            if (config.isGenerateService()) {
                serviceTemplate = cfg.getTemplate(
                        "com/senpure/base/generator/template/service.ftl",
                        "utf-8");
                serviceLocalCacheTemplate = cfg.getTemplate(
                        "com/senpure/base/generator/template/serviceLocalCache.ftl",
                        "utf-8");
                serviceSpringCacheTemplate = cfg.getTemplate(
                        "com/senpure/base/generator/template/serviceSpringCache.ftl",
                        "utf-8");
            }
            if (config.isGenerateController()) {
                controllerTemplate = cfg.getTemplate(
                        "com/senpure/base/generator/template/controller.ftl",
                        "utf-8");
            }
            if (config.isGenerateView()) {
                viewTemplate = cfg.getTemplate(
                        "com/senpure/base/generator/template/view.ftl",
                        "utf-8");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        logger.info("classRootPath:{}", AppEvn.getClassRootPath());
        File file = new File(AppEvn.getClassRootPath());
        File parent=file;
        for (int i = 0; i <config.getClassLevel() ; i++) {
            parent=parent.getParentFile();
        }
        File javaPart = new File(parent,
                config.getJavaCodePart() + part.replace(".", "/"));
        File viewPart = new File(parent,
                config.getViewPart() + module);
        EntityReader reader = new EntityReader(config);
        Map<String, Model> modelMap = reader.read(part + ".entity");
        Collection<Model> models = modelMap.values();
        int menuId = config.getMenuStartId();
        if (menuId == 0) {
            menuId = module.hashCode() / 100 * 100;
        }
        for (Model model : models) {
            // System.out.println(model.getName());
            model.setModelPackage(part + ".model");
            model.setMapperPackage(part + ".mapper");
            model.setCriteriaPackage(part + ".criteria");
            model.setServicePackage(part + ".service");
            model.setControllerPackage(part + ".controller");
            model.setModule(module);
            model.setMenuId(menuId);
            model.setGenerateMenu(config.isGenerateMenu());
            model.setGeneratePermission(config.isGeneratePermission());
            model.setUseCriteriaStr(config.isUserCriteriaStr());
            model.setTableType(config.getTableConfig(model.getName()));
            if (!model.getTableType().equalsIgnoreCase(Config.TABLE_TYPE_SINGLE)) {
                ModelField modelField = new ModelField();
                modelField.setAccessType("private");
                modelField.setClazzType("String");
                modelField.setName("tableName");
                model.setTable(modelField);

            }
            menuId += 100;
            if (model.getId() == null) {
                Assert.error(model.getName() + "没有主键");
            }
            if (modelTemplate != null) {
                File modelFile = new File(javaPart, "model/" + model.getName() + ".java");
                generateFile(modelTemplate, model, modelFile, config.getModelCover(model.getName()));
            }
            if (mapperJavaTemplate != null) {
                File javaFile = new File(javaPart, "mapper/" + model.getName() + "Mapper.java");
                generateFile(mapperJavaTemplate, model, javaFile, config.getMapperJavaCover(model.getName()));
            }
            if (mapperXmlTemplate != null) {
                File xmlFile = new File(javaPart, "mapper/" + model.getName() + "Mapper.xml");
                generateFile(mapperXmlTemplate, model, xmlFile, config.getMapperXmlCover(model.getName()));
            }

            if (criteriaTemplate != null) {
                File criteriaFile = new File(javaPart, "criteria/" + model.getName() + "Criteria.java");
                generateFile(criteriaTemplate, model, criteriaFile, config.getCriteriaCover(model.getName()));
            }
            if (criteriaStrTemplate != null&&model.isUseCriteriaStr()) {
                File criteriaFile = new File(javaPart, "criteria/" + model.getName() + "CriteriaStr.java");
                generateFile(criteriaStrTemplate, model, criteriaFile, config.getCriteriaCover(model.getName()));
            }
            if (serviceTemplate != null) {

                if (config.getGenerateService(model.getName())) {
                    if (model.isCache() && !model.isSpringCache() && model.isSpringLocal()) {
                        springLocal.add(model.getName());
                    }
                    File serviceFile = new File(javaPart, "service/" + model.getName() + "Service.java");
                    if (model.isCache()) {
                        Template template = model.isSpringCache() ? serviceSpringCacheTemplate :
                                model.isSpringLocal() ? serviceSpringCacheTemplate :
                                        model.isLocalCache() ? serviceLocalCacheTemplate : serviceTemplate;
                        generateFile(template, model, serviceFile, config.getServiceCover(model.getName()));
                    } else {
                        generateFile(serviceTemplate, model, serviceFile, config.getServiceCover(model.getName()));
                    }

                }
            }
            if (controllerTemplate != null) {
                File controllerFile = new File(javaPart, "controller/" + model.getName() + "Controller.java");
                generateFile(controllerTemplate, model, controllerFile, config.getControllerCover(model.getName()));

            }
            if (viewTemplate != null) {
                File viewFile = new File(viewPart, NameRule.nameRule(model.getName()) + ".ftl");
                generateFile(viewTemplate, model, viewFile, config.getViewCover(model.getName()));
            }
        }

        springLocal.addAll(config.getSpecificSpringLocal());
        generateSpringCacheConfiguration(part, javaPart, cfg);
        if (exists.size() > 0) {
            logger.warn(AnsiOutput.toString(new Object[]{AnsiColor.BRIGHT_RED, "↓↓↓↓↓↓↓↓↓↓以下文件存在没有生成↓↓↓↓↓↓↓↓↓↓"}));
            for (String name : exists) {
                logger.warn(AnsiOutput.toString(new Object[]{AnsiColor.BRIGHT_CYAN, name}));
            }
            logger.warn(AnsiOutput.toString(new Object[]{AnsiColor.BRIGHT_RED, "↑↑↑↑↑↑↑↑↑↑以上文件存在没有生成↑↑↑↑↑↑↑↑↑↑"}));
        }
    }

    private void generateFile(Template template, Object model, File file, boolean cover) {
        if (!file.getParentFile().exists()) {
            file.getParentFile().mkdirs();
        }
        if (file.exists()) {
            if (cover) {
                if (file.getName().endsWith("Service.java")) {
                    logger.debug("{}{} useCache:{}", AnsiOutput.toString(new Object[]{AnsiColor.BRIGHT_RED, "覆盖生成"}), file.getAbsolutePath(), AnsiOutput.toString(new Object[]{AnsiColor.BRIGHT_RED, getCache((Model) model)}));

                } else {
                    logger.debug("{}{}", AnsiOutput.toString(new Object[]{AnsiColor.BRIGHT_RED, "覆盖生成"}), file.getAbsolutePath());

                }
                generate(model, template, file);
            } else {
                exists.add(file.getAbsolutePath());
                if (file.getName().endsWith("Service.java") && model instanceof Model) {

                    springLocal.remove(((Model) model).getName());
                }
                logger.warn("{}存在无法生成", file.getAbsolutePath());
            }

        } else {
            if (file.getName().endsWith("Service.java")) {
                logger.debug("生成{} useCache:{}", file.getAbsolutePath(), AnsiOutput.toString(new Object[]{AnsiColor.BRIGHT_RED, getCache((Model) model)}));
            } else {
                logger.debug("生成{}", file.getAbsolutePath());
            }
            generate(model, template, file);

        }
    }

    private String getCache(Model model) {
        if (model.isCache()) {
            if (model.isSpringCache()) {
                return "true:springCache";
            } else if (model.isSpringLocal()) {
                return "true:springLocal";
            } else if (model.isLocalCache()) {
                return "true:localMap";
            }
        }
        return "false";
    }

    private void generateSpringCacheConfiguration(String part, File javaPart, Configuration cfg) {
        if (springLocal.size() > 0) {
            logger.debug("springLocal = {}", springLocal);
        }

        String configName = "LocalCacheConfiguration";
        if ("com.senpure.base".equals(part)) {
            configName = "LocalCacheConfiguration";
        } else {
            int index = StringUtil.indexOf(part, ".", 1, true);
            String tempPart;
            if (index > 0) {
                tempPart = part.substring(index + 1);
            } else {
                tempPart = part;
            }
            configName = StringUtil.toUpperFirstLetter(tempPart) + "LocalCacheConfiguration";
        }
        File configFile = new File(javaPart, "configuration/" + configName + ".java");
        if (springLocal.size() > 0) {
            Class clazz = null;
            try {
                clazz = Class.forName("org.springframework.data.redis.core.RedisTemplate");
            } catch (ClassNotFoundException e) {

                logger.debug("不用生成redis的本地缓存配置");
            }
            if (clazz != null) {


                Map<String, Object> args = new HashMap<>(16);

                args.put("configName", configName);
                args.put("names", springLocal);
                args.put("package", part + ".configuration");
                Template template = null;
                try {
                    template = cfg.getTemplate(
                            "com/senpure/base/generator/template/LocalRemoteConfiguration.ftl",
                            "utf-8");
                } catch (IOException e) {
                    e.printStackTrace();
                }
                if (template != null) {
                    generateFile(template, args, configFile, true);
                }
            }
        } else {

            if (configFile.exists()) {
                logger.info("{} {}", AnsiOutput.toString(new Object[]{AnsiColor.BRIGHT_RED, "删除"}), configFile.getAbsolutePath());
                configFile.delete();
            }

        }
    }

    private static void prepLog() {
        AnsiOutput.setEnabled(AnsiOutput.Enabled.ALWAYS);
        System.setProperty("PID", AppEvn.getPid());
        AnsiConsole.systemInstall();
        AnsiConsole.systemUninstall();
    }

    public static void main(String[] args) {

        prepLog();
        DatabaseGenerator mybatisGenerator = new DatabaseGenerator();

        Config config = new Config();
        config.addCoverConfig("Player", true);
        config.setAllCover(true);
        mybatisGenerator.generate("com.senpure.base.test", config);
    }
}
