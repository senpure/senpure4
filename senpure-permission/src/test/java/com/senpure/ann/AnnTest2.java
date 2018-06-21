package com.senpure.ann;

import com.senpure.base.struct.PatternDate;
import com.senpure.base.validator.DynamicDate;
import javassist.NotFoundException;
import org.junit.Test;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Proxy;
import java.util.Map;

/**
 * Created by 罗中正 on 2017/10/25.
 */
public class AnnTest2 {

    @DynamicDate
    private PatternDate patternDate;

    @Test
    public  void annTest() throws NoSuchFieldException, NotFoundException, IllegalAccessException {
        AnnTest2 annTest=new AnnTest2();
        Field field2= annTest.getClass().getDeclaredField("patternDate");
        DynamicDate dynamicDate2=  field2.getAnnotation(DynamicDate.class);



        Field field= this.getClass().getDeclaredField("patternDate");

        System.out.println(field);
       DynamicDate dynamicDate=  field.getAnnotation(DynamicDate.class);
        InvocationHandler h = Proxy.getInvocationHandler(dynamicDate);
        // 获取 AnnotationInvocationHandler 的 memberValues 字段
        Field hField = h.getClass().getDeclaredField("memberValues");
        // 因为这个字段事 private final 修饰，所以要打开权限
        hField.setAccessible(true);
        // 获取 memberValues
        Map memberValues = (Map) hField.get(h);
        // 修改 value 属性值
        memberValues.put("pattern", "ddd");






    }
}
