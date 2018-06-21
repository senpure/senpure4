package com.senpure.base.util;

import java.util.Arrays;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 主要是应用于数据库采用utf-8的方式进行存储
 * 将emoji的字符转换为[\u65536]（数字为代码点 codePoint的十进制形式） 的格式，需要的时候在转换回来
 * <p>
 * <br>
 *
 */
public class EmojiUtil {
    private static String regex = "\\[\\\\u\\d{5,}]";
    private static Pattern pattern = Pattern.compile(regex);

    /**
     * 转义emoji字符
     *
     * @param str
     * @return
     */
    public static String emojiEncode(String str) {

        return execute(str, true);
    }

    private static String execute(String str, boolean convert) {
        if (str == null) {
            return null;
        }
        int len = str.length();
        int pointLen = str.codePointCount(0, len);
        if (pointLen == len) {
            return str;
        }
        int firCodeIndex = str.offsetByCodePoints(0, 0);
        int lstCodeIndex = str.offsetByCodePoints(0, pointLen - 1);
        StringBuilder sb = new StringBuilder(len);
        for (int index = firCodeIndex; index <= lstCodeIndex; ) {
            int codePoint = str.codePointAt(index);
            if (Character.isSupplementaryCodePoint(codePoint)) {
                if (convert) {
                    sb.append("[\\u").append(codePoint).append("]");
                }
                index += 2;
            } else {
                sb.append((char) codePoint);
                index++;
            }
        }
        return sb.toString();
    }

    /**
     * 将转义的字符串还原
     * @param emoji
     * @return
     */
    public static String emojiDecode(String emoji) {
        if (emoji == null) {
            return null;
        }
        Matcher matcher = pattern.matcher(emoji);
        StringBuffer sb = new StringBuffer();
        while (matcher.find()) {
            String e = matcher.group();
            int codePoint = Integer.parseInt(e.substring(3, e.length() - 1));
            matcher.appendReplacement(sb, new String(Character.toChars(codePoint)));
        }
        if (sb.length() > 0) {
            matcher.appendTail(sb);
            return sb.toString();
        }
        return emoji;
    }

    /**
     * 将emoji直接过滤掉
     *
     * @param str
     * @return
     */
    public static String emojiFilter(String str) {
        return execute(str, false);
    }

    public static void main(String[] args) {

        EmojiUtil emojiConvert = new EmojiUtil();
        String str = "abC";
        System.out.println(emojiEncode(str));

        int codePoint = 653550;
        char[] c = Character.toChars(codePoint);

        System.out.println(Arrays.toString(c));
        String pstr = new String(c);
        pstr += "788" + pstr + "jinh叫";
        System.out.println("str = " + pstr);
        System.out.println(emojiEncode(pstr));

        // System.out.println("\\u");
        String s = "[\\u653550]788[\\u653550]jinh叫";

        System.out.println(emojiDecode(s));

       int i= Integer.parseInt("1D306", 16);
        System.out.println(i);
        String kk="u+1D306";
        System.out.println(kk);
    }
}
