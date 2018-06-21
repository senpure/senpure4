package com.senpure.base.result;

import com.senpure.base.AppEvn;
import com.senpure.base.util.Assert;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.stereotype.Component;

import java.io.*;
import java.lang.reflect.Field;
import java.text.MessageFormat;
import java.util.*;

/**
 * Created by 罗中正 on 2017/8/2.
 */
@Component
@ConfigurationProperties(
        prefix = "senpure"
)
public class ResultHelper implements ApplicationListener<ContextRefreshedEvent>, ApplicationContextAware {
    private static Class<Result>[] results = new Class[]{Result.class};
    public static List<FieldAndInstance> fieldAndInstances = new ArrayList<>();
    private static boolean develop = false;
    private static boolean force = false;
    private static Logger logger = LoggerFactory.getLogger(ResultHelper.class);
    private static Map<Integer, String> codeMap = new HashMap();
    private static Map<Integer, String> codeName = new HashMap();
    private static Map<String, String> keyMap = new HashMap();
    private static String BASE_NAME = "i18n/result/result";
    private String resultBaseName;

    public static String getKey(int code) {

        String key = codeMap.get(code);
        return key == null ? codeMap.get(Result.FAILURE) : key;
    }


    public static String getMessage(int code, Locale locale) {
        try {
            return ResourceBundle.getBundle(BASE_NAME, locale).getString(getKey(code));
        } catch (MissingResourceException e) {

            return "RESULT_CODE[" + code + "]";
        }

    }

    public static String getMessage(String key, Locale locale) {
        try {
            return ResourceBundle.getBundle(BASE_NAME, locale).getString(key);
        } catch (MissingResourceException e) {

            return "Message[" + key + "]";
        }

    }

    public static String getMessage(int code, Locale locale, Object... args) {


        return MessageFormat.format(getMessage(code, locale), args);

    }

    public static ResultMap wrapMessage(ResultMap resultMap, Locale locale) {

        if (resultMap.getArgs() != null && !resultMap.isClientFormat()) {
            return wrapMessage(resultMap, locale, resultMap.getArgs().toArray());
        }
        return
                resultMap.put(ResultMap.MESSAGE_KEY, ResultHelper.getMessage(resultMap.getCode(), locale));
    }

    public static ResultMap wrapMessage(ResultMap resultMap, Locale locale, Object... args) {

        return
                resultMap.put(ResultMap.MESSAGE_KEY, ResultHelper.getMessage(resultMap.getCode(), locale, args));
    }

    public static void refreshProperties() {
        ResourceBundle.clearCache();
    }

    @Override
    public void onApplicationEvent(ContextRefreshedEvent event) {
        try {
            syncResults();
        } catch (Exception e) {
            logger.error("result解析出错，关闭服务器", e);
            act.close();
        }

    }


