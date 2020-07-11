package com.goufaning.mall.common.utils;

/**
 * 字符串工具
 *
 * @author goufn
 * @version V1.0
 * @date 2020-07-11 15:33
 */
public class StringUtils {

    public static boolean isEmpty(String str) {
        return org.apache.commons.lang3.StringUtils.isEmpty(str);
    }

    public static boolean isBlank(String str) {
        return org.apache.commons.lang3.StringUtils.isBlank(str);
    }
}
