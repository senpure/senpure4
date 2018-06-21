package com.senpure.base.generator;

import com.senpure.base.util.Inflector;
import freemarker.template.SimpleScalar;
import freemarker.template.TemplateMethodModelEx;
import freemarker.template.TemplateModelException;

import java.util.List;

/**
 * Created by 罗中正 on 2017/10/12.
 */
public class Pluralize implements TemplateMethodModelEx {
    @Override
    public Object exec(List list) throws TemplateModelException {
        SimpleScalar simpleScalar= (SimpleScalar) list.get(0);
        return Inflector.getInstance().pluralize(simpleScalar.getAsString());
    }
}
