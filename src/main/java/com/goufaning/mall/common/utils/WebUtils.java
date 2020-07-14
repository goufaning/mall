package com.goufaning.mall.common.utils;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * 通用工具类
 *
 * @author goufn
 * @version V1.0
 * @date 2020-07-11 15:53
 */
public class WebUtils {

    public static boolean isEmptyList(Collection<?> collection) {
        return collection == null || collection.isEmpty();
    }

    public static String listToString(Collection<?> collection) {
        if (isEmptyList(collection)) {
            return "";
        }
        StringBuilder sb = new StringBuilder();
        for (Object o : collection) {
            sb.append(o.toString());
            sb.append(",");
        }
        if (sb.length() > 0) {
            sb.deleteCharAt(sb.length() - 1);
        }
        return sb.toString();
    }

    public static List<Integer> stringToList(String str) {
        return stringToList(str, ",");
    }

    public static List<Integer> stringToList(String str, String sep) {
        return stringToList(str, sep, true);
    }


    public static List<Integer> stringToList(String str, String sep, boolean withZero) {
        if (StringUtils.isBlank(str)) {
            return new ArrayList<>();
        }
        if (StringUtils.isBlank(sep)) {
            sep = ",";
        }
        String[] strings = str.split(sep);
        List<Integer> integerList = new ArrayList<>();
        for (String string : strings) {
            if (StringUtils.isBlank(string)) {
                continue;
            }
            int value = Integer.valueOf(string);
            if (withZero || value != 0) {
                integerList.add(value);
            }
        }
        return integerList;
    }
}
