package com.senpure.base.util;

import net.sourceforge.pinyin4j.PinyinHelper;
import net.sourceforge.pinyin4j.format.HanyuPinyinCaseType;
import net.sourceforge.pinyin4j.format.HanyuPinyinOutputFormat;
import net.sourceforge.pinyin4j.format.HanyuPinyinToneType;
import net.sourceforge.pinyin4j.format.HanyuPinyinVCharType;
import net.sourceforge.pinyin4j.format.exception.BadHanyuPinyinOutputFormatCombination;

import java.util.Arrays;
import java.util.LinkedHashSet;
import java.util.Set;

/**
 *  * dependency pinyin4j
 */
public class Pinyin {

    private static final HanyuPinyinOutputFormat quanpinformat = new HanyuPinyinOutputFormat();

    static {

        quanpinformat.setCaseType(HanyuPinyinCaseType.LOWERCASE);
        quanpinformat.setToneType(HanyuPinyinToneType.WITHOUT_TONE);
        quanpinformat.setVCharType(HanyuPinyinVCharType.WITH_V);
    }

    /**
     * 将中文名字，转换为拼音,全小写  <br>
     * 张三 zhangsan
     * 王重阳 wangzhongyang  wangchongyang
     *
     * @param name
     * @return
     */
    public static String[] toAccount(String name) {

        char[] cs = name.toCharArray();
        String[] results = {};
        int cl = cs.length;
        for (int i = 0; i < cl; i++) {
            String[] pys = null;
            try {
                pys = PinyinHelper.toHanyuPinyinStringArray(cs[i],
                        quanpinformat);
                // 去音调重复
                if (pys != null&&pys.length>0) {
                    Set<String> set = new LinkedHashSet<>(Arrays.asList(pys));
                    pys = new String[set.size()];


                    set.toArray(pys);
                } else {
                    pys = new String[1];
                    pys[0] = String.valueOf(cs[i]);
                }


            } catch (BadHanyuPinyinOutputFormatCombination e) {

                e.printStackTrace();
            }
            //结果拼装
            if (pys != null) {
                int rl = results.length;
                int pl = pys.length;

                if (rl > 0) {
                    String[] temps = new String[rl * pl];
                    int index = 0;
                    for (int j = 0; j < rl; j++) {

                        for (int k = 0; k < pl; k++) {

                            temps[index++] = results[j] + pys[k];
                        }
                    }
                    results = temps;

                } else {
                    results = pys;
                }

            }

        }

        return results;

    }

    public static void main(String[] args) {

        System.out.println(Arrays.toString(toAccount("王j重123阳")));
        try {
            System.out.println(Arrays.toString(PinyinHelper.toHanyuPinyinStringArray('j',
                    quanpinformat)));
        } catch (BadHanyuPinyinOutputFormatCombination badHanyuPinyinOutputFormatCombination) {
            badHanyuPinyinOutputFormatCombination.printStackTrace();
        }

    }
}
