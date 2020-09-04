package com.goufaning.mall.db.util;

/**
 * 排序类型
 *
 * @author goufn
 * @version V1.0
 * @date 2020/8/31 1:56 下午
 */
public enum  EnumSortType {
    // 降序
    DESC("desc"),
    // 升序
    ASC("asc"),
    ;

    private String type;

    EnumSortType(String type) {
        this.type = type;
    }

    public String type() {
        return type;
    }
}
