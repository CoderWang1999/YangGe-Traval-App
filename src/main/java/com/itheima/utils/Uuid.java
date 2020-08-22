package com.itheima.utils;

import java.util.UUID;

/**
 * @author: LHL
 * @date: 2020-08-21 12:31
 */
public class Uuid {
    private Uuid(){}
    public static String getId(){
        return UUID.randomUUID().toString().replaceAll("-","");
    }
}
