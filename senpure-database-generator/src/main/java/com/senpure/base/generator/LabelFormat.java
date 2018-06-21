package com.senpure.base.generator;

import freemarker.template.SimpleScalar;
import freemarker.template.TemplateMethodModelEx;
import freemarker.template.TemplateModelException;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by 罗中正 on 2017/10/13.
 */
public class LabelFormat implements TemplateMethodModelEx {
    @Override
    public Object exec(List list) throws TemplateModelException {
        SimpleScalar simpleScalar = (SimpleScalar) list.get(0);
        String name = simpleScalar.getAsString();
        return format(name);
    }
   static List<String> str = new ArrayList<>();
    static {
        str.add("i");
        str.add("I");
        str.add("l");

    }
    public static String format(String name) {
        boolean allLetter=true;
        int l = 0;
        int index = -1;
        for (int i = 0; i < name.length(); i++) {
            char c = name.charAt(i);
            if (c > 127) {
                l += 2;
                allLetter=false;
            } else {
                l++;
            }
            if (l >= 8) {
                index = i;
                break;
            }
        }
        if (index > -1) {
            return name.substring(0, index + 1);
        }
        if(name.equals("id"))
        {
            name+="&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;";
        }
        else  if(name.equals("clazzId"))
        {
            name+="&nbsp;";
        }
        for (int i = l; i < 8; i++) {
            name = name + "&nbsp;";
        }



        return name;
    }
}
