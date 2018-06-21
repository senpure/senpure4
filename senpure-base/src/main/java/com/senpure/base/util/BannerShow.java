package com.senpure.base.util;


import com.senpure.base.AppEvn;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.ansi.AnsiColor;
import org.springframework.boot.ansi.AnsiOutput;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;


public class BannerShow {
    private static Logger LOGGER = LoggerFactory.getLogger(BannerShow.class);

    static List<String> strs = new ArrayList<>(16);

    static {
        strs.add("      ___           ___           ___           ___           ___           ___           ___");
        strs.add("     /  /\\         /__/\\         /  /\\         /  /\\         /  /\\         /  /\\         /  /\\");
        strs.add("    /  /:/_        \\  \\:\\       /  /:/        /  /:/        /  /:/_       /  /:/_       /  /:/_");
        strs.add("   /  /:/ /\\        \\  \\:\\     /  /:/        /  /:/        /  /:/ /\\     /  /:/ /\\     /  /:/ /\\");
        strs.add("  /  /:/ /::\\   ___  \\  \\:\\   /  /:/  ___   /  /:/  ___   /  /:/ /:/_   /  /:/ /::\\   /  /:/ /::\\");
        strs.add(" /__/:/ /:/\\:\\ /__/\\  \\__\\:\\ /__/:/  /  /\\ /__/:/  /  /\\ /__/:/ /:/ /\\ /__/:/ /:/\\:\\ /__/:/ /:/\\:\\");
        strs.add(" \\  \\:\\/:/~/:/ \\  \\:\\ /  /:/ \\  \\:\\ /  /:/ \\  \\:\\ /  /:/ \\  \\:\\/:/ /:/ \\  \\:\\/:/~/:/ \\  \\:\\/:/~/:/");
        strs.add("  \\  \\::/ /:/   \\  \\:\\  /:/   \\  \\:\\  /:/   \\  \\:\\  /:/   \\  \\::/ /:/   \\  \\::/ /:/   \\  \\::/ /:/");
        strs.add("   \\__\\/ /:/     \\  \\:\\/:/     \\  \\:\\/:/     \\  \\:\\/:/     \\  \\:\\/:/     \\__\\/ /:/     \\__\\/ /:/");
        strs.add("     /__/:/       \\  \\::/       \\  \\::/       \\  \\::/       \\  \\::/        /__/:/        /__/:/");
        strs.add("     \\__\\/         \\__\\/         \\__\\/         \\__\\/         \\__\\/         \\__\\/         \\__\\/");
    }

    public static void show() {
        //LOGGER.debug("\n\n{}", str());
        System.out.println(str());
    }


    public static String str() {
        List<String> strs = null;
        try {
            InputStream
                    inputStream = BannerShow.class.getClassLoader().getResourceAsStream("showbanner.txt");
            // 建立一个输入流对象reader
            if (inputStream != null) {
                InputStreamReader reader = new InputStreamReader(
                        inputStream);
                BufferedReader br = new BufferedReader(reader);
                strs = new ArrayList<>();
                while (br.ready()) {
                    String line = br.readLine();
                    strs.add(line);
                }
                br.close();
                reader.close();
                inputStream.close();
            }

        } catch (IOException e) {

        }
        if (strs == null || strs.size() == 0) {
            strs = BannerShow.strs;
        }
        int length = 0;
        for (int i = 0; i < strs.size(); i++) {
            int tl = strs.get(i).length();
            length = tl > length ? tl : length;
        }
        if (length % 2 != 0) {
            length++;
        }

        StringBuilder banner = new StringBuilder();
        String top = "";
        if (AppEvn.isWindowsOS()) {
            top = getStr("═", length >> 1);
        } else {
            top = getStr("═", length);
        }
        top = "╔" + top + "╗";
        top = AnsiOutput.toString(new Object[]{AnsiColor.BRIGHT_RED, top});
        banner.append(top);
        banner.append("\n");

        for (int i = 0; i < strs.size(); i++) {
            banner.append(AnsiOutput.toString(new Object[]{AnsiColor.BRIGHT_RED, "║"}));
            banner.append(AnsiOutput.toString(new Object[]{AnsiColor.BRIGHT_CYAN, strs.get(i)}));
            int l = strs.get(i).length();
            String temp = getStr(" ", length - l);
            banner.append(AnsiOutput.toString(new Object[]{AnsiColor.BRIGHT_RED, temp + "║"}));
            banner.append("\n");
        }
        String empty;
        empty = getStr(" ", length);
        empty = "║" + empty + "║";
        empty = AnsiOutput.toString(new Object[]{AnsiColor.BRIGHT_RED, empty});
        banner.append(empty);
        banner.append("\n");
        String down = "";
        if (AppEvn.isWindowsOS()) {
            down = getStr("═", length >> 1);
        } else {
            down = getStr("═", length);
        }
        down = "╚" + down + "╝";
        down = AnsiOutput.toString(new Object[]{AnsiColor.BRIGHT_RED, down});
        banner.append(down);
        banner.append("\n");
        return banner.toString();
    }

    private static String getStr(String ch, int num) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < num; i++) {
            sb.append(ch);

        }
        return sb.toString();
    }

    public static void bannerText(String name) {
        List<String> strs = new ArrayList<>();

        try {
            InputStream inputStream;
            inputStream = BannerShow.class.getClassLoader().getResourceAsStream(name);
            // 建立一个输入流对象reader
            InputStreamReader reader = new InputStreamReader(
                    inputStream);
            BufferedReader br = new BufferedReader(reader);
            strs = new ArrayList<>();
            while (br.ready()) {
                String line = br.readLine();
                // strs.add(line);
                line = line.replace("\\", "\\\\");
                System.out.println("strs.add(\"" + line + "\") ;");
            }
            br.close();
            reader.close();
            inputStream.close();
        } catch (IOException e) {

        }
    }

    public static void main(String[] args) {
        System.setProperty("PID", AppEvn.getPid());
        AnsiOutput.setEnabled(AnsiOutput.Enabled.ALWAYS);

        bannerText("showbanner.txt");

        show();
    }
}
