package com.senpure.base.util;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.SecretKeySpec;
import java.io.UnsupportedEncodingException;
import java.security.InvalidKeyException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * 请自行确保字符串的有意义性 <Br>
 * <b>空字符("") 空格符(" ")可能会产生一些意外的结果
 */
public class StringUtil {


    private StringUtil() {
        super();

    }

    /**
     * 检查字符串是否是数字(支持小数/科学计数法)
     *
     * @param str
     * @return
     */
    public static boolean isNum(String str) {
        boolean result = false;
        try {
            Double.valueOf(str);
            result = true;
        } catch (NumberFormatException e) {

        }
        // 检查字符串是否是数字(支持小数)(不支持科学计数法)
        // str.matches("^[-+]?(([0-9]+)([.]([0-9]+))?|([.]([0-9]+))?)$")
        return result;
    }

    public static boolean isNullOrEmpty(String str) {
        return str == null || str.length() == 0;
    }

    public static boolean isNullOrEmptyTrim(String str) {
        return str == null || str.trim().length() == 0;
    }

    /**
     * 检查字符串是否不为空 注意: 空字符("")返回fale 空格(" ")返回 true
     *
     * @param str
     * @return
     */
    public static boolean isExist(String str) {
        return str != null && str.length() > 0;
    }

    public static boolean isExistTrim(String str) {
        return str != null && str.trim().length() > 0;
    }

    /**
     * 反转字符串(产生新的字符串)
     *
     * @param str
     * @return
     */
    public static String reverse(String str) {
        StringBuilder sb = new StringBuilder(str);
        return sb.reverse().toString();

    }

    /**
     * 统计字符(串)出现的次数<br>
     * <b> 若果待统计的字符为空字符("") 将会被任意字符匹配(包括空字符(""))
     *
     * @param str 字符串源
     * @param arg 待统计的字符(串)
     * @return
     */
    public static int count(String str, String arg) {

        int index = -1;
        int offset = 0;
        int count = 0;
        int offsetNumber = str.length() - 1;
        while (offset < offsetNumber) {
            index = str.indexOf(arg, index + 1);
            if (index < 0) {
                break;
            }
            offset = index;
            count++;
        }
        return count;
    }

    public static int indexOf(String str, String arg) {

        return str.indexOf(arg);
    }

    /**
     * 取得字符(串)，第几次出现的下标，没有则返回-1 <br>
     * <b> 若果待统计的字符为空字符("") 将会被任意字符匹配(包括空字符(""))
     *
     * @param str  字符串源
     * @param arg  待统计的字符(串)
     * @param time 第几次
     * @return
     */
    public static int indexOf(String str, String arg, int time) {

        return indexOf(str, arg, time, false);
    }

    /**
     * 取得字符(串)，第几次出现的下标，没有则返回-1 <br>
     * <b> 若果待统计的字符为空字符("") 将会被任意字符匹配(包括空字符(""))
     *
     * @param str       字符串源
     * @param arg       待统计的字符(串)
     * @param time      第几次
     * @param countdown 是否是倒数
     * @return
     */
    public static int indexOf(String str, String arg, int time,
                              boolean countdown) {
        int count = 0;
        int index = -1;
        if (countdown) {
            time = count(str, arg) - time + 1;
        }
        while (count++ < time) {
            index = str.indexOf(arg, index + 1);
            if (index < 0) {
                break;
            }

        }

        return index;
    }

    /**
     * 是否是大写字母
     *
     * @param c
     * @return
     */
    public static boolean isUpperLetter(char c) {
        return c > 64 && c < 91;
    }

    /**
     * 是否是小写字母
     *
     * @param c
     * @return
     */
    public static boolean isLowerLetter(char c) {
        return c > 96 && c < 123;
    }

    /**
     * 是否是字母
     *
     * @param c
     * @return
     */
    public static boolean isLetter(char c) {

        return c > 96 && c < 123 || (c > 64 && c < 91);
    }

    /**
     * 将第一个字母转换为大写
     *
     * @param str
     * @return
     */
    public static String toUpperFirstLetter(String str) {

        return toUpperLetter(str, 0);
    }

    /**
     * 将下标为index的字母转换为大写
     *
     * @param str
     * @param index
     * @return
     */
    public static String toUpperLetter(String str, int index) {
        byte[] items = str.getBytes();
        char c = (char) items[index];
        if (isLowerLetter(c)) {
            items[index] = (byte) (c - 32);
        }

        return new String(items);
    }

