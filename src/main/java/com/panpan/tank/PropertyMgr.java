package com.panpan.tank;

import jdk.internal.org.objectweb.asm.commons.TryCatchBlockSorter;

import java.io.IOException;
import java.util.Properties;

/**
 * @Date 2021/7/22 21:48
 * @Author LiuPanpan
 */
public class PropertyMgr {
    static Properties properties = new Properties();

    static{
        try{
            properties.load(PropertyMgr.class.getClassLoader().getResourceAsStream("config.properties"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static Object get(String key) {
        if(properties == null) return null;
        return properties.get(key);
    }

    public static void main(String[] args) {
        System.out.println(PropertyMgr.get("initTankCount"));
    }
}
