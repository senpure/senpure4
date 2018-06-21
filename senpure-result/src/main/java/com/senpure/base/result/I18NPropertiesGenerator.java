package com.senpure.base.result;


import com.senpure.base.AppEvn;
import com.senpure.base.util.Pinyin;
import com.senpure.base.util.StringUtil;

import java.io.*;
import java.util.Iterator;
import java.util.Locale;
import java.util.Properties;

/**
 * 将资源文件转换为text ，方便翻译，
 * 将翻译好的text文件转换长资源文件。
 */

public class I18NPropertiesGenerator {

    public static boolean DEV = true;
    private static final String defaultBaseName = "i18n/result/result";
    private static final String textDirectory = "/F:/i18n";
    private static final String textPath = "/F:/i18n";
    private static Locale locale = null;
    private static Locale toLocale = new Locale("zh", "TW");
    //true 表示生成 text文件， false 表示生成properties
    private static boolean toText = false;
    private static boolean toTextKey = false;
    // private static boolean toText = false;
    //如果text没有key值，需要该值座位参照
    private static Locale referLocale = new Locale("");


    /**
     * @param baseName
     * @param locale
     * @param textDirectory
     * @param toTextKey     是否是生成key
     */
    public static void generateText(String baseName, Locale locale, String textDirectory, boolean toTextKey) {
        try {

            if (textDirectory == null) {
                textDirectory = AppEvn.getClassRootPath();
            }
            String localeStr = locale == null ? "" : "_" + locale.toString();
            if (localeStr.length() < 2) {
                localeStr = "";
            }
            File file = new File(AppEvn.getClassRootPath(), baseName + localeStr + ".properties");
            System.out.println("资源路径:" + file.getAbsolutePath());
            Properties pro = new SortProperties();
            pro.load(new FileInputStream(file));
            File save;
            int index = StringUtil.indexOf(baseName, "/", 1, true);
            if (index > -1) {

                save = new File(textDirectory, baseName.substring(index) + ".txt");
            } else {
                save = new File(textDirectory, baseName + ".txt");
            }
            File temp = new File(save.getParent());
            temp.mkdirs();

            System.out.println("text 路径:" + save.getAbsolutePath());
            Iterator<Object> bs = pro.keySet().iterator();
            FileWriter writer = new FileWriter(save);
            while (bs.hasNext()) {
                Object b = bs.next();
                if (toTextKey) {
                    writer.write(b.toString());
                    writer.write("=");
                    System.out.print(b.toString());
                    System.out.print("=");
                }
                writer.write(pro.getProperty(b.toString()));
                writer.write("\r\n");

                System.out.println(pro.getProperty(b.toString()));
                // System.out.println();

            }
            writer.flush();
            writer.close();
            System.out.println("text生成完成 路径:" + save.getAbsolutePath());

        } catch (FileNotFoundException e) {

            e.printStackTrace();
        } catch (IOException e) {

            e.printStackTrace();
        }

    }

    public static void generateProperties(String textDirectory, String baseName, Locale toLocale, Locale referLocale) {

        if (textDirectory == null) {
            textDirectory = AppEvn.getClassRootPath();
        }
        File text;
        int index = StringUtil.indexOf(baseName, "/", 1, true);

        if (index > -1) {

            text = new File(textDirectory, baseName.substring(index) + "_" + toLocale + ".txt");
        } else {
            text = new File(textDirectory, baseName + "_" + toLocale + ".txt");
        }

        try {
            SortProperties props = new SortProperties();
            InputStream inputStream = new FileInputStream(text);
            // new input
            InputStreamReader ir = new InputStreamReader(inputStream, "utf-8");
            BufferedReader reader = new BufferedReader(ir);
            if (referLocale != null) {
                String referLocaleStr = "_" + referLocale.toString();
                if (referLocaleStr.length() < 3) {
                    referLocaleStr = "";
                }
                File file = new File(AppEvn.getClassRootPath(), baseName + referLocaleStr + ".properties");
                Properties pro = new SortProperties();
                pro.load(new FileInputStream(file));
                Iterator<Object> bs = pro.keySet().iterator();
                while (reader.ready()) {
                    String line = reader.readLine();
                    if (line.startsWith("#")) {
                        continue;
                    }
                    System.out.println(line);
                    props.put(bs.next().toString(), line);
                }
            } else {
                while (reader.ready()) {
                    String line = reader.readLine();
                    System.out.println(line);

                    if (line.startsWith("#")) {
                        continue;
                    }
                    // System.out.println(line.endsWith("\n"));
                    int index2 = line.indexOf("=");
                    String key;
                    String value;
                    if (index2 == -1) {
                        key = Pinyin.toAccount(line)[0];
                        value = line;
                    } else {
                        key = line.substring(0, index2);
                        value = line.substring(index2 + 1);
                    }
                    props.put(key, value);
                    System.out.print(key);
                    // System.out.print("=");
                    // System.out.println(value);

                }
            }
            reader.close();
            File save = new File(AppEvn.getClassRootPath() + baseName + "_" + toLocale + ".properties");
            if (DEV) {
                save = new File(new File(AppEvn.getClassRootPath()).getParentFile().getParentFile(), "src/main/resources/" + baseName + "_" + toLocale + ".properties");
            }
            OutputStream outputStream = new FileOutputStream(save);
            //BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(outputStream, "utf-8"));
            // FileWriter writer;
            //
            // writer = new FileWriter(save);
            //
            // props.store(writer, "");
            // writer.close();
            //
            //props.put("key", "value1");
            //props.put("key2", " 你好");
            props.store(outputStream, null);
            System.out.println("资源文件生成完成,路径" + save.getAbsolutePath());

        } catch (IOException e) {

            e.printStackTrace();
        }

    }

    public static void main(String[] args) {

        if (toText) {
            generateText(defaultBaseName, locale, textDirectory, toTextKey);
        } else {

            generateProperties(textPath, defaultBaseName, toLocale, referLocale);
        }

        // System.out.println("123".substring(-1, 1));

    }

}
