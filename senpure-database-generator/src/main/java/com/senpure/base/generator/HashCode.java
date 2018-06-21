package com.senpure.base.generator;

import freemarker.template.SimpleHash;
import freemarker.template.TemplateMethodModelEx;
import freemarker.template.TemplateModelException;

import java.util.List;
import java.util.Map;
import java.util.TreeMap;

/**
 * Created by 罗中正 on 2017/12/28 0028.
 */
public class HashCode implements TemplateMethodModelEx {
    @Override
    public Object exec(List list) throws TemplateModelException {
        Map<String, ModelField> shortMap = new TreeMap();
        try {
            freemarker.template.SimpleHash simpleHash = (SimpleHash) list.get(0);
            shortMap.putAll(simpleHash.toMap());
        } catch (Exception e) {
            e.printStackTrace();
        }
        StringBuilder sb = new StringBuilder();
        shortMap.values().forEach(modelField -> sb.append(modelField.getName()));
        int code=sb.toString().hashCode();
        if (code < 0) {
            code=code*-1;
        }
        return code+"";
    }
}
