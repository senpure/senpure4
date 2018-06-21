package com.senpure.demo;

import org.junit.Test;
import org.springframework.web.servlet.mvc.condition.PatternsRequestCondition;

import java.util.List;

/**
 * Created by 罗中正 on 2018/1/22 0022.
 */
public class PatternsRequestConditionTest  {

    @Test
    public  void p()
    {
        PatternsRequestCondition patternsRequestCondition = new PatternsRequestCondition("/join/**","join/{jkl}");

        List<String>  sts= patternsRequestCondition.getMatchingPatterns("/join/jd/");


        System.out.println(sts);

        PatternsRequestCondition p = new PatternsRequestCondition("/demo/students", "/demo/students/{page}", "/demo/students/**");
        System.out.println(p.getMatchingPatterns("/dem2o/students/5"));

    }

    @Test
    public void druid()
    {
        PatternsRequestCondition patternsRequestCondition = new PatternsRequestCondition("/home","/druid/**.*");


        System.out.println(patternsRequestCondition);
        System.out.println(patternsRequestCondition.getMatchingPatterns("/druid"));
        System.out.println(patternsRequestCondition.getMatchingPatterns("/druid/"));
        System.out.println(patternsRequestCondition.getMatchingPatterns("/druid/abc/jkl"));
        System.out.println(patternsRequestCondition.getMatchingPatterns("/druid/index.html"));
    }
}
