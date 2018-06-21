package com.senpure.template.sovereignty;

import freemarker.template.Template;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Soverignty
 *
 * @author 罗中正
 * @email senpure@senpure.com
 * @github https://github.com/senpure
 * @date 2018-05-08 10:39
 */
public class Sovereignty {


    private static Sovereignty sovereignty = new Sovereignty();

    public static Sovereignty getInstance() {
        return sovereignty;
    }

    private List<String> sovereignties = new ArrayList<>();
    private List<String> javaSovereignties = new ArrayList<>();
    private Map<Template, Boolean> checks = new HashMap<>();

    private Sovereignty() {
        sovereignties.add("author    罗中正");
        sovereignties.add("email     senpure@senpure.com");
        sovereignties.add("github    https://github.com/senpure");
        javaSovereignties.add("author 罗中正");
        javaSovereignties.add("email senpure@senpure.com");
        javaSovereignties.add("github https://github.com/senpure");
    }

    public String sovereignty() {
        return sovereignty("", false,sovereignties);
    }

    public String sovereigntyJavaComment() {
        return sovereignty(" * @",true,javaSovereignties);
    }

    public String sovereigntyLuaComment() {
        return sovereignty("");
    }

    public String sovereigntyLuaLRKComment() {
        List<String> sovereignties = new ArrayList<>();
        sovereignties.add("author    李荣科    罗中正");
        sovereignties.add("email     senpure@senpure.com");
        sovereignties.add("github    https://github.com/senpure");
        return sovereignty("",true,sovereignties);
    }

    private String sovereignty(String prefix) {

        return sovereignty(prefix, true,sovereignties);
    }

    private String sovereignty(String prefix, boolean newLine,List<String> sovereignties) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < sovereignties.size(); i++) {
            if (i > 0 && newLine) {
                if (newLine) {
                    sb.append("\n");
                } else {
                    sb.append("    ");
                }
            }
            sb.append(prefix).append(sovereignties.get(i));
        }
        return sb.toString();
    }

    public boolean checkSovereignty(Template template) {
        Boolean result = checks.get(template);
        if (result != null) {
            return result;
        }
        result = template.toString().contains("${sovereignty}");
        checks.put(template, result);
        return result.booleanValue();
    }

}

