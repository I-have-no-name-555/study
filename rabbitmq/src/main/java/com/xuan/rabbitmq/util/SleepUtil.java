package com.xuan.rabbitmq.util;

/**
 * @author :Xuan
 * @date :Create in 2021/7/3 19:17
 * @description
 */
public class SleepUtil {
    public static void sleep(int seconds){
        try {
            Thread.sleep(seconds * 1000L);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