    private static void syncResults() {

        String rootPath = AppEvn.getClassRootPath();
        logger.debug("rootPath {}", rootPath);
        logger.debug("result baseName {}", BASE_NAME);
        File i18n = new File(rootPath, BASE_NAME + ".properties");
        if (develop) {
            i18n = new File(new File(rootPath).getParentFile().getParentFile(), "src/main/resources/" + BASE_NAME + ".properties");
        }
        logger.debug("资源文件完整路径：" + i18n.getAbsolutePath());
        boolean exist = true;
        boolean create = false;
        boolean update = false;
        if (develop&&!i18n.exists()) {
            exist = false;
            create = true;
            i18n.getParentFile().mkdirs();
        }
        Properties props = new Properties();
        SortProperties save = new SortProperties();
        if (exist) {
            InputStream in = null;
            try {
                in = new FileInputStream(i18n);
                props.load(in);
                in.close();

            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }

        }

        List<CodeAndInstance> codeAndInstanceList = new ArrayList<>();
        for (FieldAndInstance fieldAndInstance : fieldAndInstances) {
            for (Field field : fieldAndInstance.fields) {
                int code = 0;
                try {
                    code = field.getInt(fieldAndInstance.instance);
                } catch (IllegalArgumentException e) {
                    e.printStackTrace();
                    continue;
                } catch (IllegalAccessException e) {

                    e.printStackTrace();
                    continue;
                }
                CodeAndInstance codeAndInstance = new CodeAndInstance();
                codeAndInstance.code = code;
                codeAndInstance.field = field;
                codeAndInstance.instance = fieldAndInstance.instance;
                codeAndInstanceList.add(codeAndInstance);
            }
        }
        Collections.sort(codeAndInstanceList, Comparator.comparingInt(o -> o.code));

        refreshProperties();
        keyMap.clear();
        codeMap.clear();
        StringBuilder updateBuilder = new StringBuilder();
        for (CodeAndInstance codeAndInstance : codeAndInstanceList) {

            Field field = codeAndInstance.field;
            Object instance = codeAndInstance.instance;
            int code = codeAndInstance.code;
            String name = field.getName().replace("_", ".").toLowerCase();
            String tempName = instance.getClass().getName() + "_" + field.getName();
            Assert.isNull(keyMap.get(name), "key不能重复 [" + name + "]\n" + keyMap.get(name) + "\n" + tempName);
            Assert.isNull(codeMap.get(code), "错误码不能重复 [" + code + "]\n" + codeName.get(code) + "\n" + tempName);
            codeName.put(code, tempName);
            keyMap.put(name, tempName);
            codeMap.put(code, name);
            Message m = field.getAnnotation(Message.class);
            String thisValue = null;
            if (m != null && m.message().trim().length() != 0) {
                thisValue = m.message();
            } else {
                thisValue = "RESULT-CODE[" + code + "]";

            }
            logger.trace(code + " >> " + name + " >> " + "RESULT-CODE[" + code + "]");
            save.put(name, thisValue);
            String value = props.getProperty(name);
            if (value == null) {
                update = true;
                updateBuilder.append(name).append("\n");

            } else if (force) {
                if (!value.equals(thisValue)) {
                    update = true;
                    updateBuilder.append(name).append("\n");
                }

            }
        }
        if (develop&&update) {
            OutputStream out = null;
            try {
                out = new FileOutputStream(i18n);
                if (create) {
                    logger.debug("创建 result 资源文件");
                    save.store(out, "create properties");
                } else {
                    logger.debug("更新 result 资源文件");
                    save.store(out, "update name:\n" +
                            "############################################################################\n"
                            + updateBuilder.toString()
                            + "############################################################################");
                }
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }

        }
        StringBuilder info = new StringBuilder();
        int maxLen = 0;
        for (Map.Entry<Integer, String> entry : codeMap.entrySet()) {

            int len = (entry.getKey() + "").length();
            maxLen = maxLen > len ? maxLen : len;
        }
        for (CodeAndInstance codeAndInstance : codeAndInstanceList) {
            int len = (codeAndInstance.code + "").length();
            info.append(codeAndInstance.code);
            for (int i = len; i < maxLen; i++) {
                info.append(" ");
            }
            info.append(":").append(getMessage(codeAndInstance.code, Locale.CHINA)).append("\n");
        }

        logger.debug("结果集对照表\n{}", info.toString());
    }

    private AbstractApplicationContext act;

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        act = (AbstractApplicationContext) applicationContext;

    }

    public static void devSyncResult(Class<Result>[] results) throws IllegalAccessException, InstantiationException {
        ResultHelper obj = new ResultHelper();
        develop = true;
        force = true;
        for (Class<Result> result : results) {
            result.newInstance().report();
        }

        syncResults();
    }

    public String getResultBaseName() {
        return resultBaseName;
    }

    public void setResultBaseName(String resultBaseName) {
        this.resultBaseName = resultBaseName;
        BASE_NAME = resultBaseName;
    }

    public static void main(String[] args) throws IllegalAccessException, InstantiationException {
        AppEvn.markClassRootPath();
        devSyncResult(results);
        // ResultMap result = ResultMap.result(Result.ACCOUNT_OTHER_LOGIN);

        // ResultHelper.wrapMessage(result, Locale.CANADA,"77");

        // System.out.println(result);
    }


}
