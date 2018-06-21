package com.senpure.jre;

import java.security.ProtectionDomain;

/**
 * Created by 罗中正 on 2017/7/25.
 */
public class Main {
    public static void main2(String[] args) {

        System.out.println("main2=================");
    }
    public static void main(String[] args) {
        ProtectionDomain p=Main.class.getProtectionDomain();
        System.out.println(p);
    }
}
