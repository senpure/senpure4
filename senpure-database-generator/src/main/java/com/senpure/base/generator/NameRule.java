package com.senpure.base.generator;

import com.senpure.base.util.StringUtil;
import freemarker.template.SimpleScalar;
import freemarker.template.TemplateMethodModelEx;
import freemarker.template.TemplateModelException;

import java.util.List;

/**
 * Created by 罗中正 on 2017/10/13.
 */
public class NameRule implements TemplateMethodModelEx {
    @Override
    public Object exec(List list) throws TemplateModelException {
        SimpleScalar simpleScalar = (SimpleScalar) list.get(0);
        String name = simpleScalar.getAsString();
        return nameRule(name);
    }

    public static String nameRule(String name) {
        if (StringUtil.isUpperLetter(name.charAt(1))) {
            int len = name.length() - 1;
            int index = 0;
            for (int i = 1; i < len; i++) {
                if (!StringUtil.isUpperLetter(name.charAt(i + 1))) {
                    index = i - 1;
                    break;
                }
            }
            if (index > 0) {
                for (int i = 0; i <= index; i++) {
                    name = StringUtil.toLowerLetter(name, i);
                }
            }
            return name;

        }
        return StringUtil.toLowerLetter(name, 0);
    }
}
