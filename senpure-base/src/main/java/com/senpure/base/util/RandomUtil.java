package com.senpure.base.util;

import java.util.Random;

/**
 * Created by 罗中正 on 2017/4/10.
 */
public class RandomUtil {

    private static Random random=new Random();

    /**
     * 随机0-num 不含 num
     * @param num
     * @return
     */
    public static int random(int num)
    {

        return random.nextInt(num);
    }

    /**
     *
     * @param start 包含
     * @param end 不含
     * @return
     */
    public static int random(int start,int end)
    {

        Assert.isTrue(start<end,"参数不合法");

        return random.nextInt(end-start)+start;
    }
    public static void main(String[] args) {


        for (int i = 0; i <100 ; i++) {

            System.out.println(random(3,5));
        }

    }
}