    /**
     * 将下标为index的字母转换为小写
     *
     * @param str
     * @param index
     * @return
     */
    public static String toLowerLetter(String str, int index) {
        byte[] items = str.getBytes();
        char c = (char) items[index];
        if (isUpperLetter(c)) {
            items[index] = (byte) (c + 32);
        }

        return new String(items);
    }

    /**
     * 将第一个字母转换为小写
     *
     * @param str
     * @return
     */
    public static String toLowerFirstLetter(String str) {

        return toLowerLetter(str, 0);
    }

    public static String toUnicode(String str) {
        if (str == null) {
            return null;
        }
        int length = str.length();
        if (length == 0) {
            return null;
        }
        int l = str.length();
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < l; i++) {
            char c = str.charAt(i);

            sb.append("\\u");
            //char16位
            String temp = Integer.toHexString(c & 0xffff);
            if (temp.length() < 4) {
                sb.append("00");
            }
            sb.append(temp);
        }
        return sb.toString();
    }

    public static String aesDecrypt(byte[] encrypted, String key) {
        try {
            byte[] raw = key.getBytes("ASCII");
            SecretKeySpec skeySpec = new SecretKeySpec(raw, "AES");
            Cipher cipher = Cipher.getInstance("AES");
            cipher.init(Cipher.DECRYPT_MODE, skeySpec);
            byte[] original = cipher.doFinal(encrypted);
            return new String(original, "utf-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (InvalidKeyException e) {
            e.printStackTrace();
        } catch (NoSuchPaddingException e) {
            e.printStackTrace();
        } catch (BadPaddingException e) {
            e.printStackTrace();
        } catch (IllegalBlockSizeException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * aes 解密
     *
     * @param encrypted
     * @param key
     * @return
     */

    public static String aesDecryptHex(String encrypted, String key) {

        return aesDecrypt(hex2byte(encrypted), key);
    }


    /**
     * aes 加密
     *
     * @param content
     * @param key
     * @return
     */
    public static byte[] aesEncrypt(String content, String key) {
        try {
            byte[] raw = key.getBytes("ASCII");
            SecretKeySpec skeySpec = new SecretKeySpec(raw, "AES");
            Cipher cipher = Cipher.getInstance("AES");
            cipher.init(Cipher.ENCRYPT_MODE, skeySpec);
            byte[] encrypted = cipher.doFinal(content.getBytes());
            return encrypted;
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (InvalidKeyException e) {
            e.printStackTrace();
        } catch (NoSuchPaddingException e) {
            e.printStackTrace();
        } catch (BadPaddingException e) {
            e.printStackTrace();
        } catch (IllegalBlockSizeException e) {
            e.printStackTrace();
        }
        return null;
    }


    public static String aesEncryptHex(String content, String key) {
        return byte2hex(aesEncrypt(content, key));
    }


    public static String md5(String str) {
        MessageDigest md = null;
        try {
            md = MessageDigest.getInstance("MD5");
        } catch (NoSuchAlgorithmException e) {

            e.printStackTrace();
        }
        md.update(str.getBytes());
        byte[] b = md.digest();
        return byte2hex(b);

    }

    public static String sha(String str) {
        MessageDigest md = null;
        try {
            md = MessageDigest.getInstance("sha");
        } catch (NoSuchAlgorithmException e) {

            e.printStackTrace();
        }
        md.update(str.getBytes());
        byte[] b = md.digest();
        return byte2hex(b);

    }

    public static String byte2hex(byte[] b) {
        StringBuilder sb = new StringBuilder();
        String stmp = null;
        int l = b.length;
        for (int n = 0; n < l; n++) {
            int temp = b[n] & 0XFF;
            //小于16补位0
            if (temp >> 4 == 0) {
                sb.append("0");
            }
            sb.append(Integer.toHexString(temp));
        }
        return sb.toString();
    }

    public static byte[] hex2byte(String hexstr) {
        if (hexstr == null) {
            return null;
        }
        int l = hexstr.length();
        if ((l & 1) == 1) {
            return null;
        }
        byte[] b = new byte[l / 2];
        for (int i = 0; i != l / 2; i++) {
            b[i] = (byte) Integer.parseInt(hexstr.substring(i * 2, i * 2 + 2),
                    16);
        }
        return b;
    }


    /**
     * 连续空格只保留一个空格首尾空格去掉
     *
     * @param str
     * @return
     */
    public static String cutMoreSpace(String str) {
        StringBuilder sb = new StringBuilder(str);

        int index = sb.indexOf("  ");
        while (index > 0) {
            sb.delete(index, index + 1);
            index = sb.indexOf("  ");
        }

        return sb.toString().trim();
    }

    public static void main(String[] args) {
        System.out.println(Integer.toBinaryString(~4));

    }
}
